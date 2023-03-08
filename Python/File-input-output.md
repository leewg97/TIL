## 파일 입출력
### 파일 입출력을 사용하는 이유

- 파일로부터 데이터를 읽어와서 프로그램에 사용하기 위해
- 프로그램에서 만든 데이터를 파일형태로 저장하기 위해

### 파일 열기 모드

- w : 쓰기모드(write)
    - 쉽게말해 덮어쓰기
- a : 추가모드(append)
    - 쉽게말해 이어쓰기
- r : 읽기모드(read)

### 순서

- 파일 열기 → 파일 작업 → 파일 닫기

### 파일 쓰기

```python
파일객체 = open("파일이름", "파일모드)
파일객체.write(데이터)
파일객체.close()

file = open("data.txt", "w")
file.write("1. Python Study)
file.close()
```

### 파일 추가

```python
파일객체 = open("파일이름", "파일모드)
파일객체.write(데이터)
파일객체.close()

file = open("data.txt", "a")
file.write("2. Python Study Start)
file.close()
```

### 파일 읽기

```python
파일객체 = open("파일이름", "파일모드)
변수 = 파일객체.read()
파일객체.close()

file = open("data.txt", "r")
data = file.read() # data.txt 데이터 전체 가져옴
file.close()
```

### pickle 모듈

- 파일에 파이썬 객체 저장하기위해 사용하는 모듈

```python
import pickle
data = {
    "목표1" : "매일 팔굽혀 펴기 100회",
    "목표2" : "매일 코딩 공부 1시간"
}
file = open("data.pickle", "wb")# wb -> 바이너리보드 (컴퓨터가 바로 읽을 수 있는 데이터 형식)
pickle.dump(data, file) 
file.close()
```

- 파일로부터 파이썬 객체 읽기

```python
import pickle
file = open("data.pickle", "rb")
data = pickle.load(file)
print(data)
file.close()
```

### with 구문

- with 구문 X

```python
file = open("data.txt", "r")
data = file.read()
file.close()
```

- wirh 구문 O

```python
with open("data.txt", "r") as file:
		data = file.read()
```

- 사용하는 이유는 `file.close()` 를 매번 신경쓰기가 번거롭기 때문에 `with ~~ as file` 로 `file.close()` 가 항상 실행되게 하는 것

### csv 란?

- csv(comma-separated-values) : 데이터가 콤마로 구분된 텍스트 파일 형식

### csv 파일 입출력 사용방법

- csv 파일 쓰기

```python
import csv

data = [
    ["이름", "반", "번호"],
    ["재석", 1, 20],
    ["홍철", 3, 8],
    ["형돈", 5, 32]
]

file = open("student.csv", "w")
writer = csv.writer(file)
for d in data:
		writer.writerow(d)
file.close()
```

- csv 파일 읽기

```python
import csv

file = open("student.csv", "r")
reader = csv.reader(file)
for d in reader:
		print(d)
file.close()
```