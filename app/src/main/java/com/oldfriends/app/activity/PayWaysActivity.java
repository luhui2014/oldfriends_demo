package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;

public class PayWaysActivity extends BaseActivity
{
    private void initContent()
    {
        View localView1 = findViewById(R.id.pay_way_offLine_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
            }
        };
        localView1.setOnClickListener(local2);
        View localView2 = findViewById(R.id.pay_way_weixin_layout);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
            }
        };
        localView2.setOnClickListener(local3);
        View localView3 = findViewById(R.id.pay_way_qq_layout);
        View.OnClickListener local4 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
            }
        };
        localView3.setOnClickListener(local4);
        View localView4 = findViewById(R.id.pay_way_zfb_layout);
        View.OnClickListener local5 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
            }
        };
        localView4.setOnClickListener(local5);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.pay_way_top_layout);
        View localView = localRelativeLayout.findViewById(R.id.common_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(PayWaysActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        localRelativeLayout.findViewById(R.id.common_left_btn).setBackgroundResource(R.drawable.back_icon);
        ((TextView)localRelativeLayout.findViewById(R.id.common_title)).setText("选择支付方式");
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_payways_layout;
    }

    protected void initView()
    {
        initTop();
        initContent();
    }
}
