package com.example.polyjarvis;

import android.content.Context;
import android.os.Bundle;

import android.os.Vibrator;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SpeechToTextActivity sttactivity;
    private TextToSpeechActivity ttsactivity;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        ttsactivity = new TextToSpeechActivity(this);
        sttactivity = new SpeechToTextActivity(this, ttsactivity, v);
    }
}