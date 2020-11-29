package FROGV;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static void solve() {
        final int N = _in.nextInt();
        final int K = _in.nextInt();
        final int P = _in.nextInt();

        // [frog index] = x-coordinate
        final int[] A = IntStream.range(0, N).map(i -> _in.nextInt()).toArray();

        // sorted ascending
        // [sort index] = frog index
        final int[] X = IntStream.range(0, N)
                .boxed()
                .sorted((i, j) -> Integer.compare(A[i], A[j]))
                .mapToInt(x -> x)
                .toArray();

        // color frogs if they can communicate
        // [frog index] = frog color
        int[] groups = new int[A.length];
        for (int i = 1; i < N; i++) {
            final int d =  A[X[i]] - A[X[i-1]];
            groups[X[i]] = d <= K
                    ? groups[X[i-1]]
                    : groups[X[i-1]]+1;
        }

        // answer questions
        for (int i = 0; i < P; i++) {
            final int F1 = _in.nextInt()-1;
            final int F2 = _in.nextInt()-1;

            System.out.println(groups[F1] == groups[F2] ? "Yes" : "No");
        }
    }

    public static void main(String[] args) throws Exception {
        solve();
    }
}