package com.indeema.circularlistview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.indeema.circularlistview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with AndroidStudio.
 * User: Michael Nester
 * Date: July, 13, 2017
 * Time: 10:01 AM
 */
public class ViewPagerAdapter extends PagerAdapter {

    List<String> arrayModelClasses = new ArrayList<>();

    @SuppressLint("NewApi")
    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);

    }

    public ViewPagerAdapter() {
        super();

    }

    public ViewPagerAdapter(List<String> arrayModelClasses) {
        super();
        this.arrayModelClasses = arrayModelClasses;

    }

    @Override
    public int getCount() {
        return arrayModelClasses.size();
    }

    @Override
    public boolean isViewFromObject(View collection, Object object) {
        return collection == ((View) object);
    }

    @Override
    public Object instantiateItem(View collection, int position) {
        // Inflating layout
        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.menu_item_layout, null);
        TextView itemText = (TextView) view.findViewById(R.id.title);

        try {
            itemText.setText(arrayModelClasses.get(position));

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        ((ViewPager) collection).addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
