package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by qiqu on 1/28/16.
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (oneDiff(beginWord, endWord)) return 2;
        wordList.add(endWord);
        int size = wordList.size(), length = size + 2, c;
        String[] dict = wordList.toArray(new String[wordList.size()]);
        int[] visited = new int[size];
        Arrays.fill(visited, -1);
        Set<Integer>[] oneDiff = new Set[size];
        Set<Integer>[] moreDiff = new Set[size];
        for (int i = 0; i < size; i++) {
            oneDiff[i] = new HashSet<>();
            moreDiff[i] = new HashSet<>();
        }
        for (int i = 0; i < size; i++) {
            if (oneDiff(beginWord, dict[i])) {
                c = change(i, visited, dict, oneDiff, moreDiff);
                if (c != 0) length = Math.min(length, c + 1);
            }
        }
        return length == size + 2 ? 0 : length;
    }

    private void oneDiff(String[] dict, Set<Integer>[] oneDiff) {
        int n = dict.length, l, r, m, size;
        if (n == 0) return;
        size = dict[0].length();
        if (size == 0) return;
        String start, end;
        for (int i = 0; i < n; i++) {
            start = dict[i];
            for (int j = i + 1; j < n; j++) {
                end = dict[j];
                if (oneDiff(start, end)) {
                    oneDiff[i].add(j);
                    oneDiff[j].add(i);
                }
            }
        }
    }

    private boolean oneDiff(String start, String end) {
        if (start.equals(end)) return false;
        int i = 0, j = start.length() - 1, mid;
        while (i < j) {
            mid = (i + j) / 2;
            if (start.substring(i, mid + 1).equals(end.substring(i, mid + 1))) {
                i = mid + 1;
            } else {
                if (!start.substring(mid + 1, j + 1).equals(end.substring(mid + 1, j + 1))) return false;
                j = mid;
            }
        }
        return true;
    }

    private int change(int i, int[] visited, String[] dict, Set<Integer>[] oneDiff, Set<Integer>[] moreDiff) {
        int size = oneDiff.length, length = size + 2;
        if (oneDiff[size - 1].contains(i)) return 2;
        visited[i] = 0;
        for (int j = 0; j < size; j++) {
            if (j <= i) {
                if (oneDiff[i].contains(j)) {
                    if (visited[j] == -1) visited[j] = change(j, visited, dict, oneDiff, moreDiff);
                    if (visited[j] != 0) length = Math.min(length, visited[j] + 1);
                }
            } else {
                if (oneDiff(dict[i], dict[j])) {
                    oneDiff[i].add(j);
                    oneDiff[j].add(i);
                    if (visited[j] == -1) visited[j] = change(j, visited, dict, oneDiff, moreDiff);
                    if (visited[j] != 0) length = Math.min(length, visited[j] + 1);
                } else {
                    moreDiff[i].add(j);
                    moreDiff[j].add(i);
                }
            }
            if (length <= 3) break;
        }
        if (length != size + 2) visited[i] = length;
        return visited[i];
    }
}
