package com.example.svr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RecordItemView extends LinearLayout {
    TextView tv_name;
    TextView tv_date;
    TextView tv_length;
    ToggleButton tb_bookmark;
    //int[] images = new int[]{R.drawable.singer, R.drawable.singer2, R.drawable.singer3,R.drawable.singer4,R.drawable.singer5};
    public RecordItemView(Context context) {
        super(context);
        init(context);
    }
    public RecordItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.record_item, this, true);
        tv_name = (TextView) findViewById(R.id.recordItem_name);
        tv_date = (TextView) findViewById(R.id.recordItem_date);
        tv_length = (TextView) findViewById(R.id.recordItem_length);
        tb_bookmark=findViewById(R.id.btn_bookmark);
    }
    public void setName(String name) { tv_name.setText(name); }
    public void setDate(String date) { tv_date.setText(date); }
    public void setLength(String length) { tv_length.setText(length); }
    public void setToggleBtn(boolean bookmark){
        if(bookmark)
            tb_bookmark.setPressed(true);
    }
}