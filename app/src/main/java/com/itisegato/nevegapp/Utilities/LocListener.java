package com.itisegato.nevegapp.Utilities;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itisegato.nevegapp.R;

import org.osmdroid.util.GeoPoint;

import java.util.List;

public class LocListener implements LocationListener {

    private TextView message;
    private LinearLayout layout;
    private List<Location> points;

    public LocListener(TextView message, LinearLayout layout, List<Location> points){
        this.message = message;
        this.layout = layout;
        this.points = points;
    }

    @Override
    public void onLocationChanged(Location location) {
        GeoPoint newPosition = new GeoPoint(location);
        float distMin = Float.MAX_VALUE;
        int minDistancePoint = 0;
        int i=0;
        for (Location p : points){
            i++;
            float [] dist = new float[1];
            Location.distanceBetween(p.getLatitude(),p.getLongitude(),
                    newPosition.getLatitude(),newPosition.getLongitude(), dist);
            if (dist[0]<distMin && dist[0] < 51){
                distMin = dist[0];
                minDistancePoint = i;
            }
        }
        if(minDistancePoint!=0){
            message.setText("Sei vicino al punto " + minDistancePoint + " : " +
                    Integer.valueOf((int) distMin) + " m");
            layout.requestLayout();
            palesati();
        }else {
            nasconditi();
        }

    }

    private void palesati(){
        if (layout.getVisibility() == View.INVISIBLE){
            layout.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.
                    loadAnimation(layout.getContext(), R.anim.slide_down);
            layout.startAnimation(anim);
        }
    }

    private void nasconditi(){
        if (layout.getVisibility() == View.VISIBLE){
            Animation anim = AnimationUtils.
                    loadAnimation(layout.getContext(), R.anim.slide_up);
            layout.startAnimation(anim);
            layout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}
}
