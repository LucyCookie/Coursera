/**
 * Created by qiqu on 1/16/16.
 */
public class ImageRotate {
    public void rotate(int[][] matrix) {
        int n=matrix.length, step=n/2, tmp;
        for (int i=0;i<step;i++){
            for (int j=0;j<n-step;j++) {
                tmp=matrix[i][j];
                matrix[i][j]=matrix[n-j-1][i];
                matrix[n-j][i]=matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j]=matrix[j][n-1-i];
                matrix[j][n-1-i]=tmp;
            }
        }
    }
}
