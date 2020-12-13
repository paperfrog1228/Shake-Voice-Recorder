package com.example.svr;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
public class RecordAdapter extends BaseAdapter {
    public ArrayList<RecordItem> items = new ArrayList<RecordItem>();
    @Override
    public int getCount() {
        return items.size();
    }
    public void addItem(RecordItem item) {
        items.add(item);
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
        view.setName(item.getName());
        view.setDate(item.getDate());
        view.setLength(item.getLength());

        return view;
    }
}

