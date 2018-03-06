package com.oldfriends.app.fragment;

/**
 * lh on 2016/2/25.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.adapter.SplitViewHolder;
import java.math.BigDecimal;

public class FragmentSplitAverage extends Fragment
{
    private static final String SPLIT_AVERAGE_OR_MANPAY = "split_average_or_manpay";
    private LinearLayout averageLayout;
    private TextView averagePrice;
    private TextView averageTotalPersons;
    private boolean isAverageState;
    private View layoutView;
    private String[] personTempData = new String[0];
    private SparseArray<String> sparseArray;
    private int totalCount = 0;
    private Double totalPrice = Double.valueOf(0.0D);

    public FragmentSplitAverage()
    {
        this.sparseArray = new SparseArray<String>();
    }

    private double calculateSum(Double paramDouble, int paramInt)
    {
        Log.d("FragmentSplitAverage","paramDouble="+paramDouble+",paramInt="+paramInt);
        BigDecimal localBigDecimal1 = new BigDecimal(Double.toString(paramDouble.doubleValue()));
        BigDecimal localBigDecimal2 = new BigDecimal(paramInt + "");
        return localBigDecimal1.divide(localBigDecimal2, 2).doubleValue();
    }

    private void initView()
    {
        if (!this.isAverageState)
        {
            ((TextView)this.layoutView.findViewById(R.id.fragment_split_average_title)).setText("勾选参加成员,由其中男性成员进行分账");
            ((TextView)this.layoutView.findViewById(R.id.fragment_split_average_unit)).setText("/男");
        }
        this.averageLayout = ((LinearLayout)this.layoutView.findViewById(R.id.fragment_split_average_right_layout));
        this.averageTotalPersons = ((TextView)this.layoutView.findViewById(R.id.fragment_split_average_sum_persons));
        this.averagePrice = ((TextView)this.layoutView.findViewById(R.id.fragment_split_average_price));
        TextView localTextView = (TextView)this.layoutView.findViewById(R.id.fragment_split_average_sum_money);
        localTextView.setText(this.totalPrice + "");
        ListView localListView = (ListView)this.layoutView.findViewById(R.id.fragment_split_average_list);
        SplitAverageAdapter localSplitAverageAdapter = new SplitAverageAdapter(getActivity());
        localListView.setAdapter(localSplitAverageAdapter);
    }

    public static FragmentSplitAverage newInstance(Double paramDouble, String[] paramArrayOfString, boolean paramBoolean)
    {
        Bundle localBundle = new Bundle();
        localBundle.putDouble("CHARGE_SPLIT_TOTAL_MONEY", paramDouble.doubleValue());
        localBundle.putStringArray("charge_split_person_data", paramArrayOfString);
        localBundle.putBoolean("split_average_or_manpay", paramBoolean);
        FragmentSplitAverage localFragmentSplitAverage = new FragmentSplitAverage();
        localFragmentSplitAverage.setArguments(localBundle);
        return localFragmentSplitAverage;
    }

    public SparseArray<String> getSplitAverageData()
    {
        return this.sparseArray;
    }

    public void onCreate(@Nullable Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        Bundle localBundle = getArguments();
        if (localBundle != null)
        {
            this.totalPrice = Double.valueOf(localBundle.getDouble("CHARGE_SPLIT_TOTAL_MONEY"));
            this.personTempData = localBundle.getStringArray("charge_split_person_data");
            this.isAverageState = localBundle.getBoolean("split_average_or_manpay", true);
            Log.d("BillEditActivity", "传递过来的价格是=" + this.totalPrice);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        this.layoutView = paramLayoutInflater.inflate(R.layout.fragment_split_check_layout, paramViewGroup, false);
        initView();
        return this.layoutView;
    }

    public class SplitAverageAdapter extends BaseAdapter
    {
        private LayoutInflater mInflater;

        public SplitAverageAdapter(Context arg2)
        {
            this.mInflater = LayoutInflater.from(arg2);
        }

        public int getCount()
        {
            return FragmentSplitAverage.this.personTempData.length;
        }

        public String getItem(int paramInt)
        {
            return FragmentSplitAverage.this.personTempData[paramInt];
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
                localView = this.mInflater.inflate(R.layout.custom_split_check_listitem, paramViewGroup, false);
                localSplitViewHolder1 = new SplitViewHolder();
                localSplitViewHolder1.headImage = ((ImageView)localView.findViewById(R.id.split_check_item_image));
                localSplitViewHolder1.name = ((TextView)localView.findViewById(R.id.split_check_item_name));
                localSplitViewHolder1.checkBox = ((CheckBox)localView.findViewById(R.id.split_check_item_checkbox));
                localView.setTag(localSplitViewHolder1);
            }else {
                localSplitViewHolder1 = (SplitViewHolder)localView.getTag();
            }

            localSplitViewHolder1.name.setText(getItem(paramInt));
            CheckBox localCheckBox = localSplitViewHolder1.checkBox;
            CompoundButton.OnCheckedChangeListener local1 = new CompoundButton.OnCheckedChangeListener()
            {
                public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
                {
                    if (paramAnonymousBoolean)
                    {
                        sparseArray.put(paramInt, getItem(paramInt));
                        totalCount = sparseArray.size();
                    }else {
                        TextView localTextView1 = FragmentSplitAverage.this.averageTotalPersons;
                        localTextView1.setText(FragmentSplitAverage.this.totalCount + "");
                        if ((FragmentSplitAverage.this.totalCount != 0) && (FragmentSplitAverage.this.totalPrice.doubleValue() != 0.0D)){
                            FragmentSplitAverage.this.sparseArray.remove(paramInt);
                        }else {
                            FragmentSplitAverage.this.averageLayout.setVisibility(View.GONE);
                            FragmentSplitAverage.this.averagePrice.setText("0");
                        }
                        totalCount = sparseArray.size();
                    }

                    FragmentSplitAverage.this.averageLayout.setVisibility(View.VISIBLE);
                    TextView localTextView2 = FragmentSplitAverage.this.averagePrice;
                    localTextView2.setText(FragmentSplitAverage.this.calculateSum(FragmentSplitAverage.this.totalPrice, FragmentSplitAverage.this.totalCount) + "");
                }
            };
            localCheckBox.setOnCheckedChangeListener(local1);

            return localView;

        }
    }
}
