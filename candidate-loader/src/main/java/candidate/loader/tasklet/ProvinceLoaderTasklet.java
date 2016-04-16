package candidate.loader.tasklet;

import candidate.loader.domain.Province;
import candidate.loader.repository.ProvinceRepository;
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
public class ProvinceLoaderTasklet implements Tasklet {

    private static final Logger LOGGER = Logger.getLogger(ProvinceLoaderTasklet.class);

    private static final String url = "http://api.bilangpilipino.com/api-bilang-pilipino/api/provinces/2016?key=zbHqXwiYv&token=PfDxQQr4EetNv0KESIfR0J9R7WiO";

    private ProvinceRepository provinceRepository;

    public ProvinceLoaderTasklet(ProvinceRepository provinceRepository){
        this.provinceRepository = provinceRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        LOGGER.info("Province Loader Tasklet Init..");

        RestTemplate restTemplate = new RestTemplate();
        String responseString = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<ProvinceJson> provinces = mapper.readValue(responseString,
                new TypeReference<List<ProvinceJson>>() {
                });

        provinces.stream().forEach(p -> {
            Province province = new Province();
            province.setName(p.getName());
            province.setProvinceId(p.getId());
            province.setRegion(p.getRegion());
            provinceRepository.save(province);
        });

        return RepeatStatus.FINISHED;
    }
}
