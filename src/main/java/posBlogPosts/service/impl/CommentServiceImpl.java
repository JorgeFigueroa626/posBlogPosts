package posBlogPosts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import posBlogPosts.entity.Comment;
import posBlogPosts.entity.Post;
import posBlogPosts.exception.CommentNotFoundException;
import posBlogPosts.exception.PostNotFoundException;
import posBlogPosts.repository.CommentRepository;
import posBlogPosts.repository.PostRepository;
import posBlogPosts.service.ICommentService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Long postId, String postedBy, String content) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Comment comment = new Comment();

            comment.setPost(optionalPost.get());
            comment.setContent(content);
            comment.setPostedBy(postedBy);
            comment.setCreateAt(new Timestamp(System.currentTimeMillis()));
            return commentRepository.save(comment);
        }
        throw new PostNotFoundException("Post not found");
    }

    @Override
    public Comment getByCommentId(Long id) throws CommentNotFoundException {
        Comment commentId = commentRepository.findById(id).orElseThrow(()-> new CommentNotFoundException(id));
        return commentId;
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteByCommentId(Long id) {
        Comment commentId = commentRepository.findById(id).orElseThrow(()-> new CommentNotFoundException(id));
        commentRepository.deleteById(commentId.getId());
    }
}
