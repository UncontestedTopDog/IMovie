package com.example.administrator.imovie.views.smallvideo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.imovie.comand.HideImageCommand;
import com.example.administrator.imovie.models.TouTiaoVideoData;
import com.example.administrator.imovie.R;
import com.example.administrator.imovie.models.YGMovieData;
import com.example.administrator.imovie.utils.RxBus;
import com.example.administrator.imovie.utils.todaynewsvideo.Video;
import com.example.administrator.imovie.utils.todaynewsvideo.VideoPathDecoder;
import com.example.administrator.imovie.views.common.CircularImageView;
import com.example.administrator.imovie.views.common.NewJZVideoPlayerStandard;
import cn.jzvd.JZVideoPlayer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * Created by huangweiliang on 2018/02/12.
 */

public class VideoListCard extends RelativeLayout {
    private static final String TAG = "VideoListCard";
    private NewJZVideoPlayerStandard mVideoPlayer ;
    private CircularImageView mAvatarImage ;
    private TextView mAvatarName ;
    private Subscription mSubscription;
    private String mClassName ;

    public VideoListCard(Context context) {
        super(context);
        initView();
    }

    public VideoListCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VideoListCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.video_list_card,this);
        mVideoPlayer = (NewJZVideoPlayerStandard) findViewById(R.id.video_player);
        mAvatarImage = (CircularImageView) findViewById(R.id.avatar_image);
        mAvatarName = (TextView) findViewById(R.id.avatar_name);
    }

    public boolean bindData(final YGMovieData mYGMovieData){
        if (mYGMovieData == null)
            return false ;
        mVideoPlayer.setmMediaCreatorId(mYGMovieData.getGroup_id());
//        if (mYGMovieData.getPlayer_url() == null)
//            parseUrl(mYGMovieData);
        Glide.with(getContext())
                .load(mYGMovieData.getImage_url())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(mVideoPlayer.thumbImageView) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );
        Glide.with(getContext()).load(mYGMovieData.getMedia_avatar_url()).into(mAvatarImage) ;
        mAvatarName.setText(mYGMovieData.getSource());
        mSubscription = RxBus.getDefault().register(HideImageCommand.class, this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HideImageCommand>() {
                    @Override
                    public void call(HideImageCommand cmd) {
                        mAvatarImage.setVisibility(VISIBLE);
                        if (cmd.getMedia_creator_id() ==  mYGMovieData.getGroup_id())
                            mAvatarImage.setVisibility(INVISIBLE);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        return true ;
    }

    private void parseUrl(final YGMovieData mYGMovieData) {
        //解析地址
        VideoPathDecoder decoder = new VideoPathDecoder() {
            @Override
            public void onSuccess(Video s) {
                Log.i(TAG,s.toString());
                mVideoPlayer.setUp(s.main_url, JZVideoPlayer.SCREEN_WINDOW_NORMAL, mYGMovieData.getTitle(),0);
                mYGMovieData.setPlayer_url(s.main_url);
            }

            @Override
            public void onDecodeError(Throwable e) {
            }
        };
        Log.i(TAG,mYGMovieData.getShare_url());
        decoder.decodePath(mYGMovieData.getShare_url());
    }

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }
}
