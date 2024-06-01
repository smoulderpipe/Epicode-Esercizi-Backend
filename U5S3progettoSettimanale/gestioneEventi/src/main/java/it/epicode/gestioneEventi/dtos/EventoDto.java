package it.epicode.gestioneEventi.dtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EventoDto {
    @NotEmpty
    @Size(min=1, max=20)
    private String titolo;

    @NotEmpty
    @Size(min=1, max=300)
    private String descrizione;

    @NotNull
    private LocalDate data;

    @NotEmpty
    @Size(min=1, max=70)
    private String luogo;

    @NotNull
    @Min(1)
    private int numeroPostiDisponibili;

}
