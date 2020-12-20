package com.example.svr.fragment;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    String file_length;
    String file_name;
    String record_date;
    SimpleDateFormat simpleDate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.record_action_frag, container, false);
        String file_path=Environment.getExternalStorageDirectory()+"/SVR";
        File file= new File(file_path);
        Long date=new Date().getTime();
        Date current_time = new Date(Long.valueOf(date));
        simpleDate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Button recordBtn=rootView.findViewById(R.id.btn_record);
        Button stopBtn=rootView.findViewById(R.id.btn_stop);
        stopBtn.setEnabled(false);
        if (!file.exists()){
            file.mkdirs();
        }
        file_name=file+"/"+current_time;
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder = new MediaRecorder();
                myAudioRecorder.setOutputFile(file_name);
                myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                try {
                    myAudioRecorder.prepare();
                }
                catch (IllegalStateException ise){
                    System.out.println("씨발 뭐여 : "+ise);
                }
                catch (IOException ioe){
                    System.out.println("씨발 뭐여 : "+ioe);
                }
                mediaStart();
                recordBtn.setEnabled(false);
                stopBtn.setEnabled(true);
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer =new MediaPlayer();
                System.out.println("sssss:"+file_name);
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
                myAudioRecorder=null;
               recordBtn.setEnabled(true);
               stopBtn.setEnabled(false);
                file_length=getLength();
                record_date=simpleDate.format(new Date(System.currentTimeMillis()));
                System.out.println(record_date);
               RecordDB.getInstance().insertRecord(file_name,record_date,file_length,0,file_name);
            }
        });

        return rootView;
    }
    public static boolean checkWritable() {
        // Retrieving the external storage state
        String state = Environment.getExternalStorageState();

        // Check if writable
        if (Environment.MEDIA_MOUNTED.equals(state)){
            if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
    private void mediaStart(){
        try{
            myAudioRecorder.start();
        }
        catch (IllegalStateException ise){
            System.out.println(ise);
        }
    }
    private String getLength(){
        int duration=0;

        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(file_name);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String Hour = String.format("%02d",(duration/1000/3600));
        String Min = String.format("%02d",((duration/1000/60)%60));
        String Sec = String.format("%02d", (duration / 1000) % 60);
        return Hour+":"+Min+":"+Sec;
    }
}