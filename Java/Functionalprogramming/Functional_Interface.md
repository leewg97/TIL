### Supplier

---

```java
@FunctionalInterface
public interface Supplier<T> {
	T get();
}
```

- Input 없이 Return 값만 가지고 있는 함수

```java
public static void main(String[] args) {
    Supplier<Double> doubleSupplier = () -> Math.random();
    printRandomDoubles(doubleSupplier, 5);
}
public static void printRandomDoubles(Supplier<Double> randomSupplier, Integer count) {
    for (int i = 0; i < count; i++) {
        System.out.println(randomSupplier.get());
    }
}
```

### Consumer

---

```java
@FunctionalInterface
public interface Consumer<T> {
	void accept(T t);
}
```

- 받기만 하고 아무것도 return하지 않는 함수

```java
public static void main(String[] args) {
    List<Integer> integerList = Arrays.asList(4, 2, 3);
    Consumer<Integer> integerConsumer = 
										x -> System.out.println("Processing Integer" + x);
    process(integerList, integerConsumer);

    Consumer<Integer> anotherIntegerProcessor = 
										x -> System.out.println("Another Processing Integer" + x);
    process(integerList, anotherIntegerProcessor);

		List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
    Consumer<Double> doubleConsumer = 
										x -> System.out.println("Double Processing" + x);
    process(doubles, doubleConsumer);
}

public static <T> void process(List<T> inputs, Consumer<T> processor) {
    for (T input : inputs) {
        processor.accept(input);
    }
}
```

### BiConsumer

---

```java
@FunctionalInterface
public interface BiConsumer<T, U> {
	void accept(T t, U u);
}
```

- input 2개

```java
public static void main(String[] args) {
    BiConsumer<Integer, Double> myDoubleProcessor =
            (index, input) ->
                    System.out.println("Processing " + input + " at index " + index);
    List<Double> inputs = Arrays.asList(1.1, 2.2, 3.3);
    process(inputs, myDoubleProcessor);
}

public static <T> void process(List<T> inputs, BiConsumer<Integer, T> processor) {
    for (int i = 0; i < inputs.size(); i++) {
        processor.accept(i, inputs.get(i));
    }
}
```

### Predicate

---

```java
@FunctionalInterface
public interface Predicate<T> {
	boolean test(T t);
}
```

- test라는 하나의 메소드를 갖고, 이 test는 input을 받아 boolean으로 return 한다

```java
public static void main(String[] args) {
	Predicate<Integer> isPositive = x -> x > 0;
	System.out.println(isPositive.test(-10));
	
	List<Integer> inputs = Arrays.asList(10, -5, 4, -2, 0, 3);
	System.out.println("Positive number: " + filter(inputs, isPositive));
	System.out.println("Non-positive number: " + filter(inputs, isPositive.negate()));
	System.out.println("Non-negative number: " + filter(inputs, isPositive.or(x -> x == 0)));
	System.out.println("Positive even numbers: " + filter(inputs, isPositive.and(x -> x % 2 == 0)));
}

public static <T> List<T> filter(List<T> inputs, Predicate<T> condition) {
	List<T> output = new ArrayList<>();
	for (T input : inputs) {
		if (condition.test(input)) {
			output.add(input);
		}
	}
	return output;
}
```

### Comparator

---

- 비교를 위한 인터페이스

```java
@FunctionalInterface
public interface Comparator<T> {
	int compare(T o1, T o2);
}
```

- <T> 선언하고 input 2개를 받는다
- 음수면 o1 < o2
- 0이면 o1 = o2
- 양수면 o1 > o2

```java
public static void main(String[] args) {
	List<User> users = new ArrayList<>();
	users.add(new User(3, "Alice"));
	users.add(new User(1, "Charlie"));
	users.add(new User(5, "Bob"));
	System.out.println(users);
	
	Comparator<User> idComparator = (u1, u2) -> u1.getId() - u2.getId();
	Collections.sort(users, idComparator);
	System.out.println(users);
	
	Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
	System.out.println(users);
}
```

### Summary

---

- 여러가지 Functional Interface
    - Supplier
    - Consumer / BiConsumer
    - Prdicate
    - Comparator
- 1등 시민으로서의 함수를 활용하는 방법