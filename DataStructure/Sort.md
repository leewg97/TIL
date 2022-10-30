# 정렬(Sort)

-   안정 정렬 vs 불안정 정렬 : 중복된 값의 순서 보장 여부
    -   순서가 보장 되면 안정정렬(STABLE)
    -   순서가 보장 되지 않으면 불안정 정렬(UNSTABLE)
-   In-place 정렬 vs Out-of-place 정렬
    -   원본 데이터 내 정렬 : In-place 정렬
    -   원본 데이터가 아닌 새로운 배열로 정렬 : Out-of-place 정렬

1.  **버블 정렬(Bubble Sort)**
    1.  인접한 두 elements를 비교하고 정렬되어 있지 않다면 두 elements의 위치를 바꿔줌
    2.  정렬이 완료된 elements를 제외하고 반복 수행
    3.  (n-1)+(n-2)+(n-3)+...+2+1=n(n-1)/2
    4.  시간 복잡도O(N^2)
    5.  단순하고 직관적, 생김새는 거품 같다고 하여 버블

<br>

2.  **삽입 정렬(Insertion Sort)**
    1.  리스트의 앞에서부터 이미 정렬된 서브 리스트의 값들과 비교를 통해 자신의 위치에 값을 삽입
    2.  서브 리스트는 정렬이 되어 있으니 삽입 되어야 할 위치가 정해져 있을 것
    3.  사이즈가 1인 배열이 있다면 여기서는 어떤 값이 들어 있어도 정렬 되어 있는 것 → 여기에서부터 출발하게 됨
    4.  리스트에서 가장 앞에있는 하나의 원소를 이미 정렬된 서브 리스트로 보고 정렬 시작
    5.  실질적으로 리스트에 두 번째 값부터 정렬 시작
    6.  안정 정렬이며 단순하다
    7.  데이터의 이동이 많음. But, 리스트 내의 데이터가 어느정도 정렬되어 있으면 이동 횟수 적어짐
    8.  시간 복잡도O(n^2)/최선O(n)/최악O(n^2)

<br>

3.  **합병 정렬(Merge Sort)**
    
    1.  하나의 리스트를 두 개의 균등한 크기로 분할하고 분할된 부분 리스트를 정렬한 다음, 두 개의 정렬된 부분 리스트를 합하여 전체가 정렬된 리스트가 되게 하는 방법
    2.  합병 정렬은 다음의 단계들로 이루어진다.
    
    -   분할(Divide): 입력 배열을 같은 크기의 2개의 부분 배열로 분할한다.
    -   정복(Conquer): 부분 배열을 정렬한다. 부분 배열의 크기가 충분히 작지 않으면 순환 호출 을 이용하여 다시 분할 정복 방법을 적용한다.
    -   결합(Combine): 정렬된 부분 배열들을 하나의 배열에 합병한다.
    
    1.  보통 재귀함수를 통해 구현
    2.  시간 복잡도O(NlogN)

<br>

4.  **퀵 정렬(Quick Sort)**
    1.  하드웨어 특성 때문에 더 빠름 : 참조 지역성 원리
    2.  알고리즘 특성 상 동일한 배열 내에서 자리를 이동 시킴 → 인접한 데이터들 사이의 이동이 발생함. ⇒ 제일 처음 배열에 접근 할 때만 실제 메모리에서 가져오고 이후에는 캐시로 접근.
    3.  한번 결정된 pivot 값은 이후의 연산에서 제외 : 분할이 될 수록 계산해야 할 데이터의 수가 줄어듦 → 정렬 속도 줄여줌
    4.  추가 메모리 공간 필요없음
    5.  Divide and conquer
    6.  평균 O(NlogN) / 최악 O(N^2) : pivot값이 리스트의 최소값이나 최대값이면 효율을 낼 수 없음
    7.  pivot값을 고를 때 후보를 두고 가장 중간 값을 사용하는 알고리즘을 사용하기도 함  
        → Median of Three

프로그램은 CPU에 의해 실행 → 실행에 필요한 데이터는 메모리에 있음 → CPU 메모리에 접근하는데 그 전에 캐시공간 먼저 탐색 → 데이터가 있다면 캐시공간에서 데이터를 가져옴 → 아니면 메모리로

-   보조배열 : 메인메모리에 생성 됨
-   퀵 정렬 : 제일 처음 제외 모두 캐시공간

**QUICK SORT**

1.  pivot값을 정하는 것 부터 시작(가장 앞, 뒤 원소 상관없음)
2.  pivot값 기준 원소 재배치 → 왼쪽은 pivot보다 작은 값, 우측은 pivot보다 큰 값
3.  각각 서브리스트가 생성 → pivot값 정해주기 시작
4.  이후 다시 원소 재배치 이루어짐