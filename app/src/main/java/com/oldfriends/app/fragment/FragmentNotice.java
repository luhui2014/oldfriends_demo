package com.oldfriends.app.fragment;

/**
 * lh on 2016/2/24.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oldfriends.app.R;

import java.util.List;

public class FragmentNotice extends Fragment {
//    private List<RecentContact> items;

    private View layoutView;

    private void initContent() {
    }

    private void initTop() {
        ((TextView) (this.layoutView.findViewById(R.id.fragment_find_top_layout)).findViewById(R.id.common_title)).setText("提醒");
    }

    private void initView() {
        initTop();
        initContent();
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.layoutView = paramLayoutInflater.inflate(R.layout.fragment_find_layout, paramViewGroup, false);
        initView();
        return this.layoutView;
    }
}
