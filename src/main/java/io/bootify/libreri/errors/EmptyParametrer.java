package io.bootify.libreri.errors;

public class EmptyParametrer extends Exception{
    private String parametro;

    public EmptyParametrer( String parametro) {
        this.parametro = parametro;
    }

    public EmptyParametrer(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyParametrer(Throwable cause) {
        super(cause);
    }


    @Override
    public String getMessage(){
        String error =  parametro + "   esta VACIO" ;
        return error;
    }
}
