import java.util.Stack;

/**
 * Created by Cookie on 30/7/2015.
 */
public class MyQueue {
    /*Stack head=new Stack();
    // Push element x to the back of head.
    public void push(int x) {
        Stack tmp=new Stack();
        int s=this.size();
        for (int i=0;i<s;i++){
            tmp.push(this.peek());
            this.pop();
        }
        tmp.push(x);
        for (int i=0;i<s+1;i++){
            head.push(tmp.pop());
        }
    }
    // Removes the element from in front of head.
    public void pop() {
        head.pop();
    }
    // Get the front element.
    public int peek() {
        return (int) head.peek();
    }
    // Return whether the head is empty.
    public boolean empty() {
        return head.isEmpty();
    }
    public int size(){
        return head.size();
    }*/
    //a faster implementation: use a stack as the heads and a stack as the ends
    Stack head=new Stack(), end=new Stack();
    public void push(int x) {
        end.push(x);
    }
    // Removes the element from in front of head.
    public void pop() {
        if (head.isEmpty()){
            while (!end.isEmpty()) head.push(end.pop());
        }
        head.pop();
    }
    // Get the front element.
    public int peek() {
        if (head.isEmpty()){
            while (!end.isEmpty()) head.push(end.pop());
        }
        return (int) head.peek();
    }
    // Return whether the head is empty.
    public boolean empty() {
        return (head.isEmpty() && end.isEmpty());
    }
}
