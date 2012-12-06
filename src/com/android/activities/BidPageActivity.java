package com.android.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;

import com.android.Player;
import com.android.R;
import com.android.adapters.ViewAdapter;
import com.android.interfaces.OnBidEntered;

/**
 * This activity is called when a player is going to enter their bid for trump. Once the activity 
 * is started the user will see their dominos and be given the oppurtunity to enter their bid. There 
 * is a text input box that the user can type their bid and buttons for "PASS" or "SHOOT THE MOON" 
 * 
 */
public class BidPageActivity extends Activity implements OnClickListener{
	int myBid = 0;		//current players bid
	static String TAG = "BidPageActivity";
	static Player PLAYER;
	static OnBidEntered BidListener;
	static int prevBid;
	/* Screen Elements */
	EditText entText;
	Button bidButton, passButton, shootButton;
	TextView textPrev;

	public static void start(Context context, OnBidEntered bidListener, Player player, int previousBid){
		PLAYER = player;
		BidListener = bidListener;
		prevBid = previousBid;
		Intent i = new Intent(context, BidPageActivity.class);
		((Activity) context).startActivity(i);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bid_screen1);
		
		/* Init Screen Elements */
		entText = (EditText) findViewById(R.id.txt_input);
		bidButton = (Button) findViewById(R.id.bt_bid);
		passButton = (Button) findViewById(R.id.bt_pass);
		shootButton = (Button) findViewById(R.id.bt_shoot_moon);
		textPrev = (TextView) findViewById(R.id.prevBidVal);
		textPrev.setText(Integer.toString(prevBid));
		
		/* Set onClickListener for buttons */
		bidButton.setOnClickListener(this);
		passButton.setOnClickListener(this);
		shootButton.setOnClickListener(this);
		Toast.makeText(this, "Player "+Integer.toString(PLAYER.getID()), Toast.LENGTH_LONG).show();
		
		/* This gallery shows the players domino hand */
        Gallery g = (Gallery) findViewById(R.id.domGallery);
        g.setSpacing(15);
        g.setScrollContainer(true);
        g.setAdapter(new ViewAdapter(this, PLAYER.getHand()));
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			/* The bid button was pressed */
			case R.id.bt_bid:
					/* Get the text in the text box */
					String bidStr = "0";
					bidStr = entText.getText().toString().trim();
					if(!(bidStr.equals(null)) && !(bidStr.equalsIgnoreCase(""))){
						int bid = Integer.parseInt(bidStr);
						/* if no bid before and players bid is valid */
						if( (bid >= 4 && bid <= 7) && prevBid == 0){
							myBid = bid;
							finish();
						/* If a player bid previous and current players bid is above previous bid */
						}else if((bid > prevBid && bid <= 7) && prevBid != 0){
							myBid = bid;
							finish();
						}else{
							//wrong input
							Toast.makeText(this, "Enter Correct Bid", Toast.LENGTH_LONG).show();
						}
					}else{
						Toast.makeText(this, "Enter Correct Bid", Toast.LENGTH_LONG).show();
					}
			break;
			/* The pass button was pressed */
			case R.id.bt_pass:
				myBid = 0;
				finish();
			break;
			/* The shoot the moon button was pressed */
			case R.id.bt_shoot_moon:
				if(prevBid <= 7){
					myBid = 8;
				}else{
					myBid = prevBid;
					myBid++;
				}
				Toast.makeText(this, "Good Luck", Toast.LENGTH_LONG).show();
				finish();
			break;
			default:
			break;
		}
	}
	
	/**
	 * This overrides the hardware back button. 
	 */
	@Override
	public void onBackPressed() {
		Toast.makeText(this, "Enter your bid", Toast.LENGTH_LONG).show();
		return;
	}
	
	/**
	 * called when the activity is destroyed, either through finish() or Android destroying it
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(BidListener != null){
			BidListener.playerBidEntered(myBid, PLAYER.getID());
		}
	}
}
