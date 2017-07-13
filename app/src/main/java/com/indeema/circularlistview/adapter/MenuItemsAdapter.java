package com.indeema.circularlistview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.indeema.circularlistview.R;
import com.indeema.circularlistview.entity.MenuItem;

import java.util.List;

/**
 * Created by Kostiantyn Bushko on 7/12/17.
 */

public class MenuItemsAdapter extends ArrayAdapter<MenuItem> {

    public MenuItemsAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public MenuItemsAdapter(@NonNull Context context, @LayoutRes int resource, List<MenuItem> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.menu_item_layout, null);
        }

        MenuItem menuItem = getItem(position);

        if (menuItem != null) {
            ImageView imageView = (ImageView)view.findViewById(R.id.iv_icon);
            TextView subTitle = (TextView) view.findViewById(R.id.tv_sub_title);
            imageView.setImageBitmap(menuItem.getImage());
            subTitle.setText(menuItem.getTitle());
        }

        return view;
    }
}
