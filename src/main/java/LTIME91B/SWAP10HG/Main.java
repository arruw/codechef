package LTIME91B.SWAP10HG;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter _out = new PrintWriter(System.out);

    private static void solve() {
        // Solution is not 100% correct, first test sets fails with WA, second and third test sets are valid. Final score is: 80 / 100
        final int N = _in.nextInt();
        final String S = _in.next();
        final String P = _in.next();

        final Map<Integer, Long> countS = S.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final Map<Integer, Long> countP = P.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final boolean result = countS.getOrDefault((int)'0', 0l) == countP.getOrDefault((int)'0', 0l)
                && countS.getOrDefault((int)'1', 0l) == countP.getOrDefault((int)'1', 0l);

        _out.println(result ? "Yes" : "No");
    }

    public static void main(String[] args) throws Exception {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
            _out.flush();
        }
    }
}