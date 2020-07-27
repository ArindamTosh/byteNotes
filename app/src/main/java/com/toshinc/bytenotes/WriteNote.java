package com.toshinc.bytenotes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import petrov.kristiyan.colorpicker.ColorPicker;

public class WriteNote extends AppCompatActivity {

    TextView note,date;
    myCoreDatabase mcd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);

        mcd=new myCoreDatabase(this);
        note=(TextView)findViewById(R.id.note_data);
        date= (TextView)findViewById(R.id.date);


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

            //Set Date or Time Stamp

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
            String currentDateandTime = sdf.format(new Date());

            System.out.println("Current Date and Time::   "+currentDateandTime);

            //Date

            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            System.out.println("Current Date ::   "+currentDate);
            //date.setText(currentDate);
            //Time

            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            System.out.println("Current Time::   "+currentTime);


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


    public void updateData(View view) {
    }

    public void update(View view) {

    }

    public void colorChooser(View view) {
        ColorPicker colorPicker = new ColorPicker(this);
        colorPicker.setRoundColorButton(true);
        colorPicker.show();
        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position,int color) {
                // put code

                System.out.println(color);
                System.out.println(position);
                Toast.makeText(WriteNote.this, "Upcoming Feature", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(){
                // put code
            }
        });

    }
}