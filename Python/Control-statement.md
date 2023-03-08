### For 문

```python
for 변수 in 시퀀스 자료:
	명령문
for a in [1,2,3,4]:
	print(a)
```

### Range

```python
range(10) # 0~9까지 숫자를 포함하는 range 객체를 만들어준다.

# - range 객체 사용
# range(10) -> 0~9까지 숫자를 포함하는 range 객체가 나온다. 0,1,2,3,4,5,6,7,8,9
# range(시작, 끝+1, 단계)
for i in range(1, 10, 2):
    print(i)
==> 1 3 5 7 9 출력
```

### While

```python
초기식
while 조건식:
	반복할 명령
	증감

i = 0
while i < 10:
	print(i, "번째 다짐")
	i += 1

while True:
	반복할 명령
	if 조건식:
		break

while True:
	x = input("종료하려면 exit을 입력하세요>>>")
	if x == "exit":
		break
```