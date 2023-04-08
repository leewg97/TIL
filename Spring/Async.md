## Async

### Async
---

- Spring Framework에서 비동기 처리 위해서는 Spring Framework 도움이 필요함
- Spring Framework가 비동기로 처리하고자 하는 메서드

- Bean 주입을 받되 `@Async` 가 붙은 메서드 활용
- 해당 어노테이션은 무조건 `public` 이어야 함

### Spring Async 환경 세팅 - Config
---

- Executor 선언
    - 어떤 메서드에서 어떤 `ThreadPool`을 사용할 것인지 지정
- `@EnableAsync` 선언을 위한 Config 파일 정의

### Spring Async 환경 세팅 - Controller, Service
---

- Service단에서 비동기하게 동작하는 코드 작성을 하기 위해서는 Spring Framework 도움이 필요함
- Spring Container에 등록되어 있는 Bean을 사용해야 함
    - 즉, 주입받은 빈 안에 있는 메서드 호출

- 어떤 Executor를 사용할 것 인지 명시해주어야 함
- 예를 들면  `@Async("defaultTaskExecutor")` 처럼

### Spring에서 Async 동작 원리
---

![image](https://user-images.githubusercontent.com/77063888/230728672-e32b8578-8d6d-4158-bfb4-6f73576f5466.png)

- Caller(AsyncService)에서 EmailService를 호출하는데 여기서 Spring이 개입을 하여 순수한 EmailService Bean이 아니라, 이 Bean을 wrapping한 EmailService를 사용하게 하여 async하게 동작하도록 하는 원리
- 즉, EmailService를 Proxy 객체로 wrapping을 하여 async 하게 동작이 가능하도록 함
- 키워드
    - 순수한 빈 X
    - 해당 빈을 한번 wrapping한 proxy 객체를 받아옴
    - 그 사이에서 Spring Framework가 비동기로 동작할 수 있도록 지원해줌

### Spring에서 Async 사용 시 주의해야 할 부분
---

- 인스턴스 선언 후 인스턴스 내 메서드 호출시 비동기하게 동작하지 않음
    - 인스턴스 선언 후 사용이기 떄문에 Spring Framework의 도움을 받을 수 없음
- 내부 메서드에 `@Async` 를 붙이고 해당 메서드 호출을 해도 비동기하게 동작하지 않음