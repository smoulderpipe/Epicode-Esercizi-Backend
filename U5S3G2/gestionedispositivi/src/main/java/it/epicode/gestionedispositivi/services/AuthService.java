package it.epicode.gestionedispositivi.services;
import it.epicode.gestionedispositivi.dtos.DipendenteLoginDto;
import it.epicode.gestionedispositivi.exceptions.UnauthorizedException;
import it.epicode.gestionedispositivi.models.Dipendente;
import it.epicode.gestionedispositivi.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUserAndCreateToken(DipendenteLoginDto dipendenteLoginDto){
        Dipendente dipendente = dipendenteService.getDipendenteByEmail(dipendenteLoginDto.getEmail());

        if(passwordEncoder.matches(dipendenteLoginDto.getPassword(), dipendente.getPassword())){
            return jwtTool.createToken(dipendente);
        } else {
            throw new UnauthorizedException("Error in authorization, relogin!");
        }
    }
}
