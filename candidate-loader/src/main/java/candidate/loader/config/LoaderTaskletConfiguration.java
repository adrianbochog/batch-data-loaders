package candidate.loader.config;

import candidate.loader.repository.CandidateRepository;
import candidate.loader.repository.IssueRepository;
import candidate.loader.repository.MunicipalityRepository;
import candidate.loader.repository.ProvinceRepository;
import candidate.loader.tasklet.*;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.Task;

/**
 * Created by Adrian Perez on 4/16/16.
 */
@Configuration
public class LoaderTaskletConfiguration {

    @Autowired
    public CandidateRepository candidateRepository;

    @Autowired
    public IssueRepository issueRepository;

    @Autowired
    public ProvinceRepository provinceRepository;

    @Autowired
    public MunicipalityRepository municipalityRepository;

    @Bean
    public Tasklet loaderTasklet(){
        return new LoaderTasklet(candidateRepository);
    }

    @Bean
    public Tasklet issueLoaderTasklet(){ return new IssueLoaderTasklet(issueRepository);}

    @Bean
    public Tasklet provinceLoaderTasklet(){
        return new ProvinceLoaderTasklet(provinceRepository);
    }

    @Bean
    public Tasklet municipalityLoaderTasklet(){
        return new MunicipalityLoaderTasklet(municipalityRepository,provinceRepository);
    }

    @Bean
    public Tasklet campaignTrailMapperTasklet(){
        return new CampaignTrailMapperTasklet(candidateRepository,municipalityRepository);
    }

}
