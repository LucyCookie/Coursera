package LeetCode;

import java.util.Stack;

/**
 * Created by qiqu on 1/16/16.
 */
public class Parenthesis {
    public boolean isValid(String s) {
        Stack<Character> left=new Stack();
        for (char c: s.toCharArray()){
            switch (c){
                case '(':
                    left.push(c);
                    break;
                case '{':
                    left.push(c);
                    break;
                case '[':
                    left.push(c);
                    break;
                case ')':
                    if (left.isEmpty() || left.pop()!='(') return false;
                    break;
                case '}':
                    if (left.isEmpty() || left.pop()!='{') return false;
                    break;
                case ']':
                    if (left.isEmpty() || left.pop()!='[') return false;
                    break;
            }
        }
        if (left.isEmpty()) return true;
        return false;
    }
}
