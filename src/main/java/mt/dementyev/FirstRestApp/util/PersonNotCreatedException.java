package mt.dementyev.FirstRestApp.util;

public class PersonNotCreatedException extends RuntimeException {
  public PersonNotCreatedException(String msg) {
    super(msg);
  }
}
