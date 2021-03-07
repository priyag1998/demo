package android.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.PointF;


public class LineView extends View{
	private Paint paint = new Paint();
	private PointF pointA,pointB;
	public LineView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public LineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		paint.setColor(Color.RED);
		paint.setStrokeWidth(10);
		canvas.drawLine(pointA.x,pointA.y,pointB.x,pointB.y,paint);
		super.onDraw(canvas);
	}
	public void setPointA(PointF point)
	{
		pointA = point;
	}
	public void setPointB(PointF point)
	{
		pointB = point;
	}
	public void draw()
	{
		invalidate();
		requestLayout();
	}

}
