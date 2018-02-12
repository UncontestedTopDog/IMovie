package com.example.administrator.imovie.views.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.imovie.R;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class MeFragment extends Fragment {
    private static final String TAG = "MeFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.me_fragment,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }
}
