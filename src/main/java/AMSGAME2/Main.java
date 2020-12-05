package AMSGAME2;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static long solve() {
        final int N = _in.nextInt();
        final int[] A = IntStream.range(0, N).map(i -> _in.nextInt()).sorted().toArray();

        Map<Integer,Long> DT = new TreeMap<Integer,Long>();
        Map<Integer,Long> addDT = new TreeMap<Integer,Long>();
        for (final int a: A) {
            addDT.merge(a, 1l, Long::sum);
            for (final int x: DT.keySet()) {
                addDT.merge(gcd(a, x), DT.get(x), Long::sum);
            }
            addDT.forEach((k, v) -> DT.merge(k, v, Long::sum));
            addDT.clear();
        }

        return DT.getOrDefault(1, 0l);
    }

    private static int gcd(int n1, int n2) {
        if (n1 == 0) {
            return n2;
        }

        if (n2 == 0) {
            return n1;
        }

        int n;
        for (n = 0; ((n1 | n2) & 1) == 0; n++) {
            n1 >>= 1;
            n2 >>= 1;
        }

        while ((n1 & 1) == 0) {
            n1 >>= 1;
        }

        do {
            while ((n2 & 1) == 0) {
                n2 >>= 1;
            }

            if (n1 > n2) {
                int temp = n1;
                n1 = n2;
                n2 = temp;
            }
            n2 = (n2 - n1);
        } while (n2 != 0);
        return n1 << n;
    }

    public static void main(String[] args) throws Exception {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            System.out.println(solve());
        }
    }
}