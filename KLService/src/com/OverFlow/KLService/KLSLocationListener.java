package com.OverFlow.KLService;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class KLSLocationListener implements LocationListener {
	private int mDebugLevel;
	
	public KLSLocationListener() {
		
		mDebugLevel = KLSDebugLog.getDebugLevel();
	}
	
	@Override
	public void onLocationChanged(Location location) {
	       if (location != null) {
	    		if (mDebugLevel  > 1) {
	    			//Toast.makeText(mContext, "Location Thread Starting ...", Toast.LENGTH_LONG).show();
	    		}
	    		if (mDebugLevel > 0) {
	    			Log.i(getClass().getSimpleName(), "AVIAD: Location changed: -- LAT=" + location.getLatitude() + " LON=" + location.getLongitude() +" ...");
	    		}
	    		
	    		//The next line should have been KLService.setLocation(location) but this is o.k. for now...
	    		KLService.mLocation = location;
	       }
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
