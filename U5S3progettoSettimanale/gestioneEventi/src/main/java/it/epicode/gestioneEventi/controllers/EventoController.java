package it.epicode.gestioneEventi.controllers;
import it.epicode.gestioneEventi.dtos.EventoDto;
import it.epicode.gestioneEventi.exceptions.BadRequestException;
import it.epicode.gestioneEventi.exceptions.NotFoundException;
import it.epicode.gestioneEventi.models.Evento;
import it.epicode.gestioneEventi.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/api/eventi")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE_EVENTI', 'UTENTE_NORMALE')")
    public Page<Evento> getEventi(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy){
        return eventoService.getAllEventi(page, size, sortBy);
    }

    @GetMapping("/api/eventi/{id}")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE_EVENTI', 'UTENTE_NORMALE')")
    public Evento getEventoById(@PathVariable int id){
        return eventoService.getEventoById(id).orElseThrow(() -> new NotFoundException("Evento con id=" + id + " non trovato"));
    }

    @PostMapping("/api/eventi")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public String saveEvento(@RequestBody @Validated EventoDto eventoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s+s2));
        }
        return eventoService.saveEvento(eventoDto);
    }

    @PutMapping("/api/eventi/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public Evento updateEvento(@PathVariable int id, @RequestBody @Validated EventoDto eventoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return eventoService.updateEvento(id, eventoDto);
    }

    @DeleteMapping("/api/eventi/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public String deleteEvento(@PathVariable int id){
        return eventoService.deleteEvento(id);
    }
}
