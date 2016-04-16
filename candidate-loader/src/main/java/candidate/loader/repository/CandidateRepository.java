package candidate.loader.repository;

import candidate.loader.domain.Candidate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public interface CandidateRepository extends CrudRepository<Candidate, String> {

}