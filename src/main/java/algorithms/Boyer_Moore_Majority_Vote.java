package algorithms;
import java.util.Scanner;
import java.util.Arrays;

public class Boyer_Moore_Majority_Vote {
    private int comparisons = 0;
    private int arrayAccesses = 0;
    private int memoryAllocations = 0;

    public Integer findMajority(int[] nums) {
        if (nums == null) throw new IllegalArgumentException("Input array cannot be null");
        if (nums.length == 0) return null;
        memoryAllocations++;
        Integer candidate = null;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            arrayAccesses++;
            if (count == 0) {
                candidate = nums[i];
                count = 1;
                comparisons++;
            } else {
                arrayAccesses++;
                comparisons++;
                if (nums[i] == candidate) count++;
                else count--;
            }
        }
        count = 0;
        for (int num : nums) {
            arrayAccesses++;
            comparisons++;
            if (num == candidate) count++;
        }
        return (count > nums.length / 2) ? candidate : null;
    }

    public int getComparisons() {
        return comparisons;
    }
    public int getArrayAccesses() {
        return arrayAccesses;
    }
    public int getMemoryAllocations() {
        return memoryAllocations;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter array elements, space-separated:");
        String[] parts = sc.nextLine().trim().split("\\s+");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }
        Boyer_Moore_Majority_Vote bm = new Boyer_Moore_Majority_Vote();
        Integer result = bm.findMajority(nums);
        System.out.println("Majority element: " + result);
        System.out.println("Comparisons: " + bm.getComparisons());
        System.out.println("Array accesses: " + bm.getArrayAccesses());
        System.out.println("Memory allocations: " + bm.getMemoryAllocations());
    }
}
