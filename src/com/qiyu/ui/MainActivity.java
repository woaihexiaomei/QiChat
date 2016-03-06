package com.qiyu.ui;

import java.io.IOException;

import org.apache.harmony.javax.security.sasl.SaslException;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Packet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.qiyu.qichat.R;
import com.qiyu.util.UserConfig;

public class MainActivity extends Activity{

	
	Context sInstance;
	
	EditText et_name,et_psw;
	Button btn_login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_layout);
		
		sInstance = this;
		
		et_name = (EditText) findViewById(R.id.et_name);
		et_psw = (EditText) findViewById(R.id.et_psw);
		btn_login = (Button) findViewById(R.id.send);
		
		btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				
				try {
					UserConfig.xmppConnection.login(et_name.getText().toString(), et_psw.getText().toString());
				} catch (SaslException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XMPPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SmackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		UserConfig.init(sInstance);
		
		
		
	}
	
	
	
	
}
