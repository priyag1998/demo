package android.demo;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements View.OnClickListener{
	//drawing snake and ladder
	private LineView line;
	View view;
	//creating snake and ladder board
	TableLayout table;
    TableRow row[] = new TableRow[10];
    TextView text[] = new TextView[100];
    TextView odds[] = new TextView[10];
    //overall layout
    LinearLayout vertical;
    //for dice layout
    TableLayout horizontal;
    TableRow row1;
    Button dice;
    TextView t1,t2,p1,p2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//creating memory and setting background with opacity
		vertical = new LinearLayout(this);
		vertical.setOrientation(LinearLayout.VERTICAL);
		vertical.setBackgroundResource(R.drawable.bg);
		vertical.setAlpha((float) 0.8);
		t1 = new TextView(this);
		t2 = new TextView(this);
		t1.setPadding(60, 60, 60, 60);
		t1.setGravity(1);
		t2.setPadding(20, 30, 20, 30);
		t2.setGravity(1);
		vertical.addView(t1);
	    table = new TableLayout(this);
	    
	    //adding dice layout
	    horizontal = new TableLayout(this);
	    row1 = new TableRow(this);
	    p1 = new TextView(this);
	    dice = new Button(this);
	    p2 = new TextView(this);
	    
	    TableLayout.LayoutParams llp = new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
	    horizontal.setLayoutParams(llp);
		p1.setTextColor(Color.BLACK);
    	p1.setTextSize(20);
    	p2.setTextColor(Color.BLACK);
    	p2.setTextSize(20);
    	String s1 = "Player 1",s2 = "Player 2",sd="1";
    	SpannableString ss1 = new SpannableString(s1);
        StyleSpan bold = new StyleSpan(Typeface.BOLD);
        ss1.setSpan(bold, 0, s1.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        p1.setText(ss1);
        p1.setPadding(125, 20,125, 20);
        SpannableString ss2 = new SpannableString(s2);
        ss2.setSpan(bold, 0, s2.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        p2.setText(ss2);
        p2.setPadding(125, 20,125, 20);
        SpannableString ssd = new SpannableString(sd);
        ssd.setSpan(bold, 0, 1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        dice.setText(ssd);
        dice.setPadding(40, 10, 40, 10);
        dice.setBackgroundColor(Color.WHITE);
	    row1.addView(p1);
	    row1.addView(dice);
	    row1.addView(p2);
	    horizontal.addView(row1);
	    horizontal.setBackgroundResource(R.drawable.border);
	    
	    //logic for values 1 to 100
	  for(int i=0;i<10;i++)
	       	row[i] = new TableRow(this);
	    for(int i=0;i<100;i++)
	    {
	    	text[i] = new TextView(this);
	    	text[i].setGravity(1);
	    	text[i].setPadding(25, 25, 25, 25);
	    	text[i].setBackgroundResource(R.drawable.border);
	    	text[i].setTextColor(Color.BLACK);
	    	text[i].setTextSize(16);
	    }
	    for(int inc=0;inc<10;inc++)
	    	odds[inc] = new TextView(this);
	    for(int i=100,j=0;i>0;i--,j++)
	    {
	    	String index = Integer.toString(i);
	        SpannableString ss = new SpannableString(index);
	        ss.setSpan(bold, 0, index.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	        text[j].setText(ss);
	        text[j].setId(i);
	    }
	    
	    TableLayout.LayoutParams lp = new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
	    table.setLayoutParams(lp);
	    table.setStretchAllColumns(true);
	    for(int i=0,k=0;i<10;i++) 
	    {
	    	if(i%2==0)
	    	{
	    		for(int j=0;j<10;j++,k++)
	    			row[i].addView(text[k]);
	    	}
	    	//logic to get odd lines to print in reverse
	    	else
	    	{
	    		for(int j=0;j<10;j++,k++)     
	    			odds[j] = text[k];
	    		for(int j=0,p=10;j<10;j++,p--)    
	    			row[i].addView(odds[p-1]);
	    	}
	    }
	    for(int i=0;i<10;i++)
	    	table.addView(row[i]);
	   vertical.addView(table);
	    vertical.addView(t2);
	    
	    dice.setOnClickListener(this); 
	    
	    //get coordinates to draw snake and ladder
	    line = (LineView) findViewById(R.id.lineview);
	  int snake_start[] = new int[] {27,38,52,62,66,90,93,95,97,99};
	   int snake_end[] = new int[] {5,17,33,60,48,70,67,75,58,81};
	    int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
	    PointF pointA,pointB;
	    for(int i=0;i<10;i++)
	    {
//	    	text[snake_start[i]-1].measure(0,0);
//	    	y1 = text[snake_start[i]-1].getMeasuredHeight();
//		    x1 = text[snake_start[i]-1].getMeasuredWidth();
//		    text[snake_end[i]-1].measure(0,0);
//	    	y2 = text[snake_end[i]-1].getMeasuredHeight();
//		    x2 = text[snake_end[i]-1].getMeasuredWidth();
		    pointA = new PointF(x1,y1);
		    pointB = new PointF(x2,y2);
//		    line.setPointA(pointA);
//		    line.setPointB(pointB);
//		    line.draw();
		    final TextView txt_s = text[i];
//		    final TextView txt_e = text[snake_end[i]-1];
		    txt_s.post(new Runnable() {
		        @Override
		        public void run() {
		        	
		            System.out.println(txt_s.getY());
		            System.out.println(txt_s.getX());
		        }
		    });
//		    txt_s.getViewTreeObserver().addOnGlobalLayoutListener(
//		    	    new ViewTreeObserver.OnGlobalLayoutListener() {
//		    	        @Override
//		    	        public void onGlobalLayout() {
//		    	        	 System.out.println(txt_s.getX()+":"+txt_s.getY());
//
//		    	            // Don't forget to remove your listener when you are done with it.
//		    	        	txt_s.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//		    	        }
//		    	    });
//		    txt_e.getViewTreeObserver().addOnGlobalLayoutListener(
//		    	    new ViewTreeObserver.OnGlobalLayoutListener() {
//		    	        @Override
//		    	        public void onGlobalLayout() {
//		    	           System.out.println(txt_e.getX()+":"+txt_e.getY()+"\n");
//
//		    	            // Don't forget to remove your listener when you are done with it.
//		    	        	txt_e.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//		    	        }
//		    	    });
	    }
	    
	    vertical.addView(horizontal);
	    setContentView(vertical);
    } 
  
	 //dice roll
    @Override
    public void onClick(View view) 
    { 
    	Random random = new Random();
    	String num = Integer.toString(random.nextInt(6)+1);
    	dice.setText(num);
    } 
	    
}
