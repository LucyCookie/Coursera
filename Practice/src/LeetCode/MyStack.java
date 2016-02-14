package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Cookie on 30/7/2015.
 */
public class MyStack {
    Queue head =new LinkedList<Integer>();
    Queue tail=new LinkedList<Integer>();
    Queue tmp=new LinkedList<Integer>();
    // Push element x onto stack.
    public void push(int x) {
        tail.offer(x);
    }
    // Removes the element on top of the stack.
    public void pop() {
        top();
        tail.poll();
    }
    // Get the top element.
    public int top() {
        if(tail.isEmpty()){
            while (head.size() > 1) tail.offer( head.poll());
            tmp=head;
            head=tail;
            tail=tmp;
            return (int) tail.peek();
        }else {
            while (tail.size() > 1) head.offer(tail.poll());
            return (int) tail.peek();
        }
    }
    // Return whether the stack is empty.
    public boolean empty() {
        if (tail.size()>0) return false;
        else if (head.size()>0) return false;
        else return true;
    }
}
