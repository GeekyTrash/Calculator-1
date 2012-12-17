package in.manaspaldhe.helloworld;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	String exp="";
	public final static String EXTRA_MESSAGE = "in.manaspaldhe.helloworld.MESSAGE";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
  
    public void updateExpression (View v)
    {
      Log.d("string", "updateExpression Called");
      TextView text = (TextView) findViewById(R.id.name);
//      text.setText("HELLO EVERYONE");

      switch (v.getId())
      {
        case R.id.Button01:
        	exp=exp+"/";
         	text.setText(exp);
            break;
        case R.id.Button02:
        	exp=exp+"*";
         	text.setText(exp);
        break;
        case R.id.Button03:
        	exp="";
         	text.setText(exp);
        break;
        case R.id.Button04:
        	exp=exp.substring(0,exp.length()-1);
         	text.setText(exp);
        break;
        case R.id.Button05:
        	exp=exp+"9";
         	text.setText(exp);
            Log.d("string", "9 in");
        break;
        case R.id.Button06:
        	exp=exp+"-";
         	text.setText(exp);
        break;
        case R.id.Button07:
        	exp=exp+"8";
         	text.setText(exp);
        break;
        case R.id.Button08:
        	exp=exp+"7";
         	text.setText(exp);
        break;
        case R.id.Button09:
        	exp=exp+"+";
         	text.setText(exp);
        break;
        case R.id.Button10:
        	exp=exp+"6";
         	text.setText(exp);
        break;
        case R.id.Button11:
        	exp=exp+"5";
         	text.setText(exp);
        break;
        case R.id.Button12:
        	exp=exp+"4";
         	text.setText(exp);
        break;
        case R.id.Button13:
        	exp=exp+"1";
         	text.setText(exp);
        break;
        case R.id.Button14:
        	exp=exp+"2";
         	text.setText(exp);
        break;
        case R.id.Button15:
        	exp=exp+"3";
         	text.setText(exp);
        break;
        case R.id.Button16:
        	exp=exp+".";
         	text.setText(exp);
        break;
        case R.id.Button17:
        	exp=exp+"(";
         	text.setText(exp);
        break;
        case R.id.Button18:
        	exp=exp+"0";
         	text.setText(exp);
        break;
        case R.id.Button19:
        	exp=exp+")";
         	text.setText(exp);
        break;
        case R.id.Button21:
        	exp=exp+"^";
         	text.setText(exp);
        break;
        case R.id.Button22:
        	exp=exp+"cos(";
         	text.setText(exp);
        break;
        case R.id.Button23:
        	exp=exp+"sin(";
         	text.setText(exp);
        break;
        case R.id.Button24:
        	exp=exp+"tan(";
         	text.setText(exp);
        break;
        case R.id.Button25:
        	exp=exp+"arctan(";
         	text.setText(exp);
        break;
        case R.id.Button26:
        	exp=exp+"arcsin(";
         	text.setText(exp);
        break;
        case R.id.Button27:
        	exp=exp+"arccos(";
         	text.setText(exp);
        break;
        case R.id.Button29:
        	exp=exp+"tanlog";
         	text.setText(exp);
        break;
      }
      Log.d("string", "updateExpression Exiting");
    }

    
    public void sendMessage (View view){
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	//EditText editText = (EditText) findViewById(R.id.name);

    	//String message = editText.getText().toString();
    	//Log.d("string", message);
    	RPNCalculator calc = new RPNCalculator();
    	String Value="";
    	try {
    		Value=calc.Calculate(exp);
    	}
    	catch(Exception e){
    		Value="Invalid Expression";
    	}
    	//String Value = "xyz";
    	//Log.d("ans", Value);
    	intent.putExtra(EXTRA_MESSAGE, exp+ " =  " +Value);
        startActivity(intent);
    }

}
