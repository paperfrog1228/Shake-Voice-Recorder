package com.example.svr.fragment;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.svr.MainActivity;
import com.example.svr.R;
import com.example.svr.RecordAdapter;
import com.example.svr.RecordItem;
import com.example.svr.Setting;

import java.sql.BatchUpdateException;
public class SettingFrag extends Fragment {
    final String[] formats = new String[] {"3GP", "MP3", "M4A(AAC)"};
    final String[] samplings = new String[] {"44kHz", "32kHz", "22kHz", "16kHz"};
    Button btn_format;
    Button btn_sampling;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.setting_frag, container, false);
        btn_format=rootView.findViewById(R.id.btn_format);
        btn_sampling=rootView.findViewById(R.id.btn_sampling);
        btn_format.setText(Setting.getInstance().getFormatStr());
        btn_format.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioDialogFormat(rootView);
            }
        });
        btn_sampling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioDialogSamplings(rootView);
            }
        });
        return rootView;
    }
    public void radioDialogFormat(View view) {
        new AlertDialog.Builder(getContext()).setTitle("확장자 선택").setSingleChoiceItems(formats, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "파일 확장자를 " + formats[which]+"로 설정하였습니다.", Toast.LENGTH_SHORT).show();
                Setting.getInstance().SetFormat(formats[which]);
                btn_format.setText(formats[which]);
                dialog.dismiss();
            }
        }).show();
    }
    public void radioDialogSamplings(View view) {
        new AlertDialog.Builder(getContext()).setTitle("샘플링 레이트 선택").setSingleChoiceItems(samplings, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "샘플링 레이트를 " + samplings[which]+"로 설정하였습니다.", Toast.LENGTH_SHORT).show();
                Setting.getInstance().SetFormat(samplings[which]);
                btn_sampling.setText(samplings[which]);
                dialog.dismiss();
            }
        }).show();
    }
}
