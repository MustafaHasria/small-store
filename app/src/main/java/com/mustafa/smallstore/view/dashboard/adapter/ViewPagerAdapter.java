package com.mustafa.smallstore.view.dashboard.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mustafa.smallstore.view.dashboard.fragment.AccountFragment;
import com.mustafa.smallstore.view.dashboard.fragment.CategoryFragment;
import com.mustafa.smallstore.view.dashboard.fragment.ProductFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new CategoryFragment();
            case 2:
                return new AccountFragment();
            default:
                return new ProductFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
