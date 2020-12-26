package LTIME91B.THREE;

import java.io.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter _out = new PrintWriter(System.out);

    private static void solve() {
        final String S = _in.next();

        if (S.length() < 3) {
            _out.println(0);
            return;
        }

        final Map<Integer, Long> counts = S.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long countPairs = counts.values().stream().map(c -> c / 2).mapToLong(c -> c).sum();
        long countRemainders = counts.values().stream().map(c -> c % 2).mapToLong(c -> c).sum();

        while (countPairs > countRemainders && countPairs > 0) {
            countPairs -= 1;
            countRemainders += 2;
        }

        _out.println(countPairs);
    }


    public static void main(String[] args) throws Exception {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
            _out.flush();
        }
    }
}