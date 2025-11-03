/**
 * PROBLEM: Meeting Room Scheduler - Merge Overlapping Meetings
 *
 * You are building a calendar application for a meeting room.
 * Given a list of meeting time intervals where each interval is [start_time, end_time], 
 * merge all overlapping meetings and return the list of merged meeting blocks.
 * 
 * Two meetings overlap if one starts before or when the other ends.
 *
 * Example:
 * Input: meetings = [[9,10], [10,12], [11,13], [15,16]]
 * Output: [[9,13], [15,16]]
 * Explanation:
 *   - [9,10], [10,12], and [11,13] overlap, so they merge into [9,13]
 *   - [15,16] doesn't overlap with anything
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * 
 * Constraints:
 *  - 1 <= meetings.length <= 10^4
 *  - meetings[i].length == 2
 *  - 0 <= start_time < end_time <= 10^4
 *  - Meetings can touch at boundaries (e.g., [1,2] and [2,3] should merge to [1,3])
 */

// Please do not modify this code
const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
rl.question('', (input) => {
    try {
        const meetings = JSON.parse(input);
        const result = mergeMeetings(meetings);
        console.log(JSON.stringify(result));
    } catch (error) {
        console.error('Invalid input format. Please provide a valid array.');
    }
    rl.close();
});

/**
 * Merges overlapping meeting time intervals.
 * @param {number[][]} meetings - Array of [start, end] meeting times
 * @return {number[][]} - Merged non-overlapping meeting intervals
 */
function mergeMeetings(meetings) {
    if (!Array.isArray(meetings) || meetings.length === 0) return [];

    // Sort meetings by start time
    meetings.sort((a, b) => a[0] - b[0]);

    const merged = [];
    let [start, end] = meetings[0];

    for (let i = 1; i < meetings.length; i++) {
        const [currStart, currEnd] = meetings[i];

        if (currStart <= end) {
            // Overlap or touch, merge them
            end = Math.max(end, currEnd);
        } else {
            // No overlap, push and reset
            merged.push([start, end]);
            start = currStart;
            end = currEnd;
        }
    }
    merged.push([start, end]);
    return merged;
}