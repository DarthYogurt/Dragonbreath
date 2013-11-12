package com.dragonbreath.thesaurusapp;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText mainText;
	TextWatcher mTextEditorWatcher;
	String lastWord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainText.addTextChangedListener(mTextEditorWatcher);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void getLastWord() {
		mTextEditorWatcher = new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				int indexOfFirstSpace;
				int indexOfLastSpace;
				
				String convertToString = s.toString();
				indexOfFirstSpace = convertToString.lastIndexOf(' ');
				indexOfLastSpace = convertToString.lastIndexOf(' ', -1);
				lastWord = convertToString.substring(indexOfFirstSpace, indexOfLastSpace);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
		};
		
		EditText editText = (EditText) findViewById(R.id.main_text);
		mTextEditorWatcher.afterTextChanged((Editable) editText);
	}
}
