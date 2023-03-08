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