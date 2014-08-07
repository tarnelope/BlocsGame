package Tetrominos;

import org.andengine.entity.sprite.Sprite;

import com.bloc.blocs.TetrisBoard;

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
	int mRotationBy90 = 0;
	
	boolean mIsReflected = false;
	
	public boolean isMoveable;
	
	int mX;
	int mY;
	
	float mHeight;
	float mWidth;
	
	Sprite s;
	
	public void rotate90CW() {
		if (mRotationBy90 == 3) {
			mRotationBy90 = 0;
		} else {
			mRotationBy90++;
		}
		rotate();
			
	};
	
	public int getRotationBy90() {
		return mRotationBy90;
	}
	
	public Tetromino(int x, int y, int numTilesWide, int numTilesTall, boolean orientation) {
		mX = x;
		mY = y;
		mHeight = numTilesTall * TetrisBoard.TILE_DIMEN;
		mWidth = numTilesWide * TetrisBoard.TILE_DIMEN;
		isMoveable = true;
	}
	
	protected abstract void rotate();
	protected abstract float getOrigHeight();
	protected abstract float getOrigWidth();
	
	public void setSprite(Sprite sprite) {
		s = sprite;
	}
	
	public Sprite getPiece() {
		return s;
	}
	
	public void setMoveable(boolean b) {
		isMoveable = b;
	}
}
