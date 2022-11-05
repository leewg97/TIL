package com.example.demospringbatch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DemoJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean("demoJob")
    public Job demoJob(Step demoStep) {
        return jobBuilderFactory.get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(demoStep)
                .build();
    }

    @JobScope // Job 이 실행되는 동안에만 실행될 수 있게 지정
    @Bean("demoStep")
    public Step demoStep(Tasklet tasklet) {
        return stepBuilderFactory.get("demoStep")
                .tasklet(tasklet)
                .build();
    }

    @StepScope // Step 이 실행되는 동안에만 실행될 수 있게 지정
    @Bean
    public Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            log.info("HELLO SPRING BATCH");
            return RepeatStatus.FINISHED;
        };
    }

}
