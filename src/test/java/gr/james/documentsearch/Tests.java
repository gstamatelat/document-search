package gr.james.documentsearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tests {
    private final int size = 25;
    private final List<Integer> fullList = IntStream.iterate(0, x -> x + 1).limit(size).mapToObj(v -> v)
            .collect(Collectors.toList());

    @Test
    public void simpleRangeTests() {
        for (int a = 0; a < 2 * size; a++) {
            for (int b = 0; b < 2 * size; b++) {
                Iterator<Integer> query = IntStream.of(a, b).iterator();
                Assert.assertEquals(
                        (b > a && b < size) ? (b - a + 1) : (-1),
                        DocumentSearch.search(fullList.iterator(), query)
                );
            }
        }
    }

    @Test
    public void tripleRangeTests() {
        for (int a = 0; a < 2 * size; a++) {
            for (int b = 0; b < 2 * size; b++) {
                for (int c = 0; c < 2 * size; c++) {
                    Iterator<Integer> query = IntStream.of(a, b, c).iterator();
                    Assert.assertEquals(
                            (c > b && b > a && c < size) ? (c - a + 1) : (-1),
                            DocumentSearch.search(fullList.iterator(), query)
                    );
                }
            }
        }
    }
}
