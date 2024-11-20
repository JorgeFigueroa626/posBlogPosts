package posBlogPosts.exception;

public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException() {
    }

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(Long id) {
        this(String.format("No se pudo encontrar el Post con id: %d", id));
    }
}
