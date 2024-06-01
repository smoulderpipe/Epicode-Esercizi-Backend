package it.epicode.gestioneEventi.services;

import it.epicode.gestioneEventi.dtos.UtenteLoginDto;
import it.epicode.gestioneEventi.exceptions.UnauthorizedException;
import it.epicode.gestioneEventi.models.Utente;
import it.epicode.gestioneEventi.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUserAndCreateToken(UtenteLoginDto utenteLoginDto){
        Utente utente = utenteService.getUtenteByEmail(utenteLoginDto.getEmail());

        if(passwordEncoder.matches(utenteLoginDto.getPassword(), utente.getPassword())){
            return jwtTool.createToken(utente);
        } else {
            throw new UnauthorizedException("Errore nell'autorizzazione, riprovare il login");
        }
    }
}
