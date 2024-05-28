package it.epicode.gestionedispositivi.services;
import it.epicode.gestionedispositivi.dtos.DispositivoDto;
import it.epicode.gestionedispositivi.exceptions.DipendenteNonTrovatoException;
import it.epicode.gestionedispositivi.exceptions.DispositivoNonTrovatoException;
import it.epicode.gestionedispositivi.models.Dipendente;
import it.epicode.gestionedispositivi.models.Dispositivo;
import it.epicode.gestionedispositivi.repositories.DipendenteRepo;
import it.epicode.gestionedispositivi.repositories.DispositivoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepo dispositivoRepo;

    @Autowired
    private DipendenteRepo dipendenteRepo;

    public Optional<Dispositivo> getDispositivoById(int id){
        return dispositivoRepo.findById(id);
    }

    public Page<Dispositivo> getAllDispositivi(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dispositivoRepo.findAll(pageable);
    }

    public Dispositivo updateDispositivo(int id, DispositivoDto dispositivoDto){
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);
        if(dispositivoOptional.isPresent()) {
            Dispositivo dispositivoDaAggiornare = dispositivoOptional.get();
            dispositivoDaAggiornare.setTipoDispositivo(dispositivoDto.getTipoDispositivo());
            dispositivoDaAggiornare.setStatoDispositivo(dispositivoDto.getStatoDispositivo());

            Integer dipendenteId = dispositivoDto.getDipendenteId();
            if (dipendenteId != null) {
                Optional<Dipendente> dipendenteOptional = dipendenteRepo.findById(dipendenteId);
                if (dipendenteOptional.isPresent()) {
                    dispositivoDaAggiornare.setDipendente(dipendenteOptional.get());
                } else {
                    throw new DipendenteNonTrovatoException("Dipendente con id= " + dipendenteId + " non trovato");
                }
            }

            return dispositivoRepo.save(dispositivoDaAggiornare);
        } else {
            throw new DispositivoNonTrovatoException("Dispositivo con id=" + id + " non trovato");
        }
    }

    public String saveDispositivo(DispositivoDto dispositivoDto){
        Dispositivo dispositivoDaSalvare = new Dispositivo();
        dispositivoDaSalvare.setTipoDispositivo(dispositivoDto.getTipoDispositivo());
        dispositivoDaSalvare.setStatoDispositivo(dispositivoDto.getStatoDispositivo());

        Integer dipendenteId = dispositivoDto.getDipendenteId();
        if (dipendenteId != null) {
            Optional<Dipendente> dipendenteOptional = dipendenteRepo.findById(dipendenteId);
            if(dipendenteOptional.isPresent()) {
                dispositivoDaSalvare.setDipendente(dipendenteOptional.get());
                dispositivoRepo.save(dispositivoDaSalvare);
                return "Dispositivo con id=" + dispositivoDaSalvare.getId() + " salvato correttamente";
            } else {
                throw new DipendenteNonTrovatoException("Dipendente con id=" + dipendenteId + " non trovato");
            }
        } else {
            dispositivoRepo.save(dispositivoDaSalvare);
            return "Dispositivo con id=" + dispositivoDaSalvare.getId() + " salvato correttamente";
        }
    }

    public String deleteDispositivo(int id){
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);
        if(dispositivoOptional.isPresent()){
            dispositivoRepo.delete(dispositivoOptional.get());
            return "Dispositivo con id=" + id + " cancellato con successo";
        }
        else {
            throw new DispositivoNonTrovatoException("Dispositivo con id=" + id + " non trovato");
        }
    }

    public String assegnaDispositivo(int dispositivoId, int dipendenteId){
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(dispositivoId);
        if(dispositivoOptional.isPresent()){
            Optional<Dipendente> dipendenteOptional = dipendenteRepo.findById(dipendenteId);
            if(dipendenteOptional.isPresent()){
                Dispositivo dispositivoDaAssegnare = dispositivoOptional.get();
                dispositivoDaAssegnare.setDipendente(dipendenteOptional.get());
                dispositivoRepo.save(dispositivoDaAssegnare);
                return "Dispositivo con id=" + dispositivoId + " assegnato al dipendente con id=" + dipendenteId + " con successo";
            } else {
                throw new DipendenteNonTrovatoException("Dipendente con id=" + dipendenteId + " non trovato");
            }
        } else {
            throw new DispositivoNonTrovatoException("Dispositivo con id=" + dispositivoId + " non trovato");
        }
    }
}
