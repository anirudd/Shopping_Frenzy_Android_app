/**
 * 
 */
package com.kero.mama.shopping;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
/**
 * @author aniruddh
 *
 */
public class BaseActivity extends Activity {
	public static SharedPreferences pref;
	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		pref=PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
		Editor editor=pref.edit();
		editor.putString("country", "US");
	}
	public boolean onCreateOptionsMenu(Menu m)
	{
		MenuInflater mi=this.getMenuInflater();
		mi.inflate(R.menu.menus, m);
		return(true);
	}
	public boolean onOptionsItemSelected(MenuItem mi)
	{
		if(mi.getItemId()==R.id.preference)
		{
		Intent i=new Intent(this,PrefenceHandler.class);
		startActivityForResult(i,10);
		return true;
		}
		return false;
		
	}

}
