package com.itisegato.nevegapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itisegato.nevegapp.R;
import com.itisegato.nevegapp.Utilities.Image;
import com.itisegato.nevegapp.Utilities.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;



/**
 *  La galleria visualizza una serie di immagini,
 *  scaricabili dall'utente sul proprio dispositivo
 */
public class GalleriaActivity extends AppCompatActivity {

    private List<Image> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleria);

        images = new ArrayList<>();

        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle("Galleria");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        images.add(new Image("1° Giochi della Gioventù anno 1970 (foto Zanfron)", R.drawable.foto_01));
        images.add(new Image("Aconito napello nei pressi dell'orto botanico",  R.drawable.foto_02));
        images.add(new Image("Albergo S. Martino 1961 (foto Zanfron)",  R.drawable.foto_03));
        images.add(new Image("Campanula morettiana",  R.drawable.foto_04));
        images.add(new Image("Dolina nei pressi dell'orto botanico", R.drawable.foto_05));
        images.add(new Image("Gentiana punctata",  R.drawable.foto_06));
        images.add(new Image("Genziana pannonica",  R.drawable.foto_07));
        images.add(new Image("Giglio di S. Giovanni",  R.drawable.foto_08));
        images.add(new Image("Gli autori, in cresta",  R.drawable.foto_09));
        images.add(new Image("Gli autori, in pausa contemplativa", R.drawable.foto_10));
        images.add(new Image("Ingresso del giardino botanico",  R.drawable.foto_11));
        images.add(new Image("Nigritella nigra",  R.drawable.foto_12));
        images.add(new Image("Ninfea comune",  R.drawable.foto_13));
        images.add(new Image("Panoramica Dolomiti",  R.drawable.foto_14));
        images.add(new Image("Raponzolo di roccia",  R.drawable.foto_15));
        images.add(new Image("Regina delle Alpi",  R.drawable.foto_16));
        images.add(new Image("Rif. Col Visentìn  anni 50 (foto Fausto Viola)",  R.drawable.foto_17));
        images.add(new Image("Rocce calcaree",  R.drawable.foto_18));
        images.add(new Image("Scarpetta della Madonna",  R.drawable.foto_19));
        images.add(new Image("Tracce lasciate da cinghiali",  R.drawable.foto_20));
        images.add(new Image("Vista su monte Serva e Schiara",  R.drawable.foto_21));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, images);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
