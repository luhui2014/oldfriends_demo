package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ChooseFriendsActivity extends BaseActivity
{
    private static final int CHOOSE_FRIEND_TO_CURREY = 1;
    public static final String CREATE_CHARGE_CURRENCY = "CREATE_CHARGE_CURRENCY";
    public static final String CREATE_CHARGE_FRIENDS = "CREATE_CHARGE_FRIENDS";
    private static final String TAG = "ChooseFriendsActivity";
    private MyAdapter adapter;
    private boolean[] checkState;
    String[] dates;
    private boolean isNewCharge;

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.charge_chooseFriends_top);
        View localView1 = localRelativeLayout.findViewById(R.id.common_font_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(ChooseFriendsActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        View localView2 = localRelativeLayout.findViewById(R.id.common_font_right_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Log.d(TAG, "submit on click");
                if (ChooseFriendsActivity.this.isNewCharge)
                {
                    Intent localIntent = new Intent(ChooseFriendsActivity.this, ChooseCurrencyActivity.class);
                    startActivityForResult(localIntent,CHOOSE_FRIEND_TO_CURREY);
                    overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
                }
            }
        };
        localView2.setOnClickListener(local2);
        ((TextView)localRelativeLayout.findViewById(R.id.common_right_font)).setTextColor(getResources().getColor(R.color.black));
        ((TextView)localRelativeLayout.findViewById(R.id.common_font_title)).setText(R.string.choose_group_charge_friends);
    }

    private void setupData()
    {
        this.checkState = new boolean[this.dates.length];
        StickyListHeadersListView localStickyListHeadersListView = (StickyListHeadersListView)findViewById(R.id.charge_chooseFriends_list);
        this.adapter = new MyAdapter(this, this.dates);
        localStickyListHeadersListView.setAdapter(this.adapter);
        localStickyListHeadersListView.setDividerHeight(0);
        AdapterView.OnItemClickListener local3 = new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                if(checkState[paramAnonymousInt]){
                    checkState[paramAnonymousInt] = false;
                }else {
                    checkState[paramAnonymousInt] = true;
                }
            }
        };
        localStickyListHeadersListView.setOnItemClickListener(local3);
    }

    protected int getLayoutResId()
    {
        return R.layout.charge_new_choose_friends_layout;
    }

    protected void initView()
    {
        this.isNewCharge = getIntent().getBooleanExtra("CREATE_NEW_CHARGE", false);
        this.dates = getResources().getStringArray(R.array.countries);
        initTop();
        setupData();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent paramIntent)
    {
        super.onActivityResult(requestCode, resultCode, paramIntent);
        Log.d(TAG, "onActivityResult requestCode=" + requestCode + ",resultCode=" + resultCode);
        if ((resultCode == -1) && (paramIntent != null) && (requestCode == 1))
        {
            Intent localIntent = new Intent();
            String str1 = paramIntent.getStringExtra("CURRENCY_MONEY_TYPE");
            String str2 = "";
            for (int i = 0; i < this.checkState.length; i++)
                if (this.checkState[i])
                {
                    str2 = str2 + this.dates[i];
                }
            localIntent.putExtra(CREATE_CHARGE_CURRENCY, str1);
            localIntent.putExtra(CREATE_CHARGE_FRIENDS, str2);
            Log.d(TAG, "onActivityResult CHOOSE_FRIEND_TO_CURREY currency=" + str1 + ",friendsName=" + str2);
            setResult(-1, localIntent);
            finish();
        }
    }

    class HeaderViewHolder
    {
        TextView text;
    }

    public class MyAdapter extends BaseAdapter
            implements StickyListHeadersAdapter
    {
        private String[] countries;
        private LayoutInflater inflater;

        public MyAdapter(Context paramArrayOfString, String[] arg3)
        {
            this.inflater = LayoutInflater.from(paramArrayOfString);
            this.countries = arg3;
        }

        public int getCount()
        {
            return this.countries.length;
        }

        public long getHeaderId(int paramInt)
        {
            return countries[paramInt].subSequence(0, 1).charAt(0);
        }

        public View getHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            ChooseFriendsActivity.HeaderViewHolder localHeaderViewHolder2;
            if (localView == null)
            {
                localHeaderViewHolder2 = new ChooseFriendsActivity.HeaderViewHolder();
                localView = this.inflater.inflate(R.layout.choose_friends_head_layout, paramViewGroup, false);
                localHeaderViewHolder2.text = ((TextView)localView.findViewById(R.id.text1));
                localView.setTag(localHeaderViewHolder2);
            }else {
                localHeaderViewHolder2 = (ChooseFriendsActivity.HeaderViewHolder)localView.getTag();
            }

            String str = "" + this.countries[paramInt].subSequence(0, 1).charAt(0);
            localHeaderViewHolder2.text.setText(str);

            return localView;

        }

        public Object getItem(int paramInt)
        {
            return this.countries[paramInt];
        }

        public long getItemId(int paramInt)
        {
            return paramInt;
        }

        public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            ChooseFriendsActivity.ViewHolder localViewHolder2;
            if (localView == null)
            {
                localViewHolder2 = new ChooseFriendsActivity.ViewHolder();
                localView = this.inflater.inflate(R.layout.choose_friends_listitem_layout, paramViewGroup, false);
                localViewHolder2.text = ((TextView)localView.findViewById(R.id.choose_friends_name));
                localViewHolder2.checkBox = ((CheckBox)localView.findViewById(R.id.choose_friends_checkbox));
                localView.setTag(localViewHolder2);
            }else {
                localViewHolder2 = (ChooseFriendsActivity.ViewHolder)localView.getTag();
            }

            localViewHolder2.checkBox.setChecked(ChooseFriendsActivity.this.checkState[paramInt]);
            CheckBox localCheckBox = localViewHolder2.checkBox;
            CompoundButton.OnCheckedChangeListener local1 = new CompoundButton.OnCheckedChangeListener()
            {
                public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
                {
                    ChooseFriendsActivity.this.checkState[paramInt] = paramAnonymousBoolean;
                }
            };
            localCheckBox.setOnCheckedChangeListener(local1);
            localViewHolder2.text.setText(this.countries[paramInt]);
            return localView;

        }
    }

    class ViewHolder
    {
        CheckBox checkBox;
        ImageView image;
        TextView text;
    }
}
