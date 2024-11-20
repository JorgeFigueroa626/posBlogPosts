package posBlogPosts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posBlogPosts.entity.Post;
import posBlogPosts.exception.PostNotFoundException;
import posBlogPosts.service.IPostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private IPostService postService;

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody Post post) {
        try{
            return ResponseEntity.ok(postService.savePost(post));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getByPostId(@PathVariable Long postId) throws PostNotFoundException {
        try {
            return ResponseEntity.ok(postService.getByPostId(postId));
        }catch (PostNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<?> likeByPostId(@PathVariable Long postId) throws PostNotFoundException {
        try {
            postService.likeByPostId(postId);
            return ResponseEntity.ok(new String[]{"Post liked successfully"});
        }catch (PostNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchName(@PathVariable String name){
        try {
            return ResponseEntity.ok(postService.searchName(name));
        }catch (PostNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Post> findAllPosts(){
        return postService.findAllPosts();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deleteByPostId(@PathVariable Long postId) throws PostNotFoundException{
        try{
            postService.deleteByPostId(postId);
            return ResponseEntity.noContent().build();
        }catch (PostNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
