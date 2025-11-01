/*
Question:
You are given an array of integers nums and the head of a linked list. 
Return the head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        java.util.Arrays.sort(nums);
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        ListNode cur = head;

        while (cur != null) {
            if (!binarySearch(nums, cur.val)) {
                res.next = new ListNode(cur.val);
                res = res.next;
            }

            cur = cur.next;
        }
        return dummy.next;
    }

    private boolean binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return true;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        
        return false;
    }
}

/*
First Thought:
class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        ListNode result = new ListNode(0);
        ListNode res = result;
        ListNode cur = head;

        while (cur != null) {
            boolean present = false;

            for (int num : nums)
                if (num == cur.val) {
                    present = true;
                    break;
                }

            if (!present) {
                res.next = new ListNode(cur.val);
                res = res.next;
            }
            
            cur = cur.next;
        }

        return result.next;
    }
}
*/