package mt.dementyev.SensorApp.impl;

import lombok.RequiredArgsConstructor;
import mt.dementyev.SensorApp.model.Measurement;
import mt.dementyev.SensorApp.repository.MeasurementRepository;
import mt.dementyev.SensorApp.services.MeasurementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

  private final MeasurementRepository measurementRepository;

  @Override
  public List<Measurement> getMeasures() {
    return StreamSupport.stream(measurementRepository.findAll().spliterator(), false)
      .collect(Collectors.toList());
  }

  @Override
  public Integer countRainyDays() {
    List<Measurement> list = measurementRepository.findAllByRaining(true)
      .orElse(Collections.EMPTY_LIST);
    Set<LocalDate> uniqueRainyDates = list.stream()
      .map(measurement -> measurement.getCreated_at().toLocalDate()
      ).collect(Collectors.toSet());
    return uniqueRainyDates.size();
  }

  @Override
  @Transactional
  public void save(Measurement measurement) {
    measurement.setCreated_at(LocalDateTime.now());
    measurementRepository.save(measurement);
  }
}