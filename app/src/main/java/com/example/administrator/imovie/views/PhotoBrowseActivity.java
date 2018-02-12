package com.example.administrator.imovie.views;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.imovie.base.BaseActivity;
import com.example.administrator.imovie.R;
import com.example.administrator.imovie.views.common.myphotoview.PhotoView;
import com.example.administrator.imovie.views.common.myphotoview.PhotoViewAttacher;
import com.example.administrator.imovie.views.common.myphotoview.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class PhotoBrowseActivity extends BaseActivity {

    private List<String> imageList = new ArrayList<>();
    private int current;
    private TextView imageAmount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_image_detail);
        imageList = getIntent().getStringArrayListExtra("imageList");
        PhotoViewPager viewPager = (PhotoViewPager) findViewById(R.id.view_pager);
        imageAmount = (TextView) findViewById(R.id.image_amount);
        imageAmount.setText(current+1 + "/" + imageList.size());
        viewPager.setAdapter(new ImagePagerAdapter());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imageAmount.setText(position+1 + "/" + imageList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(getIntent().getIntExtra("CURRENT", 0));
    }

    class ImagePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {

            PhotoView photoView = new PhotoView(container.getContext());

            Glide.with(PhotoBrowseActivity.this)
                    .load(imageList.get(position))
                    .placeholder(R.drawable.no_pictrue)
                    .error(R.drawable.no_pictrue)
                    .into(photoView);

            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    finish();
                }
            });
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
