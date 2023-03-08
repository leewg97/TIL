## Querydsl

### Jooq

---

- DB schema → Java class 생성 도구
- ORM framework가 아님
- “Jooq is not a replacement for JPA” : 대체제가 아님
    - SQL 잘 어울리면 Jooq 잘 맞음
    - Object Persistence가 잘 어울리면 JPA가 잘맞음
- Jooq says : “Jooq + JPA”

### Querydsl

---

“Unified Queries for Java. Querydsl is compact, safe and easy to learn.”

- 자바 코드(엔티티) → DB 쿼리 생성 도구
- HQL 생성 라이브러리
    - type-safety가 부족한 HQL(JPQL)의 대한
    - 읽기 어려운 Criteia API의 대안
- 커스텀 key join
- 자유로운 query projection
- Spring Data JPA Repository interface와 매끄러운 연동
- Spring Data 에서 다양한 서포트 지원
    - QuerydslRepositorySupport: EntityManager를 노출하지 않고, Querydsl 필요 기능 직접 지원
    - QuerydslPredicateExecutor: Predicate 을 이용한 dynamic select, Spring Data REST 지원
    - QuerydslBinderCustomizer: 파라미터 바인딩의 세부 기능 조절을 지원

### 커스텀 프로젝션

---

- setter 주입
- 생성자 주입 : `Projections.constructor()`
- `@QueryProjection`

- 다이나믹하게 where 조건 작성

`query.where("Q클래스"."검색 원하는 이름"."검색 원하는 방법(검색하고 싶은 파라미터)")`

```java
if (placeName != null && !placeName.isBlank()) {
      query.where(event.place.placeName.contains(placeName));
}
if (eventName != null && !eventName.isBlank()) {
    query.where(event.eventName.contains(eventName));
}
if (eventStatus != null) {
    query.where(event.eventStatus.eq(eventStatus));
}
if (eventStartDatetime != null) {
    query.where(event.eventStartDatetime.goe(eventStartDatetime));
}
if (eventEndDatetime != null) {
    query.where(event.eventEndDatetime.loe(eventEndDatetime));
}
```

- 쭉 이어 쓰면 And 로 연결이 됨
- `eq` : equal
- `goe` : greater or equal 이상
- `loe` : less or equal 이하

### Fetch?

---

- 어플리케이션이 DB로 부터 데이터를 가졍는 것
- DB와 통신하여 데이터를 읽는 것에는 큰 비용이 소모되기 때문에, 똑똑하게 가져오는 전략이 필요
- eager : 쿼리 날리는 시점에 데이터 즉시 가져오기
- lazy : 가져오려는 데이터를 접근할 때 가져오기
- lazy는 기본적으로
    - ORM의 특징이자 기능적 장점
    - 더 빠르고 경제적인 쿼리(적절히만 사용한다면)
    - 잘못 사용하면 데이터 접근 에러

- 효율성 - 데이터가 어느 쪽으로 더 자주 사용될 것 같은지 예측
- default 내버려두기 : 필요한 시점에 최선의 방식으로 데이터를 가져옴
- LAZY : 연관관계가 있는 엔티티에서 자식 엔티티만 가져오는 시나리오
    - LAZY 세팅이 후속 쿼리 발생 방지를 보장하지는 않는다
- EAGER 사용 : 연관 관계가 있는 엔티티에서 무조건 다 가져오는 시나리오
    - EAGER 세팅이 join 동작을 보장하지 않음
    - JPQL을 직접 작성하여 join을 영속성 컨텍스트에 알려줘야 함

### N+1 query problem

---

- 3가지 방법
    - 똑똑한 lazy
        - 비즈니스 로직을 면밀히 분석하여, 불필요한 연관 관계 테이블 정보를 불러오는 부분을 제거
        - 가장 똑똑하고 효율적인 방법
    - eager fetch + join jpql
        - join 쿼리를 직접 작성하는 방법은 다양 (@Query, querydsl, ...)
        - 쿼리 한 번에 오긴 하겠지만, join 쿼리 연산 비용과 네트워크로 전달되는 데이터가 클 수 있음
    - 후속 쿼리를 in 으로 묶어주기: N + 1 -> 1 + 1 로 I/O 줄일 수 있음
        - 하이버네이트 프로퍼티: default_batch_fetch_size
        - 스프링 부트에서 쓰는 법: spring.jpa.properties.hibernate.default_batch_fetch_size
        - 100 ~ 1000 사이를 추천
        - 모든 쿼리에 적용되고, 복잡한 도메인에서 join 쿼리를 구성하는 것이 골치아플 때 효율적