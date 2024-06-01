package it.epicode.gestioneEventi.security;
import it.epicode.gestioneEventi.exceptions.NotFoundException;
import it.epicode.gestioneEventi.exceptions.UnauthorizedException;
import it.epicode.gestioneEventi.models.Utente;
import it.epicode.gestioneEventi.services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private UtenteService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Error in authorization, relogin!");
        }

        String token = authHeader.substring(7);

        jwtTool.verifyToken(token);

        int utenteId = jwtTool.getIdFromToken(token);

        Optional<Utente> utenteOptional = utenteService.getUtenteById(utenteId);

        if(utenteOptional.isPresent()){
            Utente utenteUser = utenteOptional.get();
            Authentication authentication = new UsernamePasswordAuthenticationToken(utenteUser, null, utenteUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw new NotFoundException("Utente con id=" + utenteId + " non trovato");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
