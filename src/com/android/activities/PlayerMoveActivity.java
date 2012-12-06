package com.android.activities;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.Domino;
import com.android.DominoHand;
import com.android.DominoTrick;
import com.android.Move;
import com.android.PlayedTricks;
import com.android.Player;
import com.android.R;
import com.android.adapters.ViewAdapter;
import com.android.interfaces.OnDominoPlayedListener;
import com.android.interfaces.OnPlayerMove;

/**
 * This activity will be called when the player is making their domino play. I imagine it would 
 * show something on screen that have their domino hand, the dominos that have been played this 
 * round, Indicator showing current trump & scores, Also previous played hands in a much smaller view. 
 * 
 * 
 */
public class PlayerMoveActivity extends Activity implements OnDominoPlayedListener {

	static Player PLAYER;
	static OnPlayerMove MOVE_LISTENER;
	static DominoTrick DOMINO_TRICK;
	static int team1score, team2score, trump, bidNum;
	static PlayedTricks PLAYED_TRICKS;
	DominoHand PLAYERS_HAND;	//current players hand
	Domino PLAYED_DOMINO;
	Gallery hg;
	TableLayout trickTable, playedDomTable;
	TableRow team1, team2;
	TextView playerDisplay, team1text, team2text, trmptext, bidtext;
	
	public static void start(Context context, OnPlayerMove moveListener, Player player, DominoTrick domTrick, PlayedTricks plydTricks, int t1score, int t2score, int trmp, int bid){
		MOVE_LISTENER = moveListener;
		PLAYER = player;
		DOMINO_TRICK = domTrick;
		PLAYED_TRICKS = plydTricks;
		team1score = t1score;
		team2score = t2score;
		trump = trmp;
		bidNum = bid;
		Intent i = new Intent(context, PlayerMoveActivity.class);
		((Activity) context).startActivity(i);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_move_screen);

		PLAYERS_HAND = new DominoHand();
		PLAYERS_HAND = PLAYER.getHand();
		PLAYERS_HAND.setDomPlayedListener(this);
		
		PLAYED_DOMINO = new Domino(getApplicationContext());
		
		playerDisplay = (TextView) findViewById(R.id.player_screen_title);
		playerDisplay.setText("PLAYER" + Integer.toString(PLAYER.getID()));
		team1text = (TextView) findViewById(R.id.team1);
		team1text.setText("TEAM 1: " + Integer.toString(team1score));
		team2text = (TextView) findViewById(R.id.team2);
		team2text.setText("TEAM 2: " + Integer.toString(team2score));
		trmptext = (TextView) findViewById(R.id.trmp);
		trmptext.setText("TRUMP: " + Integer.toString(trump));
		bidtext = (TextView) findViewById(R.id.bid);
		bidtext.setText("BID: " + Integer.toString(bidNum));
		
		hg = (Gallery) findViewById(R.id.domGallery);
        hg.setSpacing(15);
        hg.setScrollContainer(true);
        hg.setAdapter(new ViewAdapter(this, PLAYERS_HAND));
        
        trickTable = (TableLayout) findViewById(R.id.trick_table);
        team1 = new TableRow(this);
//        team1.setBackgroundColor(Color.RED);
        team2 = new TableRow(this);
//        team2.setBackgroundColor(Color.BLUE);
        trickTable.addView(team1);
        trickTable.addView(team2);
        if(DOMINO_TRICK.isEmptyTrick() != true){
        	buildTrickTable();
        }
        
        playedDomTable = (TableLayout) findViewById(R.id.played_dom_table);
        if(PLAYED_TRICKS.isEmpty() != true){
        	buildPlayedDomTable();
        }
        
	}
	
	@Override
	public void onBackPressed() {
		Toast.makeText(this, "Play domino asshole", Toast.LENGTH_LONG).show();
		return;
	}

	@Override
	public void onDominoPlayed(Domino domino) {
		if(MOVE_LISTENER != null){
			Domino copy = new Domino(domino.getContext(),domino.getSuit(), domino.getWeight());
			MOVE_LISTENER.onMovePlayed(new Move(PLAYER.getID(), copy));
		}
		trickTable.removeAllViews();
		playedDomTable.removeAllViews();
		finish();
	}
	
	public void buildTrickTable(){
		TextView t1Label = new TextView(this);
		TextView t2Label = new TextView(this);
		t1Label.setText(R.string.team1Vertical);
		t2Label.setText(R.string.team2Vertical);
		team1.addView(t1Label);
		team2.addView(t2Label);
		team1.setPadding(0, 5, 0, 2);
		team2.setPadding(0, 2, 0, 5);
		LinkedList<Move> team1dom = new LinkedList<Move>();
		LinkedList<Move> team2dom = new LinkedList<Move>();
		team1dom = DOMINO_TRICK.getTeamDominos(1);
		team2dom = DOMINO_TRICK.getTeamDominos(2);
		if(team1dom.isEmpty() != true){
			for(int i = 0; i < team1dom.size(); i++){
				View v = team1dom.get(i).getDomino().resetView();
				v.setPadding(3, 0, 3, 0);
				team1.addView(v);
			}
		}
		if(team2dom.isEmpty() != true){
			for(int i = 0; i < team2dom.size(); i++){
				View v = team2dom.get(i).getDomino().resetView();
				v.setPadding(3, 0, 3, 0);
				team2.addView(v);
			}
		}
		trickTable.removeAllViews();
		trickTable.addView(team1);
		trickTable.addView(team2);
		trickTable.invalidate();
	}
	
	private void buildPlayedDomTable(){
		if(PLAYED_TRICKS.isEmpty() != true){
			for(int i = 0; i < PLAYED_TRICKS.getSize(); i++){
				TableRow tr = PLAYED_TRICKS.getRow(getApplicationContext(), i);
				tr.setPadding(10, 5, 10, 5);
				playedDomTable.addView(tr);
			}
//			for(int i = 0; i < PLAYED_TRICKS.getSize(); i++){
//				playedDomTable.addView(PLAYED_TRICKS.getTrickTableRow(i));
//			}
//			playedDomTable.setColumnShrinkable(0, true);
			playedDomTable.invalidate();
		}
	}
}
