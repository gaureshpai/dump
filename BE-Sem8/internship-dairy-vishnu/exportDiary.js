import fs from "fs";
import PDFDocument from "pdfkit";

const entries = JSON.parse(fs.readFileSync("diary_entries.json", "utf-8"));

entries.sort((a, b) => new Date(a.date) - new Date(b.date));

const totalHours = entries.reduce((sum, e) => sum + (e.hours || 0), 0);

const isNothingMuch = (text) => {
  if (!text) return false;
  const normalized = text.toLowerCase().trim();
  return (
    normalized === "nothing much" ||
    normalized ===
      "nothing much but preserving the session token is a big deal" ||
    normalized === "no blockers"
  );
};

const formatDate = (dateStr) => {
  const date = new Date(dateStr);
  const day = date.getDate();
  const month = date.toLocaleString("en-US", { month: "long" });
  const year = date.getFullYear();
  return `${day} ${month} ${year}`;
};

const generateMarkdown = () => {
  let md = "# Internship Diary\n\n";
  md += "****NAME:**** Vishnu Chandroth \n";
  md += "****USN:**** 4JK22CS061  \n";
  md +=
    "****COMPANY:**** Take it Smart  \n";
  md += "****DESIGNATION:**** Internship in Data Science  \n\n---\n\n";

  let runningTotal = 0;

  for (const entry of entries) {
    const date = entry.date;
    const hours = entry.hours || 0;
    runningTotal += hours;
    const description = entry.description || "No work description provided";
    let learnings = entry.learnings || "N/A";
    const blockers = entry.blockers || null;

    if (isNothingMuch(learnings)) {
      learnings =
        "Continued working on frontend development and codebase exploration";
    }

    md += `## ${formatDate(date)}\n\n`;
    md += `**Entry:** ${description}\n\n`;
    md += `**Learnings:** ${learnings}\n\n`;
    md += `**Hours:** ${hours} hours\n`;
    md += `**Total Hours:** ${runningTotal}\n\n`;
    if (blockers) {
      md += `**Blockers:** ${blockers}\n\n`;
    }
    md += `---\n\n`;
  }

  fs.writeFileSync("diary.md", md);
  console.log("✅ Saved to diary.md");
};

const generatePDF = () => {
  const doc = new PDFDocument({ size: "A4", margin: 50 });
  const stream = fs.createWriteStream("diary.pdf");

  doc.pipe(stream);

  const pageHeight = 842;
  const pageWidth = 595;

  const drawBorder = () => {
    doc.rect(25, 25, pageWidth - 50, pageHeight - 50).stroke();
  };

  // Automatically draw border on every new page
  doc.on("pageAdded", () => {
    drawBorder();
  });

  drawBorder();

  doc
    .fontSize(22)
    .font("Helvetica-Bold")
    .text("Internship Diary", { align: "center" });
  doc
    .fontSize(12)
    .font("Helvetica-Bold")
    .text("Name: Vishnu Chandroth", { align: "center" });
  doc.font("Helvetica-Bold").text("USN: 4JK22CS061", { align: "center" });
  doc
    .font("Helvetica-Bold")
    .text("Company: Take it Smart", {
      align: "center",
    });
  doc
    .font("Helvetica-Bold")
    .text("Designation: Internship in Data Science", { align: "center" });
  doc.moveDown();
  doc
    .strokeColor("black")
    .lineWidth(1)
    .moveTo(50, doc.y)
    .lineTo(550, doc.y)
    .stroke();
  doc.moveDown(2);

  let runningTotal = 0;
  const bottomMargin = 60;

  for (let i = 0; i < entries.length; i++) {
    const entry = entries[i];
    const hours = entry.hours || 0;
    runningTotal += hours;
    const description = entry.description || "No work description provided";
    let learnings = entry.learnings || "N/A";
    const blockers = entry.blockers || null;

    if (isNothingMuch(learnings)) {
      learnings =
        "Continued working on frontend development and codebase exploration";
    }

    // Calculate required height for the entire entry
    let requiredHeight = 0;
    const testDoc = new PDFDocument({ size: "A4", margin: 50 }); // Temporary doc for height calculation

    // We can simulate the text to get height
    requiredHeight += doc.heightOfString(formatDate(entry.date), {
      font: "Helvetica-Bold",
      size: 14,
    });
    requiredHeight += 10; // doc.moveDown(0.3)
    requiredHeight += doc.heightOfString("Entry:", {
      font: "Helvetica-Bold",
      size: 10,
    });
    requiredHeight += doc.heightOfString(description, {
      font: "Helvetica",
      size: 10,
    });
    requiredHeight += 5; // doc.moveDown(0.2)
    requiredHeight += doc.heightOfString("Learnings:", {
      font: "Helvetica-Bold",
      size: 10,
    });
    requiredHeight += doc.heightOfString(learnings, {
      font: "Helvetica",
      size: 10,
    });
    requiredHeight += 5; // doc.moveDown(0.2)
    requiredHeight += doc.heightOfString(`Hours: ${hours} hours`, {
      font: "Helvetica-Bold",
      size: 10,
    });
    requiredHeight += doc.heightOfString(`Total: ${runningTotal} hours`, {
      font: "Helvetica-Bold",
      size: 10,
    });
    requiredHeight += 15; // doc.moveDown(0.4)

    if (blockers) {
      requiredHeight += doc.heightOfString("Blockers:", {
        font: "Helvetica-Bold",
        size: 10,
      });
      requiredHeight += doc.heightOfString(blockers, {
        font: "Helvetica",
        size: 10,
      });
      requiredHeight += 5; // doc.moveDown(0.2)
    }

    requiredHeight += 30; // Extra spacing between entries

    // If entry doesn't fit on the current page, move to next
    if (doc.y + requiredHeight > pageHeight - bottomMargin) {
      doc.addPage();
      // Border is drawn automatically by the event listener
    }

    doc.fontSize(14).font("Helvetica-Bold").text(formatDate(entry.date));
    doc.moveDown(0.3);

    doc.fontSize(10).font("Helvetica-Bold").text("Entry:");
    doc.font("Helvetica").text(description);
    doc.moveDown(0.2);

    doc.font("Helvetica-Bold").text("Learnings:");
    doc.font("Helvetica").text(learnings);
    doc.moveDown(0.2);

    doc.font("Helvetica-Bold").text(`Hours: ${hours} hours`);
    doc.font("Helvetica-Bold").text(`Total: ${runningTotal} hours`);
    doc.moveDown(0.4);

    if (blockers) {
      doc.font("Helvetica-Bold").text("Blockers:");
      doc.font("Helvetica").text(blockers);
      doc.moveDown(0.2);
    }

    doc.moveDown(1.5);
  }

  doc.end();
  stream.on("finish", () => {
    console.log("✅ Saved to diary.pdf");
  });
};

generateMarkdown();
generatePDF();
