# Rapid-Fire Java Interview Q&A (Medium Level)

## Core / JVM
1. **JDK vs JRE vs JVM?** JVM runs bytecode. JRE = JVM + libraries. JDK = JRE + dev tools (javac, jdb). Since Java 11 there's no separate JRE download.
2. **Why is Java platform independent?** It compiles to bytecode, and each OS has its own JVM that runs it.
3. **Is Java pass-by-value?** Always. For objects, the value passed is a copy of the reference.
4. **`==` vs `equals()`?** `==` compares references (or primitive values). `equals()` compares logical content when overridden.
5. **equals/hashCode contract?** Equal objects must have equal hashCodes. If you override one, override both, or HashMap/HashSet break.
6. **Why is String immutable?** For the String pool, security, thread safety and hashCode caching.
7. **What does `final`, `finally` and `finalize` mean?** `final` is a modifier: no reassign, no override, no extend. `finally` is a block that always runs after try. `finalize` is a deprecated GC hook.
8. **What does `static` mean?** It belongs to the class. Static methods can't access instance members or `this`.
9. **Can you override a static or private method?** No. A static method with the same signature is *hidden*. A private method isn't inherited.
10. **Can a constructor be private?** Yes. Singletons, utility classes and factory methods use it.
11. **What does `this()` vs `super()` do?** They call a constructor of the same class or of the parent class. The call must come first, and you can only use one of them.
12. **What are the memory areas?** Heap (objects), Stack (per-thread frames), Metaspace (class metadata), PC register and native stack.
13. **What's the difference between ClassNotFoundException and NoClassDefFoundError?** The first happens when a class is loaded dynamically and isn't found. The second happens when a class that was present at compile time is missing at runtime.
14. **What's the difference between shallow and deep copy?** `clone()` is shallow by default. A deep copy also copies the nested mutable objects. A copy constructor is the better choice.
15. **What are `transient` and `volatile`?** `transient` skips a field during serialization. `volatile` makes changes visible across threads and stops instruction reordering.

## OOP
16. **What's the difference between overloading and overriding?** Overloading uses the same name with different params and is chosen at compile time. Overriding uses the same signature in a subclass and is chosen at runtime.
17. **What are the overriding rules?** The return type is the same or covariant. Access can't be narrower. Checked exceptions can't be broader.
18. **Abstract class vs interface?** An abstract class has state and constructors, and you can extend only one. An interface defines a contract, and a class can implement many. Interfaces gained default methods in Java 8.
19. **Composition vs inheritance?** Prefer composition (has-a). It's flexible and avoids fragile base classes.
20. **Can an interface have a constructor?** No.

## Collections
21. **How does HashMap work internally?** It hashes the key and finds the bucket with `(n-1) & hash`. Collisions go into a linked list, which becomes a red-black tree past 8 entries (once capacity ≥ 64). It resizes at a load factor of 0.75.
22. **HashMap vs ConcurrentHashMap vs Hashtable?** HashMap isn't thread-safe and allows one null key. ConcurrentHashMap is thread-safe with fine-grained locking and allows no nulls. Hashtable is legacy, synchronizes everything, and allows no nulls.
23. **ArrayList vs LinkedList?** ArrayList gives O(1) random access. LinkedList gives O(1) insert and remove at the ends. In practice ArrayList is usually faster.
24. **HashSet internally?** It's backed by a HashMap, where each element is a key and the value is a dummy constant.
25. **Comparable vs Comparator?** Comparable is the natural order, defined by `compareTo` inside the class. Comparator is an external order, and you can have many of them.
26. **Fail-fast vs fail-safe?** A fail-fast iterator throws ConcurrentModificationException if the collection changes during iteration (ArrayList, HashMap). A fail-safe iterator works on a snapshot or is weakly consistent (CopyOnWriteArrayList, ConcurrentHashMap).
27. **How do you make a collection read-only?** Use `List.of()`, `Collections.unmodifiableList()` or `List.copyOf()`.
28. **TreeMap complexity?** O(log n). It's a red-black tree with sorted keys.

## Exceptions
29. **Checked vs unchecked?** The compiler forces you to handle checked exceptions (IOException). Unchecked exceptions are RuntimeException and its subclasses.
30. **throw vs throws?** `throw` throws an exception instance. `throws` declares the exceptions a method can throw.
31. **Does finally always run?** Yes, except with System.exit, a JVM crash or a killed thread. A return inside finally overrides the try's return.
32. **What's special about try-with-resources?** It needs AutoCloseable and closes resources in reverse order. Exceptions from close() are *suppressed*.

## Java 8+
33. **What's a functional interface?** An interface with exactly one abstract method, like Runnable, Comparator, Predicate, Function, Consumer or Supplier.
34. **map vs flatMap?** `map` gives one-to-one output. `flatMap` turns one element into a stream and then flattens the results.
35. **Intermediate vs terminal operations?** Intermediate operations are lazy and return a Stream. Terminal operations trigger execution. You can consume a stream only once.
36. **What is Optional for?** To avoid null checks in return types. Use `orElseGet` for expensive defaults, and don't call `get()` blindly.
37. **What are default methods for?** They let you evolve an interface without breaking existing implementations.
38. **Stream vs parallelStream?** parallelStream uses the ForkJoin common pool. It helps only for large, CPU-bound, stateless work.
39. **What's a record?** An immutable data class with auto-generated equals, hashCode, toString and accessors (Java 16).
40. **What's a sealed class?** A class that limits which classes may extend it. Combined with switch, the compiler can check that every case is handled (Java 17).

## Multithreading
41. **Thread vs Runnable?** Prefer Runnable or Callable submitted to an ExecutorService.
42. **start() vs run()?** `start()` creates a new thread. Calling `run()` just runs the method on the current thread.
43. **sleep() vs wait()?** `sleep` keeps the lock. `wait` releases it and must be called inside synchronized code.
44. **synchronized vs ReentrantLock?** A Lock adds tryLock, timeouts, fairness and multiple conditions, but you have to unlock it in finally.
45. **volatile vs Atomic?** volatile only guarantees visibility. Atomic classes also give atomic compound operations via CAS.
46. **Runnable vs Callable?** Callable returns a value and can throw checked exceptions.
47. **How do deadlocks happen, and how do you prevent them?** A deadlock is a circular wait for locks. Prevent it with a consistent lock order, tryLock with a timeout, or fewer locks.
48. **What's ThreadLocal?** A per-thread copy of a variable, used for things like user context or SimpleDateFormat. Call remove() when you're done if you use thread pools.
49. **CountDownLatch vs CyclicBarrier?** A latch is one-shot: threads wait for others to count down. A barrier is reusable: threads wait for each other.
50. **What are virtual threads (Java 21)?** Lightweight threads managed by the JVM, good for high-concurrency blocking I/O.

## Tricky outputs to remember
```java
Integer a = 127, b = 127;  a == b   // true  (Integer cache)
Integer c = 128, d = 128;  c == d   // false
System.out.println(1 + 2 + "3" + 4 + 5);   // "3345"
System.out.println('a' + 'b');            // 195
System.out.println(0.1 + 0.2 == 0.3);      // false
System.out.println(Math.min(Double.MIN_VALUE, 0.0)); // 0.0 (MIN_VALUE is smallest POSITIVE)
String s = null; s + "x"                   // "nullx"
list.remove(1) on List<Integer>            // removes by INDEX, not value
```
