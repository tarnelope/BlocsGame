package com.bloc.blocs;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.SurfaceGestureDetector;

import android.hardware.SensorManager;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;



public class PlayScene extends Scene implements IOnSceneTouchListener {
	
	Camera mCamera;
	SurfaceGestureDetector gDetector;
	
	public PhysicsWorld physicsWorld;
	public Entity grid;
	TetrisPiece tetrisPiece;
	final TetrisBoard board;
	
	Sprite magLog;
	Sprite currentPiece;
	
	private float counter = 0.0f;
	
	public PlayScene() {
		
		setBackground(new Background(0.1f, 0.6f, 0.134f));
		mCamera = Blocs.getSharedInstance().mCamera;
		
		physicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);
		
		board = new TetrisBoard(this);

		addNewPiece();
	
		registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			float counter = 0;
			@Override
			public void onUpdate(float pSecondsElapsed) {
				counter += pSecondsElapsed;
				if (currentPiece.collidesWith(board.getGround())) {
					Log.d("PlayScene", "COLLIDED! at "+currentPiece.getY());
					tetrisPiece.setMoveable(false);
					float y = board.getGround().getY();
					currentPiece.setPosition(currentPiece.getX(), y-currentPiece.getHeight());
					addNewPiece();
				}
				if (counter > 0.5 && tetrisPiece.moveable) {
					counter = 0;
					currentPiece.setPosition(currentPiece.getX(), currentPiece.getY()+board.TILE_DIMEN);
				}
			}
		});
		
		
		Blocs.getSharedInstance().setCurrentScene(this);
		setOnSceneTouchListener(this);
	}
	
	float origX = TetrisBoard.LEFT_X+TetrisBoard.BOARD_WIDTH/2;
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, final TouchEvent pSceneTouchEvent)
	{
		
	    if (pSceneTouchEvent.isActionDown() && !pSceneTouchEvent.isActionMove())
	    {
	    	Log.d("PlayScene", "TOUCH!");
	        tetrisPiece.rotatePieceCCW();
	    }
		if (pSceneTouchEvent.isActionMove()) {
			Log.d("ACTION", "MOVING!!!");
			float newX = pSceneTouchEvent.getX();
			
    		int deltaX = Math.round((origX - newX)%1000);
    		Log.d("PlayScene", "deltaX is "+deltaX+" MOVED!!!");
    		if (Math.abs(deltaX) > 20) {
    	//		tetrisPiece.movePieceHorizontally(deltaX);
    		}
		};
		if (pSceneTouchEvent.isActionUp()) {
			origX = pSceneTouchEvent.getX();
		}
	    return false;
	}
	
	public void addNewPiece() {
		tetrisPiece = new TetrisPiece();
		currentPiece = tetrisPiece.getPiece();
		attachChild(currentPiece);
	}

}
