package org.news.adapter;

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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.insthub.BeeFramework.adapter.BeeBaseAdapter;

import org.news.R;
import org.news.component.ShopHelpCell;
import org.news.model.HelpModel;
import org.news.protocol.SHOPHELP;

import java.util.ArrayList;

public class HelpListAdapter extends BeeBaseAdapter
{
    protected static final int TYPE_HELPSELL = 2;

    private HelpModel dataModel;

    public HelpListAdapter(Context c, ArrayList dataList) {
        super(c, dataList);
    }

    public HelpListAdapter(Context c, HelpModel dataModel) {
        super(c, dataModel.shophelpsList);
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
        return dataList.size();
    }

    public int getItemViewType(int position)
    {
        return position;
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

        if (null == cellView )
        {
        }
        else
        {
            return cellView;
        }

        cellView = (ShopHelpCell)LayoutInflater.from(mContext).inflate(R.layout.shophelp_cell, null);

        final SHOPHELP shophelp = (SHOPHELP)dataList.get(position);
        ((ShopHelpCell)cellView).bindData(shophelp, mContext, shophelp, position);

        return cellView;
    }

}

