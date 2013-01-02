package com.manas.anurag.calculator;

import android.content.Context;
import android.widget.GridView;
import android.widget.TextView;

/**
 *
 * @author anuragjain
 */

public class Actions {
	Context ctx;
	TextView tv;
	GridView gv;

	boolean first=true;

	public Actions(Context applicationContext, TextView textView, GridView gridView) {
		ctx = applicationContext;
		tv = textView;
		gv = gridView;

	}

	public void performButtonClick(int pos) {

		MainActivity.lastKeyPressed = pos;
		if(pos==19){		// equals button pressed
			RPNCalculator calc = new RPNCalculator();
			String value="";
			try {
				value=calc.Calculate(MainActivity.exp);
				String display_value;
				if(MainActivity.dec2frac){
					String[] f = calc.Calculate_Fraction(MainActivity.exp);
					display_value = f[0]+"/"+f[1];
				}
				else{
					display_value = value;
				}
				MainActivity.Ans=display_value;
				tv.setText(MainActivity.exp + " = "+display_value);
				MainActivity.tempexp = MainActivity.exp;
				MainActivity.exp = value;
				first=true;
			}
			catch(Exception e){
				tv.setText(e.getMessage() + "  <-- to correct");
				MainActivity.exp+=" ";				
			}
		}
		else if(pos==18){
			MainActivity.exp = "";
			tv.setText("");
			first=false;
		}
		else if(pos==3){
			if(MainActivity.exp.length()!=0){
				MainActivity.exp = MainActivity.exp.substring(0,MainActivity.exp.length()-1);
			}
			tv.setText(MainActivity.exp);
			first=false;
		}
		else if((pos==24 || pos==25 || pos==26 || pos==28 || pos==29 || pos==30) && MainActivity.inDegrees){
			MainActivity.exp += MainActivity.button_text_values[pos];
			MainActivity.exp = MainActivity.exp.substring(0,MainActivity.exp.length()-1)+"d(";
			tv.setText(MainActivity.exp);
			first=false;
		}
		else{
			if ( ((pos==3|| pos==7 ||pos==11 || pos==15 ||pos==14 ||pos==13 || pos==33)) || (first==false)){
				MainActivity.exp += MainActivity.button_text_values[pos];
			}
			else{
				MainActivity.exp = MainActivity.button_text_values[pos];				
			}
			tv.setText(MainActivity.exp);
			first=false;
		}
	}
}
