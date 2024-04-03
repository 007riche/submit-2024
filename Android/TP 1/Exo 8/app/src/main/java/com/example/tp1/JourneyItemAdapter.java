package com.example.tp1;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.tp1.models.Journeys;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JourneyItemAdapter extends ArrayAdapter<Journeys> {
    public JourneyItemAdapter(Context context, List<Journeys> journeys) {
        super(context, 0, journeys);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Journeys journey = getItem(position);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.journey_item, parent, false);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        }
        TextView from = convertView.findViewById(R.id.from);
        TextView to = convertView.findViewById(R.id.to);
        TextView timeTextView = convertView.findViewById(R.id.timeTextView);
        from.setText(journey.getDepartureCity());
        to.setText(journey.getArrivalCity());

        System.out.println("Temp a partitr de Date(): "+formatter.format(new Date()));
        timeTextView.setText(formatter.format(journey.getJourneyDate()));
        return convertView;
    }
}
