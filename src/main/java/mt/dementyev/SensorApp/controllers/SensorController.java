package mt.dementyev.SensorApp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mt.dementyev.SensorApp.services.SensorService;
import mt.dementyev.SensorApp.dto.SensorDTO;
import mt.dementyev.SensorApp.model.Sensor;
import mt.dementyev.SensorApp.util.SensorErrorResponse;
import mt.dementyev.SensorApp.util.PersonNotCreatedException;
import mt.dementyev.SensorApp.util.SensorNotFoundException;
import mt.dementyev.SensorApp.util.SensorValidator;
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
@RequestMapping("/sensors")
public class SensorController {

  private final SensorService sensorService;
  private final ModelMapper modelMapper;
  private final SensorValidator sensorValidator;

  @GetMapping
  public List<SensorDTO> getSensors() {
    return sensorService.getSensors().stream().map(p -> convertToSensorDTO(p)).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public SensorDTO getPerson(@PathVariable("id") int id) throws SensorNotFoundException {
    SensorDTO sensorDTO = convertToSensorDTO(sensorService.getUserById(id));
    return sensorDTO;
  }

  @PostMapping("/registration")
  public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
      sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);
      if (bindingResult.hasErrors()) {
         StringBuilder errorMessages = new StringBuilder();
         List<FieldError> errors = bindingResult.getFieldErrors();
         errors.forEach(e-> errorMessages.append(e.getField()).append("-").append(e.getDefaultMessage()).append(";"));
         throw new PersonNotCreatedException(errorMessages.toString());
      }

      sensorService.save(convertToSensor(sensorDTO));
      return ResponseEntity.ok(HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e)  {
    SensorErrorResponse response = new SensorErrorResponse(
      "Person with this id wasn't found",
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  private ResponseEntity<SensorErrorResponse> handleException(PersonNotCreatedException e)  {
    SensorErrorResponse response = new SensorErrorResponse(
      e.getMessage(),
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  private Sensor convertToSensor(SensorDTO sensorDTO) {
    return modelMapper.map(sensorDTO, Sensor.class);
  }

  private SensorDTO convertToSensorDTO(Sensor person) {
    return modelMapper.map(person, SensorDTO.class);
  }

}
