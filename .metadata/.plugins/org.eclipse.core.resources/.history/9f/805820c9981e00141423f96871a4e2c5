package Tetrominos;

import java.util.ArrayList;

import com.bloc.blocs.TetrisBoard;
import com.bloc.blocs.Tile;

import android.util.Log;

public class LogPiece extends Tetromino {
	
	ArrayList<Tile> tiles;
	
	/*
	 * Parameters: (initial x, initial y, tilesWide, tilesTall, orientation)
	 */
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
			//Log.d("Building LogPiece", "row is "+t.getRow()+" and column is "+t.getCol());
		}
		//Log.d("LogPiece", "end of drawLogSprite(). s childCount is "+s.getChildCount());
	}

	@Override
	public float getOrigHeight() {
		switch (mRotationBy90) {
		case 0:
			return mHeight;
		case 1:
			return 2*TetrisBoard.TILE_DIMEN;
		case 2:
			return mHeight;
		case 3:
			return 3*TetrisBoard.TILE_DIMEN;
		default:
			return mHeight;
		}
	}

	@Override
	public float getOrigWidth() {
		switch (mRotationBy90) {
		case 0:
			return mWidth;
		case 1:
			return mHeight;
			//return mHeight-TetrisBoard.TILE_DIMEN;
		case 2:
			return mWidth;
		case 3:
			return mHeight;
		default:
			return mWidth;
		}
	}

	@Override
	protected void rotate() {
		s.setRotationCenter(TetrisBoard.TILE_DIMEN, 2f*TetrisBoard.TILE_DIMEN);
		s.setRotation(s.getRotation()+90);
		
	}

}
