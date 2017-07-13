package com.indeema.circularlistview;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;

import com.indeema.circularlistview.adapter.MenuItemsAdapter;
import com.indeema.circularlistview.entity.MenuItem;
import com.indeema.circularlistview.widget.CircularListView;
import com.indeema.circularlistview.widget.CircularListViewListener;

/**
 * Created by Kostiantyn Bushko on 7/13/17.
 */

public class MenuActivity extends Activity {

    private static final String TAG = MenuActivity.class.getSimpleName();

    private CircularListView mCircularListView;

    private boolean mIsAdapterDirty = true;

    private int[] drawableResources = new int[] {
            R.drawable.ic_album_black_48dp,
            R.drawable.ic_group_work_black_48dp,
            R.drawable.ic_help_black_48dp,
            R.drawable.ic_info_black_48dp,
            R.drawable.ic_pause_circle_filled_black_48dp,
            R.drawable.ic_play_circle_filled_black_48dp,
            R.drawable.ic_stars_black_48dp,
            R.drawable.ic_watch_later_black_48dp,
            R.drawable.ic_swap_vertical_circle_black_48dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mCircularListView = (CircularListView) findViewById(R.id.circularListView);


        MenuItemsAdapter listAdapter = new MenuItemsAdapter(this, R.layout.menu_item_layout);
        for (int i = 0; i < drawableResources.length; i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.setImage(BitmapFactory.decodeResource(getResources(), drawableResources[i]));
            menuItem.setTitle("Item : " + Integer.toString(i));
            listAdapter.add(menuItem);
        }

        Display display = getWindowManager().getDefaultDisplay();
        mCircularListView.setRadius(Math.min(300, display.getWidth() / 2));
        mCircularListView.setAdapter(listAdapter);
        mCircularListView.scrollFirstItemToCenter();

        mCircularListView.setCircularListViewListener(new CircularListViewListener() {
            @Override
            public void onCircularLayoutFinished(CircularListView circularListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                refreshCircular();
            }
        });

        mCircularListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, " On Item Click -> " + position + ", id -> " + id);
            }
        });
    }

    void refreshCircular() {
//        if (mIsAdapterDirty) {
//            mCircularListView.scrollFirstItemToCenter();
//            mIsAdapterDirty = false;
//        }
//
//        TextView centerView = (TextView) mCircularListView.getCentralChild();
//
//        if (centerView != null) {
//            centerView.setTextColor(getResources().getColor(R.color.center_text));
//        }
//        for (int i = 0; i < mCircularListView.getChildCount(); i++) {
//            TextView view = (TextView) mCircularListView.getChildAt(i);
//            if (view != null && view != centerView) {
//                view.setTextColor(getResources().getColor(R.color.default_text));
//            }
//        }
    }
}
