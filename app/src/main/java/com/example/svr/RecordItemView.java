package com.example.svr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecordItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageView imageView;
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
        textView = (TextView) findViewById(R.id.recordItem_name);
        textView2 = (TextView) findViewById(R.id.recordItem_date);
        textView3 = (TextView) findViewById(R.id.recordItem_length);
        imageView = (ImageView) findViewById(R.id.recordItem_IV);
    }
    public void setName(String name) { textView.setText(name); }
    public void setDate(String date) { textView.setText(date); }
    public void setLength(String length) { textView.setText(length); }
}