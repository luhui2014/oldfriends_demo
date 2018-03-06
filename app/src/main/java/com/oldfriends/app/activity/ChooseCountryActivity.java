package com.oldfriends.app.activity;

/**
 * Created by Administrator on 2016/2/24.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oldfriends.app.R;
import com.oldfriends.app.model.CountryModel;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.SideBar;
import jxl.Sheet;
import jxl.Workbook;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChooseCountryActivity extends BaseActivity
{
    public static final String COUNTRY_DATA = "country_data";
    private View headView;
    private ProgressDialog progressDialog;
    private StickyListHeadersListView stickyList;

    private void finishWithData(String paramString)
    {
        Intent localIntent = new Intent();
        localIntent.putExtra("country_data", paramString);
        setResult(-1, localIntent);
        Utility.finishActivityTranslate(this);
    }

    private int getPositionForSelection(List<CountryModel> paramList, String paramString)
    {
        for (int i = 0; i < paramList.size(); i++)
            if (((CountryModel)paramList.get(i)).getEnglishName().toUpperCase().charAt(0) == paramString.charAt(0))
                return i;
        return -1;
    }

    private List<CountryModel> getXlsData(String paramString, int paramInt)
    {
        ArrayList localArrayList = new ArrayList();
        AssetManager localAssetManager = getAssets();
        try
        {
            Workbook localWorkbook = Workbook.getWorkbook(localAssetManager.open(paramString));
            Sheet localSheet = localWorkbook.getSheet(paramInt);
            int i = localWorkbook.getNumberOfSheets();
            int j = localSheet.getRows();
            int k = localSheet.getColumns();
            Log.d("ChooseCountryActivity", "the num of sheets is " + i);
            Log.d("ChooseCountryActivity", "the name of sheet is  " + localSheet.getName());
            Log.d("ChooseCountryActivity", "total rows is 行=" + j);
            Log.d("ChooseCountryActivity", "total cols is 列=" + k);
            for (int m = 0; m < j; m++)
            {
                CountryModel localCountryModel = new CountryModel();
                localCountryModel.setChinaName(localSheet.getCell(0, m).getContents());
                localCountryModel.setEnglishName(localSheet.getCell(1, m).getContents());
                localCountryModel.setAreaNumber(localSheet.getCell(2, m).getContents());
                localArrayList.add(localCountryModel);
            }
            localWorkbook.close();
            return localArrayList;
        }
        catch (Exception localException)
        {
            Log.e("ChooseCountryActivity", "read error=" + localException, localException);
        }

        return null;
    }

    private void initListHeadView()
    {
        this.headView = View.inflate(this, R.layout.custom_choose_country_head_layout, null);
        View localView1 = this.headView.findViewById(R.id.china_number_layout);
        View.OnClickListener local3 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                ChooseCountryActivity.this.finishWithData("中国  +86");
            }
        };
        localView1.setOnClickListener(local3);
        View localView2 = this.headView.findViewById(R.id.hongkong_number_layout);
        View.OnClickListener local4 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                ChooseCountryActivity.this.finishWithData("香港  +852");
            }
        };
        localView2.setOnClickListener(local4);
        View localView3 = this.headView.findViewById(R.id.taiwan_number_layout);
        View.OnClickListener local5 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                ChooseCountryActivity.this.finishWithData("台湾  +886");
            }
        };
        localView3.setOnClickListener(local5);
        View localView4 = this.headView.findViewById(R.id.macao_number_layout);
        View.OnClickListener local6 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                ChooseCountryActivity.this.finishWithData("澳门  +853");
            }
        };
        localView4.setOnClickListener(local6);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.choose_country_top_layout);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_text)).setText(R.string.common_back);
        View localView1 = localRelativeLayout.findViewById(R.id.common_backFont_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(ChooseCountryActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        ((TextView)localRelativeLayout.findViewById(R.id.common_backFont_title)).setText(R.string.choose_country);
        localRelativeLayout.findViewById(R.id.common_backFont_finish).setVisibility(View.GONE);
        View localView2 = findViewById(R.id.choose_country_search_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(ChooseCountryActivity.this, CountrySearchActivity.class);
                ChooseCountryActivity.this.startActivity(localIntent);
            }
        };
        localView2.setOnClickListener(local2);
    }

    private void setupData(final List<CountryModel> paramList)
    {
        this.stickyList = ((StickyListHeadersListView)findViewById(R.id.choose_country_list));
        CountryChooseAdapter localCountryChooseAdapter = new CountryChooseAdapter(this, paramList);
        this.stickyList.addHeaderView(this.headView);
        this.stickyList.setAdapter(localCountryChooseAdapter);
        this.stickyList.setDividerHeight(0);
        StickyListHeadersListView localStickyListHeadersListView = this.stickyList;
        AdapterView.OnItemClickListener local7 = new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                Log.d("ChooseCountryActivity", "onItemClick position=" + paramAnonymousInt);
                CountryModel localCountryModel = (CountryModel)paramAnonymousAdapterView.getAdapter().getItem(paramAnonymousInt);
                String str = localCountryModel.getChinaName() + "  +" + localCountryModel.getAreaNumber();
                ChooseCountryActivity.this.finishWithData(str);
            }
        };
        localStickyListHeadersListView.setOnItemClickListener(local7);
        SideBar localSideBar = (SideBar)findViewById(R.id.sideBar);
        localSideBar.setTextView((TextView)findViewById(R.id.dialog));
        SideBar.OnTouchingLetterChangedListener local8 = new SideBar.OnTouchingLetterChangedListener()
        {
            public void onTouchingLetterChanged(String paramAnonymousString)
            {
                Log.d("ChooseCountryActivity", "onTouchingLetterChanged s=" + paramAnonymousString + ",char(0)=" + paramAnonymousString.charAt(0));
                int i = ChooseCountryActivity.this.getPositionForSelection(paramList, paramAnonymousString);
                if (i != -1)
                    ChooseCountryActivity.this.stickyList.setSelection(i);
            }
        };
        localSideBar.setOnTouchingLetterChangedListener(local8);
    }

    private void sortByName(List<CountryModel> paramList)
    {
        if ((paramList != null) && (paramList.size() > 0))
        {
            Comparator local9 = new Comparator<CountryModel>()
            {
                public int compare(CountryModel paramAnonymousCountryModel1, CountryModel paramAnonymousCountryModel2)
                {
                    if (!paramAnonymousCountryModel1.getEnglishName().equals(paramAnonymousCountryModel2.getEnglishName()))
                        return paramAnonymousCountryModel1.getEnglishName().compareTo(paramAnonymousCountryModel2.getEnglishName());
                    return 0;
                }
            };
            Collections.sort(paramList, local9);
        }
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_choose_country_layout;
    }

    protected void initView()
    {
        ProgressDialog localProgressDialog = new ProgressDialog(this);
        this.progressDialog = localProgressDialog;
        initTop();
        initListHeadView();
        CountryLoader localCountryLoader = new CountryLoader();
        localCountryLoader.execute(new Void[0]);
    }

    class ChooseViewHolder
    {
        private TextView chinaName;
        private TextView englishName;
        private TextView line;
        private TextView number;

        public ChooseViewHolder(View arg2)
        {
            this.chinaName = ((TextView)arg2.findViewById(R.id.choose_country_listItem_chineseName));
            this.englishName = ((TextView)arg2.findViewById(R.id.choose_country_listItem_englishName));
            this.number = ((TextView)arg2.findViewById(R.id.choose_country_listItem_number));
            this.line = ((TextView)arg2.findViewById(R.id.choose_country_listItem_line));
        }
    }

    public class CountryChooseAdapter extends BaseAdapter
            implements StickyListHeadersAdapter
    {
        private List<CountryModel> countries;
        private LayoutInflater inflater;

        public CountryChooseAdapter(Context context,List<CountryModel> arg2)
        {
            this.inflater = LayoutInflater.from(context);
            this.countries = arg2;
        }

        public int getCount()
        {
            return this.countries.size();
        }

        public long getHeaderId(int paramInt)
        {
            return ((CountryModel)this.countries.get(paramInt)).getEnglishName().subSequence(0, 1).charAt(0);
        }

        public View getHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            ChooseCountryActivity.HeaderViewHolder localHeaderViewHolder2;
            if (localView == null)
            {
                ChooseCountryActivity.HeaderViewHolder localHeaderViewHolder1 = new ChooseCountryActivity.HeaderViewHolder();
                localHeaderViewHolder2 = localHeaderViewHolder1;
                localView = this.inflater.inflate(R.layout.choose_friends_head_layout, paramViewGroup, false);
                localHeaderViewHolder2.text = ((TextView)localView.findViewById(R.id.text1));
                localView.setTag(localHeaderViewHolder2);
            }else {
                localHeaderViewHolder2 = (ChooseCountryActivity.HeaderViewHolder)localView.getTag();
            }

            CountryModel localCountryModel = getItem(paramInt);
            String str = "" + localCountryModel.getEnglishName().subSequence(0, 1).charAt(0);
            localHeaderViewHolder2.text.setText(str);
            return localView;

        }

        public CountryModel getItem(int paramInt)
        {
            return (CountryModel)this.countries.get(paramInt);
        }

        public long getItemId(int paramInt)
        {
            return paramInt;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            View localView = paramView;
            ChooseCountryActivity.ChooseViewHolder localChooseViewHolder1;
            if (localView == null)
            {
                localView = this.inflater.inflate(R.layout.custom_choose_country_listitem_layout, paramViewGroup, false);
                ChooseCountryActivity.ChooseViewHolder localChooseViewHolder2 = new ChooseCountryActivity.ChooseViewHolder(localView);
                localChooseViewHolder1 = localChooseViewHolder2;
                localView.setTag(localChooseViewHolder1);
                CountryModel localCountryModel = getItem(paramInt);
                localChooseViewHolder1.englishName.setText(localCountryModel.getEnglishName());
                localChooseViewHolder1.chinaName.setText(localCountryModel.getChinaName());
                String str = localCountryModel.getAreaNumber();
                if (StringUtils.isNotBlank(str))
                {
                    TextView localTextView = localChooseViewHolder1.number;
                    localTextView.setText("+" + str);
                }
                if (paramInt == -1 + getCount()){
                    localChooseViewHolder1.line.setVisibility(View.GONE);
                }

            }else {
                localChooseViewHolder1 = (ChooseCountryActivity.ChooseViewHolder)localView.getTag();
            }

            if(paramInt == getHeaderId(paramInt)){
                localChooseViewHolder1.line.setVisibility(View.GONE);
            }else {
                localChooseViewHolder1.line.setVisibility(View.VISIBLE);
            }

            return localView;
        }
    }

    private class CountryLoader extends AsyncTask<Void, Void, List<CountryModel>>
    {
        protected List<CountryModel> doInBackground(Void[] paramArrayOfVoid)
        {
            return ChooseCountryActivity.this.getXlsData("phone_country_info.xls", 0);
        }

        protected void onPostExecute(List<CountryModel> paramList)
        {
            if ((ChooseCountryActivity.this.progressDialog != null) && (ChooseCountryActivity.this.progressDialog.isShowing()))
                ChooseCountryActivity.this.progressDialog.dismiss();
            if (paramList != null)
            {
                ChooseCountryActivity.this.sortByName(paramList);
                ChooseCountryActivity.this.setupData(paramList);
            }
        }

        protected void onPreExecute()
        {
            ChooseCountryActivity.this.progressDialog.setMessage("加载中,请稍后......");
            ChooseCountryActivity.this.progressDialog.setCanceledOnTouchOutside(false);
            ChooseCountryActivity.this.progressDialog.show();
        }
    }

    class HeaderViewHolder
    {
        TextView text;
    }
}
