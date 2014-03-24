package com.kero.mama.shopping;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseBooleanArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShoppingActivity extends BaseActivity implements Runnable,OnItemClickListener{
	
	TextView tv;
	ImageView iv;
	Thread t=null;
	List<Data> ladapter;
	ListView lv;
	String link,title,content,price,brand,country,currency,image_link,scountry,nproducts,url_string,up,key;
	int _id=0,nresults=0;
	Darwin darwin;
	String product_type,qbrand,nlink;
	List<String> brands,sbrands;
	List<Boolean> truth;
	Activity a;
	AlertDialog.Builder a1,a2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.lis_display);
        ladapter=new ArrayList<Data>();
        lv=(ListView)findViewById(R.id.listView1);
		darwin=new Darwin(this,ladapter);
		lv.setAdapter(darwin);
		lv.setOnItemClickListener(this);
        Intent i=getIntent();
        final Bundle b=i.getExtras();
        product_type=b.getString("query");
        scountry=b.getString("country");
        nproducts=BaseActivity.pref.getString("rpp", "25");
        url_string="https://www.googleapis.com/shopping/search/v1/public/products?key=AIzaSyC7NqaUudU4R0Oabb72E6diZC4vQZtTo-I&country="+scountry+"&q="+covert(b.getString("query"))+"&maxResults="+nproducts;
        up="&alt=atom";
        brands=new ArrayList<String>();
        sbrands=new ArrayList<String>();
        truth=new ArrayList<Boolean>();
        brands.add("All");
        key="key=AIzaSyC7NqaUudU4R0Oabb72E6diZC4vQZtTo-I";
        qbrand="123";
        a1=new AlertDialog.Builder(this);
		a1.setTitle("Failed to connect to Internet");
		a1.setMessage("Search couldn't be completed due to no connectivity.\nNo results to display.\nPlease search again");
		a1.setPositiveButton("Okay", null);
		a2=new AlertDialog.Builder(this);
		a2.setTitle("SSL Error");
		a2.setMessage("Search couldn't be completed due to no connectivity.\nNo results to display.\nPlease search again");
		a2.setPositiveButton("Okay", null);
        a=this;
        search(url_string+up);
            }
    public void search(final String query_url)
    {
    	 final Handler h=new Handler();
    	 final ProgressDialog pd=ProgressDialog.show(ShoppingActivity.this, "Search in Progress", "Loading search results...Please Wait...",true);
    	 pd.setCancelable(false);
    	 
    	 t=new Thread(new Runnable()
         {
         public void run()
         {
         	try {
     			URL url=new URL(query_url);
     			HttpURLConnection con=(HttpURLConnection) url.openConnection();
     			con.connect();
     			InputStream is=con.getInputStream();
     			InputStreamReader r=new InputStreamReader(is);
     			XmlPullParser parser=Xml.newPullParser();
     			parser.setInput(r);
     			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
     			parser.nextTag();
     			parser.require(XmlPullParser.START_TAG, null, "feed");
     			readfeed(parser);
     		} catch (MalformedURLException e) {
     			// TODO Auto-generated catch block
     			Looper.prepare();
     			h.post(new Runnable()
         		{
         			public void run()
         			{
         				pd.cancel();
         				a1.show();
         			}
         		});
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			Looper.prepare();
     			h.post(new Runnable()
         		{
         			public void run()
         			{
         				pd.cancel();
         				a2.show();
         			}
         		});
     			
     		} catch (XmlPullParserException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
         }
         

 		private void readfeed(XmlPullParser parser) {
 			// TODO Auto-generated method stub
 			
 			try {
 				parser.require(XmlPullParser.START_TAG, null, "feed");
 				while(parser.next()!=XmlPullParser.END_DOCUMENT)
 				{
 					if(parser.getEventType()!=XmlPullParser.START_TAG)
 						continue;
 					String s=parser.getName();
 					if(s.equals("entry")&&(parser.getEventType()==XmlPullParser.START_TAG))
 					{
 						readentry(parser);
 						_id++;
 						Data d=new Data(link,title,content,price,brand,country,currency,_id,image_link);
 						ladapter.add(d);
 					}
 					else if(s.equals("link")&&(parser.getEventType()==XmlPullParser.START_TAG)&&parser.getAttributeValue(0).equals("next"))
 					{
 						nlink=parser.getAttributeValue(2);
 						parser.next();
 					}
 					else if(s.equals("totalResults"))
 					{
 						parser.require(XmlPullParser.START_TAG, "http://a9.com/-/spec/opensearchrss/1.0/", "totalResults");
 						parser.next();
 						nresults=Integer.valueOf(parser.getText());
 						parser.next();
 					}
 					else 
 					{
 						skip(parser);
 					}
 				}
 			} catch (XmlPullParserException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 		}

 		private void skip(XmlPullParser parser) {
 			// TODO Auto-generated method stub
 			try {
 				if(parser.getEventType()!=XmlPullParser.START_TAG)
 					return;
 				int i=1;
 				while(i!=0)
 				{
 					switch(parser.next())
 					{
 					case XmlPullParser.START_TAG: i++;
 												   break;
 					case XmlPullParser.END_TAG: i--;
 												break;
 					}
 				}
 			} catch (XmlPullParserException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 			
 		}

 		private void readentry(XmlPullParser parser) {
 			// TODO Auto-generated method stub
 			try {
 				parser.require(XmlPullParser.START_TAG, null, "entry");
 				while(parser.next()!=XmlPullParser.END_TAG)
 				{
 					if(parser.getEventType()!=XmlPullParser.START_TAG)
 						continue;
 					String s=parser.getName();
 					if(s.equals("title")&&(parser.getEventType()==XmlPullParser.START_TAG))
 					{
 						title=readtext(parser);
 					}
 					else if(s.equals("content")&&(parser.getEventType()==XmlPullParser.START_TAG))
 					{
 						content=readtext(parser);
 					}
 					else if(s.equals("link")&&(parser.getEventType()==XmlPullParser.START_TAG)&&(parser.getAttributeValue(0).equals("alternate")))
 					{
 						link=readtext(parser);
 					}
 					else if(s.equals("product")&&(parser.getEventType()==XmlPullParser.START_TAG)&&(parser.getNamespace().equals("http://www.google.com/shopping/api/schemas/2010")))
 					{
 						readproduct(parser);
 					}
 					else 
 					{
 						skip(parser);
 					}
 				}
 				}
 			 catch (XmlPullParserException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 			
 		}

 		private void readproduct(XmlPullParser parser) {
 			// TODO Auto-generated method stub
 			try {
 				parser.require(XmlPullParser.START_TAG,"http://www.google.com/shopping/api/schemas/2010", "product");
 				while(parser.next()!=XmlPullParser.END_TAG)
 				{
 					if(parser.getEventType()!=XmlPullParser.START_TAG)
 						continue;
 					String s=parser.getName();
 					if(s.equals("country")&&(parser.getEventType()==XmlPullParser.START_TAG))
 					{
 						country=readtext(parser);
 					}
 					else if(s.equals("brand")&&(parser.getEventType()==XmlPullParser.START_TAG))
 					{
 						brand=readtext(parser);
 					}
 					else if(s.equals("inventories")&&(parser.getEventType()==XmlPullParser.START_TAG))
 					{
 						readinventories(parser);
 					}
 					else if(s.equals("images")&&(parser.getEventType()==XmlPullParser.START_TAG))
 					{
 						readimages(parser);
 					}
 					else 
 					{
 						skip(parser);
 					}
 				}
 			} catch (XmlPullParserException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}

 		}

 		private void readimages(XmlPullParser parser) {
 			// TODO Auto-generated method stub
 			try {
 				parser.require(XmlPullParser.START_TAG,"http://www.google.com/shopping/api/schemas/2010", "images");
 				while(parser.next()!=XmlPullParser.END_TAG)
 				{
 					if(parser.getEventType()!=XmlPullParser.START_TAG)
 						continue;
 					String s=parser.getName();
 					if(s.equals("image")&&(parser.getEventType()==XmlPullParser.START_TAG))
 					{
 						image_link=parser.getAttributeValue(0);
 						parser.next();
 						
 					}
 					else 
 					{
 						skip(parser);
 					}
 				}

 			} catch (XmlPullParserException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 			
 		}

 		private void readinventories(XmlPullParser parser) {
 			// TODO Auto-generated method stub
 			try {
 				parser.require(XmlPullParser.START_TAG,"http://www.google.com/shopping/api/schemas/2010", "inventories");
 				while(parser.next()!=XmlPullParser.END_TAG)
 				{
 					if(parser.getEventType()!=XmlPullParser.START_TAG)
 						continue;
 					String s=parser.getName();
 					if(s.equals("inventory")&&(parser.getEventType()==XmlPullParser.START_TAG))
 					{
 						readinventory(parser);
 					}
 					else 
 					{
 						skip(parser);
 					}
 				}

 			} catch (XmlPullParserException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 			
 			
 		}

 		private void readinventory(XmlPullParser parser) {
 			// TODO Auto-generated method stub
 			try {
 				parser.require(XmlPullParser.START_TAG,"http://www.google.com/shopping/api/schemas/2010", "inventory");
 				while(parser.next()!=XmlPullParser.END_TAG)
 				{
 					if(parser.getEventType()!=XmlPullParser.START_TAG)
 						continue;
 					String s=parser.getName();
 					if(s.equals("price")&&(parser.getEventType()==XmlPullParser.START_TAG))
 					{
 						currency=parser.getAttributeValue(null,"currency");
 						price=readtext(parser);
 					}
 					else 
 					{
 						skip(parser);
 					}
 				}

 			} catch (XmlPullParserException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 			
 		}

 		private String readtext(XmlPullParser parser) {
 			// TODO Auto-generated method stub
 			try {
 				if(parser.getEventType()!=XmlPullParser.START_TAG)
 				return null;
 				if(parser.getName().equals("link"))
 				{
 					String s=parser.getAttributeValue(2);
 					parser.next();
 					return(s);
 				}
 				if(parser.next()==XmlPullParser.TEXT)
 				{
 					String s=parser.getText();
 					parser.next();
 					return(s);
 				}
 				
 			} catch (XmlPullParserException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 				
 			return null;
 		}
         });
         t.start();
         Thread t1=new Thread(new Runnable()
         {
         	public void run()
         	{
         		
         		Looper.prepare();
         		while(t.isAlive())
         		{
         			;
         		}
         		h.post(new Runnable()
         		{
         			public void run()
         			{
         				darwin.notifyDataSetChanged();
         				pd.cancel();
         				if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB)
         				a.invalidateOptionsMenu();
         			}
         		});
         	}
         }
         );
         t1.start();
    }
    
	public void run() {
		// TODO Auto-generated method stub
	
	}
	public void handler(View v)
	{
		
	
	}
	public boolean onCreateOptionsMenu(Menu m)
	{
		super.onCreateOptionsMenu(m);
		MenuInflater mi=this.getMenuInflater();
		mi.inflate(R.menu.mit, m);
		return true;
	}
	public boolean onPrepareOptionsMenu(Menu m)
	{
		if(_id==nresults)
			m.removeItem(R.id.more_results);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem mi)
	{
		
		switch(mi.getItemId())
		{
		case R.id.more_results: if(nlink!=null)
								{
								ladapter.clear();
								int pos=nlink.indexOf("?");
								String h=nlink.substring(0, pos+1);
								h+=key+"&"+nlink.substring(pos+1);
								search(h);
								}
								else
								{
									AlertDialog.Builder alert=new AlertDialog.Builder(this);
									alert.setTitle("No more Results");
									alert.setMessage("No more results found in the given product category with the given filters.\nPlease select another catergory or change the filter from the menu");
									alert.setPositiveButton("Okay", null);
									alert.show();
								}
									
		
								return true;
		case R.id.price: 	LayoutInflater li=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
							View v=li.inflate(R.layout.dialog, null);
							AlertDialog.Builder ab=new AlertDialog.Builder(this);
							ab.setView(v);
							final EditText et=(EditText)v.findViewById(R.id.pricer);
							ab.setTitle("Enter Max Price:");
							ab.setPositiveButton("Okay", new DialogInterface.OnClickListener(){
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									String s=et.getText().toString();
									if(s!=null)
									{
									ladapter.clear();
									nlink=null;
									nresults=0;
									_id=0;
									search(url_string+"&restrictBy=price=[0,"+s+"]"+up);
									}
								}
							});
							ab.setNegativeButton("Cancel", null);
							ab.show();
							return true;
		case R.id.brand:	for(int i=0;i<ladapter.size();i++)
							{
								if(ladapter.get(i).brand!=null)
								{
								String br=ladapter.get(i).brand;
								if(!brands.contains(br))
									brands.add(br);
								}	
							}
								if(!sbrands.isEmpty())
									{
									for(int t=0;t<brands.size();t++)
									{
										if(sbrands.contains(brands.get(t)))
											truth.add(true);
										else
											truth.add(false);
									}
									sbrands.clear();
									}
							AlertDialog.Builder abd=new AlertDialog.Builder(this);
							String qw[]=brands.toArray(new String[brands.size()]);
							boolean[] boo=loop(truth);
							abd.setMultiChoiceItems(qw,null, new OnMultiChoiceClickListener()
							{
								public void onClick(DialogInterface dialog,int which,boolean ischecked)
								{
									if(ischecked)
									{
										sbrands.add(brands.get(which));
									}
									else
									{
										sbrands.remove(sbrands.indexOf(brands.get(which)));
									}
								}
							});
							truth.clear();
							abd.setTitle("Select Brands:");
							
							abd.setPositiveButton("Okay", new DialogInterface.OnClickListener()
							{

								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									
										if(sbrands.contains("All"))
										{
											_id=0;
											ladapter.clear();
											nlink=null;
											nresults=0;
											search(url_string+up);
										}
										else
										{
											for(int u=0;u<sbrands.size();u++)
											{
												qbrand+="|"+covert(sbrands.get(u));
											}
											if(qbrand!=null)
											{
											_id=0;
											ladapter.clear();
											nlink=null;
											nresults=0;
											search(url_string+"&restrictBy=brand="+qbrand+up);
											qbrand="123";
											}
										}
								}
								
							});
							abd.setNegativeButton("Cancel", null);
							abd.show();
							return true;
		case R.id.preference: return(super.onOptionsItemSelected(mi));
							
		default: return(false);
		}
	}

	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		// TODO Auto-generated method stub
		Intent i=new Intent(this,Indivisual.class);
		Data d=ladapter.get(position);
		i.putExtra("link",d.link);
		i.putExtra("title", d.title);
		i.putExtra("content", d.content);
		i.putExtra("price", d.price);
		i.putExtra("brand", d.brand);
		i.putExtra("country", d.country);
		i.putExtra("currency", d.currency);
		i.putExtra("image_link", d.image_link);
		i.putExtra("product_type",product_type);
		startActivity(i);
	}
	public void onResume()
	{
		super.onResume();
	}
	public String covert(String s)
	{
		char a[]=s.toCharArray();
		for(int i=0;i<a.length;i++)
		{
			if(a[i]==' ')
				{a[i]='+';}
		}
		s=String.valueOf(a);
		return(s);
	}
	public boolean[] loop(List<Boolean> l)
	{
		final boolean[] primitives = new boolean[l.size()];
	    int index = 0;
	    for (Boolean object :l) {
	        primitives[index++] = object;
	    }
	    return primitives;
	}
	
	
}