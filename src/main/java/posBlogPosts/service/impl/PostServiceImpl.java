package posBlogPosts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import posBlogPosts.entity.Post;
import posBlogPosts.exception.PostNotFoundException;
import posBlogPosts.repository.PostRepository;
import posBlogPosts.service.IPostService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public Post savePost(Post post) {
        post.setLikeCount(0);
        post.setViewCount(0);
        post.setDate(new Timestamp(System.currentTimeMillis()));
        return postRepository.save(post);
    }

    @Override
    public Post getByPostId(Long id) throws PostNotFoundException{
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setViewCount(post.getViewCount() +1);
            return postRepository.save(post);
        }else {
            throw new PostNotFoundException("Post not found");
        }

    }

    @Override
    public Post likeByPostId(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setLikeCount(post.getLikeCount() + 1);
            return postRepository.save(post);
        }else {
            throw  new PostNotFoundException("Post not found with id : " + id);
        }
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> searchName(String name) {
        return postRepository.findAllByNameContaining(name);
    }

    @Override
    public void deleteByPostId(Long id) throws PostNotFoundException {
        Post postId = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException(id));
        postRepository.deleteById(postId.getId());
    }
}
