package com.qiyu.ui;

import com.qiyu.qichat.R;
import com.qiyu.util.Contants;
import com.qiyu.util.UserConfig;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ChatActivity extends Activity{

	TextView tx_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.chat_layout);
	
		
		tx_name = (TextView) findViewById(R.id.tx_username);

		tx_name.setText(Contants.userModel.username);
	
	}
	
}
