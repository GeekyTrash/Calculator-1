package in.manaspaldhe.helloworld;

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
		if(pos==19){		// equals button pressed
			RPNCalculator calc = new RPNCalculator();
			String value="";
			try {
				value=calc.Calculate(MainActivity.exp);
				tv.setText(MainActivity.exp + " = "+value);
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
			MainActivity.exp = MainActivity.exp.substring(0,MainActivity.exp.length()-1);
         	tv.setText(MainActivity.exp);
		}
		else{
			MainActivity.exp += MainActivity.button_text_values[pos];
			tv.setText(MainActivity.exp);
		}
	}


}
