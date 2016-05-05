package com.kaleidosstudio.listview_load_data_from_json;

import com.example.listview_load_data_from_json.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	MainActivity main;
	
	ListAdapter(MainActivity main)
	{
		this.main = main;
	}
	
	@Override
	public int getCount() {
		return  main.countries.size();
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	static class ViewHolderItem {
		TextView rtrname;
		TextView ctgcode;
		TextView rtrcode;
		TextView valueclassname;
		TextView distcode;
		TextView srpcde;
		TextView billedret;
		TextView rtrphoneno;
		TextView rtrclassid;
		TextView rtradd1;
		TextView rtrid;
		TextView mktid;
		TextView class1;
		TextView ctgname;
		TextView valueclasscode;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolderItem holder = new ViewHolderItem();
		if (convertView == null) {
		 LayoutInflater inflater = (LayoutInflater) main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.cell, null);
	        
	        holder.billedret = (TextView) convertView.findViewById(R.id.billedret);
	        holder.ctgcode = (TextView) convertView.findViewById(R.id.ctgcode);
			holder.ctgname = (TextView) convertView.findViewById(R.id.ctgname);
			holder.class1 = (TextView) convertView.findViewById(R.id.class1);
			holder.rtradd1 = (TextView) convertView.findViewById(R.id.rtradd1);
			holder.rtrclassid = (TextView) convertView.findViewById(R.id.rtrclassid);
			holder.rtrcode = (TextView) convertView.findViewById(R.id.rtrcode);
			holder.rtrid = (TextView) convertView.findViewById(R.id.rtrid);
			holder.rtrname = (TextView) convertView.findViewById(R.id.rtrname);
			holder.rtrphoneno = (TextView) convertView.findViewById(R.id.rtrphoneno);
			holder.valueclasscode = (TextView) convertView.findViewById(R.id.valueclasscode);
			holder.valueclassname = (TextView) convertView.findViewById(R.id.valueclassname);
			holder.mktid = (TextView) convertView.findViewById(R.id.mktid);
			holder.srpcde = (TextView) convertView.findViewById(R.id.srpcde);
			holder.distcode = (TextView) convertView.findViewById(R.id.distcode);

	        convertView.setTag(holder);
		}
		else
		{
			 holder = (ViewHolderItem) convertView.getTag();
		}
	        
		
		holder.billedret.setText(this.main.countries.get(position).billedret);
		holder.class1.setText(this.main.countries.get(position).class1);
		holder.ctgname.setText(this.main.countries.get(position).ctgname);
		holder.ctgcode.setText(this.main.countries.get(position).ctgcode);
		holder.rtradd1.setText(this.main.countries.get(position).rtradd1);
		holder.rtrphoneno.setText(this.main.countries.get(position).rtrphoneno);
		holder.rtrname.setText(this.main.countries.get(position).rtrname);
		holder.rtrid.setText(this.main.countries.get(position).rtrid);
		holder.rtrcode.setText(this.main.countries.get(position).rtrcode);
		holder.rtrclassid.setText(this.main.countries.get(position).rtrclassid);
		holder.valueclasscode.setText(this.main.countries.get(position).valueclasscode);
		holder.valueclassname.setText(this.main.countries.get(position).valueclassname);
		holder.mktid.setText(this.main.countries.get(position).mktid);
		holder.srpcde.setText(this.main.countries.get(position).srpcde);
		holder.distcode.setText(this.main.countries.get(position).distcode);
		
		return convertView;
	}

}
