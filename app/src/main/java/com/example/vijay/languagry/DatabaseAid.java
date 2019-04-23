package com.example.vijay.languagry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DisplayContext;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijay on 25/9/2017.
 */

public class DatabaseAid extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "allwords.db";
    public static final String TABLE_NAME = "master_table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "WORD";
    public static final String COLUMN_3 = "TYPE";
    public static final String COLUMN_4 = "GENDER";
    public static final String COLUMN_5 = "DEFINITION";


    public DatabaseAid(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME + " ("+COLUMN_1 +"  INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_2 +" TEXT, "+COLUMN_3 +" TEXT, "+ COLUMN_4 +" TEXT, "+COLUMN_5 +" TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void insertData(String word, String type, String gender, String definition){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, word);
        contentValues.put(COLUMN_3, type);
        contentValues.put(COLUMN_4, gender);
        contentValues.put(COLUMN_5, definition);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<get_set_methods> getAllWords(){
        String [] columns = {
                DatabaseAid.COLUMN_1,
                DatabaseAid.COLUMN_2,
                DatabaseAid.COLUMN_3,
                DatabaseAid.COLUMN_4,
                DatabaseAid.COLUMN_5
        };

        String sortOrder =
                DatabaseAid.COLUMN_2 + " ASC ";
        List<get_set_methods> wordList = new ArrayList<get_set_methods>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, null, null, null, null, sortOrder);

        if (cursor.moveToFirst()){
            do{
                get_set_methods wordd = new get_set_methods();
                wordd.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_1))));
                wordd.setWord(cursor.getString(cursor.getColumnIndex(COLUMN_2)));
                wordd.setType(cursor.getString(cursor.getColumnIndex(COLUMN_3)));
                wordd.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_4)));
                wordd.setDef(cursor.getString(cursor.getColumnIndex(COLUMN_5)));
                wordList.add(wordd);
            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return wordList;
    }
}
