package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;

public class BillGroupChooseActivity extends BaseActivity
{
    public static final String CHOOSE_LIST_RESULT_DATA = "LIST_RESULT_DATA";
    private String[] groupTempData = { "复仇者联盟", "大栗科技", "CWX,周小夕", "209", "CWX,joane,Vio" };
    private boolean isPersonChoose = false;
    private String[] personTempData = { "CWX", "钢铁侠", "雷神", "美国队长" };

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.bill_group_choose_top);
        View localView = localRelativeLayout.findViewById(R.id.common_font_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(BillGroupChooseActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        TextView localTextView = (TextView)localRelativeLayout.findViewById(R.id.common_font_title);
        if (this.isPersonChoose){
            localTextView.setText("选择付款人");
        }else {
            localRelativeLayout.findViewById(R.id.common_font_right_layout).setVisibility(View.GONE);
            localTextView.setText("所属群账");
        }
    }

    private void setupData()
    {
        ListView localListView = (ListView)findViewById(R.id.bill_group_choose_list);
        BillChooseGroupAdapter localBillChooseGroupAdapter1;
        if (this.isPersonChoose){
            localBillChooseGroupAdapter1 = new BillChooseGroupAdapter(this, this.personTempData);
        }else {
            localBillChooseGroupAdapter1 = new BillChooseGroupAdapter(this, this.groupTempData);
        }

        localListView.setAdapter(localBillChooseGroupAdapter1);
        localListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String str = (String)parent.getAdapter().getItem(position);
                    Intent localIntent = new Intent();
                    localIntent.putExtra(CHOOSE_LIST_RESULT_DATA, str);
                    BillGroupChooseActivity.this.setResult(-1, localIntent);
                    BillGroupChooseActivity.this.finish();
                    BillGroupChooseActivity.this.overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_left_out);
            }
        });
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_bill_group_choose_layout;
    }

    protected void initView()
    {
        this.isPersonChoose = getIntent().getBooleanExtra(BillEditActivity.INTENT_TO_PERSONCHOOSE_STATE, false);
        initTop();
        setupData();
    }

    private class BillChooseGroupAdapter extends BaseAdapter
    {
        private LayoutInflater mInflater;
        private String[] tempData;

        public BillChooseGroupAdapter(Context paramArrayOfString, String[] arg3)
        {
            this.mInflater = LayoutInflater.from(paramArrayOfString);
            this.tempData = arg3;
        }

        public int getCount()
        {
            return this.tempData.length;
        }

        public String getItem(int paramInt)
        {
            return this.tempData[paramInt];
        }

        public long getItemId(int paramInt)
        {
            return paramInt;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            BillGroupChooseActivity.ChooseGroupViewHolder localChooseGroupViewHolder2;
            if (localView == null)
            {
                localChooseGroupViewHolder2 = new BillGroupChooseActivity.ChooseGroupViewHolder();
                localView = this.mInflater.inflate(R.layout.custom_bill_group_choose_item_layout, paramViewGroup, false);
                localChooseGroupViewHolder2.groupImage = ((ImageView)localView.findViewById(R.id.bill_chooseGroup_item_image));
                localChooseGroupViewHolder2.groupName = ((TextView)localView.findViewById(R.id.bill_chooseGroup_item_name));
                localChooseGroupViewHolder2.line = ((TextView)localView.findViewById(R.id.bill_chooseGroup_item_line));
                localView.setTag(localChooseGroupViewHolder2);
                localChooseGroupViewHolder2.groupName.setText(getItem(paramInt));

            }else {
                localChooseGroupViewHolder2 = (BillGroupChooseActivity.ChooseGroupViewHolder)localView.getTag();
            }

            if (paramInt != -1 + getCount()){
                localChooseGroupViewHolder2.line.setVisibility(View.VISIBLE);
            }else {
                localChooseGroupViewHolder2.line.setVisibility(View.GONE);
            }

            return localView;
        }
    }

    private class ChooseGroupViewHolder
    {
        ImageView groupImage;
        TextView groupName;
        TextView line;
    }
}