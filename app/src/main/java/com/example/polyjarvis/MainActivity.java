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
        data.put("envoie un message", "a qui voulez-vous envoyer le message");
        data.put("ouvre Twitter", "application twitter ouverte");
        data.put("ouvre Instagram", "application instagram ouverte");
        data.put("ouvre YouTube", "application youtube ouverte");
        data.put("ouvre snapchat", "application snapchat ouverte");
        data.put("lance Spotify", "voici une musique agréable");
        data.put("donne-moi la météo", "il fait beau aujourd'hui");
        return data;
    }

    public String getData(String text) {
        String value = donnees.get(text);
        if (value==null) {
            return "je n'ai pas compris, veuillez répéter";
        }
        return value;
    }
}