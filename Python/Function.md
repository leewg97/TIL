## 함수

### 함수를 사용하는 이유

- 재사용성이 좋아짐
- 유지보수가 편해짐
- 가독성이 좋아짐

### 정의하기

```python
def 함수이름():
	명령블록

호출하기
함수이름()

def printhello():
	print("hello")

printhello()
```

### 매개변수가 있는 함수

```python
정의하기
def 함수이름(매개변수1, 매개변수2):
	명령블록
호출하기
함수이름(인자1, 인자2)

def sum(a,b):
	print(a+b)
sum(4,5)
```

### 반환값이 있는 함수

```python
정의하기
def 함수이름():
	명령블록
	return 반환값
호출하기
함수이름()

def getRandomNum():
	number = random.radint(1,10)
	return number
getRandomNum()
```

### 매개변수와 반환값이 있는 함수

```python
def 함수이름(매개변수1, 매개변수2)
	명령블록
	return 반환값
호출하기
함수이름(인자1, 인자2)

def sum(a,b):
	result = a+b
	return result
sum(3,5) =>> 8
```

### 문자열 포매팅

- 문자열을 만들 때, 형식을 지정해준다는 뜻

```python
# 문자열 포매팅
def printSumAvg(x, y, z):
    """
    세개의 숫자를 받아 합계와 평균을 출력하는 함수
    """
    sum = x + y + z
    avg = sum / 3
    print(f"합계 : {sum},  평균 : {avg}")

printSumAvg(10, 20, 30)
```
---
## 클로저와 데코레이터

### 클로저 (Closure)

- 함수 안의 함수를 결과로 반환할 때, 그 **내부 함수를 `클로저(Closure)`** 라고 한다.
- 사용되는 곳
    - 콜백(Callback) 함수에 사용
    - 함수의 순차적 실행
    - 데코레이터 함수

### 데코레이터 (Decorator)

- `decorate`는 '꾸미다, 장식하다'라는 뜻으로 **함수를 꾸며주는 함수**.
- **함수를 인수로 받는 클로저**
- @를 이용한 어노테이션으로 사용
- 사용되는 곳
    - 반복되는 작업을 여러 함수에 적용할 경우
    - 기존 함수를 수정하지 않고 추가 기능을 구현하고 싶을 경우