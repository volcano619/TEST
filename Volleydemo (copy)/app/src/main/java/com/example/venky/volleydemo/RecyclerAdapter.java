package com.example.venky.volleydemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by venky on 15/6/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    ArrayList<Contact> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Contact> arrayList){
        this.arrayList = arrayList;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.rtrname.setText(arrayList.get(position).getRtrname());
        holder.ctgname.setText(arrayList.get(position+1).getCtgname());
        holder.rtrphoneno.setText(arrayList.get(position+2).getRtrphoneno());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView rtrname,ctgname,rtrphoneno;

        public MyViewHolder(View itemView) {
            super(itemView);
            rtrname = (TextView)itemView.findViewById(R.id.rtrname);
            ctgname= (TextView)itemView.findViewById(R.id.ctgname);
            rtrphoneno = (TextView)itemView.findViewById(R.id.rtrphoneno);


        }
    }


}
