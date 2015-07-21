package com.hari.home.expense.tracker;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ViewExpenseActivity extends Activity {

	private static final String TAG = "ViewExpenseActivity";
	private static final String TAG2 = "ViewItemedClicked";
	private static final String TAG3 = "onCreate()";
	private static final String TAG4 = "List View - Click";
	private static final String TAG5 = "updateListView";
	private static final String TAG6 = "Selected Menu  Items:";

	private ListView lv;
	

	private List<String> expenseDataList = new ArrayList<String>();
	
	public List<String> getExpenseDataList() {
		return expenseDataList;
	}

	public String formatToken(String expenseData) {
		String formattedString;
		Log.d(TAG, "formatToken expenseData[" + expenseData + "]");
		String[] items = expenseData.split("\\|");
		int count = 0;
		for (String item : items) {
			Log.d(TAG, "formatToken : items[" + count + "] = " + item);
			count += 1;
		}
		formattedString = items[3] + ":  Vendor : " + items[0] + "\n"
				+ items[1] + " $" + items[2];
		return formattedString;
	}

	/*
	 * / ListView list = (ListView) findViewById(R.id.workers_list);
	 * lv.setOnItemLongClickListener(new OnItemLongClickListener() {
	 * 
	 * @Override public boolean onItemLongClick(AdapterView<?> parent, View
	 * view, int position, long id) {
	 * alert(parent.getItemAtPosition(position).toString()); // alert is Toast
	 * return true; } }); //
	 */
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		Log.d(TAG2, "onItemLongClick : position [" + position + "] id[" + id
				+ "]");
		return true;

	}
	
	public void updateTextOnView() {
		//Log.d(TAG5, "updateListView" );
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_view_expenses);

		lv = (ListView) findViewById(R.id.activity_view_expense);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				
				Log.d(TAG4, "onItemLongClick : position [" + position + "] id["
						+ id + "]");
				Toast.makeText(getApplicationContext(),
						"You clicked item at position: " + position + " id: " + id,
						Toast.LENGTH_SHORT).show();
				String elementItem = expenseDataList.get( position );
				Log.d(TAG4, "onItemLongClick : Selected Item : " + elementItem );
				Log.d(TAG4, "onItemLongClick : CURRENT or DELETE??? ===> " + elementItem.indexOf("|CURRENT") );
				if (elementItem.indexOf("|CURRENT") > -1) {
					expenseDataList.set( position, elementItem.replace("|CURRENT", "|DELETE") );
				} else {
					expenseDataList.set( position, elementItem.replace("|DELETE", "|CURRENT") );
				}
				/* I don't know where to put this... */
				CustomListAdapter listAdapter;
				//List<String> mList = null;
				listAdapter = new CustomListAdapter(ViewExpenseActivity.this , R.layout.custom_list , expenseDataList ); //mList);
				Log.d(TAG4, "listAdapter = " + listAdapter.toString() );
				lv.setAdapter(listAdapter);
			}
		});
		
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

		Log.d(TAG3, "onCreate lv = " + lv.toString());

		// Instantiating an array list (you don't need to do this,
		// you already have yours).
		List<String> expenseViewList = new ArrayList<String>();
		Context myContext = getBaseContext();
		FileIO fio = new FileIO();
		String expenseData = fio.readExpenseFile(myContext,
				getString(R.string.expense_file));
		ExpenseData expData = null;
		String[] tokens = expenseData.split("\n");
		String displayExpense = "";
		
		for (String token : tokens) {
			displayExpense = formatToken(token);
			expData = new ExpenseData(token);
			expenseDataList.add( token );
			expenseViewList.add(displayExpense);
			Log.d(TAG, "token ===========> " + token);
			Log.d(TAG, "displayExpense ==> " + displayExpense);
			Log.d(TAG, "expData =========> " + expData.toString() );
		}


		
		// This is the array adapter, it takes the context of the activity as a
		// first parameter, the type of list view as a second parameter and your
		// array as a third parameter.
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, expenseViewList);

		lv.setAdapter(arrayAdapter);	

	}
	
	public boolean onCreateOptionsMenu(Menu menu) {      
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.view_menu, menu);
	    return true;
	}
	
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	Log.d(TAG6, "item id ==> " + item.getMenuInfo());
    	  	
		//String[] tokens = expenseDataList.split(",");
		
		for (String expenseItem : expenseDataList) {
			Log.d(TAG6, "expenseList ==> " + expenseItem );
		}
		
        switch (item.getItemId()) {
        case R.id.view_american_express:
        	Log.d(TAG6, "Menu Item: American Express");
        return true;
        case R.id.view_visa:
        	Log.d(TAG6, "Menu Item: VISA");
        return true;
        case R.id.view_discover:
        	Log.d(TAG6, "Menu Item: Discover");
        return true;
        case R.id.sort_by_date:
        	Log.d(TAG6, "Menu Item: Sort By Date");
        return true;
        case R.id.save_expenses:
        	Log.d(TAG6, "Menu Item: Save Expense Data");
        return true;
        
        default:
        	Log.d(TAG6, "Menu Item: --- Menu Default ---");
        }
        
    	return true;
    }

}
