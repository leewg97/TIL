# SSL(Spring Security Learned)

## 스프링 시큐리티

### 시큐리티의 필요성
---
웹사이트는 서비스를 사용하는 유저들의 개인정보를 보호하고, 서버 리소스를 보호하기 위해 보안 정책을 설정해야 한다.

### 인증 (Authentication)
---
사이트에 접근하는 사람이 누구인지 시스템이 알아야 한다.<br> 
익명사용자(anonymous user)를 허용하는 경우도 있지만, 특정 리소스에 접근하거나 개인화된 사용성을 보장 받기 위해서는 반드시 로그인하는 과정이 필요하다.<br>
로그인은 보통 ```username```과 ```password``` 를 입력하고 로그인하는 경우와 SNS사이트를 통해 인증을 대리하는 경우가 있다.

- UsernamePassword 인증
  - Session 관리
  - 토큰 관리 (sessionless)
- SNS 로그인 (소셜 로그인) : 인증 위임

---
# JWT
## [Json Web Tokens](https://jwt.io/)

우리가 흔히 사용하는 인증 방식으로는 세션이 있다. 하지만,

- (1)서버를 여러대 두거나
- (2)같은 사용자가 서로 다른 도메인의 데이터를 요청할 경우(SSO > Single Sign-On)

에는 세션을 유지하기 위한 비용이 커지게 된다.

이러한 상황에서 우리는 서버에 사용자 정보를 저장하는 대신 클라이언트에 사용자 정보를 내려주고, 서버는 토큰의 사용자 정보를 모든 요청에서 확인하고 서비스를 해주는 방식을 사용할 수 있을 것이다. 이때 JWT 토큰이 유용하게 사용된다. (sessionless)

즉, session 정보가 서버에 없고 로그인을 하게되면 서버가 토근을 주게 되는 것. 요청할 때마다 DB를 뒤지는 것이 아닌, 보낸 토큰과 내가 발급한 토큰이 동일한지 검증하게 된다.

PAYLOAD에 Claim이라고 하는 데이터들을 <Key, Value>쌍으로 가지고 있는 데이터를 서버에서 주고받게 된다. 이때 PAYLOAD에 있는 데이터를 신뢰하기 위해서는 위변조 되지 않았다는 것을 증명하기 위해 뒤에 SIGNATURE값이 붙게 된다. 즉, 작성자가 작성한대로 전달했다는 것을 보장할 수 있는 것이다.

JWT 자바 라이브러리는 보통 [auth0.com](http://auth0.com) 에서 만든 java-jwt 라이브러리를 사용하거나, okta에서 만든 jjwt 라이브러리를 사용한다.

- JWT 토큰 확인 : [https://jwt.io/#debugger-io](https://jwt.io/#debugger-io)
- JWT : RFC7519 [https://datatracker.ietf.org/doc/html/rfc7519](https://datatracker.ietf.org/doc/html/rfc7519)
- JWS : RFC7515 [https://datatracker.ietf.org/doc/html/rfc7515](https://datatracker.ietf.org/doc/html/rfc7515)
- JWE : RFC7516 [https://datatracker.ietf.org/doc/html/rfc7516](https://datatracker.ietf.org/doc/html/rfc7516)

---

### JWT는 Header, Payload, Signature 3개의 부분으로 구성되어 있다

- Header : Signature를 해싱하기 위한 알고리즘 정보가 담겨있다.
- Payload : 서버와 클라이언트가 주고 받는, 시스템에서 실제로 사용될 정보에 대한 내용들을 담고 있다.
- Signature : 토큰의 유효성 검증을 위한 문자열이다.

### JWT 장단점

- 장점
  - 중앙의 인증서버, 데이터 스토어에 대한 의존성 없음, 시스템 수평 확장 유리
  - Base64 URL Safe Encoding > URL, Cookie, Header 모두 사용가능
- 단점
  - Payload의 정보가 많아지면 네트워크 사용량 증가, 데이터 설계 고려 필요
  - 토큰이 클라이언트에 저장, 서버에서 클라이언트의 토큰을 조작할 수 없음

---

- JWT Token의 구조 : header + body + signature
- 구현 라이브러리
  - java-jwt : oauth0.io
  - jjwt : okta

## Session | Token

`Session`을 대체하는 용도로 `Token`을 사용하는 것이기 때문에 `Session`에서 쓰는 기능을 그대로 쓸 수 있다.

- `sessionId` = 인증 서버에서 `Auth Token`을 내려주게 됨
- `session Timeout` = `Expire Time(토큰 만료시간)`
- 자동 로그인
  - `remember-me 쿠키` =  `Auth Token`이 만료 되고 자동로그인을 하려면 `Refresh Token`을 내려주면 됨 → 즉, `Auth Token`을 일정 기간 동안 갱신 받도록 해주는 것

- 그렇다면 만약 `Refresh Token`을 클라이언트에 두었을 때, 탈취가 된다면?
  - 먼저, Session으로 예를 들면  `remember-me`를 클라이언트에만 두었을 때, 카피가 되었을 때 대응이 어렵기 때문에, 서버에서`remember-me`를  시리즈로 관리하면 좋다.
  - `Refresh Token`은 어디서 관리해야 할까? → `Refresh Token`도 DB관리를 하거나, 추적 관리를 하는 기법을 사용해야 한다.

## JWT 스펙에서 지정한 claim

- iss : Issuer 토큰을 발행한 주체가 누구인지
  - 보통 단체, 회사, 사이트를 넣음
- sub : Subject 무엇에 관한 토큰인지
- aud : Audience 누구를 대상으로 한 토큰인지
- exp : Expiration 토큰의 만료 시간은 언제인지
- nbf : Not Before 토큰이 언제부터 유효한지
  - 보통 발행 직후부터 유효하지만, 특정 토큰은 그렇지 않은 경우가 있을 수 있음
- iat : Issued At 토큰 발행 시간
- jti : JWT ID : 토큰 자체의 아이디(일련번호?)
  - 토큰 추적할 수 있게 관리
- 그 밖에 인증에 필요하거나 대상서버에서 필요로 하는 데이터

## 토큰에는 어떤 내용을 넣어야 할까?

- 일반적으로는 인증에 필요한 최소한의 데이터를 넣음.
- 비밀번호나 전화번호등을 넣는 것은 안전하지 않음.
- 이 토큰은 언제든 공개할 수 있는 정보를 넣는 것이 좋음.
  - 서버에서 인증된 키가 아니라도 언제든 서버는 이 토큰을 열어서 그 안에 어떤 Claim 이 있는지를 볼 수 있기 때문.
- Signature : 어떤 서버든 검증 가능하도록 해주는 오픈된 토큰
  - 내부 내용은 Base64로 인코딩 되어 있음

## 토큰을 어떻게 관리할 것인가?

- 이론적으로는 토큰을 클라이언트가 관리하게 함.
  - 실제로 서버는 사용자 정보 캐싱이나 토큰의 유효성 평가, 혹은 Refresh Token 정책을 위해 서버에서 토큰을 관리하기도 함.
- 이 경우, 토큰과 사용자 정보를 관리하는 방법으로 다음과 같은 방법들을 사용하기도 합니다.
  - redis, hazelcast : 메모리 그리드
    - 사용자가 로그인을 했는데 userId로 사용자를 구별할 수 있지만 detail한 정보를 매번 DB에 가서 정보를 가져오게 되면 비효율적. 즉, 캐싱의 개념으로 생각하면 된다.
  - db 저장

## 간단한 SSO 사이트 구현할 때

인증 서버를 두고, 해당 인증서버로부터 토큰의 유효성을 검증하도록 하는 방식이 이상적이지만, 토큰을 다루는 방식이 동일하다면, 한 서버에서 발행한 토큰으로 SSO와 같은 효과를 볼 수도 있음

- 인증 서버와 리소스 서버를 분리한다.
- 인증토큰을 여러 리소스 서버에 재사용한다.
