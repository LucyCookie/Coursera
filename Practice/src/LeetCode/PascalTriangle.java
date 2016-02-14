package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiqu on 1/17/16.
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> row, last;
        int i, j;
        for (i = 0; i < numRows && i < 2; i++) {
            row = new ArrayList<>();
            for (j = 0; j <= i; j++) {
                row.add(1);
            }
            triangle.add(row);
        }
        for (i = 2; i < numRows; i++) {
            row = new ArrayList<>();
            row.add(1);
            last = triangle.get(i - 1);
            for (j = 0; j < i - 1; j++) {
                row.add(last.get(j) + last.get(j + 1));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }

    public List<Integer> getRow(int rowIndex) {
        return generate(rowIndex + 1).get(rowIndex);
    }
}
