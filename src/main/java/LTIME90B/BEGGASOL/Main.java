package LTIME90B.BEGGASOL;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static void solve() {
        final int N = _in.nextInt();
        final int[] F = IntStream.range(0, N).map(x -> _in.nextInt()).toArray();

        if(F[0] <= 0) {
            System.out.println(0);
            return;
        };

        int distance = 0;
        for (int i = 1; i < N; i++) {
            distance++;
            F[i] += F[i-1]-1;
            if(F[i] <= 0) {
                System.out.println(i);
                return;
            };
        }

        distance += F[N-1];

        System.out.println(distance);
    }

    public static void main(String[] args) throws Exception {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
        }
    }
}