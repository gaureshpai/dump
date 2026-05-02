import fs from "fs";

const internship_id = 4570;
const baseUrl = "https://vtuapi.internyet.in/api/v1/student/internship-diaries";
const skillsUrl = "https://vtuapi.internyet.in/api/v1/student/skills";
const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
  Cookie:
        "_ga=GA1.1.1188540200.1754661119; twk_uuid_689c7188a7ee3319309bdeae=%7B%22uuid%22%3A%221.SwzLxrJJpd2aXhpRDRLsmA2AFx8AcHDsIaLIjuKwh8XeIoYA7CoaplBXEqB0paCRTQDUzQuAOsuE5jCmPNDEH03ivm2MK5P0TFQmw5qbO3MOoaYXp4eRm%22%2C%22version%22%3A3%2C%22domain%22%3A%22internyet.in%22%2C%22ts%22%3A1777700308065%7D; _ga_FRQJNHYVRZ=GS2.1.s1777696689$o16$g1$t1777700325$j41$l0$h0; access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc3MDAzMzMsImV4cCI6MTc3NzcwMzkzMywibmJmIjoxNzc3NzAwMzMzLCJqdGkiOiJTSWpyTEx6ZWxRbEJ2UkVQIiwic3ViIjoiNzQzMCIsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.kBNHqnFjpkqGQpsCASLqbg_0zxtyJohfLEE2DLClMe4; refresh_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc3MDAzMzQsImV4cCI6MTc3NzcyMDQ5MywibmJmIjoxNzc3NzAwMzM0LCJqdGkiOiJPb2FRZUtSMUg4REtyY3dYIiwic3ViIjoiNzQzMCIsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjciLCJ0eXBlIjoicmVmcmVzaCJ9.A_QCjEAKY78ZmdkVkjex7PYcqQrUR1I61ZSm3bxjj9s" ,
};

async function safeJson(res) {
  const text = await res.text();
  if (!text || text.trim() === "") return null;
  try {
    return JSON.parse(text);
  } catch (e) {
    console.error("Failed to parse JSON. Raw response:", text.slice(0, 500));
    return null;
  }
}

async function getSkillIds() {
  console.log("Fetching available skills...");
  try {
    const res = await fetch(skillsUrl, { headers });
    const json = await safeJson(res);
    if (json && json.success && json.data) {
      const reactSkill = json.data.find((s) =>
        s.name.toLowerCase().includes("react"),
      );
      const tsSkill = json.data.find((s) =>
        s.name.toLowerCase().includes("typescript"),
      );
      const ids = [];
      if (reactSkill) ids.push(reactSkill.id);
      if (tsSkill) ids.push(tsSkill.id);

      if (ids.length > 0) {
        console.log(`Found skill IDs: ${ids.join(", ")}`);
        return ids;
      }
      return json.data.slice(0, 2).map((s) => s.id);
    }
  } catch (e) {
    console.error("Error fetching skills:", e.message);
  }
  return [14, 17];
}

async function checkEntry(date) {
  const url = `${baseUrl}/show?internship_id=${internship_id}&date=${date}`;
  try {
    const res = await fetch(url, { headers });
    if (res.status === 429) {
      return "RATE_LIMIT";
    }
    const json = await safeJson(res);
    if (json && json.success && json.data && json.data.id) {
      return json.data.id;
    }
  } catch (e) {
    // Silence other check errors
  }
  return null;
}

async function processDiaries() {
  const skillIds = await getSkillIds();
  console.log("Using skill_ids:", skillIds);

  let localEntries;
  try {
    localEntries = JSON.parse(fs.readFileSync("diary_entries.json", "utf-8"));
    console.log(`Loaded ${localEntries.length} entries.`);
  } catch (error) {
    console.error("Error reading diary_entries.json:", error);
    return;
  }

  for (const entry of localEntries) {
    console.log(`Processing ${entry.date}...`);

    try {
      // Small delay before check
      await new Promise((resolve) => setTimeout(resolve, 2000));

      const existingId = await checkEntry(entry.date);

      if (existingId === "RATE_LIMIT") {
        console.error("Rate limit hit during check. Stopping.");
        return;
      }

      if (existingId) {
        console.log(`Entry exists (ID: ${existingId}). Skipping...`);
        continue;
      }

      const payload = {
        internship_id: internship_id,
        date: entry.date,
        description: entry.description,
        hours: entry.hours,
        learnings: entry.learnings,
        blockers: entry.blockers,
        mood_slider: entry.mood_slider || 5,
        status: 2,
        skill_ids: skillIds,
      };

      console.log("Entry does not exist. Creating...");
      // Another delay before create
      await new Promise((resolve) => setTimeout(resolve, 2000));

      const res = await fetch(`${baseUrl}/store`, {
        method: "POST",
        headers,
        body: JSON.stringify(payload),
      });
      const resJson = await safeJson(res);
      if (res.ok) {
        console.log(
          `✅ Success for ${entry.date}: ${resJson?.message || "Created"}`,
        );
      } else {
        console.error(`❌ Failed for ${entry.date}: ${res.status}`);
        console.error("Response:", JSON.stringify(resJson));
        if (res.status === 429) {
          console.error("Rate limit hit during store. Stopping execution.");
          return;
        }
      }
    } catch (error) {
      console.error(`Error processing ${entry.date}:`, error.message);
    }
  }

  console.log("Done.");
}

processDiaries();
