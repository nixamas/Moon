package com.android.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.Domino;
import com.android.DominoHand;

/**
 * This class is used to display the domino objects in Gallery Views. (Scrolling horizontal windows)
 *
 */
public class ViewAdapter extends BaseAdapter {
	
	Context mContext;
	Domino[] domList;
	
	public ViewAdapter(Context context, DominoHand dominoList){
		mContext = context;
		domList = new Domino[dominoList.getLinkedListHand().size()];

		for(int i = 0; i < dominoList.getLinkedListHand().size(); i++){
			domList[i] = new Domino(context);
			domList[i] = dominoList.getLinkedListHand().get(i);
		}
	}
	
	@Override
	public int getCount() {
		return domList.length;
	}

	@Override
	public Domino getItem(int position) {
		Domino item;
		item = domList[position];
		return item;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		view = domList[position].getView();
		return view;
	}
	
}
