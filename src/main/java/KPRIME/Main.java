package KPRIME;

import java.io.*;
import java.util.*;

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static int N = 100000;
    private static int K = 5;
    private static int[] sieve = new int[N+1];
    private static int[][] integral = new int[K][N+1];
    static {
        sieveOfEratosthenes(N);
        integral(N, K);
    }

    private static void solve() {
        final int A = _in.nextInt();
        final int B = _in.nextInt();
        final int K = _in.nextInt();

        final int result = integral[K-1][B] - integral[K-1][A-1];
        System.out.println(result);
    }

    private static void sieveOfEratosthenes(final int n) {
        for (int i = 2; i <= n; i++) {
            if(sieve[i] != 0) continue;
            sieve[i] = 1;
            for (int j = i+i; j <= n; j+=i) {
                sieve[j]++;
            }
        }
    }

    private static void integral(final int n, final int k) {
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                integral[j][i] = integral[j][i-1];
            }
            final int x = sieve[i];
            if(x > K) continue;
            integral[x-1][i] = integral[x-1][i] + 1;
        }
    }

    public static void main(String[] args) {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
        }
    }
}