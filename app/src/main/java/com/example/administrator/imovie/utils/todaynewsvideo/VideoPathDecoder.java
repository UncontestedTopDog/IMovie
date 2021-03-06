package com.example.administrator.imovie.utils.todaynewsvideo;

import android.util.Base64;
import android.util.Log;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public abstract class VideoPathDecoder {
    static String TAG = VideoPathDecoder.class.getSimpleName();
    private CompositeSubscription mCompositeSubscription;

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void decodePath(final String srcUrl) {

        AppClient.getApiService().getVideoHtml(srcUrl)
                .flatMap(new Func1<String, Observable<ResultResponse<VideoModel>>>() {
                    @Override
                    public Observable<ResultResponse<VideoModel>> call(String response) {
                        Log.i(TAG,response);
                        Pattern pattern = Pattern.compile("videoid:\'(.+)\'");
                        Matcher matcher = pattern.matcher(response);
                        if (matcher.find()) {
                            String videoId = matcher.group(1);
                            Log.i(TAG,videoId);
                            //1.将/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()}，进行crc32加密。
                            String r = getRandom();
                            CRC32 crc32 = new CRC32();
                            String s = String.format(ApiService.URL_VIDEO, videoId, r);
                            //进行crc32加密。
                            crc32.update(s.getBytes());
                            String crcString = crc32.getValue() + "";
                            //2.访问http://i.snssdk.com/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()}&s={crc32值}
                            String url = ApiService.HOST_VIDEO + s + "&s=" + crcString;
                            Log.i(TAG,url);
                            return AppClient.getApiService().getVideoData(url);
                        }
                        return null;
                    }
                })
                .map(new Func1<ResultResponse<VideoModel>, Video>() {
                    @Override
                    public Video call(ResultResponse<VideoModel> videoModelResultResponse) {
                        VideoModel.DataBean data = videoModelResultResponse.data.getData();

                        if (data.getVideo_list().getVideo_1().getMain_url() != null)
                            return updateVideo(data.getVideo_list().getVideo_1(),data.getPoster_url());
                        return null;
                    }

                    private String getRealPath(String base64) {
                        return new String(Base64.decode(base64.getBytes(), Base64.DEFAULT));
                    }

                    private Video updateVideo(Video video , String poster_url) {
                        //base64解码
                        video.setMain_url(getRealPath(video.getMain_url()));
                        video.setPoster_url(poster_url);
                        return video;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Video>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        onDecodeError(e);
                    }

                    @Override
                    public void onNext(Video s) {
                        onSuccess(s);
                    }
                });
    }

    public abstract void onSuccess(Video s);

    public abstract void onDecodeError(Throwable e);

    private String getRandom() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
