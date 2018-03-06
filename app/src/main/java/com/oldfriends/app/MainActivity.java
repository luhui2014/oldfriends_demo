package com.oldfriends.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.oldfriends.app.activity.BillEditActivity;

public class MainActivity extends FragmentActivity
        implements View.OnClickListener
{
    public static final String MIAN_INTENT_EDIT_STATE = "main_intent_edit";
    private static final String TAG = "MainActivity";
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageView homeChargeBtn;
    private ImageView homeFriendsBtn;
    private ImageView homeRecommendBtn;
    private ImageView homeUserBtn;
    private long mExitTime;
    private Fragment[] mFragments;

    private void initView()
    {
        this.mFragments = new Fragment[4];
        this.fragmentManager = getSupportFragmentManager();
        this.mFragments[0] = this.fragmentManager.findFragmentById(R.id.fragment_charge);
        this.mFragments[1] = this.fragmentManager.findFragmentById(R.id.fragment_friends);
        this.mFragments[2] = this.fragmentManager.findFragmentById(R.id.fragment_find);
        this.mFragments[3] = this.fragmentManager.findFragmentById(R.id.fragment_user);
        this.fragmentTransaction = this.fragmentManager.beginTransaction().
                hide(this.mFragments[0]).hide(this.mFragments[1]).hide(this.mFragments[2]).hide(this.mFragments[3]);
        this.fragmentTransaction.show(this.mFragments[0]).commit();
        setFragmentIndicator();
    }

    private void setDefaultRadioBtnBackground()
    {
        this.homeChargeBtn.setSelected(false);
        this.homeFriendsBtn.setSelected(false);
        this.homeRecommendBtn.setSelected(false);
        this.homeUserBtn.setSelected(false);
    }

    private void setFragmentIndicator()
    {
        this.homeChargeBtn = ((ImageView)findViewById(R.id.home_nav_charge_btn));
        this.homeFriendsBtn = ((ImageView)findViewById(R.id.home_nav_friends_btn));
        this.homeRecommendBtn = ((ImageView)findViewById(R.id.home_nav_recommend_btn));
        this.homeUserBtn = ((ImageView)findViewById(R.id.home_nav_user_btn));
        this.homeChargeBtn.setSelected(true);
        findViewById(R.id.home_nav_charge_layout).setOnClickListener(this);
        findViewById(R.id.home_nav_friends_layout).setOnClickListener(this);
        findViewById(R.id.home_nav_recommend_layout).setOnClickListener(this);
        findViewById(R.id.home_nav_user_layout).setOnClickListener(this);
        View localView = findViewById(R.id.home_nav_menu_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Log.d("MainActivity", "menu onClick  aa ");
                Intent localIntent = new Intent(MainActivity.this, BillEditActivity.class);
                localIntent.putExtra(MIAN_INTENT_EDIT_STATE, true);
                startActivity(localIntent);
                overridePendingTransition(R.anim.activity_down_in, R.anim.activity_alpha_out);
            }
        };
        localView.setOnClickListener(local1);
    }

    @SuppressLint({"NewApi"})
    public void onClick(View paramView)
    {
        Log.d("MainActivity", "onClick v.id=");
        setDefaultRadioBtnBackground();
        this.fragmentTransaction = this.fragmentManager.beginTransaction().
                hide(this.mFragments[0]).hide(this.mFragments[1]).hide(this.mFragments[2]).hide(this.mFragments[3]);
        switch (paramView.getId())
        {
            default:
                break;
            case R.id.home_nav_charge_layout:
                this.homeChargeBtn.setSelected(true);
                this.fragmentTransaction.show(this.mFragments[0]).commit();
                break;
            case R.id.home_nav_friends_layout:
                this.homeFriendsBtn.setSelected(true);
                this.fragmentTransaction.show(this.mFragments[1]).commit();
                break;
            case R.id.home_nav_recommend_layout:
                this.homeRecommendBtn.setSelected(true);
                this.fragmentTransaction.show(this.mFragments[2]).commit();
                break;
            case R.id.home_nav_user_layout:
                this.homeUserBtn.setSelected(true);
                this.fragmentTransaction.show(this.mFragments[3]).commit();
                break;
        }
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(-1);
            }
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }
}
