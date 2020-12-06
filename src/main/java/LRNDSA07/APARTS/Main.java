 package LRNDSA07.APARTS;

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

        List<Integer> upperWindows = new ArrayList<Integer>(3);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(i > 0) {
                    upperWindows.add(A[i-1][j]);
                    if(j > 0) upperWindows.add(A[i-1][j-1]);
                    if(j < M - 1) upperWindows.add(A[i-1][j+1]);
                }
                int max = upperWindows.size() > 0 ? Collections.max(upperWindows) : 0;
                if (A[i][j] > max) {
                    _out.print(1);
                } else {
                    A[i][j] = max;
                    _out.print(0);
                }
                upperWindows.clear();
            }
            _out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
            _out.flush();
        }
    }
}