package com.example.administrator.imovie.views.smallvideo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.imovie.models.TouTiaoVideoData;
import com.example.administrator.imovie.models.YGMovieData;

import java.util.List;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class VideoRecyclerView extends RecyclerView {
    private List<YGMovieData> mYGMovieData ;
    private VideoAdapter adapter;
    private String mClassName ;
    public VideoRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public VideoRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VideoRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(VERTICAL);
        setLayoutManager(linearLayoutManager);
    }

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public void bindData(List<YGMovieData> mYGMovieData){
        this.mYGMovieData = mYGMovieData ;
        if (adapter == null){
            adapter = new VideoAdapter();
            setAdapter(adapter);
        }else adapter.notifyDataSetChanged();
    }


    private class VideoAdapter  extends Adapter<ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            VideoListCard view = new VideoListCard(parent.getContext());
            view.setmClassName(mClassName);
            return new TrailerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ((VideoListCard)holder.itemView).setmClassName(mClassName);
            ((VideoListCard)holder.itemView).bindData(mYGMovieData.get(position));
        }

        @Override
        public int getItemCount() {
            return mYGMovieData.size();
        }

        private class TrailerViewHolder extends ViewHolder {
            public TrailerViewHolder(View view) {
                super(view);
            }
        }
    }

}
