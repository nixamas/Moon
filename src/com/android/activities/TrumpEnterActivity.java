package com.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Toast;

import com.android.DominoHand;
import com.android.R;
import com.android.adapters.ViewAdapter;

public class TrumpEnterActivity extends Activity implements OnClickListener {
	
	Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, followMe;
	int TRUMP = -1;
//	Dom[] PLAYERS_HAND;		//current players hand
	DominoHand PLAYERS_HAND;
	int PLAYER;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trump_screen1);
		
		bt0 = (Button) findViewById(R.id.bt_0);
		bt0.setOnClickListener(this);
		bt1 = (Button) findViewById(R.id.bt_1);
		bt1.setOnClickListener(this);
		bt2 = (Button) findViewById(R.id.bt_2);
		bt2.setOnClickListener(this);
		bt3 = (Button) findViewById(R.id.bt_3);
		bt3.setOnClickListener(this);
		bt4 = (Button) findViewById(R.id.bt_4);
		bt4.setOnClickListener(this);
		bt5 = (Button) findViewById(R.id.bt_5);
		bt5.setOnClickListener(this);
		bt6 = (Button) findViewById(R.id.bt_6);
		bt6.setOnClickListener(this);
		followMe = (Button) findViewById(R.id.followMe);
		followMe.setOnClickListener(this);
		
		Bundle bundle = getIntent().getExtras();
		PLAYER = bundle.getInt("PLAYER");						//current player
		PLAYERS_HAND = MoonGameActivity.PLAYERS[PLAYER-1].getHand();		//current players hand
		
		Gallery g = (Gallery) findViewById(R.id.domGallery);
        g.setSpacing(15);
        g.setScrollContainer(true);
        g.setAdapter(new ViewAdapter(this, PLAYERS_HAND));
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.bt_0:
				TRUMP = 0;
			break;
			case R.id.bt_1:
				TRUMP = 1;
			break;
			case R.id.bt_2:
				TRUMP = 2;
			break;
			case R.id.bt_3:
				TRUMP = 3;
			break;
			case R.id.bt_4:
				TRUMP = 4;
			break;
			case R.id.bt_5:
				TRUMP = 5;
			break;
			case R.id.bt_6:
				TRUMP = 6;
			break;
			case R.id.followMe:
				TRUMP = 7;
			break;
		}
		Intent returnIntent = new Intent();
	    returnIntent.putExtra("ChoosenTrump",TRUMP);
	    setResult(RESULT_OK,returnIntent);
		finish();
	}
	
	@Override
	public void onBackPressed() {
		Toast.makeText(this, "Select a Trump", Toast.LENGTH_LONG).show();
		return;
	}

}
