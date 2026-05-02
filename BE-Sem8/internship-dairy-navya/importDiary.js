import fs from "fs";

const mdPath = "diary.md";
const jsonPath = "diary_entries.json";

if (!fs.existsSync(mdPath)) {
  console.error(`❌ ${mdPath} not found.`);
  process.exit(1);
}

if (!fs.existsSync(jsonPath)) {
  console.error(`❌ ${jsonPath} not found.`);
  process.exit(1);
}

const mdContent = fs.readFileSync(mdPath, "utf-8");
const jsonEntries = JSON.parse(fs.readFileSync(jsonPath, "utf-8"));

// Regex to match entry blocks in Markdown
// Matches: ## Date \n\n **Entry:** Description \n\n **Learnings:** Learnings
const entryRegex =
  /## (\d{1,2} \w+ 2026)\s+\*\*Entry:\*\* ([\s\S]*?)\s+\*\*Learnings:\*\* ([\s\S]*?)(?=\n\n\*\*Hours:\*\*|\n\n##|---|$)/g;

const months = {
  January: "01",
  February: "02",
  March: "03",
  April: "04",
  May: "05",
  June: "06",
  July: "07",
  August: "08",
  September: "09",
  October: "10",
  November: "11",
  December: "12",
};

const formatDateToISO = (dateStr) => {
  const [day, monthName, year] = dateStr.split(" ");
  const month = months[monthName];
  const paddedDay = day.padStart(2, "0");
  return `${year}-${month}-${paddedDay}`;
};

let match;
let updatedCount = 0;

while ((match = entryRegex.exec(mdContent)) !== null) {
  const [_, dateStr, description, learnings] = match;
  const isoDate = formatDateToISO(dateStr);

  const entry = jsonEntries.find((e) => e.date === isoDate);
  if (entry) {
    entry.description = description.trim();
    entry.learnings = learnings.trim();
    updatedCount++;
  } else {
    console.warn(`⚠️ No JSON entry found for date: ${isoDate}`);
  }
}

fs.writeFileSync(jsonPath, JSON.stringify(jsonEntries, null, 2));
console.log(
  `✅ Successfully synced ${updatedCount} entries from MD back to JSON.`,
);
