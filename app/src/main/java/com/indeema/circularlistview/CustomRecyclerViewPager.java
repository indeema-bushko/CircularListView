/*
 * Copyright (C) 2017 MileCatcher, Inc.
 * http://MileCatcher.com
 * All rights reserved
 */

package com.indeema.circularlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

/**
 * Created with AndroidStudio.
 * User: Michael Nester
 * Date: March, 14, 2017
 * Time: 3:33 PM
 */
public class CustomRecyclerViewPager extends RecyclerViewPager {

    private View mEmptyView;

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public CustomRecyclerViewPager(Context context) {
        super(context);
    }

    public CustomRecyclerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void checkIfEmpty() {
        if (mEmptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible =
                    getAdapter().getItemCount() == 0;
            mEmptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }

        super.setAdapter(adapter);

        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }

        checkIfEmpty();
    }

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
        checkIfEmpty();
    }

    public View getEmptyView() {
        return mEmptyView;
    }
}