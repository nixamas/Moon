package com.android;

import java.util.LinkedList;

import android.content.Context;

/**
 * This class is the domino Deck. The linked list within this class holds all the dominos.
 * When the Class is instantiated the deck is built automatically
 * 
 */
public class DominoDeck {
	LinkedList<Domino> DECK = new LinkedList<Domino>();
	
	private static Context context;
	
	public DominoDeck(Context _context){
		context = _context;
		int suit = 0;
		int weight = 0;
		/**
		 * creates the deck of dominos by looping the required amount of times for each weight and suit
		 */
		while(suit <= 6 ){
			while(weight <= suit){
				Domino DOM = new Domino(context, suit, weight);
				DECK.add(DOM);
				weight++;
			}//while(weight <= suit)
			suit++;
			weight = 0;
		}//while(suit <= 6)
	}
	
	/**
	 * Deals 7 individual Dom objects at random.
	 * These Doms are removed from the Deck once they are dealt.
	 * @return 
	 */	
	public DominoHand dealDeck(){
		DominoHand hand = new DominoHand();
		int randNum;
		for(int i = 0; i < 7 ; i++){
			randNum = (int)(Math.random()*(DECK.size()));
			hand.addDomino(DECK.get(randNum));
			DECK.remove(randNum);
		}
		return hand;
	}
	
}

