## Lambda Expression

---

### Function Interface

```java
@FunctionalInterface
public interface Function<T, R> {
		R apply(T t);
}
```

- T라는 타입의 input을 받아 R이라는 타입의 값을 리턴하는 함수
- 함수의 구성요소
    - 함수의 이름
    - 반환 값의 타입(return type)
    - 매개변수(parameters)
    - 함수의 내용(body)
    

### Lambda Expression - 이름이 없는 함수(Anonymous function)

```java
Function<Integer, Integer> myAdder = (Integer x) -> {
		return x + 10;
}

Function<Integer, Integer> myAdder = x -> x + 10;
```

- 매개변수의 타입이 유추가능할 경우에는 타입 생략가능
- 매개변수가 하나일 경우에는 괄호 생략가능
- 바로 리턴하는 경우에는 괄호 생략가능

### BiFunction Interface - 매개변수가 두 개일 때

```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
		R apply(T t, U u);
}

BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        int result = add.apply(3, 6);
```

### Functional Interface - 함수의 뼈대

- 단하나의 abstract method만을 가지는 인터페이스
    
    (Single Abstract Method interface)
    
- Default method와 static method는 이미 구현이 되어있으므로 있어도 괜찮음
- java.lang.Runnable, java.util.Comparator, java.util.concurrent.Callable, etc

```java
@FunctionalInterface
public interface Function<T, R> {
		R apply(T t);
		default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
		…
		}
		default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
		…
		}
		static <T> Function<T, T> identity() {
		…
		}
}

@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

TriFunction<Integer, Integer, Integer, Integer> addThreeNumbers = (x, y, z) -> x + y + z;
        Integer apply = addThreeNumbers.apply(3, 5, 6);
```

### Summary

- Functional Interface
    - 단 하나의 abstract method를 가진 interface
    - Function Interface
    - BiFunction Interface
    - 나만의 Functional Interface
- Lambda Expression
    - 함수형 인터페이스를 구현하는 가장 간단한 방법