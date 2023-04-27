package mt.dementyev.FirstRestApp.repository;

import mt.dementyev.FirstRestApp.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
