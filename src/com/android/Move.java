package com.android;

public class Move {
	int player_id;
	Domino domino;
	
	public Move(int id, Domino dom){
		this.player_id = id;
		this.domino = dom;
	}
	
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public Domino getDomino() {
		return domino;
	}
	public void setDomino(Domino domino) {
		this.domino = domino;
	}
}
