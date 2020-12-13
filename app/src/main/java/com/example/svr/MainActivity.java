package com.example.svr;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.svr.fragment.RecordActionFrag;
import com.example.svr.fragment.RecordListFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private RecordListFrag recordListFrag;
    private RecordActionFrag recordActionFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recordActionFrag = new RecordActionFrag();
        recordListFrag = new RecordListFrag();
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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

       //if(!RecordDB.getInstance().isDatabaseCreated())
            RecordDB.getInstance().initDB(this);
    }
    public void createDatabase(){
        RecordDB.getInstance().DB = openOrCreateDatabase("recordDB", MODE_PRIVATE, null);
    }
    public void onFragmentChanged(int index){
        System.out.println("index : "+index);
        switch (index) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_Frame,recordListFrag).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_Frame,recordActionFrag).commit();
                break;
        }
    }

}
