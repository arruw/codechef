package LRNDSA07.SUBINC;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static void solve() {
        final int N = _in.nextInt();
        final int[] A = IntStream.range(0, N)
                .map(x -> _in.nextInt())
                .toArray();

        long[] DT = new long[N];
        DT[0] = 1;
        long result = 0;
        for (int i = 1; i < N; i++) {
            if(A[i-1] <= A[i]) {
                DT[i] = DT[i-1] + 1;
            } else {
                DT[i] = 1;
                result += (DT[i-1] * (DT[i-1] + 1) / 2);
            }
        }
        result += (DT[N-1] * (DT[N-1] + 1) / 2);

        System.out.println(result);
    }

    public static void main(String[] args) {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
        }
    }
}