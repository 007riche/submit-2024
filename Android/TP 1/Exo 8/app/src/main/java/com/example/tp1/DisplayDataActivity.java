package com.example.tp1;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tp1.models.Journeys;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DisplayDataActivity extends AppCompatActivity {
    private ListView availabilityListView;
    private Button goBack;
    List<Journeys> journeysData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        journeysData = (List<Journeys>) getIntent().getSerializableExtra("journeys");
        System.out.println("Size data: "+journeysData.size());
        availabilityListView = (ListView) findViewById(R.id.availabilityListView);
        goBack = (Button) findViewById(R.id.goBack);

//        ArrayAdapter adapter = new ArrayAdapter<Journeys>(this, journeysData);

        JourneyItemAdapter adapter = new JourneyItemAdapter(this, journeysData);
        availabilityListView.setAdapter(adapter);


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackIntent =  new Intent(DisplayDataActivity.this, MainActivity.class);
                startActivity(goBackIntent);
                finish();
            }
        });


    }
}

