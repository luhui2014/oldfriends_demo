package com.oldfriends.app;

/**
 * Created by Administrator on 2016/2/24.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.oldfriends.app.activity.ForgetPasswordActivity;
import com.oldfriends.app.activity.RegisterActivity;
import com.oldfriends.app.model.NetworkException;
import com.oldfriends.app.model.User;
import com.oldfriends.app.network.UserRepository;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.util.Utility;

public class AppStart extends Activity
{
    private static final int INTENT_REGISTER_REQUESTCODE = 1;
    private static final String TAG = "AppStart";
    public static final String USER_LOGIN_PHONE_NUMBER = "user_login_phone_number";
    public static final String USER_REGISTER_PHONE_NUMBER = "user_register_phone_number";
    private String errorMessage;
    private LinearLayout loginEditLayout;
    private LinearLayout loginLayout;
    private LinearLayout loginTextLayout;
    private Handler mHandler;
    private LinearLayout otherWaysLayout;
    private EditText passwordEdit;
    private EditText phoneNumberEdit;
    private ProgressDialog progressDialog;

    public AppStart()
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
                        AppStart.this.loginLayout.setVisibility(View.VISIBLE);
                        AppStart.this.setTranslateAnim(AppStart.this.loginEditLayout, 500.0F, 0.0F, 0.0F, 0.0F);
                        AppStart.this.setAlphaAnim(AppStart.this.loginTextLayout);
                        AppStart.this.setTranslateAnim(AppStart.this.otherWaysLayout, 0.0F, 0.0F, 150.0F, 0.0F);
                        return;
                    case 1:
                }
                AppStart.this.init2MainActivity();
            }
        };
        this.mHandler = local4;
    }

    private String getUserToken()
    {
        return PreferenceManager.getDefaultSharedPreferences(this).getString("ACCESS_TOKEN", "");
    }

    private void init2MainActivity()
    {
        Intent localIntent = new Intent(this, MainActivity.class);
        startActivity(localIntent);
        finish();
    }

    private void initView()
    {
        ProgressDialog localProgressDialog = new ProgressDialog(this);
        this.progressDialog = localProgressDialog;
        this.phoneNumberEdit = ((EditText)findViewById(R.id.app_star_phoneNumber));
        this.passwordEdit = ((EditText)findViewById(R.id.app_star_password));
        this.loginLayout = ((LinearLayout)findViewById(R.id.app_start_login_layout));
        this.loginLayout.setVisibility(View.GONE);
        this.otherWaysLayout = ((LinearLayout)findViewById(R.id.app_start_otherWays_login_layout));
        this.loginTextLayout = ((LinearLayout)findViewById(R.id.app_start_loginRegister_layout));
        this.loginEditLayout = ((LinearLayout)findViewById(R.id.app_start_loginForget_layout));
        View localView1 = findViewById(R.id.start_forget_icon);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(AppStart.this, ForgetPasswordActivity.class);
                Utility.startActivitytranslate(AppStart.this, localIntent);
            }
        };
        localView1.setOnClickListener(local1);
        View localView2 = findViewById(R.id.start_login_btn);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (Utility.checkNetworkStatus(AppStart.this))
                {
                    String str1 = AppStart.this.phoneNumberEdit.getText().toString();
                    String str2 = AppStart.this.passwordEdit.getText().toString();
                    if ((StringUtils.isNotBlank(str1)) && (StringUtils.isNotBlank(str2)))
                    {
                        AppStart.LoginLoader localLoginLoader = new AppStart.LoginLoader(str1, str2);
                        localLoginLoader.execute();
                        return;
                    }
                    Toast.makeText(AppStart.this, "请填写完整信息......", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(AppStart.this, "请检查手机网络...", Toast.LENGTH_LONG).show();
            }
        };
        localView2.setOnClickListener(local2);
        View localView3 = findViewById(R.id.start_regist_btn);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(AppStart.this, RegisterActivity.class);
                String str = AppStart.this.phoneNumberEdit.getText().toString();
                if (StringUtils.isNotBlank(str))
                    localIntent.putExtra(USER_LOGIN_PHONE_NUMBER, str);
                startActivityForResult(localIntent, INTENT_REGISTER_REQUESTCODE);
                overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView3.setOnClickListener(local3);
        if (StringUtils.isBlank(getUserToken()))
        {
            this.mHandler.sendEmptyMessageDelayed(0, 1000L);
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(1, 2000L);
    }

    private void setAlphaAnim(View paramView)
    {
        AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.1F, 1.0F);
        localAlphaAnimation.setDuration(1000L);
        localAlphaAnimation.setFillAfter(true);
        paramView.startAnimation(localAlphaAnimation);
    }

    private void setTranslateAnim(View paramView, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
        TranslateAnimation localTranslateAnimation = new TranslateAnimation(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
        localTranslateAnimation.setDuration(500L);
        paramView.startAnimation(localTranslateAnimation);
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        if ((paramInt2 == -1) && (paramIntent != null) && (paramInt1 == 1))
            this.phoneNumberEdit.setText(paramIntent.getStringExtra(USER_REGISTER_PHONE_NUMBER));
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_app_start_layout);
        initView();
    }

    private class LoginLoader extends AsyncTask<Void, Void, User>
    {
        private String mPassword;
        private String mUserName;

        public LoginLoader(String paramString1, String arg3)
        {
            this.mUserName = paramString1;
            this.mPassword = arg3;
        }

        protected User doInBackground(Void[] paramArrayOfVoid)
        {
            try
            {
                return  new UserRepository().login(this.mUserName, this.mPassword);
            }
            catch (NetworkException localNetworkException)
            {
                Log.d(TAG, "LoginLoader error:", localNetworkException);
            }
            return null;
        }

        protected void onPostExecute(User paramUser)
        {
            if ((AppStart.this.progressDialog != null) && (AppStart.this.progressDialog.isShowing()))
                AppStart.this.progressDialog.dismiss();
            if (paramUser != null)
            {
                String str1 = paramUser.getServer();
                String str2 = paramUser.getNimserver();
                Log.d(TAG, "loginLoader : accesstoken=" + str1 + ",nimserver=" + str2);
                PreferenceManager.getDefaultSharedPreferences(AppStart.this).edit().putString("ACCESS_TOKEN", str1).apply();
                PreferenceManager.getDefaultSharedPreferences(AppStart.this).edit().putString("USER_NAME", this.mUserName).apply();
                AppStart.this.init2MainActivity();
                return;
            }
            Toast.makeText(AppStart.this, "登录失败！", Toast.LENGTH_LONG).show();
        }

        protected void onPreExecute()
        {
            AppStart.this.progressDialog.setMessage("加载中,请稍后......");
            AppStart.this.progressDialog.show();
        }
    }
}