package it.epicode.u5w2d2.service;
import it.epicode.u5w2d2.exception.StudenteNonTrovatoException;
import it.epicode.u5w2d2.model.Studente;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudenteService {

    private List<Studente> studenti = new ArrayList<>();

    public Optional<Studente> getStudenteByMatricola(int matricola){
        return studenti.stream().filter(studente -> studente.getMatricola()==matricola).findFirst();
    }

    public List<Studente> getAllStudenti(){
        return studenti;
    }

    public String saveStudente(Studente studente){
        studenti.add(studente);
        return "Studente creato con questa matricola: " + studente.getMatricola();
    }

    public Studente updateStudente(int matricola, Studente studenteUpd) throws StudenteNonTrovatoException {
        Optional<Studente> studenteOpt = getStudenteByMatricola(matricola);
        if(studenteOpt.isPresent()){
            Studente studente = studenteOpt.get();
            studente.setNome(studenteUpd.getNome());
            studente.setCognome(studenteUpd.getCognome());
            studente.setDataNascita(studenteUpd.getDataNascita());
            return studente;
        }
        else {
            throw new StudenteNonTrovatoException("Lo studente cercato non esiste.");
        }
    }

    public String deleteStudente(int matricola){
        Optional<Studente> studenteOpt = getStudenteByMatricola(matricola);
        if(studenteOpt.isPresent()){
            studenti.remove(studenteOpt.get());
            return "Studente cancellato";
        } else {
            return "ciao";
        }
    }
}
