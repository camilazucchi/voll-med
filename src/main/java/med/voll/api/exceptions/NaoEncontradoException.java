package med.voll.api.exceptions;

public class NaoEncontradoException extends RuntimeException {

    public NaoEncontradoException() {
        super("Médico ou paciente não encontrados.");
    }

    public NaoEncontradoException(String message) {
        super(message);
    }

    public NaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

}
