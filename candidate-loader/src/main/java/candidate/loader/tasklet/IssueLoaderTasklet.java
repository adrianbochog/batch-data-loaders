package candidate.loader.tasklet;

import candidate.loader.domain.Candidate;
import candidate.loader.domain.Issue;
import candidate.loader.repository.IssueRepository;
import candidate.loader.web.json.CandidateJson;
import candidate.loader.web.json.IssueJson;
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
public class IssueLoaderTasklet implements Tasklet {

    private static final Logger LOGGER = Logger.getLogger(IssueLoaderTasklet.class);

    private IssueRepository issueRepository;

    private static final String url = "http://api.bilangpilipino.com/api-bilang-pilipino/api/issues?key=zbHqXwiYv&token=PfDxQQr4EetNv0KESIfR0J9R7WiO";

    public IssueLoaderTasklet(IssueRepository issueRepository){
        this.issueRepository = issueRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        LOGGER.info("Issue Loader Tasklet Init..");


        RestTemplate restTemplate = new RestTemplate();
        String candidateString = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<IssueJson> issues = mapper.readValue(candidateString,
                new TypeReference<List<IssueJson>>() {
                });

        issues.stream().forEach(i -> {
            Issue issue = new Issue();
            issue.setIssue(i.getIssue());
            issue.setIssueId(i.getId());
            issue.setShortname(i.getShortname());

            issueRepository.save(issue);
        });

        return RepeatStatus.FINISHED;
    }
}
