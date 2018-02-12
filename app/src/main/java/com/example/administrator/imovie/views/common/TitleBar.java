package com.example.administrator.imovie.views.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.imovie.R;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class TitleBar extends RelativeLayout {

    private TextView title ;
    private ImageButton back ;

    public TitleBar(Context context) {
        super(context);
        initView();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.title_bar,this);
        title = (TextView) findViewById(R.id.trailer_title);
        back = (ImageButton) findViewById(R.id.back);
    }

    public void setTitle(String s){
        title.setText(s+"预告片");
    }

    public void setBackListener (OnClickListener onClickListener){
        back.setOnClickListener(onClickListener);
    }
}
