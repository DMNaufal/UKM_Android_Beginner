package app_project.nyobafragment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VivzAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.drawerList);
        adapter = new VivzAdapter((Context) this.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_local_hospital_black_24dp};
        String[] titles = {"LAA", "LII", "LUU", "LEE", "LOO"};
        for (int i=0;i<titles.length && i<icons.length;i++)
        {
            Information current = new Information();
            current.iconId=icons[i];
            current.title=titles[i];
            data.add(current);
        }
        return data;
    }
}
