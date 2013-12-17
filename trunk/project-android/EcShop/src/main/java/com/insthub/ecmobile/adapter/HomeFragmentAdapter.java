package com.insthub.ecmobile.adapter;

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

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.insthub.BeeFramework.adapter.BeeBaseAdapter;
import com.insthub.BeeFramework.view.WebImageView;
import com.insthub.ecmobile.R;
import com.insthub.ecmobile.component.CategorySellingCell;
import com.insthub.ecmobile.component.HotSellingCell;
import com.insthub.ecmobile.component.ShopHelpCell;
import com.insthub.ecmobile.model.HomeModel;
import com.insthub.ecmobile.protocol.CATEGORYGOODS;
import com.insthub.ecmobile.protocol.SHOPHELP;
import com.insthub.ecmobile.protocol.SIMPLEGOODS;

public class HomeFragmentAdapter extends BeeBaseAdapter 
{
	protected static final int TYPE_HOTSELL = 0;
	protected static final int TYPE_CATEGORYSELL = 1;
	protected static final int TYPE_HELPSELL = 2;
	
	private HomeModel dataModel;
	
	public HomeFragmentAdapter(Context c, ArrayList dataList) {
		super(c, dataList);
	}
	
	public HomeFragmentAdapter(Context c, HomeModel dataModel) {
		super(c, dataModel.playersList);
		this.dataModel = dataModel;
	}

	@Override
	protected BeeCellHolder createCellHolder(View cellView) {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Override
    public int getViewTypeCount() {
        
        return 1000;
    }
    
    @Override
    public int getCount() 
    {
    	return (int)Math.ceil(dataModel.simplegoodsList.size()*1.0/2)  + dataModel.categorygoodsList.size();
    }
    
    public int getItemViewType(int position)
    {    	
        return position;
    }


    public int getItemViewRealType(int position)
    {
        if (position < (int)Math.ceil(dataModel.simplegoodsList.size()*1.0/2))
        {
            return TYPE_HOTSELL;
        }
        else  if (position < (int)Math.ceil(dataModel.simplegoodsList.size()*1.0/2) + dataModel.categorygoodsList.size())
        {
            return TYPE_CATEGORYSELL;
        }
        else
        {
            return TYPE_HELPSELL;
        }

    }

    @Override
    public long getItemId(int position) {
        if (position < (int)Math.ceil(dataModel.simplegoodsList.size()*1.0/2))
        {
            return position;
        }
        else  if (position < (int)Math.ceil(dataModel.simplegoodsList.size()*1.0/2) + dataModel.categorygoodsList.size())
        {
            return position - (int)Math.ceil(dataModel.simplegoodsList.size()*1.0/2);
        }
        else
        {
            return position - (int)Math.ceil(dataModel.simplegoodsList.size()*1.0/2)- dataModel.categorygoodsList.size();
        }
    }

    @Override
	protected View bindData(int position, View cellView, ViewGroup parent,
			BeeCellHolder h) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View createCellView() {
		// TODO Auto-generated method stub
		return null;
	}
    @Override
    public View getView(final int position, View cellView, ViewGroup parent) {

        BeeCellHolder holder = null;

        if (TYPE_HOTSELL== getItemViewRealType(position))
        {

            if (null == cellView || cellView.getClass()!= HotSellingCell.class)
            {
                cellView = (HotSellingCell)LayoutInflater.from(mContext).inflate(R.layout.hotselling_cell, null);
            }
            else
            {
                return cellView;
            }

            List<SIMPLEGOODS> itemList = null;

            int distance =  dataModel.simplegoodsList.size() - position*2;
            int cellCount = distance >= 2? 2:distance;

            itemList = dataModel.simplegoodsList.subList(position*2,position*2+cellCount);
            ((HotSellingCell)cellView).bindData(itemList);

		}    
        else if (TYPE_CATEGORYSELL == getItemViewRealType(position))
        {
            if (null == cellView || cellView.getClass()!= CategorySellingCell.class)
            {
                cellView = (CategorySellingCell)LayoutInflater.from(mContext).inflate(R.layout.categoryselling_cell, null);
            }
            else
            {
                return cellView;
            }

            List<SIMPLEGOODS> itemList = null;


            int realPosition = position -  (int)Math.ceil(dataModel.simplegoodsList.size()*1.0/2);

            CATEGORYGOODS categorygoods =  dataModel.categorygoodsList.get(realPosition);

            ((CategorySellingCell)cellView).bindData(categorygoods);
            
		}

        return cellView;
    }

}
