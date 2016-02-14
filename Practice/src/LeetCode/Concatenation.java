package LeetCode;

import java.util.*;

/**
 * Created by qiqu on 1/29/16.
 */
public class Concatenation {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        String[] c = s.split(words[0]);
        int size = c.length, l = words[0].length(), tmp, j, offset = 0;
        if (l > s.length()) return result;
        if (words.length == 1) {
            for (String sub : c) {
                offset += sub.length();
                if (offset < s.length()) result.add(offset);
                offset += l;
            }
            return result;
        }
        if (size < 1) return result;
        List<String>[] matches = new List[size - 1];
        for (int i = 0; i < size - 1; i++) {
            matches[i] = new ArrayList<>();
        }
        Map<String, Integer> word = new HashMap<>();
        for (int i = 1; i < words.length; i++) {
            word.put(words[i], i);
        }
        String sub, test, x;
        for (int i = 0; i < size - 1; i++) {
            sub = c[i];
            tmp = sub.length();
            offset += tmp + l;
            for (j = 0; (j + 1) * l <= tmp; j++) {
                test = sub.substring(tmp - (j + 1) * l, tmp - j * l);
                if (word.containsKey(test)) {
                    if (matches[i].contains(test)) {
                        if (matches[i].size() == words.length - 1) result.add(offset - words.length * l);
                        break;
                    } else {
                        matches[i].add(0, test);
                        if (matches[i].size() == words.length - 1) {
                            result.add(offset - words.length * l);
                            break;
                        }
                    }
                } else break;
            }
            sub = c[i + 1];
            tmp = sub.length();
            for (j = 0; (j + 1) * l <= tmp; j++) {
                test = sub.substring(j * l, (j + 1) * l);
                if (word.containsKey(test)) {
                    if (matches[i].contains(test)) {
                        for (int k = 0; k < matches[i].size(); k++) {
                            x = matches[i].get(0);
                            matches[i].remove(0);
                            if (x.equals(test)) break;
                        }
                        if (matches[i].size() < j) break;
                        matches[i].add(test);
                    } else {
                        matches[i].add(test);
                    }
                    if (matches[i].size() == words.length - 1) {
                        result.add(offset + (j + 1) * l - words.length * l);
                    }
                } else break;
            }
            if (j * l == tmp && i + 1 < size - 1) {
                for (int k = 0; k < matches[i].size() - j; k++) {
                    matches[i].remove(0);
                }
                matches[i + 1].addAll(matches[i]);
            }
        }
        return result;
    }
}
