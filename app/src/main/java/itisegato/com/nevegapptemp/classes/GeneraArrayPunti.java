package itisegato.com.nevegapptemp.classes;
import android.content.Context;

import itisegato.com.nevegapptemp.R;


/**
 * Created by Davide on 07/02/2018.
 * Classe che crea un array statico con i dati
 */

public class GeneraArrayPunti {

    private Context contesto;

    private static Punto[] PUNTI;

    public GeneraArrayPunti(Context contesto) {
        this.contesto = contesto;
        PUNTI = carica();

    }

    private Punto[] carica(){
        Punto[] punti = new Punto[13];
        String[] names = {"Piazzale del Nevegal","Casera Faverghera", "Palestra di roccia", "Belvedere Alpago", "Paesaggio carsico", "Cansiglio", "Orto botanico", "Rifugio Brigata Cadore", "Panoramica", "Rifugio Visentin", "Strada forestale", "Bivio per Malga Toront", "Rifugio Bristot",};
        CharSequence[] storia = {contesto.getResources().getText(R.string.storia_00),null,null,contesto.getResources().getText(R.string.storia_03),null,null,contesto.getResources().getText(R.string.storia_06),contesto.getResources().getText(R.string.storia_07),null,contesto.getResources().getText(R.string.storia_09),null,null,contesto.getResources().getText(R.string.storia_12)};
        CharSequence[] turismo = {contesto.getResources().getText(R.string.turismo_00),null,contesto.getResources().getText(R.string.turismo_02),null,null,null,null,null,null,null,null,null,contesto.getResources().getText(R.string.turismo_12)};
        CharSequence[] fauna = {contesto.getResources().getText(R.string.fauna_00),null,null,null,contesto.getResources().getText(R.string.fauna_04),null,null,null,contesto.getResources().getText(R.string.fauna_08),null,contesto.getResources().getText(R.string.fauna_10),contesto.getResources().getText(R.string.fauna_11),null};
        CharSequence[] flora = {contesto.getResources().getText(R.string.flora_00),null,contesto.getResources().getText(R.string.flora_02),contesto.getResources().getText(R.string.flora_03),null,null,contesto.getResources().getText(R.string.flora_06),null,contesto.getResources().getText(R.string.flora_08),null,contesto.getResources().getText(R.string.flora_10),contesto.getResources().getText(R.string.flora_11),null};
        CharSequence[] geologia = {contesto.getResources().getText(R.string.geologia_00),null,null,contesto.getResources().getText(R.string.geologia_03),contesto.getResources().getText(R.string.geologia_04),contesto.getResources().getText(R.string.geologia_05),null,null,null,null,contesto.getResources().getText(R.string.geologia_10),null,contesto.getResources().getText(R.string.geologia_12)};
        String[] descrizioni = {null,contesto.getResources().getString(R.string.descrizione_punto_1),contesto.getResources().getString(R.string.descrizione_punto_2),contesto.getResources().getString(R.string.descrizione_punto_3),contesto.getResources().getString(R.string.descrizione_punto_4),contesto.getResources().getString(R.string.descrizione_punto_5),
                contesto.getResources().getString(R.string.descrizione_punto_6),contesto.getResources().getString(R.string.descrizione_punto_7),contesto.getResources().getString(R.string.descrizione_punto_8),contesto.getResources().getString(R.string.descrizione_punto_9),contesto.getResources().getString(R.string.descrizione_punto_10),
                contesto.getResources().getString(R.string.descrizione_punto_11),contesto.getResources().getString(R.string.descrizione_punto_12)};
        int[] immagini = {R.drawable.infopoint,R.drawable.punto_01,R.drawable.punto_02, R.drawable.punto_03,R.drawable.punto_04,R.drawable.punto_05,R.drawable.punto_06,R.drawable.punto_07,R.drawable.punto_08,R.drawable.punto_09,R.drawable.punto_10,R.drawable.punto_11,R.drawable.punto_12};
        int[] altitudini = {1050,1518,1406,1418,1498,1517,1520,1583,1633,1729,1683,1643,1623,};
        CharSequence[] link_contatti = {contesto.getResources().getText(R.string.contatti_00),contesto.getResources().getText(R.string.contatti_01),null,null,null,null,contesto.getResources().getText(R.string.contatti_06),null,null,contesto.getResources().getText(R.string.contatti_09),null,null,contesto.getResources().getText(R.string.contatti_12)};
        CharSequence[] varie = {contesto.getResources().getText(R.string.varie_00),null,null,null,contesto.getResources().getText(R.string.varie_04),null,contesto.getResources().getText(R.string.varie_06),null,null,contesto.getResources().getText(R.string.varie_09),null,null,null};
        CharSequence[] descrizioniComplete = {contesto.getResources().getText(R.string.descrizione_00),contesto.getResources().getText(R.string.descrizione_01),contesto.getResources().getText(R.string.descrizione_02),contesto.getResources().getText(R.string.descrizione_03),null,null,contesto.getResources().getText(R.string.descrizione_06),contesto.getResources().getText(R.string.descrizione_07),contesto.getResources().getText(R.string.descrizione_08),null,contesto.getResources().getText(R.string.descrizione_10),contesto.getResources().getText(R.string.descrizione_11),contesto.getResources().getText(R.string.descrizione_12)};

        for(int i=0; i<punti.length;i++){
            punti[i] = new Punto(names[i],
                    descrizioni[i],
                    immagini[i],
                    altitudini[i],
                    storia[i],
                    turismo[i],
                    fauna[i],
                    flora[i],
                    geologia[i],
                    i,
                    varie[i],
                    link_contatti[i],
                    descrizioniComplete[i]);
        }
        return punti;
    }

    public static Punto[] getPUNTI() {
        return PUNTI;
    }
}
