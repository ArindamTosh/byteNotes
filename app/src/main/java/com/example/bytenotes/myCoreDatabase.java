package com.example.bytenotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class myCoreDatabase extends SQLiteOpenHelper {

    SQLiteDatabase myDb;
    Context ctx;
    ArrayList[] myArray;

    private Context context;
    private static final String DATABASE_NAME = "mynotes1.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "mynotes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "note";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_pages";

    private static final String Noter="note";

    myCoreDatabase(Context context) {
        super(context,"mynotes1.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PAGES + " INTEGER);";
        sqLiteDatabase.execSQL(query);

        //sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mynotes( _id integer primary key autoincrement, note text );");

        //sqLiteDatabase.execSQL("create table mynotes( n_id integer primary key autoincrement, note text )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists mynotes");
        onCreate(sqLiteDatabase);

    }

    public String getAll() {
        myDb = getReadableDatabase();
        Cursor cr = myDb.rawQuery("Select * from mynotes", null);
        StringBuilder str = new StringBuilder();
        while (cr.moveToNext()) {
            String s1 = cr.getString(1);
            String s2 = cr.getString(1);
            str.append(s1 + "               " + s2 + "\n");


            ArrayList<String> notesArray = new ArrayList<String>();
            while (cr.moveToNext()) {
                String s = cr.getString(1);
                notesArray.add(s);
                //  Log.i("Data:","Added");

            }

            for (int i = 0; i < notesArray.size(); i++) {
                //System.out.println(notesArray.get(i));
            }

           // Log.i("Strings:-", notesArray.toString());

            // Toast.makeText(ctx,str.toString(),Toast.LENGTH_SHORT).show();


        }

        return null;
    }

    public ArrayList<String> getMyArray() {
        myDb = getReadableDatabase();
        Cursor cr = myDb.rawQuery("Select * from mynotes", null);
        StringBuilder str = new StringBuilder();
        while (cr.moveToNext()) {
            String s1 = cr.getString(1);
            String s2 = cr.getString(1);
            str.append(s1 + "               " + s2 + "\n");


            ArrayList<String> notesArray = new ArrayList<String>();
            while (cr.moveToNext()) {
                String s = cr.getString(1);
                notesArray.add(s);
                //  Log.i("Data:","Added");

            }

            for (int i = 0; i < notesArray.size(); i++) {

               // System.out.println(notesArray.get(i));
            }

            //Log.i("Strings:-", notesArray.toString());
            // Toast.makeText(ctx,str.toString(),Toast.LENGTH_SHORT).show();


        }
        return null;
    }
    Cursor getAllData(){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=null;

        if (db!=null){
            cursor= db.rawQuery("Select * from mynotes", null);
        }
        return cursor;
    }

    void updateData(String row_id,String note){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("_id",row_id);
        cv.put("note", note);

        long result = db.update("mynotes", cv, "_id=?", new String[]{row_id});
        System.out.println( "result"+result);

       if (result==-1){
           Toast.makeText(ctx,"error data",Toast.LENGTH_SHORT).show();
       }else{
           System.out.println("Successfully Updated"+note);
         Toast.makeText(context,"Successfully updated",Toast.LENGTH_SHORT).show();

       }
    }
    void deleteRow(String row_id){
     SQLiteDatabase db= this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }    }

}
