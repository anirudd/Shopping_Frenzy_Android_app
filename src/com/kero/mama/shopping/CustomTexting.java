/**
 * 
 */
package com.kero.mama.shopping;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author aniruddh
 *
 */
public class CustomTexting extends TextView {

	Paint p,p1;
	int height;
	int width;
	/**
	 * @param context
	 */
	public CustomTexting(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CustomTexting(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CustomTexting(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		// TODO Auto-generated constructor stub
	}
	public void init()
	{
		p=new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setColor(Color.WHITE);
		p1=new Paint(Paint.ANTI_ALIAS_FLAG);
		p1.setStyle(Paint.Style.FILL_AND_STROKE);
		p1.setColor(Color.BLUE);
	}
	public void onDraw(Canvas c)
	{
		c.drawColor(this.getResources().getColor(R.color.text_background));
		c.drawRect(this.getMeasuredWidth()-58, 0, getMeasuredWidth(), getMeasuredHeight(), p);
		Path path=new Path();
		path.setFillType(Path.FillType.EVEN_ODD);
		path.moveTo(this.getMeasuredWidth()-58, 0);
		path.lineTo(this.getMeasuredWidth()-8, this.getMeasuredHeight()/2);
		path.lineTo(this.getMeasuredWidth()-58, this.getMeasuredHeight());
		path.lineTo(this.getMeasuredWidth()-58, 0);
		path.close();
		c.drawPath(path, p1);
		this.setTextColor(Color.WHITE);
		super.onDraw(c);
	}

	/* (non-Javadoc)
	 * @see android.widget.TextView#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}




}
