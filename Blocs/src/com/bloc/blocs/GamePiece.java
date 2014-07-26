package com.bloc.blocs;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.Entity;

import android.util.Log;

public class GamePiece extends Entity {
	
	private ArrayList<Entity> pieceArray;
	public Entity piece;
	public static GamePiece instance;
	boolean moveable;
	Camera mCamera;
	
	/*public static GamePiece getSharedInstance() {
		if (instance == null)
			instance = new GamePiece();
		return instance;
	} */
	
	public GamePiece() {
		moveable = true;
		mCamera = Blocs.getSharedInstance().mCamera;
		pieceArray = Blocs.getSharedInstance().getPieceArray();
		
		int index = randInt(0, 6);

		piece = pieceArray.get(index);
		piece.setColor(0.2f, 0.9f, 0.1f);
		piece.setPosition(mCamera.getWidth()/2, 60);
		
		TimerHandler timerHandler = new TimerHandler(1, true, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				movePieceDown();
				
			}
		});
		
		piece.registerUpdateHandler(timerHandler);
		
	}
	
	public void movePieceDown() {
		if (piece.getY() < TetrisBoard.BOTTOM_Y - TetrisBoard.TILE_DIMEN) {
			float newY = piece.getY() + TetrisBoard.TILE_DIMEN;
			piece.setPosition(piece.getX(), newY);
		}
	}
	
	public void releasePiece(float gravitySpeed) {
		if (!moveable) return;
		//physicsHandler.setVelocityY(Blocs.getSharedInstance().gravity_rate);
		
		if (gravitySpeed != 0) {
			//Left Limit
	        int lL = 90;
	        //Right Limit
	        int rL = 390;
	        //Lower Limit
	        int bottomL = 630; 
	       
	        float newX;
	        float newY;
	        
	        if (piece.getY() >= bottomL) {
	        	newY = bottomL;
	        } else {
	        	newY = piece.getY();
	        }
	        
	        piece.setPosition(piece.getX(), newY);
	       /* if (piece.getX() <= lL) {
	        	newX = lL;
	        } */
	        
		}
	}
	
	public boolean clearPiece() {
		synchronized (this) {
			return false;
		}
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
