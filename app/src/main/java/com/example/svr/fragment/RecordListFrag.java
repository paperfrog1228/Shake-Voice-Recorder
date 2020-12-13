package com.example.svr.fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.svr.MainActivity;
import com.example.svr.R;
import com.example.svr.RecordAdapter;
import com.example.svr.RecordDB;
import com.example.svr.RecordItem;

public class RecordListFrag extends Fragment {
    RecordAdapter adapter;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.record_list_frag, container, false);
        listView =  rootView.findViewById(R.id.RecordListView);
        adapter = new RecordAdapter();
        updateListview();
        listView.setAdapter(adapter);
        return rootView;
    }
    private void updateListview(){
        if(RecordDB.getInstance().DB != null){
            adapter.items.clear();
            String sql = "select name from record";
            Cursor cursor = RecordDB.getInstance().DB.rawQuery(sql, null);
            for( int i = 0; i< cursor.getCount(); i++){
                cursor.moveToNext();
                String name = cursor.getString(0);
                String date = cursor.getString(1);
                //String length = cursor.getInt(2);
                adapter.items.add(new RecordItem(name,date,"test"));
            }
            cursor.close();
        }
    }
}
