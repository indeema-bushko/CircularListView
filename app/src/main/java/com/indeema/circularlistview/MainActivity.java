package com.indeema.circularlistview;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.indeema.circularlistview.adapter.MenuItemsAdapter;
import com.indeema.circularlistview.entity.MenuItem;
import com.indeema.circularlistview.widget.CircularListView;

public class MainActivity extends AppCompatActivity implements MenuItemsAdapter.MenuItemAdapterListener {

    private CircularListView mCircularListView;
    private boolean mIsAdapterDirty = true;
    private Button mButton;
    private RelativeLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.open_menu);
        mRoot = (RelativeLayout) findViewById(R.id.root);

        mCircularListView = (CircularListView) findViewById(R.id.circularListView);

        mRoot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                Display display = getWindowManager().getDefaultDisplay();

                Point size = new Point();
                display.getSize(size);
//                int viewHeight = size.y / 4;
                int viewHeight = mRoot.getHeight() / 4;
                MenuItemsAdapter listAdapter = new MenuItemsAdapter(MainActivity.this, R.layout.menu_item_layout, viewHeight, MainActivity.this);
                for (int i = 0; i < 5; i++) {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_help_black_48dp));
                    menuItem.setTitle("Item : " + Integer.toString(i));
                    listAdapter.add(menuItem);
                }

                mCircularListView.setRadius(200);
                mCircularListView.setAdapter(listAdapter);

                return false;
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCircularListView.getVisibility() == View.GONE) {
                    openMenu();
                } else {
                    hideMenu();
                }
            }
        });
    }

    private void openMenu() {
        mCircularListView.animate()
                .alpha(1.0f)
                .setDuration(300)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        mCircularListView.setAlpha(0);
                        mCircularListView.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

    private void hideMenu() {
        mCircularListView.animate()
                .alpha(0)
                .setDuration(300)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        mCircularListView.setVisibility(View.GONE);
                    }
                })
                .start();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Click position: " + position, Toast.LENGTH_SHORT).show();
        hideMenu();
    }
}
