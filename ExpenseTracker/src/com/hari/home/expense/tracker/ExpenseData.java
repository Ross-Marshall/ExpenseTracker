package com.hari.home.expense.tracker;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Arrays;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.view.View;

public class ExpenseData extends ActionBarActivity {
	
	/**
	 * 
	 */
	public String vendor;
	public String card;
	public String date;
	public String cost;
	public String status;
	public String indexDate;
	
	public String [] months = { "January", "February", "March",
			                    "April", "May", "June",
			                    "July", "August", "September",
			                    "October", "November", "December" };

	public String TAG = "ExpenseData";
	//private static String expenseDataString;
		
	public String getIndexDate() {
		return indexDate;
	}

	public void setIndexDate(String indexDate) {
		this.indexDate = indexDate;
	}

	@Override
	public String toString() {
		return "ExpenseData [vendor=" + vendor + ", card=" + card + ", date="
				+ date + ", cost=" + cost + ", status=" + status
				+ ", indexDate=" + indexDate + "]";
	}
	
	public static String createExpenseRecord(String name,
			                                 String cardType,
			                                 String cost,
			                                 String statusFlag,
			                                 String date_string,
			                                 String indexDate) {

	    return name        + "|" +
	           cardType    + "|" +
	    	   cost        + "|" + 
		       date_string + "|" + 
	    	   statusFlag  + "|" + 
		       indexDate   + "\n";

	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMonthIndex( String month ) {
		int index = Arrays.asList(months).indexOf(month) + 1;
		//Log.d(TAG, "month = [" + month + "]");
		//Log.d(TAG, "index = [" + index + "]");
		if ( index < 10 ) {
			return "0" + index;
		} else {
			return "" + index;
		}
	}
	
	public String convertDateToIndex( String dateString ) {
	    String indexDate;
	    String [] tokens = dateString.split("-");
	    //Log.d(TAG, "convertDateToIndex : dateString : " + dateString );
	    for ( String token : tokens ) {
	    	Log.d(TAG, "convertDateToIndex : " + token );
	    }
	    indexDate = tokens[2] + getMonthIndex( tokens[1] ) + tokens[0];
	    //Log.d(TAG, "indexDate = " + indexDate );
	    return indexDate;
	}
	
	public ExpenseData(String inData) {
		//Log.d(TAG, "inData ===>" + inData );
		String [] tokens = inData.split("\\|");
		String iDate = "";
		//Log.d(TAG, "convertDateToIndex( tokens[3] ) ===>" + convertDateToIndex( tokens[3] ) );			
		setVendor( tokens[0] );
		setCard( tokens[1] );
		setCost( tokens[2] );
		setDate( tokens[3] );
		setStatus( tokens[4] );
		if ( tokens.length > 5 ) {
			iDate = tokens[5];
			//Log.d(TAG, "constructor : iDate.length() " + iDate.length() );
			if ( iDate.length() == 8 ) {
			    setIndexDate( tokens[5] );
			} else {
				//Log.d(TAG, "constructor : iDate [" + iDate + "]" );
				//Log.d(TAG, "constructor : iDate.substring(0,6) + 0 + iDate.substring(6) : " + iDate.substring(0,6) + "0" + iDate.substring(6) );
				setIndexDate( iDate.substring(0,6) + "0" + iDate.substring(6) );				
			}
		} else {
			setIndexDate( convertDateToIndex( tokens[3] ));
		}
		//Log.d(TAG, "constructor : indexDate : " + getIndexDate().length() );
	}
	
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	

}
