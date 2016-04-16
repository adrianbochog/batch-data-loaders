package candidate.loader.tasklet;

import candidate.loader.domain.Candidate;
import candidate.loader.repository.CandidateRepository;
import candidate.loader.web.json.CandidateJson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public class LoaderTasklet implements Tasklet {

    private static final Logger LOGGER = Logger.getLogger(LoaderTasklet.class);

    private static final String key = "zbHqXwiYv";

    private static final String token = "PfDxQQr4EetNv0KESIfR0J9R7WiO";

    private static final String year = "2016";

    private static final String url = "http://api.bilangpilipino.com/api-bilang-pilipino/api/candidates/2016?key=zbHqXwiYv&token=PfDxQQr4EetNv0KESIfR0J9R7WiO";

    private CandidateRepository candidateRepository;

    public LoaderTasklet(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("Candidate Loader Tasklet Init..");
        RestTemplate restTemplate = new RestTemplate();
        List<MediaType> acceptedMediaTypes = new ArrayList<>();
        acceptedMediaTypes.add(MediaType.APPLICATION_JSON);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(acceptedMediaTypes);
        String candidateString = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<CandidateJson> candidates = mapper.readValue(candidateString,
                new TypeReference<List<CandidateJson>>() {
                });

        candidates.stream().filter(c -> {
            if (c.getPosition().equals("President") || c.getPosition().equals("Vice President")) {
                return true;
            } else {
                return false;
            }
        }).forEach(c -> {

            Candidate candidate = new Candidate();
            candidate.setCandidateId(c.getCandidateId());
            candidate.setGivenName(c.getCandidateName());
            candidate.setPosition(c.getPosition());

            candidateRepository.save(candidate);
        });
        return RepeatStatus.FINISHED;
    }
}
