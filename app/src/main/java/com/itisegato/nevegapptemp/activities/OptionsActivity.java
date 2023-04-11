package com.itisegato.nevegapptemp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.itisegato.nevegapptemp.R;

/**
 * Activity che visualizza l'opzione cliccata nel drawer laterale
 */

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        getSupportActionBar().setTitle(getIntent().getStringExtra(MainActivity.SECTION));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.freccia_indietro_bianca);
        selezionaContenuto();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selezionaContenuto(){
        if(getIntent().getStringExtra(MainActivity.SECTION).equals(MainActivity.CREDITI)){
            ((TextView) findViewById(R.id.testoOpzioni)).
                    setText(getResources().getText(R.string.contenuto_Crediti));
        }
        else if(getIntent().getStringExtra(MainActivity.SECTION).equals(MainActivity.ABOUTUS)){
            ((TextView) findViewById(R.id.testoOpzioni)).
                    setText(getResources().getText(R.string.contenuto_aboutUs));
        }
        else if(getIntent().getStringExtra(MainActivity.SECTION).equals(MainActivity.ABOUTNEVEGAPP)){
            ((TextView) findViewById(R.id.testoOpzioni)).
                    setText(getResources().getText(R.string.contenuto_aboutNevegApp));
        }
        else if(getIntent().getStringExtra(MainActivity.SECTION).equals(MainActivity.LICENZA)){
            findViewById(R.id.testoOpzioni).setVisibility(View.INVISIBLE);
            findViewById(R.id.nestedContenitore).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.testoLicenza)).
                    setText(getResources().getText(R.string.contenuto_licenza));
        }
        else if(getIntent().getStringExtra(MainActivity.SECTION).equals(MainActivity.BIBLIOGRAFIA)){
            ((TextView) findViewById(R.id.testoOpzioni)).
                    setText(getResources().getText(R.string.contenuto_bibliografia));
        }
    }
}
