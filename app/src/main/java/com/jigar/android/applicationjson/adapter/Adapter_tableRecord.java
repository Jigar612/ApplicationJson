package com.jigar.android.applicationjson.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jigar.android.applicationjson.R;
import com.jigar.android.applicationjson.models.TableData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP11 on 03-Dec-18.
 */

public class Adapter_tableRecord extends BaseAdapter {
    List<TableData>list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;

    public Adapter_tableRecord(List<TableData> list, Context context, LayoutInflater layoutInflater) {
        this.list = list;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.list_data,null);

        TextView tv_tabmle_no = convertView.findViewById(R.id.tv_table_no);
        TextView tv_table_name = convertView.findViewById(R.id.tv_table_name);


        String[] table_getItemNm = new String[list.size()];
        String[] table_getPrice = new String[list.size()];

        String price = list.get(position).getPrice();
        String name = list.get(position).getItem_name();


        tv_table_name.setText(name);
        tv_tabmle_no.setText(price);



        return convertView;
    }
}
