package com.example.svr;

import android.media.MediaRecorder;

public class Setting {
    private static Setting instance;
    private int format=MediaRecorder.OutputFormat.THREE_GPP;
    private int encoder=MediaRecorder.AudioEncoder.AAC;
    private int samplingRate=22;
    private String formatStr=".3gp";

    public static Setting getInstance(){
        if(instance == null){
            instance = new Setting();
        }
        return instance;
    }
    public int getFormat(){
        return this.format;
    }
    public int getEncoder(){
        return this.encoder;
    }
    public String getFormatStr(){
        return this.formatStr;
    }
    public int getSamplingRate() { return  this.samplingRate;}
    public void SetFormat(String format){
        switch (format) {
            case "3GP":
                this.formatStr=".3gp";
                this.format=MediaRecorder.OutputFormat.THREE_GPP;
                this.encoder= MediaRecorder.AudioEncoder.AMR_NB;
                break;
            case "MP3":
                this.formatStr=".mp3";
                this.format=MediaRecorder.OutputFormat.MPEG_4;
                this.encoder=MediaRecorder.AudioEncoder.AAC;
                break;
            case "M4A(AAC)":
                this.formatStr=".m4a";
                this.format=MediaRecorder.OutputFormat.MPEG_4;
                this.encoder=MediaRecorder.AudioEncoder.HE_AAC;
                break;
        }
    }
    public void SetSampling(String format){
        switch (format) {
            case "44kHz":
                this.samplingRate=44;
                break;
            case "32kHz":
                this.samplingRate=32;
                break;
            case "22kHz":
                this.samplingRate=22;
                break;
            case "16Hz":
                this.samplingRate=16;
                break;
        }
    }
}
