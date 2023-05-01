package mt.dementyev.SensorApp.util;


import static java.util.Objects.nonNull;

import io.micrometer.common.lang.NonNullApi;
import io.micrometer.common.lang.NonNullFields;
import lombok.AllArgsConstructor;
import mt.dementyev.SensorApp.model.Measurement;
import mt.dementyev.SensorApp.repository.SensorRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
@NonNullApi
@NonNullFields
public class MeasurementValidator implements Validator {

  private SensorRepository sensorRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return Measurement.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Measurement measurement = (Measurement) target;
    if (nonNull(measurement.getSensor())) {
      if (sensorRepository.findById(measurement.getSensor().getId().intValue()).isEmpty()) {
        errors.rejectValue("sensor", "", "This sensor with id "
          + measurement.getSensor().getId().toString() + " doesn't present in DB");
      }
    }
  }
}
