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

### 윤년 판별 함수

```python
def isLeapYear(year): # 윤년이면 True, 아니면 False 를 출력하는 함수.
    return year % 4 == 0 and year % 100 != 0 or year % 400 == 0

isLeapYear(2022) # False
isLeapYear(2024) # True
```

### 달력 관련 라이브러리 - calendar

```python
import calendar

# 윤년 확인
calendar.isleap(2022) # False
calendar.isleap(2020) # True

# 윤년 횟수
calendar.leapdays(1990, 2023) # 8

# 요일 반환
calendar.weekday(2023, 3, 15) # 2

# 달력 출력
print(calendar.calendar(2023))
```

### 날짜 출력

`strptime, strftime`

- `strptime` - 날짜 형식 문자열을 datetime 객체로 변환
- `strftime` - 날짜와 시간(datetime) 을 문자열로 출력

### strptime

- 연(%Y), 월(%m), 일(%d), 시(%H), 분(%M), 초(%S)
- [호환 가능 포멧 공식문서](https://docs.python.org/ko/3/library/datetime.html#strftime-strptime-behavior)

```python
import datetime

str_datetime = '2021-04-08 21:31:48'  # 날짜 형식 문자열
currdate = datetime.datetime.strptime(str_datetime, '%Y-%m-%d %H:%M:%S')

type(currdate) # datetime.datetime
currdate -> # datetime.datetime(2021, 4, 8, 21, 31, 48)
```

### strftime

```python
import datetime
now = datetime.datetime.now()

now # datetime.datetime(2023, 3, 22, 2, 20, 39, 139269)

date = now.strftime('%Y-%m-%d')
type(date) # str
date # '2023-03-22'

time = now.strftime('%H:%M:%S')
type(time) # str
time '02:20:39'

datetime = now.strftime('%Y-%m-%d %H:%M:%S')
type(datetime) # str
datetime # '2023-03-22 02:20:39'
```

### time

- `datetime` 라이브러리와 같이 파이썬에서 시간과 날짜를 다루기 위한 내장 라이브러리
- 프로그램 실행 경과 시간, 프로그램 대기 시간 등을 만들 때 주로 사용

```python
import time

# 현재 시간 출력(실수형)
time.time() # 1679419698.20942

# 현재 시간 출력(문자형)
time.time() # Web Mar 22 02:28:46 2023

# 대기 시간 생성
print('바로 출력되는 구문')
time.sleep(3)
print('3초후 출력되는 구문')
```

```python
# 경과 시간 출력
start_time = time.time()

for i in range(5):
    time.sleep(1) # 1초간 대기
    print('반복 횟수', i+1)
    
end_time = time.time()
elapsed_time = end_time - start_time

print('경과 시간은 : {} 초 입니다 !'.format(elapsed_time))
```