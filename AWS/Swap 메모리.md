# Swap 메모리

## Swap 메모리?
- Swap 메모리란 실제 메모리가 가득 찼지만 더 많은 메모리가 필요할 때 디스크 공간을 이용하여 부족한 메모리를 대체할 수 있는 공간
- 즉, 가상 메모리라고 할 수 있음
- 실제 메모리가 아닌 하드디스크를 이용하는 것이기 때문에 속도가 굉장히 떨어짐

## EC2 Swap 메모리
- free tier로 EC2를 사용했는데, t2.micro의 RAM이 1GB밖에 되지 않아서 Spring을 띄우기에는 메모리가 부족함
- 이때, Swap 메모리 설정을 통해 임시로 메모리를 매꿀 수 있음

## Swap 메모리 생성방법
1. Swap File 생성
    ```
    sudo dd if=/dev/zero of=/swapfile bs=128M count=16
    ```
    - dd명령을 사용하여 루트 파일 시스템에 swap 파일을 생성
    - dd는 블록 크기, count는 블록 수
    - 지정한 블록 크키는 인스턴스의 가용 메모리보다 작아야함
    - 왜? -> 그렇지 않으면 memory exhauted 발샘
    - 위 코드는 2GB

2. Swap File에 대한 권한 업데이트
    ```
    sudo chmod 600 /swapfile
    ```

3. Linux swap 영역 설정
    ```
    sudo mkswap /swapfile
    ```

4. swap 공간에 swapfile추가하여 즉사 사용 가능케 하기
    ```
    sudo swapon /swapfile
    ```

5. 과정 성공 확인
   ```
   sudo swapon -s
   ```

6. /etc/fstab 파일을 편집하여 부팅 시 swapfile 활성화
    ```
    파일 열기
    sudo vi /etc/fstab
    파일 최하단에 다음을 추가하고 :wq로 저장 및 종료
    /swapfile swap swap defaults 0 0
    ```

7. `free`로 메모리 확인

## Swap 메모리 비활성화

- `swapoff` 명령어를 사용하여 Swap 메모리를 비활성화 가능
    ```
    단일 Swap 메모리 off
    sudo swapon swapfile
    모든 Swap 메모리 off
    swapon -a
    ```

## Swap 메모리 삭제

- `rm -r`을 통해 Swap 메모리 삭제 가능
    ```
    sudo rm -r swapfile
    ```