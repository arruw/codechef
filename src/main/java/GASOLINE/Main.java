package GASOLINE;

import java.io.*;
import java.util.*;
import java.util.stream.*;

class Input implements Comparable<Input> {
    public int fuel, cost;

    public Input(int fuel, int cost) {
        this.fuel = fuel;
        this.cost = cost;
    }

    @Override
    public int compareTo(Input o) {
        return Long.compare(this.cost, o.cost);
    }
}

public class Main {
    private static final Scanner _in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static long solve() {
        final int N = _in.nextInt();
        final int[] F = IntStream.range(0, N).map(x -> _in.nextInt()).toArray();
        final int[] C = IntStream.range(0, N).map(x -> _in.nextInt()).toArray();

        if(N == 1) return 0;

        List<Input> inputs = new ArrayList<Input>(N);
        for (int i = 0; i < N; i++) {
            inputs.add(new Input(F[i], C[i]));
        }
        Collections.sort(inputs);

        long totalCost = 0;
        long totalLiters = 0;
        int i = 0;
        while(totalLiters < N) {
            Input input = inputs.get(i);
            long liters = Math.min(N - totalLiters, input.fuel);
            totalLiters += liters;
            totalCost += liters * input.cost;
            i++;
        }
        return totalCost;
    }

    public static void main(String[] args) throws Exception {
        final int T = _in.nextInt();
        for (int i = 0; i < T; i++) {
            System.out.println(solve());
        }
    }
}