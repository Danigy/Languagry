package com.example.vijay.languagry;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class WordRecyclerAdapter extends RecyclerView.Adapter<WordRecyclerAdapter.WordViewHolder> implements TextToSpeech.OnInitListener{

    TextView textViewWord;
    ImageButton greenBtn;
    public TextToSpeech speech;
    private ArrayList<get_set_methods> listWord;
    public Context context;
    //private ArrayList<get_set_methods> mfilteredlist;

    public WordRecyclerAdapter(ArrayList<get_set_methods> listWord, Context context){
        this.listWord = listWord;
        this.context = context;
        //this.mfilteredlist = listWord;
    }

    public class WordViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewWord;
        public TextView textViewType;
        public TextView textViewGender;
        public ImageButton greenBtn;
        public TextView textViewDef;

        public WordViewHolder(View view){
            super(view);
            textViewWord = (AppCompatTextView) view.findViewById(R.id.textViewWord);
            textViewType = (AppCompatTextView) view.findViewById(R.id.textViewType);
            textViewGender = (AppCompatTextView) view.findViewById(R.id.textViewGender);
            textViewDef = (AppCompatTextView) view.findViewById(R.id.textViewDef);
            greenBtn = (AppCompatImageButton) view.findViewById(R.id.greenBtn);
        }
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.the_recycler, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WordViewHolder holder, int position){
        holder.textViewWord.setText(listWord.get(position).getWord());
        holder.textViewType.setText(listWord.get(position).getType());
        holder.textViewGender.setText(listWord.get(position).getGender());
        holder.textViewDef.setText(listWord.get(position).getDef());
        holder.greenBtn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeakOutNow();
            }
        });

    }

    @Override
    public void onInit(int i){
        if (i == TextToSpeech.SUCCESS){
            int language = speech.setLanguage(Locale.FRANCE);
            if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED){
               greenBtn.setEnabled(true);
               SpeakOutNow();
            }
        }
    }

    private void SpeakOutNow(){
        String i = textViewWord.getText().toString();
        speech.speak(i, TextToSpeech.QUEUE_FLUSH, null);

    }

    public int getItemCount(){
        //return mfilteredlist.size();
        return listWord.size();
    }

    public void setFilter (ArrayList<get_set_methods> list){
        listWord = new ArrayList<>();
        listWord.addAll(list);
        notifyDataSetChanged();

    }



}
