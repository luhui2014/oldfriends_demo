package com.oldfriends.app.activity;

/**
 * Created by Administrator on 2016/2/24.
 */
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;

public class ForgetPasswordActivity extends BaseActivity
{
    private Handler mHandler;
    private EditText phoneNumberEdit;

    public ForgetPasswordActivity()
    {
        Handler local4 = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                switch (paramAnonymousMessage.what)
                {
                    default:
                        return;
                    case 0:
                }
                ((InputMethodManager)ForgetPasswordActivity.this.phoneNumberEdit.getContext().getSystemService("input_method")).showSoftInput(ForgetPasswordActivity.this.phoneNumberEdit, 0);
            }
        };
        this.mHandler = local4;
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.forget_top_layout);
        localRelativeLayout.findViewById(R.id.common_left_btn).setBackgroundResource(R.drawable.back_icon);
        View localView1 = localRelativeLayout.findViewById(R.id.common_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(ForgetPasswordActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_title)).setText("找回密码");
        TextView localTextView = (TextView)localRelativeLayout.findViewById(R.id.common_right_font);
        localTextView.setText(R.string.common_submit);
        localTextView.setTextColor(getResources().getColor(R.color.common_grey_click_selector));
        View localView2 = localRelativeLayout.findViewById(R.id.common_right_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
            }
        };
        localView2.setOnClickListener(local2);
        View localView3 = findViewById(R.id.forget_phoneNumber_country_layout);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(ForgetPasswordActivity.this, ChooseCountryActivity.class);
                Utility.startActivitytranslate(ForgetPasswordActivity.this, localIntent);
            }
        };
        localView3.setOnClickListener(local3);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_forget_password_layout;
    }

    protected void initView()
    {
        initTop();
        this.phoneNumberEdit = ((EditText)findViewById(R.id.forget_phoneNumber_edit));
        this.mHandler.sendEmptyMessageDelayed(0, 100L);
    }
}
