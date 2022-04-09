package com.mustafa.smallstore.steppers;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.activity.LoginActivity;
import com.mustafa.smallstore.databinding.ActivityMainSteppersBinding;

public class MainActivitySteppers extends AppCompatActivity {

    //region Components
    ActivityMainSteppersBinding binding;
    TextView[] dots;
    ViewPager mSLideViewPager;
    //endregion

    //region Variables
    ViewPagerAdapter viewPagerAdapter;
    //endregion
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpIndicator(position);

            if (position > 0) {
                binding.activityMainSteppersButtonBack.setVisibility(View.VISIBLE);
            } else {
                binding.activityMainSteppersButtonBack.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainSteppersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.activityMainSteppersButtonBack.setOnClickListener(v -> {

            if (getItem(0) > 0) {

                binding.activityMainSteppersViewPagerSlide.setCurrentItem(getItem(-1), true);
            }

        });

        binding.activityMainSteppersButtonNext.setOnClickListener(v -> {

            if (getItem(0) < 3)
                binding.activityMainSteppersViewPagerSlide.setCurrentItem(getItem(1), true);

            else {
                Intent i = new Intent(MainActivitySteppers.this, LoginActivity.class);
                startActivity(i);
                finish();
            }

        });

        binding.activityMainSteppersButtonSkip.setOnClickListener(v -> {
            Intent i = new Intent(MainActivitySteppers.this, LoginActivity.class);
            startActivity(i);
            finish();
        });


        viewPagerAdapter = new ViewPagerAdapter(this);
        binding.activityMainSteppersViewPagerSlide.setAdapter(viewPagerAdapter);

        setUpIndicator(0);
        binding.activityMainSteppersViewPagerSlide.addOnPageChangeListener(viewListener);


    }

    //region Methods
    public void setUpIndicator(int position) {

        dots = new TextView[4];
        binding.activityMainSteppersLinearLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            //to show dots in main screen
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            binding.activityMainSteppersLinearLayout.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));

    }

    private int getItem(int i) {

        return binding.activityMainSteppersViewPagerSlide.getCurrentItem() + i;
    }
    //endregion
}