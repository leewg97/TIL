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

`Application Layer`

  - 사용자(=우리) 코드와 구성
  - 비즈니스, 서비스 로직
  - Core, Infrastructure를 이용해 배치의 기능을 만듬

 `Core Layer`

  - 배치 작업을 시작하고 제어하는데 필수적 클래스
  - Job, Step, JobLauncher

`Infrastructure Layer`

  - 외부와 상호작용
  - ItemReader, ItemProcessor, ItemWriter

## Batch 실행 방식

### OS 스케줄러

- 한 머신 내부에서 os의 기능을 사용하여 os 스케줄러에서 batch 프로그램을 실행하는 것
- Linux crontab
- 실무에서는 사용하지 않는다

### Quartz Scheduler

- Quartz + Spring Batch 프레임워크를 함께 사용해 어플리케이션을 개발한다.
    - 어플리케이션 내부에서 스케줄링하므로 Batch 실행이 빠르다.
    - 스케줄링 데이터가 DB에 저장되어 Admin을 따로 만들어야 한다.

### CI Tool 이용

`Jenkins`

- 마스터에서 슬레이브로 명령을 전달해 배치 프로그램을 실행시킨다
- 젠킨스에서 지원하는 스케줄링 기능을 통해 배치 프로그램을 실행시킬 수 있다.

### Spring Cloud Data Flow

- Spring Cloud Data Flow를 활용해 잡을 실행시킨다.
    - Kubernetes 클러스터를 구축해야 한다.
    - Admin이 제공되고, 잡의 실행을 연결할 수 있는 등 다양한 기능을 제공한다.
