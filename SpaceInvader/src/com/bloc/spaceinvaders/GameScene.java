package com.bloc.spaceinvaders;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;

import android.hardware.Sensor;
import android.hardware.SensorManager;

public class GameScene extends Scene{
	
	public Ship ship;
	Camera mCamera;
	
	public float accelerometerSpeedX;
	
	public GameScene() {
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		mCamera = SpaceInvaders.getSharedInstance().mCamera;
		ship = Ship.getSharedInstance();
		attachChild(ship.sprite);
		
		SpaceInvaders.getSharedInstance().setCurrentScene(this);
		SensorManager sensorManager = (SensorManager) SpaceInvaders.getSharedInstance().getSystemService(SpaceInvaders.SENSOR_SERVICE);
		SensorListener.getSharedInstance();
		sensorManager.registerListener(SensorListener.getSharedInstance(),
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
		
		registerUpdateHandler(new GameLoopUpdateHandler());
	}
	
	public void moveShip() {
		ship.moveShip(accelerometerSpeedX);
	}

}
