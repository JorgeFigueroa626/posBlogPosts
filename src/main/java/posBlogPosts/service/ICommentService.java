package posBlogPosts.service;

import posBlogPosts.entity.Comment;

import java.util.List;

public interface ICommentService {

    Comment createComment(Long postId, String postedBy, String content);

    Comment getByCommentId(Long id);

    List<Comment> getCommentsByPostId(Long postId);

    List<Comment> findAllComments();

    void deleteByCommentId(Long id);
}
