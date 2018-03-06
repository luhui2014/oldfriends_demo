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
import com.oldfriends.app.view.CustomHeadChooseDialog;
import com.sevenheaven.iosswitch.ShSwitchView;

public class ChargeSettingActivity extends BaseActivity
{
    private static final int CHARGE_SETTING_CURRENCY_CHOOSE_REQUEST = 3;
    private static final int CHARGE_SETTING_NAME_CHANGE_REQUESTCODE = 1;
    private static final int CHARGE_SETTING_NAME_USER_REQUESTCODE = 2;
    private static final int CHARGE_SETTING_PAYFOR_CHOOSE_REQUEST = 4;
    private TextView chargeGroupName;
    private TextView chargeUserNickName;
    private TextView moneyTypeText;
    private TextView payForFriendsText;

    private void showChooseHeadDialog()
    {
        CustomHeadChooseDialog.UserChooseHeadListener local11 = new CustomHeadChooseDialog.UserChooseHeadListener()
        {
            public void cameraClick()
            {
            }

            public void photoClick()
            {
            }
        };
        CustomHeadChooseDialog localCustomHeadChooseDialog = new CustomHeadChooseDialog(this, R.style.MyDialog, local11);
        localCustomHeadChooseDialog.show();
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_charge_setting_layout;
    }

    protected void initView()
    {
        View localView1 = findViewById(R.id.charge_setting_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(ChargeSettingActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        GridView localGridView = (GridView)findViewById(R.id.charge_setting_member);
        MemberGridViewAdapter localMemberGridViewAdapter = new MemberGridViewAdapter(this);
        localGridView.setAdapter(localMemberGridViewAdapter);
        AdapterView.OnItemClickListener local2 = new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                if (paramAnonymousInt == -1 + paramAnonymousAdapterView.getAdapter().getCount())
                {
                    Toast.makeText(ChargeSettingActivity.this, "点击了增加好友的按钮....", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(ChargeSettingActivity.this, "查看好友详情....", Toast.LENGTH_LONG).show();
            }
        };
        localGridView.setOnItemClickListener(local2);
        View localView2 = findViewById(R.id.charge_setting_name_layout);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(ChargeSettingActivity.this, FriendRemarkActivity.class);
                localIntent.putExtra("RENAME_NAME_STATE", 2);
                ChargeSettingActivity.this.startActivityForResult(localIntent, CHARGE_SETTING_NAME_CHANGE_REQUESTCODE);
                ChargeSettingActivity.this.overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView2.setOnClickListener(local3);
        this.chargeGroupName = ((TextView)findViewById(R.id.charge_setting_group_name));
        View localView3 = findViewById(R.id.charge_setting_headImage_layout);
        View.OnClickListener local4 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                ChargeSettingActivity.this.showChooseHeadDialog();
            }
        };
        localView3.setOnClickListener(local4);
        ((ShSwitchView)findViewById(R.id.switch_view_group_member)).setOn(true);
        View localView4 = findViewById(R.id.charge_setting_exit_btn);
        View.OnClickListener local5 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Toast.makeText(ChargeSettingActivity.this, "退出群账....", Toast.LENGTH_LONG).show();
            }
        };
        localView4.setOnClickListener(local5);
        this.moneyTypeText = ((TextView)findViewById(R.id.charge_setting_moneyType_name));
        View localView5 = findViewById(R.id.charge_setting_currency_layout);
        View.OnClickListener local6 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(ChargeSettingActivity.this, ChooseCurrencyActivity.class);
                localIntent.putExtra("CHOOSE_CURRENCY_TYPE", 1);
                startActivityForResult(localIntent, CHARGE_SETTING_CURRENCY_CHOOSE_REQUEST);
                overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView5.setOnClickListener(local6);
        View localView6 = findViewById(R.id.charge_setting_QRcode_layout);
        View.OnClickListener local7 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(ChargeSettingActivity.this, QRcodeActivity.class);
                localIntent.putExtra("INTENT_QRCODE_STATE", 2);
                Utility.startActivitytranslate(ChargeSettingActivity.this, localIntent);
            }
        };
        localView6.setOnClickListener(local7);
        View localView7 = findViewById(R.id.charge_setting_invite_layout);
        View.OnClickListener local8 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(ChargeSettingActivity.this, InviteFriendsActivity.class);
                Utility.startActivitytranslate(ChargeSettingActivity.this, localIntent);
            }
        };
        localView7.setOnClickListener(local8);
        this.payForFriendsText = ((TextView)findViewById(R.id.charge_setting_payfor_name));
        View localView8 = findViewById(R.id.charge_setting_payFor_friend);
        View.OnClickListener local9 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(ChargeSettingActivity.this, PayForFriendsActivity.class);
                ChargeSettingActivity.this.startActivityForResult(localIntent, CHARGE_SETTING_PAYFOR_CHOOSE_REQUEST);
                overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView8.setOnClickListener(local9);
        this.chargeUserNickName = ((TextView)findViewById(R.id.charge_setting_myname));
        View localView9 = findViewById(R.id.charge_setting_myName_layout);
        View.OnClickListener local10 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(ChargeSettingActivity.this, FriendRemarkActivity.class);
                localIntent.putExtra("RENAME_NAME_STATE", 3);
                ChargeSettingActivity.this.startActivityForResult(localIntent, CHARGE_SETTING_NAME_USER_REQUESTCODE);
                overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView9.setOnClickListener(local10);
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        if ((-1 == paramInt2) && (paramIntent != null))
        {
            if(paramInt1 == 1){
                this.chargeGroupName.setText(paramIntent.getStringExtra("FRIEND_RENAME_NAME"));
            }else if(paramInt1 == 2){
                this.chargeUserNickName.setText(paramIntent.getStringExtra("FRIEND_RENAME_NAME"));
            }else if(paramInt1 == 3) {
                this.moneyTypeText.setText(paramIntent.getStringExtra("CURRENCY_MONEY_TYPE"));
            }else if(paramInt1 == 4) {
                this.payForFriendsText.setText(paramIntent.getStringExtra("CHOOSE_PAYFOR_PERSON_NAME"));
            }
        }
    }

    private class MemberGridViewAdapter extends BaseAdapter
    {
        private LayoutInflater mInflater;

        public MemberGridViewAdapter(Context arg2)
        {
            this.mInflater = LayoutInflater.from(arg2);
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
            return 0L;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            ChargeSettingActivity.ViewHolder localViewHolder1;
            if (localView == null)
            {
                localView = this.mInflater.inflate(R.layout.custom_charge_setting_griditem_layout, paramViewGroup, false);
                localViewHolder1 = new ChargeSettingActivity.ViewHolder(localView);
                localView.setTag(localViewHolder1);
                localViewHolder1.headImage.setBackgroundResource(R.drawable.group_charge_head_add);
                localViewHolder1.memberName.setText("");
            }else {
                localViewHolder1 = (ChargeSettingActivity.ViewHolder)localView.getTag();
            }

            if(paramInt == -1 + getCount()){
                localViewHolder1.headImage.setBackgroundResource(R.drawable.group_charge_head_default);
                TextView localTextView = localViewHolder1.memberName;
                localTextView.setText("cxw " + paramInt);
            }

            return localView;
        }
    }

    private class ViewHolder
    {
        ImageView headImage;
        TextView memberName;

        public ViewHolder(View arg2)
        {
            this.headImage = ((ImageView)arg2.findViewById(R.id.charge_setting_girdItem_image));
            this.memberName = ((TextView)arg2.findViewById(R.id.charge_setting_girdItem_name));
        }
    }
}
