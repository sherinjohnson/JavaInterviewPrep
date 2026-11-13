# Java Interview Prep — Medium Level

Each topic folder has **runnable, self-contained `.java` files** with comments explaining
*what interviewers ask* and *why the code behaves the way it does*.

## How to run
Install JDK 17+ (e.g. `winget install Microsoft.OpenJDK.17`), then from any topic folder:

```bash
java OopDemo.java        # single-file source launch: no javac needed (Java 11+)
```

## Study order

| # | Folder | Key topics |
|---|--------|-----------|
| 01 | `01_OOP` | 4 pillars, overloading vs overriding, abstract class vs interface, `super`, constructors |
| 02 | `02_Strings` | immutability, String pool, `==` vs `equals`, StringBuilder vs StringBuffer |
| 03 | `03_Keywords_Memory` | `static`, `final`, pass-by-value, stack vs heap, wrapper caching, GC |
| 04 | `04_Exceptions` | checked vs unchecked, try-with-resources, `finally` traps, custom exceptions |
| 05 | `05_Collections` | List/Set/Map, HashMap internals, `equals`/`hashCode`, Comparable vs Comparator, fail-fast |
| 06 | `06_Generics` | bounded types, wildcards, PECS, type erasure |
| 07 | `07_Java8_Features` | lambdas, functional interfaces, streams, collectors, Optional, method refs |
| 08 | `08_Multithreading` | Thread vs Runnable, synchronized, volatile, ExecutorService, CompletableFuture, deadlock, producer-consumer |
| 09 | `09_Design_Patterns` | Singleton (thread-safe), Builder, Factory, Strategy, Immutable class |
| 10 | `10_Modern_Java` | records, sealed classes, switch expressions, `var`, text blocks, pattern matching |
| 11 | `11_Coding_Problems` | 20+ classic interview coding questions (strings, arrays, streams) |

See `INTERVIEW_QUESTIONS.md` for a rapid-fire Q&A revision sheet.
