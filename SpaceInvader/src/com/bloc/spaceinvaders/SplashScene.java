package com.bloc.spaceinvaders;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.modifier.IModifier;

import com.bloc.blocs.R;

import android.util.Log;

public class SplashScene extends Scene {

	SpaceInvaders activity;
	
	public SplashScene() {
		setBackground(new Background(0.09804f, 0.6274f, 0));
		activity = SpaceInvaders.getSharedInstance();
		
		Text title1 = new Text(5, 5, SpaceInvaders.mFont, activity.getString(R.string.app_name), "Score: XXXX".length(), activity.getVertexBufferObjectManager());
				//new Text(0, 0, activity.getString(R.string.title_1), activity.getVertexBufferObjectManager());
		Text title2 = new Text(0, 0, SpaceInvaders.mFont, activity.getString(R.string.title_2), activity.getVertexBufferObjectManager()); 
	
		title1.setPosition(-title1.getWidth(), activity.mCamera.getHeight() / 2);
		title2.setPosition(activity.mCamera.getWidth(), activity.mCamera.getHeight() / 2);
		 
		attachChild(title1);
		attachChild(title2);
		 
		title1.registerEntityModifier(new MoveXModifier(1, title1.getX(), activity.mCamera.getWidth() / 2 - title1.getWidth()));
		title2.registerEntityModifier(new MoveXModifier(1, title2.getX(), activity.mCamera.getWidth() / 2));
		
		loadResources();
	}
	
	public void loadResources() {
		//This shows the Splash Scene for 2 seconds then switches it to the MainmenuScene
		DelayModifier dMod = new DelayModifier(2, new IEntityModifierListener() {
			
			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
				Log.d("SplashScene", "onModifierStarted");
			}
			
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				Log.d("SplashScene", "onModifierFinished");
				activity.setCurrentScene(new MainMenuScene());
			}
		});
		registerEntityModifier(dMod);
	}
}
