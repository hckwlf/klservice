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

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class KLSLocationListener implements LocationListener {

	@Override
	public void onLocationChanged(Location location) {
		Log.i(getClass().getSimpleName(),"AVIAD: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.i(getClass().getSimpleName(), "AVIAD: GPS Provider Disabled...");
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.i(getClass().getSimpleName(), "AVIAD: GPS Provider Enabled...");
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
