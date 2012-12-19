package in.manaspaldhe.helloworld;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 *
 * @author anuragjain
 */

public class GridAdapter extends BaseAdapter{
	private Context ctx;
	private Actions a;

	public GridAdapter(Context c, Actions a){
		ctx = c;
		this.a = a;
	}

	@Override
	public int getCount() {
		return MainActivity.button_text.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Button btn;        
		if (convertView == null) {          
			btn = new Button(ctx); 
			final int pos = position;

			btn.setOnClickListener(new View.OnClickListener() { 
				public void onClick(View v) { 
					a.performButtonClick(pos);
				} 
			}); 
			btn.setPadding(1, 1, 1, 1);
		} 
		else 
		{           
			btn = (Button) convertView;       
		}       
		btn.setText(MainActivity.button_text[position]);  
		btn.setBackgroundResource(R.drawable.button);
		btn.setTextColor(Color.LTGRAY);
		return btn;    	
	}
}
