package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.org.xclcharts.common.DensityUtil;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.CustomExportBillDialog;

public class ExportBillActivity extends BaseActivity
{
    private Context mContext;

    private void initContent()
    {
        LinearLayout localLinearLayout = (LinearLayout)findViewById(R.id.export_bill_head);
        for (int i = 0; i < 4; i++)
        {
            TextView localTextView = (TextView)View.inflate(this.mContext, R.layout.custom_exportbill_item_layout, null);
            localTextView.setWidth(DensityUtil.dip2px(this, 110.0F));
            localTextView.setText("测试i=" + i);
            localLinearLayout.addView(localTextView);
        }
        ListView localListView = (ListView)findViewById(R.id.export_bill_contentList);
        Adapter localAdapter = new Adapter();
        localListView.setAdapter(localAdapter);
    }

    private void initTop()
    {
        View localView1 = findViewById(R.id.export_bill_left_layout);
        View.OnClickListener local1 = new View.OnClickListener(){
            public void onClick(View paramAnonymousView){
                Utility.finishActivityTranslate(ExportBillActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        View localView2 = findViewById(R.id.export_bill_right_layout);
        View.OnClickListener local2 = new View.OnClickListener(){
            public void onClick(View paramAnonymousView){
                ExportBillActivity.this.showDialog();
            }
        };
        localView2.setOnClickListener(local2);
    }

    private void showDialog()
    {
        Context localContext = this.mContext;
        CustomExportBillDialog.ExportBillListener local3 = new CustomExportBillDialog.ExportBillListener()
        {
        };
        CustomExportBillDialog localCustomExportBillDialog = new CustomExportBillDialog(localContext, R.style.MyDialog, local3);
        localCustomExportBillDialog.show();
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_export_bill_list_layout;
    }

    protected void initView()
    {
        this.mContext = this;
        initTop();
        initContent();
    }

    private class Adapter extends BaseAdapter
    {
        private LayoutInflater mInflater = LayoutInflater.from(ExportBillActivity.this.mContext);

        public Adapter()
        {
        }

        public int getCount()
        {
            return 5;
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
            if (localView == null)
            {
                ExportBillActivity.BillReport localBillReport = new ExportBillActivity.BillReport();
                localView = this.mInflater.inflate(R.layout.custom_export_bill_list_layout, paramViewGroup, false);
                localBillReport.headLayout = ((LinearLayout)localView.findViewById(R.id.export_bill_listItem_head));
                for (int i = 0; i < 4; i++)
                {
                    TextView localTextView = (TextView)this.mInflater.inflate(R.layout.custom_exportbill_item_layout, null);
                    localTextView.setWidth(DensityUtil.dip2px(ExportBillActivity.this, 110.0F));
                    localTextView.setText("测试i=" + i);
                    localBillReport.headLayout.addView(localTextView);
                }
            }
            return localView;
        }
    }

    private class BillReport{
        LinearLayout headLayout;
    }
}
