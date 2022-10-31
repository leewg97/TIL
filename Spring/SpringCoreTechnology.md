# 스프링 프레임워크 핵심기술

![image](https://user-images.githubusercontent.com/77063888/199105450-e9e27f29-d607-448c-abc7-329cbbed43be.png)

## Core (DI, IoC)

스프링의 근간, 내가 만든 클래스를 스프링이 직접 관리하여 어플리케이션을 동작하게 한다

## AOP(Aspect Oriented Programming)

공통적인 코드를 프레임워크 레벨에서 지원해주는 방법

## Validation, Data binding

검증 그리고 외부에서 받은 데이터를 담아내는 방법

## Resource

스프링 내부에서 설정이 들어있는 파일들에 접근하는 동작 원리

## SpEL

짧은 표현식을 통해 필요한 데이터나 설정 값을 얻어올 수 있게 하는 특별한 형태의 표현식에 가까운 간편한 언어

## Null-Safety

Null을 조금 더 잘 다루고 싶다면



# 스프링의 디자인 철학

- 모든 기능에서 다양한 가능성(다양한 모듈)을 사용 가능, 심지어 외부 모듈을 활용 가능
    - 너무 높은 자유도 어떤 점에서는 스프링을 어렵게 하는 요소
- 유연하게 계속 추가 개발을 하고 있는 프레임워크
- 이전 버전과의 강력한 호환성
    - 너무 많은 레거시 때문에 코드의 복잡성이 높아지긴 함
- API 디자인을 섬세하게 노력한다
    - 스프링 코드 자체가 하나의 좋은 참고 소스
- 높은 코드 품질을 유지하려 함
    - 스프링 프로젝트 github은 아주 좋은 참고 소스이자 PR과 이슈 관리도 좋은 프로세스 참고용

→ 한마디로 높은 자유도를 주고 계속 발전하는 고품질의 다양성이 있는 프로젝트, 그런데 너무 자유로워서 때론 어렵다.


## References

[Overview of Spring Framework](https://docs.spring.io/spring-framework/docs/5.0.0.RC2/spring-framework-reference/overview.html#overview-modules)

[Spring Framework Overview](https://docs.spring.io/spring-framework/docs/current/reference/html/overview.html#overview-philosophy)

[Core Technologies](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#spring-core)