package it.epicode.blogWebService.controller;
import it.epicode.blogWebService.models.CreatePostRequestBody;
import it.epicode.blogWebService.models.Post;
import it.epicode.blogWebService.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public ResponseEntity<List<Post>> getPosts(){
        postService.stampaPost();
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatusCode.valueOf(200));
        //è uguale a usare lo status OK (generico)
    }

    //ResponseEntity ritorna un JSON (oppure un XML, se serve) di (in questo caso) Post
    @GetMapping("/{id}")
    public ResponseEntity<Post> getSinglePost(@PathVariable UUID id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Post> createSinglePost(@RequestBody CreatePostRequestBody newPost){
        try {
            Post createdPost = postService.addPost(newPost);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable UUID id){
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.deletePost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

}
