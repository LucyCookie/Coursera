package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cookie on 28/7/2015.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    //Lowest Common Ancestor of a Binary Search Tree
//235
    public static TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root.left != null && containNode(root.left, p) && containNode(root.left, q))
            return LCA(root.left, p, q);
        else if (root.right != null && containNode(root.right, p) && containNode(root.right, q))
            return LCA(root.right, p, q);
        else return root;
    }

    private static boolean containNode(TreeNode root, TreeNode n) {
        if (root.equals(n)) return true;
        else if ((root.left != null && containNode(root.left, n)) || (root.right != null && containNode(root.right, n)))
            return true;
        else return false;
    }

    //226
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode newTree = new TreeNode(root.val);
            if (root.left != null) newTree.right = invertTree(root.left);
            if (root.right != null) newTree.left = invertTree(root.right);
            return newTree;
        }
        return null;
    }

    /**
     * Created by qiqu on 1/5/16.
     * 105
     */
    //TODO: make it faster: transfer inorder from int[] to Map<val, index> so can look up index faster; also recurse in another method with the index instead of arraycopy
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int leftNum = 0, size = inorder.length;
        TreeNode root = null;
        if (size > 0) {
            root = new TreeNode(preorder[0]);
            for (int i = 0; i < size; i++) {
                if (inorder[i] != preorder[0]) leftNum++;
                else break;
            }
            int rightNum = size - leftNum - 1;
            if (leftNum > 0) {
                int[] subPreLeft = new int[leftNum], subInLeft = new int[leftNum];
                System.arraycopy(inorder, 0, subInLeft, 0, leftNum);
                System.arraycopy(preorder, 1, subPreLeft, 0, leftNum);
                root.left = buildTree(subPreLeft, subInLeft);
            }
            if (rightNum > 0) {
                int[] subPreRight = new int[rightNum], subInRight = new int[rightNum];
                System.arraycopy(inorder, leftNum + 1, subInRight, 0, rightNum);
                System.arraycopy(preorder, leftNum + 1, subPreRight, 0, rightNum);
                root.right = buildTree(subPreRight, subInRight);
            }
        }
        return root;
    }

    /**
     * Created by qiqu on 1/5/16.
     * 106
     * postorder
     */
    public TreeNode postbuildTree(int[] inorder, int[] postorder) {
        int leftNum = 0, size = inorder.length;
        TreeNode root = null;
        if (size > 0) {
            root = new TreeNode(postorder[size - 1]);
            for (int i = 0; i < size; i++) {
                if (inorder[i] != root.val) leftNum++;
                else break;
            }
            int rightNum = size - leftNum - 1;
            if (leftNum > 0) {
                int[] subPostLeft = new int[leftNum], subInLeft = new int[leftNum];
                System.arraycopy(inorder, 0, subInLeft, 0, leftNum);
                System.arraycopy(postorder, 0, subPostLeft, 0, leftNum);
                root.left = buildTree(subInLeft, subPostLeft);
            }
            if (rightNum > 0) {
                int[] subPostRight = new int[rightNum], subInRight = new int[rightNum];
                System.arraycopy(inorder, leftNum + 1, subInRight, 0, rightNum);
                System.arraycopy(postorder, leftNum, subPostRight, 0, rightNum);
                root.right = buildTree(subInRight, subPostRight);
            }
        }
        return root;
    }

    //101
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return mirror(root.left, root.right);
    }

    private boolean mirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return mirror(left.left, right.right) && mirror(left.right, right.left) && left.val == right.val;
    }

    //111
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return minDepth(root, Integer.MAX_VALUE, 0);
    }

    private int minDepth(TreeNode node, int currentMin, int currentLength) {
        if (node == null) return currentLength;
        else currentLength++;
        if (currentLength >= currentMin) return currentMin;//give up
        else {
            int left = minDepth(node.left, currentMin, currentLength), right;
            if ((left - currentMin) * (left - currentLength) < 0) {
                right = minDepth(node.right, left, currentLength);
            } else right = minDepth(node.right, currentMin, currentLength);
            if (1l * (left - currentLength) * (right - currentLength) != 0) currentLength = Math.min(left, right);
            else currentLength = left + right - currentLength;
            if (currentLength < currentMin) return currentLength;
            else return currentMin;
        }
    }

    //104
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    private int maxDepth(TreeNode node, int currentLength) {
        if (node == null) return currentLength;
        else currentLength++;
        int left = maxDepth(node.left, currentLength), right = maxDepth(node.right, currentLength);
        currentLength = Math.max(left, right);
        return currentLength;
    }

    //144
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        result.add(root.val);
        result.addAll(preorderTraversal(root.left));
        result.addAll(preorderTraversal(root.right));
        return result;
    }

    //114
    public void flatten(TreeNode root) {
        flattenWithLast(root);
    }

    private TreeNode flattenWithLast(TreeNode root) {
        TreeNode lastLeaf = root;
        if (root == null) return null;
        TreeNode tmp = root.right;
        if (root.left != null) {
            lastLeaf = flattenWithLast(root.left);
            root.right = root.left;
            root.left = null;
        }
        if (tmp == null) return lastLeaf;
        lastLeaf.right = tmp;
        lastLeaf = flattenWithLast(tmp);
        return lastLeaf;
    }

    //257
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> sub, result = new ArrayList<>();
        if (root == null) return result;
        sub = binaryTreePaths(root.left);
        sub.addAll(binaryTreePaths(root.right));
        for (String subpath : sub) {
            subpath = root.val + "->" + subpath;
            result.add(subpath);
        }
        if (sub.size() == 0) {
            result.add(root.val + "");
        }
        return result;
    }

    /**
     * Created by qiqu on 1/15/16.
     * 95
     */
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) return new ArrayList<>();
        List<TreeNode>[][] dp = new List[n][n];
        System.out.println(dp.length);
        System.out.println(dp[0].length);
        return generateTrees(1, n, dp);
    }

    private List<TreeNode> generateTrees(int left, int right, List<TreeNode>[][] dp) {
        List<TreeNode> result = new ArrayList<>(), lefts, rights;
        TreeNode newTree;
        if (left > right) return result;
        if (dp[left - 1][right - 1] != null) return dp[left - 1][right - 1];
        if (left == right) {
            newTree = new TreeNode(left);
            result.add(newTree);
        } else {
            rights = generateTrees(left + 1, right, dp);
            for (TreeNode rightChoice : rights) {
                newTree = new TreeNode(left);
                result.add(newTree);
                newTree.right = rightChoice;
            }
            lefts = generateTrees(left, right - 1, dp);
            for (TreeNode leftChoice : lefts) {
                newTree = new TreeNode(right);
                result.add(newTree);
                newTree.left = leftChoice;
            }
            for (int i = left + 1; i < right; i++) {
                lefts = generateTrees(left, i - 1, dp);
                rights = generateTrees(i + 1, right, dp);
                for (TreeNode leftChoice : lefts) {
                    for (TreeNode rightChoice : rights) {
                        newTree = new TreeNode(i);
                        newTree.left = leftChoice;
                        newTree.right = rightChoice;
                        result.add(newTree);
                    }
                }
            }
        }
        dp[left - 1][right - 1] = result;
        return result;
    }

    //96
    public int numTrees(int n) {
        if (n < 1) return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        return numTrees(n, dp);
    }

    private int numTrees(int n, int[] dp) {
        if (n < 1) return 0;
        if (dp[n - 1] > 0) return dp[n - 1];
        int sum = 0, i;
        sum += numTrees(n - 1, dp) * 2;
        for (i = 2; i <= n / 2; i++) {
            sum += numTrees(i - 1, dp) * numTrees(n - i, dp) * 2;
        }
        if (n % 2 == 1) sum += numTrees(i - 1, dp) * numTrees(i - 1, dp);
        dp[n - 1] = sum;
        return sum;
    }

    //108
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int i, int j) {
        if (i > j) return null;
        int m = (i + j) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = sortedArrayToBST(nums, i, m - 1);
        root.right = sortedArrayToBST(nums, m + 1, j);
        return root;
    }
}

//297
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#";
        return root.val + "\t" + serialize(root.left) + "\t" + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split("\t");
        TreeNode root = null;
        if (nodes[0].equals("#")) return root;
        root = new TreeNode(Integer.parseInt(nodes[0]));
        deserialize(nodes, 0, root);
        return root;
    }

    private int deserialize(String[] nodes, int i, TreeNode root) {
        TreeNode left, right;
        int next;
        if (nodes[i + 1].equals("#")) {
            left = null;
            next = i + 2;
        } else {
            left = new TreeNode(Integer.parseInt(nodes[i + 1]));
            next = deserialize(nodes, i + 1, left);
        }
        if (nodes[next].equals("#")) {
            right = null;
            next++;
        } else {
            right = new TreeNode(Integer.parseInt(nodes[next]));
            next = deserialize(nodes, next, right);
        }
        root.left = left;
        root.right = right;
        return next;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));