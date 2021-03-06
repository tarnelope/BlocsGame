package com.bloc.spaceinvaders;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.BaseActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;


public class SpaceInvaders extends SimpleBaseGameActivity {
	 
    static final int CAMERA_WIDTH = 800;
    static final int CAMERA_HEIGHT = 480;
    
    //For singleton activity
    public static SpaceInvaders instance;
    public Scene mCurrentScene;
    
    public Camera mCamera;
    public static Font mFont;
 
    @Override
    public EngineOptions onCreateEngineOptions() {
        instance = this;
        mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
     
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
    }
 
    @Override
    protected void onCreateResources() {
    	 mFont = FontFactory.create(this.getFontManager(),this.getTextureManager(), 256, 256,Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
    	    mFont.load();
    }
 
    @Override
    protected Scene onCreateScene() {
        mEngine.registerUpdateHandler(new FPSLogger());
        mCurrentScene = new Scene();
        mCurrentScene.setBackground(new Background(0.09804f, 0.7274f, 0.8f));
        
        mEngine.registerUpdateHandler(new FPSLogger());
        mCurrentScene = new SplashScene();
        
        return mCurrentScene;
    }
    
    //Retrieves the current instance of BaseActivity
    public static SpaceInvaders getSharedInstance() {
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
    
    // ===========================================================
 	// Methods
 	// ===========================================================
}

