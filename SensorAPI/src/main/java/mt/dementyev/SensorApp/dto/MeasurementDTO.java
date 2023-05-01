package mt.dementyev.SensorApp.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mt.dementyev.SensorApp.model.Sensor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeasurementDTO {

  private Long id;

  @DecimalMin(value = "-100", inclusive = false)
  @DecimalMax(value = "100", inclusive = false)
  private BigDecimal value;

  @NotNull
  private Boolean raining;

  @NotNull
  private Sensor sensor;
}