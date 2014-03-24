/**
 * 
 */
package com.kero.mama.shopping;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author aniruddh
 *
 */
public class Custom extends BaseActivity {
	AutoCompleteTextView act;
	ArrayList<String> l;
	Button b1,b2;
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		setContentView(R.layout.customised_search);
		act=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		l=new ArrayList<String>();
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.everything_else)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_input_devices)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_storage_device)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_swimming)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_soccer)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_cricket)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_basket_ball)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_climate_control)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_television)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_computer_acc)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_audio_components)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_kitchen_app)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_string_instruments)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_shirtsANDtops)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_pants)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_software)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_house_hold_app)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_communications)));
		l.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_cameras)));
		ArrayAdapter<String> adp=new ArrayAdapter<String>(this,R.layout.dropdown,R.id.ttv,l);
		act.setAdapter(adp);
		b1=(Button)findViewById(R.id.bt1);
		b2=(Button)findViewById(R.id.bt2);
		
	}
	public void handler(View v)
	{
		switch(v.getId())
		{
		case R.id.bt1: 	ConnectivityManager cm=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
						NetworkInfo ni=cm.getActiveNetworkInfo();
						if(ni!=null)
						{
							if(act.getText().toString().equals(""))
							{
								Toast.makeText(this, "Please enter a valid search query", Toast.LENGTH_LONG);
								return;
							}
							else
							{
								String country;
								country=BaseActivity.pref.getString("country", "US");
								Intent i=new Intent(this,ShoppingActivity.class);
								i.putExtra("country", country);
								i.putExtra("query", act.getText().toString());
								startActivity(i);
							}
						}
						else
						{
							AlertDialog.Builder ab=new AlertDialog.Builder(this);
							ab.setTitle("Connectivity Error");
							ab.setMessage("Connection to Internet Failed\nPlease connect to Internet");
							ab.setNeutralButton("Connect to Internet", new DialogInterface.OnClickListener()
							{
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Intent i1=new Intent(Settings.ACTION_WIRELESS_SETTINGS);
									startActivityForResult(i1,1);
								}
							});
							ab.setNegativeButton("Cancel", null);
							ab.show();
						}
						break;
		case R.id.bt2: act.setText("");
						break;
			}
	}
}
