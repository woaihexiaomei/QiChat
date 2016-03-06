package com.qiyu.util;

import java.io.IOException;

import org.apache.harmony.javax.security.sasl.SaslException;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.Packet;

import com.qiyu.model.UserModel;
import com.qiyu.ui.ChatActivity;

import android.content.Context;
import android.util.Log;

public class UserConfig {

 	public static XMPPConnection xmppConnection = null;
	
	public static ChatManager chatManager = null; 
	 
	public static void init(final Context context){
		
		ConnectionConfiguration configuration = new ConnectionConfiguration("192.168.0.105", 5222);
		
		 xmppConnection = new XMPPConnection(configuration) {
			
			@Override
			protected void shutdown() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected void sendPacketInternal(Packet arg0) throws NotConnectedException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void loginAnonymously() throws XMPPException, SmackException,
					SaslException, IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void login(String arg0, String arg1, String arg2)
					throws XMPPException, SmackException, SaslException, IOException {
				// TODO Auto-generated method stub
				
				Log.e("innog", "login");
				
				Contants.userModel = new UserModel(arg0, arg1);
				
				Util.gotoActivity(context, ChatActivity.class);
				
				
			}
			
			@Override
			public boolean isUsingCompression() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isSecureConnection() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isConnected() {
				// TODO Auto-generated method stub
				
				Log.e("innog", "connected");
				
				chatManager = ChatManager.getInstanceFor(xmppConnection);
				
				
				return false;
			}
			
			@Override
			public boolean isAuthenticated() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isAnonymous() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String getUser() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getConnectionID() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			protected void connectInternal() throws SmackException, IOException,
					XMPPException {
				// TODO Auto-generated method stub
				
			}
		};
		
		try {
			xmppConnection.connect();
			
			
			
		} catch (SmackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
}
