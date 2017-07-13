package com.indeema.circularlistview;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.indeema.circularlistview.adapter.MenuItemsAdapter;
import com.indeema.circularlistview.entity.MenuItem;
import com.indeema.circularlistview.widget.CircularListView;
import com.indeema.circularlistview.widget.CircularListViewListener;

/**
 * Created by Kostiantyn Bushko on 7/13/17.
 */

public class MenuActivity extends Activity implements MenuItemsAdapter.MenuItemAdapterListener {

    private static final String TAG = MenuActivity.class.getSimpleName();

    private CircularListView mCircularListView;

    private RelativeLayout mRoot;

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


        mRoot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int viewHeight = mRoot.getHeight() / 4;
                MenuItemsAdapter listAdapter = new MenuItemsAdapter(MenuActivity.this, R.layout.menu_item_layout, viewHeight, MenuActivity.this);
                for (int i = 0; i < 5; i++) {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_help_black_48dp));
                    menuItem.setTitle("Item : " + Integer.toString(i));
                    listAdapter.add(menuItem);
                }

                mCircularListView.setRadius(Math.min(300, display.getWidth() / 2));
                mCircularListView.setAdapter(listAdapter);
                mCircularListView.scrollFirstItemToCenter();

                return false;
            }
        });

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
    }

    @Override
    public void onItemClick(int position) {

    }
}
