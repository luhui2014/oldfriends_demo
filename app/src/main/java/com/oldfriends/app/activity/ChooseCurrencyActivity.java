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
import com.oldfriends.app.model.CurrencyModel;
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

public class ChooseCurrencyActivity extends BaseActivity {
    public static final String CHOOSE_CURRENCY_TYPE = "CHOOSE_CURRENCY_TYPE";
    public static final String CURRENCY_MONEY_TYPE = "CURRENCY_MONEY_TYPE";
    private int currencyType;
    private View headView;
    private StickyListHeadersListView listView;
    private ProgressDialog progressDialog;

    private void finishWithData(String paramString) {
        Intent localIntent = new Intent();
        localIntent.putExtra("CURRENCY_MONEY_TYPE", paramString);
        setResult(-1, localIntent);
        Utility.finishActivityTranslate(this);
    }

    private int getPositionForSelection(List<CurrencyModel> paramList, String paramString) {
        for (int i = 0; i < paramList.size(); i++)
            if (((CurrencyModel) paramList.get(i)).getShortName().toUpperCase().charAt(0) == paramString.charAt(0))
                return i;
        return -1;
    }

    private List<CurrencyModel> getXlsData(String paramString, int paramInt) {
        ArrayList localArrayList = new ArrayList();
        AssetManager localAssetManager = getAssets();
        try {
            Workbook localWorkbook = Workbook.getWorkbook(localAssetManager.open(paramString));
            Sheet localSheet = localWorkbook.getSheet(paramInt);
            int i = localWorkbook.getNumberOfSheets();
            int j = localSheet.getRows();
            int k = localSheet.getColumns();
            Log.d("ChooseCurrencyActivity", "the num of sheets is " + i);
            Log.d("ChooseCurrencyActivity", "the name of sheet is  " + localSheet.getName());
            Log.d("ChooseCurrencyActivity", "total rows is 行=" + j);
            Log.d("ChooseCurrencyActivity", "total cols is 列=" + k);
            for (int m = 0; m < j; m++) {
                CurrencyModel localCurrencyModel = new CurrencyModel();
                localCurrencyModel.setCurrencyName(localSheet.getCell(0, m).getContents());
                localCurrencyModel.setEnglishName(localSheet.getCell(1, m).getContents());
                localCurrencyModel.setSymbol(localSheet.getCell(2, m).getContents());
                localCurrencyModel.setShortName(localSheet.getCell(3, m).getContents());
                localArrayList.add(localCurrencyModel);
            }
            localWorkbook.close();
            return localArrayList;
        } catch (Exception localException) {
            Log.e("ChooseCurrencyActivity", "read error=" + localException, localException);
        }
        return null;
    }

    private void initListHeadView() {
        this.headView = View.inflate(this, R.layout.custom_choose_currency_head_layout, null);
        View localView1 = this.headView.findViewById(R.id.choose_currency_head_china_layout);
        View.OnClickListener local3 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ChooseCurrencyActivity.this.finishWithData("CNY-人民币(RMB￥)");
            }
        };
        localView1.setOnClickListener(local3);
        View localView2 = this.headView.findViewById(R.id.choose_currency_head_taiwan_layout);
        View.OnClickListener local4 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ChooseCurrencyActivity.this.finishWithData("TWD-新台币(NT＄)");
            }
        };
        localView2.setOnClickListener(local4);
        View localView3 = this.headView.findViewById(R.id.choose_currency_head_Hk_layout);
        View.OnClickListener local5 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ChooseCurrencyActivity.this.finishWithData("HKD-港元(HK＄)");
            }
        };
        localView3.setOnClickListener(local5);
        View localView4 = this.headView.findViewById(R.id.choose_currency_head_macao_layout);
        View.OnClickListener local6 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                ChooseCurrencyActivity.this.finishWithData("MOP-澳门元(MOP)");
            }
        };
        localView4.setOnClickListener(local6);
    }

    private void initTop() {
        RelativeLayout localRelativeLayout = (RelativeLayout) findViewById(R.id.choose_currency_top_layout);
        View localView1 = localRelativeLayout.findViewById(R.id.common_backFont_left_layout);
        View.OnClickListener local1 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Utility.finishActivityTranslate(ChooseCurrencyActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        TextView localTextView = (TextView) localRelativeLayout.findViewById(R.id.common_backFont_title);
        if (this.currencyType == 1) {
            localTextView.setText("选择结算币种");
        } else if (this.currencyType == 2)
            localTextView.setText("更改结算币种");
        else if (this.currencyType == 3)
            localTextView.setText("消费币种");

        localRelativeLayout.findViewById(R.id.common_backFont_finish).setVisibility(View.GONE);
        View localView2 = findViewById(R.id.choose_currency_search_layout);
        View.OnClickListener local2 = new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent localIntent = new Intent(ChooseCurrencyActivity.this, CountrySearchActivity.class);
                ChooseCurrencyActivity.this.startActivity(localIntent);
            }
        };
        localView2.setOnClickListener(local2);
    }

    private void setupData(final List<CurrencyModel> paramList) {
        this.listView = ((StickyListHeadersListView) findViewById(R.id.choose_currency_list));
        CurrencyListAdapter localCurrencyListAdapter = new CurrencyListAdapter(ChooseCurrencyActivity.this,paramList);
        this.listView.addHeaderView(this.headView);
        this.listView.setDividerHeight(0);
        this.listView.setAdapter(localCurrencyListAdapter);
        StickyListHeadersListView localStickyListHeadersListView = this.listView;
        AdapterView.OnItemClickListener local8 = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                CurrencyModel localCurrencyModel = (CurrencyModel) paramAnonymousAdapterView.getAdapter().getItem(paramAnonymousInt);
                String str = localCurrencyModel.getShortName() + "-" + localCurrencyModel.getCurrencyName() + "(" + localCurrencyModel.getSymbol() + ")";
                ChooseCurrencyActivity.this.finishWithData(str);
            }
        };
        localStickyListHeadersListView.setOnItemClickListener(local8);
        SideBar localSideBar = (SideBar) findViewById(R.id.sideBar);
        localSideBar.setTextView((TextView) findViewById(R.id.dialog));
        SideBar.OnTouchingLetterChangedListener local9 = new SideBar.OnTouchingLetterChangedListener() {
            public void onTouchingLetterChanged(String paramAnonymousString) {
                if (paramAnonymousString.equals("#"))
                    ChooseCurrencyActivity.this.listView.setSelection(0);
                int i;
                do {
                    i = ChooseCurrencyActivity.this.getPositionForSelection(paramList, paramAnonymousString);
                }
                while (i == -1);
                ChooseCurrencyActivity.this.listView.setSelection(i);
            }
        };
        localSideBar.setOnTouchingLetterChangedListener(local9);
    }

    private void sortByName(List<CurrencyModel> paramList) {
        if ((paramList != null) && (paramList.size() > 0)) {
            Comparator local7 = new Comparator<CurrencyModel>() {
                public int compare(CurrencyModel paramAnonymousCurrencyModel1, CurrencyModel paramAnonymousCurrencyModel2) {
                    if (!paramAnonymousCurrencyModel1.getEnglishName().equals(paramAnonymousCurrencyModel2.getEnglishName()))
                        return paramAnonymousCurrencyModel1.getShortName().compareTo(paramAnonymousCurrencyModel2.getShortName());
                    return 0;
                }
            };
            Collections.sort(paramList, local7);
        }
    }

    protected int getLayoutResId() {
        return R.layout.activity_choose_currency_layout;
    }

    protected void initView() {
        this.currencyType = getIntent().getIntExtra("CHOOSE_CURRENCY_TYPE", 1);
        ProgressDialog localProgressDialog = new ProgressDialog(this);
        this.progressDialog = localProgressDialog;
        initTop();
        initListHeadView();
        CurrencyLoader localCurrencyLoader = new CurrencyLoader();
        localCurrencyLoader.execute();
    }

    private class CurrencyListAdapter extends BaseAdapter
            implements StickyListHeadersAdapter {
        private List<CurrencyModel> currencyModelList;
        private LayoutInflater mInflater;

        public CurrencyListAdapter(Context context, List<CurrencyModel> arg2) {
            this.mInflater = LayoutInflater.from(context);
            this.currencyModelList = arg2;
        }

        public int getCount() {
            return this.currencyModelList.size();
        }

        public long getHeaderId(int paramInt) {
            return getItem(paramInt).getShortName().subSequence(0, 1).charAt(0);
        }

        public View getHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            View localView = paramView;
            ChooseCurrencyActivity.HeaderViewHolder localHeaderViewHolder2;
            if (localView == null) {
                ChooseCurrencyActivity.HeaderViewHolder localHeaderViewHolder1 = new ChooseCurrencyActivity.HeaderViewHolder();
                localHeaderViewHolder2 = localHeaderViewHolder1;
                localView = this.mInflater.inflate(R.layout.choose_friends_head_layout, paramViewGroup, false);
                localHeaderViewHolder2.text = ((TextView) localView.findViewById(R.id.text1));
                localView.setTag(localHeaderViewHolder2);
            } else {
                localHeaderViewHolder2 = (ChooseCurrencyActivity.HeaderViewHolder) localView.getTag();
            }

            TextView localTextView = localHeaderViewHolder2.text;
            localTextView.setText("" + getItem(paramInt).getShortName().subSequence(0, 1).charAt(0));

            return localView;
        }

        public CurrencyModel getItem(int paramInt) {
            return (CurrencyModel) this.currencyModelList.get(paramInt);
        }

        public long getItemId(int paramInt) {
            return paramInt;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            View localView = paramView;
            ChooseCurrencyActivity.CurrencyViewHolder localCurrencyViewHolder2;
            if (localView == null) {
                ChooseCurrencyActivity.CurrencyViewHolder localCurrencyViewHolder1 = new ChooseCurrencyActivity.CurrencyViewHolder();
                localCurrencyViewHolder2 = localCurrencyViewHolder1;
                localView = this.mInflater.inflate(R.layout.custom_choose_currency_listitem_layout, paramViewGroup, false);

                localCurrencyViewHolder2.shortName = (TextView) localView.findViewById(R.id.choose_currency_listItem_short);
                localCurrencyViewHolder2.chineseName = (TextView) localView.findViewById(R.id.choose_currency_listItem_chineseName);
                localCurrencyViewHolder2.symbol = (TextView) localView.findViewById(R.id.choose_currency_listItem_symbol);
                localCurrencyViewHolder2.line = (TextView) localView.findViewById(R.id.choose_currency_line);
                localView.setTag(localCurrencyViewHolder2);

            } else {
                localCurrencyViewHolder2 = (ChooseCurrencyActivity.CurrencyViewHolder) localView.getTag();
            }

            CurrencyModel localCurrencyModel = getItem(paramInt);
            localCurrencyViewHolder2.shortName.setText(localCurrencyModel.getShortName());
            localCurrencyViewHolder2.chineseName.setText(localCurrencyModel.getCurrencyName());
            localCurrencyViewHolder2.symbol.setText("(" + localCurrencyModel.getSymbol() + ")");

            if (paramInt == -1 + getCount()) {
                localCurrencyViewHolder2.line.setVisibility(View.GONE);
            }

            if (getHeaderId(paramInt) != getHeaderId(paramInt + 1))
                localCurrencyViewHolder2.line.setVisibility(View.GONE);
            else
                localCurrencyViewHolder2.line.setVisibility(View.VISIBLE);

            return localView;
        }
    }

    private class CurrencyLoader extends AsyncTask<Void, Void, List<CurrencyModel>> {
        private CurrencyLoader() {
        }

        protected List<CurrencyModel> doInBackground(Void[] paramArrayOfVoid) {
            return ChooseCurrencyActivity.this.getXlsData("currency_symbol_info.xls", 0);
        }

        protected void onPostExecute(List<CurrencyModel> paramList) {
            if ((ChooseCurrencyActivity.this.progressDialog != null) && (ChooseCurrencyActivity.this.progressDialog.isShowing()))
                ChooseCurrencyActivity.this.progressDialog.dismiss();
            if (paramList != null) {
                ChooseCurrencyActivity.this.sortByName(paramList);
                ChooseCurrencyActivity.this.setupData(paramList);
            }
        }

        protected void onPreExecute() {
            ChooseCurrencyActivity.this.progressDialog.setMessage("加载中,请稍后......");
            ChooseCurrencyActivity.this.progressDialog.setCanceledOnTouchOutside(false);
            ChooseCurrencyActivity.this.progressDialog.show();
        }
    }

    class CurrencyViewHolder {
        private TextView chineseName;
        private TextView line;
        private TextView shortName;
        private TextView symbol;

        CurrencyViewHolder() {
        }
    }

    class HeaderViewHolder {
        TextView text;

    }
}