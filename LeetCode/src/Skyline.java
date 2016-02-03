import java.util.*;

/**
 * Created by qiqu on 1/20/16.
 */
public class Skyline {
    public List<int[]> getSkyline(int[][] buildings) {
        Comparator<int[]> positionComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                return o2[1] - o1[1];
            }
        };
        PriorityQueue<int[]> lefts = new PriorityQueue<>(positionComparator), rights = new PriorityQueue<>(positionComparator);
        int[] position;
        for (int[] building : buildings) {
            position = new int[2];
            position[0] = building[0];
            position[1] = building[2];
            lefts.add(position);
            position = new int[2];
            position[0] = building[1];
            position[1] = building[2];
            rights.add(position);
        }
        PriorityQueue<Integer> heights = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        heights.add(0);
        List<int[]> result = new LinkedList<>();
        int compare, maxHeight;
        while (rights.size() > 0) {
            if (lefts.size() > 0) {
                compare = lefts.peek()[0] - rights.peek()[0];
                maxHeight = heights.peek();
                if (compare < 0) {
                    position = lefts.poll();
                    heights.add(position[1]);
                    if (heights.peek() > maxHeight) result.add(position);
                } else {
                    do {
                        position = rights.poll();
                        heights.remove(position[1]);
                    } while (rights.size()>0 && rights.peek()[0] == position[0]);
                    if (compare == 0) {
                        do {
                            position = lefts.poll();
                            heights.add(position[1]);
                        } while (lefts.size()>0&&lefts.peek()[0] == position[0]);
                    }
                    if (maxHeight!=(position[1]=heights.peek())) result.add(position);
                }
            } else {
                maxHeight = heights.peek();
                do {
                    position = rights.poll();
                    heights.remove(position[1]);
                } while (rights.size()>0 && rights.peek()[0] == position[0]);
                if (maxHeight!=(position[1]=heights.peek())) result.add(position);
            }
        }
        return result;
    }
}
