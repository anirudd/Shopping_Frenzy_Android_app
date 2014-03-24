/**
 * 
 */
package com.kero.mama.shopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author aniruddh
 *
 */
public class Indivisual extends Activity {
	Intent i;
	String link,title,content,price,brand,country,currency,image_link,product_type;
	Bundle b;
	TextView tv1,tv2,tv3,tv5,tv6,tv7;
	TextView tv4;
	int flag=0;
	public static ImageView iv;
	public static ProgressBar pg;
	Context c=this;
	public void onCreate(Bundle savedinstancestate)
	{
		super.onCreate(savedinstancestate);
		this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		this.setProgressBarIndeterminateVisibility(true);
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB)
		setContentView(R.layout.indivisual);
		else
		{
			setContentView(R.layout.and);
			pg=(ProgressBar) findViewById(R.id.progressBar1);
			
		}
		tv1=(TextView)findViewById(R.id.textView5);
		tv2=(TextView)findViewById(R.id.textView7);
		tv3=(TextView)findViewById(R.id.textView8);
		tv4=(TextView)findViewById(R.id.textView);
		tv5=(TextView)findViewById(R.id.textView10);
		tv6=(TextView)findViewById(R.id.textView14);
		tv7=(TextView)findViewById(R.id.textView3);
		iv=(ImageView)findViewById(R.id.imageView1);
		i=this.getIntent();
		b=i.getExtras();
		link=b.getString("link");
		title=b.getString("title");
		content=b.getString("content");
		price=b.getString("price");
		brand=b.getString("brand");
		country=b.getString("country");
		currency=b.getString("currency");
		image_link=b.getString("image_link");
		product_type=b.getString("product_type");
		new Synco(this).execute(image_link);
		tv4.setText(title);
		tv1.setText(brand);
		tv2.setText(currency);
		tv3.setText(price);
		tv5.setText(content);
		tv6.setText(link);
		tv7.setText(product_type);
	}

}
