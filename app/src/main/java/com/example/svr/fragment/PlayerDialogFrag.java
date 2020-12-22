package com.example.svr.fragment;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import com.example.svr.R;
import com.example.svr.RecordItem;
import java.io.IOException;
public class PlayerDialogFrag extends DialogFragment {
    RecordItem record;
    MediaPlayer mediaPlayer;
    String fileName;
    String filePath;
    boolean isPlaying=false;
    SeekBar playingBar;
    TextView curTimeTv;
    TextView endTimeTv;
    Thread playingBarThread;
    ImageButton playBtn;
    public static final String TAG_EVENT_DIALOG="test";
   public PlayerDialogFrag(RecordItem recordItem){
       record=recordItem;
       fileName=recordItem.getName();
       filePath=recordItem.getPath();
   }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        playingBarThread=new Thread(new playingBarThread());
        mediaPlayer=new MediaPlayer();
        View v =inflater.inflate(R.layout.player_dialog_frag,container);
        playBtn=v.findViewById(R.id.btn_player_start);
        playingBar= v.findViewById(R.id.seekBar_player);
        TextView nameTv=v.findViewById(R.id.tv_recordplayerName);
        curTimeTv=v.findViewById(R.id.tv_curpos);
        endTimeTv=v.findViewById(R.id.tv_endpos);
        nameTv.setText(fileName);
        playBtn.setEnabled(false);
        try {
            mediaPlayer.setDataSource(filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                playBtn.setImageResource(R.drawable.play);
                playBtn.setEnabled(true);
                endTimeTv.setText(getTimeString(mediaPlayer.getDuration()));
                playingBar.setMax(mediaPlayer.getDuration());
                playingBarThread.start();
            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying) {
                    mediaPlayer.pause();
                    playBtn.setImageResource(R.drawable.play);
                    isPlaying=false;
                }
                else{
                    playBtn.setImageResource(R.drawable.pause);
                    mediaPlayer.start();
                    isPlaying=true;
                }
            }
        });
       return v;
    }
    public void endPlayer(){
        mediaPlayer.seekTo(0);

        mediaPlayer.pause();
        playBtn.setImageResource(R.drawable.play);
        playingBar.setProgress(0);
        isPlaying=false;
        playingBarThread.interrupt();
    }
    private String getTimeString(int duration){
        String Hour = String.format("%02d",(duration)/1000/3600);
        String Min = String.format("%02d",((duration)/1000/60)%60);
        String Sec = String.format("%02d", ((duration) / 1000) % 60);
        return Hour+":"+Min+":"+Sec;
    }
    final Handler handler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void handleMessage(Message msg){
            curTimeTv.setText(getTimeString(mediaPlayer.getCurrentPosition()));
            playingBar.setProgress(mediaPlayer.getCurrentPosition());
        }
    };
    class playingBarThread implements Runnable {
        @Override
        public void run() {
            while(true)
            while(isPlaying) {
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
                if(mediaPlayer.getCurrentPosition()==mediaPlayer.getDuration())
                    endPlayer();
            }
        }
    }
}
