package candidate.loader.tasklet;

import candidate.loader.domain.Candidate;
import candidate.loader.repository.CandidateRepository;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public class LoaderTasklet implements Tasklet {

    private static final Logger LOGGER = Logger.getLogger(LoaderTasklet.class);

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
        return RepeatStatus.FINISHED;
    }
}
