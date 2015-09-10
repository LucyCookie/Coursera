import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by qiqu on 8/22/15.
 */
public class Medians {
    public static void main(String[] args) {
        File file = new File("/Users/qiqu/Google Drive/coursera/Median.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line;
            int size=10000, sumMedian=0;
            HeapMin bigger=new HeapMin(size/2);
            HeapMax smaller=new HeapMax(size/2);
            while ((line = bufReader.readLine()) != null) {
                int key=Integer.parseInt(line);
                if (bigger.getSize()<smaller.getSize()){
                    int c=smaller.getMax();
                    if (key>c) {
                        bigger.insert(key);
                        sumMedian=sumMedian+c;
                    } else {
                        smaller.extractMax();
                        bigger.insert(c);
                        smaller.insert(key);
                        sumMedian=sumMedian+smaller.getMax();
                    }
                } else {
                    if (smaller.getSize()>0) {
                        int c = bigger.getMin();
                        if (key < c) {
                            smaller.insert(key);
                            sumMedian = sumMedian + smaller.getMax();
                        } else {
                            smaller.insert(c);
                            bigger.extractMin();
                            bigger.insert(key);
                            sumMedian = sumMedian + smaller.getMax();
                        }
                    } else {
                        smaller.insert(key);
                        sumMedian=sumMedian+key;
                    }
                }
            }
            System.out.println(sumMedian);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
