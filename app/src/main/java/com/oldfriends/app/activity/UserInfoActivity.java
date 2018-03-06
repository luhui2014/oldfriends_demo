package com.oldfriends.app.activity;

/**
 * lh on 2016/2/25.
 */

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.CustomHeadChooseDialog;
import com.oldfriends.app.view.CustomSexChooseDialog;

public class UserInfoActivity extends BaseActivity
{
    private static final int INTENT_TO_CHANGE_NAME_REQUEST_CODE = 1;
    private static final int INTENT_TO_CHANGE_PHONE_REQUEST_CODE = 2;
    private CustomHeadChooseDialog headChooseDialog;
    private ImageView headImage;
    private CustomSexChooseDialog sexChooseDialog;
    private TextView userName;
    private TextView userPhone;
    private ImageView userQRcode;
    private ImageView userSexImage;
    private TextView userSexText;

    private void initContent()
    {
        this.headImage = ((ImageView)findViewById(R.id.user_info_headImage));
        View localView1 = findViewById(R.id.user_info_headImage_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                UserInfoActivity.this.showChooseHeadDialog();
            }
        };
        localView1.setOnClickListener(local2);
        this.userName = ((TextView)findViewById(R.id.user_info_userName));
        View localView2 = findViewById(R.id.user_info_userName_layout);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(UserInfoActivity.this, FriendRemarkActivity.class);
                localIntent.putExtra("RENAME_NAME_STATE", 3);
                UserInfoActivity.this.startActivityForResult(localIntent, 1);
                UserInfoActivity.this.overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView2.setOnClickListener(local3);
        this.userQRcode = ((ImageView)findViewById(R.id.user_info_userQR));
        ImageView localImageView = this.userQRcode;
        View.OnClickListener local4 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                UserInfoActivity.this.intent2QRActivity();
            }
        };
        localImageView.setOnClickListener(local4);
        View localView3 = findViewById(R.id.user_info_userQR_layout);
        View.OnClickListener local5 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                UserInfoActivity.this.intent2QRActivity();
            }
        };
        localView3.setOnClickListener(local5);
        this.userPhone = ((TextView)findViewById(R.id.user_info_phoneNumber));
        View localView4 = findViewById(R.id.user_info_userPhone_layout);
        View.OnClickListener local6 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(UserInfoActivity.this, UserChangePhoneActivity.class);
                UserInfoActivity.this.startActivityForResult(localIntent, 2);
                UserInfoActivity.this.overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView4.setOnClickListener(local6);
        this.userSexText = ((TextView)findViewById(R.id.user_info_sex_text));
        this.userSexImage = ((ImageView)findViewById(R.id.user_info_sex_image));
        View localView5 = findViewById(R.id.user_info_userSex_layout);
        View.OnClickListener local7 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                UserInfoActivity.this.showSexChooseDialog();
            }
        };
        localView5.setOnClickListener(local7);
        View localView6 = findViewById(R.id.user_info_exit_btn);
        View.OnClickListener local8 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Toast.makeText(UserInfoActivity.this, "用户点击了退出的按钮...", Toast.LENGTH_LONG).show();
            }
        };
        localView6.setOnClickListener(local8);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.user_info_top_layout);
        localRelativeLayout.findViewById(R.id.common_left_btn).setBackgroundResource(R.drawable.back_icon);
        View localView = localRelativeLayout.findViewById(R.id.common_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(UserInfoActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_title)).setText("个人信息");
    }

    private void intent2QRActivity()
    {
        Intent localIntent = new Intent(this, QRcodeActivity.class);
        localIntent.putExtra("INTENT_QRCODE_STATE", 3);
        startActivityForResult(localIntent, 1);
        overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
    }

    private void showChooseHeadDialog()
    {
        CustomHeadChooseDialog.UserChooseHeadListener local9 = new CustomHeadChooseDialog.UserChooseHeadListener()
        {
            public void cameraClick()
            {
            }

            public void photoClick()
            {
            }
        };
        this.headChooseDialog = new CustomHeadChooseDialog(this, R.style.MyDialog, local9);
        this.headChooseDialog.show();
    }

    private void showSexChooseDialog()
    {
        CustomSexChooseDialog.SexClickListener local10 = new CustomSexChooseDialog.SexClickListener()
        {
            public void onManClick()
            {
                UserInfoActivity.this.userSexText.setText("男");
                UserInfoActivity.this.userSexImage.setBackgroundResource(R.drawable.register_man_check);
                UserInfoActivity.this.sexChooseDialog.dismiss();
            }

            public void onWomanClick()
            {
                UserInfoActivity.this.userSexText.setText("女");
                UserInfoActivity.this.userSexImage.setBackgroundResource(R.drawable.register_woman_check);
                UserInfoActivity.this.sexChooseDialog.dismiss();
            }
        };
        this.sexChooseDialog = new CustomSexChooseDialog(this, R.style.MyDialog, local10);
        this.sexChooseDialog.show();
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_user_info_layout;
    }

    protected void initView()
    {
        initTop();
        initContent();
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        if ((paramInt2 == -1) && (paramIntent != null))
        {
            if (paramInt1 == 1) {
                this.userName.setText(paramIntent.getStringExtra("FRIEND_RENAME_NAME"));
            }
        }
    }
}
