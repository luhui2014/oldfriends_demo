package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;

public class ChargeAccountActivity extends BaseActivity
{
    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.charge_account_top_layout);
        View localView = localRelativeLayout.findViewById(R.id.common_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(ChargeAccountActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        localRelativeLayout.findViewById(R.id.common_left_btn).setBackgroundResource(R.drawable.back_icon);
        ((TextView)localRelativeLayout.findViewById(R.id.common_title)).setText("结算");
    }

    private void setupData()
    {
        ListView localListView = (ListView)findViewById(R.id.charge_account_member_list);
        AccountMemberAdapter localAccountMemberAdapter = new AccountMemberAdapter(this);
        localListView.setAdapter(localAccountMemberAdapter);
        AdapterView.OnItemClickListener local2 = new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                Intent localIntent = new Intent(ChargeAccountActivity.this, PayActivity.class);
                Utility.startActivitytranslate(ChargeAccountActivity.this, localIntent);
            }
        };
        localListView.setOnItemClickListener(local2);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_charge_account_layout;
    }

    protected void initView()
    {
        initTop();
        setupData();
    }

    private class AccountMemberAdapter extends BaseAdapter
    {
        private LayoutInflater mInflater;

        public AccountMemberAdapter(Context arg2)
        {
            this.mInflater = LayoutInflater.from(arg2);
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

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            ChargeAccountActivity.ViewHolder localViewHolder;
            if (localView == null)
            {
                localViewHolder = new ChargeAccountActivity.ViewHolder();
                localView = this.mInflater.inflate(R.layout.custom_charge_account_list_layout, paramViewGroup, false);
                localViewHolder.headImage = ((ImageView)localView.findViewById(R.id.charge_account_member_image));
                localViewHolder.name = ((TextView)localView.findViewById(R.id.charge_account_member_name));
                localViewHolder.moneyState = ((TextView)localView.findViewById(R.id.charge_account_item_state));
                localViewHolder.totalMoney = ((TextView)localView.findViewById(R.id.charge_account_item_money));
                localView.setTag(localViewHolder);
            }else {
                localViewHolder = ((ChargeAccountActivity.ViewHolder)localView.getTag());
            }

             return localView;

        }
    }

    private class ViewHolder
    {
        ImageView headImage;
        TextView moneyState;
        TextView name;
        TextView totalMoney;

        private ViewHolder()
        {
        }
    }
}
