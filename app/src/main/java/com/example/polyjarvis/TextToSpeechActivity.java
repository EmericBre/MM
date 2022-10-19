package com.example.polyjarvis;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Locale;
import android.widget.Toast;

public class TextToSpeechActivity {
    TextToSpeech t1;
    MainActivity mainActivity;

    public TextToSpeechActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;


        t1=new TextToSpeech(mainActivity.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.FRANCE);
                }
            }
        });
    }

    public void sendText(String text) {
        String toSpeak = "T K T frérot je te reçois 5 sur 5";
        Toast.makeText(mainActivity.getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
}
