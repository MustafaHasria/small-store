package com.mustafa.smallstore.view.datepickerstartandend.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.view.datepickerstartandend.MainForDateActivity;
import com.mustafa.smallstore.view.datepickerstartandend.views.CalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class TabFromFragment extends Fragment {

    private View view;
    private CalendarView calendarView;

    public TabFromFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_from_for_date_picker_start_and_end, container, false);
        initView(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getSelectedDate();

    }

    private void getSelectedDate() {
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        calendarView.updateCalendar(events);

        calendarView.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(Date date) {
                DateFormat df = SimpleDateFormat.getDateInstance();
                ((MainForDateActivity) getActivity()).getSelectedDatefrom(df.format(date));
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView(View view) {
        calendarView = view.findViewById(R.id.fragment_tab_from_for_date_picker_start_and_end_calendar_view);
    }


}
