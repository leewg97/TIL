## Shorten url

주로 모바일 등으로 URL을 공유하고자 할 때, 긴 URL을 줄여서 제공

- 요청 URL에 대응하는 Unique한 식별자가 필요
- 1. DB에 길안내 정보(위, 경도와 약국정보)를 저장하고, pk를 사용하는방법
    - ex) http://localhost:8080/dir/1000000000..
    - 10진수 보다 더 간결한 64진수 또는 62진수
- 2. base64 사용
    - “=” 등은 URL 예약어 이기 때문에 부적절
- 3. base62 사용
    - ex) http://localhost:8080/dir/raad21