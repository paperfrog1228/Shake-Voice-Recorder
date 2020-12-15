package com.example.svr.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.svr.R;
import com.example.svr.RecordDB;
import com.example.svr.RecordItem;

import java.io.IOException;

public class PlayerDialogFrag extends DialogFragment {
    RecordItem record;
    MediaPlayer mediaPlayer;
    String filePath;
    public static final String TAG_EVENT_DIALOG="test";
   public PlayerDialogFrag(RecordItem recordItem){
       record=recordItem;
       mediaPlayer=new MediaPlayer();
       filePath=recordItem.getPath();
   }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View v =inflater.inflate(R.layout.player_dialog_frag,container);
        Button startbtn=v.findViewById(R.id.btn_player_start);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("sex:"+filePath);
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                try {
                    mediaPlayer.setDataSource(filePath+".3gp");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                //mediaPlayer.prepareAsync();
                System.out.println("시작한다구~~");
                startbtn.setEnabled(false);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });
       return v;
    }
}
