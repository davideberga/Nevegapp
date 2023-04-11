package com.itisegato.nevegapptemp.activities;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.itisegato.nevegapptemp.classes.GeneraArrayPunti;
import com.itisegato.nevegapptemp.R;

/**
 * Splash Acitivity che gestisce l'animazione iniziale
 * ed un piccolo caricamento dati
 */

public class SplashActivity extends AppCompatActivity {

    final int INTERNET=1;
    final int NETSTATE=2;
    final int LOCATION=3;
    final int LOCATIONC=4;
    final int WIFI=5;
    final int STORAGE=6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        this.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);

        Animation moveGiu;
        Animation moveSu;

        ImageView montagna = findViewById(R.id.montagna);
        ImageView enne = findViewById(R.id.enne);

        moveGiu = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movegiu);
        moveSu = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movesu);

        montagna.startAnimation(moveSu);
        enne.startAnimation(moveGiu);
        GeneraArrayPunti g = new GeneraArrayPunti(this);
        int SPLASH_TIME_OUT = 2300;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent homeIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}


