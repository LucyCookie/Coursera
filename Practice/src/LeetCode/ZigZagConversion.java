package LeetCode;

/**
 * Created by qiqu on 1/18/16.
 */
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        int size=s.length(),i,j;
        String converted="";
        if (numRows<1) return null;
        if (size<=numRows || numRows==1) return s;
        for (j=0;j*(2*numRows-2)<size;j++){
            converted+=s.charAt(j*(2*numRows-2));
        }
        for (i=1;i<numRows-1;i++){
            if (i<size) converted+=s.charAt(i);
            else break;
            for (j=1;j*(2*numRows-2)-i<size;j++){
                converted+=s.charAt(j*(2*numRows-2)-i);
                if (j*(2*numRows-2)+i<size) converted+=s.charAt(j*(2*numRows-2)+i);
                else break;
            }
        }
        if (numRows>1) {
            for (j=0;j*(2*numRows-2)+numRows-1<size;j++){
                converted+=s.charAt(j*(2*numRows-2)+numRows-1);
            }
        }
        return converted;
    }
}
