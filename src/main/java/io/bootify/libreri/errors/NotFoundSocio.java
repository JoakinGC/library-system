package io.bootify.libreri.errors;

public class NotFoundSocio  extends  Exception{

    public NotFoundSocio() {
        super("Socico NO encontrado");
    }

    public NotFoundSocio(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundSocio(Throwable cause) {
        super(cause);
    }


}
