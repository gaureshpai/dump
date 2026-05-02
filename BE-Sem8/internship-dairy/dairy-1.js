import fs from "fs";

const internship_id = 6581;
const baseUrl = "https://vtuapi.internyet.in/api/v1/student/internship-diaries";
const skillsUrl = "https://vtuapi.internyet.in/api/v1/student/skills";
const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
  Cookie:
    "_ga=GA1.1.1298826862.1768596501; _ga_FRQJNHYVRZ=GS2.1.s1769845317$o9$g0$t1769845317$j60$l0$h0; twk_uuid_689c7188a7ee3319309bdeae=%7B%22uuid%22%3A%221.Sx09s0L7l9KgWQxU0sDaD8eYWdEB8E5QDveNO178rskXD5PId1dGOdN9bztWXYKdX8zk4XqwEJ3GOurxUWWojUpx8xPKifsrBUAfbWLxFZPuGPQ3rn0yE%22%2C%22version%22%3A3%2C%22domain%22%3A%22internyet.in%22%2C%22ts%22%3A1777646235070%7D; access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc2NDYyNDIsImV4cCI6MTc3NzY0OTg0MiwibmJmIjoxNzc3NjQ2MjQyLCJqdGkiOiJyRFJaRnFhUlRxcWdtaFphIiwic3ViIjoiNjk4MjMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.3J6lHgZffQ4npx2rjgwU4TuWWUbGmvX-k6rg35u1tVc; refresh_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc2NDYyNDIsImV4cCI6MTc3NzY2NjQwMiwibmJmIjoxNzc3NjQ2MjQyLCJqdGkiOiJHYnZLTlNsNVlvTXVVR3owIiwic3ViIjoiNjk4MjMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3IiwidHlwZSI6InJlZnJlc2gifQ.MwRhzA1T8t2Nho9pHDUw2J2n0J7NVr39DRiPEtaQm7E",
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
      await new Promise((resolve) => setTimeout(resolve, 5000));

      const existingId = await checkEntry(entry.date);

      if (existingId === "RATE_LIMIT") {
        console.error("Rate limit hit during check. Stopping.");
        return;
      }

      if (existingId) {
        console.log(`Entry exists (ID: ${existingId}). Updating...`);
        // Delay before update
        await new Promise((resolve) => setTimeout(resolve, 5000));

        const updatePayload = {
          id: existingId,
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

        const res = await fetch(`${baseUrl}/store`, {
          method: "POST",
          headers,
          body: JSON.stringify(updatePayload),
        });
        const resJson = await safeJson(res);
        if (res.ok) {
          console.log(
            `✅ Updated ${entry.date}: ${resJson?.message || "Success"}`,
          );
        } else {
          console.error(`❌ Failed to update ${entry.date}: ${res.status}`);
          console.error("Response:", JSON.stringify(resJson));
          if (res.status === 429) {
            console.error("Rate limit hit during update. Stopping execution.");
            return;
          }
        }
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
      await new Promise((resolve) => setTimeout(resolve, 5000));

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
