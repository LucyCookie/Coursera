package LeetCode;

import java.util.*;

/**
 * Created by qiqu on 1/21/16.
 * LeetCode 76
 */
public class MinWindow {
    public String minWindow(String s, String t) {
        Map<Character, Integer> tChars = new HashMap<>();
        Map<Character, PriorityQueue<Integer>> charPositions = new HashMap<>();
        String window = s;
        int count, i = 0, j = 0, total = t.length(), tmp;
        for (char c : t.toCharArray()) {
            if (tChars.containsKey(c)) count = tChars.get(c) + 1;
            else count = 1;
            tChars.put(c, count);
        }
        boolean found=false;
        PriorityQueue<Integer> positions = new PriorityQueue<>(), letters = new PriorityQueue<>();
        for (char c : s.toCharArray()) {
            if (tChars.containsKey(c)) {
                count = tChars.get(c);
                letters.add(j);
                if (charPositions.containsKey(c)) {
                    positions = charPositions.get(c);
                    if (positions.size() == count) {
                        tmp=positions.poll();
                        if (i==tmp){
                            while (letters.peek() <= i) {
                                letters.poll();
                            }
                            i=letters.peek();
                        } else letters.remove(tmp);
                    }
                    positions.add(j);
                } else {
                    positions = new PriorityQueue<Integer>();
                    positions.add(j);
                    charPositions.put(c, positions);
                }
                if (letters.size() == total && j - i + 1 <= window.length()) {
                    window = s.substring(i, j + 1);
                    found=true;
                }
            }
            j++;
            if (charPositions.size() == 0) i++;
        }
        return found ? window : "";
    }
}
