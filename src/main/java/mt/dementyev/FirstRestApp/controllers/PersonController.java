package mt.dementyev.FirstRestApp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mt.dementyev.FirstRestApp.UserService;
import mt.dementyev.FirstRestApp.dto.PersonDTO;
import mt.dementyev.FirstRestApp.model.Person;
import mt.dementyev.FirstRestApp.util.PersonErrorResponse;
import mt.dementyev.FirstRestApp.util.PersonNotCreatedException;
import mt.dementyev.FirstRestApp.util.PersonNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController {

  private final UserService userService;
  private final ModelMapper modelMapper;

  @GetMapping
  public List<PersonDTO> getPeople() {
    List<Person> personList = userService.getUsers();
    List<PersonDTO> personDTOList = personList.stream().map(p -> convertToPersonDTO(p)).collect(Collectors.toList());
    return personList.stream().map(p -> convertToPersonDTO(p)).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public PersonDTO getPerson(@PathVariable("id") int id) throws PersonNotFoundException {
    PersonDTO personDTO = convertToPersonDTO(userService.getUserById(id));
    return personDTO;
  }

  @PostMapping
  public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
         StringBuilder errorMessages = new StringBuilder();
         List<FieldError> errors = bindingResult.getFieldErrors();
         errors.forEach(e-> errorMessages.append(e.getField()).append("-").append(e.getDefaultMessage()).append(";"));
         throw new PersonNotCreatedException(errorMessages.toString());
      }

      userService.save(converteToPerson(personDTO));
      return ResponseEntity.ok(HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e)  {
    PersonErrorResponse response = new PersonErrorResponse(
      "Person with this id wasn't found",
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e)  {
    PersonErrorResponse response = new PersonErrorResponse(
      e.getMessage(),
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  private Person converteToPerson(PersonDTO personDTO) {
    return modelMapper.map(personDTO, Person.class);
  }

  private PersonDTO convertToPersonDTO(Person person) {
    return modelMapper.map(person, PersonDTO.class);
  }

}
