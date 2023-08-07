## IAM(Identity and Access Management)

### IAM : Users & Groups
* IAM에서는 사용자를 생성하고 그룹에 배치하기 때문에 global service에 해당된다.
* 사용자와 그룹은 왜 생성하나?
  * 사용자들이 AWS계정을 사용토록 허용하기 위해서이며, 그러한 허용을 위해 권한 부여를 하기 위함이다.
  * 이를 위해 사용자 또는 그룹에게 정책 또는 IAM Policy라 불리는 JSON 문서를 지정할 수 있다. 
  * 루트 계정을 사용하여 작업하는 것은 보안상의 이유로 지양하는 것이 좋다.	
  * 때문에 루트 계정은 꼭 필요할 때만 사용하는 것이 올다.

### IAM Policy 구조 요소
**Consists of**
* Version : Policy language version 으로 항상 "2012-10-17"을 포함한다. 
* Id : An Identifier for the policy(Optional)
  * Optional 요소로 해당 정책의 식별자를 의미한다. 
* Statement : one or more individual statement(required)

**Statement consists of**
* Sid : Optional 요소로 해당 statement의 식별자를 의미한다.
* Effect : whether the statement allows or denies access(`Allow`, `Deny`) => 특정 API 접근 허용 여부를 `Allow`, `Deny`로 표현한다.
* Principal : account / user / role to which this policy applied to => 특정 정책이 적용될 사용자, 계정 혹은 역할로 구성된다. 
* Action : list of actions this policy allows or denies => effect에 기반해 허용 및 거부되는 API 호출 목록으로 구성된다. 
* Resource : list of resources to which the actions applies to => 적용될 action의 리소스 목록으로 구성되어있다. 
* Condition : confitions for when this policy is in effect(Optional) -> Statement가 언제 적용될지를 결정하는 요소로 선택 사항인 구성 요소이다.

### IAM - Password Policy
* AWS에서는 비밀번호 정책을 설정할 수 있다. 
  * 비밀번호의 최소 길이를 설정할 수 있다. 
  * 특정 문자를 요구할 수 있다. 
    * 대문자를 포함하고나, 숫자를 포함하는등의 특정 문자를 포함하여 설정하도록 지정할 수 있다. 
  * 비밀번호 만료를 설정하여 사용자들이 그들의 비밀번호를 교체하게 설정할 수 있다.

### IAM - Multi Factor Authentication - MFA
* MFA = 비밀번호 + 계정 소유주의 물리적 장치
* 장점 : 
  * 해킹을 당하더라도 로그인을 위해 사용자의 휴대전화 등, 계정 소유주의 물리적 장치가 추가로 필요하다

### MFA devices options in AWS
* Virtual MFA device
  * Google Authenticator(Phone Only)
  * Authy(nulti-device)
    * Authy는 하나의 장치에서 토큰을 여러개 지원한다.
    * 즉, 원하는 수만큼의 계정 및 사용자 등록이 가능하다.
  * Universal 2nd Factor(U2F) Security Key
    * Yubico사의 YubiKey(3rd party)
    * support for multiple root and IAM users using a single security key
  * Other hardware MFA device

### How can users access AWS?
* To access AWS, you have three options:
  * AWS Management Console (protected by password + MFA)
  * AWS Command Line Interface(CLI) : protected by accesss keys
  * AWS Software Developer Kit(SDK) - for code : protected by access keys
* Access Keys are generated through the AWS Console 
* Users manae their own access keys

### What's the AWS SDK?
* AWS Software Development Kit(AWS SDK)
* Language- specific APIs(set of libraries) - 특정 언어로 된 라이브러리 집합
  * 따라서 프로그래밍 언어에 따라 개별 SDK가 존재한다.
* Enables you to access and manage AWS services programmatically
* Embedded within your application
* Supports
  * SDK(`JavaScript`, `Pyhton`, `PHP`, `.NET`, `Ruby`, `Java`, `Go`, `Node.js`, `C++`)
  * Mobile SDKs(`Android`, `IOS`, ...)
  * IoT Device SDKs(`Emdedded C`, `Arduino`, ...)