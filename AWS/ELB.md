## ELB

### What is load balancing?
* Load Balances are servers that forward traffic to multiple servers downstream
  * 즉, 쉽게 말해 여러 서버로 트래픽을 전달하는 역할을 하는 것.

### Why use a load balancer?
* 부하를 다수의 다운스트림 인스턴스로 분산
* 다운스트림 인스턴스 장애의 원활한 처리
* 단일 DNS를 응용 프로그램에 노출
* 쿠키의 stickiness 강화
* 클라우드 내 public 및 private 트래픽 분리
* 인스턴스의 health check 
* 웹사이트에 SSL termination 제공
* 고가용성

### Why use an Elastic Load Balancer?
* ELB는 관리형 로드 밸런서이기도 하다.
* AWS에서 관리하며, 어떤 경우에도 작동할 것을 보장한다.
  * AWS에서 upgrade, maintenance, high availability 관리
  * AWS는 몇 가지 configuration knobs만 제공하며 
  * 로드 밸런서의 작동 방식을 수정할 수 있게끔 일부 configuration knob도 제공
* 자체 로드 밸런서를 마련하는 것 보다 저렴하다.
* 자체 로드 밸런서를 직접 관리하려면 확상성 측면에서 굉장히 번거롭기도 하다.

* 많은 AWS 제품 및 서브스와 통합된다.
  * EC2, EC2 ASG, ECS
  * ACM, Cloud Watch
  * Route 53, WAF, Global Accelerator

### Health Checks
* Health Checks are crucial for Load Balancers
* 로드 밸런서가 트래픽을 인스턴스에 전달할 때 해당 인스턴스가 요청에 응답할 수 있는지 여부를 알 수 있다. 
* Health Check는 port와 route에서 이루어진다.(`4567`, `/health`)
  * 200 응답이 아니면 인스턴스가 정상적인 상태가 아닌 것.

### Type of load balancer on AWS
* Class Load Balancer - CLB - 2009
  * Old Generation
  * HTTP, HTTPS, TCP, SSL(secure TCP) 지원
* Application Load Balancer - ALB - 2016
  * New Generation
  * HTTP, HTTPS, WebSocket 지원
* Network Load Balancer - NLB - 2017
  * New Generation
  * TCP, TLS, secure TCP, UDP 지원
* Gateway Load Balancer - GWLB - 2020
  * Network layer인 3계층과 IP 프로토콜에서 작동