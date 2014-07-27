package com.bloc.blocs;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import android.hardware.SensorManager;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;



public class PlayScene extends Scene {
	
	Camera mCamera;
	
	public PhysicsWorld physicsWorld;
	private static final FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
	public Entity grid;
	
	Sprite magLog;
	TetrisPiece currentPiece;
	
	private Float counter = 0.0f;

	public PlayScene() {
		
		setBackground(new Background(0.1f, 0.6f, 0.134f));
		mCamera = Blocs.getSharedInstance().mCamera;
		
		physicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);
		
		final TetrisBoard board = new TetrisBoard(this);
		//piece = new TetrisPiece().getPiece();
		//attachChild(piece);
		//magLog = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().logPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
		//Body mBody = createLogBody(physicsWorld, magLog, BodyType.StaticBody, FIXTURE_DEF);
		
		//attachChild(gamePiece.piece);
		//attachChild(magLog);
		addNewPiece();
		
		registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				if (currentPiece.collidesWith(board.getGround())) {
					currentPiece.setMoveable(false);
					float y = board.getGround().getY();
					currentPiece.setPosition(currentPiece.getX(), y-currentPiece.getHeight());
				}
				
			}
		});
		
		Blocs.getSharedInstance().setCurrentScene(this);
	}
	
	public void addNewPiece() {
		TetrisPiece tetrisPiece = new TetrisPiece();
		currentPiece = tetrisPiece;
		Log.d("Playscene", String.valueOf(currentPiece.getHeight()));
		attachChild(currentPiece);
	}
	
	
}
