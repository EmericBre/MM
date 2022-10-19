package com.example.polyjarvis;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToTextActivity {
    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private TextView textView;
    private ImageView micButton;
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

        textView = mainActivity.findViewById(R.id.text);
        micButton = mainActivity.findViewById(R.id.buttonmic);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(mainActivity);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                textView.setText("");
                textView.setHint("Listening...");
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
                micButton.setImageResource(R.drawable.ic_mic_black_off);
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                textView.setText(data.get(0));
                tts.sendText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        micButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (listening) {
                    micButton.setImageResource(R.drawable.ic_mic_black_off);
                    speechRecognizer.stopListening();
                } else {
                    micButton.setImageResource(R.drawable.ic_mic_black_24dp);
                    speechRecognizer.startListening(speechRecognizerIntent);

                }
                listening = !listening;
                return false;
            }
        });

    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(mainActivity,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }
}