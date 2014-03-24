/**
 * 
 */
package com.kero.mama.shopping;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author aniruddh
 *
 */
public class customtext extends TextView{

	Paint p;
	float size;
	/**
	 * @param context
	 */
	public customtext(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	/* (non-Javadoc)
	 * @see android.widget.TextView#onTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		// TODO Auto-generated method stub
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
	}
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public customtext(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	

	public void init() {
		// TODO Auto-generated method stub
		size=this.getTextSize();
		p=new Paint();
		p.set(this.getPaint());
		p.setColor(Color.BLUE);
		
	}
	public void onDraw(Canvas c)
	{
		super.onDraw(c);
	}
	public customtext(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	
}
