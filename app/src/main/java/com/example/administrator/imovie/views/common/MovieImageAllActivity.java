package com.example.administrator.imovie.views.common;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import com.example.administrator.imovie.base.BaseActivity;
import com.example.administrator.imovie.models.MovieImageAll;
import com.example.administrator.imovie.R;
import com.example.administrator.imovie.views.common.stagephoto.StagePhotoRecyclerView;
import com.google.gson.Gson;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class MovieImageAllActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private StagePhotoRecyclerView mRecyclerView;
    private TabLayout.Tab all;
    private TabLayout.Tab stage;
    private TabLayout.Tab poster;
    private TabLayout.Tab work;
    private TabLayout.Tab news;
    private TabLayout.Tab envelopell;
    public static MovieImageAll movieImageAll ;
    private TitleBar mTitleBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_image_all);
        mTabLayout = (TabLayout) findViewById(R.id.image_style_tablayout);
        mRecyclerView = (StagePhotoRecyclerView) findViewById(R.id.photo_grid);
        mTitleBar = (TitleBar) findViewById(R.id.movie_stage_title);
        String s=getIntent().getStringExtra("MovieImageAll");
        movieImageAll = new Gson().fromJson(s,MovieImageAll.class);
        mRecyclerView.setLimit(false);
        all  = mTabLayout.newTab().setText("全部");
        stage  = mTabLayout.newTab().setText("剧照");
        poster  = mTabLayout.newTab().setText("海报");
        work  = mTabLayout.newTab().setText("工作照");
        news  = mTabLayout.newTab().setText("新闻图片");
        envelopell  = mTabLayout.newTab().setText("封面");
        mTabLayout.addTab(all);
        mTabLayout.addTab(stage);
        mTabLayout.addTab(poster);
        mTabLayout.addTab(work);
        mTabLayout.addTab(news);
        mTabLayout.addTab(envelopell);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == all)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.ALL);
                else if (tab == stage)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.STAGE);
                else if (tab == poster)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.POSTER);
                else if (tab == news)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.NEWS);
                else if (tab == work)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.WORK);
                else if (tab == envelopell)
                    mRecyclerView.setStyle(StagePhotoRecyclerView.ENVELOPELL);
                mRecyclerView.bindData(movieImageAll);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        mTitleBar.setTitle("电影美图");
        mTitleBar.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView.bindData(movieImageAll);
    }

}
