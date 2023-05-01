package mt.dementyev.SensorApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "measurements", schema = "public")
public class Measurement {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "temperature")
  @DecimalMin(value = "-100", inclusive = false)
  @DecimalMax(value = "100", inclusive = false)
  private BigDecimal value;

  @NotNull
  private Boolean raining;

  @OneToOne
  @JoinColumn(name = "sensor_id", referencedColumnName = "id")
  @NotNull
  private Sensor sensor;

  @NotNull
  private LocalDateTime created_at;
}