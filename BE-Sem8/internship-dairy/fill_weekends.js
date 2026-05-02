import fs from "fs";

const entriesPath = "diary_entries.json";
const today = new Date("2026-05-01");
const startDate = new Date("2026-01-29");

const description = "A full-stack Todo application built with modern technologies including Protocol Buffers, Connect RPC, React, TypeScript, Express.js, and MongoDB. with the link https://todo-with-protobuf.vercel.app/ Modern Todo Application (Monorepo)";
const learnings = "A high-performance, type-safe Todo application built with a modern tech stack. This monorepo demonstrates the integration of Protocol Buffers (Connect RPC), React 19, and a TypeScript backend with MongoDB. Tech Stack: React 19, Vite, TanStack Router, TanStack Query, Tailwind CSS, shadcn/ui, Biome, Node.js, Express, Connect-RPC, Mongoose, TypeScript, pnpm, Turborepo.";

function getDatesInRange(start, end) {
  const dates = [];
  let curr = new Date(start);
  while (curr <= end) {
    dates.push(new Date(curr).toISOString().split("T")[0]);
    curr.setDate(curr.getDate() + 1);
  }
  return dates;
}

function isWeekend(dateStr) {
  const day = new Date(dateStr).getDay();
  return day === 0 || day === 6; // 0 is Sunday, 6 is Saturday
}

let entries = [];
try {
  entries = JSON.parse(fs.readFileSync(entriesPath, "utf-8"));
} catch (e) {
  console.error("Error reading entries:", e);
}

const existingDates = new Set(entries.map(e => e.date));
const allDates = getDatesInRange(startDate, today);

let addedCount = 0;
allDates.forEach(date => {
  if (isWeekend(date) && !existingDates.has(date)) {
    entries.push({
      date: date,
      description: description,
      hours: 8,
      learnings: learnings,
      blockers: "None",
      mood_slider: 5,
      status: 2,
      internship: { name: "Frontend Developer" }
    });
    addedCount++;
    console.log(`Added entry for ${date}`);
  }
});

// Sort entries by date descending to match original format
entries.sort((a, b) => new Date(b.date) - new Date(a.date));

fs.writeFileSync(entriesPath, JSON.stringify(entries, null, 2));
console.log(`Done! Added ${addedCount} entries.`);
