package com.bloc.blocs;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.R.bool;
import android.graphics.Typeface;



public class Blocs extends SimpleBaseGameActivity {
	
	/*
	 * Playfield is 10 cells wide and at least 22 cells tall, 
	 * where rows above 20 are hidden or obstructed by the field 
	 * frame
	 * 
	 * So, say each cell is 30 wide, which leave 180 for margin width wise, 74 height wise
	 * 
	 */
	static final int CAMERA_WIDTH = 480;
	static final int CAMERA_HEIGHT = 800;
	
	//For singleton activity
	public static Blocs instance;
	public Scene mCurrentScene;
	
	public Camera mCamera;
	public static Font mFont;
	
	private ArrayList<Entity> pieceArray = new ArrayList<Entity>();
	
	public float gravity_rate = 100.5f;
	
	public static Blocs getSharedInstance() {
		if (instance == null) 
			instance = new Blocs();
		return instance;
	}
	
	// updates current main scene
    public void setCurrentScene(Scene scene) {
        mCurrentScene = scene;
        getEngine().setScene(mCurrentScene);
    }
    
    public Font getFont() {
    	return mFont;
    }
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		instance = this;
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
	}
	
    @Override
    protected void onCreateResources() {
    	mFont = FontFactory.create(this.getFontManager(),this.getTextureManager(), 256, 256,Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 28);
 	    mFont.load();
 	    
 	    pieceArray.add(getSquare());
 	    pieceArray.add(getLog());
 	    pieceArray.add(getS());
 	    pieceArray.add(getZ());
	 	pieceArray.add(getRightL());
	 	pieceArray.add(getLeftL());
	 	pieceArray.add(getT());
    }

    @Override
    protected Scene onCreateScene() {
        mEngine.registerUpdateHandler(new FPSLogger());
        mCurrentScene = new Scene();
        mCurrentScene.setBackground(new Background(0f, 0f, 0f));
        
        mEngine.registerUpdateHandler(new FPSLogger());
        mCurrentScene = new HomeScene();
        
        return mCurrentScene;
    }
    
    public ArrayList<Entity> getPieceArray() {
    	return pieceArray;
    }
    
    public Entity getSquare() {
		Rectangle upperLeft = new Rectangle(0, 0, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle upperRight = new Rectangle(30, 0, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle lowerLeft = new Rectangle(0, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle lowerRight = new Rectangle(30, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Entity square = new Entity(0, 0);
		square.attachChild(upperLeft);
		square.attachChild(upperRight);
		square.attachChild(lowerRight);
		square.attachChild(lowerLeft);
		return square;
	}
	
	public Entity getLog() {
		Rectangle one = new Rectangle(0, 0, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle two = new Rectangle(0, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle three = new Rectangle(0, 60, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle four = new Rectangle(0, 90, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Entity shape = new Entity(0, 0);
		shape.attachChild(one);
		shape.attachChild(two);
		shape.attachChild(three);
		shape.attachChild(four);
		return shape;
	}
	
	public Entity getS() {
		Rectangle one = new Rectangle(0, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle two = new Rectangle(30, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle three = new Rectangle(0, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle four = new Rectangle(60, 0, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Entity shape = new Entity(0, 0);
		shape.attachChild(one);
		shape.attachChild(two);
		shape.attachChild(three);
		shape.attachChild(four);
		return shape;
	}
	
	public Entity getZ() {
		Rectangle one = new Rectangle(0, 0, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle two = new Rectangle(0, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle three = new Rectangle(30, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle four = new Rectangle(60, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Entity shape = new Entity(0, 0);
		shape.attachChild(one);
		shape.attachChild(two);
		shape.attachChild(three);
		shape.attachChild(four);
		return shape;
	}
	
	public Entity getLeftL() {
		Rectangle one = new Rectangle(0, 0, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle two = new Rectangle(0, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle three = new Rectangle(0, 60, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle four = new Rectangle(30, 60, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Entity shape = new Entity(0, 0);
		shape.attachChild(one);
		shape.attachChild(two);
		shape.attachChild(three);
		shape.attachChild(four);
		return shape;
	}
	
	public Entity getRightL() {
		Rectangle one = new Rectangle(30, 0, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle two = new Rectangle(30, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle three = new Rectangle(30, 60, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle four = new Rectangle(0, 60, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Entity shape = new Entity(0, 0);
		shape.attachChild(one);
		shape.attachChild(two);
		shape.attachChild(three);
		shape.attachChild(four);
		return shape;
	}
	
	public Entity getT() {
		Rectangle one = new Rectangle(0, 0, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle two = new Rectangle(30, 0, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle three = new Rectangle(60, 0, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Rectangle four = new Rectangle(30, 30, 30, 30, Blocs.getSharedInstance().getVertexBufferObjectManager());
		Entity shape = new Entity(0, 0);
		shape.attachChild(one);
		shape.attachChild(two);
		shape.attachChild(three);
		shape.attachChild(four);
		return shape;
	}

}
