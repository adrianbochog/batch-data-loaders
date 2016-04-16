package candidate.loader.config;

import candidate.loader.repository.CandidateRepository;
import candidate.loader.tasklet.LoaderTasklet;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Adrian Perez on 4/16/16.
 */
@Configuration
public class LoaderTaskletConfiguration {

    @Autowired
    public CandidateRepository candidateRepository;

    @Bean
    public Tasklet loaderTasklet(){
        return new LoaderTasklet(candidateRepository);
    }
}
