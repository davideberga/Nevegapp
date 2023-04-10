package com.itisegato.nevegapp.GeneralClasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che definisce un punto generico
 */

public class Punto implements Serializable{

    protected String nomePunto;
    protected String descrizione;
    protected int idImmagine;
    protected float altitudine;
    protected int numeroPunto;

    protected CharSequence storia;
    protected CharSequence turismo;
    protected CharSequence fauna;
    protected CharSequence flora;
    protected CharSequence geologia;
    protected CharSequence linkecontatti;
    protected CharSequence varie;
    protected CharSequence descrizioneCompleta;

    public static final String NOME_SEZIONE_STORIA = "storia";
    public static final String NOME_SEZIONE_FAUNA = "fauna";
    public static final String NOME_SEZIONE_FLORA = "flora";
    public static final String NOME_SEZIONE_TURISMO = "turismo";
    public static final String NOME_SEZIONE_GEOLOGIA = "geologia";
    public static final String NOME_SEZIONE_LINK_CONTATTI = "link e contatti";
    public static final String NOME_SEZIONE_VARIE = "varie";
    public static final String NOME_SEZIONE_DESCRIZIONE = "descrizione";

    public String getNomePunto() {
        return nomePunto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getIdImmagine() {
        return idImmagine;
    }

    public float getAltitudine() {
        return altitudine;
    }

    public CharSequence getStoria() {
        return storia;
    }

    public CharSequence getTurismo() {
        return turismo;
    }

    public CharSequence getFauna() {
        return fauna;
    }

    public CharSequence getFlora() {
        return flora;
    }

    public CharSequence getGeologia() {
        return geologia;
    }

    public CharSequence getLinkecontatti() {
        return linkecontatti;
    }

    public CharSequence getVarie() {
        return varie;
    }

    public CharSequence getDescrizioneCompleta() {
        return descrizioneCompleta;
    }

    public Punto(String nomePunto, String descrizione, int idImmagine, float altitudine, CharSequence storia, CharSequence turismo, CharSequence fauna, CharSequence flora, CharSequence geologia, int numeroPunto, CharSequence varie, CharSequence linkecontatti, CharSequence descrizioneCompleta) {
        this.nomePunto = nomePunto;
        this.descrizione = descrizione;
        this.idImmagine = idImmagine;
        this.altitudine = altitudine;
        this.storia = storia;
        this.turismo = turismo;
        this.fauna = fauna;
        this.flora = flora;
        this.geologia = geologia;
        this.numeroPunto = numeroPunto;
        this.linkecontatti = linkecontatti;
        this.varie = varie;
        this.descrizioneCompleta = descrizioneCompleta;

    }

    public int getNumeroPunto() {
        return numeroPunto;
    }

    public ArrayList<Sezione> sezioni(){
        ArrayList<Sezione> sezioni = new ArrayList<>();
        if (descrizioneCompleta!=null){
            sezioni.add(new Sezione(NOME_SEZIONE_DESCRIZIONE, descrizioneCompleta));
        }
        if (storia!=null){
            sezioni.add(new Sezione(NOME_SEZIONE_STORIA,storia));
        }
        if (turismo!=null){
            sezioni.add(new Sezione(NOME_SEZIONE_TURISMO,turismo));
        }
        if (flora!=null){
            sezioni.add(new Sezione(NOME_SEZIONE_FLORA,flora));
        }
        if (fauna!=null){
            sezioni.add(new Sezione(NOME_SEZIONE_FAUNA,fauna));
        }
        if (geologia!=null){
            sezioni.add(new Sezione(NOME_SEZIONE_GEOLOGIA,geologia));
        }
        if (varie!=null){
            sezioni.add(new Sezione(NOME_SEZIONE_VARIE, varie));
        }
        if (linkecontatti!=null){
            sezioni.add(new Sezione(NOME_SEZIONE_LINK_CONTATTI,linkecontatti));
        }
        return sezioni;
    }

}
