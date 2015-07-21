package com.hari.home.expense.tracker;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter {

	private Context mContext;
	private int id;
	private List<String> items;
	private String TAG5 = "CustomListAdapter()";

	public CustomListAdapter(Context context, int textViewResourceId,
			List<String> list) {
		super(context, textViewResourceId, list);
		mContext = context;
		id = textViewResourceId;
		items = list;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View mView = v;
		if (mView == null) {
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mView = vi.inflate(id, null);
		}

		TextView text = (TextView) mView.findViewById(R.id.textView);

		if (items.get(position) != null) {
			String elementItem = items.get(position);
			Log.d(TAG5, "position = " + elementItem);
			String [] tokens = elementItem.split("\\|");
			String displayString = tokens[3].concat(" Vendor : ").concat(tokens[0]).concat(" ").concat(tokens[1]).concat(" $").concat(tokens[2]); 
			text.setTextSize( 17 );
			if (elementItem.indexOf("|DELETE") > -1) {
				text.setTextColor(Color.WHITE);
				text.setText(displayString);
				text.setBackgroundColor(Color.RED);
				//int color = Color.argb(200, 255, 64, 64);
				//text.setBackgroundColor(color);
			} else {
				text.setTextColor(Color.BLACK);
				text.setText(displayString);
				text.setBackgroundColor(Color.WHITE);
				//int color = Color.argb(200, 255, 64, 64);
				//text.setBackgroundColor(color);
			}

		}

		return mView;
	}

	private Object getResources() {
		// TODO Auto-generated method stub
		return null;
	}

}