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
https://docs.spring.io/spring-batch/docs/current/reference/html/step.html