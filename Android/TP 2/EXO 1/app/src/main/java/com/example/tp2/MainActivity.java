package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tp2.models.SensorInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static SensorManager sensorManager;
    private static Button mainActivityDetectionButton;
    private static List<SensorInfo> sensorInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityDetectionButton = (Button) findViewById(R.id.main_activity_detection_button);
        mainActivityDetectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensAct = new Intent(MainActivity.this, SensorActivity.class);
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                List<Sensor> sensorList= sensorManager.getSensorList(Sensor.TYPE_ALL);
                sensorInfoList = new ArrayList<SensorInfo>();
                sensorList.forEach(sensor -> {
                    sensorInfoList.add(new SensorInfo(sensor.getName(), sensor.getStringType().replace("android.sensor.", ""), String.format("%s",sensor.getVersion()) ));
                });
                sensAct.putExtra("sensors", (Serializable) sensorInfoList);
                startActivity(sensAct);
                finish();
            }
        });


//        sensorList.forEach(sensor -> {
//            Log.i("ANDROID_SENSOR", "++++++++++++++++++ The current sensor is: "+sensor.getName());
//            Log.i("ANDROID_SENSOR", "The current sensor TYPE is: "+sensor.getStringType());
//            Log.i("ANDROID_SENSOR", "The current sensor VERSION is: "+sensor.getVersion());
//            Log.i("ANDROID_SENSOR", "The current sensor VENDOR is: "+sensor.getVendor());
//            Log.i("ANDROID_SENSOR", "The current sensor POWER CONSUMPTION is: "+sensor.getPower());
//            Log.i("ANDROID_SENSOR", "The current sensor RESOLUTION is: "+sensor.getResolution());
//            Log.i("ANDROID_SENSOR", "The current sensor MINIMUM DELAY is: "+sensor.getMinDelay()+"\n\n");
//        });
//
//        Sensor gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
//        Log.i("ANDROID_SENSOR", "defaultGravitySensor = "+gravitySensor.getName());

//        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
//            Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//            Log.i("ANDROID_SENSOR", "The default magnetic field sensor is: "+magneticSensor.getName()+". The Constructor is:  "+magneticSensor.getVendor());
//            List<Sensor> gravitySensors = sensorManager.getSensorList(Sensor.TYPE_GRAVITY);
//            gravitySensors.forEach(sensor -> {
//                if (sensor.getVendor().contains("Google Inc.") && sensor.getVersion()==3) {
//                    Log.i("ANDROID_SENSOR", "This phone has a google manufactured gravity sensor: "+sensor.getName()+":"+sensor.getVersion());
//                }
//            });
//        } else {
//            Log.i("ANDROID_SENSOR", "This phone does not have a magnetic field sensor.");
//        }

    }
}