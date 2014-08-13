package com.bloc.blocs;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.SurfaceGestureDetector;

import Tetrominos.*;
import android.hardware.SensorManager;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;



public class PlayScene extends Scene implements IOnSceneTouchListener {
	
	Camera mCamera;
	SurfaceGestureDetector gDetector;
	PlayScene scene;
	
	public PhysicsWorld physicsWorld;
	public Entity grid;
	Tetromino tetrisPiece;
	final TetrisBoard board;
	
	Sprite magLog;
	public Sprite currentPiece;
	
	private float counter = 0.0f;
	
	public PlayScene getPlayScene() {
		if (scene == null) {
			scene = new PlayScene();
		}
		return scene;
	}
	
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
				if (currentPiece != null ) {
					if (/*currentPiece.getY()+tetrisPiece.getOrigHeight() >= TetrisBoard.BOTTOM_Y+TetrisBoard.TILE_DIMEN || */!tetrisPiece.isClearBelow(board)) {
						tetrisPiece.setMoveable(false);
						currentPiece.setPosition(currentPiece.getX(), currentPiece.getY());
						Log.d("onUpdate", "y is " + currentPiece.getY() + " x is "+currentPiece.getX());
						//set tiles as filled 
						tetrisPiece.fillTiles(board);
						//check for filled row
						board.checkRows();
						//update grid
						board.updateBooleanGrid();
						//remove and redraw
						detachChildren();
						board.drawGrid(Blocs.getSharedInstance().mCurrentScene);
						
						addNewPiece();
					}
					if (counter > 0.1 && tetrisPiece.isMoveable) {
						counter = 0;
						//Log.d("onUpdate", "x is "+currentPiece.getX());
						currentPiece.setPosition(currentPiece.getX(), currentPiece.getY()+board.TILE_DIMEN);
					}
				}
			}
		});
		
		Blocs.getSharedInstance().setCurrentScene(this);
		setOnSceneTouchListener(this);
	}
	
	float origX = TetrisBoard.LEFT_X+TetrisBoard.BOARD_WIDTH/2;
	long startTime = 0;
	
	private float originX = 0.0f;
	private static final float MOVE_TOUCH_THRESHOLD = 50;
	private static final long TAP_TOUCH_THRESHOLD = 75;
	private float distanceMove = 0.0f;
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, final TouchEvent pSceneTouchEvent)
	{
		
	    if (pSceneTouchEvent.isActionDown())
	    {
	    	originX = pSceneTouchEvent.getX(); //the initial X
	    	startTime = pSceneTouchEvent.getMotionEvent().getEventTime(); //for calculating delta tie

	    } else if (pSceneTouchEvent.isActionMove()) {
			float deltaX = pSceneTouchEvent.getX() - originX;
    		int tilesMoved = Math.round(deltaX/MOVE_TOUCH_THRESHOLD);
    	
    		if (tetrisPiece != null && tilesMoved != 0) {
    			distanceMove = (TetrisBoard.TILE_DIMEN * tilesMoved);
    			
    			if (currentPiece.getX() + distanceMove <= TetrisBoard.LEFT_X) {

    				tetrisPiece.checkLeftBound();
    			} else if (currentPiece.getX() + distanceMove + tetrisPiece.getOrigWidth() >= TetrisBoard.RIGHT_X) {

    				tetrisPiece.checkRightBound();
    			} else {
    				currentPiece.setPosition(currentPiece.getX() + distanceMove, currentPiece.getY());
    			}
    			
				originX += (tilesMoved * MOVE_TOUCH_THRESHOLD); // Move logical origin to prevent large jumps after move
    		}
    		
		} else if (pSceneTouchEvent.isActionUp()) { //End of touch event
			float deltaTime = pSceneTouchEvent.getMotionEvent().getEventTime() - startTime;
			//Log.d("playScene", "UP! "+String.valueOf(deltaTime));
			if( deltaTime <= TAP_TOUCH_THRESHOLD ) {
				if (currentPiece.getX() - tetrisPiece.getOrigHeight() + TetrisBoard.TILE_DIMEN >= TetrisBoard.LEFT_X) {
					tetrisPiece.rotate90CW();
				} else {
					
				}
			}
		};
		
	    return false;
	}
	
	public void addNewPiece() {
		int randomPiece = randInt(0, 1);
		switch(randomPiece) {
			case 0:
				tetrisPiece = new LogPiece();
				break;
			case 1:
				tetrisPiece = new SquarePiece();
				break;
			default:
				tetrisPiece = new LogPiece();
		}
		currentPiece = tetrisPiece.getPiece();
		attachChild(currentPiece);
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
