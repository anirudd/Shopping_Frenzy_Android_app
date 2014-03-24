/**
 * 
 */
package com.kero.mama.shopping;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * @author aniruddh
 *
 */
public class Synco extends AsyncTask<String, Void, Bitmap> {

	String link;
	Bitmap b;
	Activity a;
	public Synco(Activity a)
	{
		this.a=a;
	}
	@Override
	protected Bitmap doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		link=arg0[0];
		b=downloadimage(link);
		return(b);
	}
	private Bitmap downloadimage(String link) {
		// TODO Auto-generated method stub
		try {
			URL url=new URL(link);
			URLConnection ucon=url.openConnection();
			InputStream is=ucon.getInputStream();
			Bitmap bitmap=BitmapFactory.decodeStream(is);
			return(bitmap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public void onPostExecute(Bitmap b)
	{
		if(b!=null)
		{
			Indivisual.iv.setImageBitmap(b);
		}
		else 
		{
			Toast.makeText(a, "Image not available", Toast.LENGTH_LONG);
			Indivisual.iv.setImageResource(R.drawable.avail);
		}
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB)
		a.setProgressBarIndeterminateVisibility(false);
		else
		{
			Indivisual.pg.setVisibility(ProgressBar.GONE);
		}
			
	}

}
