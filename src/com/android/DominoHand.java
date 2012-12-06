package com.android;

import java.util.LinkedList;

import com.android.interfaces.OnDominoPlayedListener;

public class DominoHand {
	private LinkedList<Domino> hand = new LinkedList<Domino>();
	
	public void addDomino(Domino domino){
		hand.addFirst(domino);
	}
	
	public void removeDomino(Domino domino){
		if(hand.isEmpty() != true){
			for(int i = 0; i < hand.size(); i++){
				if(domino.getSuit() == hand.get(i).getSuit() && domino.getWeight() == hand.get(i).getWeight()){
					hand.remove(i);
				}
			}
		}
		
	}
	
	public void clearHand(){
		hand.clear();
	}
	
	public LinkedList<Domino> getLinkedListHand(){
		return this.hand;
	}
	
	public void setDomPlayedListener(OnDominoPlayedListener listener){
		for(int i = 0; i < hand.size(); i++){
			hand.get(i).setClickOn(listener);
		}
	}
}
