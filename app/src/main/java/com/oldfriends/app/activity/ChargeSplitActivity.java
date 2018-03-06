package com.oldfriends.app.activity;

/**
 * lh on 2016/2/25.
 */

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.fragment.FragmentCharge;
import com.oldfriends.app.util.Utility;

public class ChargeSplitActivity extends BaseActivity
{
    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.charge_split_top_layout);
        ((TextView)localRelativeLayout.findViewById(R.id.common_title)).setText("成员分账");
        View localView = localRelativeLayout.findViewById(R.id.common_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(ChargeSplitActivity.this);
            }
        };
        localView.setOnClickListener(local1);
        localRelativeLayout.findViewById(R.id.common_left_btn).setBackgroundResource(R.drawable.back_icon);
    }

    private void setupData()
    {
        ExpandableListView localExpandableListView = (ExpandableListView)findViewById(R.id.charge_split_groupCharge_list);
        ChargeSplitAdapter localChargeSplitAdapter = new ChargeSplitAdapter(this, 4, localExpandableListView);
        localExpandableListView.setAdapter(localChargeSplitAdapter);
        BitmapDrawable localBitmapDrawable = new BitmapDrawable();
        localExpandableListView.setChildDivider(localBitmapDrawable);
        ExpandableListView.OnGroupClickListener local2 = new ExpandableListView.OnGroupClickListener()
        {
            public boolean onGroupClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView,
                                        int paramAnonymousInt, long paramAnonymousLong)
            {
                return true;
            }
        };
        localExpandableListView.setOnGroupClickListener(local2);
        ExpandableListView.OnChildClickListener local3 = new ExpandableListView.OnChildClickListener()
        {
            public boolean onChildClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView,
                                        int paramAnonymousInt1, int paramAnonymousInt2, long paramAnonymousLong)
            {
                return false;
            }
        };
        localExpandableListView.setOnChildClickListener(local3);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_charge_split_layout;
    }

    protected void initView()
    {
        initTop();
        setupData();
    }

    private class ChargeSplitAdapter extends BaseExpandableListAdapter
    {
        private LayoutInflater mInflater;
        ExpandableListView mListView;
        private int tempCounts;

        public ChargeSplitAdapter(Context paramInt, int paramExpandableListView, ExpandableListView arg4)
        {
            this.mInflater = LayoutInflater.from(paramInt);
            this.tempCounts = paramExpandableListView;
            this.mListView = arg4;
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
            ChildViewHolder localChildViewHolder1;
            if (localView == null)
            {
                localView = this.mInflater.inflate(R.layout.custom_charge_expland_childlist_layout, paramViewGroup, false);
                localChildViewHolder1 = new ChildViewHolder(localView);
                localView.setTag(localChildViewHolder1);
            }else {
                localChildViewHolder1 = (ChildViewHolder)localView.getTag();
            }

            if(paramBoolean){
                localChildViewHolder1.childLine.setVisibility(View.GONE);
            }else {
                localChildViewHolder1.childLine.setVisibility(View.VISIBLE);
            }

            if(paramInt2 == getChildrenCount(paramInt1)-1){
                localChildViewHolder1.childLine.setVisibility(View.VISIBLE);
            }else {
                localChildViewHolder1.childLine.setVisibility(View.GONE);
            }

            return localView;
        }

        public int getChildrenCount(int paramInt)
        {
            return 3;
        }

        public FragmentCharge.MessageItem getGroup(int paramInt)
        {
            return null;
        }

        public int getGroupCount()
        {
            return this.tempCounts;
        }

        public long getGroupId(int paramInt)
        {
            return paramInt;
        }

        public View getGroupView(final int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            final GroupViewHolder localGroupViewHolder1;
            if (localView == null)
            {
                localView = this.mInflater.inflate(R.layout.custom_charge_expland_grouplist_layout, paramViewGroup, false);
                localGroupViewHolder1 = new GroupViewHolder(localView);
                localView.setTag(localGroupViewHolder1);
            }else {
                localGroupViewHolder1 = (GroupViewHolder)localView.getTag();
            }

            RelativeLayout localRelativeLayout = localGroupViewHolder1.showChildBtn;
            View.OnClickListener local1 = new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    if (localGroupViewHolder1.expandBtn.getVisibility() == View.VISIBLE)
                    {
                        ChargeSplitActivity.ChargeSplitAdapter.this.mListView.expandGroup(paramInt);
                        localGroupViewHolder1.expandBtn.setVisibility(View.GONE);
                        localGroupViewHolder1.collapseBtn.setVisibility(View.VISIBLE);
                        return;
                    }
                    ChargeSplitActivity.ChargeSplitAdapter.this.mListView.collapseGroup(paramInt);
                    localGroupViewHolder1.expandBtn.setVisibility(View.VISIBLE);
                    localGroupViewHolder1.collapseBtn.setVisibility(View.GONE);
                }
            };
            localRelativeLayout.setOnClickListener(local1);

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

        private class ChildViewHolder
        {
            TextView childLine;

            ChildViewHolder(View arg2)
            {
                this.childLine = ((TextView)arg2.findViewById(R.id.charge_child_line));
            }
        }

        private class GroupViewHolder
        {
            ImageView collapseBtn;
            ImageView expandBtn;
            ImageView headImage;
            TextView moneyState;
            TextView name;
            RelativeLayout showChildBtn;
            TextView totalMoney;

            GroupViewHolder(View arg2)
            {
                this.showChildBtn = ((RelativeLayout)arg2.findViewById(R.id.group_item_up_layout));
                this.expandBtn = ((ImageView)arg2.findViewById(R.id.charge_group_item_down_icon));
                this.collapseBtn = ((ImageView)arg2.findViewById(R.id.charge_group_item_up_icon));
            }
        }
    }
}
