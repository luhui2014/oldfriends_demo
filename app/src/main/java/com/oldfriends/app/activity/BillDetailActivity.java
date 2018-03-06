package com.oldfriends.app.activity;

/**
 * lh on 2016/2/25.
 */

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;

public class BillDetailActivity extends BaseActivity
{
    private void initContent()
    {
        ListView localListView = (ListView)findViewById(R.id.custom_bill_detail_pay_list);
        localListView.setDividerHeight(0);
        PayListAdapter localPayListAdapter = new PayListAdapter();
        localListView.setAdapter(localPayListAdapter);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.bill_detail_top_layout);
        localRelativeLayout.findViewById(R.id.common_left_btn).setBackgroundResource(R.drawable.back_icon);
        View localView1 = localRelativeLayout.findViewById(R.id.common_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(BillDetailActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_title)).setText(R.string.charge_detail);
        TextView localTextView = (TextView)localRelativeLayout.findViewById(R.id.common_right_font);
        localTextView.setText(R.string.common_edit);
        localTextView.setTextColor(getResources().getColor(R.color.common_grey_click_selector));
        View localView2 = localRelativeLayout.findViewById(R.id.common_right_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(BillDetailActivity.this, BillEditActivity.class);
                localIntent.putExtra("main_intent_edit", false);
                Utility.startActivitytranslate(BillDetailActivity.this, localIntent);
            }
        };
        localView2.setOnClickListener(local2);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_bill_detail_layout;
    }

    protected void initView()
    {
        initTop();
        initContent();
    }

    private class PayListAdapter extends BaseAdapter
    {
        private LayoutInflater mInflater = LayoutInflater.from(BillDetailActivity.this);

        public PayListAdapter()
        {
        }

        public int getCount()
        {
            return 4;
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
            BillDetailActivity.PayPersonHolder localPayPersonHolder1;
            if (localView == null)
            {
                localView = this.mInflater.inflate(R.layout.custom_bill_detail_list_pay_layout, paramViewGroup, false);
                localPayPersonHolder1 = new BillDetailActivity.PayPersonHolder(localView);
                localView.setTag(localPayPersonHolder1);
            }else {
                localPayPersonHolder1 = (BillDetailActivity.PayPersonHolder)localView.getTag();
            }

            if(paramInt == -1 + getCount()){
                localPayPersonHolder1.line.setVisibility(View.GONE);
            }else {
                localPayPersonHolder1.line.setVisibility(View.VISIBLE);
            }

            return localView;
        }
    }

    private class PayPersonHolder
    {
        private TextView billMoney;
        private TextView billMoneyState;
        private ImageView billPersonImage;
        private TextView billPersonName;
        private TextView line;
        private TextView payPersonState;

        public PayPersonHolder(View arg2)
        {
            this.line = ((TextView)arg2.findViewById(R.id.custom_bill_detail_pay_line));
            this.billPersonImage = ((ImageView)arg2.findViewById(R.id.custom_bill_detail_pay_image));
            this.billPersonName = ((TextView)arg2.findViewById(R.id.custom_bill_detail_pay_name));
            this.payPersonState = ((TextView)arg2.findViewById(R.id.custom_bill_detail_pay_person));
            this.billMoneyState = ((TextView)arg2.findViewById(R.id.custom_bill_detail_pay_type));
            this.billMoney = ((TextView)arg2.findViewById(R.id.custom_bill_detail_money));
        }
    }
}
