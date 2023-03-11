## 이터레이터 (Iterator)

- 집합에서 값을 **차례대로 꺼낼 수 있는 `객체(Object)`** 를 말한다.
- for 문을 순회할 수 있는 객체
- 사용이유 ?
    - 숫자가 아주 많을 경우 미리 만들어 놓는 것 보다 <br>
    그때 그때 필요할 때 값을 뽑아 사용하고 싶을 경우가 대부분의 상황에서 효율적 (메모리 등)
- **반복 가능 (Iterable) 객체**에만 사용 가능
    - iter() 로 반복 가능 객체 변환
    - next() 로 다음값 뽑기
- 한번 반복하면 다시 사용될 수 없다.

## 제네레이터 (Generator)

- **이터레이터 (Iterator) 를 생성해주는 함수**
- 사용이유 ?
    - 함수가 하나의 고정된 값을 반환하는 것이 아닌 순차적으로 다른 값을 반환하기 원할 때.
    - 원하는 시점에 반환할 수 있다.
- `yield()` 를 통해 Iterator를 생성해준다.

```python
def client_count(total_client):
    n = 1
    for num in range(total_client):
        print(f'{n} 번째 고객님 입장하세요')
        n += 1
        yield

if __name__ == "__main__":
    mygen = client_count(100)

    next(mygen)
    next(mygen)
    next(mygen)
```
