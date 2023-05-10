## 개발환경과 운영환경 profile 분리하기

- **Spring Profile은 어플리케이션 설정을 특정 환경에서만 적용되게 하거나 환경 별(local, develop, production)로 다르게 적용할 때 사용한다.**
    
    (application-local.yml, application-prod.yml)  / **application-{profile}.yml**
    
- Spring boot는 어플리케이션이 실행될 때 자동으로 `application.properties` 또는 `application.yml`을 찾는다.
- Spring boot 버전 2.4 이전과 이후 설정 방법이 다르므로 주의해야 한다.