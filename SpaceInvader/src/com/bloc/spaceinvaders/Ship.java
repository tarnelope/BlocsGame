package com.bloc.spaceinvaders;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;

import android.util.Log;

public class Ship {
	public Rectangle sprite;
	public static Ship instance;
	Camera mCamera;
	boolean moveable;
	
	public static Ship getSharedInstance() {
		if (instance == null)
			instance = new Ship();
		return instance;
	}
	
	private Ship() {
		sprite = new Rectangle(0, 0, 70, 30, SpaceInvaders.getSharedInstance().getVertexBufferObjectManager());
		moveable = true;
		mCamera = SpaceInvaders.getSharedInstance().mCamera;
		sprite.setPosition(mCamera.getWidth()/2-sprite.getWidth()/2,
				mCamera.getHeight()-sprite.getHeight()-10);
	}
	
	public void moveShip (float accelerometerSpeedX) {
		if (!moveable)
	        return;
	 
	    if (accelerometerSpeedX != 0) {
	    	//Left Limit
	        int lL = 0;
	        //Right Limit
	        int rL = (int) (mCamera.getWidth() - (int) sprite.getWidth());
	       
	        float newX;
	 
	        // Calculate New X,Y Coordinates within Limits
	        /*
	         * if current X is greater than 0, it will move 
	         * with the accelerometer speed, otherwise it is 0;
	         */
	        if (sprite.getX() >= lL) 
	            newX = sprite.getX() + -(accelerometerSpeedX);
	        else
	            newX = lL;
	       
	        /*
	         * if current X is less than 0, it will move 
	         * with the accelerometer speed, otherwise it is 0;
	         */
	        if (newX <= rL)
	            newX = sprite.getX() + -(accelerometerSpeedX);
	        else
	            newX = rL;
	 
	        // Double Check That New X,Y Coordinates are within Limits
	        if (newX < lL)
	            newX = lL;
	        else if (newX > rL)
	        	newX = rL;
	        
	        sprite.setPosition(newX, sprite.getY());
	    }
	}
}
