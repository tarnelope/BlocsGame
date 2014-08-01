package com.bloc.blocs;

import org.andengine.entity.sprite.Sprite;

import android.util.Log;

public abstract class Tetromino {
	
	//Properties
	static final int NUM_OF_TILES = 4;
	static final float START_X = TetrisBoard.LEFT_X+5*TetrisBoard.TILE_DIMEN;
	static final float START_Y = TetrisBoard.TOP_Y-2*TetrisBoard.TILE_DIMEN;
	static final int START_ROW = 0;
	static final int START_COL = 5;
	
	boolean mIsRotated0 = true;
	boolean mIsRotated90 = false;
	boolean mIsRotated180 = false;
	boolean mIsRotated270 = false;
	
	boolean mIsReflected = false;
	
	boolean isMoveable;
	
	int mX;
	int mY;
	
	float mHeight;
	float mWidth;
	
	Sprite s;
	
	//Requires override
	public void rotate90CW() {
		if (mIsRotated0) {
			mIsRotated0 = false;
			mIsRotated90 = true;
		} else if (mIsRotated90) {
			mIsRotated90 = false;
			mIsRotated180 = true;
		} else if (mIsRotated180) {
			mIsRotated180 = false;
			mIsRotated270 = true;
		} else if (mIsRotated270) {
			mIsRotated270 = false;
			mIsRotated0 = true;
			Log.d("rotation", String.valueOf(270) + "height is "+getOrigHeight());
		}
		Log.d("rotated x", String.valueOf(s.getX()));
		s.setRotation(s.getRotation()+90);
			
	};
	
	
	public Tetromino(int x, int y, int numTilesWide, int numTilesTall, boolean orientation) {
		mX = x;
		mY = y;
		mHeight = numTilesTall * TetrisBoard.TILE_DIMEN;
		mWidth = numTilesWide * TetrisBoard.TILE_DIMEN;
		isMoveable = true;
	}
	
	public float getOrigHeight() {
		if (mIsRotated0 ) {
			return mHeight;
		} else if (mIsRotated180) {
			return TetrisBoard.TILE_DIMEN;
		} else {
			return mWidth;
		}
	}
	
	public float getOrigWidth() {
		if (mIsRotated0 || mIsRotated180) {
			return mWidth;
		} else {
			return mHeight;
		}
	}
	
	public void setSprite(Sprite sprite) {
		s = sprite;
	}
	
	public Sprite getPiece() {
		return s;
	}
	
	public void setMoveable(boolean b) {
		isMoveable = b;
	}
	
	public void movePieceDown() {
		if (isMoveable && s.getY()+s.getHeight() < TetrisBoard.BOTTOM_Y - TetrisBoard.TILE_DIMEN) {
			float newY = s.getY() + TetrisBoard.TILE_DIMEN;
			s.setPosition(s.getX(), newY);
		}
		if (s.getX() <= TetrisBoard.LEFT_X) {
			s.setPosition(TetrisBoard.LEFT_X, s.getY());
		}
		if (s.getX() >= TetrisBoard.RIGHT_X) {
			s.setPosition(TetrisBoard.RIGHT_X, s.getY());
		}
	}
	
	public void movePieceHorizontally(float delta) {
		float oldX = s.getX();
		if (delta > 0) {
			s.setPosition(oldX+TetrisBoard.TILE_DIMEN, s.getY());
		} else if (delta < 0) {
			s.setPosition(oldX-TetrisBoard.TILE_DIMEN, s.getY());
		}
	}
		

}
