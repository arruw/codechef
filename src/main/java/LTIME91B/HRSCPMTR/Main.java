package LTIME91B.HRSCPMTR;

import java.io.*;
import java.util.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter _out = new PrintWriter(System.out);

    private static void solve() {
        final int N = _in.nextInt();
        final int M = _in.nextInt();

        final List<List<Integer>> diagonals = new ArrayList<List<Integer>>(N+M-1){{
            for (int i = 0; i < N+M-1; i++) {
                add(new ArrayList<Integer>());
            }
        }};

        final Boolean[] ok = new Boolean[N+M-1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                final int A = _in.nextInt();
                final int di = diagonalIndex(j, i, M);
                diagonals.get(di).add(A);
            }
        }

        for (int i = 0; i < N+M-1; i++) {
            ok[i] = diagonals.get(i).stream().distinct().count() == 1;
        }

        final int Q = _in.nextInt();

        for (int i = 0; i < Q; i++) {
            final int X = _in.nextInt() - 1;
            final int Y = _in.nextInt() - 1;
            final int V = _in.nextInt();

            final int di = diagonalIndex(Y, X, M);
            final List<Integer> diagonal = diagonals.get(di);
            final int vi = valueIndex(Y, X);
            diagonal.remove(vi);
            diagonal.add(vi, V);

            ok[di] = diagonal.stream().distinct().count() == 1;

            final boolean result = Arrays.stream(ok).allMatch(x -> x);

            _out.println(result ? "Yes" : "No");
        }
    }

    private static int diagonalIndex(int x, int y, final int M) {
        if (x >= y) {
            x -= y;
            y -= y;
            return x + y;
        } else {
            x -= x;
            y -= x;
            return M - 1 + x + y;
        }
    }

    private static int valueIndex(int x, int y) {
        return x >= y ? y : x;
    }

    public static void main(String[] args) throws Exception {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
            _out.flush();
        }
    }
}