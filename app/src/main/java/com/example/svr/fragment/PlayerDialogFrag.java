package com.example.svr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.svr.R;

public class PlayerDialogFrag extends DialogFragment {
    public static final String TAG_EVENT_DIALOG="test";
    public static PlayerDialogFrag getInstance(){
       PlayerDialogFrag e = new PlayerDialogFrag();
       return e;
   }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
       View v =inflater.inflate(R.layout.player_dialog_frag,container);
       return v;
    }

}
