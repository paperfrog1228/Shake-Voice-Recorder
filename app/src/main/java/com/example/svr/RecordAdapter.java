package com.example.svr;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.example.svr.fragment.RecordListFrag;

import java.util.ArrayList;
public class RecordAdapter extends BaseAdapter {
    public ArrayList<RecordItem> items = new ArrayList<RecordItem>();
    RecordListFrag fragment;
    @Override
    public int getCount() {
        return items.size();
    }
    public void addItem(String name, String date, String path,int bookmark) {
        RecordItem item=new RecordItem(name,date,path,bookmark);
        items.add(item);
    }
    public RecordAdapter (RecordListFrag fragment)
    {
        this.fragment = fragment;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        RecordItemView view = new RecordItemView(viewGroup.getContext());
        RecordItem item = items.get(position);
        ImageButton btn_delete = view.findViewById(R.id.btn_delete);
        ToggleButton btn_bookmark=view.findViewById(R.id.btn_bookmark);
        if(item.isBookmarked())
            btn_bookmark.setChecked(true);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordDB.getInstance().deleteRecord(item.getDate());
                fragment.deleteRecord();
            }
        });
        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordDB.getInstance().bookmarkRecord(item.getDate());
                item.setBookmarked();
            }
        });
        view.setDate(item.getDate());
        view.setLength(item.getPath());
        return view;
    }
}

