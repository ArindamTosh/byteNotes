package com.example.bytenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    myRecyclerView rv;
    myCoreDatabase mcd;
    ArrayList <String> Notes;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.myRecycler);

        mcd = new myCoreDatabase(this);
        Notes = new ArrayList<>();

        storeDataInArray();

        rv=new myRecyclerView(this,Notes);
        rv.getItemCount();
        recyclerView.setAdapter(rv);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


    }

    void storeDataInArray(){

        Cursor c1=mcd.getAllData();
        if (c1.getCount()==0)   {
            Toast.makeText(this,"No data",Toast.LENGTH_LONG).show();

        }else{
            while (c1.moveToNext()){

                     Notes.add(c1.getString(1));

    }
            System.out.println(Notes);
}
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