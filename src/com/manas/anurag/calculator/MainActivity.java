package com.manas.anurag.calculator;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Typeface;
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
	static String tempexp="";
	Actions a;
	TextView textView;
	GridView gridView;
	static boolean dec2frac = false;
	static boolean inDegrees = false;
	static int lastKeyPressed = 19;
	static String Ans = "0";


	public static String[] button_text = new String[]{
		"7",	"8",	"9",	"<-",
		"4",	"5",	"6",	"/",
		"1",	"2",	"3",	"x",
		".",	"0",	"-",	"+",
		"(",	")",	"CE",	"=",
		"ln(",		"log(",		"^",		"\u221A(",
		"sin(",		"cos(",		"tan(",		"\u221B(",
		"asin(",	"acos(",	"atan(",	"x!",		
		"\u03C0",	"e",		"mod",		"abs(" 	
	};

	public static String[] button_text_values = new String[]{
		"7",	"8",	"9",	"<-",
		"4",	"5",	"6",	"/",
		"1",	"2",	"3",	"*",
		".",	"0",	"-",	"+",
		"(",	")",	"CE",	"=",
		"ln(",		"log(",		"^",		"\u221A(",
		"sin(",		"cos(",		"tan(",		"\u221B(",
		"asin(",	"acos(",	"atan(",	"!",		
		"\u03C0",	"e",		"mod",		"abs(" 	
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		Typeface font= Typeface.createFromAsset(getAssets(), "fonts/DejaVuSerif.ttf");

		textView = (TextView) findViewById(R.id.content);
		textView.setTypeface(font); 
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
		case R.id.inDegrees:		//degree radian implementation
			inDegrees = !inDegrees;
			if(inDegrees){
				item.setIcon(R.drawable.deg);
				// add "d" after all sin and cos terms
				exp=exp.replace("sin", "sind");
				exp=exp.replace("cos", "cosd");
				exp=exp.replace("tan", "tand");
				a.tv.setText(MainActivity.exp);
			}
			else{
				item.setIcon(R.drawable.rad);
				// remove "d" after all sind and cosd terms
				exp=exp.replace("sind", "sin");
				exp=exp.replace("cosd", "cos");
				exp=exp.replace("tand", "tan");
				a.tv.setText(MainActivity.exp);
			}
			return true;

		case R.id.dec2frac:		// fraction decimal view implementation
			dec2frac = !dec2frac;
			if(dec2frac){
				item.setIcon(R.drawable.frac);
				if(lastKeyPressed == 19 && exp.length()>0){
					//int[] f = RPNCalculator.Fractionize(Float.valueOf(exp));
					RPNCalculator calc = new RPNCalculator();
					try {
						String[] f = calc.Calculate_Fraction(tempexp);
						String display_value = f[0]+"/"+f[1];
						textView.setText(tempexp + " = "+display_value);
						MainActivity.exp = display_value;
					}
					catch(Exception e){
						a.tv.setText(e.getMessage() + "  <-- to correct");
						MainActivity.exp+=" ";				
					}					
				}
			}
			else{
				item.setIcon(R.drawable.dec);				
				if(lastKeyPressed == 19 && exp.length()>0){
					//int[] f = RPNCalculator.Fractionize(Float.valueOf(exp));
					RPNCalculator calc = new RPNCalculator();
					try {
						String f = calc.Calculate(tempexp);
						String display_value = f;
						textView.setText(tempexp + " = "+f);
						MainActivity.exp = display_value;
					}
					catch(Exception e){
						a.tv.setText(e.getMessage() + "  <-- to correct");
						MainActivity.exp+=" ";				
					}					
				}
			}
			return true;

		case R.id.last_ans:		
			exp = exp+"ANS";
			a.tv.setText(MainActivity.exp);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
