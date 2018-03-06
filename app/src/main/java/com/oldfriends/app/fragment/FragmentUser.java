package com.oldfriends.app.fragment;

/**
 * Created by Administrator on 2016/2/24.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.activity.UserInfoActivity;
import com.oldfriends.app.activity.NoticeSettingActivity;
import com.oldfriends.app.util.Utility;

public class FragmentUser extends Fragment
{
    private View layoutView;

    private void initContent()
    {
        View localView = this.layoutView.findViewById(R.id.notice_setting_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(FragmentUser.this.getActivity(), NoticeSettingActivity.class);
                Utility.startActivitytranslate(FragmentUser.this.getActivity(), localIntent);
            }
        };
        localView.setOnClickListener(local2);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)this.layoutView.findViewById(R.id.fragment_user_top);
        ((TextView)localRelativeLayout.findViewById(R.id.common_transparent_title)).setText("我");
        localRelativeLayout.findViewById(R.id.common_transparent_left_layout).setVisibility(View.GONE);
        localRelativeLayout.findViewById(R.id.common_transparent_right_image).setBackgroundResource(R.drawable.circle_right_more_icon);
        View localView = localRelativeLayout.findViewById(R.id.common_transparent_right_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(FragmentUser.this.getActivity(), UserInfoActivity.class);
                Utility.startActivitytranslate(FragmentUser.this.getActivity(), localIntent);
            }
        };
        localView.setOnClickListener(local1);
    }

    private void initView()
    {
        initTop();
        initContent();
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        this.layoutView = paramLayoutInflater.inflate(R.layout.fragment_user_layout, paramViewGroup, false);
        initView();
        return this.layoutView;
    }
}
