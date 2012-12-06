package com.android;

import java.util.LinkedList;

import android.content.Context;

public class DominoTrick {
	LinkedList<Move> trick;
	
	public DominoTrick(){
		trick = new LinkedList<Move>();
	}
	
	public void playDomino(Move playerMove){
		trick.add(playerMove);
	}
	
	public void clearTrick(){
		trick.clear();
	}
	
	public LinkedList<Move> getTrick(){
		return this.trick;
	}
	
	public Domino getPlayerXDomino(Context context, int PlayerID){
		Domino domino = new Domino(context);
		if(trick.isEmpty() != true){
			for(int i = 0; i < trick.size(); i++){
				if(trick.get(i).getPlayer_id() == PlayerID){
					domino = trick.get(i).getDomino();
				}
			}
		}
		return domino;
	}
	
	public Boolean isEmptyTrick(){
		Boolean state = false;
		if(trick.isEmpty() == true){
			state =  true;
		}else if(trick.isEmpty() == false){
			state =  false;
		}
		return state;
	}
	
	public Boolean allPlayersPlayed(){
		Boolean retVal;
		if(trick.size() == 4){			//will need to be changed if implemented for 3 players
			retVal = true;
		}else{
			retVal = false;
		}
		return retVal;
	}
	
	public LinkedList<Move> getTeamDominos(int team){
		LinkedList<Move> teamList = new LinkedList<Move>();
		if(trick.isEmpty() != true){
			if(team == 1){
				for(int i = 0; i < trick.size(); i++){
					if(trick.get(i).getPlayer_id() == 1 ||trick.get(i).getPlayer_id() == 3){
						teamList.add(trick.get(i));
					}
				}
			}else if(team == 2){
				for(int i = 0; i < trick.size(); i++){
					if(trick.get(i).getPlayer_id() == 2 ||trick.get(i).getPlayer_id() == 4){
						teamList.add(trick.get(i));
					}
				}
			}
		}
		return teamList;
	}
}
