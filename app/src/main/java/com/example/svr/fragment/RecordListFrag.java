package com.example.svr.fragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
        SearchView searchView = rootView.findViewById(R.id.searchView);
        adapter = new RecordAdapter(this);
        ft = getFragmentManager().beginTransaction();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecordItem itme=(RecordItem)parent.getItemAtPosition(position);
                PopUpPlayer(itme);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                updateSearchListView(newText);
                return false;
            }
        });
        listView.setAdapter(adapter);
        updateListView();
        return rootView;
    }
    private void updateListView(){
        if(RecordDB.getInstance().DB != null){
            adapter.items.clear();
            String sql = "select * from record";
            Cursor cursor = RecordDB.getInstance().DB.rawQuery(sql, null);
            cursor.moveToFirst();
            for( int i = 0; i< cursor.getCount(); i++){
                System.out.println("1: "+cursor.getString(1)+" 2: "+cursor.getString(2)+"3 : "+cursor.getString(3));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String path = cursor.getString(cursor.getColumnIndex("path"));
                String length = cursor.getString(cursor.getColumnIndex("length"));
                int bookmark= cursor.getInt(cursor.getColumnIndex("bookmark"));
                adapter.addItem(name,date, length,path,bookmark);
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
        getFragmentManager().executePendingTransactions();
        e.getDialog().setOnDismissListener(
                new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        e.endPlayer();
                    }
                });
    }
    private void updateSearchListView(String newText){
        if(RecordDB.getInstance().DB != null){
            adapter.items.clear();
            String sql = "select * from record where name like '%"+newText+"%'";
            Cursor cursor = RecordDB.getInstance().DB.rawQuery(sql, null);
            cursor.moveToFirst();
            for( int i = 0; i< cursor.getCount(); i++){
                System.out.println("1: "+cursor.getString(1)+" 2: "+cursor.getString(2)+"3 : "+cursor.getString(3));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String path = cursor.getString(cursor.getColumnIndex("path"));
                String length = cursor.getString(cursor.getColumnIndex("length"));
                int bookmark= cursor.getInt(cursor.getColumnIndex("bookmark"));
                adapter.addItem(name,date, length,path,bookmark);
                cursor.moveToNext();
            }
            cursor.close();
        }
    }
}
