package com.example.polyjarvis;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Vibrator;

public class SpeechToTextActivity {
    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private Button micButton;
    private MainActivity mainActivity;
    private TextToSpeechActivity tts;
    private boolean listening = false;


    public SpeechToTextActivity(MainActivity mainActivity, TextToSpeechActivity tts) {
        //super.onCreate(savedInstanceState);

        this.mainActivity = mainActivity;
        this.tts = tts;

        if(ContextCompat.checkSelfPermission(mainActivity,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }

        micButton = mainActivity.findViewById(R.id.buttonmic);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(mainActivity);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.FRANCE);

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                micButton.setBackgroundColor(Color.GREEN);
                micButton.setText("Listening");
                listening = true;
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                micButton.setBackgroundColor(Color.BLUE);
                micButton.setText("Tap to listen");
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                listening = false;

                tts.sendText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listening) {
                    micButton.setBackgroundColor(Color.BLUE);
                    micButton.setText("Tap to listen");
                    speechRecognizer.stopListening();
                } else {
                    micButton.setBackgroundColor(Color.GREEN);
                    micButton.setText("Listening");
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                listening = !listening;
                return;
            }
        });

    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(mainActivity,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }
}