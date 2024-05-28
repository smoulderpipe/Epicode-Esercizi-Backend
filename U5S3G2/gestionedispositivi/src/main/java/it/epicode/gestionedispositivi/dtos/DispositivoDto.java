package it.epicode.gestionedispositivi.dtos;
import it.epicode.gestionedispositivi.enums.StatoDispositivo;
import it.epicode.gestionedispositivi.enums.TipoDispositivo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class DispositivoDto {

    @NotNull(message = "Il tipo dispositivo non può essere null")
    private TipoDispositivo tipoDispositivo;

    @NotNull(message = "Lo stato dispositivo non può essere null")
    private StatoDispositivo statoDispositivo;

    private Integer dipendenteId;

}
