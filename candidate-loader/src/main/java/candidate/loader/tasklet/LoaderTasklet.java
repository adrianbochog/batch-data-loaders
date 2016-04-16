package candidate.loader.tasklet;

import candidate.loader.domain.Candidate;
import candidate.loader.repository.CandidateRepository;
import candidate.loader.web.json.CandidateJson;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.http.*;
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

    public LoaderTasklet(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("Tasklet Ran Successfuly");
        /*
        Candidate candidate = new Candidate();
        candidate.setCandidateId("1");
        candidate.setGivenName("Orvyl");
        candidate.setLastName("Tumaneng");
        candidate.setPosition(Candidate.Position.PRESIDENT);
        candidateRepository.save(candidate);
        */

        RestTemplate restTemplate = new RestTemplate();
//        //restTemplate.exchange("http://my-rest-url.org/rest/account/{account}?name={name}", HttpMethod.GET, httpEntity, clazz, "my-account", "my-name")
//        restTemplate.exchange("http://api.bilangpilipino.com/api-bilang-pilipino/api/candidates/{year}?key={key}&token={token}",
//                HttpMethod.GET,
//                CandidateJson.class,
//                year,
//                key,
//                token);
        List<MediaType> acceptedMediaTypes = new ArrayList<>();
        acceptedMediaTypes.add(MediaType.APPLICATION_JSON);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(acceptedMediaTypes);
//        ResponseEntity<CandidateJson[]> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(httpHeaders),
//                CandidateJson[].class);
        CandidateJson[] candidates = restTemplate.getForObject(url,CandidateJson[].class);
        //CandidateJson[] candidates = responseEntity.getBody();
        int count = 0;
        while (count < 0 ){
            LOGGER.info(candidates[count].getCandidateName());
            count++;
        }
//        CandidateJson candidateJson = restTemplate.getForObject(url, CandidateJson.class);
//        LOGGER.info(candidateJson);

        LOGGER.info("GET call Success");
        return RepeatStatus.FINISHED;
    }
}
