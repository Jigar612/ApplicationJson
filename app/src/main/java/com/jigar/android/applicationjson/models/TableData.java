package com.jigar.android.applicationjson.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by COMP11 on 03-Dec-18.
 */

public class TableData {
    @SerializedName("id")
    String id;
    @SerializedName("tabel_no")
    String tabel_no;
    @SerializedName("date")
    String date;
    @SerializedName("category")
    String category;
    @SerializedName("price")
    String price;
    @SerializedName("qty")
    String qty;
    @SerializedName("total")
    String total;
    @SerializedName("status")
    String status;
    @SerializedName("placed_order")
    String placed_order;
    @SerializedName("note")
    String note;
    @SerializedName("item_name")
    String item_name;

    public String getId() {
        return id;
    }

    public String getTabel_no() {
        return tabel_no;
    }

    public String getDate() {
        return date;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getQty() {
        return qty;
    }

    public String getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public String getPlaced_order() {
        return placed_order;
    }

    public String getNote() {
        return note;
    }



}
