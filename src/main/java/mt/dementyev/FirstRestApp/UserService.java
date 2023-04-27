package mt.dementyev.FirstRestApp;

import mt.dementyev.FirstRestApp.model.Person;
import mt.dementyev.FirstRestApp.util.PersonNotFoundException;

import java.util.List;


public interface UserService {

	List<Person> getUsers();

	Person getUserById(int id) throws PersonNotFoundException;

	void save(Person person);
}
