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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.insthub.BeeFramework.adapter.BeeBaseAdapter;
import com.insthub.ecmobile.R;
import com.insthub.ecmobile.component.HotSellingCell;
import com.insthub.ecmobile.component.SpecificationCell;
import com.insthub.ecmobile.protocol.SPECIFICATION;

import java.util.ArrayList;

public class SpecificationAdapter extends BeeBaseAdapter
{
    private Context mContext;
    public SpecificationAdapter(Context c, ArrayList dataList) {
        super(c, dataList);
        mContext = c;
    }

    @Override
    protected BeeCellHolder createCellHolder(View cellView) {
        return null;
    }

    @Override
    protected View bindData(int position, View cellView, ViewGroup parent, BeeCellHolder h) {
        SPECIFICATION specification = (SPECIFICATION)dataList.get(position);
        ((SpecificationCell)cellView).bindData(specification);
        return cellView;
    }

    @Override
    public View createCellView() {
        return LayoutInflater.from(mContext).inflate(R.layout.specification_cell, null);
    }
}
