package com.example.administrator.imovie.views.smallvideo;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.imovie.comand.RotateScreenCommand;
import com.example.administrator.imovie.models.TouTiaoVideoData;
import com.example.administrator.imovie.models.TouTiaoVideoOriginalData;
import com.example.administrator.imovie.manager.MovieManager;
import com.example.administrator.imovie.R;
import com.example.administrator.imovie.utils.RxBus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class RecommendVideoFragment extends com.trello.rxlifecycle.components.support.RxFragment {

    private static final String TAG = "HomeworkFragment";
    private VideoRecyclerView mVideoRecyclerView ;
    private SmartRefreshLayout mSmartRefreshLayout ;
    List<TouTiaoVideoData> mTouTiaoVideoDatas = new ArrayList<>();
    private String mMaxBehotTime ;
    private Subscription mSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.recommend_video_fragment,container,false);
        initView(view);
        getTouTiaoVideoDataByMinBehotTime();
        return view;
    }

    private void initView(View view) {
        mVideoRecyclerView  = (VideoRecyclerView) view.findViewById(R.id.video_list);
        mVideoRecyclerView.setmClassName(getClass().getName());
        mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.video_refreshlayout);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getTouTiaoVideoDataByMinBehotTime();
                Log.i(TAG,mMaxBehotTime);
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Log.i(TAG,mMaxBehotTime);
                getTouTiaoVideoDataByMaxBehotTime(mMaxBehotTime);
            }
        });
        mSubscription = RxBus.getDefault().register(RotateScreenCommand.class, this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RotateScreenCommand>() {
                    @Override
                    public void call(RotateScreenCommand cmd) {
                        if (cmd.getClassname().equals(getClass().getName())) {
                            if (cmd.isB()) {
                                //横屏设置
                                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                            } else {
                                //竖屏设置
                                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }



    private void getTouTiaoVideoDataByMaxBehotTime(final String maxBehotTime) {
        MovieManager.INSTANCE()
                .getTouTiaoVideoDataByMaxBehotTime(maxBehotTime)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TouTiaoVideoOriginalData>() {
                    @Override
                    public void call(TouTiaoVideoOriginalData touTiaoVideoData) {
                        for (TouTiaoVideoOriginalData.DataBean dataBean : touTiaoVideoData.getData()){
                            mTouTiaoVideoDatas.add(new TouTiaoVideoData(dataBean));
                            Log.i(TAG,dataBean.getTitle());
                        }
                        mVideoRecyclerView.bindData(mTouTiaoVideoDatas);
                        mMaxBehotTime = touTiaoVideoData.getNext().getMax_behot_time()+"";
                        mSmartRefreshLayout.finishLoadmore();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                });
    }

    private void getTouTiaoVideoDataByMinBehotTime() {
        MovieManager.INSTANCE()
                .getTouTiaoVideoDataByMinBehotTime()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TouTiaoVideoOriginalData>() {
                    @Override
                    public void call(TouTiaoVideoOriginalData touTiaoVideoData) {
                        mTouTiaoVideoDatas.clear();
                        Log.i(TAG,"TouTiaoDataSize = "+touTiaoVideoData.getData().size());
                        if (touTiaoVideoData.getData().size() != 0) {
                            for (TouTiaoVideoOriginalData.DataBean dataBean : touTiaoVideoData.getData()){
                                mTouTiaoVideoDatas.add(new TouTiaoVideoData(dataBean));
                                Log.i(TAG,dataBean.getTitle());
                            }
                            mVideoRecyclerView.bindData(mTouTiaoVideoDatas);
                            mMaxBehotTime = touTiaoVideoData.getNext().getMax_behot_time()+"";
                            mSmartRefreshLayout.finishRefresh(100);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                });
    }

}
