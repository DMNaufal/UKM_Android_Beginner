package app_project.healthapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DMNaufal on 09/06/2017.
 */

public class TabDetail extends Fragment {
    private static final String TAG = "TabDetail";

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_detail,container,false);

        return view;
    }
}
