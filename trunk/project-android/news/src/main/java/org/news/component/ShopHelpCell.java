package org.news.component;

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

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.news.R;
import org.news.activity.HelpActivity;
import org.news.activity.HelpWebActivity;
import org.news.protocol.ARTICLE;
import org.news.protocol.SHOPHELP;


public class ShopHelpCell extends LinearLayout{

    Context mContext;
    TextView shophelp_content;
    LinearLayout shophelp_item;
    public List<ARTICLE> list;

    public ShopHelpCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    void init()
    {
        if (null == shophelp_content)
        {
            shophelp_content = (TextView)findViewById(R.id.shophelp_content);
        }
        
        if (null == shophelp_item)
        {
        	shophelp_item = (LinearLayout)findViewById(R.id.shophelp_item);
        }
    }

    public void bindData(final SHOPHELP shophelp, final Context context,final SHOPHELP data,final int realPosition)
    {
        init();
        shophelp_content.setText(shophelp.name);
        shophelp_item.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(context, HelpWebActivity.class);
                System.out.println(shophelp);
				intent.putExtra("position", 0);
                System.out.println(shophelp.article.get(0));
				intent.putExtra("title", shophelp.article.get(0).short_title);
                intent.putExtra("url", shophelp.article.get(0).title);
				context.startActivity(intent);
			
			}
		});
       
    }
}
