package com.indeema.circularlistview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by Kostiantyn Bushko on 7/12/17.
 */

public class CustomViewGroup extends ViewGroup {

    private static final String TAG = CustomViewGroup.class.getSimpleName();

    public CustomViewGroup(Context context) {
        super(context);
        initialize(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, TAG + " onLayout -> changed : " + changed + ", left : " + left + ", top : " + top + ", right : " + right + ", bottom : " + bottom);
    }
}
