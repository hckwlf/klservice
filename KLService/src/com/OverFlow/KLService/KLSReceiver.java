/********************************************************************************
 * KLService
 * ---------
 * A general purpose all information gather service. 
 * Class: KLSActivity.
 * Class Description: Starter application for KLService main functionality. 
 * Author: Aviad Golan -=OverFlow=-
 * 			(aviadgolan@gmail.com)
 ********************************************************************************/

package com.OverFlow.KLService;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class KLSReceiver extends BroadcastReceiver{
	public static final String TAG = "LocationLoggerServiceManager";
	 
	@Override
	public void onReceive(Context context, Intent intent) {
		  if( "android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
		   ComponentName comp = new ComponentName(context.getPackageName(), KLService.class.getName());
		   ComponentName service = context.startService(new Intent().setComponent(comp));
		   if (null == service){
		    // something really wrong here
			   Log.e(TAG, "Could not start service " + comp.toString());
		   }
		  } else {
			  Log.e(TAG, "Received unexpected intent " + intent.toString());   
		  }
	}
}
