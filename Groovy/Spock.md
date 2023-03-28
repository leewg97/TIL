# Spock Framework
- Spock란 Groovy언어를 이용하여 테스트 코드를 작성할 수 있는 프레임워크이며 JUnit과 비교했을 때 코드를 더 간결하게 작성 가능하다.
- Groovy언어는 동적 타입 프로그래밍 언어로 JVM위에서 동작하며 Java문법과 유사하다.
- Junit의 경우 주석으로 블록을 구분했었고, 메소드 이름 또한 제약사항이 많이 존재한다.
- 테스트 메소드 이름을 문자열로 작성할 수 있으며 given, when, then 코드 블록을 명확히 구분할 수 있다.
- JUnit에서 제공하는 주요 요소들은 모두 Spock에서 제공하고 있다.

## Spock Framework 테스트 코드 작성 순서

- 테스트 클래스는 Groovy 클래스로 생성하고, Specifiacation 클래스를 상속 받는다.
- feature(테스트 메서드)는 def를 이용하여 함수롤 선언하며, 하나 이상 블록이 존재해야 한다.
- given : 테스트에 필요한 값을 준비한다.
- when : 테스트할 코드를 실행한다.
- then : when과 함께 사용하며 예외 및 결과 값을 검증한다.
- expect : then과 같으며 when을 필요로 하지 않기 때문에 간단한 테스트 및 where과 같이 사용된다.
- where : 데이터가 다르고 로직이 동일한 경우 동일한 테스트에 대한 중복 코드 제거 가능.

### 장점

- where은 | 로 구분한 Data Table로 생성이 가능하며 파라미터, 결과값을 보기 좋게 구분할 수 있다.

### 설정

- Spock 사용을 위해 IDE Spock 플러그인 및 의존성 추가
    
    ```
    plugins {
    	id 'groovy' // groovy 지원
    	id 'java
    }
    
    testImplementation('org.spockframework:spock-core:2.1-groovy-3.0')
    testImplementation('org.spockframework:spock-spring:2.1-groovy-3.0') // Spring 과 같이 사용하기 위해
    
    // 런타임에 클래스 기반 mock을 만들기 위해서 필요
    testImplementation('net.bytebuddy:byte-buddy:1.9.3')
    ```
    
- groovy 디렉토리 생성 및 spock 테스트 코드 생성

<br><br>

# TestContainers

## TestContainers 사용 이유

- [https://www.testcontainers.org/](https://www.testcontainers.org/)
- JPA이용하여 CRUD 테스트 코드를 작성할 때 어떤 DB 환경이 좋을까?
    - 1. 운영환경과 유사한 스펙의 DB(개발 환경 DB) 사용하기
    - 2. 인메모리 DB(ex H2) 사용하기
    - 3. Docker 이용하기
    - 4. TestContainers를 이용하기
- TestContainers는 운영환경과 유사한 DB 스펙으로 독립적인 환경에서 테스트 코드를 작성하여 테스트가 가능하다.

## TestContainers란?

- TestContainers는 Java 언어만으로 docker container를 활용한 테스트 환경 구성
- 도커를 이용하여 테스트할 때 컨테이너를 직접 관리해야 하는 번거로움을 해결 해주며, 운영환경 과 유사한 스펙으로 테스트 가능
- 즉, 테스트 코드가 실행 될 때 자동으로 도커 컨테이너를 실행하여 테스트 하고, 테스트가 끝나면 자동으로 컨테이너를 종료 및 정리
- TestContainers는 다양한 모듈이 존재

## TestContainers 환경(MariaDB, Redis)

- MariaDb와 Redis를 독립된 환경에서 테스트 코드 작성을 위해 TestContainers 적용
- [https://www.testcontainers.org/modules/databases/jdbc/](https://www.testcontainers.org/modules/databases/jdbc/)
- `build.gradle` 에 의존성 추가
    
    ```
    testImplementation 'org.testcontainers:spock:1.17.1'
    testImplementation 'org.testcontainers:mariadb:1.17.1'
    ```
    
- 테스트를 위한 `test/resource/application.yml` 파일 생성
    
    ```
    spring:
    	datasource:
    		driver-class-name: org.testcontainers.jdbc.ContainerDatabaseDriver
    		url: jdbc:tc:mariadb:10:///
    ```
    

### TestContainers 사용 위한 공식문서

- [https://www.testcontainers.org/quickstart/spock_quickstart/](https://www.testcontainers.org/quickstart/spock_quickstart/) ( Spock)
- [https://www.testcontainers.org/modules/databases/mariadb/](https://www.testcontainers.org/modules/databases/mariadb/) ( MariaDB)
- [https://www.testcontainers.org/test_framework_integration/manual_lifecycle_control/](https://www.testcontainers.org/test_framework_integration/manual_lifecycle_control/) ( singleton testcontainers)
- [https://www.testcontainers.org/features/creating_container/](https://www.testcontainers.org/features/creating_container/) (GenericContainer)
- [https://www.testcontainers.org/modules/databases/jdbc/](https://www.testcontainers.org/modules/databases/jdbc/) ( jdbc support )