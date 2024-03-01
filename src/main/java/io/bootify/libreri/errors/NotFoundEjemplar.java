package io.bootify.libreri.errors;

public class NotFoundEjemplar extends Exception{

    public NotFoundEjemplar() {
        super("Ejemplar NO encontrado");
    }

    public NotFoundEjemplar(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundEjemplar(Throwable cause) {
        super(cause);
    }


}
