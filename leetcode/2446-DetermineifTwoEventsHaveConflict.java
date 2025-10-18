/*
Question:
You are given two arrays of strings that represent two inclusive events that happened on the same day, event1 and event2, where:
- event1 = [startTime1, endTime1] and
- event2 = [startTime2, endTime2].
Event times are valid 24 hours format in the form of HH:MM.
A conflict happens when two events have some non-empty intersection (i.e., some moment is common to both events).
Return true if there is a conflict between two events. 
Otherwise, return false.
*/

class Solution {
    public boolean haveConflict(String[] event1, String[] event2) {
        String start1 = event1[0], end1 = event1[1];
        String start2 = event2[0], end2 = event2[1];
        
        return start1.compareTo(end2) <= 0 && start2.compareTo(end1) <= 0;
    }
}