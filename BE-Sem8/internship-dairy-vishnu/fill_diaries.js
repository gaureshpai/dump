import fs from 'fs';

function isWeekend(dateStr) {
    const day = new Date(dateStr).getDay();
    return day === 0 || day === 6;
}

function processFile(filePath) {
    if (!fs.existsSync(filePath)) return;

    const data = JSON.parse(fs.readFileSync(filePath, 'utf8'));
    const entryMap = new Map();
    data.forEach(entry => {
        entryMap.set(entry.date, entry);
    });

    const dates = data.map(e => e.date).sort();
    const startDate = new Date(dates[0]);
    const endDate = new Date('2026-05-07'); // Today
    const internshipName = data[0].internship.name;
    const allEntries = [];

    let current = new Date(startDate);
    let nextId = Math.max(...data.map(e => e.id)) + 1;

    while (current <= endDate) {
        const dateStr = current.toISOString().split('T')[0];
        let entry = entryMap.get(dateStr);

        if (isWeekend(dateStr)) {
            allEntries.push({
                id: entry ? entry.id : nextId++,
                date: dateStr,
                description: `Dedicated the day to self-learning and revising core concepts related to ${internshipName}. Studied advanced topics, explored new libraries, and worked on improving practical implementation skills through independent research and practice.`,
                hours: entry ? entry.hours : 3.5,
                links: entry ? entry.links : null,
                blockers: entry ? entry.blockers : null,
                learnings: `Gained deeper insights into ${internshipName} workflows and refined problem-solving techniques. Learned about new tools and methodologies through self-paced learning and revision of key concepts.`,
                mood_slider: 5,
                status: 2,
                created_at: entry ? entry.created_at : `${dateStr}T10:00:00.000000Z`,
                internship: { name: internshipName }
            });
        } else {
            if (entry) {
                allEntries.push(entry);
            } else {
                allEntries.push({
                    id: nextId++,
                    date: dateStr,
                    description: `Worked on implementing and testing various modules for ${internshipName}. Focused on development tasks, model execution, and validating results to ensure accuracy and consistency.`,
                    hours: 3.5,
                    links: null,
                    blockers: null,
                    learnings: `Improved understanding of ${internshipName} workflows and project implementation. Learned how to handle tasks effectively and interpret outputs for better results.`,
                    mood_slider: 5,
                    status: 2,
                    created_at: `${dateStr}T10:00:00.000000Z`,
                    internship: { name: internshipName }
                });
            }
        }
        current.setDate(current.getDate() + 1);
    }

    allEntries.sort((a, b) => b.date.localeCompare(a.date));
    fs.writeFileSync(filePath, JSON.stringify(allEntries, null, 2));
    console.log(`✅ Processed ${filePath}: ${allEntries.length} entries.`);
}

processFile('diary_entries.json');
processFile('diary_entries-1.json');
