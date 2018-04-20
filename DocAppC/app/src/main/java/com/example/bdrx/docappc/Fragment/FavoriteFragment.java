package com.example.bdrx.docappc.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bdrx.docappc.R;

/**
 * Created by reksa on 07/06/17.
 */

public class FavoriteFragment extends Fragment {

    //common
    View mView;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_favorite, container, false);
        return mView;
    }

    public static Fragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return  fragment;
    }
}