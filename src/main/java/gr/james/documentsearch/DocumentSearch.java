package gr.james.documentsearch;

import java.util.*;

public class DocumentSearch {
    /**
     * Returns the smallest interval in which {@code query} appear in {@code words} in the order given.
     * <p>
     * Returns -1 if no interval found.
     * <p>
     * This method uses extra space proportional to {@code words.length + query.length}. {@code T} must comply with
     * hashCode/equals contract.
     *
     * @param words the collection of items to search in
     * @param query the collection of items to search for
     * @param <T>   the type of items
     * @return the smallest interval in which {@code query} appear in {@code words} in the order given or {@code -1} if
     * no interval found
     */
    public static <T> int search(Iterator<T> words, Iterator<T> query) {
        final TreeSet<Integer> emptyTreeSet = new TreeSet<>();

        /* Basic data structure, needs to be a List with fast get, add and next */
        final List<TreeSet<Integer>> sets = new ArrayList<>();

        /* Terminate early (also avoid some exceptions) */
        if (!words.hasNext() || !query.hasNext()) {
            return -1;
        }

        /* Inverted word index */
        final Map<T, TreeSet<Integer>> invertedWordMap = new HashMap<>();
        for (int i = 0; words.hasNext(); ) {
            invertedWordMap.computeIfAbsent(words.next(), w -> new TreeSet<>()).add(i++);
        }

        /* Populate sets */
        while (query.hasNext()) {
            sets.add(invertedWordMap.getOrDefault(query.next(), emptyTreeSet));
        }

        /* Main loop */
        int best = -1;
        for (Integer lo : sets.get(0)) { // for every occurrence of the first word in the query
            Integer hi = lo;
            for (TreeSet<Integer> m : sets.subList(1, sets.size())) { // repeat for all words in query except first
                hi = m.higher(hi); // and find the next occurrence
                if (hi == null) { // but if there's no occurrence, we will never find a better interval
                    return best; // so return
                }
            }
            if (hi - lo + 1 < best || best < 0) { // find the minimum of all the intervals
                best = hi - lo + 1;
            }
        }

        return best;
    }
}
