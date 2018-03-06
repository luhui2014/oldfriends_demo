package com.oldfriends.app.fragment;

/**
 * Created by Administrator on 2016/2/23.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.activity.ChargeDetailActivity;
import com.oldfriends.app.activity.ChooseFriendsActivity;
import com.oldfriends.app.activity.MipcaActivityCapture;
import com.oldfriends.app.activity.SearchActivity;
import com.oldfriends.app.adapter.ChargeExpandListAdapter;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.ListViewChargeCompat;
import com.oldfriends.app.view.SlideView;

import java.util.ArrayList;
import java.util.List;

public class FragmentCharge extends Fragment implements View.OnClickListener{
    public static final String CREATE_NEW_CHARGE = "CREATE_NEW_CHARGE";
    private static final int INTENT_TO_CHOOSE_FRIENDS = 1;
    private int currentIndex;
    private ImageView cursorImageView;
    private DisplayMetrics displayMetrics;
    private ChargeExpandListAdapter expandListAdapter;
    private ListViewChargeCompat expandableListView;
    private View layoutView;
    private List<MessageItem> messageItemList;
    private PopupWindow popupWindow;
    private int screenWidth;

    private void initCursorImageView()
    {
        this.screenWidth = ((WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        this.cursorImageView = ((ImageView)this.layoutView.findViewById(R.id.cursor));
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(this.screenWidth / 3, 4);
        localLayoutParams.setMargins(0, -(int)TypedValue.applyDimension(1, 2.0F, this.displayMetrics), 0, 0);
        this.cursorImageView.setLayoutParams(localLayoutParams);
        Matrix localMatrix = new Matrix();
        localMatrix.postTranslate(0.0F, 0.0F);
        this.cursorImageView.setImageMatrix(localMatrix);
    }

    private void initSubTitle()
    {
        View localView = this.layoutView.findViewById(R.id.charge_search_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                FragmentActivity localFragmentActivity = FragmentCharge.this.getActivity();
                Intent localIntent = new Intent(FragmentCharge.this.getActivity(), SearchActivity.class);
                Utility.startActivitytranslate(localFragmentActivity, localIntent);
            }
        };
        localView.setOnClickListener(local2);
        this.layoutView.findViewById(R.id.fragment_charge_get).setOnClickListener(this);
        this.layoutView.findViewById(R.id.fragment_charge_sum).setOnClickListener(this);
        this.layoutView.findViewById(R.id.fragment_charge_pay).setOnClickListener(this);
        initCursorImageView();
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout1 = (RelativeLayout)this.layoutView.findViewById(R.id.fragment_charge_top_layout);
        ((TextView)localRelativeLayout1.findViewById(R.id.common_title)).setText(R.string.group_charge);
        final RelativeLayout localRelativeLayout2 = (RelativeLayout)localRelativeLayout1.findViewById(R.id.common_right_layout);
        (localRelativeLayout1.findViewById(R.id.common_right_image)).setBackgroundResource(R.drawable.charge_top_right_icon);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                FragmentCharge.this.showPopupWindow(localRelativeLayout2);
            }
        };
        localRelativeLayout2.setOnClickListener(local1);
    }

    private void initView()
    {
        this.displayMetrics = getResources().getDisplayMetrics();
        initTop();
        initSubTitle();
        setupData();
    }

    private void intentToDetailActivity()
    {
        Intent localIntent = new Intent(getActivity(), ChargeDetailActivity.class);
        Utility.startActivitytranslate(getActivity(), localIntent);
    }

    private void setupData()
    {
        ArrayList localArrayList = new ArrayList();
        this.messageItemList = localArrayList;
        for (int i = 0; i < 3; i++)
        {
            MessageItem localMessageItem = new MessageItem();
            this.messageItemList.add(localMessageItem);
        }
        this.expandableListView = ((ListViewChargeCompat)this.layoutView.findViewById(R.id.group_charge_list));
        ChargeExpandListAdapter localChargeExpandListAdapter = new ChargeExpandListAdapter(getActivity(), this.messageItemList, this.expandableListView);
        this.expandListAdapter = localChargeExpandListAdapter;
        this.expandableListView.setAdapter(this.expandListAdapter);
        this.expandableListView.setEmptyView(this.layoutView.findViewById(R.id.group_charge_list_empty));
        ListViewChargeCompat localListViewChargeCompat1 = this.expandableListView;
        BitmapDrawable localBitmapDrawable = new BitmapDrawable();
        localListViewChargeCompat1.setChildDivider(localBitmapDrawable);
        ListViewChargeCompat localListViewChargeCompat2 = this.expandableListView;
        ExpandableListView.OnGroupClickListener local3 = new ExpandableListView.OnGroupClickListener()
        {
            public boolean onGroupClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                Log.d("FragmentCharge", "onGroupClick 触发了");
                FragmentCharge.this.intentToDetailActivity();
                return true;
            }
        };
        localListViewChargeCompat2.setOnGroupClickListener(local3);
        ListViewChargeCompat localListViewChargeCompat3 = this.expandableListView;
        ExpandableListView.OnChildClickListener local4 = new ExpandableListView.OnChildClickListener()
        {
            public boolean onChildClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, long paramAnonymousLong)
            {
                Log.d("FragmentCharge", "onChildClick 触发了");
                FragmentCharge.this.intentToDetailActivity();
                return false;
            }
        };
        localListViewChargeCompat3.setOnChildClickListener(local4);
    }

    private void showPopupWindow(View paramView)
    {
        if (this.popupWindow == null)
        {
            View localView1 = LayoutInflater.from(getActivity()).inflate(R.layout.custom_charge_popwindow_layout, null);
            PopupWindow localPopupWindow2 = new PopupWindow(localView1, -2, -2);
            this.popupWindow = localPopupWindow2;
            View localView2 = localView1.findViewById(R.id.custom_pop_add_newCharge);
            View.OnClickListener local5 = new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView){
                    Intent localIntent = new Intent(FragmentCharge.this.getActivity(), ChooseFriendsActivity.class);
                    localIntent.putExtra(CREATE_NEW_CHARGE, true);
                    FragmentCharge.this.startActivityForResult(localIntent, INTENT_TO_CHOOSE_FRIENDS);
                    FragmentCharge.this.getActivity().overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
                    FragmentCharge.this.popupWindow.dismiss();
                }
            };
            localView2.setOnClickListener(local5);
            View localView3 = localView1.findViewById(R.id.custom_pop_scan);
            View.OnClickListener local6 = new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    Intent localIntent = new Intent();
                    localIntent.setClass(FragmentCharge.this.getActivity(), MipcaActivityCapture.class);
                    localIntent.setFlags(67108864);
                    FragmentCharge.this.startActivity(localIntent);
                    FragmentCharge.this.popupWindow.dismiss();
                }
            };
            localView3.setOnClickListener(local6);
        }
        this.popupWindow.setFocusable(true);
        this.popupWindow.setOutsideTouchable(true);
        PopupWindow localPopupWindow1 = this.popupWindow;
        BitmapDrawable localBitmapDrawable = new BitmapDrawable();
        localPopupWindow1.setBackgroundDrawable(localBitmapDrawable);
        this.popupWindow.setAnimationStyle(2131623940);
        float f = getResources().getDisplayMetrics().density;
        this.popupWindow.showAsDropDown(paramView, -(int)(72.0F * f), 0);
    }

    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        if ((paramInt1 == 1) && (paramIntent != null))
        {
            String str1 = paramIntent.getStringExtra("CREATE_CHARGE_CURRENCY");
            String str2 = paramIntent.getStringExtra("CREATE_CHARGE_FRIENDS");
            Log.d("FragmentCharge", "onActivityResult currency=" + str1 + ",friends=" + str2);
        }
    }

    public void onClick(View paramView)
    {
        TranslateAnimation animation = null;
        int i = this.screenWidth / 3;
        switch (paramView.getId()) {
            case R.id.fragment_charge_get:
                if (this.currentIndex == 1) {
                    animation = new TranslateAnimation(i, 0.0F, 0.0F, 0.0F);
                }else if(this.currentIndex == 2){
                    animation = new TranslateAnimation(i * 2, 0.0F, 0.0F, 0.0F);
                }
                currentIndex = 0;
                break;
            case R.id.fragment_charge_sum:
                if (this.currentIndex == 0) {
                    animation = new TranslateAnimation(0.0F, i, 0.0F, 0.0F);
                }else if(this.currentIndex == 2){
                    animation = new TranslateAnimation(i * 2, i, 0.0F, 0.0F);
                }
                currentIndex = 1;
                break;
            case R.id.fragment_charge_pay:
                if (this.currentIndex == 0) {
                    animation = new TranslateAnimation(0.0F, i * 2, 0.0F, 0.0F);
                }else if(this.currentIndex == 1){
                    animation = new TranslateAnimation(i, i * 2, 0.0F, 0.0F);
                }
                currentIndex = 2;
                break;
        }

        if (animation != null)
        {
            animation.setFillAfter(true);
            animation.setDuration(100L);
            this.cursorImageView.startAnimation(animation);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        this.layoutView = paramLayoutInflater.inflate(R.layout.fragment_charge_layout, paramViewGroup, false);
        initView();
        return this.layoutView;
    }

    public class MessageItem
    {
        public SlideView slideView;
    }
}
