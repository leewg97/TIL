package com.example.demospringbatch.job.player;

import com.example.demospringbatch.core.service.PlayerSalaryService;
import com.example.demospringbatch.dto.PlayerDto;
import com.example.demospringbatch.dto.PlayerSalaryDto;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
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

@Configuration
@AllArgsConstructor
public class FlatFileJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flatFileJob(Step flatFileStep) {
        return jobBuilderFactory.get("flatFileJob")
                .incrementer(new RunIdIncrementer())
                .start(flatFileStep)
                .build();
    }

    @Bean
    @JobScope
    public Step flatFileStep(
            FlatFileItemReader<PlayerDto> playerFileItemReader,
            ItemProcessorAdapter<PlayerDto, PlayerSalaryDto> playerSalaryItemProcessorAdapter,
            FlatFileItemWriter<PlayerSalaryDto> playerFileItemWriter
    ) {
        return stepBuilderFactory.get("flatFileStep")
                .<PlayerDto, PlayerSalaryDto>chunk(5)
                .reader(playerFileItemReader)
                .processor(playerSalaryItemProcessorAdapter)
                .writer(playerFileItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<PlayerSalaryDto> playerFileItemWriter() throws IOException {
        BeanWrapperFieldExtractor<PlayerSalaryDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"ID", "firstName", "lastName", "salary"});  // PlayerSalaryDto ?????? ????????? ?????? ???????????? ?????? ????????? ??????.
        fieldExtractor.afterPropertiesSet();

        DelimitedLineAggregator<PlayerSalaryDto> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter("\t");  // tab ?????? ??????
        lineAggregator.setFieldExtractor(fieldExtractor);

        // ????????? ????????? ????????????.
        new File("player-salary-list.txt").createNewFile();
        FileSystemResource resource = new FileSystemResource("player-salary-list.txt");

        return new FlatFileItemWriterBuilder<PlayerSalaryDto>()
                .name("playerFileItemWriter")
                .resource(resource)
                .lineAggregator(lineAggregator)   // ??? ?????? ????????? ????????? ??? ?????????
                .build();
    }

    /**
     * target object ??? ?????????????????? ?????? ??? ????????? ???????????? ???????????? ???????????? ?????? ??? ????????? ?????? String ?????? ??????
     * ItemProcessor ??? ????????? ???????????? ???????????? ???????????? ?????????
     */
    @Bean
    @StepScope
    public ItemProcessorAdapter<PlayerDto, PlayerSalaryDto> playerSalaryItemProcessorAdapter(PlayerSalaryService playerSalaryService) {
        ItemProcessorAdapter<PlayerDto, PlayerSalaryDto> adapter = new ItemProcessorAdapter<>();
        adapter.setTargetObject(playerSalaryService);
        adapter.setTargetMethod("calcSalary");
        return adapter;
    }

    /**
     * ItemProcessor ??? ????????? ???????????? ???????????? ??????
     */
    @Bean
    @StepScope
    public ItemProcessor<PlayerDto, PlayerSalaryDto> playerSalaryItemProcessor(PlayerSalaryService playerSalaryService) {
        return new ItemProcessor<PlayerDto, PlayerSalaryDto>() {
            @Override
            public PlayerSalaryDto process(PlayerDto item) throws Exception {
                return playerSalaryService.calcSalary(item);
            }
        };
    }

    @Bean
    @StepScope
    public FlatFileItemReader<PlayerDto> playerFileItemReader() {
        return new FlatFileItemReaderBuilder<PlayerDto>()
                .name("playerFileItemReader")
                .lineTokenizer(new DelimitedLineTokenizer())
                .linesToSkip(1) // ?????? ??? ?????? 1?????? ?????? ???????????? ????????? ????????? ????????? ????????? ????????? ??????
                .fieldSetMapper(new PlayerFieldSetMapper())   // line ??? ????????? ????????? ????????? ?????????
                .resource(new FileSystemResource("player-list.txt"))
                .build();
    }

}
