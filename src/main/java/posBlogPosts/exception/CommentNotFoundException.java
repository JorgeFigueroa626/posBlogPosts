package posBlogPosts.exception;

public class CommentNotFoundException extends RuntimeException{

    public CommentNotFoundException() {
    }

    public CommentNotFoundException(String message) {
        super(message);
    }

    public CommentNotFoundException(Long id) {
        this(String.format("No se pudo encontrar el comment con id: %d", id));
    }
}
