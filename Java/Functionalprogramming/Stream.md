## What is Stream?

- 데이터의 흐름
- 컬렉션(Collection) 형태로 구성된 데이터를 람다를 이용해 간결하고 직관적으로 프로세스 가능케 함
- For, While 등을 이용하던 기존 loop를 대체
- 손쉽게 병렬 처리를 할 수 있게 해줌
```java
Stream<String> stream = Stream.of("Chris", "Brendon", "Nick");
List<String> names = stream.collect(Collectors.toList());
System.out.println(names);

String[] city = new String[]{"Seoul", "Daejeon", "Busan"};
Stream<String> cityStream = Arrays.stream(city);
List<String> cityList = cityStream.collect(Collectors.toList());
System.out.println(cityList);

Set<Integer> integerSet = new HashSet<>(Arrays.asList(3, 5, 7));
Stream<Integer> integerStream = integerSet.stream();
List<Integer> integerList = integerStream.collect(Collectors.toList());
System.out.println(integerList);
```

## Filter

- 만족하는 데이터만 걸러내는데 사용
- Predicate에 true를 반환하는 데이터만 존재하는 stream을 리턴

```java
Stream<T> filter(Predicate<? super T> predicate);
```

```java
List<Integer> newFilteredIntegerStream = Stream.of(3, -5, 8, 15, -20)
        .filter(x -> x > 0)
        .collect(Collectors.toList());
System.out.println(newFilteredIntegerStream);
```

```java
User user1 = new User()
        .setId(101)
        .setName("Chris")
        .setVerified(true)
        .setEmailAddress("chris@gmail.com");
User user2 = new User()
        .setId(102)
        .setName("Nick")
        .setVerified(false)
        .setEmailAddress("nick@gmail.com");
User user3 = new User()
        .setId(103)
        .setName("Charlie")
        .setVerified(false)
        .setEmailAddress("charlie@gmail.com");

List<User> users = Arrays.asList(user1, user2, user3);
List<User> verifiedUser = users.stream()
        .filter(User::isVerified)
        .collect(Collectors.toList());
System.out.println(verifiedUser);

List<User> unVerifiedUser = users.stream()
        .filter(user -> !user.isVerified())
        .collect(Collectors.toList());
System.out.println(unVerifiedUser);
```
## Map

- 데이터를 변형하는데 사용
- 데이터에 해당 함수가 적용된 결과물을 제공하는 stream을 리턴

```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```

- T라는 타입의 Stream에서 호출했을 때, T나 T의 super 타입의 input 파라미터를 받아 R이나 R의 하위 타입의 오브젝트를 리턴하는 Function을 파라미터로 받는다.
- 쉽게 말해, T 라는 데이터가 흘러나오는 Stream에서 데이터 마다 Function을 적용해서 R이라는 타입으로 바꿔주고 이 결과물을 흘려보내는 Stream을 리턴하는 것이 Map임
- **예제 1**
    
    ```java
    List<Integer> integerList = Arrays.asList(3, 7, -5);
    Stream<Integer> integerStream = integerList.stream();
    Stream<Integer> integerStreamX2 = integerStream.map(x -> x * 2);
    List<Integer> integerListX2 = integerStreamX2.collect(Collectors.toLis());
    System.out.println(integerListX2);
    
    Stream<Integer> integerStream = integerList.stream();
    Stream<String> stringStream = integerStream.map(x -> "Number is " + x);
    List<String> strings = stringStream.collect(Collectors.toList());
    System.out.println(strings);
    
    =========refactor=========
    
    List<Integer> integerList = Arrays.asList(3, 7, -5);
    List<Integer> integerListX2 = integerList.stream()
            .map(x -> x * 2)
            .collect(Collectors.toList());
    System.out.println(integerListX2);
    
    Stream<Integer> integerStream = integerList.stream();
    List<String> stringList = integerStream
            .map(x -> "Number is " + x)
            .collect(Collectors.toList());
    System.out.println(stringList);
    ```
    
- **예제 2**
    
    ```java
    User user1 = new User()
            .setId(101)
            .setName("Chris")
            .setVerified(true)
            .setEmailAddress("chris@gmail.com");
    User user2 = new User()
            .setId(102)
            .setName("Nick")
            .setVerified(false)
            .setEmailAddress("nick@gmail.com");
    User user3 = new User()
            .setId(103)
            .setName("Charlie")
            .setVerified(false)
            .setEmailAddress("charlie@gmail.com");
            
    List<User> users = Arrays.asList(user1, user2, user3);
    List<String> emailAddresses = users.stream()
            .map(User::getEmailAddress)
            .collect(Collectors.toList());
    System.out.println(emailAddresses);
    ```
    
- **예제 3**
    
    ```java
    Order order1 = new Order()
          .setId(1001L)
          .setStatus(CREATED)
          .setCreatedByUserId(101L);
    Order order2 = new Order()
          .setId(1002L)
          .setStatus(ERROR)
          .setCreatedByUserId(103L);
    Order order3 = new Order()
          .setId(1003L)
          .setStatus(PROCESSED)
          .setCreatedByUserId(104L);
    Order order4 = new Order()
          .setId(1004L)
          .setStatus(ERROR)
          .setCreatedByUserId(102L);
    Order order5 = new Order()
          .setId(1005L)
          .setStatus(IN_PROGRESS)
          .setCreatedByUserId(101L);
    List<Order> orders = Arrays.asList(order1,  order2, order3, order4, order5);

    // TODO: Create list of createdByUserId
    List<Long> stringStream = orders.stream()
          .map(Order::getCreatedByUserId)
          .collect(Collectors.toList());
    System.out.println(stringStream);
    ```

## Stream의 구성요소

![image](https://user-images.githubusercontent.com/77063888/199261788-a08a614e-9b7c-4363-aee2-a6fe89a14c39.png)


- 여러가지의 중간 처리를 이어붙이는 것이 가능

- **Filter와 Map 조합**
    
    ```java
    List<String> emails = new ArrayList<>();
        for (User user : users) {
            if (!user.isVerified()) {
                String email = user.getEmailAddress();
                emails.add(email);
            }
        }
    System.out.println(emails);

    ==================refactor====================

    List<String> emailList = users.stream()
            .filter(user -> !user.isVerified())
            .map(User::getEmailAddress)
            .collect(Collectors.toList());
    System.out.println(emailList);
    ```
    
    ```java
    LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    Order order1 = new Order()
            .setId(1001L)
            .setStatus(CREATED)
            .setCreatedByUserId(101L)
            .setCreatedAt(now.minusHours(4));
    Order order2 = new Order()
            .setId(1002L)
            .setStatus(ERROR)
            .setCreatedByUserId(103L)
            .setCreatedAt(now.minusHours(40));
    Order order3 = new Order()
            .setId(1003L)
            .setStatus(PROCESSED)
            .setCreatedByUserId(104L)
            .setCreatedAt(now.minusHours(36));
    Order order4 = new Order()
            .setId(1004L)
            .setStatus(ERROR)
            .setCreatedByUserId(102L)
            .setCreatedAt(now.minusHours(15));
    Order order5 = new Order()
            .setId(1005L)
            .setStatus(IN_PROGRESS)
            .setCreatedByUserId(101L)
            .setCreatedAt(now.minusHours(10));
    List<Order> orders = Arrays.asList(order1,  order2, order3, order4, order5);
    
    // TODO : Find orders in Error status, and extract createdByUserIds as a list
    List<Long> userIds = orders.stream()
            .filter(order -> order.getStatus().equals(ERROR))
            .map(Order::getCreatedByUserId)
            .collect(Collectors.toList());
    System.out.println(userIds);
    
    // TODO : Find orders in Error status and created within 24 hours
    List<Order> findOrdersInErrorStatusIn24hrs = orders.stream()
            .filter(order -> order.getStatus().equals(ERROR))
            .filter(order -> order.getCreatedAt().isAfter(now.minusHours(24)))
            .collect(Collectors.toList());
    System.out.println(findOrdersInErrorStatusIn24hrs);
    ```

## Sorted

- 데이터가 순서대로 정렬된 stream을 리턴
- 데이터의 종류에 따라 Comparator가 필요할 수 있음

```java
Stream<T> sorted();
Stream<T> sorted(Comparator<? super T> comparator);
```

## Distinct

- 중복되는 데이터가 제거된 stream을 리턴

```java
Stream<T> distinct();
```

## FlatMap

- Map + Flatten
- 데이터에 함수를 적용한 후 중첩된 stream을 연결하여 하나의 stream으로 리턴

```java
<R> Stream<R> flatMap(
Function<? super T, ? extends Stream<? extends R>> mapper);
```

### Summary

---

- 스트림은 데이터의 흐름
- 여러 개의 중간 처리를 연결할 수 있음
    - Filter
    - Map
    - Sorted
    - Distinct
    - FlatMap