package org.news.model;

/*
 *
 *       _/_/_/                      _/        _/_/_/_/_/
 *    _/          _/_/      _/_/    _/  _/          _/      _/_/      _/_/
 *   _/  _/_/  _/_/_/_/  _/_/_/_/  _/_/          _/      _/    _/  _/    _/
 *  _/    _/  _/        _/        _/  _/      _/        _/    _/  _/    _/
 *   _/_/_/    _/_/_/    _/_/_/  _/    _/  _/_/_/_/_/    _/_/      _/_/
 *
 *
 *  Copyright 2013-2014, Geek Zoo Studio
 *  http://www.ecmobile.cn/license.html
 *
 *  HQ China:
 *    2319 Est.Tower Van Palace
 *    No.2 Guandongdian South Street
 *    Beijing , China
 *
 *  U.S. Office:
 *    One Park Place, Elmira College, NY, 14901, USA
 *
 *  QQ Group:   329673575
 *  BBS:        bbs.ecmobile.cn
 *  Fax:        +86-10-6561-5510
 *  Mail:       info@geek-zoo.com
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.external.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.insthub.BeeFramework.model.BaseModel;
import com.insthub.BeeFramework.model.BeeCallback;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.news.R;
import org.news.adapter.HelpListAdapter;
import org.news.base.BaseAsyncTask;
import org.news.protocol.SHOPHELP;
import org.news.protocol.STATUS;
import org.news.services.NewsService;

import java.io.*;
import java.util.ArrayList;


public class HelpModel extends BaseModel
{
    public ArrayList<SHOPHELP> shophelpsList = new ArrayList<SHOPHELP>();
    String pkName;

    public String rootpath;
    public HelpModel(Context context)
    {
        super(context);
        pkName = mContext.getPackageName();
        rootpath = context.getCacheDir()+"/news/cache" ;
//        readHelpDataCache();
    }

    public void readHelpDataCache() {
        String path = rootpath+"/"+pkName+"/helpData.dat";
        File f1 = new File(path);
        try {
            InputStream is = new FileInputStream(f1);
            InputStreamReader input = new InputStreamReader(is, "UTF-8");
            BufferedReader bf = new BufferedReader(input);

            helpDataCache(bf.readLine());

            bf.close();
            input.close();
            is.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void helpDataCache(String result) {

        try {
            if (result != null) {
                JSONObject jsonObject = new JSONObject(result);

                STATUS responseStatus = STATUS.fromJson(jsonObject.optJSONObject("status"));
                if (responseStatus.succeed == 1) {
                    fileSave(jsonObject.toString(), "helpData");
                    JSONArray shopHelpJsonArray = jsonObject.optJSONArray("data");
                    data = jsonObject.toString();
                    if (null != shopHelpJsonArray && shopHelpJsonArray.length() > 0) {
                        shophelpsList.clear();
                        for (int i = 0; i < shopHelpJsonArray.length(); i++) {
                            JSONObject shopHelpJsonObject = shopHelpJsonArray.getJSONObject(i);
                            SHOPHELP shopHelpItem = SHOPHELP.fromJson(shopHelpJsonObject);
                            shophelpsList.add(shopHelpItem);
                        }
                    }

                }

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // 缓存数据
    private PrintStream ps = null;
    public void fileSave(String result, String name) {

        String path = rootpath+"/"+pkName;

        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        File file = new File(filePath+"/"+name+".dat");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            ps = new PrintStream(fos);
            ps.print(result);
            ps.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String data;
    public void fetchShopHelp(final ListView helpListView)
    {
        String url = ProtocolConst.SHOPHELP;

        BaseAsyncTask<String, JSONArray> baseAsyncTask = new BaseAsyncTask<String, JSONArray>(this.mContext, "NEWS_SERVICE") {
            @Override
            protected JSONArray prepareResult(TProtocol protocol, String... param) throws Exception{

                NewsService.Client client = new NewsService.Client(protocol);

                String rs = client.findResult(param[0]);

                JSONArray ar = new JSONArray(rs);

                return ar;
            }

            @Override
            protected void executeResult(JSONArray jsonArray) {
                shophelpsList.clear();

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    try {
                        JSONObject shopHelpJsonObject = jsonArray.getJSONObject(i);

                        SHOPHELP shopHelpItem = SHOPHELP.fromJson(shopHelpJsonObject);
                        shophelpsList.add(shopHelpItem);

                        HelpListAdapter listAdapter = new HelpListAdapter(this.getContext(), shophelpsList);
                        helpListView.setAdapter(listAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        };

//        baseAsyncTask.setServicesName("NEWS_SERVICE");
        baseAsyncTask.execute("");

        /*BeeCallback<JSONArray> cb = new BeeCallback<JSONArray>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status) {
                try
                {
                    STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
                    if (responseStatus.succeed == 1)
                    {
                        fileSave(jo.toString(), "helpData");
                        JSONArray shopHelpJsonArray = jo.optJSONArray("data");
                        data = jo.toString();
                        if (null != shopHelpJsonArray && shopHelpJsonArray.length() > 0)
                        {
                            shophelpsList.clear();
                            for (int i = 0; i < shopHelpJsonArray.length(); i++)
                            {
                                JSONObject shopHelpJsonObject = shopHelpJsonArray.getJSONObject(i);
                                SHOPHELP shopHelpItem = SHOPHELP.fromJson(shopHelpJsonObject);
                                shophelpsList.add(shopHelpItem);
                            }
                            HelpModel.this.OnMessageResponse(url, jo, status);
                        }

                    }

                } catch (JSONException e) {
                    // TODO: handle exception
                }

            }

        };

        cb.url(url).type(JSONArray.class);
        ProgressDialog pd = new ProgressDialog(mContext);
        pd.setMessage("正在拉取...");
        aq.progress(pd).ajax(cb);*/

    }

}
