package mt.dementyev.SensorApp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mt.dementyev.SensorApp.dto.MeasurementDTO;
import mt.dementyev.SensorApp.model.Measurement;
import mt.dementyev.SensorApp.services.MeasurementService;
import mt.dementyev.SensorApp.util.MeasurementNotAddedException;
import mt.dementyev.SensorApp.util.MeasurementValidator;
import mt.dementyev.SensorApp.util.SensorErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurement")
public class MeasurementController {

  private final ModelMapper modelMapper;
  private final MeasurementValidator measurementValidator;
  private final MeasurementService service;


  @GetMapping
  public List<MeasurementDTO> getMeasures() {
    return service.getMeasures().stream().map(p -> modelMapper.map(p, MeasurementDTO.class))
      .collect(Collectors.toList());
  }

  @GetMapping("/rainyDaysCount")
  public Integer countRainyDays() {
    return service.countRainyDays();
  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> registerSensor(
    @RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
    measurementValidator.validate(modelMapper.map(measurementDTO, Measurement.class),
      bindingResult);
    if (bindingResult.hasErrors()) {
      StringBuilder errorMessages = new StringBuilder();
      List<FieldError> errors = bindingResult.getFieldErrors();
      errors.forEach(
        e -> errorMessages.append(e.getField()).append("-").append(e.getDefaultMessage())
          .append(";"));
      throw new MeasurementNotAddedException(errorMessages.toString());
    }

    service.save(modelMapper.map(measurementDTO, Measurement.class));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<SensorErrorResponse> handleException(MeasurementNotAddedException e) {
    SensorErrorResponse response = new SensorErrorResponse(
      e.getMessage(),
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}
