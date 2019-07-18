package io.github.salestaxes;

import org.junit.Test;

public class MainTest {

    final String basket1 = "samples/basket1.txt";
    final String basket2 = "samples/basket2.txt";
    final String basket3 = "samples/basket3.txt";

    @Test
    public void testMainToBeTrue() {
        // Setup
        final String[] args = new String[]{basket1, basket2, basket3};
        // Run the test
        Main.main(args);
        // Verify the results
    }

}
