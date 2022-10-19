package com.example.polyjarvis;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class MainActivity extends Activity{

    private SpeechToTextActivity sttactivity;
    private TextToSpeechActivity ttsactivity;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sttactivity = new SpeechToTextActivity();
        ttsactivity = new TextToSpeechActivity();
    }
}