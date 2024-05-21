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
    public ResponseEntity<Post> getSinglePost(@PathVariable int id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Post> createSinglePost(@RequestBody CreatePostRequestBody newPost){
        return new ResponseEntity<>(postService.addPost(newPost), HttpStatus.CREATED);
        //è uguale a usare lo status 201
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable int id){
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.deletePost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

}
