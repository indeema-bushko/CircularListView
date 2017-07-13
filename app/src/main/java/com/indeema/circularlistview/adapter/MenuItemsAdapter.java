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

    private MenuItemAdapterListener mListener;
    private int mViewHeight;

    public MenuItemsAdapter(@NonNull Context context, @LayoutRes int resource, int viewHeight, MenuItemAdapterListener listener) {
        super(context, resource);
        mViewHeight = viewHeight;
        this.mListener = listener;
    }

    public MenuItemsAdapter(@NonNull Context context, @LayoutRes int resource, List<MenuItem> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.menu_item_layout, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    mViewHeight));
        }

        MenuItem menuItem = getItem(position);

        if (menuItem != null) {
            ImageView imageView = (ImageView)view.findViewById(R.id.iv_icon);
            TextView textView = (TextView)view.findViewById(R.id.text_tv);
            textView.setText(menuItem.getTitle());
            imageView.setImageBitmap(menuItem.getImage());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(position);
                }
            });
        }

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public interface MenuItemAdapterListener {

        void onItemClick(int position);
    }
}
