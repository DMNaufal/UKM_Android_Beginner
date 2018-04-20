package app_project.healthapp.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app_project.healthapp.HospitalActivity;
import app_project.healthapp.MainActivity;
import app_project.healthapp.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.google.android.gms.maps.GoogleMap.*;

/**
 * Created by reksa on 07/06/17.
 */

public class MapsFragment extends Fragment implements OnMapReadyCallback, OnMarkerClickListener {

    GoogleMap mMap;
    MapView mapView;
    View mView;
    private Marker myMarker;

    GoogleApiClient mGoogleApiClient;

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.activity_maps, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView)mView.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mMap = googleMap;
        googleMap.setMapType(MAP_TYPE_NORMAL);

        LatLng Liberty = new LatLng(-6.2838184,106.780915);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Liberty));

        mMap.setOnMarkerClickListener(this);

        myMarker = mMap.addMarker(new MarkerOptions()
                .position(Liberty)
                .title("RS. Pondok Indah")
                .snippet("Jl. Metro Duta Kav. UE, Kebayoran Lama, Jakarta Selatan")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if(marker.equals(myMarker))
        {
            mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Intent intent = new Intent(MapsFragment.this.getActivity(),HospitalActivity.class);
                    startActivity(intent);


                }
            });
        }
        return false;
    }
}