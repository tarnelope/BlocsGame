package com.bloc.blocs;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;

import android.util.Log;

public class HomeScene extends MenuScene implements IOnMenuItemClickListener{
	
	Blocs blocs;
	final int PLAY_BTN = 0; //flag for our Start button
	final int LEADER_BTN = 1;
	final int ACHIEVE_BTN = 2;
	
	public HomeScene() {
		super(Blocs.getSharedInstance().mCamera);
		blocs = Blocs.getSharedInstance();
		setBackground(new Background(0.3432f, 0.9845f, 0.474f));
		
		IMenuItem playBtn = new TextMenuItem(PLAY_BTN, blocs.mFont, blocs.getString(R.string.play), blocs.getVertexBufferObjectManager());
		playBtn.setPosition(mCamera.getWidth()/2-playBtn.getWidth()/2, mCamera.getHeight()*2/3 - playBtn.getHeight()/2);
		addMenuItem(playBtn);
		
		IMenuItem leaderBtn = new TextMenuItem(LEADER_BTN, blocs.mFont, blocs.getString(R.string.leader_board), blocs.getVertexBufferObjectManager());
		leaderBtn.setPosition(mCamera.getWidth()/2-leaderBtn.getWidth()/2, mCamera.getHeight()*2/3 + leaderBtn.getHeight());
		addMenuItem(leaderBtn);
		
		IMenuItem achievementsBtn = new TextMenuItem(ACHIEVE_BTN, blocs.mFont, blocs.getString(R.string.achieve), blocs.getVertexBufferObjectManager());
		achievementsBtn.setPosition(mCamera.getWidth()/2-achievementsBtn.getWidth()/2, mCamera.getHeight()*2/3+achievementsBtn.getHeight()*5/2);
		addMenuItem(achievementsBtn);
		
		setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
			case PLAY_BTN:
				blocs.setCurrentScene(new PlayScene());
				Log.d("HomeScene", "PLAY_BTN");
				return true;
			case ACHIEVE_BTN:
				Log.d("HomeScene", "ACHIEVE_BTN");
				return true;
			case LEADER_BTN:
				Log.d("HomeScene", "LEADER_BTN");
				return true;
			default:
				Log.d("HomeScene", "default");
		}
		return false;
	}

}
