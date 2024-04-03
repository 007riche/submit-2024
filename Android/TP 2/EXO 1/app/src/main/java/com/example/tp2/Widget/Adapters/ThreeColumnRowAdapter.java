package com.example.tp2.Widget.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tp2.R;
import com.example.tp2.models.SensorInfo;

import java.util.List;

public class ThreeColumnRowAdapter extends ArrayAdapter<SensorInfo> {


    public ThreeColumnRowAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ThreeColumnRowAdapter(@NonNull Context context, @NonNull List<SensorInfo> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SensorInfo sensorInfo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
        }
        TextView sensor_name = convertView.findViewById(R.id.sensor_name);
        TextView sensor_type = convertView.findViewById(R.id.sensor_type);
        TextView sensor_version = convertView.findViewById(R.id.sensor_version);

        sensor_name.setText(sensorInfo.getName());
        sensor_type.setText(sensorInfo.getType());
        sensor_version.setText(sensorInfo.getVersion());
        return convertView;
    }
}
