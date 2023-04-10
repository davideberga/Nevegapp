package com.itisegato.nevegapp.GeneralClasses;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;
import com.itisegato.nevegapp.Activities.MenuPuntoActivity;
import com.itisegato.nevegapp.R;

import static com.itisegato.nevegapp.Fragments.OnePageFragment.SIMBOLO_PUNTO;


/**
 * Created by Davide on 14/03/2018.
 * Ridefinisce l'info window dei marker sulla mappa
 */

public class MyInfoWindow extends MarkerInfoWindow {

    private Punto puntoDelMarker;
    private MapView map;
    private Context context;

    public MyInfoWindow(int layoutResId, MapView mapView, Punto puntoDelMarker, Context context) {
        super(layoutResId, mapView);
        this.map = mapView;
        this.context = context;
        this.puntoDelMarker=puntoDelMarker;
    }


    @Override
    public void onOpen(Object item) {
        InfoWindow.closeAllInfoWindowsOn(map);
        LinearLayout layout = mView.findViewById(R.id.bubble_layout);
        TextView textTitle = mView.findViewById(R.id.bubble_title);
        TextView textDescription = mView.findViewById(R.id.bubble_description);

        if(puntoDelMarker.getNumeroPunto() == 0){
            textTitle.setText("Infopoint");
        }
        else{
            textTitle.setText(String.valueOf(puntoDelMarker.getNumeroPunto()));
        }
        textDescription.setText(puntoDelMarker.getNomePunto());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MenuPuntoActivity.class);
                i.putExtra(SIMBOLO_PUNTO, puntoDelMarker.getNumeroPunto());
                context.startActivity(i);
            }
        });
    }
}
