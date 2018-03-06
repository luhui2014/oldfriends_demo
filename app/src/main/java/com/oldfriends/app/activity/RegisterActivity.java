package com.oldfriends.app.activity;

/**
 * Created by Administrator on 2016/2/24.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.model.NetworkException;
import com.oldfriends.app.network.UserRepository;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.util.Validator;
import com.oldfriends.app.view.CustomHeadChooseDialog;

public class RegisterActivity extends BaseActivity {
    private static final int REGISTER_INTENT_TO_CHOOSECUNTRY_REQUESTCODE = 1;
    private static final String TAG = "RegisterActivity";
    private TextView countryText;
    private CustomHeadChooseDialog dialog;
    private TextView getSmsCodeBtn;
    private Handler mHandler;
    private TextView manText;
    private EditText passwordEdit;
    private EditText phoneNumberEdit;
    private ProgressDialog progressDialog;
    private EditText smsCodeEdit;
    private CountDownTimer timer;
    private EditText userNameEdit;
    private TextView womanText;

    public RegisterActivity() {
        Handler local11 = new Handler() {
            public void handleMessage(Message paramAnonymousMessage) {
                switch (paramAnonymousMessage.what) {
                    default:
                        return;
                    case 0:
                }
                ((InputMethodManager) RegisterActivity.this.userNameEdit.getContext().getSystemService("input_method")).showSoftInput(RegisterActivity.this.userNameEdit, 0);
            }
        };
        this.mHandler = local11;
    }

    private void initContent() {
        this.phoneNumberEdit = ((EditText) findViewById(R.id.register_phoneNumber_edit));
        this.smsCodeEdit = ((EditText) findViewById(R.id.register_verification_edit));
        this.passwordEdit = ((EditText) findViewById(R.id.register_password_edit));
        ProgressDialog localProgressDialog = new ProgressDialog(this);
        this.progressDialog = localProgressDialog;
        String str = getIntent().getStringExtra("user_login_phone_number");
        if (StringUtils.isNotBlank(str))
            this.phoneNumberEdit.setText(str);
        this.getSmsCodeBtn = ((TextView) findViewById(R.id.register_phoneNumber_message));
        View localView1 = findViewById(R.id.register_choose_userHead_layout);
        View.OnClickListener local3 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                RegisterActivity.this.showChooseHeadDialog();
            }
        };
        localView1.setOnClickListener(local3);
        this.userNameEdit = ((EditText) findViewById(R.id.register_userName_edit));
        this.manText = ((TextView) findViewById(R.id.register_userSex_manText));
        this.womanText = ((TextView) findViewById(R.id.register_userSex_womanText));
        final CheckBox localCheckBox1 = (CheckBox) findViewById(R.id.register_userSex_manCheckBox);
        final CheckBox localCheckBox2 = (CheckBox) findViewById(R.id.register_userSex_womanCheckBox);
        CompoundButton.OnCheckedChangeListener local4 = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
                if (paramAnonymousBoolean) {
                    localCheckBox2.setChecked(false);
                    RegisterActivity.this.womanText.setSelected(false);
                }
                RegisterActivity.this.manText.setSelected(paramAnonymousBoolean);
            }
        };
        localCheckBox1.setOnCheckedChangeListener(local4);
        CompoundButton.OnCheckedChangeListener local5 = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
                if (paramAnonymousBoolean) {
                    localCheckBox1.setChecked(false);
                    RegisterActivity.this.manText.setSelected(false);
                }
                RegisterActivity.this.womanText.setSelected(paramAnonymousBoolean);
            }
        };
        localCheckBox2.setOnCheckedChangeListener(local5);
        CountDownTimer local6 = new CountDownTimer(90000L, 1000L) {
            public void onFinish() {
                RegisterActivity.this.getSmsCodeBtn.setEnabled(true);
                RegisterActivity.this.getSmsCodeBtn.setText("获取验证码");
            }

            public void onTick(long paramAnonymousLong) {
                RegisterActivity.this.getSmsCodeBtn.setEnabled(false);
                TextView localTextView = RegisterActivity.this.getSmsCodeBtn;
                localTextView.setText(paramAnonymousLong / 1000L + "秒后可重发");
            }
        };
        this.timer = local6;
        TextView localTextView = this.getSmsCodeBtn;
        View.OnClickListener local7 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (Utility.checkNetworkStatus(RegisterActivity.this)) {
                    String str = RegisterActivity.this.phoneNumberEdit.getText().toString();
                    if ((StringUtils.isNotBlank(str)) && (Validator.isMobile(str))) {
                        RegisterActivity.this.timer.start();
                        RegisterActivity.SendVerificationCode localSendVerificationCode = new RegisterActivity.SendVerificationCode(str, "1");
                        localSendVerificationCode.execute();
                        return;
                    }
                    Toast.makeText(RegisterActivity.this, "请填写正确的手机号!", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "请检查手机网络...", Toast.LENGTH_LONG).show();
            }
        };
        localTextView.setOnClickListener(local7);
        this.countryText = ((TextView) findViewById(R.id.register_phoneNumber_country));
        View localView2 = findViewById(R.id.register_phoneNumber_country_layout);
        View.OnClickListener local8 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent localIntent = new Intent(RegisterActivity.this, ChooseCountryActivity.class);
                RegisterActivity.this.startActivityForResult(localIntent, REGISTER_INTENT_TO_CHOOSECUNTRY_REQUESTCODE);
                RegisterActivity.this.overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView2.setOnClickListener(local8);
        View localView3 = findViewById(R.id.register_money_type_layout);
        View.OnClickListener local9 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent localIntent = new Intent(RegisterActivity.this, ChooseCurrencyActivity.class);
                Utility.startActivitytranslate(RegisterActivity.this, localIntent);
            }
        };
        localView3.setOnClickListener(local9);
        this.mHandler.sendEmptyMessageDelayed(0, 300L);
    }

    private void initTop() {
        RelativeLayout localRelativeLayout = (RelativeLayout) findViewById(R.id.register_top_layout);
        localRelativeLayout.findViewById(R.id.common_left_btn).setBackgroundResource(R.drawable.back_icon);
        View localView1 = localRelativeLayout.findViewById(R.id.common_left_layout);
        View.OnClickListener local1 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Utility.finishActivityTranslate(RegisterActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        ((TextView) localRelativeLayout.findViewById(R.id.common_title)).setText("注册");
        TextView localTextView = (TextView) localRelativeLayout.findViewById(R.id.common_right_font);
        localTextView.setText(R.string.common_submit);
        localTextView.setTextColor(getResources().getColor(R.color.black));
        View localView2 = localRelativeLayout.findViewById(R.id.common_right_layout);
        View.OnClickListener local2 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                String str1 = RegisterActivity.this.userNameEdit.getText().toString();
                String str2 = RegisterActivity.this.phoneNumberEdit.getText().toString();
                String str3 = RegisterActivity.this.smsCodeEdit.getText().toString();
                String str4 = RegisterActivity.this.passwordEdit.getText().toString();
                if (RegisterActivity.this.verifiInputData(str1, str2, str3, str4)) {
                    RegisterActivity.RegisterLoader localRegisterLoader = new RegisterActivity.RegisterLoader(str2, str1, str4, str3);
                    localRegisterLoader.execute();
                }
            }
        };
        localView2.setOnClickListener(local2);
    }

    private void showChooseHeadDialog() {
        CustomHeadChooseDialog.UserChooseHeadListener local10 = new CustomHeadChooseDialog.UserChooseHeadListener() {
            public void cameraClick() {
            }

            public void photoClick() {
            }
        };
        CustomHeadChooseDialog localCustomHeadChooseDialog = new CustomHeadChooseDialog(this, R.style.MyDialog, local10);
        this.dialog = localCustomHeadChooseDialog;
        this.dialog.show();
    }

    private boolean verifiInputData(String paramString1, String paramString2, String paramString3, String paramString4) {
        if ((StringUtils.isBlank(paramString1)) || (StringUtils.isBlank(paramString2)) || (StringUtils.isBlank(paramString3)) || (StringUtils.isBlank(paramString4))) {
            Toast.makeText(this, "请填写完整用户信息(用户名,手机号，验证码，密码)", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Validator.isMobile(paramString2)) {
            Toast.makeText(this, "请正确填写手机号！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    protected int getLayoutResId() {
        return R.layout.activity_register_layout;
    }

    protected void initView() {
        initTop();
        initContent();
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        if ((paramInt2 == -1) && (paramIntent != null) && (paramInt1 == 1))
            this.countryText.setText(paramIntent.getStringExtra("country_data"));
    }

    private class RegisterLoader extends AsyncTask<Void, Void, String> {
        private String mCode;
        private String mNickName;
        private String mPassword;
        private String mUserName;

        RegisterLoader(String paramString1, String paramString2, String paramString3, String arg5) {
            this.mUserName = paramString1;
            this.mNickName = paramString2;
            this.mPassword = paramString3;
            this.mCode = arg5;
        }

        protected String doInBackground(Void[] paramArrayOfVoid) {
            String str = "";
            if (isCancelled())
                return str;
            try {
                UserRepository localUserRepository = new UserRepository();
                localUserRepository.register(this.mUserName, this.mPassword, this.mNickName, this.mCode);
                return str;
            } catch (NetworkException localNetworkException) {
                str = localNetworkException.getMessage();
                Log.d("RegisterActivity", "RegisterLoader error", localNetworkException);
            }
            return str;
        }

        protected void onPostExecute(String paramString) {
            if ((RegisterActivity.this.progressDialog != null) && (RegisterActivity.this.progressDialog.isShowing()))
                RegisterActivity.this.progressDialog.dismiss();
            if (StringUtils.isNotBlank(paramString)) {
                Toast.makeText(RegisterActivity.this, "注册用户失败!", 1).show();
                return;
            }
            Toast.makeText(RegisterActivity.this, "注册用户成功!", 1).show();
            Intent localIntent = new Intent();
            localIntent.putExtra("user_register_phone_number", this.mUserName);
            RegisterActivity.this.setResult(-1, localIntent);
            Utility.finishActivityTranslate(RegisterActivity.this);
        }

        protected void onPreExecute() {
            RegisterActivity.this.progressDialog.setMessage("加载中,请稍后...");
            RegisterActivity.this.progressDialog.show();
        }
    }

    private class SendVerificationCode extends AsyncTask<Void, Void, String> {
        private String mPhoneNumber;
        private String mType;

        SendVerificationCode(String paramString1, String arg3) {
            this.mPhoneNumber = paramString1;
            this.mType = arg3;
        }

        protected String doInBackground(Void[] paramArrayOfVoid) {
            String str = "";
            try {
                UserRepository localUserRepository = new UserRepository();
                localUserRepository.verificationCode(this.mPhoneNumber, this.mType);
                return str;
            } catch (NetworkException localNetworkException) {
                Log.d("RegisterActivity", "SendVerificationCode error", localNetworkException);
                str = localNetworkException.getMessage();
            }
            return str;
        }

        protected void onPostExecute(String paramString) {
            if (StringUtils.isNotBlank(paramString)) {
                Toast.makeText(RegisterActivity.this, "发送验证码失败!",Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(RegisterActivity.this, "发送验证码成功!", Toast.LENGTH_LONG).show();
        }
    }
}