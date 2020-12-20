package com.example.svr;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.svr.fragment.BookmarkListFrag;
import com.example.svr.fragment.RecordActionFrag;
import com.example.svr.fragment.RecordListFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private RecordListFrag recordListFrag;
    private RecordActionFrag recordActionFrag;
    private BookmarkListFrag bookmarkListFrag;
    private boolean isRecording=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recordActionFrag = new RecordActionFrag(this);
        recordListFrag = new RecordListFrag();
        bookmarkListFrag = new BookmarkListFrag();
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(isRecording&&menuItem.getItemId()!=R.id.record_action){
                    makeSaveToast();
                    bottomNavigationView.setSelectedItemId(R.id.record_action);
                    return false;
                }
                switch (menuItem.getItemId()) {
                    case R.id.record_list:
                        onFragmentChanged(0);
                        break;
                    case R.id.bookmark:
                        onFragmentChanged(1);
                        break;
                    case R.id.record_action:
                        onFragmentChanged(2);
                        break;
                    case R.id.schedule:
                        onFragmentChanged(3);
                        break;
                    case R.id.action_setting:
                        onFragmentChanged(4);
                        break;
                }
                return true;
            }
            });
        onFragmentChanged(0);
            RecordDB.getInstance().initDB(this);
    }
    public void createDatabase(){
        RecordDB.getInstance().DB = openOrCreateDatabase("recordDB", MODE_PRIVATE, null);
    }
    public void onFragmentChanged(int index){
        System.out.println("시발 진짜 뭐냐고"+isRecording);

        switch (index) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_Frame,recordListFrag).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_Frame,bookmarkListFrag).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_Frame,recordActionFrag).commit();
                break;
        }
    }
    public void onCompleteSave(){
        setIsRecording(false);
        onFragmentChanged(0);
        bottomNavigationView.setSelectedItemId(R.id.record_list);
        Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show();
    }
    public void setIsRecording(boolean bool){
        isRecording=bool;
        bottomNavigationView.setEnabled(!bool);
    }
    private void makeSaveToast(){
        Toast.makeText(this, "정지 버튼을 눌러 저장을 완료해주십시오.", Toast.LENGTH_LONG).show();
    }
}
