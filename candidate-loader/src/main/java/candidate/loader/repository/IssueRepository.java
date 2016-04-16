package candidate.loader.repository;

import candidate.loader.domain.Issue;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public interface IssueRepository extends CrudRepository<Issue,String> {
}
