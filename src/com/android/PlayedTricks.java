package com.android;

import java.util.LinkedList;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;

public class PlayedTricks {
	LinkedList<DominoTrick> PlayedDoms;
//	LinkedList<TableRow> PlayedDominoHands;
	Context context;
	
	public PlayedTricks(Context inContext){
		context = inContext;
		PlayedDoms = new LinkedList<DominoTrick>();
//		PlayedDominoHands = new LinkedList<TableRow>();
	}
	
	/**
	 * This function must copy the current trick into the object because the trick that is passed in is always cleared after every hand. 
	 * @param trick
	 */
	public void addTrick(DominoTrick trick){
		DominoTrick copyTrick = new DominoTrick();
		for(int i = 0; i < trick.getTrick().size(); i++){
			copyTrick.playDomino(new Move(trick.getTrick().get(i).getPlayer_id(), new Domino(context, trick.getTrick().get(i).getDomino().getSuit(), trick.getTrick().get(i).getDomino().getWeight())));
		}
		this.PlayedDoms.add(copyTrick);
	}
	
//	public void addTrickTableRow(DominoTrick trick){
//		TableRow tableRow = new TableRow(context);
//		for(int i = 0; i < trick.getTrick().size(); i++){
//			View v = trick.getTrick().get(i).getDomino().getMiniView();
//			v.setPadding(10, 3, 10, 3);
//			tableRow.addView(v);
//		}
//		this.PlayedDominoHands.add(tableRow);
//	}
//	public TableRow getTrickTableRow(int row){
//		return this.PlayedDominoHands.get(row);
//	}
	
	public DominoTrick getTrick(int i){
		return this.PlayedDoms.get(i);
	}
	
	public TableRow getRow(Context context, int row){
		TableRow tblRow = new TableRow(context);
		
		for(int i = 0; i < 4; i++){
			View v = this.getTrick(row).getTrick().get(i).getDomino().getMiniView();
			v.setPadding(3, 2, 3, 2);
			//perhaps add change the background for 1st play and winning domino
			tblRow.addView(v);
		}
		return tblRow;
	}
	
	public int getSize(){
		return PlayedDoms.size();
	}
	
	public boolean isEmpty(){
		return PlayedDoms.isEmpty();
	}
}
