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
        "_ga=GA1.1.1188540200.1754661119; twk_uuid_689c7188a7ee3319309bdeae=%7B%22uuid%22%3A%221.SwzLxrJJpd2aXhpRDRLsmA2AFx8AcHDsIaLIjuKwh8XeIoYA7CoaplBXEqB0paCRTQDUzQuAOsuE5jCmPNDEH03ivm2MK5P0TFQmw5qbO3MOoaYXp4eRm%22%2C%22version%22%3A3%2C%22domain%22%3A%22internyet.in%22%2C%22ts%22%3A1777700308065%7D; _ga_FRQJNHYVRZ=GS2.1.s1777696689$o16$g1$t1777700325$j41$l0$h0; access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc3MDAzMzMsImV4cCI6MTc3NzcwMzkzMywibmJmIjoxNzc3NzAwMzMzLCJqdGkiOiJTSWpyTEx6ZWxRbEJ2UkVQIiwic3ViIjoiNzQzMCIsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.kBNHqnFjpkqGQpsCASLqbg_0zxtyJohfLEE2DLClMe4; refresh_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3Z0dWFwaS5pbnRlcm55ZXQuaW4vYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3Nzc3MDAzMzQsImV4cCI6MTc3NzcyMDQ5MywibmJmIjoxNzc3NzAwMzM0LCJqdGkiOiJPb2FRZUtSMUg4REtyY3dYIiwic3ViIjoiNzQzMCIsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjciLCJ0eXBlIjoicmVmcmVzaCJ9.A_QCjEAKY78ZmdkVkjex7PYcqQrUR1I61ZSm3bxjj9s"
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
