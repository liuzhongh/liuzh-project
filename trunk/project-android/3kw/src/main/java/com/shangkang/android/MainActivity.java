package com.shangkang.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.shangkang.bo.CommonType;
import com.shangkang.core.bo.Pagination;
import com.shangkang.dto.AutoCompletedDto;
import com.shangkang.dto.ProductDetailDto;
import com.shangkang.helper.CacheDataHelper;
import com.shangkang.helper.CacheHelper;
import com.shangkang.remote.ProductSearchRemote;
import com.shangkang.tools.UtilHelper;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private ListView productList;
    private AutoCompleteTextView autoCompleteTextView;
    private Button searchBtn;
    private TextView filterView;
    private ListView filterList;
    private static final String TAG = "MainActivity";
    private ProductAdapter adapter;
    //用来判断是否加载完成
    private boolean loadFinish = true;
    private int pageNo = 1;// 从第1页开始计数
    private int size = 10;// 每次下载十条数据
    private Map<String, String> queryCondition = new HashMap<String, String>();
    private View loadView;
    private int totalPage;
    private TextView clearView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        productList = (ListView) findViewById(R.id.product_list);
        filterView = (TextView) findViewById(R.id.editText);
        filterList = (ListView) findViewById(R.id.filter_list);
        clearView = (TextView) findViewById(R.id.clear_text);

        CacheDataHelper.getInstance().init(getBaseContext());

        /*autoList = (ListView) findViewById(R.id.auto_list);
        searchView = (SearchView) findViewById(R.id.searchView);

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                if(autoList.getVisibility() != View.GONE)
                    autoList.setVisibility(View.GONE);

                new LoadProductList().execute();
                System.out.println("sub ");

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if(!UtilHelper.isEmpty(s))
                {
                    new AutoCompleteList().execute();
                }else
                {
                    autoList.setVisibility(View.GONE);
                }
                System.out.println("change ");

                return false;
            }

        });

        autoList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String val = (String) MainActivity.this.autoList.getAdapter().getItem(i);
                val = val.substring(0, val.indexOf("　　"));
                searchView.setQuery(val, true);
                autoList.setVisibility(View.GONE);
            }

        });*/

        searchBtn = (Button) findViewById(R.id.button);

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                new AutoCompleteList().execute();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        autoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String val = (String) MainActivity.this.autoCompleteTextView.getAdapter().getItem(i);
                val = val.substring(0, val.indexOf("　　"));

                autoCompleteTextView.setText(val);
                autoCompleteTextView.clearListSelection();
            }
        });

        loadView = getLayoutInflater().inflate(R.layout.page_progress_bar, null);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != filterList) {
                    Log.d(TAG, "筛选框视图是否可见:" + (View.VISIBLE == filterList.getVisibility()));
                    if (filterList.getVisibility() != View.GONE) {
                        filterList.setVisibility(View.GONE);
                        filterView.setTextColor(getResources().getColor(R.color.org));
                    }
                }
                new LoadProductList().execute();
            }
        });

        filterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterList.getVisibility() != View.GONE) {
                    filterList.setVisibility(View.GONE);
                    filterView.setTextColor(getResources().getColor(R.color.org));
                } else {
                    filterList.setVisibility(View.VISIBLE);
                    filterView.setTextColor(getResources().getColor(R.color.blk));
                    new LoadFilterList().execute();
                }
            }
        });

        productList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView v, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                Log.i(TAG, "Scroll>>>first: " + firstVisibleItem + ", visible: " + visibleItemCount + ",        total: " + totalItemCount);

                //得到listview最后一项的id
                int lastItemId = productList.getLastVisiblePosition();
                //判断用户是否滑动到最后一项，因为索引值从零开始所以要加上1
                if ((lastItemId + 1) == totalItemCount) {
                    /**
                     * 计算当前页，因为每一页只加载十条数据，所以总共加载的数据除以每一页的数据的个数
                     * 如果余数为零则当前页为currentPage=totalItemCount/number；
                     * 如果不能整除则当前页为(int)(totalItemCount/number)+1;
                     * 下一页则是当前页加1
                     */
                    int currentPage = totalItemCount % size;
                    if (currentPage == 0) {
                        currentPage = totalItemCount / size;
                    } else {
                        currentPage = (int) (totalItemCount / size) + 1;
                    }
                    Log.d(TAG, "当前页为：" + currentPage);
                    pageNo = currentPage + 1;
                    //当总共的数据大于0时才加载数据
                    if (totalItemCount > 0) {
                        //判断当前页是否超过最大页，以及上一页的数据是否加载完成
                        if (pageNo <= totalPage && loadFinish) {
                            //添加页脚视图
                            productList.addFooterView(loadView);

                            loadFinish = false;
                            new LoadProductList4Scroll().execute();
                        }
                    }

                }
                //判断加载的数据的页数有没有超过最大页，并且是否已经记载完成

            }

        });

        clearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "清除数据: ");
                autoCompleteTextView.setText("");
                queryCondition.clear();
                Log.d(TAG, "清除数据: " + autoCompleteTextView.getText() + " -- " + queryCondition);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    class AutoCompleteList extends AsyncTask<String, String, String> {
        private List<AutoCompletedDto> dtos;

        @Override
        protected String doInBackground(String... strings) {
            ProductSearchRemote productSearchRemote = new ProductSearchRemote();

            dtos = productSearchRemote.loadProductNamesResult(autoCompleteTextView.getText().toString());
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            ArrayAdapter<String> promtAdapter;

            if (!UtilHelper.isEmpty(dtos)) {
                promtAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.default_dropdown_item);

                for (AutoCompletedDto dto : dtos) {
                    promtAdapter.add(dto.getInfoContent());
                }

                autoCompleteTextView.setAdapter(promtAdapter);

                if (autoCompleteTextView.getText().toString().length() > 0) {
                    autoCompleteTextView.showDropDown();
                }
            }
        }
    }

    class LoadProductList extends AsyncTask<String, String, String> {

        private List<ProductDetailDto> productDetailDtos;
        private ProgressDialog pDialog;

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("数据加载中 ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            ProductSearchRemote productSearchRemote = new ProductSearchRemote();

            Pagination<ProductDetailDto> pagination = new Pagination<ProductDetailDto>();

            pageNo = 1;
            pagination.setPageNo(pageNo);
            pagination.setPageSize(size);

            queryCondition.put("keyword", autoCompleteTextView.getText().toString());

            Log.d(TAG, "queryCondition = " + queryCondition);
            Log.d(TAG, "autoCompleteTextView = " + autoCompleteTextView);

            pagination = productSearchRemote.loadProductDtosResult(pagination, queryCondition);

            productDetailDtos = pagination.getResultList();
            totalPage = pagination.getTotalPage();
            Log.i(TAG, " total : " + pagination.getTotal());
            Log.d(TAG, "total page : " + totalPage);
            Log.i(TAG, "current size: " + productDetailDtos.size());

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

           /* runOnUiThread(new Runnable() {

                @Override
                public void run() {*/
            pDialog.dismiss();
            if (!UtilHelper.isEmpty(productDetailDtos)) {
                adapter = new ProductAdapter(getApplicationContext(), R.layout.product_list, productDetailDtos);

                pageNo += 1;
                productList.addFooterView(loadView);
                productList.setAdapter(adapter);
                productList.removeFooterView(loadView);
            }

//                }
//            });
        }

    }

    class LoadProductList4Scroll extends AsyncTask<String, String, String> {

        private List<ProductDetailDto> productDetailDtos;

        @Override
        protected String doInBackground(String... params) {

            ProductSearchRemote productSearchRemote = new ProductSearchRemote();

            Pagination<ProductDetailDto> pagination = new Pagination<ProductDetailDto>();

            pagination.setPageNo(pageNo);
            pagination.setPageSize(size);

            Log.d(TAG, "当前查询页为：" + pageNo + ",执行查询中...");
            queryCondition.put("keyword", autoCompleteTextView.getText().toString());

            pagination = productSearchRemote.loadProductDtosResult(pagination, queryCondition);

            Log.d(TAG, "result total :" + pagination.getTotal());

            productDetailDtos = pagination.getResultList();

            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            adapter.addAll(productDetailDtos);

            adapter.notifyDataSetChanged();
            loadFinish = true;
            if (productList.getFooterViewsCount() != 0) {
                productList.removeFooterView(loadView);
            }
            setViewStatus(true);
        }

        private void setViewStatus(boolean clickable) {
            searchBtn.setEnabled(clickable);
            autoCompleteTextView.setEnabled(clickable);
            filterView.setEnabled(clickable);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setViewStatus(false);
        }
    }

    class LoadFilterList extends AsyncTask<String, String, String> {
        private List<CommonType> areas;
        private List<CommonType> productTypes;
        private ProgressDialog pDialog;

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("数据加载中 ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            ProductSearchRemote productSearchRemote = new ProductSearchRemote();

            /*SoftReference softAreas = (SoftReference) CacheHelper.getSingleton().getCache().get("areas");
            SoftReference softProduct = (SoftReference) CacheHelper.getSingleton().getCache().get("productTypes");

            if (softAreas != null)
                areas = (List<CommonType>) softAreas.get();
            if (softProduct != null)
                productTypes = (List<CommonType>) softProduct.get();*/

            areas = (List<CommonType>) CacheDataHelper.getInstance().get("areas");
            productTypes = (List<CommonType>) CacheDataHelper.getInstance().get("productTypes");

            if (UtilHelper.isEmpty(areas)) {
                areas = productSearchRemote.listAreas();
                CacheDataHelper.getInstance().put("areas", areas);
            }

            if (UtilHelper.isEmpty(productTypes)) {
                productTypes = productSearchRemote.queryAllProductTypeCommonType();
                CacheDataHelper.getInstance().put("productTypes", productTypes);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            pDialog.dismiss();

            if (!UtilHelper.isEmpty(areas)) {
                FilterAdapter adapter = new FilterAdapter(getApplicationContext(), getBaseContext().getResources(), areas, productTypes, queryCondition);

                filterList.setAdapter(adapter);
            }
            super.onPostExecute(s);
        }
    }

}
