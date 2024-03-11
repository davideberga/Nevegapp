package itisegato.com.nevegapptemp.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.osmdroid.config.Configuration;
import org.osmdroid.config.IConfigurationProvider;

import itisegato.com.nevegapptemp.fragments.MainFragment;

import static org.osmdroid.tileprovider.util.StorageUtils.getStorage;

import itisegato.com.nevegapptemp.BuildConfig;
import itisegato.com.nevegapptemp.R;
import pub.devrel.easypermissions.EasyPermissions;

/**
 *  Main Activity
 *  Contiene l'intera grafica principale: gestisce il drawer laterale e
 *  lo scorrimento delle tre sezioni (mappa, punti, temi)
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle mtoggle;
    private MainFragment mainFragment;

    public static final String CONDIVIDI = "Condividi";
    public static final String LICENZA = "Licenza";
    public static final String GUIDA = "Guida";
    public static final String ABOUTUS = "About us";
    public static final String ABOUTNEVEGAPP = "About Nevegapp";
    public static final String CREDITI = "Crediti";
    public static final String BIBLIOGRAFIA = "Fonti";

    public static final String SECTION = "sezione";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        IConfigurationProvider provider = Configuration.getInstance();
        provider.setUserAgentValue(BuildConfig.APPLICATION_ID);
        provider.setOsmdroidBasePath(getStorage());
        provider.setOsmdroidTileCache(getStorage());

        if (firstStart) {
            showStartDialog();
        }

        int permissionAll = 1;
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.INTERNET
        };
        if(!EasyPermissions.hasPermissions(this, permissions))
            requestPermissions(permissions, 10);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar barra = getSupportActionBar();

        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        barra.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        barra.setCustomView(R.layout.layout_action_bar);
        barra.setDisplayHomeAsUpEnabled(true);

        BottomNavigationView mBottomNavigationView = findViewById(R.id.navigation);
        mainFragment = new MainFragment(mBottomNavigationView);

        drawer = findViewById(R.id.drawer_layout);
        mtoggle = new ActionBarDrawerToggle(this, drawer,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(mtoggle);
        mtoggle.syncState();

        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);

        NavigationView navigationView = findViewById(R.id.menulaterale);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().
                add(R.id.frameLayoutFragment, mainFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.bottomSensori) {
            Intent i = new Intent(this, Compass.class);
            startActivity(i);
            return true;
        }
        if(mtoggle.onOptionsItemSelected(item)){
            if (!drawer.isDrawerOpen(GravityCompat.START))
                drawer.openDrawer(GravityCompat.START);
            else
                drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    /**
     *  Guida rapida, gestione dell'apertura automatica al primo avvio
     *  e del contenuto
     */
    private void showStartDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.guida_layout, null);
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
        builderSingle.setTitle("Guida rapida");
        builderSingle.setView(v);
        builderSingle.setPositiveButton("SALTA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = new Intent(this, OptionsActivity.class);
        Bundle b = new Bundle();
        if (id == R.id.section_condividi) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBodyText = "https://play.google.com/store/apps/details?id=com.itisegato.nevegapptemp";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(sharingIntent, "Opzioni di condivisione"));
            return true;
        }
        else if (id == R.id.section_guida) {showStartDialog(); return  true;}
        else if (id == R.id.section_crediti) {b.putString(SECTION, CREDITI);}
        else if (id == R.id.section_aboutNevegapp) {b.putString(SECTION, ABOUTNEVEGAPP);}
        else if (id == R.id.section_aboutUs) {b.putString(SECTION, ABOUTUS);}
        else if (id == R.id.section_licenza) {b.putString(SECTION, LICENZA);}
        else if (id == R.id.section_bibliografia) {b.putString(SECTION, BIBLIOGRAFIA);}
        else if (id == R.id.section_esci){System.exit(0);}
        intent.putExtras(b);
        drawer.closeDrawer(GravityCompat.START);
        startActivity(intent);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
