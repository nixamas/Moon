package com.android;

import android.content.Context;

/**
 * this is the "brains" behind the system. My idea is a sort of table (see reference document in the future). 
 * Once the trump is chosen a "domino table" will be built. This "domino table" will consist of all the dominos. 
 * fuck explaining this ill start working on a document. 
 * @author ryannix
 *
 */
public class Base_Domino_Elements {
	public Value_Tree domValTree;
	private static Context context;
	private int TRUMP;
	
	public Base_Domino_Elements(int trmp, Context _context){
		TRUMP = trmp;
		context = _context;
		
		int ROW6_SUIT = -1;
		int ROW5_SUIT = -1;
		int ROW4_SUIT = -1;
		int ROW3_SUIT = -1;
		int ROW2_SUIT = -1;
		int ROW1_SUIT = -1;
		switch(trmp){
		case 6:
			ROW6_SUIT = 5;
			ROW5_SUIT = 4;
			ROW4_SUIT = 3;
			ROW3_SUIT = 2;
			ROW2_SUIT = 1;
			ROW1_SUIT = 0;
		break;
		case 5:
			ROW6_SUIT = 6;
			ROW5_SUIT = 4;
			ROW4_SUIT = 3;
			ROW3_SUIT = 2;
			ROW2_SUIT = 1;
			ROW1_SUIT = 0;
		break;
		case 4:
			ROW6_SUIT = 6;
			ROW5_SUIT = 5;
			ROW4_SUIT = 3;
			ROW3_SUIT = 2;
			ROW2_SUIT = 1;
			ROW1_SUIT = 0;
		break;
		case 3:
			ROW6_SUIT = 6;
			ROW5_SUIT = 5;
			ROW4_SUIT = 4;
			ROW3_SUIT = 2;
			ROW2_SUIT = 1;
			ROW1_SUIT = 0;
		break;
		case 2:
			ROW6_SUIT = 6;
			ROW5_SUIT = 5;
			ROW4_SUIT = 4;
			ROW3_SUIT = 3;
			ROW2_SUIT = 1;
			ROW1_SUIT = 0;
		break;
		case 1:
			ROW6_SUIT = 6;
			ROW5_SUIT = 5;
			ROW4_SUIT = 4;
			ROW3_SUIT = 3;
			ROW2_SUIT = 2;
			ROW1_SUIT = 0;
		break;
		case 0:
			ROW6_SUIT = 6;
			ROW5_SUIT = 5;
			ROW4_SUIT = 4;
			ROW3_SUIT = 3;
			ROW2_SUIT = 2;
			ROW1_SUIT = 1;
		break;
		}
		domValTree = new Value_Tree(trmp, ROW6_SUIT, ROW5_SUIT, ROW4_SUIT, ROW3_SUIT, ROW2_SUIT, ROW1_SUIT);
	}
	
	public static class Value_Tree{
		static Domino[] row7 = new Domino[7];
		static Domino[] row6 = new Domino[6];
		static Domino[] row5 = new Domino[5];
		static Domino[] row4 = new Domino[4];
		static Domino[] row3 = new Domino[3];
		static Domino[] row2 = new Domino[2];
		static Domino[] row1 = new Domino[1];
		static Domino[][] allRows = {row1, row2, row3, row4, row5, row6, row7};
		
		public Value_Tree(int trmp, int suit6, int suit5, int suit4, int suit3, int suit2, int suit1){
			initDomArry(row7);
			setSuit(row7, trmp);
			setArryWeight(row7, 7, trmp);
			initDomArry(row6);
			setSuit(row6, suit6);
			setArryWeight(row6, 6, trmp);
			initDomArry(row5);
			setSuit(row5, suit5);
			setArryWeight(row5, 5, trmp);
			initDomArry(row4);
			setSuit(row4, suit4);
			setArryWeight(row4, 4, trmp);
			initDomArry(row3);
			setSuit(row3, suit3);
			setArryWeight(row3, 3, trmp);
			initDomArry(row2);
			setSuit(row2, suit2);
			setArryWeight(row2, 2, trmp);
			initDomArry(row1);
			setSuit(row1, suit1);
			setArryWeight(row1, 1, trmp);
		}
	}
	
	public static void setSuit(Domino[] dominoArry, int suit){
		for(int i = 0; i < dominoArry.length; i++){
			dominoArry[i].setSuit(suit);
		}
	}
	
	public static void setArryWeight(Domino[] dominoArry, int row, int trmp){
		int suit = dominoArry[0].getSuit();
		int wght = suit;
		dominoArry[(dominoArry.length) - 1].setWeight(wght);
		wght = row - 1;
		for(int i = 1; i < dominoArry.length; i++){
			if(wght == trmp || wght == suit){
				wght--;
				dominoArry[(dominoArry.length - i) - 1].setWeight(wght);
			}else{
				dominoArry[(dominoArry.length - i) - 1].setWeight(wght);
			}
			wght--;
		}
	}
	
	public static void initDomArry(Domino[] dominoArry){
		for(int i = 0; i < dominoArry.length; i++){
			dominoArry[i] = new Domino(context);
		}
	}
	
	@SuppressWarnings("static-access")
	public DominoTablePosition getDominoPosition(Domino domino){
		DominoTablePosition dominoPosition = new DominoTablePosition();
		boolean foundMatch = false; //I hate using flags but I figured this would be a lot easier
		int tableRow = 6;
		while(!foundMatch){
			for(int i = 0; i < domValTree.allRows[tableRow].length; i++){
//				Domino currentDomino = domValTree.allRows[tableRow][i];
				if(domValTree.allRows[tableRow][i].isEqual(domino) == true){
					dominoPosition.setColumn(i);
					dominoPosition.setRow(tableRow);
					if(tableRow == 6 && TRUMP != 7){ dominoPosition.setTrump(); }
					foundMatch = true;
				}
			}
			tableRow--;
		}
		
		return dominoPosition;
	}
	
}
