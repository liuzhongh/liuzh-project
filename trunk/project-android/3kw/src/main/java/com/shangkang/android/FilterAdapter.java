package com.shangkang.android;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shangkang.bo.CommonType;
import com.shangkang.tools.UtilHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by liuzh on 13-9-23.
 */
public class FilterAdapter extends BaseAdapter{
    private LayoutInflater mLayoutInflater;
    private List<CommonType> areas;
    private List<CommonType> productTypes;
    private Context context;
    private Resources resources;
    private Map<String, String> queryCondition;

    public FilterAdapter(Context context, Resources resources, List<CommonType> areas, List<CommonType> productTypes, Map<String, String> queryCondition) {

        mLayoutInflater = LayoutInflater.from(context);
        this.areas = areas;
        this.context = context;
        this.productTypes = productTypes;
        this.resources = resources;
        this.queryCondition = queryCondition;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.filter_list, null);


        if(convertView.getVisibility() != View.VISIBLE)
        {
            convertView.setVisibility(View.VISIBLE);
        }
        RadioGroup radioGroup1 = (RadioGroup) convertView.findViewById(R.id.radioGroup);
        RadioGroup radioGroup2 = (RadioGroup) convertView.findViewById(R.id.radioGroup2);
//        RadioGroup radioGroup3 = (RadioGroup) convertView.findViewById(R.id.radioGroup3);

        String provinceCode = queryCondition.get("provinceCode");
        String productType = queryCondition.get("productType");

        for(CommonType commonType : areas)
        {
            RadioButton radioButton = new RadioButton(this.context);

            radioButton.setId(commonType.getInfoId().intValue());
            radioButton.setText(commonType.getInfoName());
            setRadioBtnStyle(radioButton, provinceCode, commonType.getInfoCode());
            radioButton.setTag(commonType);

            radioGroup1.addView(radioButton);
        }

        if(!UtilHelper.isEmpty(productTypes))
        {
            for (CommonType commonType : productTypes)
            {
                RadioButton rb = new RadioButton(this.context);

                rb.setId(commonType.getInfoId().intValue());
                rb.setText(commonType.getInfoName());
                setRadioBtnStyle(rb, productType, commonType.getInfoCode());
                rb.setTag(commonType);

                radioGroup2.addView(rb);
            }
        }

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(i);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    CommonType ct = (CommonType) checkedRadioButton.getTag();

                    if(ct != null)
                        queryCondition.put("provinceCode", ct.getInfoCode());
                    else
                        queryCondition.remove("provinceCode");
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(i);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    CommonType ct = (CommonType) checkedRadioButton.getTag();

                    if(ct != null)
                        queryCondition.put("productType", ct.getInfoCode());
                    else
                        queryCondition.remove("productType");
                }
            }
        });

        return convertView;
    }

    private void setRadioBtnStyle(RadioButton radioButton, String code, String code1) {
        radioButton.setPadding(10, 0, 0, 0);
        radioButton.setTextSize(10);
        radioButton.setButtonDrawable(android.R.color.transparent);
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    compoundButton.setTextColor(resources.getColor(R.color.blk));
                else
                    compoundButton.setTextColor(resources.getColor(R.color.def));
            }
        });
        radioButton.setTextColor(resources.getColor(R.color.def));
        if(!UtilHelper.isEmpty(code) && code.equals(code1))
        {
            radioButton.setChecked(true);
        }
    }
}
