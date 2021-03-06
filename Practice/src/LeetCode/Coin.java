package LeetCode;

import java.util.*;

/**
 * Created by qiqu on 1/26/16.
 */
public class Coin {
    //322
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        HashSet<Integer> amounts = new HashSet<>();
        Queue<Integer> current = new LinkedList<>();
        amounts.add(0);
        int number = 0;
        while (amounts.size() > 0) {
            number++;
            current.addAll(amounts);
            amounts.clear();
            while (current.size() > 0) {
                int toBeAdd = current.poll();
                for (int coin : coins) {
                    int possible = coin + toBeAdd;
                    if (possible < amount) amounts.add(possible);
                    else if (possible == amount) return number;
                }
            }
            current.clear();
        }
        return -1;
    }

//    private int coinChange(int[] coins, int amount, int index){
//        if (amount==0) return 0;
//        if (index<0) return -1;
//        int num=amount/coins[index], toComplete=amount%coins[index], possible;
//        do {
//            possible=coinChange(coins,toComplete,--index);
//
//        }while (possible==-1 && index>0);
//        possible==-1?-1:num+p
//    }

    //70
    public int climbStairs(int n) {
        if (n < 2) return 1;
        int[] mem = new int[n + 1];
        mem[0] = mem[1] = 1;
        mem[2] = 2;
        for (int i = 3; i <= n; i++) mem[i] = 2 * mem[i - 2] + mem[i - 3];
        return mem[n];
    }
}
