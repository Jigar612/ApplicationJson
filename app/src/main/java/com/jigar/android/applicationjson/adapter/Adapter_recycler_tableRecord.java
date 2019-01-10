package com.jigar.android.applicationjson.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jigar.android.applicationjson.R;
import com.jigar.android.applicationjson.models.TableData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP11 on 04-Dec-18.
 */

public class Adapter_recycler_tableRecord extends RecyclerView.Adapter<Adapter_recycler_tableRecord.ViewHolder> {

    private List<TableData> list = new ArrayList<>();
    private Context mContext;

    public Adapter_recycler_tableRecord(List<TableData> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_price;
        public TextView tv_items;


        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            tv_price = (TextView) itemView.findViewById(R.id.tv_table_no);
            tv_items = (TextView) itemView.findViewById(R.id.tv_table_name);

            mContext= itemView.getContext();
        }
    }

    @NonNull
    @Override
    public Adapter_recycler_tableRecord.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_data, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_recycler_tableRecord.ViewHolder holder, int position) {
        TableData tableData = list.get(position);

        TextView tv_items = holder.tv_items;
        TextView tv_price = holder.tv_price;

        String aa= tableData.getItem_name();

        String items = list.get(position).getItem_name();
        String price = list.get(position).getPrice();

        tv_items.setText(items);
        tv_price.setText(price);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
