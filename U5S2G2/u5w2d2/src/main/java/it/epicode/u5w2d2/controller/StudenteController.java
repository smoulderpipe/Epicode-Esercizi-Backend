package it.epicode.u5w2d2.controller;

import it.epicode.u5w2d2.exception.StudenteNonTrovatoException;
import it.epicode.u5w2d2.model.Studente;
import it.epicode.u5w2d2.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//con RestController non abbiamo problemi di interpretazione del corpo della risposta
//se invece avessimo usato Controller avremmo avuto problemi di intepretazione risolvibili in altro modo


public class StudenteController {

    @Autowired
    private StudenteService studenteService;

    @PostMapping("/api/studenti")
    public String saveStudente(@RequestBody Studente studente){
        return studenteService.saveStudente(studente);
    }

    public List<Studente> getAllStudenti(){
        return studenteService.getAllStudenti();
    }

    @GetMapping("/api/studenti/{matricola}")
    public Studente getStudenteByMatricola(@PathVariable int matricola) throws StudenteNonTrovatoException{
        Optional<Studente> studenteOpt = studenteService.getStudenteByMatricola(matricola);

        if(studenteOpt.isPresent()){
            return studenteOpt.get();
        } else {
            throw new StudenteNonTrovatoException("Studente con matricola " + matricola + " non trovato.");
        }
    }

    public Studente updateStudente(){

    }
    /*@GetMapping("/api")
    public String benvenuto(){
        return "benvenuto";
    }

    //esempio uso queryparams:
    @GetMapping("/api/nome_cognome")
    public String benvenuto2(@RequestParam String nome, String cognome) {
        return "benvenuto " + nome + " " + cognome;
    }

    @GetMapping("/api/{nome}/{cognome}")
    public String benvenuto3(@PathVariable String nome, @PathVariable String cognome) {
        return "benvenuto " + nome + " " + cognome;
    }

    @GetMapping("/api/body")//@RequestBody fa capire a Spring che i dati deve recuperarli dal body della richiesta
    public String benvenuto4(@RequestBody String nomeCognome){
        return "benvenuto " + nomeCognome;
    }*/
}
