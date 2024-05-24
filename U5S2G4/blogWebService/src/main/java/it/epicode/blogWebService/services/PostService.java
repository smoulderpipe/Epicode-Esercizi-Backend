package it.epicode.blogWebService.services;
import it.epicode.blogWebService.exceptions.NotFoundException;
import it.epicode.blogWebService.models.*;
import it.epicode.blogWebService.repositories.AutoreRepo;
import it.epicode.blogWebService.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private AutoreRepo autoreRepo;

    private List<Post> posts = new ArrayList<>();

    public void stampaPost(){
        posts.forEach(System.out::println);
    }

    public Page<Post> getAllPosts(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return postRepo.findAll(pageable);
    }

    public Post getPostById(UUID id){
        return postRepo.findById(id).orElseThrow(() -> new NotFoundException("Post non trovato con id " + id));
    }

    public String addPost(CreatePostRequestBody postRequestBody) {
        Autore autoreToFind = autoreRepo.findById(postRequestBody.getAutoreId()).orElseThrow(() ->
                new NotFoundException("Autore con id: " + postRequestBody.getAutoreId() + " non trovato"));

        Post postToAdd = new Post();
        postToAdd.setTitolo(postRequestBody.getTitolo());
        postToAdd.setCategoria(postRequestBody.getCategoria());
        postToAdd.setTempoDiLettura(postRequestBody.getTempoDiLettura());
        postToAdd.setAutore(autoreToFind);
        postToAdd.setCover("https://picsum.photos/200/300");
        postToAdd.setContenuto(postRequestBody.getContenuto());

        postRepo.save(postToAdd);
        return "post creato con successo \n" + postToAdd;
    }

    public String updatePost(EditPostRequestBody postRequestBody, UUID id){
        Autore autoreToFind = autoreRepo.findById(postRequestBody.getAutoreId()).orElseThrow(() ->
                new NotFoundException("Autore con id: " + postRequestBody.getAutoreId() + " non trovato"));

        Post postToUpdate = postRepo.findById(id).orElse(null);
        if(postToUpdate != null) {
            postToUpdate.setContenuto(postRequestBody.getContenuto());
            postToUpdate.setCategoria(postRequestBody.getCategoria());
            postToUpdate.setTempoDiLettura(postRequestBody.getTempoDiLettura());
            postToUpdate.setCover("https://picsum.photos/200/300");
            postToUpdate.setAutore(autoreToFind);
            postToUpdate.setTitolo(postRequestBody.getTitolo());
        }

    }


}


