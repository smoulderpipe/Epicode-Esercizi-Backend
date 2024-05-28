package it.epicode.gestionedispositivi.controllers;

import it.epicode.gestionedispositivi.dtos.DipendenteDto;
import it.epicode.gestionedispositivi.dtos.DipendenteLoginDto;
import it.epicode.gestionedispositivi.exceptions.BadRequestException;
import it.epicode.gestionedispositivi.services.AuthService;
import it.epicode.gestionedispositivi.services.DipendenteService;
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
    private DipendenteService dipendenteService;

    @PostMapping("/auth/register")
    public String register(@RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s+2));
        }
        return dipendenteService.saveDipendente(dipendenteDto);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated DipendenteLoginDto dipendenteLoginDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage())
                    .reduce("", (s, s2) -> s+s2));
        }
        return authService.authenticateUserAndCreateToken(dipendenteLoginDto);
    }
}
