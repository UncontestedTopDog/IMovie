package com.example.administrator.imovie.views.home;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.imovie.base.BaseRecyclerView;
import com.example.administrator.imovie.models.ComingMovie;
import com.example.administrator.imovie.models.ShowingMovie;
import java.util.List;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class MovieRecyclerView extends BaseRecyclerView {

    private List<ShowingMovie.MsBean> showingMovies ;
    private List<ComingMovie.MoviecomingsBean> commingMovies ;
    private RecyclerView.Adapter mAdapter;

    public MovieRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public MovieRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MovieRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(HORIZONTAL);
        setLayoutManager(linearLayoutManager);
        final int dividerSpace = (int) (getResources().getDisplayMetrics().density * 10);
        final int leftRightSpace = (int) (getResources().getDisplayMetrics().density * 15);
        addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int index = parent.getChildAdapterPosition(view);
                outRect.right = index != mAdapter.getItemCount() - 1 ? dividerSpace : leftRightSpace;
                if (index == 0) {
                    outRect.left = leftRightSpace;
                }
            }

        });
    }

    public void bindShowingMovieData(List<ShowingMovie.MsBean> showingMovies) {
        this.showingMovies = showingMovies;
        if (mAdapter == null) {
            mAdapter = new ShowingMovieAdapter();
            setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void bindComingMovieData(List<ComingMovie.MoviecomingsBean> commingMovies) {
        this.commingMovies = commingMovies;
        if (mAdapter == null) {
            mAdapter = new CommingMovieAdapter();
            setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ShowingMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new MovieCard(parent.getContext());
            return new MovieViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MovieCard) holder.itemView).bindData(showingMovies.get(position));
        }

        @Override
        public int getItemCount() {
                return showingMovies.size();
        }
    }

    private class CommingMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new MovieCard(parent.getContext());
            return new MovieViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MovieCard) holder.itemView).bindData(commingMovies.get(position));
        }

        @Override
        public int getItemCount() {
            return commingMovies.size();
        }
    }


    private class MovieViewHolder extends RecyclerView.ViewHolder {
        public MovieViewHolder(View view) {
            super(view);
        }
    }
}
