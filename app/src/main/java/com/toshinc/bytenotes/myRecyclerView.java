package com.toshinc.bytenotes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myRecyclerView extends RecyclerView.Adapter<myRecyclerView.MyOwnHolder> {

    private Context ctx;
    private ArrayList book_id,my_Notes;
    private Activity activity;

    myRecyclerView(Activity activity,Context context, ArrayList book_id, ArrayList my_Notes) {
        this.activity=activity;
        this.ctx = context;
        this.book_id=book_id;
        this.my_Notes = my_Notes;
    }

    @NonNull
    @Override
    public MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


    LayoutInflater myInflater = LayoutInflater.from(ctx);     // 7 Layout Inflate from context
    View myOwnView = myInflater.inflate(R.layout.my_row,parent , false);
    return new MyOwnHolder(myOwnView);

}
    @Override
    public void onBindViewHolder(@NonNull final MyOwnHolder holder, final int position) {



    holder.t1.setText(String.valueOf(my_Notes.get(position)));
    holder.t2.setText(String.valueOf(book_id.get(position)));
    //holder.cardView.setCardBackgroundColor(color);
   // String i=String.valueOf(book_id.get(position));



    holder.mainLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent=new Intent(ctx,viewNote.class);

            intent.putExtra("Note",String.valueOf((my_Notes.get(position))));
            intent.putExtra("id", String.valueOf(book_id.get(position)));


            //System.out.println("position"+position);

            activity.startActivityForResult(intent,1);

        }
    });
    }




    @Override
    public int getItemCount() {
        //int m= my_Notes.size();
        //System.out.println(m);
        return my_Notes.size();
    }

    public static class MyOwnHolder extends RecyclerView.ViewHolder{

        TextView t1,viewNote,t2;
        ConstraintLayout mainLayout;
        CardView cardView;

        public MyOwnHolder(@NonNull View itemView) {
            super(itemView);


            t1=itemView.findViewById(R.id.noteView);
            t2=itemView.findViewById(R.id.date);
            cardView=itemView.findViewById(R.id.cardView);
            //cardView.setCardBackgroundColor(color);
            viewNote=itemView.findViewById(R.id.note_viewdata);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }

    }
}
