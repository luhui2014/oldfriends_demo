package com.oldfriends.app.activity;

/**
 * lh on 2016/2/25.
 */

import android.content.*;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.oldfriends.app.R;
import com.oldfriends.app.model.ContactModel;
import com.oldfriends.app.util.Utility;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactActivity extends BaseActivity
{
    private static final String TAG = "ContactActivity";
    private List<ContactModel> contactModelList = new ArrayList<ContactModel>();
    private ContactQueryLoader contactQueryLoader;

    private void initContactQuery()
    {
        ContactQueryLoader localContactQueryLoader = new ContactQueryLoader(getContentResolver());
        this.contactQueryLoader = localContactQueryLoader;
        Uri localUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] arrayOfString = { "data1", "display_name", "photo_id", "contact_id" };
        this.contactQueryLoader.startQuery(0, null, localUri, arrayOfString, null, null, "sort_key COLLATE LOCALIZED asc");
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.contact_top_layout);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_text)).setText(R.string.common_back);
        View localView1 = localRelativeLayout.findViewById(R.id.common_backFont_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(ContactActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_title)).setText(R.string.contact_friends);
        localRelativeLayout.findViewById(R.id.common_backFont_finish).setVisibility(View.GONE);
        View localView2 = findViewById(R.id.contact_search_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(ContactActivity.this, SearchActivity.class);
                Utility.startActivitytranslate(ContactActivity.this, localIntent);
            }
        };
        localView2.setOnClickListener(local2);
    }

    private void setupData()
    {
        StickyListHeadersListView localStickyListHeadersListView = (StickyListHeadersListView)findViewById(R.id.contact_friends_list);
        FriendsSectionedAdapter localFriendsSectionedAdapter = new FriendsSectionedAdapter(this,contactModelList);
        localStickyListHeadersListView.setAdapter(localFriendsSectionedAdapter);
    }

    private void sortByName(List<ContactModel> paramList)
    {
        if ((paramList != null) && (paramList.size() > 0))
        {
            Comparator<ContactModel> local3 = new Comparator<ContactModel>()
            {
                public int compare(ContactModel paramAnonymousContactModel1, ContactModel paramAnonymousContactModel2)
                {
                    if (!paramAnonymousContactModel1.getContactName().equals(paramAnonymousContactModel2.getContactName()))
                        return paramAnonymousContactModel1.getContactName().compareTo(paramAnonymousContactModel2.getContactName());
                    return 0;
                }
            };
            Collections.sort(paramList, local3);
        }
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_add_from_contact_layout;
    }

    protected void initView()
    {
        initTop();
        initContactQuery();
    }

    class ContactFriendsViewHolder
    {
        private ImageView headImageView;
        private TextView invateState;
        private TextView nickName;
        private TextView userName;
        private LinearLayout userNameLayout;

        public ContactFriendsViewHolder(View arg2)
        {
            this.headImageView = ((ImageView)arg2.findViewById(R.id.contact_listItem_image));
            this.userNameLayout = ((LinearLayout)arg2.findViewById(R.id.contact_listItem_userName_layout));
            this.userName = ((TextView)arg2.findViewById(R.id.contact_listItem_userName));
            this.nickName = ((TextView)arg2.findViewById(R.id.contact_listItem_nickName));
            this.invateState = ((TextView)arg2.findViewById(R.id.contact_listItem_add_state));
        }
    }

    public class ContactQueryLoader extends AsyncQueryHandler
    {
        public ContactQueryLoader(ContentResolver arg2)
        {
            super(arg2);
        }

        protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
        {
            super.onQueryComplete(paramInt, paramObject, paramCursor);
            Log.d("ContactActivity", "ContactQueryLoader onQueryComplete ");
            if (paramCursor.moveToFirst())
                for (int i = 0; i < paramCursor.getCount(); i++)
                {
                    paramCursor.moveToPosition(i);
                    ContactModel localContactModel = new ContactModel();
                    localContactModel.setPhoneNumber(paramCursor.getString(0));
                    localContactModel.setContactName(paramCursor.getString(1));
                    localContactModel.setPhotoId(paramCursor.getLong(2));
                    localContactModel.setContactId(paramCursor.getInt(3));
                    contactModelList.add(localContactModel);
                }
            sortByName(ContactActivity.this.contactModelList);
            setupData();
        }
    }

    private class FriendsSectionedAdapter extends BaseAdapter
            implements StickyListHeadersAdapter
    {
        private List<ContactModel> contactModelList;
        private LayoutInflater mInflater;

        public FriendsSectionedAdapter(Context context,List<ContactModel> arg2)
        {
            this.mInflater = LayoutInflater.from(context);
            this.contactModelList = arg2;
        }

        public int getCount()
        {
            return this.contactModelList.size();
        }

        public long getHeaderId(int paramInt)
        {
            return getItem(paramInt).getContactName().subSequence(0, 1).charAt(0);
        }

        public View getHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            ContactActivity.HeaderViewHolder localHeaderViewHolder2;
            if (localView == null)
            {
                localHeaderViewHolder2 = new ContactActivity.HeaderViewHolder();
                localView = this.mInflater.inflate(R.layout.charge_detail_list_head_layout, paramViewGroup, false);
                localHeaderViewHolder2.text = ((TextView)localView.findViewById(R.id.text));
                localView.setTag(localHeaderViewHolder2);
            }else {
                localHeaderViewHolder2 = (ContactActivity.HeaderViewHolder)localView.getTag();
            }

            TextView localTextView = localHeaderViewHolder2.text;
            localTextView.setText(" " + getItem(paramInt).getContactName().subSequence(0, 1).charAt(0));

            return localView;
        }

        public ContactModel getItem(int paramInt)
        {
            return contactModelList.get(paramInt);
        }

        public long getItemId(int paramInt)
        {
            return paramInt;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            ContactActivity.ContactFriendsViewHolder localContactFriendsViewHolder1;
            final ContactModel localContactModel;
            if (localView == null)
            {
                localView = this.mInflater.inflate(R.layout.custom_contact_listitem_layout, paramViewGroup, false);
                localContactFriendsViewHolder1 = new ContactActivity.ContactFriendsViewHolder(localView);
                localView.setTag(localContactFriendsViewHolder1);

            }else {
                localContactFriendsViewHolder1 = (ContactActivity.ContactFriendsViewHolder)localView.getTag();

            }

            localContactModel = getItem(paramInt);
            localContactFriendsViewHolder1.userName.setText(localContactModel.getContactName());
            if(0L != localContactModel.getPhotoId()){
                localContactFriendsViewHolder1.headImageView.setImageResource(R.drawable.contact_friend_default);
            }else {
                Uri localUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, localContactModel.getContactId());
                Bitmap localBitmap = BitmapFactory.decodeStream(ContactsContract.Contacts.openContactPhotoInputStream(ContactActivity.this.getContentResolver(), localUri));
                localContactFriendsViewHolder1.headImageView.setImageBitmap(localBitmap);
            }

            localContactFriendsViewHolder1.userNameLayout.setVisibility(8);
            localContactFriendsViewHolder1.invateState.setText("邀请");
            localContactFriendsViewHolder1.invateState.setTextColor(ContactActivity.this.getResources().getColor(R.color.white));
            localContactFriendsViewHolder1.invateState.setBackgroundResource(R.drawable.custom_contact_invite_style);
            TextView localTextView = localContactFriendsViewHolder1.invateState;
            View.OnClickListener local1 = new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    ContactActivity localContactActivity = ContactActivity.this;
                    Toast.makeText(localContactActivity, "phoneNumber=" + localContactModel.getPhoneNumber(), Toast.LENGTH_SHORT).show();
                }
            };
            localTextView.setOnClickListener(local1);

            return localView;
        }
    }

    class HeaderViewHolder
    {
        TextView text;
    }
}
