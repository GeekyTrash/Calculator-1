package in.manaspaldhe.helloworld;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.widget.GridView;
import android.widget.TextView;

/**
*
* @author anuragjain
*/

public class MainActivity extends Activity {
	static String exp="";
	public final static String EXTRA_MESSAGE = "in.manaspaldhe.helloworld.MESSAGE";
	Actions a;
	TextView textView;
	GridView gridView;
	
	public static String[] button_text = new String[]{
		"7",	"8",	"9",	"<-",
		"4",	"5",	"6",	"/",
		"1",	"2",	"3",	"x",
		"0",	".",	"-",	"+",
		"(",	")",	"CE",	"=",
		"^",		"ln(",		"log(",		"%",
		"sin(",		"cos(",		"tan(",		"sqrt",
		"arcsin(",	"arccos(",	"arctan(",	"test",		
	};

	public static String[] button_text_values = new String[]{
		"7",	"8",	"9",	"<-",
		"4",	"5",	"6",	"/",
		"1",	"2",	"3",	"*",
		"0",	".",	"-",	"+",
		"(",	")",	"CE",	"=",
		"^",		"tanlog(",		"log(",		"%",
		"sin(",		"cos(",		"tan(",		"^0.5",
		"arcsin(",	"arccos(",	"arctan(",	"test",		
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
		textView.setMinimumHeight(height/4);
		
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
			textView.setMinimumHeight(height/4);
	    }
	    if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
			textView.setMinimumHeight(height/3);
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}
}
