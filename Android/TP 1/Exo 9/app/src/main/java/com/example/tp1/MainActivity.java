package com.example.tp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.tp1.CustomWidget.ExtentedCalendarView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity {

    private  static ExtentedCalendarView calendarView;
    private static ListView eventListView;
    private static HashMap<String, List<Date>> events;
    ArrayList<String> eventsListItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = (ExtentedCalendarView)  findViewById(R.id.calendarView);
        eventListView = (ListView) findViewById(R.id.eventListView);
        calendarView.invalidate();
        events = getIntent().hasExtra("events") ? (HashMap < String, List < Date >> ) getIntent().getSerializableExtra("events"): new HashMap<String, List < Date >>();
        calendarView.setEvents(events);



        List<String> dateList = events.entrySet().stream().flatMap(stringListEntry -> stringListEntry.getValue().stream().map(date -> {
            SimpleDateFormat dateFormat =new SimpleDateFormat(String.format("dd-MMMM-yyyy '%s' HH:mm", getResources().getString(R.string.at_label)));
            return dateFormat.format(date);
        })).collect(Collectors.toList());
        adapter = new ArrayAdapter<String> (getApplicationContext(), android.R.layout.simple_list_item_1, dateList);

        eventListView.setAdapter(adapter);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String keyString = String.format("%s-%s-%s",year,month,dayOfMonth);
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        String dateString = String.format("%s %s:%s",keyString,hourOfDay,minute);
                    if (events.containsKey(keyString)) {
                        try {
                            Date date = dateFormat.parse(dateString);
                            events.get(keyString).add(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else
                    {
                        try {
                            Date date = dateFormat.parse(dateString);
                            List<Date> eventDate = new ArrayList<Date>(Collections.singleton(date));
                            events.put(keyString, eventDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                        Intent reload = new Intent(MainActivity.this, MainActivity.class);
                        reload.putExtra("events", events);
//                        overridePendingTransition(0,0);
                        startActivity(reload);
                        overridePendingTransition(0,0);
                        finish();
                    }
                }, hour, min, true );
                timePickerDialog.show();

//                timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        Intent reload = new Intent(MainActivity.this, MainActivity.class);
//                        reload.putExtra("events", events);
////                        overridePendingTransition(0,0);
//                        startActivity(reload);
//                        overridePendingTransition(0,0);
//                        finish();
//
//                    }
//                });
               events.forEach((s, dates) -> {
                   System.out.println("Event "+s+": ");
                   dates.forEach(date -> {
                       System.out.println("\t Date: "+date );
                   });
                   System.out.println("Expanded Calendar: "+ calendarView.getEvents().size());
               });


                System.out.println(" Selected Date: "+dayOfMonth+" "+month+" "+year);
            }
        });



    }



}








//                view.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        if (v.isLongClickable()) {
//                            System.out.println("Long click,  Selected Date: "+dayOfMonth+" "+month+" "+year);
//                        }
//                        System.out.println("Long click,  Selected Date: "+dayOfMonth+" "+month+" "+year);
//                        return true;
//                    }
//                });
