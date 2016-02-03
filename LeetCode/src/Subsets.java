import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qiqu on 1/4/16.
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> sets = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int size = nums.length;
        if (size <= 1) {
            List<Integer> sub = new ArrayList<>();
            sub.add(nums[0]);
            sets.add(sub);
            List<Integer> none = new ArrayList<>();
            sets.add(none);
            return sets;
        } else {
            int[] first = new int[size - 1];
            System.arraycopy(nums, 0, first, 0, size - 1);
            List<List<Integer>> one = subsets(first);
            for (int i = 0; i < one.size(); i++) {
                List<Integer> temp = (List<Integer>) one.get(i);
                sets.add(temp);
                List<Integer> cross=new ArrayList<Integer>() ;
                listCopy(temp,cross);
                cross.add(nums[size - 1]);
                sets.add(cross);
            }
            return sets;
        }
    }

    void listCopy(List src, List dest){
        int size=src.size();
        for (int i=0; i<size; i++){
            dest.add(src.get(i));
        }
    }
}
