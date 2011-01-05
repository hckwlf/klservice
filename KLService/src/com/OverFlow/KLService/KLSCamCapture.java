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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class KLSCamCapture extends Activity implements SurfaceHolder.Callback {
	private Camera camera;
	private boolean isPreviewRunning = false;
	private boolean mTakePic = true;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	public static AudioManager audioContext;
	private int ringerMode;
	private int mDebugLevel;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.onResume();
        setContentView(R.layout.takepicture);
        mDebugLevel = KLSDebugLog.getDebugLevel();
        
        //Get volume instance(We don't want shutter FX...)
        audioContext = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        
        Log.e(getClass().getSimpleName(), "TakingPicture...");
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        surfaceView = (SurfaceView)findViewById(R.id.svQuickLookup);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //camera.startPreview();
    }
    
	/*
	protected void onResume() {
		Log.e(getClass().getSimpleName(), "AVIAD: onResume... ");
		camera.startPreview();
		//super.onResume();
	}*/

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	public void surfaceCreated(SurfaceHolder holder)
	{
		if (mDebugLevel < 1) {
			Log.e(getClass().getSimpleName(), "AVIAD: surfaceCreated");
		}
		camera = Camera.open();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		Log.e(getClass().getSimpleName(), "AVIAD: surfaceChanged");
		if (mTakePic){
			camera.stopPreview();
			isPreviewRunning = false;
			//camera.setDisplayOrientation(90);
			
			//Save Ringer State and mute:
			ringerMode = audioContext.getRingerMode();
			if (mDebugLevel < 1) {
				audioContext.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			}
        	
        	//Take picture:
			camera.takePicture(null, mPictureCallback, mPictureCallback);
			
			//Restore Ringer State:
			if (mDebugLevel < 1) {
				audioContext.setRingerMode(ringerMode);
			}
			
			mTakePic = false;
		}
		if (isPreviewRunning) {
			camera.stopPreview();
		}
		Camera.Parameters p = camera.getParameters();
		p.setPreviewSize(w, h);
		camera.setParameters(p);
		try {
			camera.setPreviewDisplay(holder);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		camera.startPreview();
		isPreviewRunning = true;
	}
	
	Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
		public void onPictureTaken(byte[] imageData, Camera c) {
			Log.e(getClass().getSimpleName(), "AVIAD: Saving IMAGE....");
			if (imageData != null) {
				String state = Environment.getExternalStorageState();
			    if (Environment.MEDIA_MOUNTED.equals(state)) {
					//String fileName = Environment.getExternalStorageDirectory() + createName() + ".jpg";
			    	String fileName = Environment.getExternalStorageDirectory() + "TmpImage_MUHAHAHA.jpg";
					Log.i(getClass().getSimpleName(), "AVIAD: Saving image to: " + fileName);
					File imageFile = new File(fileName);
					try {
						BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(imageFile));
						bos.write(imageData);
						bos.flush();
						bos.close();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
					Log.e(getClass().getSimpleName(), "AVIAD: SDCARD is not Accessible...");
				}
				camera.startPreview();
				finish();
			}
		}
	};

	public void surfaceDestroyed(SurfaceHolder holder)
	{
		Log.e(getClass().getSimpleName(), "AVIAD: surfaceDestroyed");
		camera.stopPreview();
		isPreviewRunning = false;
		camera.release();
	}
	
	protected void onStop()
	{
		Log.e(getClass().getSimpleName(), "AVIAD: onStop");
		super.onStop();
	}
	
    private String createName() {
    	long dateTaken = System.currentTimeMillis();
        Date date = new Date(dateTaken);
        SimpleDateFormat dateFormat = new SimpleDateFormat("y_MM_d_m_s");

        return "qpt_" + dateFormat.format(date);
    }
}


