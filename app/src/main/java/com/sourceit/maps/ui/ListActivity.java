package com.sourceit.maps.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sourceit.maps.R;
import com.sourceit.maps.ui.Json.Result;
import com.sourceit.maps.utils.L;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    public static final String SELECTED = "selected";
    public static final String LIST = "list";
    RecyclerView list;
    LinearLayoutManager layoutManager;
    ArrayList<String> barNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        layoutManager = new LinearLayoutManager(getBaseContext());
        list = (RecyclerView) findViewById(R.id.recycler_list);
        list.setLayoutManager(layoutManager);

        getBars();

        list.setAdapter(new MyRecyclerAdapter(barNames, new OnItemClickWatcher<String>() {
            @Override
            public void onItemClick(View v, int position, String item) {
                L.d("click " + item);
                Intent intent = new Intent(ListActivity.this, MapActivity.class);
                intent.putExtra(SELECTED, position);
                intent.putExtra(LIST, true);
                startActivity(intent);
            }
        }));
    }

    private void getBars() {
        barNames = new ArrayList<>();
        for (Result result : Data.barsList.results) {
            barNames.add(result.name);
        }
    }
}
