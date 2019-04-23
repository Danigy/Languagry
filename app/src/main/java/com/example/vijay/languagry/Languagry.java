package com.example.vijay.languagry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Languagry extends AppCompatActivity {

    public ImageButton ibSearch;
    public ImageButton ibAdd;
    public ImageButton ibPronounce;
    public ImageButton ibAlbum;

    public void change(){
        ibPronounce = (ImageButton) findViewById(R.id.ibPronounce);
        ibPronounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Languagry.this, Listen.class);
                startActivity(a);
            }
        });
    }

    public void change2(){
        ibAdd = (ImageButton) findViewById(R.id.ibAdd);
        ibAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent b = new Intent(Languagry.this, AddWord.class);
                startActivity(b);
            }
        });
    }

    public void change3(){
        ibSearch = (ImageButton) findViewById(R.id.ibSearch);
        ibSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent c = new Intent(Languagry.this, SearchWord.class);
                startActivity(c);
            }
        });
    }

    public void change4(){
        ibAlbum = (ImageButton) findViewById(R.id.ibAlbum);
        ibAlbum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent d = new Intent(Languagry.this, Album.class);
                startActivity(d);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languagry);
        change();
        change2();
        change3();
        change4();
    }
}
