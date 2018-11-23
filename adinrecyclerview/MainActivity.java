package com.example.alitamoor.adinrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Object> mRecyclerViewItems = new ArrayList<>();
    private List<Object> contentItems = new ArrayList<>();
    private List<AdRequest> adsItems = new ArrayList<AdRequest>();

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 150; i++) {
            contentItems.add(new TextDM("String " + i));
            //Log.i("loop", "onCreate: " + contentItems.size());
        }

        int contentItemsEvenSize = contentItems.size() % 2 == 0 ? contentItems.size():contentItems.size()+1;
        final int adsInterval = 8;
        int newLength = contentItems.size() + (contentItemsEvenSize/(adsInterval));

        int counter = 0;
        for (int i = 0; i < newLength; i++) {
            if(i % (adsInterval + 1) == 0){
                counter+=1;
                mRecyclerViewItems.add(new AdsDM(new AdRequest.Builder().build()));
            }else {
                mRecyclerViewItems.add(contentItems.get(i-counter));
            }
            Log.i("onCreate", "Item["+i+"] = "+ mRecyclerViewItems.get(i).getClass().getSimpleName());
        }

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position % (adsInterval +1) == 0){
                    return 2;
                }
                return 1    ;
            }
        });

        recyclerView = findViewById(R.id.recyclerViewID);
        layoutManager = new GridLayoutManager(this,2);
        recyclerAdapter = new RecyclerAdapter(this,mRecyclerViewItems);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyclerAdapter);

    }
}
