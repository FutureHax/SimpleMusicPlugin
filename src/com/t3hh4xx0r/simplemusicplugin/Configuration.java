package com.t3hh4xx0r.simplemusicplugin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class Configuration extends LinearLayout {
	Context ctx;
	EditText et;
	String ON = "On all the time.";
	String OFF = "On only when triggered.";
	
	public Configuration(Context context) {
		super(context);
		init(context);
	}

	public Configuration(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public Configuration(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context c) {	
		ctx = c;
		final SharedPreferences prefs = c.getSharedPreferences(MainActivity.getPrefsKey(c), Context.MODE_WORLD_WRITEABLE);

        LayoutInflater layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.config, this);		
		
		ToggleButton b = (ToggleButton) findViewById(R.id.toggle);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean status = ((ToggleButton) v).getText().toString().equals(ON);
				prefs.edit().putBoolean("alwaysShow", status).apply();
				((ToggleButton) v).setText(prefs.getBoolean("alwaysShow", false) ? 
						ON : OFF);

			}	
		});
		b.setText(prefs.getBoolean("alwaysShow", false) ? 
				ON : OFF);
		b.setChecked(prefs.getBoolean("alwaysShow", false));
	}
}
