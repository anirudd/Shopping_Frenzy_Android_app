/**
 * 
 */
package com.kero.mama.shopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * @author aniruddh
 *
 */

public class Welcome_class extends Activity {
	private Thread t;
	Handler h;
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		setContentView(R.layout.welcome);
		h=new Handler();
		t=new Thread(new Runnable()
		{
			public void run()
			{
				h.postDelayed(new Runnable()
				{
					public void run()
					{
						Intent i=new Intent(Welcome_class.this,Main_Class.class);
						startActivity(i);
						Welcome_class.this.finish();
						
					}
				}, 2130);
				
			}
		});
		t.start();
		
	}

}
