package com.oldfriends.app.activity;

/**
 * lh on 2016/2/25.
 */

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;

public class FriendsDetailActivity extends BaseActivity
{
    private SparseBooleanArray checkStateArray;
    private TextView footerMoney;
    private TextView footerMoneyState;
    private View footerView;
    private FriendsChargeAdapter friendsChargeAdapter;
    private ListView listView;
    private SparseArray<String> moneyArray;

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.friendsDetail_top);
        View localView1 = localRelativeLayout.findViewById(R.id.common_transparent_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(FriendsDetailActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_transparent_left_font)).setText("好友");
        ((TextView)localRelativeLayout.findViewById(R.id.common_transparent_title)).setText("SADOYO");
        localRelativeLayout.findViewById(R.id.common_transparent_right_image).setBackgroundResource(R.drawable.circle_right_more_icon);
        View localView2 = localRelativeLayout.findViewById(R.id.common_transparent_right_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(FriendsDetailActivity.this, FriendsSettingActivity.class);
                Utility.startActivitytranslate(FriendsDetailActivity.this, localIntent);
            }
        };
        localView2.setOnClickListener(local2);
        TextView localTextView1 = (TextView)findViewById(R.id.friendsDetail_create_charge);
        TextView localTextView2 = (TextView)findViewById(R.id.friendsDetail_account);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                FriendsDetailActivity localFriendsDetailActivity = FriendsDetailActivity.this;
                Intent localIntent = new Intent(FriendsDetailActivity.this, ChooseFriendsActivity.class);
                Utility.startActivitytranslate(localFriendsDetailActivity, localIntent);
            }
        };
        localTextView1.setOnClickListener(local3);
        View.OnClickListener local4 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(FriendsDetailActivity.this, FriendsAccountsActivity.class);
                Utility.startActivitytranslate(FriendsDetailActivity.this, localIntent);
            }
        };
        localTextView2.setOnClickListener(local4);
    }

    private void setupCheckState(boolean paramBoolean)
    {
        for (int i = 0; i < 3; i++)
            this.checkStateArray.put(i, paramBoolean);
    }

    private void setupData()
    {
        this.checkStateArray = new SparseBooleanArray(3);
        setupCheckState(false);
        this.friendsChargeAdapter = new FriendsChargeAdapter(this);
        this.listView.addFooterView(this.footerView);
        this.listView.setAdapter(this.friendsChargeAdapter);
        this.footerView.setVisibility(View.GONE);
        this.listView.setFooterDividersEnabled(false);
        CheckBox localCheckBox = (CheckBox)this.footerView.findViewById(R.id.friendDetail_listItem_footer_checkbox);
        CompoundButton.OnCheckedChangeListener local5 = new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
            {
                FriendsDetailActivity.this.setupCheckState(paramAnonymousBoolean);
                FriendsDetailActivity.this.friendsChargeAdapter.notifyDataSetChanged();
            }
        };
        localCheckBox.setOnCheckedChangeListener(local5);
        View localView = this.footerView.findViewById(R.id.friendDetail_listItem_footer_submit);
        View.OnClickListener local6 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Toast.makeText(FriendsDetailActivity.this, "点击了footer的确认按钮", Toast.LENGTH_LONG).show();
            }
        };
        localView.setOnClickListener(local6);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_friends_detail;
    }

    protected void initView()
    {
        initTop();
        this.listView = ((ListView)findViewById(R.id.friendsDetail_list));
        this.footerView = View.inflate(this, R.layout.custom_friend_detail_list_footer, null);
        this.footerMoneyState = ((TextView)this.footerView.findViewById(R.id.friendDetail_listItem_footer_state));
        this.footerMoney = ((TextView)this.footerView.findViewById(R.id.friendDetail_listItem_footer_money));
        setupData();
    }

    private class FriendChargeHolder
    {
        private TextView chargeMoney;
        private TextView chargeName;
        private TextView chargeType;
        private CheckBox checkBox;
        private ImageView headImage;

    }

    private class FriendsChargeAdapter extends BaseAdapter
    {
        private boolean isShowCheckbox = false;
        private LayoutInflater mInflater;

        public FriendsChargeAdapter(Context localContext)
        {
            this.mInflater = LayoutInflater.from(localContext);
        }

        public int getCount()
        {
            return 3;
        }

        public Object getItem(int paramInt)
        {
            return null;
        }

        public long getItemId(int paramInt)
        {
            return paramInt;
        }

        public boolean getShowState()
        {
            return this.isShowCheckbox;
        }

        public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            FriendsDetailActivity.FriendChargeHolder localFriendChargeHolder2;
            if (localView == null)
            {
                localFriendChargeHolder2 = new FriendsDetailActivity.FriendChargeHolder();
                localView = this.mInflater.inflate(R.layout.custom_friend_detail_listitem_layout, paramViewGroup, false);

                localFriendChargeHolder2.checkBox =  (CheckBox)localView.findViewById(R.id.friendDetail_listItem_checkbox);
                localView.setTag(localFriendChargeHolder2);

            }else {
                localFriendChargeHolder2 = (FriendsDetailActivity.FriendChargeHolder)localView.getTag();
            }

            if (this.isShowCheckbox){
                localFriendChargeHolder2.checkBox.setChecked(FriendsDetailActivity.this.checkStateArray.get(paramInt));
                localFriendChargeHolder2.checkBox.setVisibility(View.VISIBLE);
            }else {
                localFriendChargeHolder2.checkBox.setVisibility(View.GONE);
            }

            CheckBox localCheckBox = localFriendChargeHolder2.checkBox;
            CompoundButton.OnCheckedChangeListener local1 = new CompoundButton.OnCheckedChangeListener()
            {
                public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
                {
                    FriendsDetailActivity.this.checkStateArray.put(paramInt, paramAnonymousBoolean);
                }
            };
            localCheckBox.setOnCheckedChangeListener(local1);

            return localView;
        }

        public void showCountView(boolean paramBoolean)
        {
            this.isShowCheckbox = paramBoolean;
        }
    }
}
