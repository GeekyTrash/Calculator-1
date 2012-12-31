package com.manas.anurag.calculator;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

/**
 *
 * @author anuragjain
 */

public class MainActivity extends Activity {
	static String exp="";
//	public final static String EXTRA_MESSAGE = "in.manaspaldhe.helloworld.MESSAGE";
	Actions a;
	TextView textView;
	GridView gridView;
	static boolean dec2frac = false;
	static boolean inDegrees = false;
	static int lastKeyPressed = 19;
	static String Ans = "";
	

	public static String[] button_text = new String[]{
		"7",	"8",	"9",	"<-",
		"4",	"5",	"6",	"/",
		"1",	"2",	"3",	"x",
		"0",	".",	"-",	"+",
		"(",	")",	"CE",	"=",
		"^",		"ln(",		"log(",		"sqrt(",
		"sin(",		"cos(",		"tan(",		"e",
		"asin(",	"acos(",	"atan(",	"mod",		
		"pi",		"x!",		"abs(",		"cbrt(" 	
	};

	public static String[] button_text_values = new String[]{
		"7",	"8",	"9",	"<-",
		"4",	"5",	"6",	"/",
		"1",	"2",	"3",	"*",
		"0",	".",	"-",	"+",
		"(",	")",	"CE",	"=",
		"^",		"ln(",		"log(",		"sqrt(",
		"sin(",		"cos(",		"tan(",		"e",
		"asin(",	"acos(",	"atan(",	"mod",		
		"PI",		"!",		"abs(",		"cbrt(" 	
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.content);
		textView.setMovementMethod(new ScrollingMovementMethod());
		textView.setText("Expression here");

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		int height = metrics.heightPixels;
		textView.setMinimumHeight(height/5);

		gridView = (GridView) findViewById(R.id.grid);
		a = new Actions(this.getApplicationContext(), textView, gridView);
		gridView.setAdapter(new GridAdapter(this,a)); 

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		int height = metrics.heightPixels;
		int width = metrics.widthPixels;

		Log.i("width height ", Integer.toString(width)+Integer.toString(height));

		if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
			textView.setMinimumHeight(height/5);
		}
		if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
			textView.setMinimumHeight(height/4);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.inDegrees:		//FIXME - degree radian implementation
			inDegrees = !inDegrees;
			if(inDegrees){
				item.setIcon(R.drawable.deg);
			}
			else{
				item.setIcon(R.drawable.rad);
			}
			return true;
		
		case R.id.dec2frac:		//FIXME - fraction decimal view implementation
			dec2frac = !dec2frac;
			if(dec2frac){
				item.setIcon(R.drawable.frac);
				if(lastKeyPressed == 19){
					int[] f = RPNCalculator.Fractionize(Float.valueOf(exp));
					String display_value = f[0]+"/"+f[1];
					textView.setText(exp + " = "+display_value);
				}
			}
			else{
				item.setIcon(R.drawable.dec);
			}
			return true;

		case R.id.last_ans:		//FIXME - degree radian implementation
			exp+=Ans;
			a.tv.setText(MainActivity.exp);
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
