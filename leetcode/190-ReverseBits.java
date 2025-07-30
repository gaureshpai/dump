/*
Question:
Reverse bits of a given 32 bits unsigned integer.

Note:
Note that in some languages, such as Java, there is no unsigned integer type. 
In this case, both input and output will be given as a signed integer type. 
They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. 
Therefore, in Example 2 below, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
*/

class Solution {
    public int reverseBits(int n) {
        String x =  String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0');
        String rev="";
        for(int i=x.length()-1;i>=0;i--)
            rev+=x.charAt(i);
        int w = Integer.parseUnsignedInt(rev,2);
        return w;
    }
}