package com.android.adapters;

import com.android.Domino;
import com.android.DominoTrick;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * This class is used to display the domino objects in Gallery Views. (Scrolling horizontal windows)
 *
 */
public class TrickViewAdapter extends BaseAdapter {
	
	Context mContext;
	Domino[] domList;
	
	public TrickViewAdapter(Context context, DominoTrick dominoTrick){
		mContext = context;

		if(!dominoTrick.getTrick().isEmpty()){
			domList = new Domino[dominoTrick.getTrick().size()];
	
			for(int i = 0; i < dominoTrick.getTrick().size(); i++){
				domList[i] = new Domino(context);
				domList[i] = dominoTrick.getTrick().get(i).getDomino();
			}
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
		return domList[position].getView();
	}

}
