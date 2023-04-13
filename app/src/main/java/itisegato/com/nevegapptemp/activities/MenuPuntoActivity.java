package itisegato.com.nevegapptemp.activities;


import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import itisegato.com.nevegapptemp.fragments.OnePageFragment;
import itisegato.com.nevegapptemp.classes.GeneraArrayPunti;
import itisegato.com.nevegapptemp.classes.Punto;
import itisegato.com.nevegapptemp.classes.Sezione;

import itisegato.com.nevegapptemp.R;

/**
 * Activity che permette la visualizzazione di tutte le info di un punto;
 * in particolare contiene:
 *  - identificatore del punto
 *  - lista delle sezioni ad esso associate
 */
public class MenuPuntoActivity extends AppCompatActivity {

    protected Punto puntoVisualizzato;
    protected ArrayList<Sezione> sezioni;
    protected ViewPager visualizzatorePagine;
    protected AdattatorePagine adattatore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        puntoVisualizzato = GeneraArrayPunti.getPUNTI()[getIntent().getIntExtra(OnePageFragment.SIMBOLO_PUNTO, 0)];
        sezioni = puntoVisualizzato.sezioni();


        setContentView(R.layout.activity_menu_punto);

        ImageView immagine = findViewById(R.id.immagineMenuPunto);
        Glide.with(this)
                .load(puntoVisualizzato.getIdImmagine())
                .centerCrop()
                .into(immagine);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.freccia_indietro);
        toolbar.setClickable(true);
        toolbar.setTitle(puntoVisualizzato.getNomePunto());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPuntoActivity.super.onBackPressed();
            }
        });

        visualizzatorePagine = findViewById(R.id.viewPagerMenuPunto);
        adattatore = new AdattatorePagine(getSupportFragmentManager());
        visualizzatorePagine.setAdapter(adattatore);

        TabLayout mTabLayout = findViewById(R.id.tabLayoutArgomenti);
        visualizzatorePagine.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(visualizzatorePagine));

        for (Sezione s: sezioni) {
            mTabLayout.addTab(mTabLayout.newTab().setText(s.getNomeSezione()));
        }

    }

    /**
     *  Queste due classi permettono la visulizzazione 'con tab' delle diverse sezioni di un punto
     */

    public static class FragmentSezionePunto extends Fragment {

        private CharSequence testoSezione;
        private String nome;

        public FragmentSezionePunto() {
        }

        public static Fragment newInstance(CharSequence testo, String nome){
            FragmentSezionePunto fr = new FragmentSezionePunto();
            fr.testoSezione=testo;
            fr.nome = nome;
            return fr;
        }

        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View composizione =
                    inflater.inflate(R.layout.fragment_menu_sezione,container,false);
            TextView testo = composizione.findViewById(R.id.testoScrollabile);
            testo.setText(testoSezione);
            return composizione;
        }
    }

    public class AdattatorePagine extends FragmentStatePagerAdapter {

        public AdattatorePagine(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentSezionePunto.newInstance(sezioni.get(position).getContenutoSezione(), sezioni.get(position).getNomeSezione());
        }

        @Override
        public int getCount() {
            return sezioni.size();
        }
    }

}
