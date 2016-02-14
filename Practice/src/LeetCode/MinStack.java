package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by qiqu on 2/6/16.
 */
public class MinStack {
    private int bound=-1;
    private List<Integer> stack=new ArrayList<>();
    Stack<Integer> min=new Stack<>();
    public void push(int x) {
        stack.add(x);
        bound++;
        if (min.isEmpty()) min.add(bound);
        else if (stack.get(min.peek())>x) min.add(bound);
    }

    public void pop() {
        stack.remove(bound);
        if (min.peek()==bound) min.pop();
        bound--;
    }

    public int top() {
        return stack.get(bound);
    }

    public int getMin() {
        return stack.get(min.peek());
    }
}
