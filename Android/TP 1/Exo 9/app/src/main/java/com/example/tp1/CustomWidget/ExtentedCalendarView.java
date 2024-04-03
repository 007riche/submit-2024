package com.example.tp1.CustomWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.util.Calendar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.tp1.R;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SimpleTimeZone;

public class ExtentedCalendarView extends CalendarView implements NavigationView.OnNavigationItemSelectedListener {

    private  HashMap<String, List<Date>> events = new HashMap<String, List<Date>>();
    private CalendarView.OnLongClickListener onLongClickListener;

    private OnDateChangeListener onDateChangeListener;


    public ExtentedCalendarView(@NonNull Context context) {
        super(context);
        invalidate();
//        init(context, null);
    }

    public ExtentedCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        init(context, attrs);
    }

    public ExtentedCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        invalidate();
//        init(context, attrs);
    }

    public ExtentedCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        invalidate();
//        init(context, attrs);
    }



    public void setEvents(HashMap<String, List<Date>> events) {
        this.events = events;
    }

    public HashMap<String, List<Date>> getEvents() {
        return events;
    }

    private void init(Context context, AttributeSet attrs) {


        setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            if (onDateChangeListener != null) {
                onDateChangeListener.onSelectedDayChange(view, year, month, dayOfMonth);
            }
        });
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ ON DRAW IS CALLED FROM ON OVERRIDEN ON DRAW");

        // Get the current month and year
        java.util.Calendar c = java.util.Calendar.getInstance();

        int month = c.get(java.util.Calendar.MONTH);
        int year = c.get(java.util.Calendar.YEAR);

        // Set up the paint object
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        // Draw a custom background for the highlighted dates
//        for (Date date : dates) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//
//            if (calendar.get(Calendar.MONTH) == month && calendar.get(Calendar.YEAR) == year) {
//                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//                int cellWidth = getWidth() / 7;
//                int cellHeight = getHeight() / 6;
//                int x = (dayOfMonth - 1) % 7 * cellWidth;
//                int y = (dayOfMonth - 1) / 7 * cellHeight;
//                paint.setColor(Color.RED);
//                canvas.drawRect(x, y, x + cellWidth, y + cellHeight, paint);
//            }
//        }

//        System.out.println("////////////////////////////+++++++++++++++++++++++++++++++++++++++++++++++++****************************************Draw called");
        this.events.forEach((s, currentEventDates) -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = dateFormat.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int cellWidth = getWidth() / 7;
            int cellHeight = getHeight() / 6;
            int x = (dayOfMonth - 1) % 7 * cellWidth;
            int y = (dayOfMonth - 1) / 7 * cellHeight;
            if (currentEventDates.size()==0) {
                paint.setColor(getResources().getColor(R.color.white));
            } else if (currentEventDates.size()<=3) {
                paint.setColor(getResources().getColor(R.color.indigo_500));
            }
            else if (currentEventDates.size()<=7) {
                paint.setColor(getResources().getColor(R.color.orange_700));
            }
            else {
                paint.setColor(getResources().getColor(R.color.warning_red));
            }

            canvas.drawRect(x, y, x + cellWidth, y + cellHeight, paint);
        });

    }


    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}


//
//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//
//        for (int i = 0; i < getChildCount(); i++) {
//            final View child = getChildAt(i);
//            child.setOnLongClickListener(new OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    // get the date of the cell that was clicked
//                    Date date = new Date(getDate());
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(date);
//                    calendar.add(Calendar.DATE, child.getTag() != null ? (int) child.getTag() : 0);
//                    Date clickedDate = calendar.getTime();
//
//                    // do something with the clicked date
//                    System.out.println("CustomCalendarView Clicked on date: " + clickedDate.toString());
//
//                    return true;
//                }
//            });
//        }
//    }
//
//    @Override
//    public void setOnDateChangeListener(OnDateChangeListener listener)  {
//        onDateChangeListener = listener;
//        super.setOnDateChangeListener(listener);
//    }
//
//
//    public void setOnLongClickListener(OnLongClickListener listener) {
//        super.setOnLongClickListener(listener);
//
//        System.out.println("OCCURED LONG CLICK EVENT FROM THE CLASS");
//        onLongClickListener = listener;
//    }
//

