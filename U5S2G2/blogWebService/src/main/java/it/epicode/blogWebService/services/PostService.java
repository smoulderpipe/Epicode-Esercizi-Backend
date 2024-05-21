package it.epicode.blogWebService.services;
import it.epicode.blogWebService.models.CreatePostRequestBody;
import it.epicode.blogWebService.models.EditPostRequestBody;
import it.epicode.blogWebService.models.Post;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private List<Post> posts = new ArrayList<>();

    public void stampaPost(){
        posts.forEach(System.out::println);
    }

    public List<Post> getAllPosts(){
        return this.posts;
    }

    public Post getPostById(int id){
        return this.posts.stream().filter(post -> post.getId() == id).findFirst().orElse(null);
    }

    public Post addPost(CreatePostRequestBody postRequestBody) {
        Post newPost = new Post(
                postRequestBody.getCategoria(),
                postRequestBody.getTitolo(),
                postRequestBody.getCover(),
                postRequestBody.getContenuto(),
                postRequestBody.getTempoDiLettura()
        );
        posts.add(newPost);
        return newPost;
    }

    public Post updatePost(EditPostRequestBody postRequestBody, int id) {
        Post postDaAggiornare = this.posts.stream().filter(post -> post.getId() == id).findFirst().orElse(null);
        postDaAggiornare.setCategoria(postRequestBody.getCategoria());
        postDaAggiornare.setContenuto(postRequestBody.getContenuto());
        postDaAggiornare.setTitolo(postRequestBody.getTitolo());
        postDaAggiornare.setTempoDiLettura(postRequestBody.getTempoDiLettura());
        postDaAggiornare.setCover(postRequestBody.getCover());
        return postDaAggiornare;
    }

    public void deletePost(int id){
        Post postDaEliminare = this.posts.stream().filter(post -> post.getId() == id).findFirst().orElse(null);
        this.posts.remove(postDaEliminare);
    }

}
