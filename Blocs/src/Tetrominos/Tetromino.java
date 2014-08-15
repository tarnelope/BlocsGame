package Tetrominos;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;

import com.bloc.blocs.Blocs;
import com.bloc.blocs.TetrisBoard;
import com.bloc.blocs.Tile;

import android.util.Log;

public abstract class Tetromino {
	
	TetrisBoard tetrisBoard;
	
	//Properties
	static final int NUM_OF_TILES = 4;
	static final float START_X = TetrisBoard.LEFT_X+5*TetrisBoard.TILE_DIMEN;
	static final float START_Y = TetrisBoard.TOP_Y-2*TetrisBoard.TILE_DIMEN;
	static final int START_ROW = 0;
	static final int START_COL = 5;
	
	private String mTetromino;
	
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
	
	ArrayList<Tile> tiles;
	Sprite s;
	
	protected abstract void drawSprite();
	public abstract void rotate();
	public abstract float getOrigHeight();
	public abstract float getOrigWidth();
	public abstract void checkLeftBound();
	public abstract void checkRightBound();
	public abstract void fillTiles(TetrisBoard board);
	public abstract boolean isClearBelow(TetrisBoard board);
	
	public void setTetrominoType(String s) {
		mTetromino = s;
	}
	
	public String getTetrominoType() {
		return mTetromino;
	}
	
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
		tiles = new ArrayList<Tile>();
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
}
