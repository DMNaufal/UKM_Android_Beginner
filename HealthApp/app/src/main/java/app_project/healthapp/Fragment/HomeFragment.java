package app_project.healthapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import app_project.healthapp.NotificationActivity;
import app_project.healthapp.R;
import app_project.healthapp.SearchActivity;

/**
 * Created by reksa on 07/06/17.
 */
public class HomeFragment extends Fragment {

    Toolbar toolbar;
    public static HomeFragment instance;
    TabLayout tabLayout;
    MapsFragment mapsFragment;
    ListFragment listFrag;
    View mView;
    ImageButton imgBtnN,imgBtnS,imgbtn;
    Intent intent, intent1;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        imgBtnN = (ImageButton) mView.findViewById(R.id.btn_notification);
        imgBtnS = (ImageButton) mView.findViewById(R.id.btn_search);
        imgBtnN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                switch(v.getId()){

                    case R.id.btn_notification:
                        intent = new Intent(getActivity(), NotificationActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        imgBtnS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                switch(v.getId()){

                    case R.id.btn_search:
                        intent1 = new Intent(getActivity(), SearchActivity.class);
                        startActivity(intent1);
                        break;
                }
            }
        });

        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        toolbar = (Toolbar)mView.findViewById(R.id.toolBarHome);
        tabLayout = (TabLayout)mView.findViewById(R.id.tabLayout);

        bindWidgetsWithAnEvent();
        setupTabLayout();
    }

    public static HomeFragment getInstance() {
        return instance;
    }

    private void bindWidgetsWithAnEvent() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(mapsFragment);
                break;
            case 1 :
                replaceFragment(listFrag);
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainView, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void setupTabLayout() {
        mapsFragment = new MapsFragment();
        listFrag = new ListFragment();

        tabLayout.addTab(tabLayout.newTab().setText("TAMPILAN MAPS"),true);
        tabLayout.addTab(tabLayout.newTab().setText("TAMPILAN LIST"));
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return  fragment;
    }
}
