package com.indeema.circularlistview;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

import com.indeema.circularlistview.adapter.MenuItemsAdapter;
import com.indeema.circularlistview.entity.MenuItem;
import com.indeema.circularlistview.widget.CircularListView;
import com.indeema.circularlistview.widget.CircularListViewListener;

public class MainActivity extends AppCompatActivity {

    private CircularListView mCircularListView;
    private boolean mIsAdapterDirty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCircularListView = (CircularListView) findViewById(R.id.circularListView);


        MenuItemsAdapter listAdapter = new MenuItemsAdapter(this, R.layout.menu_item_layout);
        for (int i = 0; i < 5; i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_help_black_48dp));
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
