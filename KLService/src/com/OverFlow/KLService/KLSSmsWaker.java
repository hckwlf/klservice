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

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class KLSSmsWaker {
	private SmsMessage[] getMessagesFromIntent(Intent intent) {
		SmsMessage retMsgs[] = null;
		Bundle bdl = intent.getExtras();
		try{
			Object pdus[] = (Object [])bdl.get("pdus");
			retMsgs = new SmsMessage[pdus.length];
			for(int n=0; n < pdus.length; n++) {
				byte[] byteData = (byte[])pdus[n];
				retMsgs[n] = SmsMessage.createFromPdu(byteData);
			}        
		}
		catch(Exception e) {
			Log.e(getClass().getSimpleName(), "AVIAD: sms catch Failed...", e);
		}
		return retMsgs;
	}
}
