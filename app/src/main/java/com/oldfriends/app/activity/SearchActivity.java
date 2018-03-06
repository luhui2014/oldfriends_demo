package com.oldfriends.app.activity;

/**
 * Created by Administrator on 2016/2/23.
 */
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.ClearEditText;

public class SearchActivity extends BaseActivity
{
    private Handler mHandler;
    private ClearEditText searchEdit;
    private String searchKeyword;

    public SearchActivity()
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
                }
                ((InputMethodManager)SearchActivity.this.searchEdit.getContext().getSystemService("input_method")).showSoftInput(SearchActivity.this.searchEdit, 0);
            }
        };
        this.mHandler = local4;
    }

    private void closeKeyboard()
    {
        View localView = getWindow().peekDecorView();
        if (localView != null)
            ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(localView.getWindowToken(), 0);
    }

    private void initSearchView()
    {
        this.searchEdit = ((ClearEditText)findViewById(R.id.search_edit));
        this.mHandler.sendEmptyMessageDelayed(0, 100L);
        ((InputMethodManager)getSystemService("input_method")).showSoftInputFromInputMethod(this.searchEdit.getWindowToken(), 0);
        ClearEditText localClearEditText1 = this.searchEdit;
        TextWatcher local2 = new TextWatcher()
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
        localClearEditText1.addTextChangedListener(local2);
        ClearEditText localClearEditText2 = this.searchEdit;
        View.OnKeyListener local3 = new View.OnKeyListener()
        {
            public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
            {
                StringBuilder localStringBuilder = new StringBuilder();
                Log.d("AAAA", "mInputTitleBar  OnKeyListener keyCode=" + paramAnonymousInt + ", event=" + paramAnonymousKeyEvent);
                if ((paramAnonymousInt == 66) && (paramAnonymousKeyEvent.getAction() == 1));
                return false;
            }
        };
        localClearEditText2.setOnKeyListener(local3);
    }

    private void initTop()
    {
        View localView = findViewById(R.id.home_search_icon);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                SearchActivity.this.closeKeyboard();
                Utility.finishActivityTranslate(SearchActivity.this);
            }
        };
        localView.setOnClickListener(local1);
    }

    private void setupData()
    {
        ExpandableListView localExpandableListView = (ExpandableListView)findViewById(R.id.search_list);
        localExpandableListView.setDivider(null);
        localExpandableListView.addFooterView(LayoutInflater.from(this).inflate(R.layout.custom_search_list_footer_layout, null));
        ExpandListAdapter localExpandListAdapter = new ExpandListAdapter(this);
        localExpandableListView.setAdapter(localExpandListAdapter);
        for (int i = 0; i < 3; i++)
            localExpandableListView.expandGroup(i);
        ExpandableListView.OnGroupClickListener local5 = new ExpandableListView.OnGroupClickListener()
        {
            public boolean onGroupClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                Log.d("SearchActivity", "onGroupClick 触发了");
                return true;
            }
        };
        localExpandableListView.setOnGroupClickListener(local5);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_search_layout;
    }

    protected void initView()
    {
        initTop();
        initSearchView();
        setupData();
    }

    private class ExpandListAdapter extends BaseExpandableListAdapter
    {
        private LayoutInflater mInflater;

        public ExpandListAdapter(Context arg2)
        {
            this.mInflater = LayoutInflater.from(arg2);
        }

        public Object getChild(int paramInt1, int paramInt2)
        {
            return null;
        }

        public long getChildId(int paramInt1, int paramInt2)
        {
            return paramInt2;
        }


        public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            SearchActivity.SearchChildViewHolder localSearchChildViewHolder2;
            if (localView == null)
            {
                SearchActivity.SearchChildViewHolder localSearchChildViewHolder1 = new SearchActivity.SearchChildViewHolder();
                localSearchChildViewHolder2 = localSearchChildViewHolder1;
                localView = this.mInflater.inflate(R.layout.search_child_listitem_layout, paramViewGroup, false);
                localSearchChildViewHolder2.bottomBackGround = ((TextView)localView.findViewById(R.id.search_list_child_bottom_bg));
                localSearchChildViewHolder2.bottomLine = ((TextView)localView.findViewById(R.id.search_list_child_bottom_line));
                localView.setTag(localSearchChildViewHolder2);

//                if (paramInt2 != -1 + getChildrenCount(paramInt1))
//                    break label156;
//                if (paramInt1 != -1 + getGroupCount())
//                    break label134;
//                localSearchChildViewHolder2.bottomBackGround.setVisibility(8);
//                localSearchChildViewHolder2.bottomLine.setVisibility(0);
            }else {
                localSearchChildViewHolder2 = (SearchActivity.SearchChildViewHolder)localView.getTag();
            }

//            while (true)
//            {
//
//                localSearchChildViewHolder2 = (SearchActivity.SearchChildViewHolder)localView.getTag();
//                break;
//                label134: localSearchChildViewHolder2.bottomBackGround.setVisibility(0);
//                localSearchChildViewHolder2.bottomLine.setVisibility(8);
//                continue;
//                label156: localSearchChildViewHolder2.bottomBackGround.setVisibility(8);
//                localSearchChildViewHolder2.bottomLine.setVisibility(0);
//            }
            return localView;
        }

        public int getChildrenCount(int paramInt)
        {
            if (paramInt == 2)
                return 3;
            return 1;
        }

        public Object getGroup(int paramInt)
        {
            return null;
        }

        public int getGroupCount()
        {
            return 3;
        }

        public long getGroupId(int paramInt)
        {
            return paramInt;
        }

        public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            SearchActivity.SearchGroupViewHolder localSearchGroupViewHolder2;
            if (localView == null)
            {
                SearchActivity.SearchGroupViewHolder localSearchGroupViewHolder1 = new SearchActivity.SearchGroupViewHolder();
                localSearchGroupViewHolder2 = localSearchGroupViewHolder1;
                localView = this.mInflater.inflate(R.layout.search_group_listitem_layout, paramViewGroup, false);
                localSearchGroupViewHolder2.groupTitle = ((TextView)localView.findViewById(R.id.search_group_name));
                localView.setTag(localSearchGroupViewHolder2);
//                if (paramInt != 0)
//                    break label92;
                localSearchGroupViewHolder2.groupTitle.setText("好友");
            }else {
                localSearchGroupViewHolder2 = (SearchActivity.SearchGroupViewHolder)localView.getTag();
            }
//            while (true)
//            {
//                return localView;
//                localSearchGroupViewHolder2 = (SearchActivity.SearchGroupViewHolder)localView.getTag();
//                break;
//                label92: if (paramInt == 1)
//                    localSearchGroupViewHolder2.groupTitle.setText("群账");
//                else
//                    localSearchGroupViewHolder2.groupTitle.setText("账单");
//            }
            return localView;
        }

        public boolean hasStableIds()
        {
            return false;
        }

        public boolean isChildSelectable(int paramInt1, int paramInt2)
        {
            return true;
        }
    }

    private class SearchChildViewHolder
    {
        TextView bottomBackGround;
        TextView bottomLine;

        private SearchChildViewHolder()
        {
        }
    }

    private class SearchGroupViewHolder
    {
        TextView groupTitle;

        private SearchGroupViewHolder()
        {
        }
    }
}
