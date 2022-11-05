## Job

- 전체 배치 프로세스를 캡슐화한 도메인
- Step의 순서를 정의(실질적인 Batch작업의 단계에 대한 순서를 정의)
- JobParameters를 받음

```java
@Bean
public Job footballJob(){
    return this.jobBuilderFactory.get("footballJob")
                    .start(playerLoad())
                    .next(gameLoad())
                    .next(playerSummarization())
                    .build();
}
```

### Job 특징

- 1개의 Job은 여러개의 Step을 포함할 수 있음
- Job name을 통해 Job을 구분할수 있음
- Job name으로 Job을 실행시킬 수 있음
- Job을 만드는 빌더는 많지만 JobBuilderFactory로 쉽게 Job을 만들수 있음

### JobInstance

- Job이 명세서라면 JobInstance는 Job이 실행되어 실체화된 것
- JobInstance는 배치 처리에서 Job이 실행될 때 하나의 Job 실행 단위
- 같은 Job에 같은 조건(Job Parameters)이면 JobInstance는 동일하다고 판단
- Job이 실패해서 다시 같은 조건으로 Job을 실행한다면 같은 JobInstance라고 할 수 있음

### JobExecution

- JobExecution은 JobInstance의 한번 실행을 뜻함
- 어떤 Job이 같은 조건으로 1번 실패하고 1번 성공한다면 JobInstance는 1개로 JobExecution은 2개로 다름
- JobExecution은 실패했든지 성공했든지 간에 실제로 실행시킨 사실과 동일한 의미이기 때문에 배치 실행과 관련된 정보를 포함하고 있음


### Reference
https://docs.spring.io/spring-batch/docs/current/reference/html/domain.html#job <br>
https://docs.spring.io/spring-batch/docs/current/reference/html/job.html#configuringAJob