package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tp2.Widget.Adapters.ThreeColumnRowAdapter;
import com.example.tp2.models.SensorInfo;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

//    private SensorManager sensorManager;
private List<SensorInfo> sensorInfoList;
private static ListView sensorListView;
ThreeColumnRowAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        sensorListView = (ListView) findViewById(R.id.sensor_list_view);
        sensorInfoList = (List<SensorInfo>) getIntent().getSerializableExtra("sensors");
        listAdapter = new ThreeColumnRowAdapter(this, sensorInfoList);
        sensorListView.setAdapter(listAdapter);
    }
}