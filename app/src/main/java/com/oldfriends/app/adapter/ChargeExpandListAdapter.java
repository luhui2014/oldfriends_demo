package com.oldfriends.app.adapter;

/**
 * lh on 2016/2/23.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.fragment.FragmentCharge;
import com.oldfriends.app.fragment.FragmentCharge.MessageItem;
import com.oldfriends.app.view.SlideView;
import com.oldfriends.app.view.SlideView.OnSlideListener;

import java.util.List;

public class ChargeExpandListAdapter extends BaseExpandableListAdapter
{
    private Context mContext;
    private LayoutInflater mInflater;
    private SlideView mLastSlideViewWithStatusOn;
    ExpandableListView mListView;
    private List<FragmentCharge.MessageItem> mMessageItemList;

    public ChargeExpandListAdapter(Context paramContext, List<FragmentCharge.MessageItem> paramList, ExpandableListView paramExpandableListView)
    {
        this.mInflater = LayoutInflater.from(paramContext);
        this.mContext = paramContext;
        this.mListView = paramExpandableListView;
        this.mMessageItemList = paramList;
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
        return mMessageItemList.get(paramInt);
    }

    public int getGroupCount()
    {
        if (this.mMessageItemList != null)
            return this.mMessageItemList.size();
        return 0;
    }

    public long getGroupId(int paramInt)
    {
        return paramInt;
    }

    public View getGroupView(final int paramInt, boolean paramBoolean, View convertView, ViewGroup paramViewGroup)
    {
        GroupViewHolder holder ;
        SlideView slideView = (SlideView) convertView;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_charge_expland_grouplist_layout, paramViewGroup, false);
        }

        if (slideView == null) {
            slideView = new SlideView(this.mContext);
            slideView.setContentView(convertView);
            holder = new GroupViewHolder(slideView);

            slideView.setOnSlideListener(new OnSlideListener() {
                @Override
                public void onSlide(View view, int status) {
                    if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
                        Log.d("LoadPreSetActivity", "  onSlide   shrink ");
                        mLastSlideViewWithStatusOn.shrink();
                    }

                    if (status == SLIDE_STATUS_ON) {
                        Log.d("LoadPreSetActivity", "  onSlide   status SLIDE_STATUS_ON");
                        mLastSlideViewWithStatusOn = (SlideView) view;
                    }
                }
            });

            slideView.setTag(holder);
        } else {
            holder = (GroupViewHolder) slideView.getTag();
        }

        final MessageItem item = getGroup(paramInt);
        item.slideView = slideView;
        item.slideView.shrink();
        holder.deleteHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChargeExpandListAdapter.this.mContext, "11111...", Toast.LENGTH_SHORT).show();
            }
        });

        final ImageView expandBtn = holder.expandBtn;
        final ImageView collapseBtn= holder.collapseBtn;
        holder.showChildBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandBtn.getVisibility() == View.VISIBLE)
                {
                    ChargeExpandListAdapter.this.mListView.expandGroup(paramInt);
                    expandBtn.setVisibility(View.GONE);
                    collapseBtn.setVisibility(View.VISIBLE);
                    return;
                }
                ChargeExpandListAdapter.this.mListView.collapseGroup(paramInt);
                expandBtn.setVisibility(View.VISIBLE);
                collapseBtn.setVisibility(View.GONE);
            }
        });

        return slideView;

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
        private ViewGroup deleteHolder;
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
            this.deleteHolder = ((ViewGroup)arg2.findViewById(R.id.holder));
            ((TextView)this.deleteHolder.findViewById(R.id.delete)).setText("退出");
        }
    }

}
