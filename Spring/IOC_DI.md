# IoC(Inversion of Control), DI(Dependency Injection)

- IoC나 DI는 레고와 같은 것이다
    - 스프링이 바닥판처럼 깔려있고, 우리는 그 위에서 멋진 조립(나의 어플리케이션)을 만들면 된다

## Bean이란?

### 자바에서의 javaBean

- 데이터를 저장하기 위한 구조체로 자바 빈 규약이라는 것을 따르는 구조체
- private 프로퍼티와 getter/setter로만 데이터를 접근한다.
- 인수(argument)가 없는 기본 생성자가 있다.

```java
public class JavaBean {
	private String id;
	private Integer count;

	public JavaBean(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
```

### 스프링에서의 Bean

- 스프링 IoC 컨테이너에 의해 생성되고 관리되는 객체
- 자바에서처럼 new Object(); 로 생성하지 않는다
- 각각의 Bean들 끼리는 서로를 편리하게 의존(사용)할 수 있음

## 스프링 컨테이너 개요

ApplicationContext 인터페이스를 통해 제공되는 스프링 컨테이너는 Bean 객체의 생성 및 Bean들의 조립(상호 의존성 관리)을 담당합니다.

### Bean의 등록

- 과거에는 xml로 설정을 따로 관리하여 등록(불편)
- 현재는 annotation 기반으로 Bean 등록
    - @Bean, @Controller, @Service

### Bean 등록 시 정보

- Class 경로
- Bean의 이름
    - 기본적으로는 원 Class 이름에서 첫 문자만 소문자로 변경  → accountService, userDao
    - 원하는 경우 변경 가능
- Scope : Bean을 생성하는 규칙
    - singleton : 컨테이너에 단일로 생성
    - prototype: 작업 시마다 Bean을 새로 생성하고 싶을 경우
    - request: http 요청마다 새롭게 Bean을 생성하고 싶은 경우

### Bean LifeCycle callback(빈 생명주기 콜백함수)

- callback : 어떤 이벤트가 발생하는 경우 호출되는 메서드
- lifecycle callback
    - Bean을 생성하고 초기화하고 파괴하는 등 특정 시점에 호출되도록 정의된 함수
- 주로 많이 사용되는 콜백
    - @PostConstruct : 빈 생성 시점에 필요한 작업을 수행
    - @PreDestroy : 빈 파괴(주로 어플리케이션 종료) 시점에 필요한 작업을 수행

---

## References

[Core Technologies](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans)

[JavaBeans - Wikipedia](https://en.wikipedia.org/wiki/JavaBeans)