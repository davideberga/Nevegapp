package com.itisegato.nevegapp.GeneralClasses;

import android.widget.TextView;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import com.itisegato.nevegapp.Fragments.MapFragment;

/**
 * Click Listener del Marker
 *
 * Created by Davide on 16/03/2018.
 */

public class MyOnClickMarkerListener implements Marker.OnMarkerClickListener {

    private Punto puntoVisulizzato;
    private TextView text;

    public MyOnClickMarkerListener(Punto puntoVisulizzato, TextView text) {
        this.puntoVisulizzato = puntoVisulizzato;
        this.text = text;
    }

    @Override
    public boolean onMarkerClick(Marker marker, MapView mapView) {
        marker.showInfoWindow();
        text.setText(String.valueOf(puntoVisulizzato.getNumeroPunto()));
        MapFragment.setPuntoAttuale(puntoVisulizzato.getNumeroPunto());
        return false;
    }
}
