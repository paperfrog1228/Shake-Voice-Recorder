package com.example.svr.fragment;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.svr.MainActivity;
import com.example.svr.R;
import com.example.svr.RecordDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordActionFrag extends Fragment {
    MediaRecorder myAudioRecorder;
    MediaPlayer mediaPlayer;
    Thread timeThread;
    String file_name;
    String file_path;
    String record_date;
    SimpleDateFormat simpleDate;
    SimpleDateFormat simpleFilename;
    File file;
    boolean isRecord=false;
    boolean isStart=false;
    ImageButton recordBtn;
    ImageButton stopBtn;
    ToggleButton bookmarkBtn;
    TextView recordTimeTv;
    TextView fileNameTv;
    MainActivity mainActivity;
    public RecordActionFrag(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.record_action_frag, container, false);
        file_path=Environment.getExternalStorageDirectory()+"/SVR";
        file= new File(file_path);
        Long date=new Date().getTime();

        simpleDate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        simpleFilename=new SimpleDateFormat("yyyy"+"MM"+"dd"+"_"+"hh"+"mm"+"ss");
        // init
        recordBtn=rootView.findViewById(R.id.btn_record);
        stopBtn=rootView.findViewById(R.id.btn_stop);
        bookmarkBtn=rootView.findViewById(R.id.btn_record_bookmark);
        recordTimeTv=rootView.findViewById(R.id.tv_record_time);
        fileNameTv=rootView.findViewById(R.id.tv_filename);
        init();
        if (!file.exists()){
            file.mkdirs();
        }
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(isRecord){
                    myAudioRecorder.pause();
                    recordBtn.setImageResource(R.drawable.record);
                    isRecord=false;
                }
                else{
                    if(isStart)
                        myAudioRecorder.resume();
                    else
                        mediaStart();
                    recordBtn.setImageResource(R.drawable.pause);
                    isRecord=true;
                }
                stopBtn.setEnabled(true);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer =new MediaPlayer();
                try
                {
                    myAudioRecorder.stop();
                }
                catch (RuntimeException e)
                {
                    System.out.println("e"+e);
                }
                myAudioRecorder.reset();
                myAudioRecorder.release();
                timeThread.interrupt();
                myAudioRecorder=null;
                recordBtn.setEnabled(true);
                stopBtn.setEnabled(false);
                int isBookmark=0;
                if(bookmarkBtn.isChecked())
                    isBookmark=1;
                String file_length=recordTimeTv.getText().toString();
                record_date=simpleDate.format(new Date(System.currentTimeMillis()));
                timeThread.interrupt();
                RecordDB.getInstance().insertRecord(file_name,record_date,file_length,isBookmark,file_path);
                init();
                mainActivity.onCompleteSave();
            }
        });

        return rootView;
    }
    private void init(){
        fileNameTv.setText(simpleFilename.format(new Date(System.currentTimeMillis())));
        stopBtn.setEnabled(false);
        bookmarkBtn.setChecked(false);
        isRecord=false;
        isStart=false;
        timeThread=new Thread(new timeThread());
    }
    private void setMediaRecorder(){
        myAudioRecorder = new MediaRecorder();
        //myAudioRecorder.reset();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        file_name=fileNameTv.getText().toString();
        file_path=file+"/"+file_name+".3gp";
        System.out.println("sex "+file_path);
        myAudioRecorder.setOutputFile(file_path);
        try {
            myAudioRecorder.prepare();
        }
        catch (IllegalStateException ise){
            System.out.println("에러! : "+ise);
        }
        catch (IOException ioe){
            System.out.println("에러! : "+ioe);
        }
    }
    private void mediaStart(){
        setMediaRecorder();
        mainActivity.setIsRecording(true);
        stopBtn.setImageResource(R.drawable.stop_enable);
        fileNameTv.setEnabled(true);
        try{
            myAudioRecorder.start();
        }
        catch (IllegalStateException ise){
            System.out.println(ise);
        }
        isStart=true;
        timeThread.start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 / 100) / 60;
            int hour = (msg.arg1 / 100) / 360;
            @SuppressLint("DefaultLocale") String result = String.format("%02d:%02d:%02d", hour, min, sec);
            recordTimeTv.setText(result);
        }
    };
    public class timeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                while (isRecord) {
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }
    }
}


