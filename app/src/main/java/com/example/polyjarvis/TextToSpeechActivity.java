package com.example.polyjarvis;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Locale;
import android.widget.Toast;

public class TextToSpeechActivity {
    TextToSpeech t1;
    MainActivity mainActivity;
    private Vibrator v;

    public TextToSpeechActivity(MainActivity mainActivity, Vibrator v) {
        this.mainActivity = mainActivity;
        this.v = v;

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
        //String toSpeak = "T K T frérot je te reçois 5 sur 5";
        String value = this.mainActivity.getData(text);
        if (value=="je n'ai pas compris, veuillez répéter") {
            // Vibrate for 400 milliseconds
            v.vibrate(500);
        } else {
            // Vibrate for 400 milliseconds
            v.vibrate(250);
        }
        Toast.makeText(mainActivity.getApplicationContext(), value,Toast.LENGTH_SHORT).show();
        t1.speak(value, TextToSpeech.QUEUE_FLUSH, null);
    }
}
