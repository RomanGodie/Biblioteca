package com.ceiba.biblioteca.dominio.excepcion;

public class ExcepcionDeNegocio extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionDeNegocio(String message) {
        super(message);
    }
}
