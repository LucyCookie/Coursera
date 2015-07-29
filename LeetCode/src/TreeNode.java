/**
 * Created by Cookie on 28/7/2015.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    public static TreeNode LCA(TreeNode root, TreeNode p, TreeNode q){
        if (root.left!=null && containNode(root.left, p)&& containNode(root.left, q)) return LCA(root.left,p,q);
        else if (root.right!=null && containNode(root.right,p)&& containNode(root.right,q)) return LCA(root.right,p,q);
        else return root;
    }
    public static boolean containNode(TreeNode root, TreeNode n){
        if (root.equals(n)) return true;
        else if ((root.left!=null && containNode(root.left,n)) || (root.right!=null && containNode(root.right,n))) return true;
        else return false;
    }
}
