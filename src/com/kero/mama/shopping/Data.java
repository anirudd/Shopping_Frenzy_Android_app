package com.kero.mama.shopping;

public class Data {
	String link,title,content,price,brand,country,currency,image_link;
	int _id;
	
	public Data(String link,String title,String content,String price, String brand,String country,String currency,int _id,String image_link)
	{
		this.link=link;
		this.title=title;
		this.content=content;
		this.price=price;
		this.brand=brand;
		this.country=country;
		this.currency=currency;
		this._id=_id;
		this.image_link=image_link;
	}
	public String toString()
	{
		return(link+" "+title+" "+content+" "+price+" "+brand+" "+country+" "+currency+"  "+_id);
	}

}
