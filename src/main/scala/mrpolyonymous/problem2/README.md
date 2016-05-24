# Performance Findings

JMH benchmarks run with the following command:

`jmh:run -i 10 -wi 10 -f1 -t1 .*Problem2.*`

Standard disclaimers for how much trust to put in benchmarks apply.

## Results for plain sequence construction

```
[info] Benchmark              Mode  Cnt         Score          Error  Units
[info] Problem2.arrayBuffer  thrpt   10  32563300.793   11816936.753  ops/s
[info] Problem2.consList     thrpt   10  16235249.709    4464017.347  ops/s
[info] Problem2.fixedArray   thrpt   10  90693185.692   19047201.785  ops/s
[info] Problem2.fixedList    thrpt   10   4768203.188    1063509.853  ops/s
```

Findings:

* Initializing a fixed-sized List is much slower than anything else. Surprising. TODO: investigate how Scala does this.
* Initializing a fixed-sized array is the fastest. Not terribly surprising.
* ArrayBuffer almost exactly 2x faster than the `::` operator. Interesting coincidence

## Results for computing the 30th Fibonacci number

```
[info] Benchmark                          Mode  Cnt          Score          Error  Units
[info] Problem2.bmIterativeFibWithFor    thrpt   10   11801648.650    2969524.299  ops/s
[info] Problem2.bmIterativeFibWithWhile  thrpt   10  257829285.880   67852139.893  ops/s
[info] Problem2.bmRecursiveFib           thrpt   10        150.502         32.512  ops/s
[info] Problem2.bmTailRecursiveFib       thrpt   10   23571546.658    6745352.515  ops/s
```

Findings:

* `while` loop is surprisingly much faster than a `for` loop
* `for` loop is slower than tail recursion

## Results for computing a sequence of the first 30 Fibonacci numbers

```
[info] Benchmark                          Mode  Cnt          Score          Error  Units
[info] Problem2.sequenceWithIteration    thrpt   10    2407135.090     570146.172  ops/s
[info] Problem2.sequenceWithStream       thrpt   10     171612.491      29913.576  ops/s
```

Findings:

* The clever Stream construction from the Stream documentation is a nice one-liner but significantly slower than an iterative method

## References

1. [Java micro benchmark harness (JMH)](http://openjdk.java.net/projects/code-tools/jmh/)
2. [SBT plugin for running JMH](https://github.com/ktoso/sbt-jmh)
