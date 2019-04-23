package com.example.vijay.languagry;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SearchWord extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private AppCompatActivity activity = SearchWord.this;
    Context context = SearchWord.this;
    private RecyclerView recyclerViewWords;
    private ArrayList<get_set_methods> listwords;
    private WordRecyclerAdapter wordRecyclerAdapter;
    private DatabaseAid myDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_word);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        initObjects();

        Intent intentThatStartedActivity = getIntent();
        if (intentThatStartedActivity.hasExtra("Word")){

            int id = getIntent().getExtras().getInt("id");
            String wordtype = getIntent().getExtras().getString("Type");
            String gender = getIntent().getExtras().getString("Gender");
            String definition = getIntent().getExtras().getString("Definition");
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }


    private void initViews(){
        recyclerViewWords = (RecyclerView) findViewById(R.id.wordlist);
    }

    private void initObjects(){
        listwords = new ArrayList<>();
        wordRecyclerAdapter = new WordRecyclerAdapter(listwords, this);
        RecyclerView.LayoutManager mylayoutmanager = new LinearLayoutManager(getApplicationContext());
        recyclerViewWords.setLayoutManager(mylayoutmanager);
        recyclerViewWords.setItemAnimator(new DefaultItemAnimator());
        recyclerViewWords.setHasFixedSize(true);
        recyclerViewWords.setAdapter(wordRecyclerAdapter);
        myDB = new DatabaseAid(activity);
        getDataFromSql();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.word_search, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searcher = (SearchView) MenuItemCompat.getActionView(search);
//        SearchView searcher = (SearchView) search.getActionView();
        searcher.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newWord){
        newWord = newWord.toLowerCase();
        ArrayList<get_set_methods> list = new ArrayList<>();

        for (get_set_methods word : listwords){
            String result = word.getWord().toLowerCase();
            if (result.contains(newWord)){
                list.add(word);
            }
        }
        wordRecyclerAdapter.setFilter(list);
        return true;
    }



    private void getDataFromSql(){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params){
                listwords.clear();
                listwords.addAll(myDB.getAllWords());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                wordRecyclerAdapter.notifyDataSetChanged();
            }

        }.execute();

    }


}
