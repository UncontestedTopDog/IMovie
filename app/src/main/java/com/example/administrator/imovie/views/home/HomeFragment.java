package com.example.administrator.imovie.views.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.imovie.models.ComingMovie;
import com.example.administrator.imovie.models.ShowingMovie;
import com.example.administrator.imovie.models.BannerData;
import com.example.administrator.imovie.manager.MovieManager;
import com.example.administrator.imovie.R;
import com.example.administrator.imovie.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private List<BannerData> bannerDatas = new ArrayList<>();
    private List<String> bannerImgs = new ArrayList<>();
    private List<String> bannerTitles = new ArrayList<>();
    private Banner mBanner;
    private MovieBrierfCard mShowingMovieBrierfCard;
    private MovieBrierfCard mCommingMovieBrierfCard;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.home_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mBanner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());

        mShowingMovieBrierfCard = (MovieBrierfCard) view.findViewById(R.id.showing_movice);
        mCommingMovieBrierfCard = (MovieBrierfCard) view.findViewById(R.id.comming_movie);
        getShowingMovieByLocationId(290);
        getComingMovieByLocationId(290);
        getBanaerDataById("1f803d875863");

    }

    private void getShowingMovieByLocationId(final int locationId) {
        MovieManager.INSTANCE()
                .getShowingMovieByLocationId(locationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ShowingMovie>() {
                    @Override
                    public void call(ShowingMovie showingMovie) {
                        mShowingMovieBrierfCard.bindShowingMovieData(showingMovie.getMs());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getShowingMovieByLocationId(locationId);
                        Log.e(TAG, throwable.toString());
                    }
                });
    }

    private void getComingMovieByLocationId(final int locationId) {
        MovieManager.INSTANCE().getComingMovieByLocationId(locationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ComingMovie>() {
                    @Override
                    public void call(ComingMovie comingMovie) {
                        mCommingMovieBrierfCard.bindCommingMovieData(comingMovie.getMoviecomings());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getComingMovieByLocationId(locationId);
                        Log.e("MAIN", throwable.toString());
                    }
                });
    }

    private void getBanaerDataById(final String Id) {
        bannerImgs.clear();
        bannerTitles.clear();
        bannerDatas.clear();
        MovieManager.INSTANCE()
                .getBanaerDataById(Id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String result) {
                        String s = "<div data-note-content class=\"show-content\">";
                        int i = result.indexOf(s);
                        result = result.substring(i+s.length(),result.length());
                        String mark = "哈哈哈";
                        i = 0;
                        int j = 0;
                        String title = "";
                        String img = "";
                        String link = "";
                        while (j < 5) {
                            i = result.indexOf(mark);
                            result = result.substring(i + mark.length(), result.length());
                            i = result.indexOf(mark);
                            title = result.substring(0, i);
                            result = result.substring(i + mark.length(), result.length());
                            i = result.indexOf(mark);
                            img = result.substring(0, i);
                            img = "http://" + img;
                            img = img.replaceAll("#", ".");
                            result = result.substring(i + mark.length(), result.length());
                            i = result.indexOf(mark);
                            link = result.substring(0, i);
                            link = link.replace("<br>","");
                            link = "http://" + link;
                            link = link.replaceAll("#", ".");
                            BannerData bannerData = new BannerData(title, img, link);
                            bannerDatas.add(bannerData);
                            bannerImgs.add(img);
                            bannerTitles.add(title);
                            j++;
                        }
                        //设置图片集合
                        mBanner.setImages(bannerImgs);
                        mBanner.setBannerTitles(bannerTitles);
                        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                        //banner设置方法全部调用完毕时最后调用
                        mBanner.start();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                });
    }

}
