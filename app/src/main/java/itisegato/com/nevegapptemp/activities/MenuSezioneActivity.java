package itisegato.com.nevegapptemp.activities;

import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import itisegato.com.nevegapptemp.fragments.TematicheFragment;
import itisegato.com.nevegapptemp.classes.GeneraArrayPunti;
import itisegato.com.nevegapptemp.classes.Punto;
import itisegato.com.nevegapptemp.classes.Sezione;

import itisegato.com.nevegapptemp.R;

/**
 * Activity che contiene tutti i punti
 * che posseggono una determinata tematica
 */

public class MenuSezioneActivity extends AppCompatActivity {

    private static Punto[] punti;
    private ArrayList<Sezione> sezioniTab;

    public static final String SIMBOLO_SEZIONE = "sezione";

    static {
        punti = GeneraArrayPunti.getPUNTI();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sezione);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(SIMBOLO_SEZIONE).toUpperCase());
        toolbar.setNavigationIcon(R.drawable.freccia_indietro_bianca);
        toolbar.setClickable(true);
        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(getIntent().getIntExtra(TematicheFragment.COLORE, 0));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuSezioneActivity.super.onBackPressed();
            }
        });

        sezioniTab = generaSezioni();

        SectionsPagerAdapter mSectionsPagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        tabLayout.setBackgroundColor(getIntent().getIntExtra(TematicheFragment.COLORE, 0));

        for(Sezione s : sezioniTab)
            tabLayout.addTab(tabLayout.newTab().setText(s.getNomeSezione()));

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    public static class PlaceholderFragment extends Fragment {

        private int section_number;
        private ArrayList<Sezione> sezioni;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber, ArrayList<Sezione> sezioni) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.sezioni=sezioni;
            fragment.section_number=sectionNumber;
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_menu_sezione, container, false);
            TextView textView = rootView.findViewById(R.id.testoScrollabile);
            textView.setText(sezioni.get(section_number).getContenutoSezione());
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position, sezioniTab);
        }

        @Override
        public int getCount() {
            return sezioniTab.size();
        }
    }

    private ArrayList<Sezione> generaSezioni(){
        ArrayList<Sezione>  sezioni = new ArrayList<>();
        String sezione = getIntent().getStringExtra(SIMBOLO_SEZIONE);
        switch (sezione){
            case Punto.NOME_SEZIONE_STORIA:
                for(Punto p: punti)
                    if(p.getStoria()!= null)
                        if(p.getNumeroPunto() != 0)
                            sezioni.add(new Sezione("Punto " + p.getNumeroPunto(), p.getStoria()));
                        else
                            sezioni.add(new Sezione("Infopoint", p.getStoria()));
                break;
            case Punto.NOME_SEZIONE_FAUNA:
                for(Punto p: punti)
                    if(p.getFauna()!= null)
                        if(p.getNumeroPunto() != 0)
                            sezioni.add(new Sezione("Punto " + p.getNumeroPunto(), p.getFauna()));
                        else
                            sezioni.add(new Sezione("Infopoint", p.getFauna()));
                break;
            case Punto.NOME_SEZIONE_FLORA:
                for(Punto p: punti)
                    if(p.getFlora() != null)
                        if(p.getNumeroPunto() != 0)
                            sezioni.add(new Sezione("Punto " + p.getNumeroPunto(), p.getFlora()));
                        else
                            sezioni.add(new Sezione("Infopoint", p.getFlora()));
                break;
            case Punto.NOME_SEZIONE_GEOLOGIA:
                for(Punto p: punti)
                    if(p.getGeologia() != null)
                        if(p.getNumeroPunto() != 0)
                            sezioni.add(new Sezione("Punto " + p.getNumeroPunto(), p.getGeologia()));
                        else
                            sezioni.add(new Sezione("Infopoint", p.getGeologia()));
                break;
            case Punto.NOME_SEZIONE_TURISMO:
                for(Punto p: punti)
                    if(p.getTurismo()!=null)
                        if(p.getNumeroPunto() != 0)
                            sezioni.add(new Sezione("Punto " + p.getNumeroPunto(), p.getTurismo()));
                        else
                            sezioni.add(new Sezione("Infopoint", p.getTurismo()));
                break;
            case Punto.NOME_SEZIONE_LINK_CONTATTI:
                for(Punto p: punti)
                    if(p.getLinkecontatti()!=null)
                        if(p.getNumeroPunto() != 0)
                            sezioni.add(new Sezione("Punto " + p.getNumeroPunto(), p.getLinkecontatti()));
                        else
                            sezioni.add(new Sezione("Infopoint", p.getLinkecontatti()));
                break;
            case Punto.NOME_SEZIONE_VARIE:
                for(Punto p: punti)
                    if(p.getVarie()!=null)
                        if(p.getNumeroPunto() != 0)
                            sezioni.add(new Sezione("Punto " + p.getNumeroPunto(), p.getVarie()));
                        else
                            sezioni.add(new Sezione("Infopoint", p.getVarie()));
                break;
            case Punto.NOME_SEZIONE_DESCRIZIONE:
                for(Punto p: punti)
                    if(p.getDescrizioneCompleta()!=null)
                        if(p.getNumeroPunto() != 0)
                            sezioni.add(new Sezione("Punto " + p.getNumeroPunto(), p.getDescrizioneCompleta()));
                        else
                            sezioni.add(new Sezione("Infopoint", p.getDescrizioneCompleta()));
                break;
        }
        return sezioni;
    }
}
