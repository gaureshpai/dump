package com.example.texttospeach;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech ts;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        e = findViewById(R.id.editTextText2);
        ts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.ERROR) {
                    ts.setLanguage(Locale.UK);
                }
            }
        });
    }
    public void convert(View v){
        String tospeach = e.getText().toString();
        ts.speak(tospeach,TextToSpeech.QUEUE_FLUSH,null);

    }
}