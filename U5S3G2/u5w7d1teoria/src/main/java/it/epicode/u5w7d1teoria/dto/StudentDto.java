package it.epicode.u5w7d1teoria.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private LocalDate birthDate;
}
