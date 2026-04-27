import fs from "fs";

async function fetchAndSaveDiaries() {
  const baseUrl = "https://vtuapi.internyet.in/api/v1/student/internship-diaries";

  let url = baseUrl;
  let allData = [];

  while (url) {
    const res = await fetch(url, {
      method: "GET",
      headers: {
        "Accept": "application/json",
        "Cookie": "REPLACE_WITH_YOR_COOKIE"
       }
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
  fs.writeFileSync(
    "diary_entries.json",
    JSON.stringify(allData, null, 2)
  );

  console.log("✅ Saved to diary_entries.json");
}

fetchAndSaveDiaries();
