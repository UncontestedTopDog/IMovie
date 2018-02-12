package com.example.administrator.imovie.views.home;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.imovie.models.ComingMovie;
import com.example.administrator.imovie.models.ShowingMovie;
import com.example.administrator.imovie.MainActivity;
import com.example.administrator.imovie.R;
import java.util.List;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class MovieBrierfCard extends RelativeLayout {
    private static final String TAG = "MovieBrierfCard";
    private TextView amount;
    private TextView style;
    // 即将上映的电影资料中最大显示个数
    private static final int MAX_SHOW_NUM = 15;
    public MovieBrierfCard(Context context) {
        super(context);
        initView();
    }

    public MovieBrierfCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MovieBrierfCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.showing_movie_brief_card,this);
        style = (TextView) findViewById(R.id.style);
        amount = (TextView) findViewById(R.id.amount);
    }

    public void bindCommingMovieData(final List<ComingMovie.MoviecomingsBean> commingMovie) {
        style.setText("即将上映");
        if (commingMovie.size() == 0) {
            Log.d(TAG, "bind data error commingMovie = null");
            return;
        }
        amount.setText(commingMovie.size()+"部");
        amount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.switchToView(1);
            }
        });
        onLoadComingMovie(commingMovie);
    }

    public void bindShowingMovieData(final List<ShowingMovie.MsBean> showingMovies) {
        style.setText("正在热映");
        if (showingMovies.size() == 0) {
            Log.d(TAG, "bind data error showingMovies = null");
            return;
        }
        amount.setText(showingMovies.size()+"部");
        amount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.switchToView(1);
            }
        });
        onLoadShowingMovie(showingMovies);
    }

    private void onLoadShowingMovie(List<ShowingMovie.MsBean> showingMovies) {
        if (showingMovies.size() > 0) {
            MovieRecyclerView movieRecyclerView = (MovieRecyclerView) findViewById(R.id.movie_list);
            movieRecyclerView.bindShowingMovieData(showingMovies.size() > MAX_SHOW_NUM ? showingMovies.subList(0, MAX_SHOW_NUM) : showingMovies);
        }
        setVisibility(showingMovies.size() > 0 ? VISIBLE : GONE);
    }

    private void onLoadComingMovie(List<ComingMovie.MoviecomingsBean> commingMovie) {
        if (commingMovie.size() > 0) {
            MovieRecyclerView movieRecyclerView = (MovieRecyclerView) findViewById(R.id.movie_list);
            movieRecyclerView.bindComingMovieData(commingMovie.size() > MAX_SHOW_NUM ? commingMovie.subList(0, MAX_SHOW_NUM) : commingMovie);
        }
        setVisibility(commingMovie.size() > 0 ? VISIBLE : GONE);
    }


}
