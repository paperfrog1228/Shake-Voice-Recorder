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
        tv_name = findViewById(R.id.recordItem_name);
        tv_date = findViewById(R.id.recordItem_date);
        tv_length = findViewById(R.id.recordItem_length);
        tb_bookmark=findViewById(R.id.btn_bookmark);
    }
    public void setName(String name) { tv_name.setText(name); }
    public void setDate(String date) { tv_date.setText(date); }
    public void setLength(String length) { tv_length.setText(length); }
}