package metrics;


public class PerformanceTracker {
    private int comparisons;
    private int arrayAccesses;
    private int memoryAllocations;
    private long startTime;
    private long endTime;

    public PerformanceTracker() {
        reset();
    }

    public void reset() {
        comparisons = 0;
        arrayAccesses = 0;
        memoryAllocations = 0;
        startTime = 0L;
        endTime = 0L;
    }


    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public double getElapsedTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public void incrementComparisons() { comparisons++; }
    public void incrementArrayAccesses() { arrayAccesses++; }
    public void incrementMemoryAllocations() { memoryAllocations++; }

    public int getComparisons() { return comparisons; }
    public int getArrayAccesses() { return arrayAccesses; }
    public int getMemoryAllocations() { return memoryAllocations; }

    public String report() {
        return String.format(
                "Comparisons: %d, Array accesses: %d, Memory allocations: %d, Elapsed time (ms): %.3f",
                comparisons, arrayAccesses, memoryAllocations, getElapsedTimeMillis());
    }
}
