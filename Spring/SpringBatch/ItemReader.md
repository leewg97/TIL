## FlatFileItemReader

* 파일을 읽도록 해주는 ItemReader
* Spring Batch에서 제공하는 FlatFileItemReader를 사용하면 chunk 기반으로 item들을 읽어낼 수 있다

### `FlatFileItemReader` Properties

* `comments` : 주석 행을 나타내는 줄 접두사를 지정
* `encoding` : 사용할 텍스트 인코딩을 지정. 기본값은 `Charset.defaultCharset()` 값
* `lineMapper` : String을 Object로 변환할 때 사용하는 설정
* `linesToSkip` : 보통 파일 첫 번째 줄에 행의 헤더같은 것이 들어갈 때 줄을 건너뛸 수 있는 속성
* `recordSeparatorPolicy` : 기본은 파일의 라인이 나뉘어져 있는 것으로 구분하는 것이지만 다른 것으로 설정이 되어있을 때 작성하여 설정해주면 된다.
* `resource` : 파일이 존재하는 리소스 설정
* `skippedLinesCallback` : 건너 뛸 라인의 원래 내용을 전달하는 인터페이스. `lineToSkip`이 2면 이 인터페이스를 두 번 호출.
* `strict` : 입력 리소스가 없는 경우 ExecutionContext에 예외를 적용

