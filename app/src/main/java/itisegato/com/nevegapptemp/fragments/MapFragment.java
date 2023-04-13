package itisegato.com.nevegapptemp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.CopyrightOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

import itisegato.com.nevegapptemp.classes.GeneraArrayPunti;
import itisegato.com.nevegapptemp.classes.MyInfoWindow;
import itisegato.com.nevegapptemp.classes.MyOnClickMarkerListener;
import itisegato.com.nevegapptemp.classes.Punto;
import itisegato.com.nevegapptemp.R;
import itisegato.com.nevegapptemp.utils.GpxParser;
import itisegato.com.nevegapptemp.utils.LocListener;

import egolabsapps.basicodemine.offlinemap.Interfaces.MapListener;
import egolabsapps.basicodemine.offlinemap.Utils.MapUtils;
import egolabsapps.basicodemine.offlinemap.Views.OfflineMapView;

/**
 * Created by Davide on 17/01/2018.
 *
 * Classe che gestisce il fragment della mappa,
 * viene inoltre disegnato tutto il necessario a
 * partire da un file gpx
 *
 */

public class MapFragment extends Fragment implements MapListener {

    protected OfflineMapView offlineMap;
    protected MapUtils mapUtils;
    protected MapView map;
    protected IMapController controllore = null;
    protected GeoPoint currentLocation = null;
    protected GeoPoint centerOfPath = null;
    protected static int puntoAttuale = -1;
    protected TextView textPuntoSelezionato;
    protected ArrayList<Marker> listaMarker;
    private MyLocationNewOverlay myLocationNewOverlay;
    private CopyrightOverlay mCopyrightOverlay;
    private GpsMyLocationProvider provider;
    protected FloatingActionButton fabPosition;
    protected Button btnIndietro;
    protected Button btnAvanti;
    protected FloatingActionButton fabPath;
    protected TextView textNotifica;
    protected LinearLayout layoutNotifica;
    protected LocationManager locationManager;

    private static final String SAVED_ZOOM_LEVEL = "zoom";
    private static final String SAVED_CENTER = "center";
    private static final long UPDATE_LOCATION_TIME = 1000;
    private static final double NORTH = 46.1846;
    private static final double EAST = 12.3837;
    private static final double SOUTH = 46.0113;
    private static final double WEST = 12.1825;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_fragment, container, false);
        fabPosition = v.findViewById(R.id.fabPosition);
        btnIndietro = v.findViewById(R.id.bottoneIndietro);
        btnAvanti = v.findViewById(R.id.bottoneAvanti);
        fabPath = v.findViewById(R.id.fabPath);
        textNotifica = v.findViewById(R.id.textViewNotificaProssimità);
        layoutNotifica = v.findViewById(R.id.llnp);
        textPuntoSelezionato = v.findViewById(R.id.textViewPuntoAttuale);
        textPuntoSelezionato.setText("...");
        offlineMap = v.findViewById(R.id.mapView);
        offlineMap.init(getActivity(), this);
        puntoAttuale = -1;
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        /*outState.putInt(SAVED_ZOOM_LEVEL, map.getZoomLevel());
        outState.putSerializable(SAVED_CENTER, (Serializable) map.getMapCenter());*/
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        myLocationNewOverlay.disableMyLocation();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        if (myLocationNewOverlay != null)
            myLocationNewOverlay.enableMyLocation();
        super.onResume();
    }

    public static void setPuntoAttuale(int nuovoPuntoAttuale) {
        puntoAttuale = nuovoPuntoAttuale;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void mapLoadSuccess(MapView mapView, MapUtils mapUtils) {
        this.mapUtils = mapUtils;
        this.map = mapUtils.getMap();
        BoundingBox box = new BoundingBox(NORTH, EAST, SOUTH, WEST);
        map.setScrollableAreaLimitDouble(box);
        controllore = map.getController();
        map.setMultiTouchControls(true);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        map.setVisibility(View.VISIBLE);

        provider = new GpsMyLocationProvider(getActivity().getBaseContext());
        provider.addLocationSource(LocationManager.GPS_PROVIDER);
        provider.setLocationUpdateMinTime(UPDATE_LOCATION_TIME);
        mCopyrightOverlay = new CopyrightOverlay(getContext());
        myLocationNewOverlay = new MyLocationNewOverlay(provider, map);
        myLocationNewOverlay.enableMyLocation();
        myLocationNewOverlay.setDrawAccuracyEnabled(true);
        map.getOverlays().add(myLocationNewOverlay);


        ScaleBarOverlay mScaleBarOverlay = new ScaleBarOverlay(map);
        mScaleBarOverlay.enableScaleBar();
        map.getOverlays().add(mScaleBarOverlay);

        // Disegno del percorso utilizzando il file gpx

        Polyline line = new Polyline();
        line.setColor(getResources().getColor(R.color.coloreAzzurroIcona));
        line.setWidth(4.5f);
        List<GeoPoint> pts = new ArrayList<>();
        List<Location> puntiDelPercorso = GpxParser.decodeGPX("trkpt", getContext());

        for (Location l : puntiDelPercorso) {
            pts.add(new GeoPoint(l.getLatitude(), l.getLongitude()));
        }


        line.setPoints(pts);
        line.setVisible(true);
        line.setGeodesic(true);
        map.getOverlays().add(line);

        Marker infopoint = new Marker(mapView);
        infopoint.setInfoWindow(new MyInfoWindow(R.layout.bonuspack_bubble, map,
                GeneraArrayPunti.getPUNTI()[0], this.getContext()));
        infopoint.setIcon(getResources().getDrawable(R.drawable.marker_punto_blu));
        infopoint.setPosition(new GeoPoint(46.092686, 12.281283));
        infopoint.setInfoWindowAnchor(3f,0.25f);
        map.getOverlays().add(infopoint);

        // Disegno dei punti di interess lungo il percorso

        final List<Location> wayPoints = GpxParser.decodeGPX("wpt", getContext());
        locationManager=(LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                500,
                1, new LocListener(textNotifica, layoutNotifica, wayPoints));
        listaMarker = new ArrayList<>();
        for(int i=0; i<wayPoints.size();i++){
            Marker punto = new Marker(mapView);
            listaMarker.add(punto);
            punto.setPosition(new GeoPoint(wayPoints.get(i).getLatitude(),wayPoints.get(i).getLongitude()));
            if(i<13) {
                punto.setOnMarkerClickListener(new MyOnClickMarkerListener
                        (GeneraArrayPunti.getPUNTI()[i+1],textPuntoSelezionato));
                punto.setRelatedObject(GeneraArrayPunti.getPUNTI()[i+1]);
            }
            punto.setInfoWindow(new MyInfoWindow
                    (R.layout.bonuspack_bubble, map, (Punto)punto.getRelatedObject(),
                            this.getContext()));
            punto.setInfoWindowAnchor(3f,0.25f);
            punto.setIcon(getResources().getDrawable(R.drawable.marker_punto));
            map.getOverlays().add(punto);
        }

        setupClickListeners(mapView,wayPoints);
        controllore.setCenter(new GeoPoint(wayPoints.get(6)));
        controllore.setZoom(14);
        map.getOverlays().add(mCopyrightOverlay);
        //mapView.invalidate();
    }

    private void setupClickListeners(final MapView map, final List<Location> wayPoints){
        btnIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLocationNewOverlay.disableFollowLocation();
                if(puntoAttuale==0){
                    puntoAttuale--;
                }
                if(puntoAttuale!=-1){
                    puntoAttuale--;
                    textPuntoSelezionato.setText("Punto " + (puntoAttuale+1));
                    listaMarker.get(puntoAttuale).showInfoWindow();
                    controllore.animateTo(listaMarker.get(puntoAttuale).getPosition());
                }
                if(puntoAttuale==-1){
                    textPuntoSelezionato.setText("...");
                    InfoWindow.closeAllInfoWindowsOn(map);
                }
            }
        });

        btnAvanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLocationNewOverlay.disableFollowLocation();
                if(puntoAttuale!=11){
                    puntoAttuale++;
                    textPuntoSelezionato.setText("Punto " + (puntoAttuale+1));
                    listaMarker.get(puntoAttuale).showInfoWindow();
                    controllore.animateTo(listaMarker.get(puntoAttuale).getPosition());
                }
                else{
                    puntoAttuale=-1;
                    textPuntoSelezionato.setText("...");
                    InfoWindow.closeAllInfoWindowsOn(map);
                }
            }
        });


        fabPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myLocationNewOverlay.getMyLocation()!=null) {
                    controllore.animateTo(myLocationNewOverlay.getMyLocation());
                    myLocationNewOverlay.enableFollowLocation();
                }
                else
                    Toast.makeText(getContext(),getResources().
                            getText(R.string.pozizione_ancora_nulla),Toast.LENGTH_SHORT).show();
            }
        });

        fabPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLocationNewOverlay.disableFollowLocation();
                controllore.setZoom(14);
                controllore.animateTo(new GeoPoint(wayPoints.get(6)));

            }
        });
    }

    @Override
    public void mapLoadFailed(String ex) {

    }
}
