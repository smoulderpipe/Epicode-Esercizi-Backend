package it.epicode.gestionedispositivi.services;
import it.epicode.gestionedispositivi.exceptions.DipendenteNonTrovatoException;
import it.epicode.gestionedispositivi.exceptions.UnauthorizedException;
import it.epicode.gestionedispositivi.models.Dipendente;
import it.epicode.gestionedispositivi.security.JwtTool;
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
    private DipendenteService dipendenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Error in authorization, relogin!");
        }

        String token = authHeader.substring(7);

        jwtTool.verifyToken(token);

        int dipendenteId = jwtTool.getIdFromToken(token);

        Optional<Dipendente> dipendenteOptional = dipendenteService.getDipendenteById(dipendenteId);

        if(dipendenteOptional.isPresent()){
            Dipendente dipendenteUser = dipendenteOptional.get();
            Authentication authentication = new UsernamePasswordAuthenticationToken(dipendenteUser, null, dipendenteUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw new DipendenteNonTrovatoException("Dipendente con id=" + dipendenteId + " non trovato");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
