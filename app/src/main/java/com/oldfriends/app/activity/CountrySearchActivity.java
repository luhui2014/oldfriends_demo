package com.oldfriends.app.activity;

/**
 * Created by Administrator on 2016/2/24.
 */
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import com.oldfriends.app.R;
import com.oldfriends.app.view.ClearEditText;

public class CountrySearchActivity extends BaseActivity
{
    ClearEditText editText;
    private Handler mHandler;

    public CountrySearchActivity()
    {
        Handler local2 = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                switch (paramAnonymousMessage.what)
                {
                    default:
                        return;
                    case 0:
                }
                ((InputMethodManager)CountrySearchActivity.this.editText.getContext().getSystemService("input_method")).showSoftInput(CountrySearchActivity.this.editText, 0);
            }
        };
        this.mHandler = local2;
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_country_search_layout;
    }

    protected void initView()
    {
        this.editText = ((ClearEditText)findViewById(R.id.country_search_edit));
        this.mHandler.sendEmptyMessageDelayed(0, 100L);
        View localView = findViewById(R.id.country_search_cancel_icon);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                CountrySearchActivity.this.finish();
            }
        };
        localView.setOnClickListener(local1);
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {
        if (paramInt == 4)
        {
            finish();
            return false;
        }
        return true;
    }
}
