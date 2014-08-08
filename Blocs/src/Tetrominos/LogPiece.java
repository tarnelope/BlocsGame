package Tetrominos;

import java.util.ArrayList;

import com.bloc.blocs.TetrisBoard;
import com.bloc.blocs.Tile;

import android.util.Log;

public class LogPiece extends Tetromino {
	
	/*
	 * Parameters: (initial x, initial y, tilesWide, tilesTall, orientation)
	 */
	public LogPiece() {
		super(Math.round(TetrisBoard.RIGHT_X-5*TetrisBoard.TILE_DIMEN), 
				Math.round(TetrisBoard.TOP_Y-2*TetrisBoard.TILE_DIMEN), 1, 4, false);
		setTetrominoType("log");
		drawSprite();
	}
	
	@Override
	public void drawSprite() {
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
	public void rotate() {
		s.setRotationCenter(TetrisBoard.TILE_DIMEN, 2f*TetrisBoard.TILE_DIMEN);
		s.setRotation(s.getRotation()+90);
	}
	
	public void checkLeftBound() {
		switch (getRotationBy90()) {
			case 0:
				s.setPosition(TetrisBoard.LEFT_X, s.getY());
				break;
			case 1:
				s.setPosition(TetrisBoard.LEFT_X+TetrisBoard.TILE_DIMEN, s.getY());    			
				break;
			case 2:
				s.setPosition(TetrisBoard.LEFT_X-TetrisBoard.TILE_DIMEN, s.getY());
				break;
			case 3:
				s.setPosition(TetrisBoard.LEFT_X+TetrisBoard.TILE_DIMEN, s.getY());
				break;
			default:
				s.setPosition(TetrisBoard.LEFT_X, s.getY());
				break;
		}
	}
	
	public void checkRightBound() {
		switch (getRotationBy90()) {
			case 0:
				s.setPosition(TetrisBoard.RIGHT_X-TetrisBoard.TILE_DIMEN, s.getY());
				break;
			case 1:
				s.setPosition(TetrisBoard.RIGHT_X-3*TetrisBoard.TILE_DIMEN, s.getY());
				break;
			case 2:
				s.setPosition(TetrisBoard.RIGHT_X-TetrisBoard.TILE_DIMEN, s.getY());
			default:
				s.setPosition(TetrisBoard.RIGHT_X-getOrigHeight(), s.getY());
				break;
		}
	}
	
	public void fillTiles(TetrisBoard board) {
		float x = s.getX(); 
		float y = s.getY();
		switch (getRotationBy90()) {
			case 0:
				Tile t1 = board.getTileAt(x, y);
				t1.setIsFilled(true);
				t1.setTetromino(this);
				//2nd piece down
				Tile t2 = board.getTileAt(x, y+TetrisBoard.TILE_DIMEN);
				t2.setIsFilled(true);
				t2.setTetromino(this);
				//3rd piece down
				Tile t3 = board.getTileAt(x, y+TetrisBoard.TILE_DIMEN*2);
				t3.setIsFilled(true);
				t3.setTetromino(this);
				//4th piece down
				Tile t4 = board.getTileAt(x, y+TetrisBoard.TILE_DIMEN*3);
				t4.setIsFilled(true);
				t4.setTetromino(this);
				break;
			case 1:
				//1st left	
				Tile t1a = board.getTileAt(x-TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN);
				t1a.setIsFilled(true);
				t1a.setTetromino(this);
				//2nd left
				Tile t2a = board.getTileAt(x, y+TetrisBoard.TILE_DIMEN);
				t2a.setIsFilled(true);
				t2a.setTetromino(this);
				//3rd left
				Tile t3a = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN);
				t3a.setIsFilled(true);
				t3a.setTetromino(this);
				//4th right
				Tile t4a = board.getTileAt(x+TetrisBoard.TILE_DIMEN*2, y+TetrisBoard.TILE_DIMEN);
				t4a.setIsFilled(true);
				t4a.setTetromino(this);
				break;
			case 2:
				//Top	
				Tile t1b = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y);
				t1b.setIsFilled(true);
				t1b.setTetromino(this);
				//2nd 
				Tile t2b = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN);
				t2b.setIsFilled(true);
				t2b.setTetromino(this);
				//3rd 
				Tile t3b = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN*2);
				t3b.setIsFilled(true);
				t3b.setTetromino(this);
				//4th 
				Tile t4b = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN*3);
				t4b.setIsFilled(true);
				t4b.setTetromino(this);
				break;
			case 3:
				//Top	
				Tile t1c = board.getTileAt(x-TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN*2);
				t1c.setIsFilled(true);
				t1c.setTetromino(this);
				//2nd 
				Tile t2c = board.getTileAt(x, y+TetrisBoard.TILE_DIMEN*2);
				t2c.setIsFilled(true);
				t2c.setTetromino(this);
				//3rd 
				Tile t3c = board.getTileAt(x+TetrisBoard.TILE_DIMEN*2, y+TetrisBoard.TILE_DIMEN*2);
				t3c.setIsFilled(true);
				t3c.setTetromino(this);
				//4th 
				Tile t4c = board.getTileAt(x+TetrisBoard.TILE_DIMEN*3, y+TetrisBoard.TILE_DIMEN*2);
				t4c.setIsFilled(true);
				t4c.setTetromino(this);
				break;
		}
	}

	@Override
	public boolean isClearBelow(TetrisBoard board) {
		// TODO Auto-generated method stub
		return true;
	};

}