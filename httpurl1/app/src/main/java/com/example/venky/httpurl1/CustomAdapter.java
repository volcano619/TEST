package com.example.venky.httpurl1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by venky on 15/5/16.
 */
public class CustomAdapter extends BaseAdapter {
    private List<Response> mitem;
    private Context mContext;
    private LayoutInflater inflater;

    public CustomAdapter(Context mContext,List <Response> mitem) {
        this.mContext = mContext;
        this.mitem = mitem;
    }

    @Override
    public int getCount() {
        return mitem.size();
    }

    @Override
    public Object getItem(int position) {
        return mitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.each_list_item,parent,false);

        Response item = (Response) getItem(position);
        TextView rtrname = (TextView) rowView.findViewById(R.id.rtrname);
        TextView ctgname = (TextView) rowView.findViewById(R.id.ctgname);
        TextView rtrphoneno = (TextView) rowView.findViewById(R.id.rtrphoneno);


        rtrname.setText(item.getRtrname());
        ctgname.setText(item.getCtgname());
        rtrphoneno.setText(item.getRtrphoneno());
        return rowView;


    }
}

