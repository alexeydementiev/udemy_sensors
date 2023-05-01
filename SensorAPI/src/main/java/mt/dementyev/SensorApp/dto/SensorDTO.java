package mt.dementyev.SensorApp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SensorDTO {

  private Long id;
  @NotEmpty(message = "Sensor's name can't be empty")
  @Size(min = 2, max = 30, message = "Name must be 2 - 30 symbols")
  private String name;
}
