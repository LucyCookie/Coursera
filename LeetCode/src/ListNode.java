import java.util.HashSet;
import java.util.Stack;

/**
 * Created by qiqu on 1/15/16.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    /**
     * Created by Cookie on 29/7/2015.
     * 9
     */
    private static Stack linkToStack(ListNode head) {
        Stack stack = new Stack();
        ListNode previous = head;
        while (previous.next != null) {
            stack.push(previous);
            previous = previous.next;
        }
        stack.push(previous);
        return stack;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head != null) {
            Stack stack = linkToStack(head);
            int n = stack.size() / 2;
            for (int i = 0; i < n; i++) {
                if (((ListNode) stack.pop()).val != head.val) return false;
                head = head.next;
            }
        }
        return true;
    }

    //61
    public ListNode rotateRight(ListNode head, int k) {
        ListNode point = head, rotated;
        int size = 0;
        if (head != null) {
            size++;
            while (point.next != null) {
                point = point.next;
                size++;
            }
            point.next = head;
        } else return null;
        k = k % size;
        for (int i = 0; i < size - k; i++) {
            point = point.next;
        }
        rotated = point.next;
        point.next = null;
        return rotated;
    }

    //86
    public ListNode partition(ListNode head, int x) {
        ListNode small = null, smallPoint = null, big = null, bigPoint = null, point;
        point = head;
        while (point != null) {
            if (point.val < x) {
                if (small == null) {
                    small = point;
                    smallPoint = small;
                } else {
                    smallPoint.next = point;
                    smallPoint = smallPoint.next;
                }
            } else {
                if (big == null) {
                    big = point;
                    bigPoint = big;
                } else {
                    bigPoint.next = point;
                    bigPoint = bigPoint.next;
                }
            }
            point = point.next;
        }
        if (big != null) {
            bigPoint.next = null;
        }
        if (small != null) {
            smallPoint.next = big;
        } else small = big;
        return small;
    }

    //21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1, p2, sorted = null, ps;
        p1 = l1;
        p2 = l2;
        if (p1 != null && p2 != null)
            if (p1.val < p2.val) {
                sorted = new ListNode(p1.val);
                p1 = p1.next;
            } else {
                sorted = new ListNode(p2.val);
                p2 = p2.next;
            }
        else {
            sorted = p1 == null ? p2 : p1;
            return sorted;
        }
        ps = sorted;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                ps.next = p1;
                p1 = p1.next;
            } else {
                ps.next = p2;
                p2 = p2.next;
            }
            ps = ps.next;
        }
        ps.next = p1 == null ? p2 : p1;
        return sorted;
    }

    //141
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null) {
            fast = fast.next;
            if (slow == fast) return true;
            if (fast != null) fast = fast.next;
            else return false;
            slow = slow.next;
        }
        return false;
    }

    //142
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> dejavu = new HashSet<>();
        ListNode point = head;
        while (point != null) {
            if (!dejavu.add(point)) break;
            point = point.next;
        }
        return point;
    }

    //287. similar to the cycle problems
    public int findDuplicate(int[] nums) {
        //the basic idea is: there are n+1 edges (indices), but only n+1 nodes (values), so the duplicate receives more than 1 edge in
        int normal, catchUp, meet = 0;
        catchUp = nums[nums[0]];
        normal = nums[0];
        while (catchUp != normal) {
            catchUp = nums[nums[catchUp]];
            normal = nums[normal];
        }
        while (meet != normal) {
            normal = nums[normal];
            meet = nums[meet];
        }
        return meet;
    }

    //328
    public ListNode oddEvenList(ListNode head) {
        ListNode even, point = head, evenPoint;
        if (head == null) return head;
        even = head.next;
        evenPoint = even;
        if (even == null) return head;
        int index = 2;
        while (point.next != null && evenPoint.next != null) {
            if (index % 2 == 1) {
                evenPoint.next = point.next;
                evenPoint = evenPoint.next;
            } else {
                point.next = evenPoint.next;
                point = point.next;
            }
            index++;
        }
        point.next = even;
        evenPoint.next = null;
        return head;
    }
}