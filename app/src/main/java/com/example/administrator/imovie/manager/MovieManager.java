package com.example.administrator.imovie.manager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.imovie.models.ComingMovie;
import com.example.administrator.imovie.models.DoubanMovieId;
import com.example.administrator.imovie.models.MaoyanMovieId;
import com.example.administrator.imovie.models.MovieAward;
import com.example.administrator.imovie.models.MovieDetail;
import com.example.administrator.imovie.models.MovieImageAll;
import com.example.administrator.imovie.models.ShowingMovie;
import com.example.administrator.imovie.models.TimeMovieId;
import com.example.administrator.imovie.models.TodayNewsKeyword;
import com.example.administrator.imovie.models.YGMovieOriginalData;
import com.example.administrator.imovie.models.TrailerData;
import com.example.administrator.imovie.models.XiGuaMovieOriginalData;
import com.google.gson.Gson;

import java.util.List;

import rx.Observable;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class MovieManager {

    public static MovieManager mMovieManager = new MovieManager();

    public static MovieManager INSTANCE() {
        return mMovieManager;
    }

    public Observable<ShowingMovie> getShowingMovieByLocationId(@NonNull int locationId) {
        return MovieService.getShowingMovieByLocationId(locationId);
    }

    public Observable<ComingMovie> getComingMovieByLocationId(@NonNull int locationId) {
        return MovieService.getComingMovieByLocationId(locationId);
    }

    public Observable<TrailerData> getTrailerByPageIndexAndMovieId(@NonNull int pageIndex, @NonNull int movieId) {
        return MovieService.getTrailerByPageIndexAndMovieId(pageIndex, movieId);
    }

    public Observable<MovieDetail> getDetailByLocationIdAndMovieId(@NonNull int locationId, @NonNull int movieId) {
        return MovieService.getDetailByLocationIdAndMovieId(locationId, movieId);
    }

    public Observable<MovieAward> getAwardByLocationIdAndMovieId(@NonNull int locationId, @NonNull int movieId) {
        return MovieService.getAwardByLocationIdAndMovieId(locationId, movieId);
    }

    public Observable<MovieImageAll> getMovieImageAllByMovieId(@NonNull int movieId) {
        return MovieService.getMovieImageAllByMovieId(movieId);
    }

    public Observable<List<DoubanMovieId>> getDoubanMovieIdByMovieName(@NonNull String movieName) {
        return MovieService.getDoubanMovieIdByMovieName(movieName);
    }

    public Observable<MaoyanMovieId> getMaoyanMovieIdByMovieName(@NonNull String movieName) {
        return MovieService.getMaoyanMovieIdByMovieName(movieName);
    }

    public Observable<String> getTimeMovieIdByMovieNameAndTime(@NonNull String movieName,@NonNull String time) {
        return MovieService.getTimeMovieIdByMovieNameAndTime(movieName,time);
    }

    public TimeMovieId handTimeMovieIdInitialData(String result){
        TimeMovieId timeMovieId ;
        String s = " var getSearchResult = ";
        int i = result.indexOf(s);
        result = result.substring(s.length()-1,result.length()-3);
        Gson gson = new Gson();
        timeMovieId = gson.fromJson(result, TimeMovieId.class);
        for (TimeMovieId.ValueBean.MovieResultBean.MoreMoviesBean moreMoviesBean : timeMovieId.getValue().getMovieResult().getMoreMovies())
            Log.i("TCQS",moreMoviesBean.getMovieId()+moreMoviesBean.getMovieTitle());
        return timeMovieId;
    }

    public Observable<String> getBanaerDataById(@NonNull String Id) {
        return MovieService.getBanaerDataById(Id);
    }

    public Observable<TodayNewsKeyword> getTodayNewsByKeyword(@NonNull String keyword) {
        return MovieService.getTodayNewsByKeyword(keyword);
    }

    public Observable<XiGuaMovieOriginalData> getXiGuaMovieOriginalDataByKeyword(@NonNull String keyword) {
        return MovieService.getXiGuaMovieOriginalDataByKeyword(keyword);
    }

    public Observable<YGMovieOriginalData> getTouTiaoVideoDataByMaxBehotTime(@NonNull String max_behot_time) {
        return MovieService.getTouTiaoVideoDataByMaxBehotTime(max_behot_time);
    }

    public Observable<YGMovieOriginalData> getTouTiaoVideoDataByMinBehotTime() {
        return MovieService.getTouTiaoVideoDataByMinBehotTime();
    }

}
