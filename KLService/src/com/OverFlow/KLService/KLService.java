/****************************************
 * KLService
 * ---------
 * A general purpose all information gather service. 
 * Class: KLService.
 * Class Description: Main functionality service. 
 * Version: 0.1
 * Author: Aviad Golan -= OverFlow =-
 * 			(aviadgolan@gmail.com)
 ****************************************/

package com.OverFlow.KLService;

import android.app.Service;
//import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class KLService extends Service implements KeyListener {
	//MEMBERS:
	private int mDebugLevel;
	
	//FUNCTIONS:
    @Override
    public IBinder onBind(Intent arg0) {
          return null;
    }
    
    @Override
    public void onCreate() {
    	mDebugLevel = KLSDebugLog.getDebugLevel();
		super.onCreate();
		if (mDebugLevel  > 0) {
			Toast.makeText(this, "Service created ...", Toast.LENGTH_LONG).show();
		}
		if (mDebugLevel > 1) {
			Log.i(getClass().getSimpleName(), "AVIAD: Service created ...");
		}
		
		  
		//serviceStatus = true;
		//We go to our logic stuff....
		KLSlogic();
    }
    
    @Override
    public void onDestroy() {
		super.onDestroy();
		if (mDebugLevel  > 1) {
			Toast.makeText(this, "Service destroyed ...", Toast.LENGTH_LONG).show();
		}
		if (mDebugLevel > 0) {
			Log.i(getClass().getSimpleName(), "AVIAD: Service destroyed ...");
		}
		//serviceStatus = false;
		//TO ANNOY>: Later ;) //////////////////////////////////////
		//startService(new Intent(KLService.this, KLService.class));
		////////////////////////////////////////////////////////////
    }

	@Override
	public void clearMetaKeyState(View view, Editable content, int states) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInputType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean onKeyDown(View view, Editable text, int keyCode,
			KeyEvent event) {
		Toast.makeText(this, "...KeyPressDown...", Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public boolean onKeyOther(View view, Editable text, KeyEvent event) {
		Toast.makeText(this, "...KeyPressOther...", Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
		Toast.makeText(this, "...KeyPressUP...", Toast.LENGTH_LONG).show();
		return false;
	}
	
	public void KLSlogic() {
    	//Logication ba-rosh...
        SystemClock.sleep(1000);
	}
}

