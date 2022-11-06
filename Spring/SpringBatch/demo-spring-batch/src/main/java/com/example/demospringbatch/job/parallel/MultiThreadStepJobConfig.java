package com.example.demospringbatch.job.parallel;

import com.example.demospringbatch.dto.AmountDto;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;

/**
 * 단일 프로세스에서 청크 단위로 병렬 처리한다.
 */
@Configuration
@AllArgsConstructor
public class MultiThreadStepJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job multiThreadStepJob(Step multiThreadStep) {
        return jobBuilderFactory.get("multiThreadStepJob")
                .incrementer(new RunIdIncrementer())
                .start(multiThreadStep)
                .build();
    }

    @JobScope
    @Bean
    public Step multiThreadStep(
            FlatFileItemReader<AmountDto> amountFileItemReader,
            ItemProcessor<AmountDto, AmountDto> amountFileItemProcessor,
            FlatFileItemWriter<AmountDto> amountFileItemWriter
    ) {
        return stepBuilderFactory.get("multiThreadStep")
                .<AmountDto, AmountDto>chunk(10)
                .reader(amountFileItemReader)
                .processor(amountFileItemProcessor)
                .writer(amountFileItemWriter)
                .build();
    }

    @StepScope
    @Bean
    public FlatFileItemReader<AmountDto> amountFileItemReader() {
        return new FlatFileItemReaderBuilder<AmountDto>()
                .name("amountFileItemReader")
                .fieldSetMapper(new AmountFieldSetMapper())
                .lineTokenizer(new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_TAB))
                .resource(new FileSystemResource("data/input.txt"))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<AmountDto, AmountDto> amountFileItemProcessor() {
        return item -> {
            item.setAmount(item.getAmount() * 100);
            return item;
        };
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<AmountDto> amountFileItemWriter() throws IOException {
        BeanWrapperFieldExtractor<AmountDto> fieldExtractor = new BeanWrapperFieldExtractor();
        fieldExtractor.setNames(new String[]{"index", "name", "amount"});
        fieldExtractor.afterPropertiesSet();

        DelimitedLineAggregator<AmountDto> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setFieldExtractor(fieldExtractor);

        String filePath = "data/output.txt";
        new File(filePath).createNewFile();

        return new FlatFileItemWriterBuilder<AmountDto>()
                .name("amountFileItemWriter")
                .resource(new FileSystemResource(filePath))
                .lineAggregator(lineAggregator)
                .build();
    }

}
