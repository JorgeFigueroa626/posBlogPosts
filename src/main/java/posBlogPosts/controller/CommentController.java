package posBlogPosts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posBlogPosts.exception.CommentNotFoundException;
import posBlogPosts.service.ICommentService;
import posBlogPosts.service.IPostService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private IPostService postService;

    @Autowired
    private ICommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestParam Long postId, @RequestParam String postedBy, @RequestBody String content){
        try {
            return ResponseEntity.ok(commentService.createComment(postId, postedBy, content));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    //@GetMapping("/{commentId}")
    public ResponseEntity<?> getByCommentId(@PathVariable Long commentId){
        try {
            return ResponseEntity.ok(commentService.getByCommentId(commentId));
        }catch (CommentNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getCommentByPostId(@PathVariable Long postId){
        try {
            return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
        }catch (CommentNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllComments(){
        return ResponseEntity.ok(commentService.findAllComments());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteByCommentId(@PathVariable Long commentId){
        commentService.deleteByCommentId(commentId);
        return ResponseEntity.noContent().build();
    }
}
