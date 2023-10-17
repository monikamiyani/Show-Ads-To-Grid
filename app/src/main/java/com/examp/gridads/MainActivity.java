package com.examp.gridads;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    GridLayoutManager manager;
    List<DataModel> dataModelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        manager = new GridLayoutManager(this, 2);
        //set spancount is 3
        //manager = new GridLayoutManager(this, 3);

        recyclerView.setLayoutManager(manager);
        customAdapter = new CustomAdapter(this);
        recyclerView.setAdapter(customAdapter);

        for (int i = 1; i <= 15; i++) {

            dataModelArrayList.add(new DataModel(TypeInter.ITEM,R.mipmap.ic_launcher));

            //show ads for every items
            if (i % 4 == 0) {
                dataModelArrayList.add(new DataModel(TypeInter.AD));
            }
        }

        customAdapter.submitList(dataModelArrayList);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                int viewType = customAdapter.getItemViewType(position);

                switch (viewType) {
                    case CustomAdapter.viewTypeItem:
                        return 1;
                    // if set spancount is 3 then set return value is 3
                    // case CustomAdapter.viewTypeAd:
                    // return 3;
                    case CustomAdapter.viewTypeAd:
                        return 2;
                    default:
                        return -1;
                }
            }
        });

    }

}
