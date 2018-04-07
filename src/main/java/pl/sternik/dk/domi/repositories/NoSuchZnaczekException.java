package pl.sternik.dk.domi.repositories;

public class NoSuchZnaczekException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchZnaczekException(String string) {
        super(string);
    }

    public NoSuchZnaczekException() {
    }


}
