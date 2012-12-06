package com.android.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.Base_Domino_Elements;
import com.android.DominoDeck;
import com.android.DominoTrick;
import com.android.Move;
import com.android.PlayedTricks;
import com.android.Player;
import com.android.R;
import com.android.UtilityFile;
import com.android.interfaces.OnBidEntered;
import com.android.interfaces.OnGameOver;
import com.android.interfaces.OnPlayerMove;
/**
 * This is the main activity for a moon game. When a new game is started, this activity will control everything. 
 * Class starts by initializing all buttons and shit, then creates players and deals the deck.  
 * 
 */
public class MoonGameActivity extends Activity implements OnClickListener, OnPlayerMove, OnBidEntered {
	static String TAG = "MoonGameActivity";
	DominoDeck GAME_DECK;
	public static DominoTrick HAND_TRICK;
	public PlayedTricks PLAYED_TRICKS;
	public static Player[] PLAYERS;
	Base_Domino_Elements GAME_TABLE;
	int PLAYER_BID_COUNT = 0, TRUMP = -1;
	private static int HIGH_BIDDER, CURRENT_PLAYERS_TURN, TEAM1_SCORE = 0, TEAM2_SCORE = 0,PLAYERS_BID = 0;
	private int TEAM1_ROUND_SCORE = 0, TEAM2_ROUND_SCORE = 0, HANDS_PLAYED = 0;
	static OnGameOver GAME_LISTENER;

	/*Buttons used to allow players to enter their bids*/
	Button plyr1, plyr2, plyr3, plyr4, playGame, player_move;
	TextView team1score, team2score;
	/*Below codes are used to start activities and retrieve results from those activities*/
	int TRUMP_ACTIVITY_CODE = 1234;
	int PLAYER_MOVE_ACTIVITY_CODE = 2345;
	
	static public void start(OnGameOver gameListener, int numOfPlayers){
		GAME_LISTENER = gameListener;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_screen);
		/* Initialize the screen elements*/
		plyr1 = (Button) findViewById(R.id.player1_bid);
		plyr1.setOnClickListener(this);
		plyr2 = (Button) findViewById(R.id.player2_bid);
		plyr2.setOnClickListener(this);
		plyr3 = (Button) findViewById(R.id.player3_bid);
		plyr3.setOnClickListener(this);
		plyr4 = (Button) findViewById(R.id.player4_bid);
		plyr4.setOnClickListener(this);
		playGame = (Button) findViewById(R.id.start_game);
		playGame.setOnClickListener(this);
		player_move = (Button) findViewById(R.id.move_button);
		player_move.setOnClickListener(this);
		team1score = (TextView) findViewById(R.id.team1_score);
		team2score = (TextView) findViewById(R.id.team2_score);
		/* Create the domino deck used for this game */
		GAME_DECK = new DominoDeck(getApplicationContext());
		/* Create the domino trick used for this game */
		HAND_TRICK = new DominoTrick();
		/* Create the PlayedTrick object that will store all played dominos */
		PLAYED_TRICKS = new PlayedTricks(getApplicationContext());
		/* Create an array of HumanPlayers */
		PLAYERS = new Player[4];			//This will be changed to numPlayers when 3 player game is implemented
		int index = 0;
		while(index < PLAYERS.length){
			/* Create new HumanPlayer and give domino Hand to player */
			PLAYERS[index] = new Player(GAME_DECK.dealDeck(), getApplicationContext(), (index +1));
			index++;
		}
	}
	
	/**
	 * This is the onClick Listener for this activity. When a button is pressed on screen the program will come here 
	 * to find which view was pressed. 
	 */
	@Override
	public void onClick(View view) {
//		Intent i;
		switch(view.getId()){
			case R.id.player1_bid:
				BidPageActivity.start(this, this, PLAYERS[0], PLAYERS_BID);
				plyr1.setVisibility(View.INVISIBLE);
				PLAYER_BID_COUNT++;
			break;
			
			case R.id.player2_bid:
				BidPageActivity.start(this, this, PLAYERS[1], PLAYERS_BID);
				plyr2.setVisibility(View.INVISIBLE);
				PLAYER_BID_COUNT++;
			break;
			
			case R.id.player3_bid:
				BidPageActivity.start(this, this, PLAYERS[2], PLAYERS_BID);
				plyr3.setVisibility(View.INVISIBLE);
				PLAYER_BID_COUNT++;
			break;
			
			case R.id.player4_bid:
				BidPageActivity.start(this, this, PLAYERS[3], PLAYERS_BID);
				plyr4.setVisibility(View.INVISIBLE);
				PLAYER_BID_COUNT++;
			break;			
			
			case R.id.start_game:
				getTrump(PLAYERS[HIGH_BIDDER-1]);
				playGame.setVisibility(View.INVISIBLE);
			break;
			
			case R.id.move_button:
				//TODO work on shit here
				//this should start activity and the onActivityResult should extract the data
				//from what domino the player played.

				PlayerMoveActivity.start(this, this, PLAYERS[CURRENT_PLAYERS_TURN - 1], HAND_TRICK, PLAYED_TRICKS, TEAM1_SCORE, TEAM2_SCORE, TRUMP, PLAYERS_BID);
				CURRENT_PLAYERS_TURN++;
				player_move.setText("Ready player "+Integer.toString(CURRENT_PLAYERS_TURN)+"?");
				if(CURRENT_PLAYERS_TURN >= PLAYERS.length + 1){ //if the current_player_turn is 
					CURRENT_PLAYERS_TURN = 1;					//out of range reset it to one
				}
			break;
		}
		
		/* If no one enters a bid then start a new game */
		if(PLAYER_BID_COUNT == 4 && PLAYERS_BID != 0){
			playGame.setVisibility(View.VISIBLE);
			PLAYER_BID_COUNT = 0;
		}else if(PLAYER_BID_COUNT == 4 && PLAYERS_BID == 0){
			//everyone passed so deal deck again
			Toast.makeText(this, "Start Another Game", Toast.LENGTH_LONG).show();
			//TODO: start another game
		}
		
	}
	
	public void dealDeckToPlayers(){
		GAME_DECK = new DominoDeck(getApplicationContext());
		for(int i = 0; i <PLAYERS.length; i++){
			PLAYERS[i].setHand(GAME_DECK.dealDeck());
		}
	}
	
	/**
	 * Allows the winner of the bid to choose the desired Trump for the game
	 * creates the DominoValueTable for the game based on the trump
	 * @param bidWinner
	 */
	public void getTrump(Player bidWinner){
		Intent i = new Intent(this, TrumpEnterActivity.class);
		i.putExtra("PLAYER", HIGH_BIDDER);
		startActivityForResult(i, TRUMP_ACTIVITY_CODE);
	}//getTrump

	private void updateGameScore(){
		if(HIGH_BIDDER == 1 || HIGH_BIDDER == 3){
			if(TEAM1_ROUND_SCORE < PLAYERS_BID){
				Toast.makeText(this, "Too Bad Team 1", Toast.LENGTH_LONG).show();
				TEAM1_SCORE -= TEAM1_ROUND_SCORE;
				if(TEAM1_SCORE < -28){ 
					TEAM1_SCORE = -28; 
					Toast.makeText(this, "Get it together Team 1", Toast.LENGTH_LONG).show();
				}
			}else if(TEAM1_ROUND_SCORE >= PLAYERS_BID){
				TEAM1_SCORE += TEAM1_ROUND_SCORE;
				Toast.makeText(this, "Good Job Team 2", Toast.LENGTH_LONG).show();
			}
			TEAM2_SCORE += TEAM2_ROUND_SCORE;
		}else if(HIGH_BIDDER == 2 || HIGH_BIDDER == 4){
			if(TEAM2_ROUND_SCORE < PLAYERS_BID){
				Toast.makeText(this, "Too Bad Team 1", Toast.LENGTH_LONG).show();
				TEAM2_SCORE -= TEAM2_ROUND_SCORE;
				if(TEAM2_SCORE < -28){ 
					TEAM2_SCORE = -28;
					Toast.makeText(this, "Get it together Team 2", Toast.LENGTH_LONG).show();
				}
			}else if(TEAM2_ROUND_SCORE >= PLAYERS_BID){
				TEAM2_SCORE += TEAM2_ROUND_SCORE;
				Toast.makeText(this, "Good Job Team 1", Toast.LENGTH_LONG).show();
			}
			TEAM1_SCORE += TEAM1_ROUND_SCORE;
		}
		TEAM1_ROUND_SCORE = 0;
		TEAM2_ROUND_SCORE = 0;
		team1score.setText("TEAM 1: " + Integer.toString(TEAM1_SCORE));
		team1score.setText("TEAM 2: " + Integer.toString(TEAM2_SCORE));
		
		if(TEAM1_SCORE >= 21){
			Toast.makeText(this, "Good Job Team 1", Toast.LENGTH_LONG).show();
			finish();
		}else if(TEAM2_SCORE >= 21){
			Toast.makeText(this, "Good Job Team 2", Toast.LENGTH_LONG).show();
			finish();
		}
	}
	
	private void updateRoundScore(int handWinner){
		switch(handWinner){
			case 1:
			case 3:
				TEAM1_ROUND_SCORE++;
			break;
			case 2:
			case 4:
				TEAM2_ROUND_SCORE++;
			break;
		}
	}
	
	/**
	 * When an activity that was started For Result completes the program comes here. 
	 * I find what activity just finished and extract the appropriate data from the Intent
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == TRUMP_ACTIVITY_CODE){
			TRUMP = data.getIntExtra("ChoosenTrump", -1);
			GAME_TABLE = new Base_Domino_Elements(TRUMP, getApplicationContext());
			CURRENT_PLAYERS_TURN = HIGH_BIDDER;
			player_move.setText("Ready player " + Integer.toString(CURRENT_PLAYERS_TURN) + "?");
			playGame.setVisibility(View.INVISIBLE);
			player_move.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onMovePlayed(Move move) {
		HAND_TRICK.playDomino(move);
		PLAYERS[move.getPlayer_id() - 1].getHand().removeDomino(move.getDomino());
		
		if(HAND_TRICK.allPlayersPlayed() == true){	//END OF TRICK
			HANDS_PLAYED++;
			PLAYED_TRICKS.addTrick(HAND_TRICK);
//			PLAYED_TRICKS.addTrickTableRow(HAND_TRICK);
			int HAND_WINNER_ID = UtilityFile.returnWinner4plyr(this, GAME_TABLE, HAND_TRICK);
			HAND_TRICK.clearTrick();
			Toast.makeText(this, "Congratulations Player " + Integer.toString(HAND_WINNER_ID), Toast.LENGTH_LONG).show();
			updateRoundScore(HAND_WINNER_ID);
			CURRENT_PLAYERS_TURN = HAND_WINNER_ID;
			player_move.setText("Ready player "+Integer.toString(CURRENT_PLAYERS_TURN)+"?");
		}
		
		if(HANDS_PLAYED == 7){		//END OF ROUND
			Toast.makeText(this, "END OF ROUND", Toast.LENGTH_LONG).show();
			updateGameScore();
			plyr1.setVisibility(View.VISIBLE);
			plyr2.setVisibility(View.VISIBLE);
			plyr3.setVisibility(View.VISIBLE);
			plyr4.setVisibility(View.VISIBLE);
			player_move.setVisibility(View.INVISIBLE);
			dealDeckToPlayers();
			PLAYERS_BID = 0;
			PLAYED_TRICKS = new PlayedTricks(this);
		}
	}

	@Override
	public void playerBidEntered(int bid, int highBidder) {
		if(bid > PLAYERS_BID){
			HIGH_BIDDER = highBidder;
			PLAYERS_BID = bid;
		}
	}
	
}
