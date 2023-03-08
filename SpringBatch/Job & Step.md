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


## Step

- 작업 처리의 단위
- Chunk 기반 스텝, Tasklet 스텝 2가지
- 우측 사진은 Chunk 기반의 스

### Chunk-oriented Processing Step

- chunk기반으로 하나의 트랜잭션에서 데이터를 처리한다.
- commitInterval만큼 데이터를 읽고 트랜잭션 경계 내에서 chunkSize만큼 write를 한다.

```java
List items = new Arraylist();
for(int i = 0; i < commitInterval; i++){
    Object item = itemReader.read();
    if (item != null) {
        items.add(item);
    }
}

List processedItems = new Arraylist();
for(Object item: items){
    Object processedItem = itemProcessor.process(item);
    if (processedItem != null) {
        processedItems.add(processedItem);
    }
}

itemWriter.write(processedItems);
```

- chunkSize: 한 트랜잭션에서 쓸 아이템의 갯수
- commitInterval: reader가 한번에 읽을 아이템의 갯수
- chunkSize >= commitInterval 하지만 보통 같게 맞춰서 사용하는 것이 좋다.

```java
@Bean
public Job sampleJob(JobRepository jobRepository, Step sampleStep) {
    return this.jobBuilderFactory.get("sampleJob")
    			.repository(jobRepository)
                .start(sampleStep)
                .build();
}

@Bean
public Step sampleStep(PlatformTransactionManager transactionManager) {
	return this.stepBuilderFactory.get("sampleStep")
				.transactionManager(transactionManager)
				.<String, String>chunk(10)
				.reader(itemReader())
				.writer(itemWriter())
				.build();
}
```

### TaskletStep


- 하나의 트랜잭션에서 모든 것을 처리하는 것 ⇒ 단순한 처리일 때 사용

```java
@Bean
public Step step1() {
    return this.stepBuilderFactory.get("step1")
    .tasklet(myTasklet())
    .build();
}
```

- Tasklet 구현체를 설정한다. 내부에 단순한 읽기, 쓰기, 처리 로직을 모두 넣는다.
- RepeatStatus (반복 상태)를 설정한다. RepeatStatus.FINISHED


### Reference
https://docs.spring.io/spring-batch/docs/current/reference/html/step.html <br>
https://docs.spring.io/spring-batch/docs/current/reference/html/domain.html#job <br>
https://docs.spring.io/spring-batch/docs/current/reference/html/job.html#configuringAJob