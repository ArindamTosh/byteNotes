package com.example.bytenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    myCoreDatabase mcd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mcd = new myCoreDatabase(this);
mcd.getAll();

    }

   /* void readData(){
        mcd = new myCoreDatabase(this);
        SQLiteDatabase sql= mcd.getReadableDatabase();
        String projection[]={"n_id","note"};
        Cursor c1= sql.query("notes",projection,"note",null,null,null,null);
        Cursor c2= sql.query("notes",projection,"note",null,null,null,null);

    }*/


    public void writeNote(View view) {

        Intent i1= new Intent(this, WriteNote.class);
        startActivity(i1);
    }
}