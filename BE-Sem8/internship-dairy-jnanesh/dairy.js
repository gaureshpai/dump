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
          "_ga=GA1.1.655019714.1775129603; twk_uuid_689c7188a7ee3319309bdeae=%7B%22uuid%22%3A%221.Sx1KFVirnWgt5BeN8MRZr59ndGFvwnCvyL0Alj6CygZJfNaELyKmitMQQwDJ604PTfHJY5xRON6R5QQGdoSSG6E8zpUwyF7PHqZUeKUSKU2W2EhA2uAEi%22%2C%22version%22%3A3%2C%22domain%22%3A%22internyet.in%22%2C%22ts%22%3A1777647723900%7D; _ga_FRQJNHYVRZ=GS2.1.s1777647713$o18$g1$t1777647729$j44$l0$h0; access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc2NDc3MzYsImV4cCI6MTc3NzY1MTMzNiwibmJmIjoxNzc3NjQ3NzM2LCJqdGkiOiJPQXNWWUlJUk9sV0pNOG5kIiwic3ViIjoiNjk4NzQiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.01Yvv8CN-i7lxzrfX_ZaM6h9xZLQvvzwEg3CtLje5ns; refresh_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc2NDc3MzYsImV4cCI6MTc3NzY2Nzg5NiwibmJmIjoxNzc3NjQ3NzM2LCJqdGkiOiIyNTlZSHBJY0pNSE1wTFFRIiwic3ViIjoiNjk4NzQiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3IiwidHlwZSI6InJlZnJlc2gifQ.fRvPNdVkL_LC2VuDdE8o3QC08UI3SBfJHYOgtv7TFow",
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
