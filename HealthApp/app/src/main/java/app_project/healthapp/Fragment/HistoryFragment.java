package app_project.healthapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app_project.healthapp.HospitalActivity;
import app_project.healthapp.R;


/**
 * Created by reksa on 07/06/17.
 */

public class HistoryFragment extends Fragment {

    CardView cdHospital1;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_history, container, false);

        cdHospital1 = (CardView) mView.findViewById(R.id.cd_Hospital1);

        cdHospital1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hospitalIntent = new Intent(getActivity(), HospitalActivity.class);
                HistoryFragment.this.startActivity(hospitalIntent);
            }
        });
        return mView;
    }

    public static Fragment newInstance() {
        return new HistoryFragment();
    }

}
