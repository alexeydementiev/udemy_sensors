package mt.dementyev.FirstRestApp.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PersonDTO {
  @NotEmpty(message = "Name can't be empty")
  @Size(min = 2, max = 30, message = "Name must be 2 - 30 symbols")
  private String name;

  @Min(value = 0, message = "Age must be more 0")
  private int age;

  @NotEmpty(message = "Email can't be empty")
  private String email;

  @NotEmpty(message = "Address can't be empty")
  private String address;
}
