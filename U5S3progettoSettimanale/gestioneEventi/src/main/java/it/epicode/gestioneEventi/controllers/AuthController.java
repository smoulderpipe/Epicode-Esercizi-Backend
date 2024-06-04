package it.epicode.gestioneEventi.controllers;
import it.epicode.gestioneEventi.dtos.UtenteDto;
import it.epicode.gestioneEventi.dtos.UtenteLoginDto;
import it.epicode.gestioneEventi.exceptions.BadRequestException;
import it.epicode.gestioneEventi.services.AuthService;
import it.epicode.gestioneEventi.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/auth/register")
    public String register(@RequestBody @Validated UtenteDto utenteDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s+s2));
        }
        return utenteService.saveUtente(utenteDto);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated UtenteLoginDto utenteLoginDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage())
                    .reduce("", (s, s2) -> s+s2));
        }
        return authService.authenticateUserAndCreateToken(utenteLoginDto);
    }
}
