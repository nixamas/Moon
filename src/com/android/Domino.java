package com.android;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.interfaces.OnDominoPlayedListener;

public class Domino extends View implements OnClickListener{
	
	private int SUIT;
	private int WEIGHT;
	private View domView;
	private View miniView;
	Context context;
	OnDominoPlayedListener mOnDominoPlayedListener;
	
	public Domino(Context _context, int suit, int weight) {
		super(_context);
		context = _context;
		domView = inflate(context, R.layout.domino_button, null);
		miniView = inflate(context, R.layout.mini_domino_button, null);
//		mView.setBackgroundColor(Color.BLACK);
		this.setSuit(suit);
		this.setWeight(weight);
	}
	
	public Domino(Context _context){
		super(_context);
		context = _context;
		domView = inflate(context, R.layout.domino_button, null);
		miniView = inflate(context, R.layout.mini_domino_button, null);
	}
	
	
	@Override
	public void onClick(View v) {
//		Toast.makeText(context, "#########################", Toast.LENGTH_LONG).show();
		playDomino();
	}
	
	public void setClickOn(/*DominoTrick trick, int playerID,*/ OnDominoPlayedListener listener){
//		this.DomTrick = trick;
//		this.PlayerID = playerID;
		mOnDominoPlayedListener = listener;
		
		this.domView.setOnClickListener(this);
		this.domView.findViewById(R.id.suit_pips).setOnClickListener(this);
		this.domView.findViewById(R.id.weight_pips).setOnClickListener(this);
	}
	
	public void playDomino(){
//		DomTrick.playDomino(PlayerID, this);
		if(mOnDominoPlayedListener != null){
			mOnDominoPlayedListener.onDominoPlayed(this);
		}
	}
	
	/**
	 * Takes the integer of the domino suit and sets it as the member suit. Also applies the png 
	 * image matching the suit.
	 * @param suit
	 */
	public void setSuit(int suit){
		this.SUIT = suit;
		switch(suit){
			case 0:
				domView.findViewById(R.id.suit_pips).setBackgroundResource(R.drawable.pips_0);
				miniView.findViewById(R.id.mini_suit_pips).setBackgroundResource(R.drawable.mini_pips_0);
			break;
			case 1:
				domView.findViewById(R.id.suit_pips).setBackgroundResource(R.drawable.pips_1);
				miniView.findViewById(R.id.mini_suit_pips).setBackgroundResource(R.drawable.mini_pips_1);
			break;
			case 2:
				domView.findViewById(R.id.suit_pips).setBackgroundResource(R.drawable.pips_2);
				miniView.findViewById(R.id.mini_suit_pips).setBackgroundResource(R.drawable.mini_pips_2);
			break;
			case 3:
				domView.findViewById(R.id.suit_pips).setBackgroundResource(R.drawable.pips_3);
				miniView.findViewById(R.id.mini_suit_pips).setBackgroundResource(R.drawable.mini_pips_3);
			break;
			case 4:
				domView.findViewById(R.id.suit_pips).setBackgroundResource(R.drawable.pips_4);
				miniView.findViewById(R.id.mini_suit_pips).setBackgroundResource(R.drawable.mini_pips_4);
			break;
			case 5:
				domView.findViewById(R.id.suit_pips).setBackgroundResource(R.drawable.pips_5);
				miniView.findViewById(R.id.mini_suit_pips).setBackgroundResource(R.drawable.mini_pips_5);
			break;
			case 6:
				domView.findViewById(R.id.suit_pips).setBackgroundResource(R.drawable.pips_6);
				miniView.findViewById(R.id.mini_suit_pips).setBackgroundResource(R.drawable.mini_pips_6);
			break;
		}
	}
	public int getSuit(){
		return this.SUIT;
	}
	
	/**
	 * Takes the integer of the domino weight and sets it as the member suit. Also applies the png 
	 * image matching the weight.
	 * @param suit
	 */
	public void setWeight(int wght){
		this.WEIGHT = wght;
		switch(wght){
			case 0:
				domView.findViewById(R.id.weight_pips).setBackgroundResource(R.drawable.pips_0);
				miniView.findViewById(R.id.mini_weight_pips).setBackgroundResource(R.drawable.mini_pips_0);
			break;
			case 1:
				domView.findViewById(R.id.weight_pips).setBackgroundResource(R.drawable.pips_1);
				miniView.findViewById(R.id.mini_weight_pips).setBackgroundResource(R.drawable.mini_pips_1);
			break;
			case 2:
				domView.findViewById(R.id.weight_pips).setBackgroundResource(R.drawable.pips_2);
				miniView.findViewById(R.id.mini_weight_pips).setBackgroundResource(R.drawable.mini_pips_2);
			break;
			case 3:
				domView.findViewById(R.id.weight_pips).setBackgroundResource(R.drawable.pips_3);
				miniView.findViewById(R.id.mini_weight_pips).setBackgroundResource(R.drawable.mini_pips_3);
			break;
			case 4:
				domView.findViewById(R.id.weight_pips).setBackgroundResource(R.drawable.pips_4);
				miniView.findViewById(R.id.mini_weight_pips).setBackgroundResource(R.drawable.mini_pips_4);
			break;
			case 5:
				domView.findViewById(R.id.weight_pips).setBackgroundResource(R.drawable.pips_5);
				miniView.findViewById(R.id.mini_weight_pips).setBackgroundResource(R.drawable.mini_pips_5);
			break;
			case 6:
				domView.findViewById(R.id.weight_pips).setBackgroundResource(R.drawable.pips_6);
				miniView.findViewById(R.id.mini_weight_pips).setBackgroundResource(R.drawable.mini_pips_6);
			break;
		}
	}
	
	public int getWeight(){
		return this.WEIGHT;
	}
	
	public void setPips(int suit, int weight){
		this.SUIT = suit;
		this.WEIGHT = weight;
	}
	
	public boolean isEqual(Domino eqDom){
		boolean isEqual = false;
		if((this.getSuit() == eqDom.getSuit()) && (this.getWeight() == eqDom.getWeight())){
			isEqual = true;
		}else if((this.getSuit() == eqDom.getWeight()) && (this.getWeight() == eqDom.getSuit())){
			isEqual = true;
		}else if((this.getWeight() == eqDom.getSuit()) && (this.getSuit() == eqDom.getWeight())){
			isEqual = true;
		}
		return isEqual;
	}
	
	public View getView(){
		return this.domView;
	}
	
	public View resetView(){
		this.domView = null;
		domView = inflate(context, R.layout.domino_button, null);
//		mView.setBackgroundColor(Color.BLACK);
		this.setSuit(this.SUIT);
		this.setWeight(this.WEIGHT);
		return this.domView;
	}
	
	public View getMiniView(){
		this.miniView = null;
		miniView = inflate(context, R.layout.mini_domino_button, null);
		this.setSuit(this.SUIT);
		this.setWeight(this.WEIGHT);
		return this.miniView;
	}
}