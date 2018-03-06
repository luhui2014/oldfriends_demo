package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.model.SplitPersonModel;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.view.CustomNoticeDialog;

import java.util.ArrayList;

public class BillEditActivity extends BaseActivity
{
    public static final String BILL_EDIT_TOTAL_MONEY = "BILL_EDIT_TOTAL_MONEY";
    private static final int CHOOSE_CONSUME_TIME_REQUESTCODE = 4;
    private static final int CHOOSE_GROUPNAME_REQUESTCODE = 1;
    private static final int CHOOSE_PAYPERSONNAME_REQUESTCODE = 2;
    private static final int CHOOSE_SPLITTYPE_REQUESTCODE = 3;
    public static final String INTENT_TO_PERSONCHOOSE_STATE = "INTENT_TO_PERSONCHOOSE_STATE";
    private LinearLayout billEditLayout;
    private LinearLayout billPayAllLayout;
    private BillSplitAdapter billSplitAdapter;
    private ImageView billType;
    private TextView calendarTime;
    private TextView chargeGroupText;
    private TextView chargePayPerson;
    private TextView chargeSplitType;
    private boolean isMainToEdit;
    private ListView listView;
    private Handler mHandler;
    private EditText moneyEdit;
    private TextView moneyEditLine;
    private String moneyNumber;
    private EditText nameEdit;
    private TextView nameEditLine;
    private CustomNoticeDialog noticeDialog;

    public BillEditActivity()
    {
        Handler local12 = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                switch (paramAnonymousMessage.what)
                {
                    default:
                        return;
                    case 0:
                        ((InputMethodManager)BillEditActivity.this.nameEdit.getContext().getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(BillEditActivity.this.nameEdit, 0);
                        break;
                }

            }
        };
        this.mHandler = local12;
    }

    private boolean checkoutData()
    {
        Log.d("BillEditActivity", "checkoutData  moneyNumber=" + this.moneyNumber);
        if (StringUtils.isBlank(this.chargeGroupText.getText().toString()))
        {
            showDialog("请选择所属的群账单!");
            return false;
        }
        if (StringUtils.isBlank(this.nameEdit.getText().toString()))
        {
            showDialog("请填写群账名称!");
            return false;
        }
        if (StringUtils.isBlank(this.chargePayPerson.getText().toString()))
        {
            showDialog("请填写付款人名称!");
            return false;
        }
        if ((StringUtils.isBlank(this.moneyNumber)) || (Double.parseDouble(this.moneyNumber) <= 0.0D)){
            showDialog("请填写消费金额!");
            return false;
        }

        return true;
    }

    private void clearEditFocus()
    {
        this.nameEdit.clearFocus();
        this.moneyEdit.clearFocus();
        this.billEditLayout.setFocusable(true);
        this.billEditLayout.setFocusableInTouchMode(true);
        this.billEditLayout.requestFocus();
    }

    private void closeKeyboard()
    {
        View localView = getWindow().peekDecorView();
        if (localView != null)
            ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(localView.getWindowToken(), 0);
    }

    private void initContent()
    {
        this.chargeSplitType = ((TextView)findViewById(R.id.bill_edit_splitType));
        this.billPayAllLayout = ((LinearLayout)findViewById(R.id.fragment_split_pay_single_layout));
        this.listView = ((ListView)findViewById(R.id.bill_edit_split_list));
        this.chargeGroupText = ((TextView)findViewById(R.id.bill_edit_group_name));
        this.billEditLayout = ((LinearLayout)findViewById(R.id.bill_edit_layout));
        this.billEditLayout.setFocusable(false);
        this.billEditLayout.setFocusableInTouchMode(false);
        this.billType = ((ImageView)findViewById(R.id.bill_detail_typeImage));

        ArrayList localArrayList = new ArrayList();
        this.billSplitAdapter = new BillSplitAdapter(this, localArrayList, " ");;
        this.listView.setAdapter(this.billSplitAdapter);
        this.listView.setDividerHeight(0);
        this.mHandler.sendEmptyMessageDelayed(0, 999L);

        initEdit();

        this.noticeDialog = new CustomNoticeDialog(this, R.style.MyDialog);

        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.bill_edit_choose_chargeGroup);
        localRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localIntent = new Intent(BillEditActivity.this, BillGroupChooseActivity.class);
                localIntent.putExtra("INTENT_TO_PERSONCHOOSE_STATE", false);
                startActivityForResult(localIntent, CHOOSE_GROUPNAME_REQUESTCODE);
                overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        });


        ImageView localImageView = this.billType;
        View.OnClickListener local4 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                BillEditActivity.this.showBillTypeDialog();
            }
        };
        localImageView.setOnClickListener(local4);

        this.calendarTime = ((TextView)findViewById(R.id.bill_edit_pay_time));
        View localView1 = findViewById(R.id.bill_edit_pay_time_layout);
        View.OnClickListener local5 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(BillEditActivity.this, CalendarActivity.class);
                BillEditActivity.this.startActivityForResult(localIntent, CHOOSE_CONSUME_TIME_REQUESTCODE);
                overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView1.setOnClickListener(local5);
        View localView2 = findViewById(R.id.bill_edit_pay_person_layout);
        View.OnClickListener local6 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(BillEditActivity.this, BillGroupChooseActivity.class);
                localIntent.putExtra("INTENT_TO_PERSONCHOOSE_STATE", true);
                BillEditActivity.this.startActivityForResult(localIntent, CHOOSE_PAYPERSONNAME_REQUESTCODE);
                overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
            }
        };
        localView2.setOnClickListener(local6);
        this.chargePayPerson = ((TextView)findViewById(R.id.bill_edit_pay_name));
        View localView3 = findViewById(R.id.bill_edit_split_layout);
        View.OnClickListener local7 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (BillEditActivity.this.checkoutData())
                {
                    Intent localIntent = new Intent(BillEditActivity.this, BillSplitTypeActivity.class);
                    localIntent.putExtra("BILL_EDIT_TOTAL_MONEY", Double.parseDouble(BillEditActivity.this.moneyNumber));
                    BillEditActivity.this.startActivityForResult(localIntent, CHOOSE_SPLITTYPE_REQUESTCODE);
                    overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
                }
            }
        };
        localView3.setOnClickListener(local7);

    }

    private void initEdit()
    {
        this.nameEditLine = ((TextView)findViewById(R.id.bill_detail_typeName_line));
        this.moneyEditLine = ((TextView)findViewById(R.id.bill_detail_typeMoney_line));
        this.nameEdit = ((EditText)findViewById(R.id.bill_detail_typeName));
        EditText localEditText1 = this.nameEdit;
        View.OnFocusChangeListener local8 = new View.OnFocusChangeListener()
        {
            public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
            {
                if (paramAnonymousBoolean)
                {
                    BillEditActivity.this.nameEditLine.setBackgroundColor(BillEditActivity.this.getResources().getColor(R.color.common_text_blue_color));
                    BillEditActivity.this.moneyEditLine.setBackgroundColor(BillEditActivity.this.getResources().getColor(R.color.common_line_color));
                    return;
                }
                BillEditActivity.this.setLineDefault();
            }
        };
        localEditText1.setOnFocusChangeListener(local8);
        EditText localEditText2 = this.nameEdit;
        TextWatcher local9 = new TextWatcher()
        {
            public void afterTextChanged(Editable paramAnonymousEditable)
            {
            }

            public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
            {
            }

            public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
            {
            }
        };
        localEditText2.addTextChangedListener(local9);
        this.moneyEdit = ((EditText)findViewById(R.id.bill_detail_typeMoney));
        this.moneyNumber = this.moneyEdit.getText().toString();
        this.moneyEdit.setHintTextColor(getResources().getColor(R.color.common_font_color));
        this.moneyEdit.setTextColor(getResources().getColor(R.color.common_text_click_color));
        EditText localEditText3 = this.moneyEdit;
        View.OnFocusChangeListener local10 = new View.OnFocusChangeListener()
        {
            public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
            {
                if (paramAnonymousBoolean)
                {
                    BillEditActivity.this.nameEditLine.setBackgroundColor(BillEditActivity.this.getResources().getColor(R.color.common_line_color));
                    BillEditActivity.this.moneyEditLine.setBackgroundColor(BillEditActivity.this.getResources().getColor(R.color.common_text_blue_color));
                    return;
                }
                BillEditActivity.this.setLineDefault();
            }
        };
        localEditText3.setOnFocusChangeListener(local10);
        EditText localEditText4 = this.moneyEdit;
        TextWatcher local11 = new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                moneyNumber = s.toString();
            }

            public void beforeTextChanged(CharSequence s, int start,int count, int after)
            {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }
        };
        localEditText4.addTextChangedListener(local11);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.bill_edit_top_layout);
        localRelativeLayout.findViewById(R.id.common_left_btn).setBackgroundResource(R.drawable.back_icon);
        View localView1 = localRelativeLayout.findViewById(R.id.common_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                BillEditActivity.this.closeKeyboard();
                BillEditActivity.this.finish();
                if (BillEditActivity.this.isMainToEdit)
                {
                    BillEditActivity.this.overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_down_out);
                    return;
                }
                BillEditActivity.this.overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_left_out);

            }
        };
        localView1.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_title)).setText(R.string.charge_detail);
        TextView localTextView = (TextView)localRelativeLayout.findViewById(R.id.common_right_font);
        localTextView.setText(R.string.common_submit);
        localTextView.setTextColor(getResources().getColor(R.color.common_text_click_color));
        View localView2 = localRelativeLayout.findViewById(R.id.common_right_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
            }
        };
        localView2.setOnClickListener(local2);
    }

    private void setLineDefault()
    {
        this.nameEditLine.setBackgroundColor(getResources().getColor(R.color.common_line_color));
        this.moneyEditLine.setBackgroundColor(getResources().getColor(R.color.common_line_color));
    }

    private void showBillTypeDialog()
    {
    }

    private void showDialog(String paramString)
    {
        this.noticeDialog.show();
        this.noticeDialog.setCanceledOnTouchOutside(false);
        this.noticeDialog.getContentText().setText(paramString);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_bill_edit_layout;
    }

    protected void initView()
    {
        this.isMainToEdit = getIntent().getBooleanExtra("main_intent_edit", true);
        initTop();
        initContent();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        this.billPayAllLayout.setVisibility(View.GONE);
        clearEditFocus();
        if ((resultCode == -1) && (data != null))
        {
            if (requestCode == CHOOSE_GROUPNAME_REQUESTCODE){
                this.chargeGroupText.setText(data.getStringExtra("LIST_RESULT_DATA"));
            }else if(requestCode == CHOOSE_PAYPERSONNAME_REQUESTCODE){
                String str2 = data.getStringExtra("LIST_RESULT_DATA");
                this.chargePayPerson.setText(str2);
                this.billSplitAdapter.changePayPersonName(str2);
                this.billSplitAdapter.notifyDataSetChanged();
            }else if(requestCode == CHOOSE_SPLITTYPE_REQUESTCODE){
                String str1 = data.getStringExtra("bill_split_type");
                if (!data.getBooleanExtra("bill_split_payall", false))
                {
                    ArrayList localArrayList = data.getParcelableArrayListExtra("bill_split_type_data");
                    BillSplitAdapter localBillSplitAdapter = new BillSplitAdapter(this, localArrayList, this.chargePayPerson.getText().toString());
                    this.billSplitAdapter = localBillSplitAdapter;
                    this.listView.setAdapter(this.billSplitAdapter);
                }else {
                    this.chargeSplitType.setText(str1);
                    ((TextView)findViewById(R.id.fragment_split_pay_single)).setText(this.chargePayPerson.getText().toString());
                    this.billPayAllLayout.setVisibility(View.VISIBLE);
                }
            }else if(requestCode != CHOOSE_CONSUME_TIME_REQUESTCODE){
                this.calendarTime.setText(data.getStringExtra("calendar_time"));
            }
        }

    }

    @Override
    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if(paramKeyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK){
            finish();
            if(isMainToEdit){
                overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_down_out);
            }else {
                overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_left_out);
            }
            return false;
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    private class BillSplitAdapter extends BaseAdapter
    {
        private LayoutInflater mInflater;
        private String payPersonName;
        private ArrayList<SplitPersonModel> personModels;

        public BillSplitAdapter(Context context,ArrayList<SplitPersonModel> paramString, String arg3)
        {
            this.mInflater = LayoutInflater.from(context);
            this.personModels = paramString;
            this.payPersonName = arg3;
        }

        public void changePayPersonName(String paramString)
        {
            this.payPersonName = paramString;
        }

        public int getCount()
        {
            return this.personModels.size();
        }

        public SplitPersonModel getItem(int paramInt)
        {
            return personModels.get(paramInt);
        }

        public long getItemId(int paramInt)
        {
            return paramInt;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            BillEditActivity.SplitViewHolder localSplitViewHolder2;
            if (localView == null)
            {
                BillEditActivity.SplitViewHolder localSplitViewHolder1 = new BillEditActivity.SplitViewHolder();
                localSplitViewHolder2 = localSplitViewHolder1;
                localView = this.mInflater.inflate(R.layout.custom_bill_split_listitem_layout, paramViewGroup, false);
                localSplitViewHolder2.payName = ((TextView)localView.findViewById(R.id.bill_split_payName));
                localSplitViewHolder2.getName = ((TextView)localView.findViewById(R.id.bill_split_getName));
                localSplitViewHolder2.splitMoney = ((TextView)localView.findViewById(R.id.bill_split_money));
                localView.setTag(localSplitViewHolder2);
            }else {
                localSplitViewHolder2 = (BillEditActivity.SplitViewHolder)localView.getTag();
            }

            SplitPersonModel localSplitPersonModel = getItem(paramInt);
            localSplitViewHolder2.payName.setText(localSplitPersonModel.getName());
            TextView localTextView = localSplitViewHolder2.splitMoney;
            localTextView.setText(localSplitPersonModel.getMoney() + "");
            localSplitViewHolder2.getName.setText(this.payPersonName);

            return localView;
        }
    }

    private class SplitViewHolder
    {
        TextView getName;
        TextView payName;
        TextView splitMoney;
    }
}
