package com.bloc.blocs;

import java.util.ArrayList;

import android.util.Log;

public class LogPiece extends Tetromino {
	
	ArrayList<Tile> tiles;
	
	public LogPiece() {
		super(Math.round(TetrisBoard.RIGHT_X-5*TetrisBoard.TILE_DIMEN), 
				Math.round(TetrisBoard.TOP_Y-2*TetrisBoard.TILE_DIMEN), 1, 4, false);
		
		drawLogSprite();
	}
	
	//How do I draw the log piece with the array
	private void drawLogSprite() {
		tiles = new ArrayList<Tile>();

		Tile t = new Tile(START_ROW, START_COL);
		for (int i = 0; i < NUM_OF_TILES; i++) {
			if (s == null) {
				s = t.getTile();
			} else {
				t = new Tile(i, 0);
				s.attachChild(t.getTile());
			}
			tiles.add(t);
			Log.d("Building LogPiece", "row is "+t.getRow()+" and column is "+t.getCol());
		}
		Log.d("LogPiece", "end of drawLogSprite(). s childCount is "+s.getChildCount());
		
	}

}
