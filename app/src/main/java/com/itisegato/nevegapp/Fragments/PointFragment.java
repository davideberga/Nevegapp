package com.itisegato.nevegapp.Fragments;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.itisegato.nevegapp.GeneralClasses.GeneraArrayPunti;
import com.itisegato.nevegapp.R;
import com.itisegato.nevegapp.Utilities.ZoomOutPageTransformer;

/**
 * Fragment punto per la sezione 'Punti' della Main Activity
 * Created by Davide on 19/01/2018.
 */

public class PointFragment extends Fragment {

    public static final int N_PAGINE = GeneraArrayPunti.getPUNTI().length;

    public PointFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.point_fragment,container,false);
        ViewPager paginatore = v.findViewById(R.id.paginatore);
        paginatore.setPageTransformer(true, new ZoomOutPageTransformer());
        Adattatore adattatorePagine = new Adattatore(getChildFragmentManager());
        paginatore.setAdapter(adattatorePagine);
        paginatore.setCurrentItem(0);
        ImageView img = v.findViewById(R.id.sfondoPunti);
        Glide.with(this.getContext())
                .load(R.drawable.sfondo_punti)
                .centerCrop()
                .into(img);
        return v;
    }

    private static class Adattatore extends FragmentPagerAdapter {

        public Adattatore(FragmentManager manager){
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return OnePageFragment.newInstance(GeneraArrayPunti.getPUNTI()[position]);
        }

        @Override
        public int getCount() { return N_PAGINE;}
    }
}
