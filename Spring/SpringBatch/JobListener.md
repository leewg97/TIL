## JobListener

* 리스너는 Job의 실행 결과를 가지고 무언가를 하고 싶을 때 사용할 수 있다.

```java
 @Bean
    public Job advancedJob(Step advancedStep) {
        return jobBuilderFactory.get("advancedJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener())
                .start(advancedStep)
                .build();
    }

    @JobScope
    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info("[JobExecutionListener#BeforeJob] jobExecution is " + jobExecution.getStatus());
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                if (jobExecution.getStatus() == BatchStatus.FAILED) {
                    log.info("[JobExecutionListener#AfterJob] jobExecution is FAILED.");
                }
            }
        };
    }

    @Bean
    @StepScope
    public Tasklet advancedTasklet(@Value("#{jobParameters['targetDate']}") String targetDate) {
        return (contribution, chunkContext) -> {
            log.info("[AdvancedJobConfig] JobParameter - targetDate = " + targetDate);
            log.info("[AdvancedJobConfig] executed advancedTasklet");
            throw new RuntimeException("ERROR");
        };
    }
```