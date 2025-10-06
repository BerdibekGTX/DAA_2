package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class Boyer_Moore_Majority_Vote_Test {

    private static final String METRICS_FILE_NAME = "target/boyer_moore_metrics.csv";

    @BeforeAll
    static void clearMetricsFile() {
        try {
            boolean deleted = Files.deleteIfExists(Paths.get(METRICS_FILE_NAME));
            if (deleted) {
                System.out.println("Metrics file deleted successfully.");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(METRICS_FILE_NAME, false))) {
                writer.write("ArraySize,ElapsedTimeMs,Comparisons,ArrayAccesses,MemoryAllocations,MajorityFound\n");
            }
        } catch (IOException e) {
            System.err.println("Error while trying to delete or initialize metrics file: " + e.getMessage());
        }
    }

    private int[] generateRandomArray(int size, int bound) {
        Random rnd = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rnd.nextInt(bound);
        }
        return arr;
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 1000, 10000, 100000})
    public void testBoyerMooreMajorityVotePerformance(int size) {
        int[] arr = generateRandomArray(size, 1000);
        Boyer_Moore_Majority_Vote algo = new Boyer_Moore_Majority_Vote();
        PerformanceTracker tracker = new PerformanceTracker();

        tracker.startTimer();
        Integer majority = algo.findMajority(arr);
        tracker.stopTimer();

        System.out.printf("Array size: %d, Time: %.3f ms, Comparisons: %d, Array Accesses: %d, Memory Allocations: %d, Majority Found: %s%n",
                size, tracker.getElapsedTimeMillis(), tracker.getComparisons(),
                tracker.getArrayAccesses(), tracker.getMemoryAllocations(),
                majority != null ? "Yes" : "No");

        // Write metrics to CSV for easier analysis
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(METRICS_FILE_NAME, true))) {
            writer.write(String.format("%d,%.3f,%d,%d,%d,%s\n",
                    size,
                    tracker.getElapsedTimeMillis(),
                    tracker.getComparisons(),
                    tracker.getArrayAccesses(),
                    tracker.getMemoryAllocations(),
                    majority != null ? "Yes" : "No"));
        } catch (IOException e) {
            System.err.println("Failed to write metrics to CSV: " + e.getMessage());
        }

        // Проверка корректности результата - если majority найден, он должен быть мажоритарным
        if (majority != null) {
            long count = java.util.Arrays.stream(arr).filter(x -> x == majority).count();
            assertTrue(count > size / 2, "Majority element count must be greater than half the array size");
        }
    }
}