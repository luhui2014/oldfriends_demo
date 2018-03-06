package com.oldfriends.app.activity;

/**
 * Created by Administrator on 2016/2/25.
 */
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;

public class FriendsAccountsActivity extends BaseActivity
{
    private SparseBooleanArray checkStateArray;
    private FriendsChargeAdapter friendsChargeAdapter;

    private void initContent()
    {
        this.checkStateArray = new SparseBooleanArray(3);
        ListView localListView = (ListView)findViewById(R.id.friends_accounts_list);
        View localView1 = View.inflate(this, R.layout.custom_friend_detail_list_footer, null);
        this.friendsChargeAdapter = new FriendsChargeAdapter(this);
        localListView.addFooterView(localView1);
        localListView.setAdapter(this.friendsChargeAdapter);
        localListView.setFooterDividersEnabled(false);
        CheckBox localCheckBox = (CheckBox)localView1.findViewById(R.id.friendDetail_listItem_footer_checkbox);
        CompoundButton.OnCheckedChangeListener local2 = new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
            {
                FriendsAccountsActivity.this.setupCheckState(paramAnonymousBoolean);
                FriendsAccountsActivity.this.friendsChargeAdapter.notifyDataSetChanged();
            }
        };
        localCheckBox.setOnCheckedChangeListener(local2);
        View localView2 = localView1.findViewById(R.id.friendDetail_listItem_footer_submit);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Toast.makeText(FriendsAccountsActivity.this, "点击了footer的确认按钮", Toast.LENGTH_SHORT).show();
            }
        };
        localView2.setOnClickListener(local3);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.friends_accounts_top_layout);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_text)).setText(R.string.common_back);
        View localView = localRelativeLayout.findViewById(R.id.common_backFont_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(FriendsAccountsActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_title)).setText(R.string.contact_friends);
        localRelativeLayout.findViewById(R.id.common_backFont_finish).setVisibility(View.GONE);
    }

    private void setupCheckState(boolean paramBoolean)
    {
        for (int i = 0; i < 3; i++)
            this.checkStateArray.put(i, paramBoolean);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_friends_accounts_layout;
    }

    protected void initView()
    {
        initTop();
        initContent();
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

        public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            FriendsAccountsActivity.FriendChargeHolder localFriendChargeHolder2;
            if (localView == null)
            {
                localFriendChargeHolder2 = new FriendsAccountsActivity.FriendChargeHolder();
                localView = this.mInflater.inflate(R.layout.custom_friend_detail_listitem_layout, paramViewGroup, false);
                localFriendChargeHolder2.checkBox = (CheckBox)localView.findViewById(R.id.friendDetail_listItem_checkbox);
                localView.setTag(localFriendChargeHolder2);
            }else {
                localFriendChargeHolder2 = (FriendsAccountsActivity.FriendChargeHolder)localView.getTag();
            }

            localFriendChargeHolder2.checkBox.setChecked(FriendsAccountsActivity.this.checkStateArray.get(paramInt));
            localFriendChargeHolder2.checkBox.setVisibility(0);
            CheckBox localCheckBox = localFriendChargeHolder2.checkBox;
            CompoundButton.OnCheckedChangeListener local1 = new CompoundButton.OnCheckedChangeListener()
            {
                public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
                {
                    FriendsAccountsActivity.this.checkStateArray.put(paramInt, paramAnonymousBoolean);
                }
            };
            localCheckBox.setOnCheckedChangeListener(local1);

            return localView;
        }
    }
}
