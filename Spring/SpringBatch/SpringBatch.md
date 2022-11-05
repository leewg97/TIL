## Spring Batch

- Spring Batch는 가볍고 포괄적인 배치 프레임워크
- 로깅, 추적, 트랜잭션관리, 작업처리통계, 재시작, 건너뛰기, 리소스 관리 등 대량의 레코드 처리에 필수적인 재사용 가능한 기능을 제공
- 멀티 코어 또는 멀티 서버에서 처리를 분산하는 기능을 제공
- 최적화, 파티셔닝 기술로 대용량 고성능 배치 작업 가능
- 확장성이 매우 뛰어남

- 스프링으로 작성된 코드를 재활용할 수 있다 ⇒ 기존 Spring 프로젝트의
코드를 활용하거나 모듈을 이용할 수 있다
- 만약 배치용 코드를 새로 작성한다면 Python, Shell script, Go 등 다른
언어로 비슷한 처리를 새로 구현해야
- 배치 처리를 위한 로직을 새로 만드는 것보다 스프링 배치에서 제공하는
기능을 이용하는게 생산성 있는 방법

### Spring Batch 도메인 언어

- JobLauncher는 Job을 실행시키는 컴포넌트
- Job은 배치 작업. JobRepository는 Job 실행과 Job, Step을 저장
- Step은 배치 작업의 단계. ItemReader, ItemProcessor, ItemWriter는 데이터를 읽고, 가공(처리)하고 쓰는 구성

### Spring Batch 아키텍처

- Spring Batch가 제공하는 Core와 Infrastructure를 활용해 Application을 구현한다

**Application Layer**

- 사용자(=우리) 코드와 구성
- 비즈니스, 서비스 로직
- Core, Infrastructure를 이용해 배치의 기능을 만듬

**Core Layer**

- 배치 작업을 시작하고 제어하는데 필수적 클래스
- Job, Step, JobLauncher

**Infrastructure Layer**

- 외부와 상호작용
- ItemReader, ItemProcessor, ItemWriter