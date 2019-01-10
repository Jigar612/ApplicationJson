package com.jigar.android.applicationjson.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jigar.android.applicationjson.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP11 on 08-Dec-18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    List<TableData>list= new ArrayList<>();

    public RecyclerAdapter(List<TableData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        String aaa = list.get(position).getItem_name().toString();
        holder.textView.setText(aaa);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);


            textView = (TextView) itemView.findViewById(R.id.tv_table_no);
        }
    }
}
