import fs from 'fs';

const data = JSON.parse(fs.readFileSync('diary_entries.json', 'utf8'));
const dates = data.map(entry => entry.date).sort();
const uniqueDates = [...new Set(dates)];

console.log('Total entries:', data.length);
console.log('Unique dates:', uniqueDates.length);
console.log('Earliest date:', uniqueDates[0]);
console.log('Latest date:', uniqueDates[uniqueDates.length - 1]);

const dateObjects = uniqueDates.map(d => new Date(d));
const diffTime = Math.abs(dateObjects[dateObjects.length - 1] - dateObjects[0]);
const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
console.log('Days between earliest and latest:', diffDays);

// Check for weekends
const weekends = uniqueDates.filter(d => {
    const day = new Date(d).getDay();
    return day === 0 || day === 6;
});
console.log('Weekend entries:', weekends.length);
