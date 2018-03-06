package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.oldfriends.app.R;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.util.Utility;

public class FriendRemarkActivity extends BaseActivity {
    public static final String FRIEND_RENAME_NAME = "FRIEND_RENAME_NAME";
    public static final String RENAME_NAME_STATE = "RENAME_NAME_STATE";
    private Handler mHandler;
    private EditText nameEdit;
    private String remarkName;
    private int remarkState;

    public FriendRemarkActivity() {
        Handler local4 = new Handler() {
            public void handleMessage(Message paramAnonymousMessage) {
                switch (paramAnonymousMessage.what) {
                    default:
                        return;
                    case 0:
                }
                ((InputMethodManager) FriendRemarkActivity.this.nameEdit.getContext().getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(FriendRemarkActivity.this.nameEdit, 0);
            }
        };
        this.mHandler = local4;
    }

    private void initTop() {
        RelativeLayout localRelativeLayout = (RelativeLayout) findViewById(R.id.friend_remark_top_layout);
        View localView1 = localRelativeLayout.findViewById(R.id.common_backFont_left_layout);
        View.OnClickListener local2 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Utility.finishActivityTranslate(FriendRemarkActivity.this);
            }
        };
        localView1.setOnClickListener(local2);
        TextView localTextView = (TextView) localRelativeLayout.findViewById(R.id.common_backFont_title);
        if (this.remarkState == 2) {
            localTextView.setText("群账名称");
        } else if (this.remarkState == 3) {
            localTextView.setText("我的昵称");
        }

        View localView2 = localRelativeLayout.findViewById(R.id.common_backFont_finish);
        View.OnClickListener local3 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (StringUtils.isNotBlank(FriendRemarkActivity.this.remarkName)) {
                    Intent localIntent = new Intent();
                    localIntent.putExtra("FRIEND_RENAME_NAME", FriendRemarkActivity.this.remarkName);
                    FriendRemarkActivity.this.setResult(-1, localIntent);
                    Utility.finishActivityTranslate(FriendRemarkActivity.this);
                    return;
                }
                Toast.makeText(FriendRemarkActivity.this, "未填写名称!", Toast.LENGTH_SHORT).show();
            }
        };
        localView2.setOnClickListener(local3);

    }

    protected int getLayoutResId() {
        return R.layout.activity_friend_remark_layout;
    }

    protected void initView() {
        this.remarkState = getIntent().getIntExtra("RENAME_NAME_STATE", 1);
        initTop();
        TextView localTextView = (TextView) findViewById(R.id.friend_remark_notice);
        if (this.remarkState == 2) {
            localTextView.setText("新的群账名称");
        } else if (this.remarkState == 3) {
            localTextView.setText("我的新昵称");
        }

        this.nameEdit = ((EditText) findViewById(R.id.friend_remark_edit));
        EditText localEditText = this.nameEdit;
        TextWatcher local1 = new TextWatcher() {
            public void afterTextChanged(Editable paramAnonymousEditable) {
            }

            public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
            }

            public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
            }
        };
        localEditText.addTextChangedListener(local1);
        this.mHandler.sendEmptyMessageDelayed(0, 100L);

    }
}
