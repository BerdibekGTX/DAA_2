package cli;

import algorithms.Boyer_Moore_Majority_Vote;
import metrics.PerformanceTracker;
import java.util.Random;

public class BenchmarkRunner {

    private static int[] generateRandomArray(int n, int range) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(range);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] ns = {100, 1000, 10000, 100000};
        int range = 100; // размер диапазона значений в массиве (не влияет на сложность, но предотвращает "идеальные" случаи)

        System.out.printf("%10s%15s%15s%20s%20s%20s\n",
                "n", "Time(ms)", "Comparisons", "Array Accesses", "Memory Alloc", "Majority?");

        for (int n : ns) {
            int[] arr = generateRandomArray(n, range);
            Boyer_Moore_Majority_Vote algo = new Boyer_Moore_Majority_Vote();
            PerformanceTracker tracker = new PerformanceTracker();

            tracker.startTimer();
            Integer majority = algo.findMajority(arr);
            tracker.stopTimer();

            System.out.printf("%10d%15.3f%15d%20d%20d%20s\n",
                    n,
                    tracker.getElapsedTimeMillis(),
                    tracker.getComparisons(),
                    tracker.getArrayAccesses(),
                    tracker.getMemoryAllocations(),
                    majority != null ? "Yes" : "No"
            );
        }
    }
}
