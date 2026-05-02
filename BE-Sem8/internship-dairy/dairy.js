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
          "_ga=GA1.1.1298826862.1768596501; _ga_FRQJNHYVRZ=GS2.1.s1769845317$o9$g0$t1769845317$j60$l0$h0; twk_uuid_689c7188a7ee3319309bdeae=%7B%22uuid%22%3A%221.Sx09s0L7l9KgWQxU0sDaD8eYWdEB8E5QDveNO178rskXD5PId1dGOdN9bztWXYKdX8zk4XqwEJ3GOurxUWWojUpx8xPKifsrBUAfbWLxFZPuGPQ3rn0yE%22%2C%22version%22%3A3%2C%22domain%22%3A%22internyet.in%22%2C%22ts%22%3A1777646235070%7D; access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc2NDYyNDIsImV4cCI6MTc3NzY0OTg0MiwibmJmIjoxNzc3NjQ2MjQyLCJqdGkiOiJyRFJaRnFhUlRxcWdtaFphIiwic3ViIjoiNjk4MjMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.3J6lHgZffQ4npx2rjgwU4TuWWUbGmvX-k6rg35u1tVc; refresh_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc2NDYyNDIsImV4cCI6MTc3NzY2NjQwMiwibmJmIjoxNzc3NjQ2MjQyLCJqdGkiOiJHYnZLTlNsNVlvTXVVR3owIiwic3ViIjoiNjk4MjMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3IiwidHlwZSI6InJlZnJlc2gifQ.MwRhzA1T8t2Nho9pHDUw2J2n0J7NVr39DRiPEtaQm7E"
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
