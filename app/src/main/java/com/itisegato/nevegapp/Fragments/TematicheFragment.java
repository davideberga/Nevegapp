package com.itisegato.nevegapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.itisegato.nevegapp.Activities.GalleriaActivity;
import com.itisegato.nevegapp.Activities.MenuSezioneActivity;
import com.itisegato.nevegapp.GeneralClasses.Punto;
import com.itisegato.nevegapp.R;

/**
 * Fragment tematiche per la sezione 'Temi' della Main Activity
 * Created by Davide on 15/03/2018.
 */

public class TematicheFragment extends Fragment {

    private View v;
    public static final String COLORE = "colore";

    private final int[] cards = {
            R.id.cardViewStoria,
            R.id.cardViewFauna,
            R.id.cardViewFlora,
            R.id.cardViewGeologia,
            R.id.cardViewTurismo,
            R.id.cardViewVarie,
            R.id.cardViewLinkEContatti,
            R.id.cardViewDescrizioni};

    private final String[] names = {
            Punto.NOME_SEZIONE_STORIA,
            Punto.NOME_SEZIONE_FAUNA,
            Punto.NOME_SEZIONE_FLORA,
            Punto.NOME_SEZIONE_GEOLOGIA,
            Punto.NOME_SEZIONE_TURISMO,
            Punto.NOME_SEZIONE_VARIE,
            Punto.NOME_SEZIONE_LINK_CONTATTI,
            Punto.NOME_SEZIONE_DESCRIZIONE};

    private final int[] colors = {
            R.color.coloreStoria,
            R.color.coloreFauna,
            R.color.coloreFlora,
            R.color.coloreGeologia,
            R.color.coloreTurismo,
            R.color.coloreVarie,
            R.color.coloreLinkEContatti,
            R.color.coloreDescrizioni
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tematiche_fragment,container,false);
        setAllOnClickListener();
        return v;
    }

    private void setAllOnClickListener(){
        for (int i = 0; i<cards.length;i++) {
            final int index = i;
            v.findViewById(cards[index]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {}
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Intent i = new Intent(getActivity().getBaseContext(), MenuSezioneActivity.class);
                            i.putExtra(MenuSezioneActivity.SIMBOLO_SEZIONE, names[index]);
                            i.putExtra(COLORE, getResources().getColor(colors[index]));
                            startActivity(i);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {}
                    });
                    view.startAnimation(anim);
                }
            });
        }
        v.findViewById(R.id.cardViewGalleria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {}
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Intent i = new Intent(getActivity().getBaseContext(), GalleriaActivity.class);
                            i.putExtra(COLORE, getResources().getColor(R.color.coloreGalleria));
                            startActivity(i);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {}
                    });
                    view.startAnimation(anim);
            }
        });
    }
}