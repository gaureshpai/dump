import fs from "fs";
import PDFDocument from "pdfkit";

const entries = JSON.parse(fs.readFileSync("diary_entries.json", "utf-8"));

entries.sort((a, b) => new Date(a.date) - new Date(b.date));

const totalHours = entries.reduce((sum, e) => sum + (e.hours || 0), 0);

const isNothingMuch = (text) => {
  if (!text) return false;
  const normalized = text.toLowerCase().trim();
  return normalized === "nothing much" || normalized === "nothing much but preserving the session token is a big deal" || normalized === "no blockers";
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
  md += "****NAME:**** YOUR_NAME  \n";
  md += "****USN:**** YOUR_USN  \n";
  md += "****COMPANY:**** YOUR_COMPANY  \n";
  md += "****DESIGNATION:**** YOUR_DESIGNATION  \n\n---\n\n";

  let runningTotal = 0;

  for (const entry of entries) {
    const date = entry.date;
    const hours = entry.hours || 0;
    runningTotal += hours;
    const description = entry.description || "No work description provided";
    let learnings = entry.learnings || "N/A";
    const blockers = entry.blockers || null;

    if (isNothingMuch(learnings)) {
      learnings = "Continued working on frontend development and codebase exploration";
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

  drawBorder();

  doc.fontSize(22).font("Helvetica-Bold").text("Internship Diary", { align: "center" });
  doc.fontSize(12).font("Helvetica-Bold").text("Name: Gauresh G Pai", { align: "center" });
  doc.font("Helvetica-Bold").text("USN: 4JK22CS016", { align: "center" });
  doc.font("Helvetica-Bold").text("Company: InUnity Pvt Ltd", { align: "center" });
  doc.font("Helvetica-Bold").text("Designation: Frontend Developer", { align: "center" });
  doc.moveDown();
  doc.strokeColor("black").lineWidth(1).moveTo(50, doc.y).lineTo(550, doc.y).stroke();
  doc.moveDown(2);

  let runningTotal = 0;
  const firstPageStart = 180;
  const laterPageStart = 80;

  const checkAndMoveToNextPage = (entryNum) => {
    const estimatedHeight = 110;
    const availableSpace = pageHeight - doc.y - 60;
    if (entryNum > 0 && availableSpace < estimatedHeight) {
      doc.addPage();
      drawBorder();
      doc.y = laterPageStart;
    } else if (entryNum > 0) {
      doc.y += 40;
    } else {
      doc.y = firstPageStart;
    }
  };

  for (let i = 0; i < entries.length; i++) {
    const entry = entries[i];
    checkAndMoveToNextPage(i);

    const hours = entry.hours || 0;
    runningTotal += hours;
    const description = entry.description || "No work description provided";
    let learnings = entry.learnings || "N/A";
    const blockers = entry.blockers || null;

    if (isNothingMuch(learnings)) {
      learnings = "Continued working on frontend development and codebase exploration";
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
  }

  doc.end();
  stream.on("finish", () => {
    console.log("✅ Saved to diary.pdf");
  });
};

generateMarkdown();
generatePDF();
