package LRNDSA07.CDQU5;

import java.io.*;
import java.util.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static final int MOD = 1000000009;

    private static int memoLen = 4;
    private static int[] memo = new int[1000000];
    static {
        memo[0] = 0;
        memo[1] = 0;
        memo[2] = 1;
        memo[3] = 1;
    }

    private static long solve(final int X) {
        if(X < memoLen) return memo[X];

        for (int i = memoLen; i <= X; i++) {
            memo[i] = (memo[i-2] + memo[i-3]) % MOD;
        }
        memoLen = X+1;

        return memo[X];
    }

    public static void main(String[] args) {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            System.out.println(solve(_in.nextInt()));
        }
    }
}