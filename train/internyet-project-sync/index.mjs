import fs from "fs";
import csv from "csv-parser";

const API_URL =
  "https://vtuapi.internyet.in/api/v1/student/project-diaries/store";

const JWT_TOKEN = "";
const PROJECT_ID = 13829;

/* ------------------ SKILL MAP ------------------ */
const skillMap = {
  "JavaScript": 1,
  "PHP": 2,
  "Python": 3,
  "Laravel": 4,
  "CakePHP": 5,
  "WordPress": 6,
  "Flutter": 7,
  "FilamentPHP": 8,
  "React.js": 9,
  "Java": 10,
  "C++": 11,
  "AWS": 12,
  "Azure": 13,
  "Google Cloud": 14,
  "Machine learning": 15,
  "Data visualization": 16,
  "Statistical analysis": 17,
  "Network architecture": 18,
  "Database design": 19,
  "SQL": 20,
  "NoSQL": 21,
  "MongoDB": 22,
  "Cassandra": 23,
  "DevOps": 24,
  "TensorFlow": 25,
  "PyTorch": 26,
  "computer vision": 27,
  "Natural language processing": 28,
  "HTML": 29,
  "CSS": 30,
  "React": 31,
  "Angular": 32,
  "Vue.js": 33,
  "Node.js": 34,
  "Ruby on Rails": 35,
  "CodeIgniter": 36,
  "IaaS": 37,
  "PaaS": 38,
  "SaaS": 39,
  "Cloud access control": 40,
  "Data encryption": 41,
  "MySQL": 42,
  "PostgreSQL": 43,
  "Data modeling": 44,
  "Indexing": 45,
  "TCP/IP": 46,
  "DHCP": 47,
  "LAN": 48,
  "WAN": 49,
  "Firewall configuration": 50,
  "Keras": 51,
  "VPNs": 52,
  "scikit-learn": 53,
  "Tableau": 54,
  "Power BI": 55,
  "D3.js": 56,
  "Xamarin": 57,
  "Swift": 58,
  "Objective-C": 59,
  "Xcode": 60,
  "Android Studio": 61,
  "Kotlin": 62,
  "Git": 63,
  "Kubernetes": 64,
  "Docker": 65,
  "TypeScript": 66,
  "VLSI Design": 67,
  "Circuit Design": 68,
  "Layout Design": 69,
  "Physical Design": 70,
  "Digital Design": 71,
  "Design with FPGA": 72,
  "Verification & Validations": 73,
  "IoT": 74,
  "Embedded Systems": 75,
  "Intelligent Machines": 76,
  "BIM FOR CONSTRUCTION": 77,
  "BIM FOR ARCHITECTURE": 78,
  "INTERIOR AND EXTERIOR DESIGN": 79,
  "BIM FOR STRUCTURES": 80,
  "BIM FOR HIGHWAY ENGINEERING": 81,
  "PRODUCT DESIGN & 3D PRINTING": 82,
  "PRODUCT DESIGN & MANUFACTURING": 83,
  "BIM CONCEPTS WITH MEP AND PRODUCT DESIGN": 84,
  "3D PRINTING CONCEPTS, DESIGN AND PRINTING": 85,
  "Manufacturing": 86
};

/* ------------------ HELPERS ------------------ */
function toISODate(mmddyyyy) {
  console.log(mmddyyyy)
  const [mm, dd, yyyy] = mmddyyyy.split("-");
  return `${yyyy}-${mm.padStart(2, "0")}-${dd.padStart(2, "0")}`;
}

function mapSkills(skillString) {
  if (!skillString) return [];
  return skillString
    .split(",")
    .map(s => s.trim())
    .map(s => skillMap[s])
    .filter(Boolean)
    .map(id => String(id)); // API expects strings
}

/* ------------------ MAIN ------------------ */
async function uploadRow(row) {
  const payload = {
    project_id: PROJECT_ID,
    date: toISODate(row["date"]),
    description: row["What I worked on? (work summary)"],
    hours: Number(row["Hours worked *"]),
    links: row["Reference Links"] || "",
    blockers: row["Blockers / Risks"] || "None",
    learnings: row["Learnings / Outcomes *"],
    mood_slider: 5,
    skill_ids: mapSkills(row["skills"])
  };

  const res = await fetch(API_URL, {
    method: "POST",
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json",
      "Cookie": `${JWT_TOKEN}`
    },
    body: JSON.stringify(payload)
  });

  if (!res.ok) {
    const txt = await res.text();
    throw new Error(`Failed (${res.status}): ${txt}`);
  }

  const json = await res.json();
  console.log("✅ Uploaded:", payload.date);
  return json;
}

/* ------------------ CSV RUNNER ------------------ */
async function run(csvFilePath) {
  const rows = [];

  fs.createReadStream(csvFilePath)
    .pipe(csv())
    .on("data", row => rows.push(row))
    .on("end", async () => {
      console.log(`📄 Read ${rows.length} rows`);
      for (const row of rows) {
        try {
          await uploadRow(row);
        } catch (err) {
          console.error("❌ Error:", err.message);
        }
      }
    });
}

/* ------------------ START ------------------ */
run("./diary.csv");
