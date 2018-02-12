package com.example.administrator.imovie.views.trailer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.imovie.models.TrailerData;
import java.util.List;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class TrailerRecyclerView extends RecyclerView {
    private List<TrailerData.VideoListBean> mTrailerDatas ;
    private TrailerAdapter mAdapter;
    private String mClassName ;
    public TrailerRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public TrailerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TrailerRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
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

    public void bindData(List<TrailerData.VideoListBean> trailerDatas){
        this.mTrailerDatas = trailerDatas ;
        if (mAdapter == null){
            mAdapter = new TrailerAdapter();
            setAdapter(mAdapter);
        }else mAdapter.notifyDataSetChanged();
    }

    private class TrailerAdapter  extends Adapter<ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TrailerListCard view = new TrailerListCard(parent.getContext());
            view.setmClassName(mClassName);
            return new TrailerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ((TrailerListCard)holder.itemView).setmClassName(mClassName);
            ((TrailerListCard)holder.itemView).bindData(mTrailerDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mTrailerDatas.size();
        }

        private class TrailerViewHolder extends ViewHolder {
            public TrailerViewHolder(View view) {
                super(view);
            }
        }
    }

}
