package it.epicode.blogWebService.controller;
import it.epicode.blogWebService.models.Autore;
import it.epicode.blogWebService.models.CreateAutoreRequestBody;
import it.epicode.blogWebService.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/autori")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping("/")
    public ResponseEntity<List<Autore>> getAutori(){
        return new ResponseEntity<>(autoreService.getAllAutori(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autore> getSingleAutore(@PathVariable UUID id){
        Autore autore = autoreService.getAutoreById(id);
        if (autore == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(autore, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Autore> createSingleAutore(@RequestBody CreateAutoreRequestBody newAutore) {
        try {
            Autore createdAutore = autoreService.addAutore(newAutore);
            return new ResponseEntity<>(autoreService.addAutore(newAutore), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAutore(@PathVariable UUID id){
        Autore autore = autoreService.getAutoreById(id);
        if(autore == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        autoreService.deleteAutore(id);
        return new ResponseEntity(autore, HttpStatus.OK);
    }

}
