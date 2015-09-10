import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by qiqu on 8/21/15.
 */
public class TwoSum {
    public static void main(String[] args) {
        long ts = System.currentTimeMillis();
        File file = new File("/Users/qiqu/Google Drive/coursera/algo1-programming_prob-2sum.txt");
        long[] numbers = loadFile(file);
        Map<Integer, Integer> target= new HashMap<Integer,Integer>();
        int i=0, j=numbers.length-1,start=j;
        while (i<j){
            if (numbers[i]+numbers[start]<-10000) {
                i++;
                start=j;
            }
            else if (numbers[i]+numbers[start]>10000) {
                j--;
                start=j;
            }
            else {
                if (start<=j) {
                    target.put((int)(numbers[i]+numbers[start]),1);
                    start--;
                    if (start==i) {
                        i++;
                        start=j;
                    }
                }
                else {
                    start=j;
                    target.put((int)(numbers[i]+numbers[start]),1);
                }
            }
        }
        System.out.println(target.size());
        long te = System.currentTimeMillis();
        double tusec = (te - ts) / 1000;
        System.out.println(tusec);
    }

    public static long[] loadFile(File numbers) {
        List<Long> integers = new ArrayList<Long>();
        try {
            FileReader fileReader = new FileReader(numbers);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufReader.readLine()) != null) {
                long key = Long.parseLong(line);
                integers.add(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int size=integers.size();
        long pivot=integers.get(0), tmp;
        long[] list=new long[size];
        int j=0;
        for (int i=1;i<size;i++){
            if (integers.get(i)<pivot) {
                j++;
                tmp=integers.get(i);
                list[i]=list[j];
                list[j]=tmp;
            }
            else list[i]=integers.get(i);
        }
        list[0]=list[j];
        list[j]=pivot;
        if (j>1) quickSort(list, 0, j-1);
        if (j<size-2) quickSort(list, j+1, size-1);
        return list;
    }
    static void quickSort(long[] a, int x, int y){
        if (x==y) return;
        long pivot=a[x];
        int j=x;
        long tmp;
        for (int i=x+1;i<=y;i++){
            if (a[i]<pivot) {
                j++;
                tmp=a[i];
                a[i]=a[j];
                a[j]=tmp;
            }
        }
        if (j>x) {
            tmp = pivot;
            a[x] = a[j];
            a[j] = tmp;
        }
        if (j>x+1) quickSort(a, x, j - 1);
        if (j<y-1) quickSort(a,j+1,y);
    }
}
