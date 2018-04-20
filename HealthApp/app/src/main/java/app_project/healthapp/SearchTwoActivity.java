package app_project.healthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchTwoActivity extends AppCompatActivity {
    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView list_view_search;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_two);

        list_view_search = (ListView)findViewById(R.id.list_view_search);
        editText = (EditText)findViewById(R.id.text_item);

        iniList();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")){
                    //reset list_view_search
                    iniList();
                }
                else {
                    // perform search
                    searchItem(s.toString());
                }
            }
        });
    }

    public void searchItem(String textToSearch){
        for (String item:items){
            if (!item.contains(textToSearch)){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void iniList(){
        items = new String[]{"Rumah Sakit Dinda", "Rumah Sakit Medika", "Rumah Sakit Permata Hijau",
                "Rumah Sakit Qadr", "Rumah Sakit Siloam"};
        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(SearchTwoActivity.this, R.layout.list_search, R.id.text_item, listItems);
        list_view_search.setAdapter(adapter);
    }
}
