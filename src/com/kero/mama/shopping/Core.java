/**
 * 
 */
package com.kero.mama.shopping;

import java.util.ArrayList;

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
public class Core extends ArrayAdapter<String> {
	Context context;
	ArrayList<String> objects;
	public Core(Context context, ArrayList<String> objects) {
		super(context,R.layout.shopping_list, objects);
		this.context=context;
		this.objects=objects;
		// TODO Auto-generated constructor stub
	}
	public View getView(int position, View convertview, ViewGroup parent)
	{
		LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v=li.inflate(R.layout.shopping_list, parent, false);
		TextView tv=(TextView) v.findViewById(R.id.customTexting1);
		tv.setText(objects.get(position));
		return(v);
	}

}
