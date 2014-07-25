package com.bloc.blocs;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;



public class PlayScene extends Scene {
	
	Camera mCamera;
	public GamePiece gamePiece;

	public PlayScene() {
		setBackground(new Background(0.1f, 0.1f, 0.1f));
		mCamera = Blocs.getSharedInstance().mCamera;
		gamePiece = GamePiece.getSharedInstance();
		
		attachChild(gamePiece.piece);
		
		Blocs.getSharedInstance().setCurrentScene(this);
	}
}