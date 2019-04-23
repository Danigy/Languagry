package com.example.vijay.languagry;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;

public class Listen extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech spch;
    private Button btnListen;
    private EditText ptListen;
    private EditText ptAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);


        spch = new TextToSpeech(this,this);

        ptListen = (EditText) findViewById(R.id.ptListen);
        btnListen = (Button) findViewById(R.id.btnListen);
        btnListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakOutNow();
            }
        });
        ptAnswer  = (EditText) findViewById(R.id.ptAnswer);
        ptAnswer.setKeyListener(null);
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS){
            int language = spch.setLanguage(Locale.FRENCH);
            if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                btnListen.setEnabled(true);
                speakOutNow();
            }
        }
    }
    private void speakOutNow () {

        String i = ptListen.getText().toString();
        spch.speak(i, TextToSpeech.QUEUE_FLUSH, null);

    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.listenbtn)
        {
            promptSpeechInput();
        }
    }

    public void promptSpeechInput()
    {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.FRENCH.toString());
        //change speech language here
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"PRONOUNCE THE WORD OR PHRASE");


        try {
            startActivityForResult(i, 100);
        }
        catch (ActivityNotFoundException a)
        {
            Toast.makeText(Listen.this, "Your Device Does Not Support Speech Language", Toast.LENGTH_SHORT).show();
        }

    }

    public void onActivityResult(int request_code, int result_code, Intent i)
    {
        super.onActivityResult(request_code, result_code, i);

        switch (request_code)
        {
            case 100: if (result_code == RESULT_OK && i != null)
            {
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                ptAnswer.setText(result.get(0));
                check();
            }
            break;
        }
    }


      public void check ()
        {
            String word = ptListen.getText().toString();
            word = word.toLowerCase();
            String answer = ptAnswer.getText().toString();
            answer = answer.toLowerCase();
           if(word.trim().equals(answer.trim())){

                Toast.makeText(getApplicationContext(), "CORRECT",
                        Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(), "INCORRECT", Toast.LENGTH_LONG).show();
            }
        }

}


