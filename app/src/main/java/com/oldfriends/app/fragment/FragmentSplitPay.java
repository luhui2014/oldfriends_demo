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
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.model.SplitPersonModel;
import com.oldfriends.app.util.PreciseCompute;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.view.ListViewSplitCompat;
import com.oldfriends.app.view.SlideView;

import java.util.ArrayList;
import java.util.List;

public class FragmentSplitPay extends Fragment {
    private View layoutView;
    private SlideView mLastSlideViewWithStatusOn;
    private List<MessageItem> messageItemList;
    private TextView payAverageText;
    private LinearLayout payBottomLayout;
    private int payCounts;
    private TextView payPersonCounts;
    private String[] personTempData = new String[0];
    private SparseArray<String> sparseArray;
    private int totalCounts;
    private double totalMoney;

    public FragmentSplitPay() {
        this.sparseArray = new SparseArray<String>();
    }

    private void dealPayBtn(TextView paramTextView, int paramInt, String paramString) {
        Log.d("FragmentSplitPay", "dealPayBtn position=" + paramInt + ",name=" + paramString);
        if (paramTextView.getText().toString().equals("取消")) {
            paramTextView.setText("我付!");
            if (this.payCounts <= 0) {
                this.payCounts = 0;
                this.sparseArray.put(paramInt, paramString);
            }
        } else {
            TextView localTextView = this.payPersonCounts;
            localTextView.setText(this.payCounts + "");
            this.payCounts = (-1 + this.payCounts);

            Log.d("FragmentSplitPay", "调用 paycount + + 了");
            this.payCounts = (1 + this.payCounts);
            this.sparseArray.remove(paramInt);
            paramTextView.setText("取消");
        }
    }

    private void initData() {
        ArrayList localArrayList = new ArrayList();
        this.messageItemList = localArrayList;
        for (String str : this.personTempData) {
            MessageItem localMessageItem = new MessageItem();
            localMessageItem.userName = str;
            this.messageItemList.add(localMessageItem);
        }
    }

    private void initView() {
        TextView localTextView = (TextView) this.layoutView.findViewById(R.id.fragment_split_pay_sum_money);
        localTextView.setText(this.totalMoney + "");
        this.payBottomLayout = ((LinearLayout) this.layoutView.findViewById(R.id.fragment_split_pay_right_layout));
        this.payPersonCounts = ((TextView) this.layoutView.findViewById(R.id.fragment_split_minePay_person));
        this.payAverageText = ((TextView) this.layoutView.findViewById(R.id.fragment_split_pay_average_money));
        ListViewSplitCompat localListViewSplitCompat = (ListViewSplitCompat) this.layoutView.findViewById(R.id.fragment_split_pay_list);
        PayListAdapter localPayListAdapter = new PayListAdapter(getActivity());
        localListViewSplitCompat.setAdapter(localPayListAdapter);
    }

    public static FragmentSplitPay newInstance(Double paramDouble, String[] paramArrayOfString) {
        FragmentSplitPay localFragmentSplitPay = new FragmentSplitPay();
        Bundle localBundle = new Bundle();
        localBundle.putDouble("CHARGE_SPLIT_TOTAL_MONEY", paramDouble.doubleValue());
        localBundle.putStringArray("charge_split_person_data", paramArrayOfString);
        localFragmentSplitPay.setArguments(localBundle);
        return localFragmentSplitPay;
    }

    private void setCheckBoxChangeEven(CheckBox paramCheckBox, final int paramInt, final String paramString) {
        CompoundButton.OnCheckedChangeListener local1 = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
                if (paramAnonymousBoolean) {
                    FragmentSplitPay.this.sparseArray.put(paramInt, paramString);
                } else {
                    if ((FragmentSplitPay.this.totalCounts == 0) || (FragmentSplitPay.this.totalMoney == 0.0D)) {
                        FragmentSplitPay.this.payBottomLayout.setVisibility(View.GONE);
                        FragmentSplitPay.this.payAverageText.setText("0");

                    } else {
                        sparseArray.remove(paramInt);
                    }
                    FragmentSplitPay.this.payBottomLayout.setVisibility(View.VISIBLE);
                    TextView localTextView = FragmentSplitPay.this.payAverageText;
                    localTextView.setText(PreciseCompute.div(FragmentSplitPay.this.totalMoney, FragmentSplitPay.this.totalCounts, 2) + "");
                }
            }
        };
        paramCheckBox.setOnCheckedChangeListener(local1);
    }

    public ArrayList<SplitPersonModel> getSplitPayData() {
        ArrayList localArrayList = new ArrayList();
        Log.d("FragmentSplitPay", "getSplitPayData totalCounts=" + this.totalCounts + ",payCounts=" + this.payCounts + ",totalMoney=" + this.totalMoney);
        Log.d("FragmentSplitPay", "sparseArray size=" + this.sparseArray.size());
        if (this.sparseArray.size() > 0) {
            for (int i = 0; i < this.totalCounts; i++) {
                String str = sparseArray.get(i);
                if (StringUtils.isNotBlank(str)) {
                    Log.e("FragmentSplitPay", "sparseArray name=" + str);
                    SplitPersonModel localSplitPersonModel = new SplitPersonModel();
                    Double localDouble = Double.valueOf(PreciseCompute.div(this.totalMoney, this.totalCounts, 2));
                    localSplitPersonModel.setName(str);
                    localSplitPersonModel.setMoney(localDouble);
                    localArrayList.add(localSplitPersonModel);
                }
            }
            return localArrayList;
        }
        return null;
    }

    public int getTotalCounts() {
        return this.totalCounts;
    }

    public void onCreate(@Nullable Bundle paramBundle) {
        super.onCreate(paramBundle);
        Bundle localBundle = getArguments();
        if (localBundle != null) {
            this.totalMoney = localBundle.getDouble("CHARGE_SPLIT_TOTAL_MONEY");
            this.personTempData = localBundle.getStringArray("charge_split_person_data");
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.layoutView = paramLayoutInflater.inflate(R.layout.fragment_split_checkpay_layout, paramViewGroup, false);
        initData();
        initView();
        return this.layoutView;
    }

    public class MessageItem {
        public SlideView slideView;
        private String userName;

    }

    private class PayListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public PayListAdapter(Context arg2) {
            this.mInflater = LayoutInflater.from(arg2);
        }

        public int getCount() {
            return FragmentSplitPay.this.messageItemList.size();
        }

        public FragmentSplitPay.MessageItem getItem(int paramInt) {
            return messageItemList.get(paramInt);
        }

        public long getItemId(int paramInt) {
            return 0L;
        }

        public View getView(final int paramInt, View convertView, ViewGroup paramViewGroup) {

            ViewHolder holder;
            SlideView slideView = (SlideView) convertView;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.custom_split_check_listitem, paramViewGroup, false);
            }

            if (slideView == null) {
                slideView = new SlideView(getActivity());
                slideView.setContentView(convertView);
                holder = new ViewHolder(slideView);

                slideView.setOnSlideListener(new SlideView.OnSlideListener() {
                    @Override
                    public void onSlide(View view, int status) {
                        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
                            Log.d("LoadPreSetActivity", "  onSlide   shrink ");
                            mLastSlideViewWithStatusOn.shrink();
                        }

                        if (status == SLIDE_STATUS_ON) {
                            Log.d("LoadPreSetActivity", "  onSlide   status SLIDE_STATUS_ON");
                            mLastSlideViewWithStatusOn = (SlideView) view;
                        }
                    }
                });

                slideView.setTag(holder);
            } else {
                holder = (ViewHolder) slideView.getTag();
            }

            final FragmentSplitPay.MessageItem item = getItem(paramInt);
            item.slideView = slideView;
            item.slideView.shrink();
            holder.name.setText(item.userName);

            final TextView localTextView = holder.mergText;
            final CheckBox localCheckBox = holder.checkBox;
            setCheckBoxChangeEven(localCheckBox, paramInt, item.userName);

            holder.payHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (localCheckBox.isChecked()) {
                        FragmentSplitPay.this.dealPayBtn(localTextView, paramInt, item.userName);
                        return;
                    }
                    Toast.makeText(FragmentSplitPay.this.getActivity(), "该好友未参与分账...", Toast.LENGTH_LONG).show();
                }
            });

            return slideView;
        }

        class ViewHolder {
            private CheckBox checkBox;
            private TextView mergText;
            private TextView name;
            public ViewGroup payHolder;

            ViewHolder(View arg2) {
                this.payHolder = ((ViewGroup) arg2.findViewById(R.id.holder));
                this.mergText = ((TextView) this.payHolder.findViewById(R.id.delete));
                this.mergText.setText("我付!");
                this.checkBox = ((CheckBox) arg2.findViewById(R.id.split_check_item_checkbox));
                this.name = ((TextView) arg2.findViewById(R.id.split_check_item_name));
            }
        }
    }
}
