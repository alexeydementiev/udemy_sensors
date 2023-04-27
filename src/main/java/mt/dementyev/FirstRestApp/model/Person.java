package mt.dementyev.FirstRestApp.model;

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
import lombok.NoArgsConstructor;

  @Data
  @Entity
  @NoArgsConstructor
  @AllArgsConstructor
  @Table(name = "person", schema = "public")
  @Builder
  public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 30, message = "Name must be 2 - 30 symbols")
    private String name;

    @Min(value = 0, message = "Age must be more 0")
    private int age;

    @NotEmpty(message = "Email can't be empty")
    private String email;

    @NotEmpty
    private String address;

    private String username;
    private String password;
    private String role;
}
