package com.android;

import android.content.Context;

public class Player {
	private DominoHand myHand;
	private String name;
	private int ID;
	Context context;
	
	public Player(DominoHand _myHand, Context _context, int _ID){
		myHand = _myHand;
		context = _context;
		ID = _ID;
		name = "Player " + Integer.toString(_ID);
	}
	
	public void setName(String _name){
		if(_name != null){
			name = _name;
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setID(int _ID){
		ID = _ID;
	}
	
	public int getID(){
		return this.ID;
	}
	
	public void setHand(DominoHand hand){
		myHand = hand;
	}
	
	public DominoHand getHand(){
		return this.myHand;
	}
}
