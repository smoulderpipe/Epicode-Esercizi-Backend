package it.epicode.blogWebService.services;
import it.epicode.blogWebService.models.CreatePostRequestBody;
import it.epicode.blogWebService.models.EditPostRequestBody;
import it.epicode.blogWebService.models.Post;
import it.epicode.blogWebService.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    private List<Post> posts = new ArrayList<>();

    public void stampaPost(){
        posts.forEach(System.out::println);
    }

    public List<Post> getAllPosts(){
        return this.postRepo.findAll();
    }

    public Post getPostById(UUID id){
        return this.postRepo.findById(id).orElse(null);
    }

    public Post addPost(CreatePostRequestBody postRequestBody) {
        try {
            Post newPost = new Post(
                    postRequestBody.getCategoria(),
                    postRequestBody.getTitolo(),
                    postRequestBody.getCover(),
                    postRequestBody.getContenuto(),
                    postRequestBody.getTempoDiLettura()
            );
            return this.postRepo.save(newPost);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio del post", e);
        }
    }

    public Post updatePost(EditPostRequestBody postRequestBody, UUID id) {
        Post postDaAggiornare = this.postRepo.findById(id).orElse(null);
        if(postDaAggiornare != null) {
            postDaAggiornare.setCategoria(postRequestBody.getCategoria());
            postDaAggiornare.setContenuto(postRequestBody.getContenuto());
            postDaAggiornare.setTitolo(postRequestBody.getTitolo());
            postDaAggiornare.setTempoDiLettura(postRequestBody.getTempoDiLettura());
            postDaAggiornare.setCover(postRequestBody.getCover());
            return postRepo.save(postDaAggiornare);
        }
        return null;
    }

    public void deletePost(UUID id){
        this.postRepo.deleteById(id);
    }

}
