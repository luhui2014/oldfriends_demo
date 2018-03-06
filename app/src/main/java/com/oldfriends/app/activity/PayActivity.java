package com.oldfriends.app.activity;

/**
 * Created by Administrator on 2016/2/24.
 */
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.oldfriends.app.R;
import com.oldfriends.app.util.KeyboardUtil;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.util.Utility;

public class PayActivity extends BaseActivity
{
    EditText editText;

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.pay_top_layout);
        View localView1 = localRelativeLayout.findViewById(R.id.common_font_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(PayActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        View localView2 = localRelativeLayout.findViewById(R.id.common_font_right_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                String str = PayActivity.this.editText.getText().toString();
                if (StringUtils.isBlank(str))
                    str = "0";
                if (Integer.parseInt(str) > 0)
                {
                    PayActivity localPayActivity = PayActivity.this;
                    Toast.makeText(localPayActivity, "支付的金额是=" + str, Toast.LENGTH_LONG).show();
                    Intent localIntent = new Intent(PayActivity.this, PayWaysActivity.class);
                    Utility.startActivitytranslate(PayActivity.this, localIntent);
                    return;
                }
                Toast.makeText(PayActivity.this, "请输入支付金额....", Toast.LENGTH_LONG).show();
            }
        };
        localView2.setOnClickListener(local2);
        ((TextView)localRelativeLayout.findViewById(R.id.common_right_font)).setText("继续");
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_pay_layout;
    }

    protected void initView()
    {
        initTop();
        this.editText = ((EditText)findViewById(R.id.textfield_et_label));
        KeyboardUtil localKeyboardUtil = new KeyboardUtil(this, this, this.editText);
        localKeyboardUtil.showKeyboard();
    }
}
