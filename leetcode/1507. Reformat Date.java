/*
Question:
Given a date string in the form Day Month Year, where:
- Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
- Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
- Year is in the range [1900, 2100].

Convert the date string to the format YYYY-MM-DD, where:
- YYYY denotes the 4 digit year.
- MM denotes the 2 digit month.
- DD denotes the 2 digit day.
*/

class Solution {
    public String reformatDate(String date) {
        String[] words = date.split(" ");
        String dayString = words[0].replaceAll("(st|nd|rd|th)", "");
        String month = words[1].toLowerCase();

        if(dayString.length() == 1) dayString = "0" + dayString;
        
        switch(month){
            case "jan": dayString = "-01-" + dayString;
                        break;
            case "feb": dayString = "-02-" + dayString;
                        break;
            case "mar": dayString = "-03-" + dayString;
                        break;
            case "apr": dayString = "-04-" + dayString;
                        break;
            case "may": dayString = "-05-" + dayString;
                        break;
            case "jun": dayString = "-06-" + dayString;
                        break;
            case "jul": dayString = "-07-" + dayString;
                        break;
            case "aug": dayString = "-08-" + dayString;
                        break;
            case "sep": dayString = "-09-" + dayString;
                        break;
            case "oct": dayString = "-10-" + dayString;
                        break;
            case "nov": dayString = "-11-" + dayString;
                        break;
            default: dayString = "-12-" + dayString;
                     break;
        }
        
        return words[2]+dayString;
    }
}