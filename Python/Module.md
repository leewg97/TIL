## Module
### 모듈을 사용하는 이유

- 프로그램 기능별로 파일을 나누어서 유지보수등의 관리를 편하게 하는데 있다.

### 모듈의 개념

- 한개의 완성된 프로그램 파일

### 파이썬 기본 모듈 사용방법

```python
import 모듈이름
모듈이름.변수
모듈이름.함수()

import math
print(math.pi)
print(math.ceil(5.7)) => ceil은 올림 함수
# 위에가 귀찮을때
from math import pi, ceil
print(pi)
print(ceil(5.7))
```

### 파이썬 외부 모듈 사용방법

```python
pip install 모듈이름     pip install pyautougui
```