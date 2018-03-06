package com.oldfriends.app.fragment;

/**
 * lh on 2016/2/25.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.adapter.SplitViewHolder;
import com.oldfriends.app.util.StringUtils;

public class FragmentSplitMoney extends Fragment
{
    private View layoutView;
    private String[] personTempData = new String[0];
    private SparseArray<Double> sparseArray;
    private Double totalMoney = Double.valueOf(0.0D);

    public FragmentSplitMoney()
    {
        this.sparseArray = new SparseArray<Double>();
    }

    private void initBottom()
    {
        TextView localTextView1 = (TextView)this.layoutView.findViewById(R.id.fragment_split_money_total);
        localTextView1.setText(this.totalMoney + "");
        TextView localTextView2 = (TextView)this.layoutView.findViewById(R.id.fragment_split_edit_sum_persons);
        localTextView2.setText(this.personTempData.length + "");
    }

    private void initView()
    {
        setupData();
        initBottom();
    }

    public static FragmentSplitMoney newInstance(Double paramDouble, String[] paramArrayOfString)
    {
        Bundle localBundle = new Bundle();
        localBundle.putDouble("CHARGE_SPLIT_TOTAL_MONEY", paramDouble.doubleValue());
        localBundle.putStringArray("charge_split_person_data", paramArrayOfString);
        FragmentSplitMoney localFragmentSplitMoney = new FragmentSplitMoney();
        localFragmentSplitMoney.setArguments(localBundle);
        return localFragmentSplitMoney;
    }

    private void setEditChangeListener(EditText paramEditText, final int paramInt)
    {
        TextWatcher local1 = new TextWatcher()
        {
            public void afterTextChanged(Editable paramAnonymousEditable)
            {
                if (StringUtils.isNotBlank(paramAnonymousEditable.toString()))
                {
                    Double localDouble = Double.parseDouble(paramAnonymousEditable.toString());
                    FragmentSplitMoney.this.sparseArray.put(paramInt, localDouble);
                }
            }

            public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
            {
            }

            public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
            {
            }
        };
        paramEditText.addTextChangedListener(local1);
    }

    private void setupData()
    {
        for (int i = 0; i < this.personTempData.length; i++)
            this.sparseArray.put(i, Double.valueOf(0.0D));
        ListView localListView = (ListView)this.layoutView.findViewById(R.id.fragment_split_money_list);
        SplitEditAdapter localSplitEditAdapter = new SplitEditAdapter(getActivity());
        localListView.setAdapter(localSplitEditAdapter);
    }

    public SparseArray<Double> getSplitMoneyData()
    {
        Log.d("BillSplitTypeActivity", "FragmentSplitMoney getSplitMoneyData 调用了这个函数");
        return this.sparseArray;
    }

    public void onCreate(@Nullable Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        Bundle localBundle = getArguments();
        if (localBundle != null)
        {
            this.totalMoney = Double.valueOf(localBundle.getDouble("CHARGE_SPLIT_TOTAL_MONEY"));
            this.personTempData = localBundle.getStringArray("charge_split_person_data");
            Log.d("BillEditActivity", "传递过来的价格是=" + this.totalMoney);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        this.layoutView = paramLayoutInflater.inflate(R.layout.fragment_split_edit_layout, paramViewGroup, false);
        initView();
        return this.layoutView;
    }

    private class SplitEditAdapter extends BaseAdapter
    {
        private LayoutInflater mInflater;

        public SplitEditAdapter(Context arg2)
        {
            this.mInflater = LayoutInflater.from(arg2);
        }

        public int getCount()
        {
            return FragmentSplitMoney.this.personTempData.length;
        }

        public String getItem(int paramInt)
        {
            return FragmentSplitMoney.this.personTempData[paramInt];
        }

        public long getItemId(int paramInt)
        {
            return paramInt;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            SplitViewHolder localSplitViewHolder1;
            if (localView == null)
            {
                localView = this.mInflater.inflate(R.layout.custom_split_edit_listitem, paramViewGroup, false);
                localSplitViewHolder1 = new SplitViewHolder();
                localSplitViewHolder1.headImage = ((ImageView)localView.findViewById(R.id.split_edit_item_image));
                localSplitViewHolder1.name = ((TextView)localView.findViewById(R.id.split_edit_item_name));
                localSplitViewHolder1.editText = ((EditText)localView.findViewById(R.id.split_edit_item_edit));
                localView.setTag(localSplitViewHolder1);
            }else {
                localSplitViewHolder1 = (SplitViewHolder)localView.getTag();
            }

            localSplitViewHolder1.name.setText(getItem(paramInt));
            FragmentSplitMoney.this.setEditChangeListener(localSplitViewHolder1.editText, paramInt);

            return localView;
        }
    }
}
