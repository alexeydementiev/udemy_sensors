package mt.dementyev.SensorApp.util;


import io.micrometer.common.lang.NonNullApi;
import io.micrometer.common.lang.NonNullFields;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mt.dementyev.SensorApp.model.Sensor;
import mt.dementyev.SensorApp.repository.SensorRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Getter
@Setter
@Component
@NonNullApi
@NonNullFields
public class SensorValidator implements Validator {

  private SensorRepository sensorRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return Sensor.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Sensor person = (Sensor) target;
    if (sensorRepository.findByName(person.getName()).isPresent()) {
      errors.rejectValue("name", "", "This sensor's name is already used");
    }
  }
}
