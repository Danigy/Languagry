package com.example.vijay.languagry;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddWord extends AppCompatActivity {

    DatabaseAid myDB;
    EditText enterWord, insertDef;
    Spinner insertType, insertGender;
    Button insertBtn;
    public String a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        enterWord = (EditText)findViewById(R.id.enterWord);
        insertDef = (EditText)findViewById(R.id.insertDef);
        insertType = (Spinner)findViewById(R.id.insertType);
        insertGender = (Spinner)findViewById(R.id.insertGender);
        insertBtn = (Button)findViewById(R.id.insertBtn);
        myDB = new DatabaseAid(this);
        add();
    }

    public void add(){
                insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enterWord.getText().toString().isEmpty() || insertDef.getText().toString().isEmpty()) {
                    Toast.makeText(AddWord.this, "Please Fill All Required Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    a = insertType.getSelectedItem().toString();
                    b = insertGender.getSelectedItem().toString();
                    myDB.insertData(enterWord.getText().toString(), a, b, insertDef.getText().toString());
                    Toast.makeText(AddWord.this, "Word Added to Personal Dictionary", Toast.LENGTH_SHORT).show();
                    clear();
                }
            }
        });
    }

    public void clear(){
        enterWord.setText(null);
        insertDef.setText(null);
    }
}
