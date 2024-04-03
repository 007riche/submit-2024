package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tp2.Widget.Adapters.ThreeColumnRowAdapter;
import com.example.tp2.models.SensorInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private SensorManager sensorManager;
private List<SensorInfo> sensorInfoList;
private static ListView sensorListView;
ThreeColumnRowAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> sensorsTypes = new ArrayList<>();

        sensorsTypes.add("TYPE_ACCELEROMETER");
        sensorsTypes.add("TYPE_GRAVITY");
        sensorsTypes.add( "TYPE_TEMPERATURE");
        sensorsTypes.add("TYPE_ORIENTATION");
        setContentView(R.layout.activity_sensor);

//        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        List<Sensor> sensorList= sensorManager.getSensorList(Sensor.TYPE_ALL);

//        sensorInfoList = new ArrayList<SensorInfo>();
//        sensorList.forEach(sensor -> {
//            sensorInfoList.add(new SensorInfo(sensor.getName(), sensor.getStringType().replace("android.sensor.", ""), String.format("%s",sensor.getVersion()) ));
//        });

        sensorListView = (ListView) findViewById(R.id.sensor_list_view);
        sensorInfoList = (List<SensorInfo>) getIntent().getSerializableExtra("sensors");
        listAdapter = new ThreeColumnRowAdapter(this, sensorInfoList);
//        sensorListView.setAdapter(listAdapter);
    }
}