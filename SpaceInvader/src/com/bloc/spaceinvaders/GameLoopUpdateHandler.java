package com.bloc.spaceinvaders;

import org.andengine.engine.handler.IUpdateHandler;

public class GameLoopUpdateHandler implements IUpdateHandler{

	@Override
	public void onUpdate(float pSecondsElapsed) {
		((GameScene) SpaceInvaders.getSharedInstance().mCurrentScene).moveShip();		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
