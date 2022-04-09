package com.mustafa.smallstore.steppers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mustafa.smallstore.R;

public class ViewPagerAdapter extends PagerAdapter {

    //region Variables
    Context context;

    int[] images = {

            R.raw.happy_user_login,
            R.raw.happy_user_login,
            R.raw.happy_user_login,
            R.raw.happy_user_login

    };

    int[] headings = {

            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three,
            R.string.heading_fourth
    };

    int[] description = {

            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three,
            R.string.desc_fourth
    };

    //endregion

    //region Constructor
    public ViewPagerAdapter(Context context) {
        this.context = context;
    }
    //endregion

    //region Methods
    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.steppers_slider_for_view_pager, container, false);

        ImageView steppersSliderForViewPagerImageView = (ImageView) view.findViewById(R.id.steppers_slider_for_view_pager_animation);
        TextView steppersSliderForViewPagerTextViewTitle = (TextView) view.findViewById(R.id.steppers_slider_for_view_pager_text_view_title);
        TextView steppersSliderForViewPagerTextViewDesc = (TextView) view.findViewById(R.id.steppers_slider_for_view_pager_text_view_desc);

        steppersSliderForViewPagerImageView.setImageResource(images[position]);
        steppersSliderForViewPagerTextViewTitle.setText(headings[position]);
        steppersSliderForViewPagerTextViewDesc.setText(description[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout) object);

    }
    //endregion
}
