/**
 * 
 */
package com.kero.mama.shopping;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author aniruddh
 *
 */
public class Darwin extends ArrayAdapter<Data> {
	
	Context context;
	List<Data> ldata;

	public Darwin(Context context,List<Data> ldata) {
		super(context, R.layout.test,ldata);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.ldata=ldata;
	}
	
	public View getView(int position, View convertview, ViewGroup parent)
	{
		LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v=li.inflate(R.layout.test, parent, false);
		TextView tv1=(TextView)v.findViewById(R.id.roger1);
		TextView tv2=(TextView)v.findViewById(R.id.roger2);
		TextView tv3=(TextView)v.findViewById(R.id.roger3);
		TextView tv4=(TextView)v.findViewById(R.id.roger4);
		Data data=ldata.get(position);
		tv1.setText(String.valueOf(data._id)+".");
		tv2.setText(data.title);
		tv3.setText(data.price);
		tv4.setText(data.currency);
		return(v);
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#notifyDataSetChanged()
	 */
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

}
