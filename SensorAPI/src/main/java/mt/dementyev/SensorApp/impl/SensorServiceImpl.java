package mt.dementyev.SensorApp.impl;

import lombok.RequiredArgsConstructor;
import mt.dementyev.SensorApp.model.Sensor;
import mt.dementyev.SensorApp.repository.SensorRepository;
import mt.dementyev.SensorApp.services.SensorService;
import mt.dementyev.SensorApp.util.SensorNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

  private final SensorRepository sensorRepository;

  @Override
  public List<Sensor> getSensors() {
    return StreamSupport.stream(sensorRepository.findAll().spliterator(), false)
      .collect(Collectors.toList());
  }

  @Override
  public Sensor getUserById(int id) throws SensorNotFoundException {
    return sensorRepository.findById(id).orElseThrow(SensorNotFoundException::new);
  }

  @Override
  @Transactional
  public void save(Sensor sensor) {
    sensorRepository.save(sensor);
  }
}