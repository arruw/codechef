package LTIME91B.SEDARR;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter _out = new PrintWriter(System.out);

    private static void solve() {
        final int N = _in.nextInt();
        final int K = _in.nextInt();
        final int[] A = IntStream.range(0, N).map(x -> _in.nextInt()).toArray();

        final int sum = Arrays.stream(A).sum();

        _out.println(sum % K == 0 ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
            _out.flush();
        }
    }
}