package LRNDSA07.AUHASH;

import java.io.*;
import java.util.*;
import java.util.stream.*;

class Tuple {
    final char c;
    final char i;

    public Tuple(char c, char i) {
        this.c = c;
        this.i = i;
    }
}

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter _out = new PrintWriter(System.out);

    private static final char[] _chars = new char[2*26];
    static {
        int m = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            _chars[m] = i;
            m++;
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            _chars[m] = i;
            m++;
        }
    }

    private static int charValue(char c) {
        if ('a' <= c && c <= 'z') {
            return c - 'a' + 1;
        }

        return c - 'A' + 27;
    }

    private static void solve() {
        final int L = _in.nextInt(); // length
        final int S = _in.nextInt(); // sum

        if (L > 52 || S > 1378){
            _out.println(0);
            return;
        }
        
        final int[][] DT = new int[L][S];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < S; j++) {

            }
        }
        

        _out.println(0);
    }

    public static void main(String[] args) throws Exception {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
            _out.flush();
        }
    }
}