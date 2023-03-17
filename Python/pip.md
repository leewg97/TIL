## pip

- 설치
  - `pip install 패키지명`
- 삭제
  - `pip uninstall 패키지명`
- 특정 버전으로 설치
  - `pip install 패키지명==버전`
- 패키지 버전 업그레이드
  - `pip install --upgrade 패키지명`
- 설치된 패키지 확인
  - `pip list`
- 패키지 리스트 파일로 저장
  - `pip list --format=freeze > requirements.txt`
- 패키지 리스트 설치
  - `pip install -r requirements.txt`