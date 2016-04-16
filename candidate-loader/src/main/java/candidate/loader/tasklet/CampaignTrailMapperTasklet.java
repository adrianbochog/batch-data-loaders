package candidate.loader.tasklet;

import candidate.loader.domain.Candidate;
import candidate.loader.domain.Municipality;
import candidate.loader.repository.CandidateRepository;
import candidate.loader.repository.MunicipalityRepository;
import candidate.loader.web.json.CampaignTrailJson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.IteratorUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public class CampaignTrailMapperTasklet implements Tasklet {

    private static final Logger LOGGER = Logger.getLogger(IssueLoaderTasklet.class);

    private MunicipalityRepository municipalityRepository;

    private CandidateRepository candidateRepository;

    private static final String url = "http://api.bilangpilipino.com/api-bilang-pilipino/api/campaign-trail/";

    private static final String keyAndToken = "?key=zbHqXwiYv&token=PfDxQQr4EetNv0KESIfR0J9R7WiO";

    public CampaignTrailMapperTasklet(CandidateRepository candidateRepository, MunicipalityRepository municipalityRepository) {
        this.candidateRepository = candidateRepository;
        this.municipalityRepository = municipalityRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("Starting campagin trail mapping");
        List<Candidate> candidates = IteratorUtils.toList(candidateRepository.findAll().iterator());
        candidates.stream().forEach(c -> {
            LOGGER.info("mapping for candidate: " + c.getGivenName());
            RestTemplate restTemplate = new RestTemplate();
            String responseString = restTemplate.getForObject(url+c.getCandidateId()+keyAndToken, String.class);
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<CampaignTrailJson> campaignTrails = mapper.readValue(responseString,
                        new TypeReference<List<CampaignTrailJson>>() {
                        });
                campaignTrails.stream().forEach(m -> {
                    Municipality municipality = municipalityRepository.findOne(m.getMunicipalityId());
                    c.addMunicipality(municipality);
                    municipality.addVisitor(c);

                    municipalityRepository.save(municipality);
                    candidateRepository.save(c);
                });
            } catch (Exception e) {

            }
        });


        return RepeatStatus.FINISHED;
    }
}
