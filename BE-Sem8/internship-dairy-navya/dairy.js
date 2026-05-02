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
          "_ga=GA1.1.1298826862.1768596501; _ga_FRQJNHYVRZ=GS2.1.s1769845317$o9$g0$t1769845317$j60$l0$h0; twk_uuid_689c7188a7ee3319309bdeae=%7B%22uuid%22%3A%221.Sx09s0L7l9KgWQxU0sDaD8eYWdEB8E5QDveNO178rskXD5PId1dGOdN9bztWXYKdX8zk4XqwEJ3GOurxUWWojUpx8xPKifsrBUAfbWLxFZPuGPQ3rn0yE%22%2C%22version%22%3A3%2C%22domain%22%3A%22internyet.in%22%2C%22ts%22%3A1777739351887%7D; access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc3Mzk1NTIsImV4cCI6MTc3Nzc0MzE1MiwibmJmIjoxNzc3NzM5NTUyLCJqdGkiOiJGRkJYQ0tqZHZra0pTbGlnIiwic3ViIjoiNjk3MDIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.fkCp4PGjmg3HslDj__k98BY_ehfi6giBW9xxxP7tlh4; refresh_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc3Mzk1NTIsImV4cCI6MTc3Nzc1OTcxMiwibmJmIjoxNzc3NzM5NTUyLCJqdGkiOiIxRENMbWI0MURnNDZzUkNXIiwic3ViIjoiNjk3MDIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3IiwidHlwZSI6InJlZnJlc2gifQ.O0clWatSHHaFBtmZcBz7a_QgckpAWxiaXKFMYxI5w6E",
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
