package app_project.healthapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app_project.healthapp.AboutActivity;
import app_project.healthapp.EditaccountActivity;
import app_project.healthapp.EditprofileActivity;
import app_project.healthapp.HelpActivity;
import app_project.healthapp.PrivacyActivity;
import app_project.healthapp.R;

/**
 * Created by reksa on 07/06/17.
 */

public class SettingsFragment extends Fragment {

    CardView cdEditProfile, cdEditAccount, cdPrivacy, cdHelp, cdAbout;

    public SettingsFragment() {
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
        View mView = inflater.inflate(R.layout.fragment_settings, container, false);

        cdEditProfile = (CardView) mView.findViewById(R.id.cd_EditProfile);
        cdEditAccount = (CardView) mView.findViewById(R.id.cd_EditAccount);
        cdPrivacy = (CardView) mView.findViewById(R.id.cd_Privacy);
        cdHelp = (CardView) mView.findViewById(R.id.cd_Help);
        cdAbout = (CardView) mView.findViewById(R.id.cd_About);

        cdEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(getActivity(), EditprofileActivity.class);
                SettingsFragment.this.startActivity(profileIntent);
            }
        });

        cdEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountIntent = new Intent(getActivity(), EditaccountActivity.class);
                SettingsFragment.this.startActivity(accountIntent);
            }
        });

        cdPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent privacyIntent = new Intent(getActivity(), PrivacyActivity.class);
                SettingsFragment.this.startActivity(privacyIntent);
            }
        });

        cdHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(getActivity(), HelpActivity.class);
                SettingsFragment.this.startActivity(helpIntent);
            }
        });

        cdAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(getActivity(), AboutActivity.class);
                SettingsFragment.this.startActivity(aboutIntent);
            }
        });

        return mView;
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return  fragment;
    }

}

