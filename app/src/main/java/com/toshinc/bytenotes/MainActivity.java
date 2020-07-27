package com.toshinc.bytenotes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    myRecyclerView rv;
    myCoreDatabase mcd;
    ArrayList<String> Notes, book_id;
    RecyclerView recyclerView;
    TextView addNote;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.myRecycler);
        addNote=findViewById(R.id.addNote);

        mcd = new myCoreDatabase(this);
        Notes = new ArrayList<>();
        book_id = new ArrayList<>();

        storeDataInArray();

        rv = new myRecyclerView(MainActivity.this, this, book_id, Notes);
        rv.getItemCount();
        recyclerView.setAdapter(rv);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        indicate();
    }

    void indicate() {
        Cursor c1 = mcd.getAllData();
        if (c1.getCount() <= 2) {
            addNote.setText("Add a Note"); //   Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        }else{
            addNote.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }

    }

    void storeDataInArray() {

        Cursor c1 = mcd.getAllData();
        if (c1.getCount() == 0) {
         //   Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();

        } else {
            while (c1.moveToNext()) {
                book_id.add(c1.getString(0));
                Notes.add(c1.getString(1));

            }
            // System.out.println(Notes);
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

        Intent i1 = new Intent(this, WriteNote.class);
        startActivity(i1);
        finish();
    }

    public void upcoming(View view) {
        Toast.makeText(this, "Upcoming Feature", Toast.LENGTH_LONG).show();
    }




}