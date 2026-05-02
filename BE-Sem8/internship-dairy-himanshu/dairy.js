import fs from "fs";

async function fetchAndSaveDiaries() {
  const baseUrl =
    "https://vtuapi.internyet.in/api/v1/student/internship-diaries";

  let url = baseUrl;
  let allData = [];

  while (url) {
    const res = await fetch(url, {
      method: "GET",
      headers: {
        Accept: "application/json",
        Cookie:
          "_ga=GA1.1.1290560616.1777437167; twk_uuid_689c7188a7ee3319309bdeae=%7B%22uuid%22%3A%221.Sx1e8Vc5AcwtDh6M6kX2RPsvY99oSeYVECxg7kYRC2kBCLiugRjGv4p0WdtyneTWGOC73kl78lge6r0VWBSBxftgV8ksKpK87xSxDAMHgSa19fS76kRtE%22%2C%22version%22%3A3%2C%22domain%22%3A%22internyet.in%22%2C%22ts%22%3A1777437170884%7D; access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc0MzcyMzQsImV4cCI6MTc3NzQ0MDgzNCwibmJmIjoxNzc3NDM3MjM0LCJqdGkiOiI2NVR3ZUZmcTN0TXJvNmUxIiwic3ViIjoiNjk3MjciLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.eTWnb7LiFkU-oAv9Ldys6p1eVIchysxG0m_Q3y0CcRo; refresh_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc0MzcyMzUsImV4cCI6MTc3NzQ1NzM5NCwibmJmIjoxNzc3NDM3MjM1LCJqdGkiOiJMTHVZMzlpSXJNWFQwYkhkIiwic3ViIjoiNjk3MjciLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3IiwidHlwZSI6InJlZnJlc2gifQ.7DHvMRINsOq9cp1LalGrgqkYn9lsuTNe1ot8cQNN26M; _ga_FRQJNHYVRZ=GS2.1.s1777437166$o1$g1$t1777437271$j31$l0$h0",
      },
    });

    const text = await res.text();
    if (!text) {
      console.log("No more data");
      break;
    }

    let json;
    try {
      json = JSON.parse(text);
    } catch (e) {
      console.error("Invalid JSON:", text.slice(0, 200));
      break;
    }

    // debug safety
    if (!json.data) {
      console.error("❌ Unexpected response:", json);
      break;
    }

    allData.push(...json.data.data);
    url = json.data.next_page_url;

    console.log("Fetched:", allData.length);
  }

  // save file
  fs.writeFileSync("diary_entries.json", JSON.stringify(allData, null, 2));

  console.log("✅ Saved to diary_entries.json");
}

fetchAndSaveDiaries();
