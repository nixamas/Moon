package com.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.R;
import com.android.interfaces.OnGameOver;

public class MainActivity extends Activity implements OnClickListener, OnGameOver {
	
//	MoonGameActivity MAIN_MOON_GAME;
//	Context APPLICATION_CONTEXT = this;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button NEW_GAME_BT = (Button) findViewById(R.id.new_game);
        NEW_GAME_BT.setOnClickListener(this);
//    	Base_Domino_Elements dominoTable = new Base_Domino_Elements(4, this);
//    	Dom firstDomino = new Dom(this, 6, 3);
//    	Dom secDomino = new Dom(this, 6, 6);
//    	Dom thrdDomino = new Dom(this, 4, 6);
//    	Dom lastDomino = new Dom(this, 2, 1);
//    	
//    	DominoTablePosition firstDominoPosition = dominoTable.getDominoPosition(firstDomino);
//    	DominoTablePosition secDominoPosition = dominoTable.getDominoPosition(secDomino);
//    	DominoTablePosition thrdDominoPosition = dominoTable.getDominoPosition(thrdDomino);
//    	DominoTablePosition lastDominoPosition = dominoTable.getDominoPosition(lastDomino);
//    	DominoTablePosition[] playedDoms = {firstDominoPosition, secDominoPosition, thrdDominoPosition, lastDominoPosition};
//    	
////    	boolean test = firstDominoPosition.isBeatenBy(secDominoPosition);
//    	int bestPlayedDom = UtilityFile.bestDomino(playedDoms);
    }

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, MoonGameActivity.class);
		startActivity(intent);
	}

	@Override
	public void onGameOver() {
		// TODO Auto-generated method stub
		
	}
    
    
    
}