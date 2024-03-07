package io.bootify.libreri.errors;

public class NotFoundUsuario extends  Exception{

    public NotFoundUsuario() {
        super("Usuario NO encontrado");
    }

    public NotFoundUsuario(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundUsuario(Throwable cause) {
        super(cause);
    }


}
