package Tetrominos;

import android.util.Log;

import com.bloc.blocs.TetrisBoard;
import com.bloc.blocs.Tile;

public class TPiece extends Tetromino {

	public TPiece() {
		super(Math.round(TetrisBoard.RIGHT_X-5*TetrisBoard.TILE_DIMEN), 
				Math.round(TetrisBoard.TOP_Y-2*TetrisBoard.TILE_DIMEN), 3, 2, false);
		setTetrominoType("t");
		drawSprite();
	}

	@Override
	protected void drawSprite() {
		Tile t = new Tile(START_ROW, START_COL);
		tiles.add(t);
		s = t.getTile();
		Tile t2 = new Tile(1, 0);
		tiles.add(t2);
		s.attachChild(t2.getTile());
		Tile t3 = new Tile(-1, 0);
		tiles.add(t3);
		s.attachChild(t3.getTile());
		Tile t4 = new Tile(0, 1);
		tiles.add(t4);
		s.attachChild(t4.getTile());
		
	}

	@Override
	public void rotate() {
		s.setRotation(s.getRotation()+90);
		
	}

	@Override
	public float getOrigHeight() {
		return 0;
	}

	@Override
	public float getOrigWidth() {
		return 0;
	}

	@Override
	public void checkLeftBound() {
		s.setPosition(TetrisBoard.LEFT_X, s.getY());
	}

	@Override
	public void checkRightBound() {
		s.setPosition(TetrisBoard.RIGHT_X-2*TetrisBoard.TILE_DIMEN, s.getY());
		
	}

	@Override
	public void fillTiles(TetrisBoard board) {
		float x = s.getX(); 
		float y = s.getY();
		
		switch (getRotationBy90()) {
			//T pointing right.
			case 0:
				Log.d("TPiece fillTiles case 0", "x is "+x+" y is "+y);
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
				Tile t3 = board.getTileAt(x, y-TetrisBoard.TILE_DIMEN);
				board.fillBooleanTileAt(x, y-TetrisBoard.TILE_DIMEN);
				t3.setIsFilled(true);
				t3.setTetromino(this);
				//4th piece down
				Tile t4 = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y);
				t4.setIsFilled(true);
				t4.setTetromino(this);
				break;
			
			//T
			case 1: 
				//Left Roof
				Tile t1a = board.getTileAt(x-TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x-TetrisBoard.TILE_DIMEN, y);
				t1a.setIsFilled(true);
				t1a.setTetromino(this);
				//Top Center
				Tile t2a = board.getTileAt(x, y);
				board.fillBooleanTileAt(x, y);
				//t2a.setIsFilled(true);
				t2a.setTetromino(this);
				//Right Roof
				Tile t3a = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y);
				t3a.setIsFilled(true);
				t3a.setTetromino(this);
				//Stem
				Tile t4a = board.getTileAt(x, y+TetrisBoard.TILE_DIMEN);
				board.fillBooleanTileAt(x, y+TetrisBoard.TILE_DIMEN);
				t4a.setIsFilled(true);
				t4a.setTetromino(this);
				break;
				
			//T pointing Left
			case 2: 
				Log.d("fillTiles case 2", "x is "+x+" y is "+y);
				//Stem
				Tile t1b = board.getTileAt(x-TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x-TetrisBoard.TILE_DIMEN, y);
				t1b.setIsFilled(true);
				t1b.setTetromino(this);
				//Center
				Tile t2b = board.getTileAt(x, y);
				board.fillBooleanTileAt(x, y);
				t2b.setIsFilled(true);
				t2b.setTetromino(this);
				//Top roof
				Tile t3b = board.getTileAt(x, y-TetrisBoard.TILE_DIMEN);
				board.fillBooleanTileAt(x, y-TetrisBoard.TILE_DIMEN);
				t3b.setIsFilled(true);
				t3b.setTetromino(this);
				//Bottom roof
				Tile t4b = board.getTileAt(x, y+TetrisBoard.TILE_DIMEN);
				board.fillBooleanTileAt(x, y+TetrisBoard.TILE_DIMEN);
				t4b.setIsFilled(true);
				t4b.setTetromino(this);
				break;
			
			//Upside down T
			case 3:
				//x is 120
				Log.d("Case 3", "x is " + x);
				//Left	
				Tile t1c = board.getTileAt(x-TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x-TetrisBoard.TILE_DIMEN, y);
				t1c.setIsFilled(true);
				t1c.setTetromino(this);
				//Center
				Tile t2c = board.getTileAt(x, y);
				board.fillBooleanTileAt(x, y);
				t2c.setIsFilled(true);
				t2c.setTetromino(this);
				//Right
				Tile t3c = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y);
				board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y);
				t3c.setIsFilled(true);
				t3c.setTetromino(this);
				//Stem
				Tile t4c = board.getTileAt(x, y-TetrisBoard.TILE_DIMEN);
				board.fillBooleanTileAt(x, y-TetrisBoard.TILE_DIMEN);
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
		//Bottom of 
			case 0:
				if (board.isBooleanTileFilled(x, y+TetrisBoard.TILE_DIMEN*2) || board.isBooleanTileFilled(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN)) {
					Log.d("case 0 is NOT clear below", "x is "+x+" y is "+y);
					return false;
				} else {
					return true;
				}
			case 1:
				//x, y is 2nd tile from left
				x -= TetrisBoard.TILE_DIMEN;
				boolean b;
				for (int i = 0; i < 3; i++) {
					if (i != 1) {
						b = board.isBooleanTileFilled(x+(i*TetrisBoard.TILE_DIMEN), y+TetrisBoard.TILE_DIMEN);
					} else  {
						b = board.isBooleanTileFilled(x+(i*TetrisBoard.TILE_DIMEN), y+TetrisBoard.TILE_DIMEN*2);
					}
					if (b) {
						return false;
					}
				}
				return true;
			case 2:

				Log.d("case 2 checkBelow", "x is "+x+" y is "+y);
				if (board.isBooleanTileFilled(x, y+TetrisBoard.TILE_DIMEN*2) || board.isBooleanTileFilled(x-TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN)) {
					Log.d("case 0 is NOT clear below", "x is "+x+" y is "+y);
					return false;
				} else {
					return true;
				}
			case 3:
				//x, y is 2nd from right
				x = x-TetrisBoard.TILE_DIMEN;
				for (int i = 0; i < 3; i++) {
					boolean b3 = board.isBooleanTileFilled(x+(i*TetrisBoard.TILE_DIMEN), y+TetrisBoard.TILE_DIMEN);
					if (b3) {
						return false;
					}
				}
				
			default:
				return true;
		}
	}

}
