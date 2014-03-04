package org.news.activity;

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

import com.insthub.BeeFramework.activity.BaseActivity;
import org.json.JSONException;
import org.json.JSONObject;
import org.news.R;
import org.news.model.ProtocolConst;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.external.androidquery.callback.AjaxStatus;
import com.insthub.BeeFramework.model.BusinessResponse;
public class HelpWebActivity extends BaseActivity implements BusinessResponse {

	
	private TextView title;
	private ImageView back;
	private WebView webView;
    private ProgressDialog pro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.helpweb);

        Intent intent = getIntent();
//		int id = intent.getIntExtra("id", 0);
		String t = intent.getStringExtra("title");
        String url1 = intent.getStringExtra("url");
		
		title = (TextView) findViewById(R.id.top_view_text);
		title.setText(t);
		
		back = (ImageView) findViewById(R.id.top_view_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		webView = (WebView) findViewById(R.id.help_web);

        pro = new ProgressDialog(this){
            @Override
            public boolean onKeyDown(int keyCode, KeyEvent event)
            {
               /* if (keyCode == KeyEvent.KEYCODE_BACK)
                {
                    webView.stopLoading(); //停止加载
                    pro.dismiss();
                    HelpWebActivity.this.finish();
                }*/
                return super.onKeyDown(keyCode, event);
            }

        };

        /*webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                HelpWebActivity.this.setProgress(newProgress * 100);
                super.onProgressChanged(view, newProgress);
            }
        });*/

        webView.loadUrl(url1);
		
		webView.setWebViewClient(new WebViewClient() { // 通过webView打开链接，不调用系统浏览器

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}

            @Override
            public void onPageFinished(WebView view, String url)
            {
                pro.dismiss();
                super.onPageFinished(view, url);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                pro.show();
                super.onPageStarted(view, url, favicon);
            }
		});

		webView.setInitialScale(25);

		WebSettings webSettings = webView.getSettings();  
		webSettings.setJavaScriptEnabled(true); 
		webSettings.setBuiltInZoomControls(true);
		//webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setSupportZoom(true);
		
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		

	}

	@Override
	public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		// TODO Auto-generated method stub
		
		if(url.endsWith(ProtocolConst.ARTICLE)) {
			

		}
		
	}
	
}
