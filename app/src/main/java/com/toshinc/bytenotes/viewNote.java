package com.toshinc.bytenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class viewNote extends AppCompatActivity {
    ImageView BackButton;
    TextView notevd;
    myCoreDatabase mcd;
    String IntentNote;
    String s1;
    String id;
    FloatingActionButton fab;
    ImageView save_small,delete_small;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
       // Toast.makeText(this,"ViewNote Activity Started",Toast.LENGTH_SHORT).show();
        BackButton = (ImageView) findViewById(R.id.backButtonv);
        save_small=(ImageView)findViewById(R.id.share);
        delete_small=(ImageView)findViewById(R.id.Delete_small);
        fab= findViewById(R.id.floatingActionButton4);
        notevd=(TextView)findViewById(R.id.note_viewdata);
        getAndSetIntentData();


        BackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                update();
            }
        });
        save_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });
        delete_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }
    void delete(){
        mcd=new myCoreDatabase(viewNote.this);

        mcd.deleteRow(id);
        System.out.println("Updated:::::: "+id+s1);
        Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i1);
        kill_activity();
    }


    void update(){
            mcd=new myCoreDatabase(viewNote.this);
            s1 = notevd.getText().toString().trim();
            mcd.updateData(id,s1);
            System.out.println("Updated:::::: "+id+s1);
            Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i1);
            kill_activity();
        }

    void back(){
            //If note data is null execute intent go back to main page

           s1= notevd.getText().toString();
            if(s1.equals("")) {
                Intent i1 = new Intent(this, MainActivity.class);
                startActivity(i1);
                kill_activity();

            }else{


                // Perform database operations save data and get back to main activity

                mcd=new myCoreDatabase(this);
                SQLiteDatabase sql= mcd.getWritableDatabase();



                ContentValues cv1=new ContentValues();
                s1 = notevd.getText().toString();


                cv1.put("_id",id);
                cv1.put("note",s1);
               //sql.insert("mynotes",null,cv1);
              sql.update("mynotes",cv1, "_id = ?", new String[]{id});
                System.out.println("Updated Id:::::: "+id+"Data"+s1);




                //then go back to main activity or home
               // myCoreDatabase mcd1= new myCoreDatabase(viewNote.this);
               // mcd1.updateData(s1);

                Intent i1 = new Intent(this, MainActivity.class);
                startActivity(i1);
                kill_activity();








            }
        }
        void kill_activity()
        {
            finish();
        }
        void getAndSetIntentData(){
           // IntentNote =  getIntent().getStringExtra("Note");
            //System.out.println(IntentNote);
        if(getIntent().hasExtra("Note") &&  getIntent().hasExtra("id")){


                // Get data from Intent

                  IntentNote =  getIntent().getStringExtra("Note");
                  id=getIntent().getStringExtra("id");
                  System.out.println("IDDDDDDDDD:"+id+"       Value:"+IntentNote);

               //Set data from intent

                  notevd.setText(IntentNote);


              }else {
                  Toast.makeText(this,"No Notes. Please add some...",Toast.LENGTH_SHORT).show();
              }


        }


    void share() {
        Intent Share= new Intent(Intent.ACTION_SEND);
        Share.setType("text/plain");
        s1= notevd.getText().toString();
        System.out.println(s1);
        Share.putExtra(Intent.EXTRA_TEXT,s1);
        startActivity(Intent.createChooser(Share, "Share via"));

    }
}