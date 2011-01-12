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

public final class KLSDebugLog {
	private static boolean mLoggingEnabled = true;
	//	0- No Output.
	//	1- Admin sees at LogCat
	//	2- User Output...
	private static int mDebugLevel = 2;
	
	private KLSDebugLog() {
		
	}
	
	public static void setDebugLogging(boolean enabled) {
		mLoggingEnabled = enabled;
	}
	
	public static boolean getDebugLogging() {
		return mLoggingEnabled;
	}
	
	public static void setDebugLevel(int level) {
		mDebugLevel = level;
	}
	
	public static int getDebugLevel() {
		return mDebugLevel;
	}
}
