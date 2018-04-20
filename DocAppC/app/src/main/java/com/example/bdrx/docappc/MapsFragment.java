package com.example.bdrx.docappc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by reksa on 17/07/17.
 */

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mapView;
    View mView;

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

        mapView = (MapView) mView.findViewById(R.id.map);
        if (mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        return mView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
}
