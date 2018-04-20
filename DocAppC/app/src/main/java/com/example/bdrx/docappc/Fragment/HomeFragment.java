package com.example.bdrx.docappc.Fragment;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.bdrx.docappc.MapsActivity;
import com.example.bdrx.docappc.NotificationActivity;
import com.example.bdrx.docappc.R;
import com.example.bdrx.docappc.SearchActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by reksa on 16/07/17.
 */

public class HomeFragment extends Fragment{

    //Button
    @BindView(R.id.btn_notification_homeFragment)
    ImageButton btn_notification_homeFragment;
    @BindView(R.id.btn_search_homeFragment)
    ImageButton btn_search_homeFragment;

    //Toolbar
    @BindView(R.id.toolbar_homeFragment)
    Toolbar toolbar_homeFragment;

    //common
    View mView;
    Intent intent, intent1;
    MapsActivity mapsAcc;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mView);

        //mainView = (FrameLayout) mView.findViewById(R.id.mainView);

        //Button : btn_notification_homeFragment
        btn_notification_homeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        //Button : btn_notification_homeFragment
        btn_search_homeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent1 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent1);
            }
        });
        
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        mapsAcc = new MapsActivity();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.mapframe, mapsAcc);
        transaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MapsActivity.MY_PERMISSIONS_REQUEST_LOCATION){
            mapsAcc.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return  fragment;
    }
}
