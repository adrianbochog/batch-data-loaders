package candidate.loader.repository;

import candidate.loader.domain.Municipality;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public interface MunicipalityRepository extends CrudRepository<Municipality,String> {
}
