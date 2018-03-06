package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.adapter.SectionedBaseAdapter;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.ListViewCompat;
import com.oldfriends.app.view.PinnedHeaderListView;
import com.oldfriends.app.view.SlideView;

import java.util.ArrayList;
import java.util.List;

public class ChargeDetailActivity extends BaseActivity implements SlideView.OnSlideListener {
    private SlideView mLastSlideViewWithStatusOn;
    private List<MessageItem> messageItemList;

    private void initData() {
        ArrayList localArrayList = new ArrayList();
        this.messageItemList = localArrayList;
        for (int i = 0; i < 12; i++) {
            MessageItem localMessageItem = new MessageItem();
            this.messageItemList.add(localMessageItem);
        }
    }

    private void initTop() {
        RelativeLayout localRelativeLayout = (RelativeLayout) findViewById(R.id.charge_detail_top);
        ((TextView) localRelativeLayout.findViewById(R.id.common_transparent_left_font)).setText(R.string.group_charge);
        View localView1 = localRelativeLayout.findViewById(R.id.common_transparent_left_layout);
        View.OnClickListener local1 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Utility.finishActivityTranslate(ChargeDetailActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        ((TextView) localRelativeLayout.findViewById(R.id.common_transparent_title)).setText("复仇者联盟");
        localRelativeLayout.findViewById(R.id.common_transparent_right_image).setBackgroundResource(R.drawable.circle_right_more_icon);
        View localView2 = localRelativeLayout.findViewById(R.id.common_transparent_right_layout);
        View.OnClickListener local2 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent localIntent = new Intent(ChargeDetailActivity.this, ChargeSettingActivity.class);
                Utility.startActivitytranslate(ChargeDetailActivity.this, localIntent);
            }
        };
        localView2.setOnClickListener(local2);
        TextView localTextView1 = (TextView) findViewById(R.id.charge_detail_search);
        TextView localTextView2 = (TextView) findViewById(R.id.charge_detail_account);
        TextView localTextView3 = (TextView) findViewById(R.id.charge_detail_split);
        View.OnClickListener local3 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent localIntent = new Intent(ChargeDetailActivity.this, ChargeSearchActivity.class);
                Utility.startActivitytranslate(ChargeDetailActivity.this, localIntent);
            }
        };
        localTextView1.setOnClickListener(local3);
        View.OnClickListener local4 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent localIntent = new Intent(ChargeDetailActivity.this, ChargeAccountActivity.class);
                Utility.startActivitytranslate(ChargeDetailActivity.this, localIntent);
            }
        };
        localTextView2.setOnClickListener(local4);
        View.OnClickListener local5 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent localIntent = new Intent(ChargeDetailActivity.this, ChargeSplitActivity.class);
                Utility.startActivitytranslate(ChargeDetailActivity.this, localIntent);
            }
        };
        localTextView3.setOnClickListener(local5);
    }

    private void setupData() {
        initData();
        ListViewCompat localListViewCompat = (ListViewCompat) findViewById(R.id.charge_detail_list);
        TestSectionedAdapter localTestSectionedAdapter = new TestSectionedAdapter(this);
        localListViewCompat.setAdapter(localTestSectionedAdapter);
        localListViewCompat.setDividerHeight(0);
        PinnedHeaderListView.OnItemClickListener local6 = new PinnedHeaderListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, long paramAnonymousLong) {
                Log.d("ChooseFriendsActivity", "onItemClick section=" + paramAnonymousInt1 + ",position=" + paramAnonymousInt2);
                Intent localIntent = new Intent(ChargeDetailActivity.this, BillDetailActivity.class);
                Utility.startActivitytranslate(ChargeDetailActivity.this, localIntent);
            }

            public void onSectionClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Log.d("ChooseFriendsActivity", "onSectionClick section=" + paramAnonymousInt);
            }
        };
        localListViewCompat.setOnItemClickListener(local6);
    }

    protected int getLayoutResId() {
        return R.layout.activity_charge_detail_layout;
    }

    protected void initView() {
        initTop();
        setupData();
    }

    public void onSlide(View paramView, int paramInt) {
        if ((this.mLastSlideViewWithStatusOn != null) && (this.mLastSlideViewWithStatusOn != paramView))
            this.mLastSlideViewWithStatusOn.shrink();
        if (paramInt == 2)
            this.mLastSlideViewWithStatusOn = ((SlideView) paramView);
    }

    class HeaderViewHolder {
        TextView text;
    }

    public class MessageItem {
        public SlideView slideView;
    }

    public class TestSectionedAdapter extends SectionedBaseAdapter {
        private LayoutInflater inflater;

        TestSectionedAdapter(Context arg2) {
            this.inflater = LayoutInflater.from(arg2);
        }

        public int getCountForSection(int paramInt) {
            return 4;
        }

        public ChargeDetailActivity.MessageItem getItem(int paramInt1, int paramInt2) {
            return ChargeDetailActivity.this.messageItemList.get(paramInt2 + paramInt1 * getCountForSection(paramInt1));
        }

        public long getItemId(int paramInt1, int paramInt2) {
            return 0L;
        }

        public View getItemView(int paramInt1, int paramInt2, View paramView, ViewGroup paramViewGroup) {
            View localView = paramView;
            SlideView localObject = (SlideView) localView;

            if (localView == null) {
                localView = this.inflater.inflate(R.layout.custom_charge_detail_listitem_layout, paramViewGroup, false);
            }

            ChargeDetailActivity.ViewHolder localViewHolder2;
            if (localObject == null) {
                localObject = new SlideView(ChargeDetailActivity.this);
                localObject.setContentView(localView);
                localObject.setOnSlideListener(ChargeDetailActivity.this);
                localViewHolder2 = new ChargeDetailActivity.ViewHolder(localObject);
                localObject.setTag(localViewHolder2);
                ChargeDetailActivity.MessageItem localMessageItem = getItem(paramInt1, paramInt2);
                localMessageItem.slideView = (localObject);
                localMessageItem.slideView.shrink();
                localViewHolder2.text.setText("饿了吗外卖");
                ViewGroup localViewGroup = localViewHolder2.deleteHolder;
                View.OnClickListener local1 = new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                    }
                };
                localViewGroup.setOnClickListener(local1);
            } else {
                localViewHolder2 = (ChargeDetailActivity.ViewHolder) localObject.getTag();
            }

            if (paramInt2 != -1 + getCountForSection(paramInt1)) {
                localViewHolder2.line.setVisibility(View.VISIBLE);
            } else {
                localViewHolder2.line.setVisibility(View.GONE);
            }

            return localObject;
        }

        public int getSectionCount() {
            return 3;
        }

        public View getSectionHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            View localView = paramView;
            ChargeDetailActivity.HeaderViewHolder localHeaderViewHolder2;
            if (localView == null) {
                localHeaderViewHolder2 = new ChargeDetailActivity.HeaderViewHolder();
                localView = this.inflater.inflate(R.layout.charge_detail_list_head_layout, paramViewGroup, false);
                localHeaderViewHolder2.text = ((TextView) localView.findViewById(R.id.text));
                localView.setTag(localHeaderViewHolder2);
            } else {
                localHeaderViewHolder2 = (ChargeDetailActivity.HeaderViewHolder) localView.getTag();
            }

            TextView localTextView = localHeaderViewHolder2.text;
            localTextView.setText("2015年" + (10 + paramInt) + "月");

            return localView;
        }
    }

    class ViewHolder {
        public ViewGroup deleteHolder;
        ImageView image;
        TextView line;
        TextView text;

        ViewHolder(View arg2) {
            this.deleteHolder = ((ViewGroup) arg2.findViewById(R.id.holder));
            this.text = ((TextView) arg2.findViewById(R.id.charge_detail_listItem_place));
            this.line = ((TextView) arg2.findViewById(R.id.charge_detail_listItem_line));
        }
    }
}
