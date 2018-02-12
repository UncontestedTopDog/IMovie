package com.example.administrator.imovie.views.common.stagephoto;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.imovie.models.MovieImageAll;
import com.example.administrator.imovie.R;
import com.example.administrator.imovie.views.common.MovieImageAllActivity;
import com.google.gson.Gson;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class StagePhotoBrierfCard extends RelativeLayout {
    private static final String TAG = "StagePhotoBrierfCard";
    private TextView imageAll;

    public StagePhotoBrierfCard(Context context) {
        super(context);
        initView();
    }

    public StagePhotoBrierfCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public StagePhotoBrierfCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.stage_photo_brief_card, this);
        imageAll = (TextView) findViewById(R.id.image_all);
    }


    public void bindData(final MovieImageAll movieImageAll) {
        if (movieImageAll.getImages().size() == 0) {
            Log.d(TAG, "bind data error movieImageAll = null");
            return;
        }
        imageAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieImageAllActivity.class);
                intent.putExtra("MovieImageAll",new Gson().toJson(movieImageAll));
                getContext().startActivity(intent);
            }
        });
        onLoad(movieImageAll);

    }

    private void onLoad(MovieImageAll movieImageAll) {

        if (movieImageAll.getImages().size() > 0) {
            StagePhotoRecyclerView stagePhotoRecyclerView = (StagePhotoRecyclerView) findViewById(R.id.stage_photo_list);

            stagePhotoRecyclerView.bindData(movieImageAll);
        }

    }
}
