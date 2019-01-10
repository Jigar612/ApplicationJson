package com.jigar.android.applicationjson.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by COMP11 on 03-Dec-18.
 */

public class GetTableResponse {

  @SerializedName("TabelsRecord")
  private List<TableData> tableRecord;

    public void setTableRecord(List<TableData> tableRecord) {
        this.tableRecord = tableRecord;
    }

    public List<TableData> getTableRecord() {
        return tableRecord;
    }


}
