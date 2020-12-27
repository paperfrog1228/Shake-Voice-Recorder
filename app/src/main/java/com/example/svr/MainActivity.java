package com.example.svr;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.svr.fragment.BookmarkListFrag;
import com.example.svr.fragment.RecordActionFrag;
import com.example.svr.fragment.RecordListFrag;
import com.example.svr.fragment.SettingFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private RecordListFrag recordListFrag;
    private RecordActionFrag recordActionFrag;
    private BookmarkListFrag bookmarkListFrag;
    private SettingFrag settingFrag;
    private boolean isRecording=false;
    String[] permission_list = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        recordActionFrag = new RecordActionFrag(this);
        recordListFrag = new RecordListFrag();
        bookmarkListFrag = new BookmarkListFrag();
        settingFrag=new SettingFrag();
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.getMenu().findItem(R.id.schedule).setVisible(false);
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
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_Frame,settingFrag).commit();
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
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkPermission(){
        //현재 안드로이드 버전이 6.0미만이면 메서드를 종료한다.
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;

        for(String permission : permission_list){
            //권한 허용 여부를 확인한다.
            int chk = checkCallingOrSelfPermission(permission);

            if(chk == PackageManager.PERMISSION_DENIED){
                //권한 허용을여부를 확인하는 창을 띄운다
                requestPermissions(permission_list,0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==0)
        {
            for(int i=0; i<grantResults.length; i++)
            {
                //허용됬다면
                if(grantResults[i]== PackageManager.PERMISSION_GRANTED){
                }
                else {
                    Toast.makeText(getApplicationContext(),"앱권한설정하세요",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }


}
