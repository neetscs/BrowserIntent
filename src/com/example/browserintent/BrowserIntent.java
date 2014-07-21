package com.example.browserintent;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.os.Build;

public class BrowserIntent extends ActionBarActivity {
	private EditText urlText;
	private Button goButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser_intent);
		//Get a handle to all user interface elements
		urlText = (EditText) findViewById(R.id.url_field);
		goButton = (Button) findViewById(R.id.go_button);
		//set up event handlers
		goButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				openBrowser();
			}
		});
	urlText.setOnEditorActionListener(new OnEditorActionListener() {
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if (actionId == EditorInfo.IME_NULL) {
				openBrowser();
				InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				return true;
			}
		return false;
		}
	});
	}
	
	//Open a browser on the URL specified in the text box
	private void openBrowser() {
		String modifiedUrl = optionallyAddScheme(urlText.getText().toString());
		Uri uri = Uri.parse(modifiedUrl);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	
	//Adding http:// to address if they need it
	private String optionallyAddScheme(String url) {
		if (url.startsWith("http://") || url.startsWith("https://")) {
			return url;
		}
		else
			return "http://".concat(url);
	}
}
