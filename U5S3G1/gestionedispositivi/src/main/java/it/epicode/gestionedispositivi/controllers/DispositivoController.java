package it.epicode.gestionedispositivi.controllers;
import it.epicode.gestionedispositivi.dtos.DispositivoDto;
import it.epicode.gestionedispositivi.exceptions.BadRequestException;
import it.epicode.gestionedispositivi.exceptions.DispositivoNonTrovatoException;
import it.epicode.gestionedispositivi.models.Dispositivo;
import it.epicode.gestionedispositivi.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping("/api/dispositivi")
    public String saveDispositivo(@RequestBody @Validated DispositivoDto dispositivoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s+2));
        }
        return dispositivoService.saveDispositivo(dispositivoDto);
    }

    @GetMapping("/api/dispositivi/{id}")
    public Dispositivo getDispositivoById(@PathVariable UUID id){
        Optional<Dispositivo> dispositivoOptional = dispositivoService.getDispositivoById(id);
        if(dispositivoOptional.isPresent()){
            return dispositivoOptional.get();
        } else {
            throw new DispositivoNonTrovatoException("Dispositivo con id=" + id + " non trovato");
        }

    }

    @GetMapping("/api/dispositivi/")
    public Page<Dispositivo> getDispositivi(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy){
        return dispositivoService.getAllDispositivi(page, size, sortBy);
    }

    @PutMapping("/api/dispositivi//{id}")
    public Dispositivo updateDispositivo(@PathVariable UUID id, @RequestBody @Validated DispositivoDto dispositivoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return dispositivoService.updateDispositivo(id, dispositivoDto);
    }

    @PutMapping("/api/dispositivi/{dispositivoId}/assegna/{dipendenteId}")
    public String assegnaDispositivo(@PathVariable UUID dispositivoId, @PathVariable UUID dipendenteId){
        return dispositivoService.assegnaDispositivo(dispositivoId, dipendenteId);
    }

    @DeleteMapping("/api/dispositivi/{id}")
    public String deleteDispositivo(@PathVariable UUID id){
        return dispositivoService.deleteDispositivo(id);
    }


}
