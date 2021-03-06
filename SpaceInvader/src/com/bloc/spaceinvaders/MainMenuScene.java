package com.bloc.spaceinvaders;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;

import com.bloc.blocs.R;

import android.util.Log;

public class MainMenuScene extends MenuScene implements IOnMenuItemClickListener{
	
	SpaceInvaders activity;
	final int MENU_START = 0; //flag for our Start button
	
	public MainMenuScene() {
		super(SpaceInvaders.getSharedInstance().mCamera);
		activity = SpaceInvaders.getSharedInstance();
		 
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		IMenuItem startButton = new TextMenuItem(MENU_START, activity.mFont, activity.getString(R.string.start), activity.getVertexBufferObjectManager());
		startButton.setPosition(mCamera.getWidth() / 2 - startButton.getWidth() / 2, mCamera.getHeight() / 2 - startButton.getHeight() / 2);
		addMenuItem(startButton);
		 
		setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		 switch (pMenuItem.getID()) {
	        case MENU_START:
	        	activity.setCurrentScene(new GameScene());
	        	Log.d("MainMenuScene", "STARTS!");
	            return true;
	        default:
	            break;
	    }
	    return false;
	}
	
}
