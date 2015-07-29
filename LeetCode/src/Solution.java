/**
 * Created by Cookie on 28/7/2015.
 */
public class Solution {
    public static void main(String[] args) {
        TreeNode six=new TreeNode(6);
        TreeNode two=new TreeNode(2);
        TreeNode zero=new TreeNode(0);
        TreeNode four=new TreeNode(4);
        TreeNode three=new TreeNode(3);
        TreeNode five=new TreeNode(5);
        TreeNode eight=new TreeNode(8);
        TreeNode seven=new TreeNode(7);
        TreeNode nine=new TreeNode(9);
        six.left=two;
        six.right=eight;
        two.left=zero;
        two.right=four;
        four.left=three;
        four.right=five;
        eight.left=seven;
        eight.right=nine;
        System.out.println(TreeNode.LCA(six, two, eight).val);
        System.out.println(TreeNode.LCA(six, two, four).val);
    }
}
