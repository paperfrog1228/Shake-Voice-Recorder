package com.example.svr;

import com.example.svr.RecordDB;

public class RecordItem {
    String name;
    String date;
    String length;
    String path;
    boolean bookmarked=false;
    public RecordItem(String name, String date,String path,int bookmark) {
        this.name = name;
        this.date = date;
        this.path=path;
        if(bookmark==1)
            this.bookmarked=true;
        else
            this.bookmarked=false;
    }
    public boolean isBookmarked(){ return bookmarked;}
    public void setBookmarked(){
        if(bookmarked==true)
            bookmarked=false;
        else
            bookmarked=true;
    }
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getPath() { return path; }
    public void setName(String name) { this.name = name; }
    public void setDate(String date) { this.date = date; }
    public void setLength(String length) { this.length = length; }

}
