package com.example.svr.fragment;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.svr.MainActivity;
import com.example.svr.R;
import com.example.svr.RecordAdapter;
import com.example.svr.RecordDB;
import com.example.svr.RecordItem;

public class RecordListFrag extends Fragment {
    RecordAdapter adapter;
    ListView listView;
    FragmentTransaction ft;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.record_list_frag, container, false);
        listView =  rootView.findViewById(R.id.RecordListView);
        adapter = new RecordAdapter(this);
        ft = getFragmentManager().beginTransaction();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecordItem itme=(RecordItem)parent.getItemAtPosition(position);
                PopUpPlayer(itme);
            }
        });
        listView.setAdapter(adapter);
        updateListview();
        return rootView;
    }
    private void updateListview(){
        if(RecordDB.getInstance().DB != null){
            adapter.items.clear();
            String sql = "select * from record";
            Cursor cursor = RecordDB.getInstance().DB.rawQuery(sql, null);
            cursor.moveToFirst();
            for( int i = 0; i< cursor.getCount(); i++){
                System.out.println("1: "+cursor.getString(1)+" 2: "+cursor.getString(2)+"3 : "+cursor.getString(3));
                String name = cursor.getString(1);
                String date = cursor.getString(2);
                String path = cursor.getString(3);
                int bookmark= cursor.getInt(4);
                adapter.addItem(name,date, path,bookmark);
                cursor.moveToNext();
            }
            cursor.close();
        }
    }
    public void deleteRecord(){
        ft.detach(this).attach(this).commit();
        Toast.makeText(getActivity(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }
    public void PopUpPlayer(RecordItem recordItem){
        PlayerDialogFrag e = new PlayerDialogFrag(recordItem);
        e.show(getFragmentManager(),PlayerDialogFrag.TAG_EVENT_DIALOG);
    }
}
