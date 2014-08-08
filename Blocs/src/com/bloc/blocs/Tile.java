package com.bloc.blocs;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

import Tetrominos.Tetromino;

public class Tile {
	
	private boolean isFilled;
	int row;
	int col;
	float dimen = 30.0f;
	Sprite mTile;
	Tetromino tet;
	
	//for creating new tetromino piece. R and C are relative to a starting sprite that it is attached to.
	public Tile(int r, int c) {
		/**
		 * Params are row, column, x, y, width, height, texture.
		 */
		this(r, 
				c, 
				c*TetrisBoard.TILE_DIMEN, 
				r*TetrisBoard.TILE_DIMEN, 
				TetrisBoard.TILE_DIMEN, 
				TetrisBoard.TILE_DIMEN, 
				Blocs.getSharedInstance().cyanTile);
		isFilled = false;
	}
	
	public Tile(int r, int c, float pX, float pY, float pWidth, float pHeight,
			ITextureRegion pTextureRegion) {
		mTile = new Sprite(pX, pY, pWidth, pHeight, pTextureRegion, Blocs.getSharedInstance().getVertexBufferObjectManager());
		//Log.d("Tile", "x is "+mTile.getX()+" and y is "+mTile.getY());
		row = r;
		col = c;
	}
	
	public Sprite getTile() {
		return mTile;
	}
	
	public void setColRow(int r, int c) {
		row = r;
		col = c;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setIsFilled(boolean b) {
		isFilled = b;
	}
	
	public boolean isFilled() {
		return isFilled;
	}
	
	public void setTetromino(Tetromino t) {
		tet = t;
	}
}