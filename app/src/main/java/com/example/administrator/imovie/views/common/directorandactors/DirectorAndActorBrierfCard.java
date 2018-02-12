package com.example.administrator.imovie.views.common.directorandactors;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.example.administrator.imovie.models.MovieDetail;
import com.example.administrator.imovie.R;
import java.util.List;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class DirectorAndActorBrierfCard extends RelativeLayout {
    private static final String TAG = "DirectorAndActorBrierfCard";

    public DirectorAndActorBrierfCard(Context context) {
        super(context);
        initView();
    }

    public DirectorAndActorBrierfCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DirectorAndActorBrierfCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.director_and_actor_brief_card, this);
    }


    public void bindData(List<MovieDetail.DataBean.BasicBean.ActorsBean> actorsBeanList, MovieDetail.DataBean.BasicBean.DirectorBean directorBean) {

        onLoad(actorsBeanList, directorBean);

    }

    private void onLoad(List<MovieDetail.DataBean.BasicBean.ActorsBean> actorsBeanList, MovieDetail.DataBean.BasicBean.DirectorBean directorBean) {

        DirectorAndActorRecyclerView directorAndActorRecyclerView = (DirectorAndActorRecyclerView) findViewById(R.id.director_and_actor_recyclerview);
        directorAndActorRecyclerView.bindData(actorsBeanList, directorBean);

    }
}
