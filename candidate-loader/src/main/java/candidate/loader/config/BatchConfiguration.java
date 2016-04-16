package candidate.loader.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
@Transactional
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public Tasklet loaderTasklet;

    @Autowired
    public Tasklet issueLoaderTasklet;

    @Autowired
    public Tasklet provinceLoaderTasklet;

    @Autowired
    public Tasklet municipalityLoaderTasklet;

    @Autowired
    public Tasklet campaignTrailMapperTasklet;

    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("loadDataJob")
                .incrementer(new RunIdIncrementer())
                .flow(issueLoaderStep())
                .next(provinceLoaderStep())
                .next(municipalityLoaderStep())
                .next(candidatesLoaderStep())
                .next(campaignTrailMapperStep())
                .end()
                .build();
    }

    @Bean
    public Step provinceLoaderStep() {
        return stepBuilderFactory.get("provinceLoaderStep")
                .tasklet(provinceLoaderTasklet)
                .build();
    }

    @Bean
    public Step municipalityLoaderStep() {
        return stepBuilderFactory.get("municipalityLoaderStep")
                .tasklet(municipalityLoaderTasklet)
                .build();
    }

    @Bean
    public Step candidatesLoaderStep() {
        return stepBuilderFactory.get("candidatesLoaderStep")
                .tasklet(loaderTasklet)
                .build();
    }

    @Bean
    public Step issueLoaderStep() {
        return stepBuilderFactory.get("issueLoaderStep")
                .tasklet(issueLoaderTasklet)
                .build();
    }

    @Bean
    public Step campaignTrailMapperStep(){
        return stepBuilderFactory.get("campaignTrailMapperStep")
                .tasklet(campaignTrailMapperTasklet)
                .build();
    }
    // end::jobstep[]
}
