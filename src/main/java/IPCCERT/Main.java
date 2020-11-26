package IPCCERT;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        final int N = _in.nextInt();
        final int M = _in.nextInt();
        final int K = _in.nextInt();

        int count = 0;
        for (int i = 0; i < N; i++) {
            final int[] T = IntStream.range(0, K)
                    .map(x -> _in.nextInt())
                    .toArray();
            final int Q = _in.nextInt();

            if(Q > 10) continue;

            int t = 0;
            for (int j = 0; j < K; j++) {
                t += T[j];
                if (t >= M) {
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);
    }
}