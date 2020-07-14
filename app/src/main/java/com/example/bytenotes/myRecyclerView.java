package com.example.bytenotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myRecyclerView extends RecyclerView.Adapter<myRecyclerView.MyOwnHolder> {

    private Context ctx;
    private ArrayList my_Notes;

    myRecyclerView(Context context, ArrayList myNotes) {

        this.ctx = context;
        this.my_Notes = myNotes;
    }

    @NonNull
    @Override
    public MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


    LayoutInflater myInflater = LayoutInflater.from(ctx);     // 7 Layout Inflate from context
    View myOwnView = myInflater.inflate(R.layout.my_row,parent , false);
    return new MyOwnHolder(myOwnView);

}
    @Override
    public void onBindViewHolder(@NonNull MyOwnHolder holder, int position) {
    holder.t1.setText(String.valueOf(my_Notes.get(position)));
    }

    @Override
    public int getItemCount() {
        int m= my_Notes.size();
        System.out.println(m);
        return my_Notes.size();
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder{

        TextView t1,t2;
        public MyOwnHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.noteView);

        }

    }
}
