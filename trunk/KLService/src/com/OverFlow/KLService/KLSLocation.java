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
		//location = new Location(locationManager)
		mLocationListener = new KLSLocationListener();
		mLocationManager = locationManager;
	}
	
	public void locate() {
		
	}
	
	//Main Function:
	
}
