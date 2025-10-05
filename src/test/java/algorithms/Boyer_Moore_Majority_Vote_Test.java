package algorithms;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Boyer_Moore_Majority_Vote_Test {
    Boyer_Moore_Majority_Vote bm = new Boyer_Moore_Majority_Vote();

    @Test
    void testEmptyArray() {
        assertNull(bm.findMajority(new int[]{}));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, bm.findMajority(new int[]{1}));
    }

    @Test
    void testNoMajority() {
        assertNull(bm.findMajority(new int[]{1,2,3,4}));
    }

    @Test
    void testMajorityPresent() {
        assertEquals(2, bm.findMajority(new int[]{2,2,1,1,1,2,2}));
    }

    @Test
    void testAllDuplicates() {
        assertEquals(3, bm.findMajority(new int[]{3,3,3,3}));
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> bm.findMajority(null));
    }
}
