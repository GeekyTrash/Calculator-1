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

	public Actions(Context applicationContext, TextView textView, GridView gridView) {
		ctx = applicationContext;
		tv = textView;
		gv = gridView;
	}

	public void performButtonClick(int pos) {
		MainActivity.lastKeyPressed = pos;
		if(pos==19){		// equals button pressed
			RPNCalculator calc = new RPNCalculator(ctx);
			String value="";
			try {
				value=calc.Calculate(MainActivity.exp);
				String display_value;
				if(MainActivity.dec2frac){
					int[] f = RPNCalculator.Fractionize(Double.valueOf(value));
					display_value = f[0]+"/"+f[1];
				}
				else{
					display_value = value;
				}
				tv.setText(MainActivity.exp + " = "+display_value);
				MainActivity.exp = value;
			}
			catch(Exception e){
				tv.setText("Invalid Expression");
				MainActivity.exp="";
			}
		}
		else if(pos==18){
			MainActivity.exp = "";
			tv.setText("");
		}
		else if(pos==3){
			if(MainActivity.exp.length()!=0){
				MainActivity.exp = MainActivity.exp.substring(0,MainActivity.exp.length()-1);
			}
			tv.setText(MainActivity.exp);
		}
		else if((pos==24 || pos==25 || pos==26 || pos==28 || pos==29 || pos==30) && MainActivity.inDegrees){
			MainActivity.exp += MainActivity.button_text_values[pos];
			MainActivity.exp = MainActivity.exp.substring(0,MainActivity.exp.length()-1)+"d(";
         	tv.setText(MainActivity.exp);
		}
		else{
			MainActivity.exp += MainActivity.button_text_values[pos];
			tv.setText(MainActivity.exp);
		}
	}


}
