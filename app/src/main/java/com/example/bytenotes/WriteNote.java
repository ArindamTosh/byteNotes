package com.example.bytenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WriteNote extends AppCompatActivity {

    TextView note;
    myCoreDatabase mcd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);

        mcd=new myCoreDatabase(this);
        note=(TextView)findViewById(R.id.note_data);
    }

    public void back(View view) {
        //If note data is null execute intent go back to main page
        String s1= note.getText().toString();
        if(s1.equals("")) {
            Intent i1 = new Intent(this, MainActivity.class);
            startActivity(i1);
            kill_activity();
        }else{


            // Perform database operations save data and get back to main activity

            SQLiteDatabase sql= mcd.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put("note",s1);
            sql.insert("mynotes",null,cv);

            //then go back to main activity or home

            Intent i1 = new Intent(this, MainActivity.class);
            startActivity(i1);
            kill_activity();








        }
    }
    void kill_activity()
    {
        finish();
    }
}