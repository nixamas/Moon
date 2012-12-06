package com.android;

import android.content.Context;

public class UtilityFile {

	public boolean isEqual(Domino domino1, Domino domino2){
		boolean isEqual = false;
		if((domino1.getSuit() == domino2.getSuit()) && (domino1.getWeight() == domino2.getWeight())){
			isEqual = true;
		}
		return isEqual;
	}
	
	static public boolean isBetter(Base_Domino_Elements dominoTable, DominoTablePosition currentBest, DominoTablePosition testedDomino){
		boolean result = false;
		
		return result;
	}
	
//	static public int bestDomino(DominoTablePosition[] playedDoms){
//		int bestDom = 0;
//		DominoTablePosition bestPlayedDomPos = playedDoms[0];
//		for(int i = 1; i < playedDoms.length; i++){
//			if(bestPlayedDomPos.isBeatenBy(playedDoms[i])){
//				bestPlayedDomPos = playedDoms[i];
//				bestDom = i;
//			}
//		}
//		
//		return bestDom;
//	}
	
	static public int returnWinner4plyr(Context context, Base_Domino_Elements dominoTable, DominoTrick trick){
		int winnerId = -1;
		int suit = -1;
		DominoTablePosition bestPosition = new DominoTablePosition();
		
		DominoTablePosition firstMovePos = new DominoTablePosition();
		Domino firstMoveDom = new Domino(context);
//		firstMoveDom = trick.getPlayerXDomino(context, 1);
		firstMoveDom = trick.getTrick().get(0).getDomino();
		firstMovePos = dominoTable.getDominoPosition(firstMoveDom);
		firstMovePos.wasFirstPlayed();	//this was the first domino played in hand
		suit = firstMoveDom.getSuit();
		
		DominoTablePosition scndMovePos = new DominoTablePosition();
		Domino scndMoveDom = new Domino(context);
//		scndMoveDom = trick.getPlayerXDomino(context, 2);
		scndMoveDom = trick.getTrick().get(1).getDomino();
		scndMovePos = dominoTable.getDominoPosition(scndMoveDom);
		
		DominoTablePosition thrdMovePos = new DominoTablePosition();
		Domino thrdMoveDom = new Domino(context);
//		thrdMoveDom = trick.getPlayerXDomino(context, 3);
		thrdMoveDom = trick.getTrick().get(2).getDomino();
		thrdMovePos = dominoTable.getDominoPosition(thrdMoveDom);
		
		DominoTablePosition frthMovePos = new DominoTablePosition();
		Domino frthMoveDom = new Domino(context);
//		frthMoveDom = trick.getPlayerXDomino(context, 4);
		frthMoveDom = trick.getTrick().get(3).getDomino();
		frthMovePos = dominoTable.getDominoPosition(frthMoveDom);
		
		bestPosition = bestPosition(firstMovePos, scndMovePos, thrdMovePos, frthMovePos, suit);
		
		if(bestPosition.isEqualPosition(firstMovePos) == true){
			winnerId = trick.getTrick().get(0).getPlayer_id();
		}else if(bestPosition.isEqualPosition(scndMovePos) == true){
			winnerId = trick.getTrick().get(1).getPlayer_id();
		}else if(bestPosition.isEqualPosition(thrdMovePos) == true){
			winnerId = trick.getTrick().get(2).getPlayer_id();
		}else if(bestPosition.isEqualPosition(frthMovePos) == true){
			winnerId = trick.getTrick().get(3).getPlayer_id();
		}
		
		return winnerId;
	}
	
	static private DominoTablePosition bestPosition(DominoTablePosition p1, DominoTablePosition p2, DominoTablePosition p3, DominoTablePosition p4, int suit){
		DominoTablePosition winner = new DominoTablePosition();
		
//		if(p1.isBeatenBy(p2,suit) == false && p1.isBeatenBy(p3,suit) == false && p1.isBeatenBy(p4,suit) == false){
//			winner = p1;
//		}else if(p2.isBeatenBy(p1,suit) == false && p2.isBeatenBy(p3,suit) == false && p2.isBeatenBy(p4,suit) == false){
//			winner = p2;
//		}else if(p3.isBeatenBy(p1,suit) == false && p3.isBeatenBy(p2,suit) == false && p3.isBeatenBy(p4,suit) == false){
//			winner = p3;
//		}else if(p4.isBeatenBy(p1,suit) == false && p4.isBeatenBy(p2,suit) == false && p4.isBeatenBy(p3,suit) == false){
//			winner = p4;
//		}
		
		if(positionCompare(p1,p2,suit) == false && positionCompare(p1,p3,suit) == false && positionCompare(p1,p4,suit) == false){
			winner = p1;
		}else if(positionCompare(p2,p1,suit) == false && positionCompare(p2,p3,suit) == false && positionCompare(p2,p4,suit) == false){
			winner = p2;
		}else if(positionCompare(p3,p1,suit) == false && positionCompare(p3,p2,suit) == false && positionCompare(p3,p4,suit) == false){
			winner = p3;
		}else if(positionCompare(p4,p1,suit) == false && positionCompare(p4,p2,suit) == false && positionCompare(p4,p3,suit) == false){
			winner = p4;
		}
		
		return winner;
	}
	
	//This function will compare the positions of two different domino positions
		//When is say THIS it is referring to this particular DominoTablePosition you are testing a domino against
	//This function will return a true if DomPos1 is beaten by DomPos2 a false is returned otherwise
	static private boolean positionCompare(DominoTablePosition domPos1, DominoTablePosition domPos2, int suit){
			boolean beaten = false;
			if(domPos1.isTrump() == true && domPos2.isTrump() == false){	//THIS is a trump so it automatically wins
				beaten = false;
			}else if(domPos1.isTrump() == true && domPos2.isTrump() == true){		//both are trumps so find which is higher
				if(domPos1.getColumn() > domPos2.getColumn()){		//THIS is a higher trump
					beaten = false;
				}else if(domPos1.getColumn() < domPos2.getColumn()){	//THIS is a lower trump
					beaten = true;
				}
			}else if(domPos1.isTrump() == false && domPos2.isTrump() == true){		//the other domino is a trump and THIS is not, so other wins
				beaten = true;
			}else{						//neither are trump
				if(domPos1.isFirstPlayed() == true){//This one was the first domino played in hand
					if(domPos1.getRow() == domPos2.getRow()){	//both are same suit
						if(domPos1.getColumn() > domPos2.getColumn()){	//THIS has a higher weight
							beaten = false;
						}else if(domPos1.getColumn() < domPos2.getColumn()){	//Other has a higher weight
							beaten = true;
						}
					}else if(domPos1.getColumn() == domPos2.getColumn()){
						if(domPos1.getRow() > domPos2.getRow()){
							beaten = false;
						}else if(domPos1.getRow() < domPos2.getRow()){
							beaten = true;
						}
					}else if(domPos1.getColumn() == domPos2.getRow()){
						if(domPos1.getRow() > domPos2.getColumn()){
							beaten = false;
						}else if(domPos1.getRow() < domPos2.getColumn()){
							beaten = true;
						}
					}else if(domPos1.getRow() == domPos2.getColumn()){
						if(domPos1.getColumn() > domPos2.getRow()){
							beaten = false;
						}else if(domPos1.getColumn() < domPos2.getRow()){
							beaten = true;
						}
					}
				}else if(domPos2.isFirstPlayed() == true){	//Other domino was first played
					if(domPos1.getRow() == domPos2.getRow()){	//same suit
						if(domPos1.getColumn() > domPos2.getColumn()){	//THIS has a higher weight
							beaten = false;
						}else if(domPos1.getColumn() < domPos2.getColumn()){	//Other has a higher weight
							beaten = true;
						}
					}else if(domPos1.getColumn() == domPos2.getColumn()){
						if(domPos1.getRow() > domPos2.getRow()){
							beaten = false;
						}else if(domPos1.getRow() < domPos2.getRow()){
							beaten = true;
						}
					}else if(domPos1.getColumn() == domPos2.getRow()){
						if(domPos1.getRow() > domPos2.getColumn()){
							beaten = false;
						}else if(domPos1.getRow() < domPos2.getColumn()){
							beaten = true;
						}
					}else if(domPos1.getRow() == domPos2.getColumn()){
						if(domPos1.getColumn() > domPos2.getRow()){
							beaten = false;
						}else if(domPos1.getColumn() < domPos2.getRow()){
							beaten = true;
						}
					}else{				//THIS domino is unable to beat the first played domino
						beaten = true;
					}
				}else{
					if(domPos1.getRow() == domPos2.getRow() && domPos2.getRow() == suit){	
						if(domPos1.getColumn() > domPos2.getColumn()){	
							beaten = false;
						}else if(domPos1.getColumn() < domPos2.getColumn()){
							beaten = true;
						}
					}else if(domPos1.getColumn() == domPos2.getColumn() && domPos2.getColumn() == suit){
						if(domPos1.getRow() > domPos2.getRow()){
							beaten = false;
						}else if(domPos1.getRow() < domPos2.getRow()){
							beaten = true;
						}
					}else if(domPos1.getColumn() == domPos2.getRow() && domPos2.getRow() == suit){
						if(domPos1.getRow() > domPos2.getColumn()){
							beaten = false;
						}else if(domPos1.getRow() < domPos2.getColumn()){
							beaten = true;
						}
					}else if(domPos1.getRow() == domPos2.getColumn() && domPos2.getColumn() == suit){
						if(domPos1.getColumn() > domPos2.getRow()){
							beaten = false;
						}else if(domPos1.getColumn() < domPos2.getRow()){
							beaten = true;
						}
					}
				}
			}
			return beaten;
		}
	
}
