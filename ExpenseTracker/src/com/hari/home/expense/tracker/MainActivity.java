package com.hari.home.expense.tracker;

import java.io.File;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity { // implements OnClickListener {
	
	public final static int EXTRA_MESSAGE = 1;
	private static final String TAG = "MainActivity";
	
	Button btnPassObject;
    //EditText etName, etAge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//btnPassObject = (Button) findViewById(R.id.activity_view_expense);
        //etName = (EditText) findViewById(R.id.etName);
        //etAge = (EditText) findViewById(R.id.etAge);
 
        //btnPassObject.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		String filename = getString(R.string.expense_file);
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.action_init_expense_file) {
			
			try {
				String expenseFile = getString(R.string.expense_file);
				Context myContext = getBaseContext();
				try {
					FileIO fio = new FileIO();
					fio.initializeExpenseFile(myContext, expenseFile);
				} catch (IOException e) {
					Log.e(TAG, "Error appending to " + expenseFile);
				}
				Log.d(TAG, "File [" + expenseFile + "] initialize" );
			} catch (Exception x) {
			    // File permission problems are caught here.
				Log.d(TAG, "File permission problems for " + filename  );
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/** 
	 * Called when the user clicks the Enter Expense button 
	 */
	public void sendEnterMessage(View view) {
		Intent intent = new Intent(this, EnterExpenseActivity.class);
		startActivity(intent);
	}
	
	/** 
	 * Called when the user clicks the Enter Expense button 
	 */
	public void sendViewMessage(View view) {
		Intent intent = new Intent(this, ViewExpenseActivity.class);
		startActivity(intent);
	}

	//@Override
	//public void onClick(View v) {
	//	Log.d(TAG, "onClick methond");
		
	//}
	
}
