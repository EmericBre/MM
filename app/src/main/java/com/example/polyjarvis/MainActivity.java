package com.example.polyjarvis;

import android.content.Context;
import android.os.Bundle;

import android.os.Vibrator;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private SpeechToTextActivity sttactivity;
    private TextToSpeechActivity ttsactivity;
    public HashMap<String, String> donnees;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donnees = createData();

        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        ttsactivity = new TextToSpeechActivity(this, v);
        sttactivity = new SpeechToTextActivity(this, ttsactivity);
    }

    protected HashMap<String, String> createData() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("say my name", "heisenberg");
        data.put("message", "a qui voulez-vous envoyer le message");
        data.put("twitter", "application twitter ouverte");
        data.put("instagram", "application instagram ouverte");
        data.put("youTube", "application youtube ouverte");
        data.put("snapchat", "application snapchat ouverte");
        data.put("spotify", "voici une musique agréable");
        data.put("météo", "il fait beau aujourd'hui");
        return data;
    }

    public HashMap<String, String> getData() {
        return donnees;
    }
}