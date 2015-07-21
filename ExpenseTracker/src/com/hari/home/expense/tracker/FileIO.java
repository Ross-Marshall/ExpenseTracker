package com.hari.home.expense.tracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;

import android.content.Context;
import android.util.Log;

public class FileIO {

	private static final String TAG = "FileIO";

	public void appendExpenseFile(Context c, String filename, String string)
			throws IOException {
		// FileOutputStream fos = c.openFileOutput(filename,
		// Context.MODE_PRIVATE);
		// FileOutputStream fos=new FileOutputStream(new File(filename),true);
		FileOutputStream fos = c.openFileOutput(filename, Context.MODE_APPEND);
		fos.write(string.getBytes());
		fos.close();
	}

	public void writeExpenseFile(Context c, String filename, String expenseData) {
		try {
			FileOutputStream fos = c.openFileOutput(filename,
					Context.MODE_PRIVATE);
			String[] tokens = expenseData.split("\n");
			for (String token : tokens) {
				Log.d(TAG, "FileIO.writeExpenseFile() token ===========> "
						+ token);
			}
			fos.close();
		} catch (Exception e) {
			Log.d(TAG, "Error writing to " + filename);
		}
	}

	public void initializeExpenseFile(Context c, String filename)
			throws IOException {
		FileOutputStream fos = c.openFileOutput(filename, Context.MODE_PRIVATE);
		// Context.MODE_PRIVATE);
		// FileOutputStream fos=new FileOutputStream(new File(filename),true);
		// FileOutputStream fos = c.openFileOutput(filename,
		// Context.MODE_WORLD_WRITEABLE);
		// fos.write();
		fos.close();
	}
	
	public void updateExpenseFile(Context c, String filename, String [] data)
			throws IOException {
		
		//FileOutputStream fos = c.openFileOutput(filename, Context.MODE_PRIVATE);
		// Context.MODE_PRIVATE);
		// FileOutputStream fos=new FileOutputStream(new File(filename),true);
		// FileOutputStream fos = c.openFileOutput(filename,
		// Context.MODE_WORLD_WRITEABLE);
		// fos.write();
		//fos.close();
	}

	public String readExpenseFile(Context c, String filename) {
		StringBuffer fileContent = new StringBuffer("");
		String retString = "";
		Log.d(TAG, "readExpenseFile : before try");
		try {
			FileInputStream fis = c.openFileInput(filename);
			int n;
			byte[] buffer = new byte[1024];

			Log.d(TAG, "readExpenseFile : before while");
			while ((n = fis.read(buffer)) != -1) {
				fileContent.append(new String(buffer, 0, n));
			}
			Log.d(TAG, "readExpenseFile : after while");
			retString = fileContent.toString();
			Log.d(TAG, "readExpenseFile : retString = [" + retString + "]");

			fis.close();

		} catch (Exception e) {
			Log.d(TAG, "Error reading " + filename);
		}

		return retString;
	}
}
