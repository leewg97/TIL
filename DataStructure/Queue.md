
## Queue
-   선입선출(First-In-Fast-Out : FIFO)
-   순서를 보장하는 프로그램
    -   ex) 사람이 몰린 이벤트 프로그램
-   `push()`, `offer()`, `add()` → data를 넣는 작업
    -   `pop()`, `poll()` → data를 빼는 작업
-   `peek()` → data를 확인하는 작업
-   enqueue : 데이터가 입력되는 동작
-   dequeue : 데이터가 빠지는 동작 → size찍어보면 dequeue 할 때마다 하나씩 줄어듬
-   제일 뒤 : rear 앞 : front

### Queue구현 방법
1.  LinkedList를 이용한 구현
2.  배열을 이용한 원형 큐 구현 : 배열로 구현하되 배열 구현의 단점 보완한 구조
       - 배열을 이용한 선형 큐 구현은 비효율적
3.  원현 큐 : 원형 큐에서는 한칸의 DUMMY공간을 주어서 꽉찬 상태와 비어있는 상태를 구분할 수 있음. front와 rear의 위치가 동일하면 비어있는 것임. front 위치 = rear 위치 +1 ⇒ 꽉찬상태
    - 고정된 크기의 배열로 구현
    - 원형 큐에서 인덱스로 접근할 때 인덱스 값의 큐 사이즈를 모듈로 연산 한 값으로 접근
    - isFull() , isEmpty()