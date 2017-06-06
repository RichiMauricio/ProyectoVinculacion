package com.vinculacion.jpa.exceptions;

/**
 * Created by Mauricio on 05/06/2017.
 */
public class EstablecimientoNotFoundException extends Exception {

    private String msg;

    public EstablecimientoNotFoundException() {
        super();
    }

    public EstablecimientoNotFoundException(String msg) {
        this.msg = System.currentTimeMillis()
                + ": " + msg;
    }

    public String getMsg() {
        return msg;
    }

}
