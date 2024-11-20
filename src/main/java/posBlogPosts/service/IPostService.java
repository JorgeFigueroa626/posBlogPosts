package posBlogPosts.service;

import org.springframework.beans.factory.annotation.Autowired;
import posBlogPosts.entity.Post;
import posBlogPosts.repository.PostRepository;

import java.util.List;

public interface IPostService {

    Post savePost(Post post);

    Post getByPostId(Long id);

    Post likeByPostId(Long id);

    List<Post> findAllPosts();

    List<Post> searchName(String name);

    void deleteByPostId(Long id);

}
