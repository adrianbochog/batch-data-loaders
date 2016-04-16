package candidate.loader.tasklet;

import candidate.loader.domain.Municipality;
import candidate.loader.domain.Province;
import candidate.loader.repository.MunicipalityRepository;
import candidate.loader.repository.ProvinceRepository;
import candidate.loader.web.json.MunicipalityJson;
import candidate.loader.web.json.ProvinceJson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class MunicipalityLoaderTasklet implements Tasklet {

    private static final Logger LOGGER = Logger.getLogger(MunicipalityLoaderTasklet.class);

    private MunicipalityRepository municipalityRepository;

    private ProvinceRepository provinceRepository;

    private static final String url = "http://api.bilangpilipino.com/api-bilang-pilipino/api/municipalities/2016?key=zbHqXwiYv&token=PfDxQQr4EetNv0KESIfR0J9R7WiO";

    public MunicipalityLoaderTasklet(MunicipalityRepository municipalityRepository, ProvinceRepository provinceRepository){
        this.municipalityRepository  = municipalityRepository;
        this.provinceRepository = provinceRepository;
    }
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        LOGGER.info("Municipality Loader Tasklet Init..");

        RestTemplate restTemplate = new RestTemplate();
        String responseString = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<MunicipalityJson> municipalities = mapper.readValue(responseString,
                new TypeReference<List<MunicipalityJson>>() {
                });

        municipalities.stream().forEach(m -> {
            Municipality municipality = new Municipality();
            municipality.setName(m.getName());
            municipality.setMunicipalityId(m.getmId());
            municipality.setProvince(provinceRepository.findOne(m.getpId()));

            municipalityRepository.save(municipality);
        });


        return RepeatStatus.FINISHED;
    }
}
