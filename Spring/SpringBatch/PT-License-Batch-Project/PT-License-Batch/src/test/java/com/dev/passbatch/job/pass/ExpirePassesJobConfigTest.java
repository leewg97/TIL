package com.dev.passbatch.job.pass;

import com.dev.passbatch.config.TestBatchConfig;
import com.dev.passbatch.repository.pass.PassStatus;
import com.dev.passbatch.repository.pass.PassEntity;
import com.dev.passbatch.repository.pass.PassRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBatchTest
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {ExpirePassesJobConfig.class, TestBatchConfig.class})
public class ExpirePassesJobConfigTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private PassRepository passRepository;

    @Test
    public void test_expirePassesStep() throws Exception {
        // given
        addPassEntities(10);

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance jobInstance = jobExecution.getJobInstance();

        // then
        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        assertEquals("expirePassesJob", jobInstance.getJobName());

    }

    private void addPassEntities(int size) {
        final LocalDateTime now = LocalDateTime.now();
        final Random random = new Random();

        List<PassEntity> passEntityEntities = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            PassEntity PassEntity = new PassEntity();
            PassEntity.setPackageSeq(1);
            PassEntity.setUserId("A" + 1000000 + i);
            PassEntity.setStatus(PassStatus.IN_PROGRESS);
            PassEntity.setRemainingCount(random.nextInt(11));
            PassEntity.setStartedAt(now.minusDays(60));
            PassEntity.setEndedAt(now.minusDays(1));
            passEntityEntities.add(PassEntity);

        }
        passRepository.saveAll(passEntityEntities);

    }

}