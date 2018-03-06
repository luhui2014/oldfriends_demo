package com.oldfriends.app.fragment;

/**
 * lh on 2016/2/24.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.activity.AddFriendsActivity;
import com.oldfriends.app.activity.FriendsDetailActivity;
import com.oldfriends.app.activity.SearchActivity;
import com.oldfriends.app.adapter.SectionedBaseAdapter;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.PinnedHeaderListView;

public class FragmentFriends extends Fragment
        implements View.OnClickListener
{
    private int currentIndex;
    private ImageView cursorImageView;
    private DisplayMetrics displayMetrics;
    private View layoutView;
    private int screenWidth;

    private void initCursorImageView()
    {
        this.screenWidth = ((WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        this.cursorImageView = ((ImageView)this.layoutView.findViewById(R.id.cursor));
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(this.screenWidth / 3, 3);
        this.cursorImageView.setLayoutParams(localLayoutParams);
        Matrix localMatrix = new Matrix();
        localMatrix.postTranslate(0.0F, 0.0F);
        this.cursorImageView.setImageMatrix(localMatrix);
    }

    private void initSubTitle()
    {
        View localView = this.layoutView.findViewById(R.id.friends_search_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                FragmentActivity localFragmentActivity = FragmentFriends.this.getActivity();
                Intent localIntent = new Intent(FragmentFriends.this.getActivity(), SearchActivity.class);
                Utility.startActivitytranslate(localFragmentActivity, localIntent);
            }
        };
        localView.setOnClickListener(local2);
        this.layoutView.findViewById(R.id.fragment_friends_get).setOnClickListener(this);
        this.layoutView.findViewById(R.id.fragment_friends_sum).setOnClickListener(this);
        this.layoutView.findViewById(R.id.fragment_friends_pay).setOnClickListener(this);
        initCursorImageView();
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)this.layoutView.findViewById(R.id.fragment_friends_top_layout);
        ((TextView)localRelativeLayout.findViewById(R.id.common_title)).setText(R.string.friends);
        ((ImageView)localRelativeLayout.findViewById(R.id.common_right_image)).setImageResource(R.drawable.friends_right_icon);
        View localView = localRelativeLayout.findViewById(R.id.common_right_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(FragmentFriends.this.getActivity(), AddFriendsActivity.class);
                Utility.startActivitytranslate(FragmentFriends.this.getActivity(), localIntent);
            }
        };
        localView.setOnClickListener(local1);
    }

    private void initView()
    {
        this.displayMetrics = getResources().getDisplayMetrics();
        initTop();
        initSubTitle();
        setupData();
    }

    private void setupData()
    {
        PinnedHeaderListView localPinnedHeaderListView = (PinnedHeaderListView)this.layoutView.findViewById(R.id.friends_detail_list);
        FriendsSectionedAdapter localFriendsSectionedAdapter = new FriendsSectionedAdapter(getActivity());
        localPinnedHeaderListView.setAdapter(localFriendsSectionedAdapter);
        PinnedHeaderListView.OnItemClickListener local3 = new PinnedHeaderListView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, long paramAnonymousLong)
            {
                Intent localIntent = new Intent(FragmentFriends.this.getActivity(), FriendsDetailActivity.class);
                Utility.startActivitytranslate(FragmentFriends.this.getActivity(), localIntent);
            }

            public void onSectionClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {

            }
        };
        localPinnedHeaderListView.setOnItemClickListener(local3);
    }

    public void onClick(View paramView)
    {
        TranslateAnimation animation = null;
        int i = this.screenWidth / 3;
        switch (paramView.getId()) {
            case R.id.fragment_charge_get:
                if (this.currentIndex == 1) {
                    animation = new TranslateAnimation(i, 0.0F, 0.0F, 0.0F);
                }else if(this.currentIndex == 2){
                    animation = new TranslateAnimation(i * 2, 0.0F, 0.0F, 0.0F);
                }
                currentIndex = 0;
                break;
            case R.id.fragment_charge_sum:
                if (this.currentIndex == 0) {
                    animation = new TranslateAnimation(0.0F, i, 0.0F, 0.0F);
                }else if(this.currentIndex == 2){
                    animation = new TranslateAnimation(i * 2, i, 0.0F, 0.0F);
                }
                currentIndex = 1;
                break;
            case R.id.fragment_charge_pay:
                if (this.currentIndex == 0) {
                    animation = new TranslateAnimation(0.0F, i * 2, 0.0F, 0.0F);
                }else if(this.currentIndex == 1){
                    animation = new TranslateAnimation(i, i * 2, 0.0F, 0.0F);
                }
                currentIndex = 2;
                break;
        }

        if (animation != null)
        {
            animation.setFillAfter(true);
            animation.setDuration(100L);
            this.cursorImageView.startAnimation(animation);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        this.layoutView = paramLayoutInflater.inflate(R.layout.fragment_friends_layout, paramViewGroup, false);
        initView();
        return this.layoutView;
    }

    private class FriendsSectionedAdapter extends SectionedBaseAdapter
    {
        private LayoutInflater inflater;

        FriendsSectionedAdapter(Context arg2)
        {
            this.inflater = LayoutInflater.from(arg2);
        }

        public int getCountForSection(int paramInt)
        {
            if (paramInt / 2 == 0)
                return 1;
            return 5;
        }

        public Object getItem(int paramInt1, int paramInt2)
        {
            return null;
        }

        public long getItemId(int paramInt1, int paramInt2)
        {
            return 0L;
        }

        public View getItemView(int paramInt1, int paramInt2, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            FragmentFriends.FriendsViewHolder  localFriendsViewHolder;
            if (localView == null)
            {
                localFriendsViewHolder = new FragmentFriends.FriendsViewHolder();
                localView = this.inflater.inflate(R.layout.custom_friend_listitem_layout, paramViewGroup, false);
                localView.setTag(localFriendsViewHolder);
            }else {
                localFriendsViewHolder = (FragmentFriends.FriendsViewHolder)localView.getTag();
            }
            return localView;
        }

        public int getSectionCount()
        {
            return 3;
        }

        public View getSectionHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            FragmentFriends.HeaderViewHolder localHeaderViewHolder2;
            if (localView == null)
            {
                localHeaderViewHolder2 = new FragmentFriends.HeaderViewHolder();
                localView = this.inflater.inflate(R.layout.charge_detail_list_head_layout, paramViewGroup, false);
                localHeaderViewHolder2.text = ((TextView)localView.findViewById(R.id.text));
                localView.setTag(localHeaderViewHolder2);
            }else {
                localHeaderViewHolder2 = (FragmentFriends.HeaderViewHolder)localView.getTag();
            }

            TextView localTextView = localHeaderViewHolder2.text;
            localTextView.setText("2015年" + (10 + paramInt) + "月");
            return localView;
        }
    }

    class FriendsViewHolder
    {
        private ImageView headImageView;
        private TextView money;
        private TextView nameText;
        private TextView payType;

    }

    class HeaderViewHolder
    {
        TextView text;
    }
}
