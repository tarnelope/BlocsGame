package Tetrominos;

import android.util.Log;

import com.bloc.blocs.TetrisBoard;
import com.bloc.blocs.Tile;

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
				Log.d("fillTiles case 0", "x is "+x+" y is "+y);
				//Top piece
				Tile t1 = board.getTileAt(x, y);
				board.fillBooleanTileAt(x, y);
				t1.setIsFilled(true);
				t1.setTetromino(this);
				//2nd piece
				Tile t2 = board.getTileAt(x, y+TetrisBoard.TILE_DIMEN);
				board.fillBooleanTileAt(x, y+TetrisBoard.TILE_DIMEN);
				t2.setIsFilled(true);
				t2.setTetromino(this);
				//3rd piece down
				Tile t3 = board.getTileAt(x, y+TetrisBoard.TILE_DIMEN*2);
				board.fillBooleanTileAt(x, y+TetrisBoard.TILE_DIMEN*2);
				t3.setIsFilled(true);
				t3.setTetromino(this);
				//4th piece down
				Tile t4 = board.getTileAt(x, y+TetrisBoard.TILE_DIMEN*3);
				board.fillBooleanTileAt(x, y+TetrisBoard.TILE_DIMEN*3);
				t4.setIsFilled(true);
				t4.setTetromino(this);
				break;
			case 1: 
				//1st left	
				Tile t1a = board.getTileAt(x-TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x-TetrisBoard.TILE_DIMEN, y);
				t1a.setIsFilled(true);
				t1a.setTetromino(this);
				//2nd left
				Tile t2a = board.getTileAt(x, y);
				board.fillBooleanTileAt(x, y);
				//t2a.setIsFilled(true);
				t2a.setTetromino(this);
				//3rd left
				Tile t3a = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y);
				t3a.setIsFilled(true);
				t3a.setTetromino(this);
				//4th right
				Tile t4a = board.getTileAt(x+TetrisBoard.TILE_DIMEN*2, y);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN*2, y);
				t4a.setIsFilled(true);
				t4a.setTetromino(this);
				break;
			case 2: //x: 60, y: 600
				Log.d("fillTiles case 2", "x is "+x+" y is "+y);
				//Top	
				Tile t1b = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y-TetrisBoard.TILE_DIMEN);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y-TetrisBoard.TILE_DIMEN);
				t1b.setIsFilled(true);
				t1b.setTetromino(this);
				//2nd 
				Tile t2b = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y);
				t2b.setIsFilled(true);
				t2b.setTetromino(this);
				//3rd 
				Tile t3b = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN);
				t3b.setIsFilled(true);
				t3b.setTetromino(this);
				//4th 
				Tile t4b = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN*2);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN*2);
				t4b.setIsFilled(true);
				t4b.setTetromino(this);
				break;
			case 3:
				//x is 120
				Log.d("Case 3", "x is " + x);
				//Left	
				Tile t1c = board.getTileAt(x-TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x-TetrisBoard.TILE_DIMEN, y);
				t1c.setIsFilled(true);
				t1c.setTetromino(this);
				//2nd 
				Tile t2c = board.getTileAt(x, y);
				board.fillBooleanTileAt(x, y);
				t2c.setIsFilled(true);
				t2c.setTetromino(this);
				//3rd 
				Tile t3c = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y);
				t3c.setIsFilled(true);
				t3c.setTetromino(this);
				//Right
				Tile t4c = board.getTileAt(x+TetrisBoard.TILE_DIMEN*2, y);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN*2, y);
				t4c.setIsFilled(true);
				t4c.setTetromino(this);
				break;
		}
	}

	@Override
	public boolean isClearBelow(TetrisBoard board) {
		float x = s.getX();
		float y = s.getY();

		switch (getRotationBy90()) {
			case 0:
				if (board.isBooleanTileFilled(x, y+TetrisBoard.TILE_DIMEN*4)) {
					Log.d("case 0 is NOT clear below", "x is "+x+" y is "+y);
					return false;
				} else {
					return true;
				}
			case 1:
				//x, y is 2nd tile from left
				x -= TetrisBoard.TILE_DIMEN;
				for (int i = 0; i < 4; i++) {
					boolean b = board.isBooleanTileFilled(x+(i*TetrisBoard.TILE_DIMEN), y+TetrisBoard.TILE_DIMEN);
					if (b) {
						return false;
					}
				}
				return true;
			case 2:
				//x, y is 2nd down
				//Log.d("case 2 checkBelow", "x is "+x+" y is "+y);
				boolean b2 = board.isBooleanTileFilled(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN*3);
				if (b2) {
					return false;
				} else {
					return true;
				}
			case 3:
				//x, y is 2nd from right
				x = x-TetrisBoard.TILE_DIMEN;
				for (int i = 0; i < 4; i++) {
					boolean b3 = board.isBooleanTileFilled(x+(i*TetrisBoard.TILE_DIMEN), y+TetrisBoard.TILE_DIMEN);
					if (b3) {
						return false;
					}
				}
				
			default:
				return true;
		}
		
	};

}
