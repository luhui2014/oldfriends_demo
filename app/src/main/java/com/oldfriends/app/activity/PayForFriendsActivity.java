package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.util.Utility;

public class PayForFriendsActivity extends BaseActivity
{
    public static final String CHOOSE_PAYFOR_PERSON_NAME = "CHOOSE_PAYFOR_PERSON_NAME";
    private PayForFriendsAdapter adapter;
    private SparseArray<String> checkStateArray;
    private boolean[] checkboxState;
    private CheckBox headCheckbox;
    private View headView;

    private void finishWithName(String paramString)
    {
        Intent localIntent = new Intent();
        localIntent.putExtra(CHOOSE_PAYFOR_PERSON_NAME, paramString);
        setResult(-1, localIntent);
        Utility.finishActivityTranslate(this);
    }

    private void initListHead()
    {
        this.headView = View.inflate(this, R.layout.custom_payfor_list_head, null);
        this.headCheckbox = ((CheckBox)this.headView.findViewById(R.id.payFor_list_head_checkbox));
        CheckBox localCheckBox = this.headCheckbox;
        CompoundButton.OnCheckedChangeListener local3 = new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
            {
                if (paramAnonymousBoolean)
                    PayForFriendsActivity.this.setDefaultCheckState();
            }
        };
        localCheckBox.setOnCheckedChangeListener(local3);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.payFor_somebody_top);
        View localView = localRelativeLayout.findViewById(R.id.common_backFont_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(PayForFriendsActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_title)).setText("为Ta买单");
        TextView localTextView = (TextView)localRelativeLayout.findViewById(R.id.common_backFont_finish);
        localTextView.setTextColor(getResources().getColor(R.color.common_text_click_color));
        localTextView.setText(getResources().getString(R.string.common_submit));
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (PayForFriendsActivity.this.headCheckbox.isChecked())
                {
                    PayForFriendsActivity.this.finishWithName("无");
                    return;
                }
                PayForFriendsActivity.this.showCheckPersonInfo();
            }
        };
        localTextView.setOnClickListener(local2);
    }

    private void setDefaultCheckState()
    {
        if (this.adapter != null)
        {
            for (int i = 0; i < this.adapter.getCount(); i++)
                this.checkboxState[i] = false;
            this.adapter.notifyDataSetChanged();
        }
    }

    private void setupData()
    {
        this.checkboxState = new boolean[] { false, false, false };
        ListView localListView = (ListView)findViewById(R.id.payFor_friends_list);
        this.adapter =  new PayForFriendsAdapter(this);
        localListView.addHeaderView(this.headView);
        localListView.setAdapter(this.adapter);
    }

    private void showCheckPersonInfo()
    {
        String str1 = "";
        for (int i = 0; i < this.checkStateArray.size(); i++)
        {
            String str2 = this.checkStateArray.valueAt(i);
            if (StringUtils.isNotBlank(str2))
            {
                str1 = str1 + str2 + ", ";
            }
        }
        if (StringUtils.isNotBlank(str1))
        {
            finishWithName(str1);
            return;
        }
        Utility.showNoticeDialog(this, "请选择买单的好友.");
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_payfor_someday_layout;
    }

    protected void initView()
    {
        this.checkStateArray = new SparseArray<String>();
        initTop();
        initListHead();
        setupData();
    }

    private class PayForFriendsAdapter extends BaseAdapter
    {
        private LayoutInflater mInflater;

        public PayForFriendsAdapter(Context arg2)
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

        public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            PayForFriendsActivity.PayForViewHolder localPayForViewHolder2;
            LinearLayout.LayoutParams localLayoutParams;
            if (localView == null)
            {
                localPayForViewHolder2 = new PayForFriendsActivity.PayForViewHolder();
                localView = this.mInflater.inflate(R.layout.custom_split_check_listitem, paramViewGroup, false);

                localPayForViewHolder2.friendImage = (ImageView)localView.findViewById(R.id.split_check_item_image);
                localPayForViewHolder2.friendNickName = (TextView)localView.findViewById(R.id.split_check_item_name);
                localPayForViewHolder2.checkBox = (CheckBox)localView.findViewById(R.id.split_check_item_checkbox);
                localPayForViewHolder2.line = (TextView)localView.findViewById(R.id.split_check_item_line);

                localView.setTag(localPayForViewHolder2);

            }else {
                localPayForViewHolder2 = (PayForFriendsActivity.PayForViewHolder)localView.getTag();
            }

            localPayForViewHolder2.checkBox.setChecked(checkboxState[paramInt]);
            CheckBox localCheckBox = localPayForViewHolder2.checkBox;

            CompoundButton.OnCheckedChangeListener local1 = new CompoundButton.OnCheckedChangeListener()
            {
                public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
                {
                    PayForFriendsActivity.this.checkboxState[paramInt] = paramAnonymousBoolean;
                    if (paramAnonymousBoolean)
                    {
                        PayForFriendsActivity.this.headCheckbox.setChecked(false);
                        PayForFriendsActivity localPayForFriendsActivity = PayForFriendsActivity.this;
                        Utility.showNoticeDialog(localPayForFriendsActivity, "p=" + paramInt + ",在本群账中的历史及将来的欠款都由我来承担，直至关闭此功能");
                        checkStateArray.put(paramInt, "p=" + paramInt);
                        return;
                    }
                    PayForFriendsActivity.this.checkStateArray.put(paramInt, " ");
                }
            };
            localCheckBox.setOnCheckedChangeListener(local1);
            localLayoutParams = new LinearLayout.LayoutParams(-1, 1);
//            if (paramInt == -1 + getCount()){
//                PayForFriendsActivity.PayForViewHolder.access$700(localPayForViewHolder2).setLayoutParams(localLayoutParams);
//                localLayoutParams.setMargins((int)TypedValue.applyDimension(1, 12.0F, PayForFriendsActivity.this.getResources().getDisplayMetrics()),
//                        (int)TypedValue.applyDimension(1, 8.0F, PayForFriendsActivity.this.getResources().getDisplayMetrics()), 0, 0);
//            }
            localLayoutParams.setMargins(0, (int)TypedValue.applyDimension(1, 8.0F, PayForFriendsActivity.this.getResources().getDisplayMetrics()), 0, 0);

            return localView;
        }
    }

    class PayForViewHolder
    {
        private CheckBox checkBox;
        private ImageView friendImage;
        private TextView friendNickName;
        private TextView line;

    }
}
