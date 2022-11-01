## Method Reference

- 기존에 이미 선언되어있는 메서드를 지정하고 싶을 때
- `::` 오퍼레이터 사용
- 생략이 많기 때문에 사용할 메서드의 매개변수의 타입과 리턴 탕비을 미리 숙지해야 함

### Method Reference의 4가지 케이스

---

- `ClassName::staticMethodName` : 클래스의 static method를 지정할 때
- `objectName::instanceMethodName` : 선언 된 객체의 instance method를 지정할 때
- `ClassName::instanceMethodName` : 객체의 instance method를 지정할 때
- `ClassName::new` : 클래스의 constructor를 지정할 때

### ClassName::staticMethodName

---

- 클래스의 static method(정적 메서드)를 지정할

```java
Function<String, Integer> str2int = Integer::parseInt;
System.out.println(str2int.apply("20"));
```

### objectName::instanceMethodName

---

- 이미 선언되어있는 객체의 instance method를 지정할 때

```java
String str = "hello";
Predicate<String> equalsToHello = str::equals;
boolean helloEqualsWorld = equalsHello.test("world");
```

### ClassName::instanceMethodName

---

- 해당 클래스의 인스턴스를 매개변수(parameter)로 넘겨 메서드를 실행해주는 함수

```java
Function<String, Integer> strLength = String::length;
int length = strLength.apply("Hello world!");
BiPredicate<String, String> strEquals = String::equals;
boolean result = strEquals.test("hello", "world");
```

### ClassName::new

---

```java
BiFunction<Integer, String, User> createUser = User::new;
User brendon = createUser.apply(7, "brendon");
System.out.println(brendon);
```

### 2차원 배열 예제

---

```java
/*
BiFunction<String, String, Car> => 자동차 constructor는 String 2개를 받음
*/

Map<String, BiFunction<String, String, Car>> carTypeToConstructorMap = new HashMap<>();
carTypeToConstructorMap.put("sedan", Sedan::new);
carTypeToConstructorMap.put("suv", Suv::new);
carTypeToConstructorMap.put("van", Van::new);

String[][] inputs = new String[][] {
        {"sedan", "G90", "Genesis"},
        {"van", "splinter", "Benz"},
        {"suv", "X7", "BMW"}
};

List<Car> cars = new ArrayList<>();
for (String[] input : inputs) {
    String carType = input[0];
    String carName = input[1];
    String carBrand = input[2];

    cars.add(carTypeToConstructorMap.get(carType).apply(carName, carBrand));
}

for (Car car : cars) {
    car.drive();
}
```

- `Car` 라는 추상클래스를 상속받는 `Sedan, Van, Suv` 클래스가 있다.
- 자동차의 Type을 받아 자동으로 생성자를 꺼내주는 Map<>을 만든다.
- for문으로 2차원 배열을 돌면서 Type을 체크하고 나머지 두 개로 construntor를 바로 호출해주면 된

### Summary

---

- 기존에선언되어있는메서드를지정
- Method Reference
    - `ClassName::StaticMethodName`
    - `objectName::instanceMethodName`
    - `ClassName::instanceMethodName`
- Constructor Reference
    - `ClassName::new`