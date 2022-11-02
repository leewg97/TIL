## 종결 처리

- 종결 처리를 통해 퇴종 결과물 도출
- 종결 처리의 실행이 필요할 때 중간 처리들도 비로소 실행(Lazy Evaluation)

## Max / Min / Count
Stream 안의 데이터의 최대값 / 최소값 / 개수

```java
Optional<T> max(Comparator<? super T> comparator);
Optional<T> min(Comparator<? super T> comparator);
long count();
```

- max – Stream 안의 데이터 중 최대값을 반환. Stream이 비어있다면 빈 Optional을 반환
- min – Stream 안의 데이터 중 최소값을 반환. Stream이 비어있다면 빈 Optional을 반환
- count – Stream 안의 데이터의 개수를 반환

## All Match / Any Match / Find First / Find Any

```java
boolean allMatch(Predicate<? super T> predicate);
boolean anyMatch(Predicate<? super T> predicate);

Optional<T> findFirst();
Optional<T> findAny();
```

- allMatch – Stream 안의 모든 데이터가 predicate을 만족하면 true
- anyMatch – Stream 안의 데이터 중 하나라도 predicate을 만족하면 true
- findFirst – Stream 안의 첫번째 데이터를 반환
    - Stream이 비어있다면 비어있는 Optional을 반환
- findAny – Stream 안의 아무 데이터나 리턴
    - 순서가 중요하지 않고 Parallel Stream을 사용할 때 최적화를 할 수 있음
    - 마찬가지로 Stream이 비어있다면 빈 Optional을 반환

## Reduce

- 주어진 함수를 반복 적용해  Stream 안의 데이터를 하나의 값으로 합치는 작업
- Max / Min / Count도 사실 reduce의 일종

```java
Optional<T> reduce(BinaryOperator<T> accumulator);
T reduce(T identity, BinaryOperator<T> accumulator);
<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
```

- reduce 1 – 주어진 accumulator를 이용해 데이터를 합침
    - Stream이 비어있을 경우 빈 Optional을 반환
- reduce 2 – 주어진 초기값과 accumulator를 이용
    - 초기값이 있기 때문에 항상 반환값이 존재
- reduce 3 – 합치는 과정에서 타입이 바뀔 경우 사용
    - Map + reduce로 대체 가능

## Collectors

### collect

```java
<R, A> R collect(Collector<? super T, A, R> collector);
java.util.stream.Collectors
Collector<T, ?, List<T>> toList();
Collector<T, ?, Set<T>> toSet();
```

- collect – 주어진 collector를 이용해 Stream안의 데이터를 합침
    - 일반적으로 특정 data structure로 데이터를 모을 때 사용
- Collectors – 자주 쓰일법한 유용한 collector들을 모아놓은 util class
    - java.util.stream 패키지에서 제공

### toList / toSet

```java
<R, A> R collect(Collector<? super T, A, R> collector);
java.util.stream.Collectors
Collector<T, ?, List<T>> toList();
Collector<T, ?, Set<T>> toSet();
```

- toList – Stream 안의 데이터를 List 형태로 반환해주는 collector
- toSet – Stream 안의 데이터를 Set 형태로 반환해주는 collector
    - Set이기 때문에 중복값은 사라지고 순서가 무의미해짐에 유의

### mapping / reducing

```java
public static <T, U, A, R> Collector<T, ?, R> mapping(Function<? super T, ? extends U> mapper, Collector<? super U, A, R> downstream)
public static <T> Collector<T, ?, T> reducing(T identity, BinaryOperator<T> op)
```

- mapping – Map과 collect를 합쳐놓은 역할을 해주는 collector
    - 일반적으로는 map을 한 후 collect를 해도 되지만 groupingBy 등 필요할 때가 있음
- reducing – reduce를 해주는collector
- 이외에도 filtering, flatMapping, counting, minBy, maxBy 등도 있음

## To Map

```java
public static <T, K, U> Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper)
```

- Stream 안의 데이터를 map의 형태로 반환해주는 collector
- keyMapper – 데이터를 map의 key로 변환하는 Function
- valueMapper – 데이터를 map의 value로 변환하는 Function

![image](https://user-images.githubusercontent.com/77063888/199607685-9fafbb82-2dd1-4008-86b5-8e32d5138d6a.png)
## Grouping By

```java
public static <T, K> Collector<T, ?, Map<K, List<T>>>
		groupingBy(Function<? super T, ? extends K> classifier)
```

- Stream 안의 데이터에 classifier를 적용했을 때 결과값이 같은 값끼리 List로 모아서 Map의 형태로 반환해주는 collector
    - 이 때 key는 classifier의 결과값, value는 그 결과값을 갖는 데이터들
- 예를 들어 stream에 {1, 2, 5, 7, 9, 12, 13}이 있을 때 classifier가 x -> x % 3이라면 반환되는 map은 {0 = [9, 12], 1 = [1, 7, 13], 2 = [2, 5]}.

```java
public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream)
```

- 두 번째 매개변수로 downstream collector를 넘기는 것도 가능
- 그 경우 List 대신 collector를 적용시킨 값으로 map의 value가 만들어짐
- 이 때 자주 쓰이는 것이 mapping / reducing 등의 collector

## Partitioning By

```java
public static <T>
		Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(Predicate<? super T> predicate)
public static <T, D, A>
		Collector<T, ?, Map<Boolean, D>> partitioningBy(Predicate<? super T> predicate, Collector<? super T, A, D> downstream)
```

- GroupingBy와 유사하지만 Function 대신 Predicate을 받아 true와 false 두 key가 존재하는 map을 반환하는 collector
- 마찬가지로 downstream collector를 넘겨 List 이외의 형태로 map의value를 만드는 것 역시 가능

## For Each

```java
void forEach(Consumer<? super T> action);
```

- 제공된 action을 Stream의 각 데이터에 적용해주는 종결 처리 메서드
- Java의 iterable 인터페이스에도 forEach가 있기 때문에 Stream의 중간 처리가 필요없다면 iterable collection(Set, List 등)에서 바로 쓰는 것도 가능

## Parallel Stream

### Stream을 병렬로

```java
List<Integer> numbers = Arrays.asList(1, 2, 3);
Stream<Integer> parallelStream = numbers.parallelStream();
Stream<Integer> parallelStream2 = numbers.stream().parallel();
```

- Sequential vs. Parallel
- 여러개의 스레드를 이용하여 stream의 처리 과정을 병렬화 (parallelize)
- 중간 과정은 병렬 처리 되지만 순서가 있는 Stream의 경우 종결 처리 했을 때의 결과물이 기존의 순차적 처리와 일치하도록 종결 처리과정에서 조정됨
    - 즉 List로 collect한다면 순서가 항상 올바르게 나온다는 것

### 장단점

- 장점 :
    - 굉장히 간단하게 병렬 처리를 사용할 수 있게 해줌
    - 속도가 비약적으로 빨라질 수 있음
- 단점 :
    - 항상 속도가 빨라지는 것은 아님
    - 공통으로 사용하는 리소스가 있을 경우 잘못된 결과가 나오거나 아예 오류가 날 수도 있음(deadlock)
    - 이를 막기 위해 mutex, semaphore등 병렬 처리 기술을 이용하면 순차 처리보다 느려질 수도 있음

### Summary

- Stream의 다양한 종결 처리들
    - max / min / count / allMatch / anyMatch / findFirst / findAny / reduce / forEach
- Collector를 이용한 종결 처리들
    - toList / toSet / mapping / reducing / toMap / groupingBy / partitioningBy
- Parallel Stream의 장점과 단점