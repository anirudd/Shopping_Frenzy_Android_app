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
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

/**
 * @author aniruddh
 *
 */
//hhkdf
public class Main_Class extends BaseActivity implements OnItemClickListener{
	ListView lv;
	TextView tv;
	MultiAutoCompleteTextView m;
	Core c;
	ArrayList<String> al;
	Animation an;
	int menu_id,prev;
	String s;
	String temp1,temp2,temp3,provider,country,tempr,tempr1="";
	LocationProvider lp;
	LocationManager lm;
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		setContentView(R.layout.main_list);
		al=new ArrayList<String>();
		al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.categories)));
		c=new Core(this,al);
		lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(c);
		lv.setDividerHeight(10);
		lv.setOnItemClickListener(this);
		an=AnimationUtils.loadAnimation(this, R.anim.animation);
		//save.putInt("menu_id", 1);
		menu_id=1;
		prev=-1;
		tv=(TextView)findViewById(R.id.textView1);
		tempr="";
		tempr1="";
	}
		
	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			if(menu_id>1)
			{
			menu_id--;
			if(menu_id==1)
			{
				tempr="";
				tempr1="";
				al.clear();
				al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.categories)));
				tv.setText("Select Product Category:");
				lv.startAnimation(an);
				c.notifyDataSetChanged();
				return true;
			}
			process(menu_id-1,prev);
			return true;
			}
			else
			{
				return super.onKeyDown(keyCode, event);
			}
				
		}
		return super.onKeyDown(keyCode, event);
	}

	public void onSaveInstanceState(Bundle save)
	{
		super.onSaveInstanceState(save);
		save.putInt("menu_id", menu_id);
		save.putInt("prev", prev);
	}
	public void onRestoreInstanceState(Bundle save)
	{
		super.onRestoreInstanceState(save);
		menu_id=save.getInt("menu_id", 1);
		prev=save.getInt("prev", 0);
	}
	public void onPause(Bundle save)
	{
		super.onPause();
		save.putInt("menu_id", menu_id);
		save.putInt("prev", prev);
	}
	public void onResume(Bundle save)
	{
		super.onResume();
		menu_id=save.getInt("menu_id", 1);
		prev=save.getInt("prev", 0);
	}
	public void onItemClick(AdapterView<?> av, View v, int position, long id) {
		// TODO Auto-generated method stub
		process(menu_id,position);
		if(!(menu_id==1&&position==0))
		{
			this.menu_id++;
		}
		
		
	}
	public void process(int menu_id,int position)
	{
		switch(menu_id)
		{
		
		case 1: if(position!=0)
				{
				tv.setText(this.getResources().getStringArray(R.array.categories)[position]+":");
				al.clear();}
				switch(position)
					{
					case 0: {Intent i4=new Intent(this,Custom.class);
							startActivity(i4);
							return;
							}
						case 1:
						{
							
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_appANDacc)));
							break;
						}
						case 2:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_artsANDent)));
							break;
						}
						case 3:
						{		al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_babyANDtod)));
								break;
						}
						case 4:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_bussANDind)));
							break;
						}
						case 5:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_camANDoptics)));
							break;
						}
						case 6:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_electronics)));
							break;
						}
						case 7:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_furniture)));
							break;
						}
						case 8:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_helthANDbeut)));
							break;
						}
						case 9:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_homeNgard)));
							break;
						}
						case 10:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_luggANDbags)));
							break;
						}
						case 11:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_offsupplies)));
							break;
						}
						case 12:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_software)));
							break;
						}
						case 13:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_sportingGoods)));
							break;
						}
						case 14:
						{
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_vehicle_acc)));
							break;
						}
					}
					break;
		case 2:	if(tempr.equals(""))
					{
							temp1=(String) tv.getText();
					}
				else
					temp1=tempr;
				if(temp1.equals("Apparel & Accessories:")||tempr.equals("Apparel & Accessories:"))
				{ prev=1;
				tv.setText(this.getResources().getStringArray(R.array.sub_appANDacc)[position]+":");
					switch(position)
					{
						case 0:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_clothing)));	
							break;
						}
						case 1:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_clothing_acc)));	
							break;
						}
						case 2:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_handbags)));	
							break;
						}
						case 3:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_jwelery)));	
							break;
						}
						case 4:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_shoes)));	
							break;
						}
						default:
		 				{
		 					tv.setText(temp1);
		 					createRequest(al.get(position));   return;      
		 				}
		
					}
				}
				else if(temp1.equals("Arts & Entertainment:")||tempr.equals("Arts & Entertainment:"))
				{prev=2;
				tv.setText(this.getResources().getStringArray(R.array.sub_artsANDent)[position]+":");
					switch(position)
					{
						case 0:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_artwork)));	
							break;
						}
						case 1:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_crafts_hobbies)));	
							break;
						}
						case 2:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_musical_instruments)));	
							break;
						}
						case 3:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_musical_instruments_acc)));	
							break;
						}
						default:
		 				{
		 					tv.setText(temp1);
		 					createRequest(al.get(position));   return;      
		 					
		 				}
					}

				}
				else if(temp1.equals("Business & Industrial:")||tempr.equals("Business & Industrial:"))
				{prev=4;
				tv.setText(this.getResources().getStringArray(R.array.sub_bussANDind)[position]+":");
					switch(position)
					{
						case 0:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_advertising_markt)));	
							break;
						}
						case 1:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_food_service)));	
							break;
						}
						case 2:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_heavy_mch)));	
							break;
						}
						case 3:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_inds_storage)));	
							break;
						}
						case 4:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_law_enf)));	
							break;
						}
						case 5:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_medical)));	
							break;
						}
						case 6:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_retail)));	
							break;
						}
						default:
		 				{
		 					tv.setText(temp1);
		 					createRequest(al.get(position));   return;
		 					
		 				}
					}

				}
				else if(temp1.equals("Cameras & Optics:")||tempr.equals("Cameras & Optics:"))
				{prev=5;
				tv.setText(this.getResources().getStringArray(R.array.sub_camANDoptics)[position]+":");
					switch(position)
					{
						case 0:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_camera_optic_acc)));	
							break;
						}
						case 1:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_cameras)));	
							break;
						}
						case 2:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_optics)));	
							break;
						}
						case 3:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_photography)));	
							break;
						}
						default:
		 				{
		 					tv.setText(temp1);
		 					createRequest(al.get(position));  
		 					return;
		 				}
					}

				}
				else if(temp1.equals("Electronics:")||tempr.equals("Electronics:"))
				{prev=6;
				tv.setText(this.getResources().getStringArray(R.array.sub_electronics)[position]+":");
					switch(position)
					{
						case 0:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_audio)));	
							break;
						}
						case 1:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_circuit_comp)));	
							break;
						}
						case 2:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_communications)));	
							break;
						}
						case 3:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_computers)));	
							break;
						}
						case 4:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_elec_acc)));	
							break;
						}
						case 5:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_gps)));	
							break;
						}
						case 6:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_networking)));	
							break;
						}
						case 7:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_print)));	
							break;
						}
						case 8:
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_video_pp)));	
							break;
						}
						default:
		 				{
		 					tv.setText(temp1);
		 					createRequest(al.get(position));   return;
		 					
		 				}
					}
				}
				else if(temp1.equals("Vehicle Accessories:")||tempr.equals("Vehicle Accessories:"))
				{tv.setText(this.getResources().getStringArray(R.array.sub_vehicle_acc)[position]+":");
					switch(position)
					{
					case 0: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_carAudANDVid)));	
						break;
					}
					case 2: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_motor_lock)));	
						break;
					}
					case 5: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_motor_protective)));	
						break;
					}
					default:
	 				{
	 					tv.setText(temp1);
	 					createRequest(al.get(position));   
	 					return;  
	 				}
					}
				}
				else if(temp1.equals("Furniture:")||tempr.equals("Furniture:"))
				{prev=7;
				tv.setText(this.getResources().getStringArray(R.array.sub_furniture)[position]+":");
					switch(position)
					{
					case 0: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_bedroom_furniture)));	
						break;
					}
					case 1: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_benches)));	
						break;
					}
					case 2: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_carts)));	
						break;
					}
					case 3: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_chairs)));	
						break;
					}
					case 4: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_desk_tables)));	
						break;
					}
					case 7: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_sofas)));	
						break;
					}
					default:
	 				{
	 					tv.setText(temp1);
	 					createRequest(al.get(position));   return;  
	 					
	 				}
					}
				}
				else if(temp1.equals("Health & Beauty:")||tempr.equals("Health & Beauty:"))
				{prev=8;
				tv.setText(this.getResources().getStringArray(R.array.sub_helthANDbeut)[position]+":");
					switch(position)
					{
					case 1: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_cosmetics)));	
						break;
					}
					case 3: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_hair_care)));	
						break;
					}
					case 5: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_shaving)));	
						break;
					}
					default:
	 				{
	 					tv.setText(temp1);
	 					createRequest(al.get(position));   
	 					return;  
	 				}
					}
				}
				else if(temp1.equals("Household Goods:")||tempr.equals("Household Goods:"))
				{prev=9;
				tv.setText(this.getResources().getStringArray(R.array.sub_homeNgard)[position]+":");
					switch(position)
					{
					case 0: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_emergency_safety)));	
						break;
					}
					case 1: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_home_security)));	
						break;
					}
					case 2: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_house_hold_app)));	
						break;
					}
					case 3: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_kitchen_dining)));	
						break;
					}
					case 4: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_lighting)));	
						break;
					}
					case 5: 
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_linens)));	
						break;
					}
					default:
	 				{
	 					tv.setText(temp1);
	 					createRequest(al.get(position));   return;  
	 					
	 				}
					}
				}
				else if(temp1.equals("Software:")||tempr.equals("Software:"))
				{prev=12;
				tv.setText(this.getResources().getStringArray(R.array.sub_software)[position]+":");
					switch(position)
					{
					case 3:	
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_multimedia_software)));	
							break;
						}
					default:
	 				{
	 					tv.setText(temp1);
	 					createRequest(al.get(position));   return;  
	 					
	 				}
					}
				}
				else if(temp1.equals("Sporting Goods:")||tempr.equals("Sporting Goods:"))
				{prev=13;
				tv.setText(this.getResources().getStringArray(R.array.sub_sportingGoods)[position]+":");
					switch(position)
					{
					case 0:	
						{
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_combat_sports)));	
							break;
						}
					case 1:	
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_exercise_fitness)));	
						break;
					}
					case 2:	
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_indoor_sports)));	
						break;
					}
					case 3:	
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_outdoor)));	
						break;
					}
					case 4:	
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_racket)));	
						break;
					}
					case 5:	
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_team_sports)));	
						break;
					}
					case 6:	
					{
						al.clear();
						al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_water_sports)));	
						break;
					}
					default:
	 				{
	 					tv.setText(temp1);
	 					createRequest(al.get(position));   return;  
	 					
	 				}
					}
				}
				else
				{
					tv.setText(temp1);
					createRequest(al.get(position));   return;   
				}
				tempr="";
				break;
		case 3:if(tempr1.equals(""))
				{
					temp2= (String) tv.getText();
				}
				else 
				{
					temp2=tempr1;
				}
				
		 		tempr=temp1;
		 		if(temp1.equals("Apparel & Accessories:")||tempr.equals("Apparel & Accessories:"))
		 		{
		 			if(temp2.equals("Clothing:")||tempr1.equals("Clothing:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_clothing)[position]+":");
		 				switch(position)
		 			{
		 				case 0:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_active_wear)));
		 					break;
		 				}
		 				case 1:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_bridal_clothing)));	
							break;
		 				}
		 				case 2:
						{	prev=0;
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_babyANDtod)));	
							break;
						}
		 				case 3:
						{	prev=0;
							al.clear();
							al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_dresses)));	
							break;
						}
		 				case 4:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_outerwear)));	
		 					break;
		 				}
		 				case 5:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_pants)));	
		 					break;
		 				}
		 				case 6:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_shirtsANDtops)));	
		 					break;
		 				}
		 				case 10:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_sleepANDlong)));	
		 					break;
		 				}
		 				case 11:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_suits)));	
		 					break;
		 				}
		 				case 12:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_uniforms)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;   
		 					
		 				}

		 			}
		 		}
		 			else if(temp2.equals("Shoes:")||tempr1.equals("Shoes:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_shoes)[position]+":");
		 				switch(position)
		 				{
		 				case 0:
		 				{	prev=4;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_athl_shoes)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   
		 					return;   
		 				}
		 				
		 				}
		 			}
		 			else
		 			{
		 				tv.setText(temp2);
		 				createRequest(al.get(position));   return;
		 			}
		 			
		 	}
		 		else if(temp1.equals("Arts & Entertainment:")||tempr.equals("Arts & Entertainment:"))
		 		{
		 			if(temp2.equals("Musical Instruments:")||tempr1.equals("Musical Instruments:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_musical_instruments)[position]+":");
		 				switch(position)
		 				{
		 				case 2:
		 				{	prev=2;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_brass)));	
		 					break;
		 				}
		 				case 4:
		 				{	prev=2;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_percussion)));	
		 					break;
		 				}
		 				case 6:
		 				{	prev=2;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_string_instruments)));	
		 					break;
		 				}
		 				case 7:
		 				{	prev=2;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_woodwinds)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;   
		 					
		 				}
		 				
		 				}
		 			}
		 			else if(temp2.equals("Musical Instrument Accessories:")||tempr1.equals("Musical Instrument Accessories:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_musical_instruments_acc)[position]+":");
		 				switch(position)
		 				{
		 				case 0:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_brass_ins_acc)));	
		 					break;
		 				}
		 				case 10:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_string_ins_acc)));	
		 					break;
		 				}
		 				case 11:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_woodwind_acc)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;
		 					
		 				}
		 				
		 				}
		 			}
		 			else
		 			{
		 				tv.setText(temp2);
		 				createRequest(al.get(position));   return;   
		 				
		 			}
		 		}
		 		else if(temp1.equals("Business & Industrial:")||tempr.equals("Business & Industrial:"))
		 		{
		 			if(temp2.equals("Retail:")||tempr1.equals("Retail:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_retail)[position]+":");
		 				switch(position)
		 				{
		 				case 2:
		 				{	prev=6;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_money_handling)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;  
		 					
		 				}
		 				}
		 			}
		 			else
		 			{
		 				tv.setText(temp2);
		 				createRequest(al.get(position));   return;   
		 			}
		 		}
		 		else if(temp1.equals("Cameras & Optics:")||tempr.equals("Cameras & Optics:"))
		 		{
		 			if(temp2.equals("Camera & Optic Accessories:")||tempr1.equals("Camera & Optic Accessories:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_camera_optic_acc)[position]+":");
		 				switch(position)
		 				{
		 				case 0:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_camera_optic_lens_acc)));	
		 					break;
		 				}
		 				case 1:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_camera_optic_lenses)));	
		 					break;
		 				}
		 				case 2:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_cam_acc)));	
		 					break;
		 				}
		 				case 4:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_video_acc)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;
		 					
		 				}
		 				}
		 			}
		 			else if(temp2.equals("Photography:")||tempr1.equals("Photography:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_photography)[position]+":");
		 				switch(position)
		 				{
		 				case 0:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_darkroom)));	
		 					break;
		 				}
		 				case 1:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_lighting_studio)));	
		 					break;
		 				}
		 				}
		 			}
		 			else
		 			{
		 				tv.setText(temp2);
		 				createRequest(al.get(position));   
		 				return;  
		 			}
		 		}
		 		else if(temp1.equals("Electronics:")||tempr.equals("Electronics:"))
		 		{
		 			if(temp2.equals("Audio:")||tempr1.equals("Audio:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_audio)[position]+":");
		 				switch(position)
		 				{
		 				case 0:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_audio_components)));	
		 					break;
		 				}
		 				case 1:
		 				{	prev=0;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_audio_player_recorder)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));
		 					return;   
		 				
		 				}
		 				}
		 			}
		 			else if(temp2.equals("Computers:")||tempr1.equals("Computers:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_computers)[position]+":");
		 				switch(position)
		 				{
		 				case 0:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_computer_acc)));	
		 					break;
		 				}
		 				case 1:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_computer_comp)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return; 
		 					
		 				}
		 				}
		 			}
		 			else if(temp2.equals("Electronics Accessories:")||tempr1.equals("Electronics Accessories:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_elec_acc)[position]+":");
		 				switch(position)
		 				{
		 				case 0:
		 				{	prev=4;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_antenas)));	
		 					break;
		 				}
		 				case 1:
		 				{	prev=4;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_cables)));	
		 					break;
		 				}
		 				case 4:
		 				{	prev=4;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_memory)));	
		 					break;
		 				}
		 				case 5:
		 				{	prev=4;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_power)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return; 
		 					
		 				}
		 				}
		 			}
		 			else if(temp2.equals("Video:")||tempr1.equals("Video:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_video_pp)[position]+":");
		 				switch(position)
		 				{
		 				case 1:
		 				{	prev=8;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_projectors)));	
		 					break;
		 				}
		 				case 2:
		 				{	prev=8;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_television)));	
		 					break;
		 				}
		 				case 3:
		 				{	prev=8;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_video_player_acc)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;   
		 					
		 				}
		 				}
		 			}
		 			else
		 			{
		 				tv.setText(temp2);
		 				createRequest(al.get(position));   return;    
		 			}
		 		}
		 		else if(temp1.equals("Household Goods:")||tempr.equals("Household Goods:"))
		 		{
		 			if(temp2.equals("Household Appliances:")||tempr1.equals("Household Appliances:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_house_hold_app)[position]+":");
		 				switch(position)
		 				{
		 				case 1:
		 				{	prev=2;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_climate_control)));	
		 					break;
		 				}
		 				case 5:
		 				{	prev=2;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_laundry_app)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;  
		 					
		 				}
		 				}
		 			}
		 			else if(temp2.equals("Kitchen & Dining:")||tempr1.equals("Kitchen & Dining:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_kitchen_dining)[position]+":");
		 				switch(position)
		 				{
		 				case 6:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_kitchen_app)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;
		 				}
		 				}
		 			}
		 			else
		 			{
		 				tv.setText(temp2);
		 				createRequest(al.get(position));   return;   
		 			}
		 		}
		 		else if(temp1.equals("Sporting Goods:")||tempr.equals("Sporting Goods:"))
		 		{
		 			if(temp2.equals("Outdoor Recreation:")||tempr1.equals("Outdoor Recreation:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_outdoor)[position]+":");
		 				switch(position)
		 				{
		 				case 0:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_archery)));	
		 					break;
		 				}
		 				case 2:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_cycling)));	
		 					break;
		 				}
		 				case 3:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_golf)));	
		 					break;
		 				}
		 				case 4:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_shooting_games)));	
		 					break;
		 				}
		 				case 8:
		 				{	prev=3;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_track_field)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;   
		 					
		 				}
		 				}
		 			}
		 			else if(temp2.equals("Racquet Sports:")||tempr1.equals("Racquet Sports:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_racket)[position]+":");
		 				switch(position)
		 				{
		 				case 0:
		 				{	prev=4;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_badminton)));	
		 					break;
		 				}
		 				case 1:
		 				{	prev=4;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_squash)));	
		 					break;
		 				}
		 				case 2:
		 				{	prev=4;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_tennis)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;   
		 					
		 				}
		 				}
		 			}
		 			else if(temp2.equals("Team Sports:")||tempr1.equals("Team Sports:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_team_sports)[position]+":");
		 				switch(position)
		 				{
		 				case 0:
		 				{	prev=5;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_baseball)));	
		 					break;
		 				}
		 				case 1:
		 				{	prev=5;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_basket_ball)));	
		 					break;
		 				}
		 				case 2:
		 				{	prev=5;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_volleyball)));	
		 					break;
		 				}
		 				case 3:
		 				{	prev=5;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_cricket)));	
		 					break;
		 				}
		 				case 4:
		 				{	prev=5;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_hockey)));	
		 					break;
		 				}
		 				case 5:
		 				{	prev=5;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_rugby)));	
		 					break;
		 				}
		 				case 6:
		 				{	prev=5;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_soccer)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;   
		 					
		 				}
		 				}
		 			}
		 			else if(temp2.equals("Water Sports:")||tempr1.equals("Water Sports:"))
		 			{
		 			tv.setText(this.getResources().getStringArray(R.array.sub_water_sports)[position]+":");
		 				switch(position)
		 				{
		 				case 5:
		 				{	prev=6;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_surfing)));	
		 					break;
		 				}
		 				case 6:
		 				{	prev=6;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_swimming)));	
		 					break;
		 				}
		 				case 7:
		 				{	prev=6;
		 					al.clear();
		 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_water_polo)));	
		 					break;
		 				}
		 				default:
		 				{
		 					tv.setText(temp2);
		 					createRequest(al.get(position));   return;   
		 					
		 				}
		 				}
		 			}
		 			else
			 		{
			 			tv.setText(temp2);
			 			createRequest(al.get(position));   return;   
			 		}
		 		}
		 		else
		 		{
		 			tv.setText(temp2);
		 			createRequest(al.get(position));   return;   
		 		}
		 		tempr1="";
		break;
		case 4: temp3=(String) tv.getText();
				tv.setText(al.get(position)+":");
				if(temp1.equals("Apparel & Accessories:"))
				{
					if(temp2.equals("Clothing:"))
					{
						if(temp3.equals("Uniforms:"))
						{
						
							switch(position)
							{
							case 4:	prev=12;
							tempr=temp1;
							tempr1=temp2;
									al.clear();
									al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_sportsUniforms)));
									break;
							default:tv.setText(temp3); 
									createRequest(al.get(position));   return;
									 
							}
							
						}
						else
						{
							tv.setText(temp2);
							createRequest(al.get(position));   return;  
							
						}
					}
					else
					{
						tv.setText(temp1);
						createRequest(al.get(position));   return; 
						
					}
				}
				else if(temp1.equals("Arts & Entertainment:"))
				{
					if(temp2.equals("Musical Instrument Accessories:"))
					{
						if(temp3.equals("String Instrument Accessories:"))
						{
						
							switch(position)
			 				{
			 				case 1:
			 				{	prev=10;
			 				tempr=temp1;
							tempr1=temp2;
			 					al.clear();
			 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_cello_acc)));	
			 					break;
			 				}
			 				case 2:
			 				{	prev=10;
			 				tempr=temp1;
							tempr1=temp2;
			 					al.clear();
			 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_guitar_acc)));	
			 					break;
			 				}
			 				case 6:
			 				{	prev=10;
			 				tempr=temp1;
							tempr1=temp2;
			 					al.clear();
			 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_viola_acc)));	
			 					break;
			 				}
			 				case 7:
			 				{	prev=10;
			 				tempr=temp1;
							tempr1=temp2;
			 					al.clear();
			 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_violin_acc)));	
			 					break;
			 				}
			 				default:
			 				{
			 					 tv.setText(temp3);
			 					createRequest(al.get(position));   return;     
			 				}
			 				
			 				}
						}
						else
						{
							tv.setText(temp2);
							createRequest(al.get(position));   return;
						}
					}
					else
					{
						tv.setText(temp1);
						createRequest(al.get(position));   return;
					}
				}
				else if(temp1.equals("Electronics:"))
				{
					if(temp2.equals("Computers:"))
					{
						if(temp3.equals("Computer Components:"))
						{
						
							switch(position)
			 				{
			 				case 2:
			 				{	prev=1;
			 					tempr=temp1;
			 					tempr1=temp2;
			 					al.clear();
			 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_input_output_cards)));	
			 					break;
			 				}
			 				case 3:
			 				{	prev=1;
			 				tempr=temp1;
							tempr1=temp2;
			 					al.clear();
			 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_input_devices)));	
			 					break;
			 				}
			 				case 4:
			 				{	prev=1;
			 				tempr=temp1;
							tempr1=temp2;
			 					al.clear();
			 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_laptop_parts)));	
			 					break;
			 				}
			 				case 6:
			 				{	prev=1;
			 				tempr=temp1;
							tempr1=temp2;
			 					al.clear();
			 					al.addAll(Arrays.asList(this.getResources().getStringArray(R.array.sub_storage_device)));	
			 					break;
			 				}
			 				default:
			 				{
			 					tv.setText(temp3);
			 					createRequest(al.get(position));   return;
			 				}
			 				
			 				}
						}
						else
						{
							tv.setText(temp3);
							createRequest(al.get(position));   return;
						}
					}
					else
					{
						tv.setText(temp2);
						createRequest(al.get(position));   return;
					}
				}
				
				
				
				else
				{
					tv.setText(temp2);
					createRequest(al.get(position));   return;
				}
				break;
				
		default:
		{
			createRequest(al.get(position));
			break;
		}
	}
	
		lv.startAnimation(an);
		c.notifyDataSetChanged();
	
	}
	public void createRequest(String query)
	{
		ConnectivityManager cm=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni=cm.getActiveNetworkInfo();
		if(ni!=null)
		{
		String country;
		country=BaseActivity.pref.getString("country", "US");
		Intent i=new Intent(this,ShoppingActivity.class);
		i.putExtra("country", country);
		i.putExtra("query", query);
		startActivity(i);
		menu_id--;
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
			menu_id--;
		}
	}
	public void onActivityResult(Intent i,int resultcode,int requestcode)
	{
		;
	}

}
