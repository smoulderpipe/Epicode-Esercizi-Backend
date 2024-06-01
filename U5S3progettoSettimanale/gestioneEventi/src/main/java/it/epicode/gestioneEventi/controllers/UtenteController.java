package it.epicode.gestioneEventi.controllers;
import it.epicode.gestioneEventi.dtos.UtenteDto;
import it.epicode.gestioneEventi.exceptions.BadRequestException;
import it.epicode.gestioneEventi.exceptions.NotFoundException;
import it.epicode.gestioneEventi.models.Utente;
import it.epicode.gestioneEventi.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/api/utenti")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> getUtenti(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy){
        return utenteService.getAllUtenti(page, size, sortBy);
    }

    @GetMapping("/api/utenti/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente getUtenteById(@PathVariable int id){
        return utenteService.getUtenteById(id).orElseThrow(() -> new NotFoundException("Utente con id=" + id + " non trovato"));
    }

    @PutMapping("/api/utenti/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente updateUtente(@PathVariable int id, @RequestBody @Validated UtenteDto utenteDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return utenteService.updateUtente(id, utenteDto);
    }

    @DeleteMapping("/api/utenti/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUtente(@PathVariable int id){
        return utenteService.deleteUtente(id);
    }
}
