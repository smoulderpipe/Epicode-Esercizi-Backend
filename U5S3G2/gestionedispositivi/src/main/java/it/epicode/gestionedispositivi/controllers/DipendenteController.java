package it.epicode.gestionedispositivi.controllers;
import it.epicode.gestionedispositivi.dtos.DipendenteDto;
import it.epicode.gestionedispositivi.exceptions.BadRequestException;
import it.epicode.gestionedispositivi.exceptions.DipendenteNonTrovatoException;
import it.epicode.gestionedispositivi.models.Dipendente;
import it.epicode.gestionedispositivi.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping("/api/dipendenti")
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy){
        return dipendenteService.getAllDipendenti(page, size, sortBy);
    }

    @GetMapping("/api/dipendenti/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Dipendente getDipendenteById(@PathVariable int id){
        return dipendenteService.getDipendenteById(id).orElseThrow(() -> new DipendenteNonTrovatoException("Dipendente con id=" + id + " non trovato"));
    }

    @PutMapping("/api/dipendenti/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return dipendenteService.updateDipendente(id, dipendenteDto);
    }

    @DeleteMapping("/api/dipendenti/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteDipendente(@PathVariable int id){
        return dipendenteService.deleteDipendente(id);
    }

    /*@PostMapping("/{id}/profile-image")
    public String uploadProfileImage(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        return dipendenteService.uploadProfileImage(id, file);
    }*/

}