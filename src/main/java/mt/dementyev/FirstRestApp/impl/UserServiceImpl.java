package mt.dementyev.FirstRestApp.impl;

import lombok.RequiredArgsConstructor;
import mt.dementyev.FirstRestApp.UserService;
import mt.dementyev.FirstRestApp.model.Person;
import mt.dementyev.FirstRestApp.repository.PersonRepository;
import mt.dementyev.FirstRestApp.util.PersonNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final PersonRepository personRepository;

  @Override
  public List<Person> getUsers() {
    return StreamSupport.stream(personRepository.findAll().spliterator(), false)
      .collect(Collectors.toList());
  }

  @Override
  public Person getUserById(int id) throws PersonNotFoundException {
    Optional<Person> personDto = personRepository.findById(id);

    return personRepository
      .findById(id)
      .orElseThrow(() -> new PersonNotFoundException());
  }

  @Override
  @Transactional
  public void save(Person person) {
    personRepository.save(person);
  }
}