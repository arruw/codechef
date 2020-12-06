package APARTS;

import java.io.*;
import java.util.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter _out = new PrintWriter(System.out);

    private static void solve() {
        final int N = _in.nextInt();
        final int M = _in.nextInt();
        final int[][] A = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                A[i][j] =  _in.nextInt();
            }
        }

        int prevMax = 0, currMax = 0, max = 0;
        for (int i = 0; i < N; i++) {
            prevMax = 0;
            for (int j = 0; j < M; j++) {
                if (i > 0) {
                    currMax = (j < M - 1) ? Math.max(A[i-1][j], A[i-1][j+1]) : A[i-1][j];
                    max = Math.max(prevMax, currMax);
                }
                if (A[i][j] > max) {
                    _out.print(1);
                } else {
                    A[i][j] = max;
                    _out.print(0);
                }
                prevMax = currMax;
            }
            _out.println();
        }
        _out.flush();
    }

    public static void main(String[] args) throws Exception {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
        }
    }
}