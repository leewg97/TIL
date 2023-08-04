## AWS Regions

-   리전은 각각의 이름이 있으며 전 세계에 결쳐 있다.
-   리전은 데이터 센터의 집합으로 각 리전은 가용 영역(Availability Zones)을 가지고 있다.
    -   `ap-southeast-2a`, `ap-southeast-2b` 등의 이름으로 되어있다.
    -   재난 발생을 대비하여 각 가용 영역들은 분리되어 있다.
-   가용 영역은 최소 2개 부터 보통 3개이며, 최대 6개이다.
-   각 가용 영역은 high bandwidth 및 ultra-low latency networking으로 리전을 형성한다.

## Global Services & Region-scoped

-   Global Services
    -   Identity and Access Management(IAM)
    -   Route 53(DNS Service)
    -   CloudFront(Content Delivery Network)
    -   WAF(Web Application Firewall)
-   Region-scoped Services
    -   Amazon EC2
    -   Elastic Beanstalk
    -   Lambda
    -   etc.
