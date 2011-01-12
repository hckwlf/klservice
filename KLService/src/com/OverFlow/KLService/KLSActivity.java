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

import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class KLSActivity extends Activity {
	private Intent takePictureIntent;
	private TextView etTxtLog;
	public Bundle instanceState;
	public static boolean serviceStatus = false;
	
	//FUNCTIONS:
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onResume();
        setContentView(R.layout.main);
        instanceState = savedInstanceState;
        takePictureIntent = new Intent(this,  KLSCamCapture.class);

        etTxtLog = (TextView) findViewById(R.id.txtLog);
        
        startService(new Intent(KLSActivity.this, KLService.class));
        serviceStatus = true;
        setMainMenuListeners();
        
        //showUserMessage("KLService Starting...");
        //System.exit(0); //(Uncoment above...)
    }
    
    private void setMainMenuListeners() {
        //Button listeners:
        final Button btnServiceToggle = (Button) findViewById(R.id.btnServiceToggle);
        btnServiceToggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if (serviceStatus) {
            		stopService(new Intent(KLSActivity.this, KLService.class));
            		serviceStatus = false;
            	}
            	else {
            		startService(new Intent(KLSActivity.this, KLService.class));
            		serviceStatus = true;
            	}
            }
        });
        
        final Button btnCamTest = (Button) findViewById(R.id.btnCamTest);
        btnCamTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	etTxtLog.append("Aviad....\n");
            	setContentView(R.layout.menucam);
            	setCameraMenuListeners();
            }
        });
    }
    
    private void setCameraMenuListeners() {
        //Button listeners:
    	etTxtLog = (TextView) findViewById(R.id.txtLog);
    	//GO Back! Button:
        final Button btnBackToMain = (Button) findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	etTxtLog.append("Aviad....\n");
            	setContentView(R.layout.main);
            	setMainMenuListeners();
            }
        });
        
        //Take Picture Button:
        final Button btntakePicture = (Button) findViewById(R.id.btntakePicture);
        btntakePicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	etTxtLog.append("Will Take Picture...");
            	
            	startActivity(takePictureIntent);
            	setContentView(R.layout.main);
        		etTxtLog.append("Done...\n");
            }
        });
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	setMainMenuListeners();
    }
    /*
    private void showUserMessage(String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("-= OverFlow =-");
        alertDialog.setMessage(msg);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
        	  // stopService(new Intent(KLSActivity.this, KLService.class));
           }
        });
        alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();
    }
    */
}