package candidate.loader;

import candidate.loader.domain.Candidate;
import candidate.loader.repository.CandidateRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Adrian Perez on 4/16/16.
 */

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = { "candidate.loader.repository" }
)
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
