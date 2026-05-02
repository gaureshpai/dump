import fs from "fs";

const internship_id = 6581;
const baseUrl = "https://vtuapi.internyet.in/api/v1/student/internship-diaries";
const skillsUrl = "https://vtuapi.internyet.in/api/v1/student/skills";
const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
  Cookie:
    "_ga=GA1.1.655019714.1775129603; twk_uuid_689c7188a7ee3319309bdeae=%7B%22uuid%22%3A%221.Sx1KFVirnWgt5BeN8MRZr59ndGFvwnCvyL0Alj6CygZJfNaELyKmitMQQwDJ604PTfHJY5xRON6R5QQGdoSSG6E8zpUwyF7PHqZUeKUSKU2W2EhA2uAEi%22%2C%22version%22%3A3%2C%22domain%22%3A%22internyet.in%22%2C%22ts%22%3A1777647723900%7D; _ga_FRQJNHYVRZ=GS2.1.s1777647713$o18$g1$t1777647729$j44$l0$h0; access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc2NDc3MzYsImV4cCI6MTc3NzY1MTMzNiwibmJmIjoxNzc3NjQ3NzM2LCJqdGkiOiJPQXNWWUlJUk9sV0pNOG5kIiwic3ViIjoiNjk4NzQiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.01Yvv8CN-i7lxzrfX_ZaM6h9xZLQvvzwEg3CtLje5ns; refresh_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc2NDc3MzYsImV4cCI6MTc3NzY2Nzg5NiwibmJmIjoxNzc3NjQ3NzM2LCJqdGkiOiIyNTlZSHBJY0pNSE1wTFFRIiwic3ViIjoiNjk4NzQiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3IiwidHlwZSI6InJlZnJlc2gifQ.fRvPNdVkL_LC2VuDdE8o3QC08UI3SBfJHYOgtv7TFow"};

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
            `✅ Updated ${entry.date}: ${resJson?.message || "Success"}`
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
