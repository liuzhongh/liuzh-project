package org.news.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.news.services.NewsService;

import java.lang.reflect.Constructor;

/**
 * Created by liuzh on 14-2-27.
 */
public abstract class BaseAsyncTask<Params, Result> extends AsyncTask<Params, Object, Result> {
    private Context context;
    private String host = "192.168.13.158";
    private int port = 9988;
    private String[] servicesName;
    private ProgressDialog pDialog;

    public BaseAsyncTask() {
    }

    public BaseAsyncTask(Context context) {
        this.context = context;
    }

    public BaseAsyncTask(Context context, String... servicesName) {
        this.context = context;
        this.servicesName = servicesName;
    }

    @Override
    protected Result doInBackground(Params... paramses) {

        TTransport transport = null;
        Result rs = null;

        try {
            transport = new TSocket(host, port);
            transport.open();
            //设置传输协议为TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);

            if(null != servicesName && servicesName.length == 1)
            {
                TMultiplexedProtocol processor = new TMultiplexedProtocol(protocol, servicesName[0]);

                rs = prepareResult(processor, paramses);
            }
            else
                rs = prepareResult(protocol, paramses);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != transport)
                transport.close();
        }
        return rs;
    }

    protected abstract Result prepareResult(TProtocol protocol, Params... paramses) throws Exception;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(null == pDialog)
        {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("正在拉取...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
        }

        pDialog.show();
    }

    @Override
    protected void onPostExecute(Result result) {
        executeResult(result);
        pDialog.dismiss();
        super.onPostExecute(result);
    }

    protected abstract void executeResult(Result result);

    public Context getContext() {
        return context;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String[] getServiceName() {
        return servicesName;
    }

    public void setServicesName(String... servicesName) {
        this.servicesName = servicesName;
    }

    public ProgressDialog getProgressDialog() {
        return pDialog;
    }

    public void setProgressDialog(ProgressDialog pDialog) {
        this.pDialog = pDialog;
    }
}
