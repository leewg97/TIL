### 날짜 및 요일 계산

`datetime`

- 날짜와 시간 데이터를 처리하는 파이썬 내장 라이브러리
- 현재 시간에 대한 정보를 가져와, 원하는 형식으로 지정하여 사용할 수 있다.
- `timedelta` 객체를 활용해서 날짜 및 시간 연산이 가능하게 한다.

### 날짜 표현 - datetime.date

```python
import datetime
day1 = datetime.date(2023, 11, 11)
```

### 날짜 및 시간 표현 - datetime.datetime

```python
day2 = datetime.datetime(2023, 11, 11, 12, 12, 12)
day2.year # 연도
day2.month # 월
day2.day # 일
day2.hour # 시간
day2.minute # 분
day2.second # 초
```

### 날짜/시간 합치기 - combine

```python
day = datetime.date(2023, 11, 11)
time = datetime.time(12, 12, 12)

dt = datetime.datetime.combine(day, time)
dt -> datetime.datetime(2023, 11, 11, 12, 12, 12)
```

### 현재 날짜/시간 - today, now

```python
datetime.date.today()
datetime.datetime.now()
```

### 날짜 계산 - timedelta

```python
day1 = datetime.date(2023, 10, 15)
day2 = datetime.date(2023, 11, 10)

# 날짜 뺄셈
diff = day2 - day1
diff -> datetime.timedelta(days=26)

# 날짜 덧셈
plus = datetime.timedelta(days=100)
add = day1 + plus
add -> datetime.date(2024, 1, 23)
```

### 요일 판별 - weekday

- 월요일(0), 화요일(1), 수요일(2), 목요일(3), 금요일(4), 토요일(5), 일요일(6)

```python
day1 = datetime.date(2023, 10, 15)
day2 = datetime.date(2023, 11, 10)

day1.weekday() -> 6
day2.weekday() -> 4
```