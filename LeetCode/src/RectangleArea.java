/**
 * Created by Cookie on 30/7/2015.
 */
public class RectangleArea {
    static int HORIZONTAL_OVERLAP=1;
    static int HORIZONTAL_FIRST_IN_SECOND=2;
    static int HORIZONTAL_SECOND_IN_FIRST=3;
    static int HORIZONTAL_NO_OVERLAP=5;
    static int VERTICAL_OVERLAP=7;
    static int VERTICAL_FIRST_IN_SECOND=11;
    static int VERTICAL_SECOND_IN_FIRST=13;
    static int VERTICAL_NO_OVERLAP=17;
    public static int totalArea(int A, int B, int C, int D, int E, int F, int G, int H){
//        int result=overlap(A,B,C,D,E,F,G,H);
//        if (result==CORNER_CROSS){
//                if (Math.abs(E-C)<Math.abs(G-A)){
//                    if (Math.abs(F-D)<Math.abs(H-B)) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,F,C,D);
//                    else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,B,C,H);
//                }else {
//                    if (Math.abs(F-D)<Math.abs(H-B)) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,F,G,D);
//                    else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,B,G,H);
//                }
//        }else if (result==VERTICAL_INCLUDE) {
//                            if (Math.abs(E-C)<Math.abs(G-A)){
//                    if (Math.abs(B-D)<Math.abs(H-F)) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,B,C,D);
//                    else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,F,C,H);
//                }else {
//                    if (Math.abs(B-D)<Math.abs(H-F)) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,B,G,D);
//                    else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,F,G,H);
//                }
//        }else if (result==HORIZONTAL_INCLUDE) {
//                            if (Math.abs(A-C)<Math.abs(G-E)){
//                    if (Math.abs(F-D)<Math.abs(H-B)) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,F,C,D);
//                    else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,B,C,H);
//                }else {
//                    if (Math.abs(F-D)<Math.abs(H-B)) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,F,G,D);
//                    else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,B,G,H);
//                }
//        }
//        else if(result==FIRST_IN_SECOND) return computeArea(E,F,G,H);
//        else if (result==SECOND_IN_FIRST) return computeArea(A,B,C,D);
//        else return computeArea(A,B,C,D)+computeArea(E,F,G,H);
        int horizontalResult=horizontalOverlap(A, B, C, D, E, F, G, H);
        int verticalResult=verticalOverlap(A, B, C, D, E, F, G, H);
        switch (horizontalResult*verticalResult){
            case 7://corner overlap
                if (Math.abs(E-C)<Math.abs(G-A)){
                    if (Math.abs(F-D)<Math.abs(H-B)) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,F,C,D);
                    else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,B,C,H);
                }else {
                    if (Math.abs(F-D)<Math.abs(H-B)) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,F,G,D);
                    else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,B,G,H);
                }
            case 11://horizontal overlap & vertically first in second
                if (A<E) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,B,C,D);
                else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,B,G,D);
            case 13://horizontal overlap & vertically second in first
                if (A<E) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,F,C,H);
                else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,F,G,H);
            case 17://horizontal parallel
                return computeArea(A,B,C,D)+computeArea(E,F,G,H);
            case 14://vertical overlap & horizontally first in second
                if (B<F) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,F,C,D);
                else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,B,C,H);
            case 22://first inside second
                return computeArea(E,F,G,H);
            case 26://cross overlap
                return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(A,F,C,H);
            case 34://horizontal parallel
                return computeArea(A,B,C,D)+computeArea(E,F,G,H);
            case 21://side overlap
                if (D<H) return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,F,G,D);
                else return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,B,G,H);
            case 33://cross overlap
                return computeArea(A,B,C,D)+computeArea(E,F,G,H)-computeArea(E,B,G,D);
            case 39://second inside first
                return computeArea(A,B,C,D);
            case 51://horizontal parallel
                return computeArea(A,B,C,D)+computeArea(E,F,G,H);
            case 35://vertical parallel
                return computeArea(A,B,C,D)+computeArea(E,F,G,H);
            case 55://vertical parallel
                return computeArea(A,B,C,D)+computeArea(E,F,G,H);
            case 65://vertical parallel
                return computeArea(A,B,C,D)+computeArea(E,F,G,H);
            case 85://no overlap
                return computeArea(A,B,C,D)+computeArea(E,F,G,H);
        }
        return 0;
    }
    static int computeArea(int A, int B, int C, int D){
        int area=(C-A)*(D-B);
        return area;
    }
//    static int overlap(int A, int B, int C, int D, int E, int F, int G, int H){
//        if (((E-A)*(E-C)<=0 || (G-A)*(G-C)<=0) && ((F-B)*(F-D)<=0 || (H-B)*(H-D)<=0)) {
//            if (contain(A, B, C, D, E, F, G, H)) return FIRST_IN_SECOND;
//            if (contain(E, F, G, H, A, B, C, D)) return SECOND_IN_FIRST;
//            return CORNER_CROSS;
//        }
//        if (((E-A)*(E-C)<=0 || (G-A)*(G-C)<=0) && ((F-B)*(F-D)>=0 && (H-B)*(H-D)>=0)) {
//            if (contain(A, B, C, D, E, F, G, H)) return FIRST_IN_SECOND;
//            if (contain(E, F, G, H, A, B, C, D)) return SECOND_IN_FIRST;
//            return VERTICAL_INCLUDE;
//        }
//        if (((E-A)*(E-C)>=0 || (G-A)*(G-C)>=0) && ((F-B)*(F-D)<=0 || (H-B)*(H-D)<=0)) {
//            if (contain(A, B, C, D, E, F, G, H)) return FIRST_IN_SECOND;
//            if (contain(E, F, G, H, A, B, C, D)) return SECOND_IN_FIRST;
//            return HORIZONTAL_INCLUDE;
//        }
//        else return NO_OVERLAP;
////
////        return (((E-A)*(E-C)<=0 || (G-A)*(G-C)<=0) && ((F-B)*(F-D)<=0 || (H-B)*(H-D)<=0) || ((E-A)*(E-C)<=0 || (G-A)*(G-C)<=0) && ((F-B)*(F-D)>=0 && (H-B)*(H-D)>=0) || ((E-A)*(E-C)>=0 || (G-A)*(G-C)>=0) && ((F-B)*(F-D)<=0 || (H-B)*(H-D)<=0));
//    }
        static int horizontalOverlap(int A, int B, int C, int D, int E, int F, int G, int H){
            if (((A>E && A<G) && C>G) || (C>E && C<G) && A<E) return HORIZONTAL_OVERLAP;
            else if (A>=E && C<=G) return HORIZONTAL_FIRST_IN_SECOND;
            else if (A<=E && C>=G) return HORIZONTAL_SECOND_IN_FIRST;
            else return HORIZONTAL_NO_OVERLAP;
        }
        static int verticalOverlap(int A, int B, int C, int D, int E, int F, int G, int H){
            if (((B>F && B<H) && D>H) || (D>F && D<H) && B<F) return VERTICAL_OVERLAP;
            else if (B>=F && D<=H) return VERTICAL_FIRST_IN_SECOND;
            else if (B<=F && D>=H) return VERTICAL_SECOND_IN_FIRST;
            else return VERTICAL_NO_OVERLAP;
        }
//    static boolean contain(int A, int B, int C, int D, int E, int F, int G, int H){
//        return (A>=E && B>=F && C<=G && D<=H);
//    }
}
