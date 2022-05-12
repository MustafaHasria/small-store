package com.mustafa.smallstore.view.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mustafa.smallstore.R;
import com.mustafa.smallstore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //region Component
    ActivityMainBinding binding;
    private BottomNavigationView bottomNavigation;
    //endregion

    //region Variable
    ViewPagerAdapter viewPagerAdapter;
    private ViewPager mainViewPager;
    //endregion


    //region Life Cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupViewPager();

        binding.mainActivityViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        binding.mainActivityBottomNavigation.getMenu().findItem(R.id.navigation_product).setChecked(true);
                        break;
                    case 1:
                        binding.mainActivityBottomNavigation.getMenu().findItem(R.id.navigation_category).setChecked(true);
                        break;
                    case 2:
                        binding.mainActivityBottomNavigation.getMenu().findItem(R.id.navigation_account).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.mainActivityBottomNavigation.setOnItemSelectedListener(item ->
        {
            switch (item.getItemId()) {
                case R.id.navigation_product:
                    binding.mainActivityViewPager.setCurrentItem(0);
                    break;
                case R.id.navigation_category:
                    binding.mainActivityViewPager.setCurrentItem(1);
                    break;
                case R.id.navigation_account:
                    binding.mainActivityViewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });
    }
    //endregion


    //region Setups
    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.mainActivityViewPager.setAdapter(viewPagerAdapter);
    }
    //endregion
}