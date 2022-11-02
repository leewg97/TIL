## Optional 만들기

```java
public static <T> Optional<T> of(T value)
public static <T> Optional<T> empty()
public static <T> Optional<T> ofNullable(T value)
```

- of : Null이 아닌 오브젝트를 이용해 Optional을 만들 때
- empty - 빈 Optional 만들 때
- ofNullable - Null인지 아닌지 알 지 못하는 오브젝트로 Optional을 만들 때

## Optional 안에 있는 값 확인하고 꺼내기

```java
public boolean isPresent()
public T get()
public T orElse(T other)
public T orElseGet(Supplier<? extends T> supplier)
public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X
```

- isPresent : 안의 오브젝트가 null인지 아닌지 체크. Null이 아닐 시 true
- get : Optional 안의 값을 추출. Null이라면 에러
- orElse : Optional이 null이 아니라면 Optional 안의 값을, null이라면 other로 공급된 값을 리턴
- orElseGet : Optional이 null이 아니라면 Optional 안의 값을, null이라면 supplier로 공급되는 값을 리턴
- orElseThrow : Optional이 null이 아니라면 Optional 안의 값을, null이라면 exceptionSupplier로 공급되는 exception을 던짐

## Optional 응용을 위해 알아야 할 것들

```java
public void ifPresent(Consumer<? super T> action)
public <U> Optional<U> map(Function<? super T, ? extends U> mapper)
public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper)
```

- ifPresent : Optional이 null이 아니라면 action을 실행
- map : Optional이 null이 mapper를 적용
- flatMap : mapper의 리턴 값이 또 다른 Optional이라면 한 단계의 Optional이 되도록 납작하게 해줌

### Summary
- Optional은 null일 수도, 아닐 수도 있는 오브젝트를 담을 때 사용
- Optional를 생성하고 추출하고 변환하는 operator들
    - of / ofNullable / empty
    - get / orElse / orElseGet / orElseThrow
    - isPresent
    - ifPresent
    - map / flatMap
