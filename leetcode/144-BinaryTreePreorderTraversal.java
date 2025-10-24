/*
Question:
Given the root of a binary tree, return the preorder traversal of its node's values.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        
        traverse(root, res);
        
        return res;
    }

    private void traverse(TreeNode node, List<Integer> res) {
        if (node == null) return;

        res.add(node.val);
        traverse(node.left, res);
        traverse(node.right, res);
    }
}