package com.oldfriends.app.activity;

/**
 * lh on 2016/2/25.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.fragment.FragmentSplitAverage;
import com.oldfriends.app.fragment.FragmentSplitMoney;
import com.oldfriends.app.fragment.FragmentSplitPay;
import com.oldfriends.app.fragment.FragmentSplitPercent;
import com.oldfriends.app.model.SplitPersonModel;
import com.oldfriends.app.util.PreciseCompute;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.CustomNoScrollViewPager;
import com.oldfriends.app.view.PagerSlidingTabStrip;
import java.util.ArrayList;

public class BillSplitTypeActivity extends FragmentActivity
{
    public static final String BILL_SPLIT_PAYALL = "bill_split_payall";
    public static final String BILL_SPLIT_TYPE = "bill_split_type";
    public static final String BILL_SPLIT_TYPE_DATA = "bill_split_type_data";
    public static final String CHARGE_SPLIT_PERSON_DATA = "charge_split_person_data";
    public static final String CHARGE_SPLIT_TOTAL_MONEY = "CHARGE_SPLIT_TOTAL_MONEY";
    private String[] personTempData = { "科比", "钢铁侠", "雷神", "美国队长" };
    private FragmentSplitAverage splitAverage;
    private FragmentSplitPercent splitCount;
    private FragmentSplitAverage splitManPay;
    private FragmentSplitMoney splitMoney;
    private FragmentSplitPay splitPay;
    private FragmentSplitPercent splitPercent;
    private String[] titles = { "平均分账", "金额分账", "百分比分账", "份额分账", "我来买单", "男A女免" };
    private Double totalMoney;
    private CustomNoScrollViewPager viewpager;

    private boolean checkTotalMoney(SparseArray<Double> paramSparseArray)
    {
        double d = 0.0D;
        for (int i = 0; i < paramSparseArray.size(); i++)
        {
            Double localDouble = paramSparseArray.get(i);
            d += localDouble;
            Log.d("FragmentSplitMoney", "checkTotalMoney 获取的值 editGetWay=" + localDouble + ",求和求得 total=" + d + ",传递过来总的 totalMoney=" + this.totalMoney);
        }
        if (d == this.totalMoney);
        for (boolean bool = true; ; bool = false)
            return bool;
    }

    private ArrayList<SplitPersonModel> getAverageListData(SparseArray<String> paramSparseArray)
    {
        ArrayList<SplitPersonModel> localArrayList = new ArrayList<SplitPersonModel>();
        int i = paramSparseArray.size();
        double d = PreciseCompute.div(this.totalMoney, i, 2);
        for (int j = 0; j < i; j++)
        {
            SplitPersonModel localSplitPersonModel = new SplitPersonModel();
            localSplitPersonModel.setName(paramSparseArray.get(j));
            localSplitPersonModel.setMoney(d);
            localArrayList.add(localSplitPersonModel);
        }
        return localArrayList;
    }

    private void getFragmentData(int paramInt)
    {
        switch (paramInt)
        {
            default:
                return;
            case 0:
                if (this.splitAverage != null)
                {
                    SparseArray<String> localSparseArray5 = this.splitAverage.getSplitAverageData();
                    if (localSparseArray5.size() == 0)
                    {
                        Utility.showNoticeDialog(this, "请选择支付人...");
                        return;
                    }
                    intentData2Main(getAverageListData(localSparseArray5), 0);
                    return;
                }
                finish();
                return;
            case 1:
                if (this.splitMoney != null)
                {
                    SparseArray<Double> localSparseArray4 = this.splitMoney.getSplitMoneyData();
                    if (!checkTotalMoney(localSparseArray4))
                    {
                        Utility.showNoticeDialog(this, "分账金额与总金额不符!");
                        return;
                    }
                    intentData2Main(getMoneyListData(localSparseArray4), 1);
                    return;
                }
                finish();
                return;
            case 2:
                if (this.splitPercent != null)
                {
                    SparseArray<String> localSparseArray3 = this.splitPercent.getSplitPercentData();
                    if (localSparseArray3.size() == 0)
                    {
                        Utility.showNoticeDialog(this, "未设置份额百分比!");
                        return;
                    }
                    intentData2Main(getPercentListData(localSparseArray3, -1.0D), 2);
                    return;
                }
                finish();
                return;
            case 3:
                if (this.splitCount != null)
                {
                    SparseArray<String> localSparseArray2 = this.splitCount.getSplitPercentData();
                    if (localSparseArray2.size() == 0)
                    {
                        Utility.showNoticeDialog(this, "未设置份额!");
                        return;
                    }
                    intentData2Main(getPercentListData(localSparseArray2, this.splitCount.getAverage()), 3);
                    return;
                }
                finish();
                return;
            case 4:
                if (this.splitPay != null)
                {
                    ArrayList<SplitPersonModel> localArrayList = this.splitPay.getSplitPayData();
                    if (localArrayList != null)
                    {
                        intentData2Main(localArrayList, 4);
                        return;
                    }
                    if (this.splitPay.getTotalCounts() > 0)
                    {
                        intentData2Main(4);
                        return;
                    }
                    Utility.showNoticeDialog(this, "未选择参与分账的好友!");
                    return;
                }
                finish();
                return;
            case 5:
        }
        if (this.splitManPay != null)
        {
            SparseArray<String> localSparseArray1 = this.splitManPay.getSplitAverageData();
            if (localSparseArray1.size() == 0)
            {
                Utility.showNoticeDialog(this, "请选择支付人...");
                return;
            }
            intentData2Main(getAverageListData(localSparseArray1), 5);
            return;
        }
        finish();
    }

    private ArrayList<SplitPersonModel> getMoneyListData(SparseArray<Double> paramSparseArray)
    {
        ArrayList<SplitPersonModel> localArrayList = new ArrayList<SplitPersonModel>();
        for (int i = 0; i < paramSparseArray.size(); i++)
        {
            SplitPersonModel localSplitPersonModel = new SplitPersonModel();
            Double localDouble = paramSparseArray.get(i);
            localSplitPersonModel.setName(this.personTempData[i]);
            localSplitPersonModel.setMoney(localDouble);
            localArrayList.add(localSplitPersonModel);
        }
        return localArrayList;
    }

    private ArrayList<SplitPersonModel> getPercentListData(SparseArray<String> paramSparseArray, double paramDouble)
    {
        ArrayList<SplitPersonModel> localArrayList = new ArrayList<SplitPersonModel>();
        int i = 0;
        if (i < paramSparseArray.size())
        {
            SplitPersonModel localSplitPersonModel = new SplitPersonModel();
            String str = paramSparseArray.get(i);
            if (StringUtils.isNotBlank(str)) {
                if (paramDouble != -1.0D)
                    localSplitPersonModel.setMoney(PreciseCompute.div(Integer.parseInt(str) * this.totalMoney, 100.0D, 2));
            }else {
                for(int j=0;j<paramSparseArray.size();j++){
                    localSplitPersonModel.setName(this.personTempData[i]);
                    localArrayList.add(localSplitPersonModel);
                }

                localSplitPersonModel.setMoney(PreciseCompute.mul(paramDouble, Integer.parseInt(str)));
                localSplitPersonModel.setMoney(0.0D);
            }
        }
        return localArrayList;
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.bill_split_type_top);
        View localView1 = localRelativeLayout.findViewById(R.id.common_font_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(BillSplitTypeActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_font_title)).setText("分账方式");
        View localView2 = localRelativeLayout.findViewById(R.id.common_font_right_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                int i = BillSplitTypeActivity.this.viewpager.getCurrentItem();
                Log.d("BillSplitTypeActivity", "当前所在的页面 currentItem=" + i);
                BillSplitTypeActivity.this.getFragmentData(i);
            }
        };
        localView2.setOnClickListener(local2);
    }

    private void initViewPager()
    {
        viewpager = ((CustomNoScrollViewPager)findViewById(R.id.bill_split_viewpager));
        viewpager.setAdapter(new AttentionAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip strip = (PagerSlidingTabStrip) findViewById(R.id.tabstrip);  //获取PagerSlidingTabStrip控件对象
        strip.setViewPager(viewpager); //这是其所handle的ViewPager

    }

    private void intentData2Main(int paramInt)
    {
        Intent localIntent = new Intent();
        localIntent.putExtra("bill_split_type", this.titles[paramInt]);
        localIntent.putExtra("bill_split_payall", true);
        setResult(-1, localIntent);
        overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_left_out);
        finish();
    }

    private void intentData2Main(ArrayList<SplitPersonModel> paramArrayList, int paramInt)
    {
        Intent localIntent = new Intent();
        localIntent.putParcelableArrayListExtra("bill_split_type_data", paramArrayList);
        localIntent.putExtra("bill_split_type", this.titles[paramInt]);
        setResult(-1, localIntent);
        overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_left_out);
        finish();
    }

    protected void initView()
    {
        initTop();
        initViewPager();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_bill_split_type_layout);
        this.totalMoney = getIntent().getDoubleExtra("BILL_EDIT_TOTAL_MONEY", 0.0D);
        initView();
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {
        if (paramInt == 4)
        {
            finish();
            overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_left_out);
            return false;
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    public class AttentionAdapter extends FragmentPagerAdapter
    {
        public AttentionAdapter(FragmentManager arg2)
        {
            super(arg2);
        }

        public int getCount()
        {
            return titles.length;
        }

        public CharSequence getPageTitle(int paramInt)
        {
            return titles[paramInt];
        }

        public Fragment getItem(int paramInt)
        {
            switch (paramInt) {
                case 0:
                    if (splitAverage == null) {
                        splitAverage = FragmentSplitAverage.newInstance(BillSplitTypeActivity.this.totalMoney, BillSplitTypeActivity.this.personTempData, true);
                    }
                    return splitAverage;
                case 1:
                    if (splitMoney == null) {
                        splitMoney = FragmentSplitMoney.newInstance(BillSplitTypeActivity.this.totalMoney, BillSplitTypeActivity.this.personTempData);
                    }
                    return splitMoney;
                case 2:
                    if (splitPercent == null) {
                        splitPercent = FragmentSplitPercent.newInstance(BillSplitTypeActivity.this.totalMoney, BillSplitTypeActivity.this.personTempData, true);
                    }
                    return splitPercent;
                case 3:
                    if (splitCount == null) {
                        splitCount = FragmentSplitPercent.newInstance(BillSplitTypeActivity.this.totalMoney, BillSplitTypeActivity.this.personTempData, false);
                    }
                    return splitCount;
                case 4:
                    if (splitPay == null){
                        splitPay = FragmentSplitPay.newInstance(BillSplitTypeActivity.this.totalMoney, BillSplitTypeActivity.this.personTempData);
                    }
                    return BillSplitTypeActivity.this.splitPay;
                case 5:
                    if (splitManPay == null) {
                        splitManPay = FragmentSplitAverage.newInstance(BillSplitTypeActivity.this.totalMoney, BillSplitTypeActivity.this.personTempData, false);
                    }
                    return BillSplitTypeActivity.this.splitManPay;
                default:
                    if (splitAverage == null) {
                        splitAverage = FragmentSplitAverage.newInstance(BillSplitTypeActivity.this.totalMoney, BillSplitTypeActivity.this.personTempData, true);
                    }
                    return splitAverage;
            }
        }


    }
}
