package com.example.svr;

import com.example.svr.RecordDB;

public class RecordItem {
    String name;
    String date;
    String length;
    public RecordItem(String name, String date,String length) {
        this.name = name;
        this.date = date;
        this.length = length;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public String getLength() { return length; }
    public void setName(String name) { this.name = name; }
    public void setDate(String date) { this.date = date; }
    public void setLength(String length) { this.length = length; }
}
