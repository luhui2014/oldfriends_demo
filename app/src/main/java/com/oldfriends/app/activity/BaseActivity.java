package com.oldfriends.app.activity;

/**
 * Created by Administrator on 2016/2/23.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import com.oldfriends.app.R;

public abstract class BaseActivity extends Activity
{
    protected int getLayoutResId()
    {
        return R.layout.activity_main;
    }

    protected abstract void initView();

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(getLayoutResId());
        initView();
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {
        if (paramInt == 4)
        {
            finish();
            overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_left_out);
            return false;
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }
}
