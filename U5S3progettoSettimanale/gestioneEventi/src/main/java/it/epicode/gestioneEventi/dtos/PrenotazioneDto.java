package it.epicode.gestioneEventi.dtos;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrenotazioneDto {

    @NotNull
    private int eventoId;
}
