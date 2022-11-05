## Chunk

### DB
![image](https://user-images.githubusercontent.com/77063888/200139548-4e9f08e6-02e1-4880-a929-a72c477d5ee6.png)

### Code
```java
@Bean("plainTextJob")
    public Job plainTextJob(Step plainTextStep) {
        return jobBuilderFactory.get("plainTextJob")
                .incrementer(new RunIdIncrementer())
                .start(plainTextStep)
                .build();
    }

    @JobScope
    @Bean("plainTextStep")
    public Step plainTextStep(ItemReader plainTextReader, ItemProcessor plainTextProcessor, ItemWriter plainTextWriter) {
        return stepBuilderFactory.get("plainTextStep")
                .<PlainText, String>chunk(5)    // <읽어 올 타입, 프로세싱 할 타입>
                .reader(plainTextReader)
                .processor(plainTextProcessor)
                .writer(plainTextWriter)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<PlainText> plainTextReader() {
        return new RepositoryItemReaderBuilder<PlainText>()
                .name("plainTextReader")
                .repository(plainTextRepository)
                .methodName("findBy")
                .pageSize(5) // 읽게되는 commit interval
                .arguments(List.of()) // 쿼라메소드에 어떤 조건이나 파라미터가 있다면 arguments 에 List 로 넘겨주게 됨
                .sorts(Collections.singletonMap("id", Sort.Direction.DESC))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<PlainText, String> plainTextProcessor() {  // <프로세싱 전 타입, 후 타입>
        return item -> "processed " + item.getText();
    }

    @StepScope
    @Bean
    public ItemWriter<String> plainTextWriter() {
        return items -> {
            items.forEach(System.out::println);
            System.out.println("==== Chunk is finished");
        };
    }
```
* Read -> Process -> Write의 순으로 진행
* DB에 총 10개의 값이 들어가 있는데 위에서 작성한 코드는 한 번에 읽는 값이 5개이므로, 총 2개의 chunk로 나눠서 처리를 하게 된다.
* 또한 Sort를 `id`의 역순인 DESC로 지정하였기 때문에 `id` 10번 부터 역순으로 읽고 처리하게 된 것.

### Console
```
Hibernate: select plaintext0_.id as id1_0_, plaintext0_.text as text2_0_ from plain_text plaintext0_ order by plaintext0_.id desc limit ?
Hibernate: select count(plaintext0_.id) as col_0_0_ from plain_text plaintext0_
processed jelly
processed ice
processed honey
processed grape
processed fork
==== Chunk is finished
Hibernate: select plaintext0_.id as id1_0_, plaintext0_.text as text2_0_ from plain_text plaintext0_ order by plaintext0_.id desc limit ?, ?
Hibernate: select count(plaintext0_.id) as col_0_0_ from plain_text plaintext0_
processed egg
processed dessert
processed carrot
processed banana
processed apple
==== Chunk is finished
Hibernate: select plaintext0_.id as id1_0_, plaintext0_.text as text2_0_ from plain_text plaintext0_ order by plaintext0_.id desc limit ?, ?
Hibernate: select count(plaintext0_.id) as col_0_0_ from plain_text plaintext0_
```
