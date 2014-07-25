package com.bloc.blocs;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;

import android.util.Log;

public class GamePiece extends Entity {
	
	private ArrayList<Entity> pieceArray;
	public Entity piece;
	public static GamePiece instance;
	boolean moveable;
	Camera mCamera;
	
	private final PhysicsHandler physicsHandler;
	
	public static GamePiece getSharedInstance() {
		if (instance == null)
			instance = new GamePiece();
		return instance;
	}
	
	private GamePiece() {
		moveable = true;
		mCamera = Blocs.getSharedInstance().mCamera;
		pieceArray = Blocs.getSharedInstance().getPieceArray();
		
		int index = randInt(0, 7);
		//int index = 1;
		Log.d("GamePiece", "index is "+index);
		piece = pieceArray.get(index);
		piece.setColor(getColor().RED);
		piece.setPosition(mCamera.getWidth()/2, mCamera.getHeight()/2);
		
		physicsHandler = new PhysicsHandler(piece);
		piece.registerUpdateHandler(physicsHandler);
		physicsHandler.setVelocityY(Blocs.getSharedInstance().gravity_rate);
		
		
	}
	
	public void fallingPiece(float gravityRate) {
		
	}
	
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

}
