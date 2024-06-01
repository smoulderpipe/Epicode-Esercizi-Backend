package it.epicode.gestioneEventi.controllers;
import it.epicode.gestioneEventi.dtos.PrenotazioneDto;
import it.epicode.gestioneEventi.exceptions.BadRequestException;
import it.epicode.gestioneEventi.exceptions.NotFoundException;
import it.epicode.gestioneEventi.models.Prenotazione;
import it.epicode.gestioneEventi.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping("/api/prenotazioni")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZZATORE_EVENTI')")
    public Page<Prenotazione> getPrenotazioni(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String sortBy){
        return prenotazioneService.getAllPrenotazioni(page, size, sortBy);
    }

    @GetMapping("/api/prenotazioni/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZZATORE_EVENTI')")
    public Prenotazione getPrenotazioneById(@PathVariable int id){
        return prenotazioneService.getPrenotazioneById(id).orElseThrow(() -> new NotFoundException("Prenotazione con id=" + id + " non trovato"));
    }

    @GetMapping("/api/prenotazioni/utente/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZZATORE_EVENTI', 'UTENTE_NORMALE')")
    public List<Prenotazione> getPrenotazioniUtente(@PathVariable int userId) {
        return prenotazioneService.getPrenotazioniByUtente(userId);
    }

    @PostMapping("/api/prenotazioni")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'UTENTE_NORMALE')")
    public String savePrenotazione(@RequestBody @Validated PrenotazioneDto prenotazioneDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s+2));
        }
        return prenotazioneService.savePrenotazione(prenotazioneDto);
    }

    @PutMapping("/api/prenotazioni/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'UTENTE_NORMALE')")
    public Prenotazione updatePrenotazione(@PathVariable int id, @RequestBody @Validated PrenotazioneDto prenotazioneDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return prenotazioneService.updatePrenotazione(id, prenotazioneDto);
    }

    @DeleteMapping("/api/prenotazioni/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'UTENTE_NORMALE')")
    public String deletePrenotazione(@PathVariable int id){
        return prenotazioneService.deletePrenotazione(id);
    }

}
