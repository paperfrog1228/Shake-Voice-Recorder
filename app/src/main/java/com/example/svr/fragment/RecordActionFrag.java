package com.example.svr.fragment;
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
    String outputFile;
    SimpleDateFormat simpleDate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.record_action_frag, container, false);
        //outputFile = Environment.
        String file_path=getActivity().getApplicationContext().getCacheDir().getPath();
        File file= new File(file_path);
        System.out.println("sssss:"+file_path);
        Long date=new Date().getTime();
        Date current_time = new Date(Long.valueOf(date));

        simpleDate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Button recordBtn=rootView.findViewById(R.id.btn_record);
        Button stopBtn=rootView.findViewById(R.id.btn_stop);
        stopBtn.setEnabled(false);
        if (!file.exists()){
            file.mkdirs();
        }
        String file_name=file+"/"+current_time+".3gp";
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder = new MediaRecorder();
                myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                myAudioRecorder.setOutputFile(file_name);
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                }
                catch (IllegalStateException ise){

                }
                catch (IOException ioe){

                }
                recordBtn.setEnabled(false);
                stopBtn.setEnabled(true);
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    myAudioRecorder.stop();
                }
                catch (RuntimeException e)
                {
                    System.out.println("e"+e);
                }
               myAudioRecorder.release();
                myAudioRecorder=null;
               recordBtn.setEnabled(true);
               stopBtn.setEnabled(false);
               RecordDB.getInstance().insertRecord("testtest",simpleDate.format(new Date(System.currentTimeMillis())),file_path);
            }
        });
        return rootView;
    }
}