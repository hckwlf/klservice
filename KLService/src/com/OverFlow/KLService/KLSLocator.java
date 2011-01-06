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

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class KLSLocator extends KLService implements LocationListener {

	//MEMBERS
	public Location currentLocation;
	public String GPSstatus;
	
	//FUNCTION
	
	@Override
	public void onLocationChanged(Location location) {
		currentLocation = location;
		if (location != null) {
			GPSstatus = "Activated...";
			Toast.makeText(
					this,
					"Location changed : Lat: " + location.getLatitude()
							+ " Lng: " + location.getLongitude(),
					Toast.LENGTH_LONG).show();

			/*GeoPoint p = new GeoPoint((int) (loc.getLatitude() * 1E6),
					(int) (loc.getLongitude() * 1E6));
			mapController.animateTo(p);
			mapController.setZoom(12);
			mapView.invalidate();
			*/
		}
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		currentLocation = null;
		GPSstatus = "GPS or Provider are OFF...";
		Toast.makeText(this, GPSstatus, Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		GPSstatus = "Scanning...";
		Toast.makeText(this, GPSstatus, Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		GPSstatus = "Status Changed...";
		Toast.makeText(this, GPSstatus, Toast.LENGTH_LONG).show();
		
	}

}
