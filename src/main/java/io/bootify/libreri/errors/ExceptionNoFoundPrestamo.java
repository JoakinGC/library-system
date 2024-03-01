package io.bootify.libreri.errors;

public class ExceptionNoFoundPrestamo extends Exception {

    public ExceptionNoFoundPrestamo() {
        super();
    }

    public ExceptionNoFoundPrestamo(String message) {
        super(message);
    }

    public ExceptionNoFoundPrestamo(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionNoFoundPrestamo(Throwable cause) {
        super(cause);
    }
}

