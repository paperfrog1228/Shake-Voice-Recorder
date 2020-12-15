package com.example.svr;

import com.example.svr.RecordDB;

public class RecordItem {
    String name;
    String date;
    String length;
    String path;
    public RecordItem(String name, String date,String path) {
        this.name = name;
        this.date = date;
        this.path=path;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public String getPath() { return path; }
    public void setName(String name) { this.name = name; }
    public void setDate(String date) { this.date = date; }
    public void setLength(String length) { this.length = length; }
}
