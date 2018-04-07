package pl.sternik.dk.domi.repositories;

public class ZnaczekAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public ZnaczekAlreadyExistsException() {
    }

    public ZnaczekAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ZnaczekAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZnaczekAlreadyExistsException(String message) {
        super(message);
    }

    public ZnaczekAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
