package com.mustafa.smallstore.activity.stepper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.activity.login.Login;
import com.mustafa.smallstore.activity.welcome.WelcomeAfterStepperActivity;
import com.mustafa.smallstore.databinding.ActivityStepperLightBinding;

import java.util.Objects;

public class StepperLight extends AppCompatActivity {

    //region Variable
    private static final int MAX_STEP = 4;
    private final String[] about_title_array = {
            "Ready To Shopping",
            "At Your Service",
            "We Support Delivery",
            "Manage Accounts"
    };
    private final String[] about_description_array = {
            "Choose your Product, plan Your Shopp. Pick the best place for Your Shopping",
            "Select the day, Choose Your Product. We give you the best Service. We guarantee!",
            "Safe and Comfort Shopping is our priority. Professional crew and services.",
            "We manage the accounts of delegates in a safe and organized manner",
    };
    private final int[] about_images_array = {
            R.drawable.shopping1,
            R.drawable.shopping2,
            R.drawable.shopping3,
            R.drawable.shopping4
    };
    //region Component
    ActivityStepperLightBinding binding;
    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position);

            if (position == about_title_array.length - 1) {
                binding.btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(StepperLight.this, WelcomeAfterStepperActivity.class);
                        startActivity(i);
                    }
                });

            }
            if (position == about_title_array.length - 1) {
                binding.btClose.setVisibility(View.GONE);
                binding.btCloses.setVisibility(View.GONE);
                binding.btnNext.setText(getString(R.string.GOT_IT));
                binding.btnNext.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                binding.btnNext.setTextColor(Color.WHITE);

            } else {
                binding.btClose.setVisibility(View.VISIBLE);
                binding.btCloses.setVisibility(View.VISIBLE);
                binding.btnNext.setText(getString(R.string.NEXT));
                binding.btnNext.setBackgroundColor(getResources().getColor(R.color.grey_10));
                binding.btnNext.setTextColor(getResources().getColor(R.color.grey_90));
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    private ViewPager viewPager;
    //endregion
    private MyViewPagerAdapter myViewPagerAdapter;
    //endregion
    private Button btnNext;
    //endregion

    //region LifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Handle the splash screen transition.
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        binding = ActivityStepperLightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        initComponent();

        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    //region Methods
    private void initComponent() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        // adding bottom dots
        bottomProgressDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() + 1;

                if (current < MAX_STEP) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    finish();
                }
            }
        });

        binding.btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(StepperLight.this, Login.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void bottomProgressDots(int current_index) {
        //LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        binding.layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            binding.layoutDots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_stepper_light, container, false);
            ((TextView) view.findViewById(R.id.title)).setText(about_title_array[position]);
            ((TextView) view.findViewById(R.id.description)).setText(about_description_array[position]);
            ((ImageView) view.findViewById(R.id.image)).setImageResource(about_images_array[position]);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return about_title_array.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
    //endregion
}