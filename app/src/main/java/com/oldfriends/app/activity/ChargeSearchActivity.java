package com.oldfriends.app.activity;

/**
 *lh on 2016/2/24.
 */
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.CustomChargeChooseDialog;

public class ChargeSearchActivity extends BaseActivity
{
    private CustomChargeChooseDialog chooseDialog;
    private TextView timeText;

    private void initContent()
    {
        View localView = findViewById(R.id.charge_search_export_layout);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(ChargeSearchActivity.this, ExportBillActivity.class);
                Utility.startActivitytranslate(ChargeSearchActivity.this, localIntent);
            }
        };
        localView.setOnClickListener(local3);
    }

    private void initTop()
    {
        View localView1 = findViewById(R.id.charge_search_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(ChargeSearchActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        this.timeText = ((TextView)findViewById(R.id.charge_search_time_font));
        View localView2 = findViewById(R.id.charge_search_time);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                ChargeSearchActivity.this.showChooseDialog();
            }
        };
        localView2.setOnClickListener(local2);
    }

    private void showChooseDialog()
    {
        CustomChargeChooseDialog.ChargeSpendTimeChooseListener local4 = new CustomChargeChooseDialog.ChargeSpendTimeChooseListener()
        {
            public void allTimeClick()
            {
                ChargeSearchActivity.this.timeText.setText("全部时间");
            }

            public void monthClick()
            {
                ChargeSearchActivity.this.timeText.setText("本月");
            }

            public void seasonClick()
            {
                ChargeSearchActivity.this.timeText.setText("本季度");
            }

            public void yearClick()
            {
                ChargeSearchActivity.this.timeText.setText("本年");
            }
        };
        this.chooseDialog = new CustomChargeChooseDialog(this, R.style.MyDialog, local4);
        this.chooseDialog.show();
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_charge_search_layout;
    }

    protected void initView()
    {
        initTop();
        initContent();
    }
}
