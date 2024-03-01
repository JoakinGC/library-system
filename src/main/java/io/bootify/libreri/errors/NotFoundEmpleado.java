package io.bootify.libreri.errors;

public class NotFoundEmpleado  extends  Exception{

    public  NotFoundEmpleado() {
        super("Empleado NO encontrado");
    }

    public NotFoundEmpleado(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundEmpleado(Throwable cause) {
        super(cause);
    }
}
