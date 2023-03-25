## Docker 왜 사용해야 할까?

- 똑같은 일을 하는 2대의 서버가 있다 해도, A서버는 1년 전에 구성했고, B서버는 이제 막 구성했다면 운영체제부터 컴파일러, 설치된 패키지까지 완벽하게 같기는 쉽지 않다.
- 이러한 차이가 문제를 발생시킬 수 있다.
- A서버는 되는데 왜 B서버는 안되지?
- 도커는 서버마다 동일한 환경을 구성해주기 때문에 이러한 문제를 해결할 수 있다.
- 동일한 환경을 구성하기 때문에 auto scaling에 유리

## 도커와 기존 가상화 기술(VM)의 차이

- 한대의 서버에서 하나의 어플리케이션만 운영하는 전통적인 방식에서 하이퍼 바이저 기반 가상화 등장
- 하이퍼 바이저는 호스트 시스템(윈도우, 리눅스 증)에서 다수의 게스트 OS(가상머신)을 구동할 수 있게 하는 소프트웨어
- 각 VM마다 독립적으로 동작
- **도커는 하이퍼 바이저 구조를 토대로 등장했으며, VM보다 훨씬 가볍게 동작하기 때문에 성능에 유리.**

## 도커의 컨테이너와 이미지

- **이미지란 코드, 런타임, 시스템 도구, 시스템 라이브러리 및 설정과 같은 응용프로그램을 실행 하는데 필요한 모든 것을 포함하는 패키지**이다.
- 이미지는 Github와 유사한 서비스인 [https://hub.docker.com](https://hub.docker.com/) 을 통해 버전 관리한다.
- **컨테이너란 도커 이미지를 독립된 공간에서 실행할 수 있게 해주는 기술**
- 즉, 도커 이미지는 프로그램을 실행하는데 필요한 설정이나 종속성들을 가지고 있고, 컨테이너는 이미지 인스턴스이며, 프로그램을 실행한다.

## 도커 파일 이란?

- **Dockerfile이란 도커 이미지를 구성하기 위해 있어야할 패키지, 의존성, 소스코드 등을 하나의 file로 기록하여 이미지화 시킬 명령 파일**
- **즉, 이미지는 컨테이너를 실행하기 위한 모든 정보를 가지고 있기 때문에 더 이상 새로운 서버가 추가되면 의존성 파일을 컴파일하고 이것 저것 설치할 필요가 없음.**

### 도커 파일 주요 명령어

- FROM
    - 새로운 이미지를 생성할 때 기반으로 사용할 이미지를 지정(이미지 이름:태그)
    - Ex) jdk 11이 있는 컨테이너 사용 → `FROM openjdk:11`
- ARG
    - 이미지 빌드 시점에서 사용할 변수 지정
    - `ARG JAR_FILE=build/libs/app.jar`
- COPY
    - 호스트에 있는 파일이나 디렉토리를 Docker 이미지의 파일 시스템으로 복사
    - `COPY ${JAR_FILE}./app.jar`
- ENV
    - 컨테이너에서 사용할 환경 변수 지정
    - TimeZone 환경 변수 ⇒ `ENV TZ=Asia/Seoul`
- ENTRYPOINT
    - 컨테이너가 실행되었을 때 항상 실행되어야 하는 커맨드 지정
    - `ENTRYPOINT ["java", "-jar", "./app.jar"]`

## 도커 컴포즈란?

- **Docker Compose란 멀티 컨테이너 도커 어플리케이션을 정의하고 실행하는 도구**
- Application, Database, Redis, Nginx 등 각 독립적인 컨테이너로 관리한다고 했을 때 다중 컨테이너 라이프 사이클을 어떻게 관리해야 할까?
- **여러개의 도커 컨테이너로부터 이루어진 서비스를 구축 및 네트워크 연결, 실행 순서를 자동으로 관리**
- docker-compose.yml파일을 작성하여 1회 실행하는 것으로 설정된 모든 컨테이너를 실행

## jar 파일 생성 및 도커 파일 작성하기

- gradle wrapper 를 이용하여 jar파일 생성

```
$ gradlew build(Window)
$ ./gradlew build(Linux, OSX)
```

- 기본 경로는 프로젝트의 `build/libs/*.jar`
- build.gradle에서 jar 파일 이름 변경

```
bootJar {
	archiveFileName = 'app.jar'
}
```

- app, database, redis 도커파일 각각 작성

```
FROM openjdk:11
ARG JAR_FILE=build/libs/app.jar
COPY ${JAR_FILE} ./app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "./app.jar"]
```

```
FROM redis:6
ENV TZ=Asia/Seoul
```

```
FROM mariadb:10
ENV TZ=Asia/Seoul
```

## 도커를 이용하여 스프링 부트 어플리케이션 싱글 컨테이너 생성

- docker run 명령어를 통해 컨테이너 생성 및 시작

```
$ docker run --name [container name] -p 8080:8080
--name : 컨테이너 이름 지정
-p : 컨테이너는 기본적으로 외부와 격리되어 있기 때문에 호스트와 컨테이너 port 포워딩 처리

$ docker ps
$ docker ps -a
$ docker exec -it [컨테이너 이름 또는 id] /bin/bash  // 컨테이너를 터미널 환경으로 접근
$ docker stop [컨테이너 이름 또는 id]
$ docker inspect [컨테이너 이름 또는 id]
```

- docker run 명령어를 통해 컨테이너 생성 및 시작

```
$ docker login

// docker hub id를 이용하여 repository 찾기
$ docker build -t [docker hub id]/[이미지 이름:태그][Dockerfile 경로] 
$ docker images
$ docker push [docker hub id]/[이미지 이름: 태그]
```

## 도커 컴포즈 파일 작성(docker-compose-local.yml)

- 로컬에서 사용할 DB, Redis 도커파일 작성 후  docker-compose 파일 작성

```
docker-compose -f docker-compose-local.yml up
```