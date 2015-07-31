import java.util.Stack;

/**
 * Created by Cookie on 29/7/2015.
 */
public class PalindromeLink {
    private static Stack linkToStack(ListNode head){
        Stack stack=new Stack();
        ListNode previous=head;
        while (previous.next!=null){
            stack.push(previous);
            previous=previous.next;
        }
        stack.push(previous);
        return stack;
    }
    public static boolean isPalindrome(ListNode head){
        if (head!=null) {
            Stack stack = linkToStack(head);
            int n = stack.size() / 2;
            for (int i = 0; i < n; i++) {
                if (((ListNode) stack.pop()).val != head.val) return false;
                head = head.next;
            }
        }
        return true;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
