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
import android.location.LocationManager;

public class KLSLocation {
	//Members:
	private Location mLocation;
	private LocationListener mLocationListener;
	private LocationManager mLocationManager;
	
	//Constructor:
	public KLSLocation(LocationManager locationManager) {
		mLocationListener = new KLSLocationListener();
		mLocationManager = locationManager;
		
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
	}
	
	//Main Function:
	public Location getLastLocation() {
		return mLocation;
	}
	

	
}
