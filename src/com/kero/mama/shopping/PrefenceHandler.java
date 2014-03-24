/**
 * 
 */
package com.kero.mama.shopping;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author aniruddh
 *
 */
public class PrefenceHandler extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		this.addPreferencesFromResource(R.xml.preferences);
		BaseActivity.pref.registerOnSharedPreferenceChangeListener(this);
	}
	public void onSharedPreferenceChanged(SharedPreferences pref, String arg1) {
		// TODO Auto-generated method stub
		;
	}
}
