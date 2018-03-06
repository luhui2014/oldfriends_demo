package com.oldfriends.app.fragment;

/**
 * lh on 2016/2/25.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.adapter.SplitViewHolder;
import com.oldfriends.app.util.PreciseCompute;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.CustomWheelDialog;

public class FragmentSplitPercent extends Fragment
{
    private static final String SPLIT_PERCENT_OR_COUNTS = "split_percent_or_counts";
    private TextView allCollection;
    private LinearLayout countLayout;
    private TextView countsAverage;
    private TextView countsCollections;
    private boolean isPercentState;
    private View layoutView;
    private LinearLayout percentLayout;
    private String[] personTempData = new String[0];
    private SparseArray<String> stringSparseArray;
    private TextView surplus;
    private double totalMoney;

    private void initView()
    {
        this.stringSparseArray = new SparseArray<String>();
        this.percentLayout = ((LinearLayout)this.layoutView.findViewById(R.id.fragment_split_percent_layout));
        this.countLayout = ((LinearLayout)this.layoutView.findViewById(R.id.fragment_split_count_layout));
        TextView localTextView1 = (TextView)this.layoutView.findViewById(R.id.fragment_split_text_title);
        if (this.isPercentState){
            localTextView1.setText("请输入各成员所承担的百分比,注意总数必须等于100%");
        }else {
            localTextView1.setText("请输入各成员所承担的份额,每一份分割将均分总金额");
        }

        TextView localTextView2 = (TextView)this.layoutView.findViewById(R.id.fragment_split_money_total);
        localTextView2.setText(this.totalMoney + "");
        this.allCollection = ((TextView)this.layoutView.findViewById(R.id.fragment_split_allocation));
        this.surplus = ((TextView)this.layoutView.findViewById(R.id.fragment_split_surplus));
        this.countsCollections = ((TextView)this.layoutView.findViewById(R.id.fragment_split_count_allocation));
        this.countsAverage = ((TextView)this.layoutView.findViewById(R.id.fragment_split_count_average));

        ListView localListView = (ListView)this.layoutView.findViewById(R.id.fragment_split_money_list);
        SplitPercentAdapter localSplitPercentAdapter = new SplitPercentAdapter(getActivity());
        localListView.setAdapter(localSplitPercentAdapter);
    }

    private void checkTotalPercent()
    {
        int i = getTotal();
        allCollection.setText(i + "%");
        surplus.setText(100 - i + "%");
        if (i > 100)
            Utility.showNoticeDialog(getActivity(), "百分比总和超出100%!");
    }

    public static FragmentSplitPercent newInstance(Double paramDouble, String[] paramArrayOfString, boolean paramBoolean)
    {
        Bundle localBundle = new Bundle();
        localBundle.putDouble("CHARGE_SPLIT_TOTAL_MONEY", paramDouble);
        localBundle.putStringArray("charge_split_person_data", paramArrayOfString);
        localBundle.putBoolean("split_percent_or_counts", paramBoolean);
        FragmentSplitPercent localFragmentSplitPercent = new FragmentSplitPercent();
        localFragmentSplitPercent.setArguments(localBundle);
        return localFragmentSplitPercent;
    }

    private void showPercentDialog(final TextView paramTextView, final int paramInt)
    {
        FragmentActivity localFragmentActivity = getActivity();
        CustomWheelDialog.WheelDialogOnclickListener local1 = new CustomWheelDialog.WheelDialogOnclickListener()
        {
            public void onSubmit(String paramAnonymousString)
            {
                stringSparseArray.put(paramInt, paramAnonymousString);
                if (FragmentSplitPercent.this.isPercentState)
                {
                    FragmentSplitPercent.this.percentLayout.setVisibility(View.VISIBLE);
                    paramTextView.setText(paramAnonymousString + "%");
                    FragmentSplitPercent.this.checkTotalPercent();
                    return;
                }
                FragmentSplitPercent.this.countLayout.setVisibility(View.VISIBLE);
                paramTextView.setText(paramAnonymousString);
                Log.d("BillEditActivity", "份额的页面  calculateAverage" + FragmentSplitPercent.this.getTotal() + ",average=" + FragmentSplitPercent.this.getAverage() + ",totalMoney=" + FragmentSplitPercent.this.totalMoney);
                countsCollections.setText(FragmentSplitPercent.this.getTotal() + "份");
                countsAverage.setText(FragmentSplitPercent.this.getAverage() + "");
            }
        };
        CustomWheelDialog localCustomWheelDialog = new CustomWheelDialog(localFragmentActivity, R.style.MyDialog, local1);
        localCustomWheelDialog.show();
        if (!this.isPercentState)
            localCustomWheelDialog.getTitleContent().setText("请选择份额数");
    }

    public double getAverage()
    {
        return PreciseCompute.div(this.totalMoney, getTotal(), 2);
    }

    public SparseArray<String> getSplitPercentData()
    {
        return this.stringSparseArray;
    }

    public int getTotal()
    {
        int i = 0;
        for (int j = 0; j < this.stringSparseArray.size(); j++)
        {
            String str = stringSparseArray.get(j);
            if (StringUtils.isNotBlank(str))
                i += Integer.parseInt(str);
        }
        return i;
    }

    public void onCreate(@Nullable Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        Bundle localBundle = getArguments();
        if (localBundle != null)
        {
            this.totalMoney = localBundle.getDouble("CHARGE_SPLIT_TOTAL_MONEY");
            this.personTempData = localBundle.getStringArray("charge_split_person_data");
            this.isPercentState = localBundle.getBoolean("split_percent_or_counts", true);
            Log.d("BillEditActivity", "传递过来的价格是=" + this.totalMoney);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        this.layoutView = paramLayoutInflater.inflate(R.layout.fragment_split_text_layout, paramViewGroup, false);
        initView();
        return this.layoutView;
    }

    private class SplitPercentAdapter extends BaseAdapter
    {
        private LayoutInflater mInflater;

        public SplitPercentAdapter(Context localContext)
        {
            this.mInflater = LayoutInflater.from(localContext);
        }

        public int getCount()
        {
            return FragmentSplitPercent.this.personTempData.length;
        }

        public String getItem(int paramInt)
        {
            return FragmentSplitPercent.this.personTempData[paramInt];
        }

        public long getItemId(int paramInt)
        {
            return paramInt;
        }

        public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            SplitViewHolder localSplitViewHolder1;
            if (localView == null)
            {
                localView = this.mInflater.inflate(R.layout.custom_split_text_listitem, paramViewGroup, false);
                localSplitViewHolder1 = new SplitViewHolder();
                localSplitViewHolder1.name = ((TextView)localView.findViewById(R.id.split_text_item_name));
                localSplitViewHolder1.textBtn = ((TextView)localView.findViewById(R.id.split_text_item_textBtn));
                localView.setTag(localSplitViewHolder1);
            }else {
                localSplitViewHolder1 = (SplitViewHolder)localView.getTag();
            }

            localSplitViewHolder1.name.setText(getItem(paramInt));
            final TextView localTextView = localSplitViewHolder1.textBtn;
            if (!FragmentSplitPercent.this.isPercentState)
                localTextView.setText("请输入份数");
            View.OnClickListener local1 = new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    FragmentSplitPercent.this.showPercentDialog(localTextView, paramInt);
                }
            };
            localTextView.setOnClickListener(local1);

            return localView;
        }
    }
}
