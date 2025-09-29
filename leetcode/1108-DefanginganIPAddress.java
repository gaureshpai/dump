/*
Question:
Given a valid (IPv4) IP address, return a defanged version of that IP address.
A defanged IP address replaces every period "." with "[.]".
*/

class Solution {
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }
}

/*
First Thought:
class Solution {
    public String defangIPaddr(String address) {
        String out = "";

        for(int i = 0; i < address.length(); i++)
            if(address.charAt(i) == '.') out += "[.]";
            else out += address.charAt(i);

        return out;
    }
}
*/