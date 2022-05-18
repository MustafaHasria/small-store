package com.mustafa.smallstore.view.datepickerstartandend;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.mustafa.smallstore.R;
import com.mustafa.smallstore.view.datepickerstartandend.fragment.TabFromFragment;
import com.mustafa.smallstore.view.datepickerstartandend.fragment.TabToFragment;

public class MainForDateActivity extends AppCompatActivity {

    CalendarView simpleCalendarView;

    TabLayout tabLayout;

    TabLayout.Tab tabFrom;
    TabLayout.Tab tabTo;

    String from, to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_date_picker_start_and_end);

        tabLayout = findViewById(R.id.activity_main_for_date_picker_start_and_end_tab_layout);
        findViewById(R.id.activity_main_for_date_picker_start_and_end_button_show_dates).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (from != null) {
                    if (to != null) {
                        Toast.makeText(MainForDateActivity.this, "From " + from + " To " + to, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainForDateActivity.this, "Select from date", Toast.LENGTH_SHORT).show();
                }
            }
        });
        setTab();


    }


    private void setTab() {

        tabFrom = tabLayout.newTab();
        tabFrom.setText("FROM");
        //tabOffers.setIcon(R.drawable.ic_home);
        tabLayout.addTab(tabFrom);
        beginTransction(new TabFromFragment());

        tabTo = tabLayout.newTab();
        tabTo.setText("TO");
        tabLayout.addTab(tabTo);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new TabFromFragment();
                        break;
                    case 1:
                        fragment = new TabToFragment();
                        break;

                }
                beginTransction(fragment);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void getSelectedDatefrom(String data) {
        this.from = data;
    }

    public void getSelectedDateto(String data) {
        this.to = data;
    }


    private void beginTransction(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activity_main_for_date_picker_start_and_end_frame_layout_change_tab, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();

    }
}