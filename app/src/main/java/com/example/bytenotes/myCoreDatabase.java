package com.example.bytenotes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class myCoreDatabase extends SQLiteOpenHelper {

    SQLiteDatabase myDb;
    Context ctx;


    public myCoreDatabase(Context context) {
        super(context, "mynotes.db", null, 1);
        ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table mynotes(n_id integer primary key autoincrement, note text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists mynotes");
        onCreate(sqLiteDatabase);

    }
public String getAll(){
        myDb= getReadableDatabase();
        Cursor cr=myDb.rawQuery("Select * from mynotes",null);
        StringBuilder str=new StringBuilder();
        while (cr.moveToNext()){
            String s1= cr.getString(1);
            String s2= cr.getString(1);
            str.append(s1+"               "+s2+"\n");


            ArrayList<String> notesArray = new ArrayList<String>();
            while (cr.moveToNext()){
                String s= cr.getString(1);
                notesArray.add(s);
                Log.i("Data:","Added");

            }

            for (int i = 0; i < notesArray.size(); i++) {
                System.out.println(notesArray.get(i));
            }

            Log.i("Strings:-",notesArray.toString());
           // Toast.makeText(ctx,str.toString(),Toast.LENGTH_SHORT).show();



    }

    return null;
}

}
