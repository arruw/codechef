package LRNDSA07.GDSUB;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static int MOD = 1000000007;

    private static long solve(final int N, int K, final int[] A) {

        // there is 1007 prime numbers between 2 and 8000
        K = Math.min(K,1007);

        final Long[] freqs = Arrays.stream(A)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()))
                .values().toArray(new Long[0]);

        long[] counts = new long[K+1];
        counts[0] = 1; // empty subsequence []

        for (int i = 0; i < freqs.length; i++) {
            long freq = freqs[i];
            for (int j = K; j > 0; j--) {
                counts[j] = (counts[j] + ((counts[j-1] * freq) % MOD)) % MOD;
            }
        }

        long count = 0;
        for (int i = 0; i <= K; i++) {
            count = (count + counts[i]) % MOD;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        final int N = _in.nextInt();
        final int K = _in.nextInt();
        final int[] A = IntStream.range(0, N).map(x -> _in.nextInt()).toArray();
        System.out.println(solve(N, K, A));
    }
}