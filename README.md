# Document Search

An efficient implementation for searching the shortest interval of a query inside a document:

> Design an algorithm that takes a sequence of `n` document words and a sequence of `m` query words and find the shortest interval in which the `m` query words appear in the document in the order given. The length of an interval is the number of words in that interval.

From Kevin Wayne's and Robert Sedgewick's "Algorithms, Part I".

## Algorithm

Given two ordered streams `words` and `query`:

**Preprocessing**

1. Declare `SETS` as list of ordered sets.
2. Iterate through `words` and create the inverted index `IW(Word,SET<Index>)`.
3. Iterate through `query` and for each element `x` do `SETS[x] = IW[x]`.

**Main loop**

Let `s.higher(e)` denote a function that returns the least element in `s` that is greater than `e`.

```
best = list()
for lo from SETS[0].min to SETS[0].max
  hi = lo
  foreach k in SETS
    hi = k.higher(hi)
  best.insert(hi - lo)
return best.min
```

## Interface

```
public static <T> int search(Iterator<T> words, Iterator<T> query)
```
Returns the length of the shortest interval in which `query` appears in order inside `words` or `-1` if no interval found. `T` must comply with the `hashCode`/`equals` contract.

## Client

```
document-search words.txt query.txt
```
The input files are tokenized using the `Scanner` class, which by default includes whitespace, line breaks, hard tabs etc.
