package com.android;

public class DominoTablePosition {
	
	private int row;
	private int column;
	private boolean trump = false;
	private boolean firstPlayed = false;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public void setTrump(){
		this.trump = true;
	}
	public boolean isTrump(){
		return this.trump;
	}
	public void wasFirstPlayed(){
		this.firstPlayed = true;
	}
	public boolean isFirstPlayed(){
		return this.firstPlayed;
	}
	
//	//This function will compare the positions of two different domino positions
//	//When is say THIS it is referring to this particular DominoTablePosition you are testing a domino against
//	public boolean isBeatenBy(DominoTablePosition testDom, int suit){
//		boolean beaten = false;
//		if(this.isTrump() == true && testDom.isTrump() == false){	//THIS is a trump so it automatically wins
//			beaten = false;
//		}else if(this.isTrump() == true && testDom.isTrump() == true){		//both are trumps so find which is higher
//			if(this.getColumn() > testDom.getColumn()){		//THIS is a higher trump
//				beaten = false;
//			}else if(this.getColumn() < testDom.getColumn()){	//THIS is a lower trump
//				beaten = true;
//			}
//		}else if(this.isTrump() == false && testDom.isTrump() == true){		//the other domino is a trump and THIS is not, so other wins
//			beaten = true;
//		}else{						//neither are trump
//			if(this.isFirstPlayed() == true){//This one was the first domino played in hand
//				if(this.getRow() == testDom.getRow()){	//both are same suit
//					if(this.getColumn() > testDom.getColumn()){	//THIS has a higher weight
//						beaten = false;
//					}else if(this.getColumn() < testDom.getColumn()){	//Other has a higher weight
//						beaten = true;
//					}
//				}else if(this.getColumn() == testDom.getColumn()){
//					if(this.getRow() > testDom.getRow()){
//						beaten = false;
//					}else if(this.getRow() < testDom.getRow()){
//						beaten = true;
//					}
//				}else if(this.getColumn() == testDom.getRow()){
//					if(this.getRow() > testDom.getColumn()){
//						beaten = false;
//					}else if(this.getRow() < testDom.getColumn()){
//						beaten = true;
//					}
//				}else if(this.getRow() == testDom.getColumn()){
//					if(this.getColumn() > testDom.getRow()){
//						beaten = false;
//					}else if(this.getColumn() < testDom.getRow()){
//						beaten = true;
//					}
//				}
//			}else if(testDom.isFirstPlayed() == true){	//Other domino was first played
//				if(this.getRow() == testDom.getRow()){	//same suit
//					if(this.getColumn() > testDom.getColumn()){	//THIS has a higher weight
//						beaten = false;
//					}else if(this.getColumn() < testDom.getColumn()){	//Other has a higher weight
//						beaten = true;
//					}
//				}else if(this.getColumn() == testDom.getColumn()){
//					if(this.getRow() > testDom.getRow()){
//						beaten = false;
//					}else if(this.getRow() < testDom.getRow()){
//						beaten = true;
//					}
//				}else if(this.getColumn() == testDom.getRow()){
//					if(this.getRow() > testDom.getColumn()){
//						beaten = false;
//					}else if(this.getRow() < testDom.getColumn()){
//						beaten = true;
//					}
//				}else if(this.getRow() == testDom.getColumn()){
//					if(this.getColumn() > testDom.getRow()){
//						beaten = false;
//					}else if(this.getColumn() < testDom.getRow()){
//						beaten = true;
//					}
//				}else{				//THIS domino is unable to beat the first played domino
//					beaten = true;
//				}
//			}else{
//				if(this.getRow() == testDom.getRow() && testDom.getRow() == suit){	
//					if(this.getColumn() > testDom.getColumn()){	
//						beaten = false;
//					}else if(this.getColumn() < testDom.getColumn()){
//						beaten = true;
//					}
//				}else if(this.getColumn() == testDom.getColumn() && testDom.getColumn() == suit){
//					if(this.getRow() > testDom.getRow()){
//						beaten = false;
//					}else if(this.getRow() < testDom.getRow()){
//						beaten = true;
//					}
//				}else if(this.getColumn() == testDom.getRow() && testDom.getRow() == suit){
//					if(this.getRow() > testDom.getColumn()){
//						beaten = false;
//					}else if(this.getRow() < testDom.getColumn()){
//						beaten = true;
//					}
//				}else if(this.getRow() == testDom.getColumn() && testDom.getColumn() == suit){
//					if(this.getColumn() > testDom.getRow()){
//						beaten = false;
//					}else if(this.getColumn() < testDom.getRow()){
//						beaten = true;
//					}
//				}
//			}
//		}
//		return beaten;
//	}
	
//	if(testDom.getRow() >= this.getRow()){
//	if(testDom.getRow() == this.getRow() && testDom.getColumn() > this.getColumn()){ //Doms are on the same row in the table
//		beaten = true;
//	}else if(testDom.getRow() > this.getRow()){ //testDom is on higher rows than the Dom under test
//		beaten = true;
//	}
//}
	
	public boolean isEqualPosition(DominoTablePosition dom){
		boolean isEqual = false;
		if(dom.getRow() == this.getRow() && dom.getColumn() == this.getColumn()){
			isEqual = true;
		}else if(dom.getRow() != this.getRow() && dom.getColumn() != this.getColumn()){
			isEqual = false;
		}
		return isEqual;
	}
		
}
