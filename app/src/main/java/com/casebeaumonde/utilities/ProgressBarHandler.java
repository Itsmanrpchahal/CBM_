package com.casebeaumonde.utilities;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class ProgressBarHandler {
    private ProgressBar mProgressBar;
    private Context mContext;
    private View _baseView;
    private View _hideView;
    public ProgressBarHandler(Context context) {
        mContext = context;

        ViewGroup layout = (ViewGroup) _baseView;//((Activity) context).findViewById(android.R.id.content).getRootView();

        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        mProgressBar.setIndeterminate(true);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        RelativeLayout rl = new RelativeLayout(context);

        rl.setGravity(Gravity.CENTER);
        rl.addView(mProgressBar);

        layout.addView(rl, params);

        hide();
    }

    public void show() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    public void hide() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}