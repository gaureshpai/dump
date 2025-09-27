/*
Question:
You are given the head of a linked list, which contains a series of integers separated by 0's. 
The beginning and end of the linked list will have Node.val == 0.
For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is the sum of all the merged nodes. 
The modified list should not contain any 0's.
Return the head of the modified linked list.
*/

class Solution {
    public ListNode mergeNodes(ListNode head) {
        ListNode cur = head.next;
        ListNode res = new ListNode(0);
        ListNode res = res;

        int sum = 0;
        while (cur != null) {
            if (cur.val == 0) {
                res.next = new ListNode(sum);
                res = res.next;
                sum = 0;
            } else sum += cur.val;
            cur = cur.next;
        }

        return res.next;
    }
}