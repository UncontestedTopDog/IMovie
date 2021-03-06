package com.example.administrator.imovie.views.movie;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.imovie.models.ShowingMovie;
import com.example.administrator.imovie.R;
import com.example.administrator.imovie.views.trailer.TrailerListActivity;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class ShowingListCard extends RelativeLayout {

    private static final String TAG = "ShowingMovieCard";

    private ImageView moviePoster ;
    private ImageView hasForenotice ;
    private TextView movieName ;
    private ImageView isImax ;
    private ImageView is3D ;
    private TextView movieGrade ;
    private TextView movieCommonSpecial ;
    private TextView movieActors ;

    public ShowingListCard(Context context) {
        super(context);
        initView();
    }

    public ShowingListCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ShowingListCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.showing_list_card,this);
        moviePoster = (ImageView) findViewById(R.id.movie_poster);
        hasForenotice = (ImageView) findViewById(R.id.has_forenotice);
        movieName = (TextView) findViewById(R.id.movie_name);
        isImax = (ImageView) findViewById(R.id.isimax);
        is3D = (ImageView) findViewById(R.id.is3d);
        movieGrade = (TextView) findViewById(R.id.movie_grade);
        movieCommonSpecial = (TextView) findViewById(R.id.movie_common_special);
        movieActors = (TextView) findViewById(R.id.movie_actors);
    }

    public boolean bindData(final ShowingMovie.MsBean showingMovie) {
        if (showingMovie == null) {
            Log.d(TAG, "bind data error showingMovie = null");
            return false;
        }

        moviePoster.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),TrailerListActivity.class);
                intent.putExtra("MOVIENAME",showingMovie.getT());
                intent.putExtra("MOVIEID",showingMovie.getId());
                getContext().startActivity(intent);
            }
        });

//        Glide.with(getContext()).load(showingMovie.getImg()).into(moviePoster);

        Glide.with(getContext())
                .load(showingMovie.getImg())
                .centerCrop()
                .placeholder(R.drawable.no_pictrue)
                .error(R.drawable.download_fail_hint)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(moviePoster) {
                          @Override
                          public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                              super.onResourceReady(drawable, anim);
                          }
                      }
                );

        for (int i = 0 ; i < showingMovie.getVersions().size();i++){
            if (showingMovie.getVersions().get(i).getEnumX()==2)
                is3D.setVisibility(VISIBLE);
            if (showingMovie.getVersions().get(i).getEnumX()==3)
                isImax.setVisibility(VISIBLE);
        }
        movieGrade.setText(showingMovie.getR() + "分");
        movieGrade.setVisibility(showingMovie.getR() > 0 ? VISIBLE : INVISIBLE);
        movieName.setText(showingMovie.getT());
        movieActors.setText(showingMovie.getActors());
        movieCommonSpecial.setText(showingMovie.getCommonSpecial());
        return true;
    }
}
