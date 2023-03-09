## Error & Exception

### try-except 구문
```python
try:
		예외가 발생할 수 있는 코드
except:
		예외가 발생하면 실행할 코드
else:
		예외가 발생하지 않은 경우 실행할 코드
finally:
		항상 실행할 코드
```

```python
try:                    # 예외가 발생할 수 있는 코드 작성
    print(코드 작성)
except ValueError as e: # 예외가 발생했을 때 실행되는 코드
    print("예외가 발생했습니다.", e)
except ZeroDivisionError as e:
    print("예외가 발생했습니다.", e)
else:
    print("예외가 발생하지 않았을 때 실행되는 코드")
finally:                # 파일 닫기와 같이 리소스 반환을 해야할 때 사용
    print("예외 발생 유무와 상관없이 항상 실행되는 코드")
```

### raise 구문
```python
raise 예외("에러메세지")

raise Exception
raise Exception("Error Message")
```

### 예외 계층 구조
```
BaseException
    +-- SystemExit
    +-- KeyboardInterrupt
    +-- GeneratorExit
    +-- Exception
        +-- StopIteration
        +-- StopAsyncIteration
        +-- ArithmeticError
        |   +-- FloatingPointError
        |   +-- OverflowError
        |   +-- ZeroDivisionError
        +-- AssertionError
        +-- AttributeError
        +-- BufferError
        +-- EOFError
        +-- ImportError
        |   +-- ModuleNotFoundError
        +-- LookupError
        |   +-- IndexError
        |   KeyError
```

### 에러 만들기

```python
class 예외(Exception):
	def __init__(self):
		super().__init__("error message")
```