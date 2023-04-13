package itisegato.com.nevegapptemp.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import itisegato.com.nevegapptemp.activities.MenuPuntoActivity;
import itisegato.com.nevegapptemp.classes.Punto;

import itisegato.com.nevegapptemp.R;

/**
 *  Punto stilizzato
 *  - immagine - titolo - descrizione -
 */

public class OnePageFragment extends Fragment{

    private Punto puntoVisualizzato;
    public static final String SIMBOLO_PUNTO = "punto";
    private Intent i;

    private View.OnClickListener listenerButton = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    i.putExtra(SIMBOLO_PUNTO, puntoVisualizzato.getNumeroPunto());
                    startActivity(i);
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            view.startAnimation(anim);

        }
    };

    public static OnePageFragment newInstance(Punto p) {
        OnePageFragment pagina = new OnePageFragment();
        pagina.puntoVisualizzato=p;
        return pagina;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i= new Intent(this.getContext(), MenuPuntoActivity.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup composizione = (ViewGroup) inflater.inflate(
                R.layout.layout_single_fragment, container, false);
        ConstraintLayout laPiccolaFinestra;
        if(puntoVisualizzato.getNumeroPunto()!=0) {
            laPiccolaFinestra = composizione.findViewById(R.id.piccolaFinestra);
            ConstraintLayout finestra = composizione.findViewById(R.id.layoutFinestra);
            laPiccolaFinestra.setBackground(getResources().getDrawable(R.drawable.sfondo_rettangolo));
            laPiccolaFinestra.setOnClickListener(listenerButton);
            ImageView immagine = composizione.findViewById(R.id.immaginePunto);

            ((TextView) composizione.findViewById(R.id.descrizionePunto)).setText(puntoVisualizzato.getDescrizione());
            TextView titolo = composizione.findViewById(R.id.titoloPunto);
            titolo.setText(puntoVisualizzato.getNomePunto());
            ((TextView) composizione.findViewById(R.id.altitudinePunto)).setText("ALTITUDINE: " + (int) puntoVisualizzato.getAltitudine() + " s.l.m.");

            Glide.with(this.getContext())
                    .load(puntoVisualizzato.getIdImmagine())
                    .into(immagine);
            ((TextView) composizione.findViewById(R.id.descrizionePunto)).setText(puntoVisualizzato.getDescrizione());
            ((TextView) composizione.findViewById(R.id.numeroPuntoTitolo)).setText("Punto " + puntoVisualizzato.getNumeroPunto());
        }
        else {
            laPiccolaFinestra = composizione.findViewById(R.id.piccolaFinestra);
            laPiccolaFinestra.setOnClickListener(listenerButton);
            ImageView immagine = composizione.findViewById(R.id.immaginePunto);
            Glide.with(this.getContext())
                    .load(puntoVisualizzato.getIdImmagine())
                    .into(immagine);
            ((TextView) composizione.findViewById(R.id.numeroPuntoTitolo)).setText("Infopoint");
            TextView titolo = composizione.findViewById(R.id.titoloPunto);
            titolo.setText(puntoVisualizzato.getNomePunto());
            titolo.setTextSize(28f);
            ((TextView) composizione.findViewById(R.id.altitudinePunto)).setText("ALTITUDINE: " + (int) puntoVisualizzato.getAltitudine() + " s.l.m.");
        }
        return composizione;
    }
}
