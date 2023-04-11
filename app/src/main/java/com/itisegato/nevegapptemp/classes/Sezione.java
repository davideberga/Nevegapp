package com.itisegato.nevegapptemp.classes;

/**
 * Created by Davide on 12/02/2018.
 * Sezione (tematica)
 */

public class Sezione {

    protected String nomeSezione;
    protected CharSequence contenutoSezione;

    public Sezione(String nomeSezione, CharSequence contenutoSezione) {
        this.nomeSezione = nomeSezione;
        this.contenutoSezione = contenutoSezione;
    }

    public String getNomeSezione() {
        return nomeSezione;
    }

    public CharSequence getContenutoSezione() {
        return contenutoSezione;
    }
}
