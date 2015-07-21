package com.hari.home.expense.tracker;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class EnterExpenseActivity extends ActionBarActivity {

	private static final String TAG = "EnterExpenseActivity";

	// private String expenseDataString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_expense);
	}

	public static String getTextMonth(int index) {
		String[] months = new DateFormatSymbols().getMonths();
		return months[index];
	}
	
	public String getDateNum( int dateString ) {
		return "" + (dateString < 10 ? ("0" + dateString) : dateString);
	}

	public void submitExpense(View button) {

		DatePicker datePicker;
		datePicker = (DatePicker) findViewById(R.id.datePicker1);

		FileIO fio = new FileIO();

		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();
		int year = datePicker.getYear();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String formatedDate = sdf.format(new Date(year, month, day));

		final EditText storeField = (EditText) findViewById(R.id.editText1);
		String name = storeField.getText().toString();

		final Spinner cardTypeSpinner = (Spinner) findViewById(R.id.SpinnerCardType);
		String cardType = cardTypeSpinner.getSelectedItem().toString();

		final EditText costField = (EditText) findViewById(R.id.editText2);
		String cost = costField.getText().toString();

		String date_string = day + "-" + getTextMonth(month) + "-" + year;
		String indexDate = "" + year + getDateNum(month) + getDateNum(day);

		Context myContext = getBaseContext();

		String expenseFile = getString(R.string.expense_file);
		Log.d(TAG, "Current working Directory : " + expenseFile);

		String expenseDataString = ExpenseData.createExpenseRecord(name,
				cardType, cost, getString(R.string.current_flag), date_string,
				indexDate);

		try {
			fio.appendExpenseFile(myContext, expenseFile, expenseDataString);
		} catch (IOException e) {
			Log.e(TAG, "Error appending to " + expenseFile);
		}
		Log.d(TAG, "before read...");
		String inline = fio.readExpenseFile(myContext, expenseFile);
		Log.d(TAG, "Inline ---> " + inline);
		this.finish();

	}

}
