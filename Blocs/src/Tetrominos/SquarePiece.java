package Tetrominos;

import com.bloc.blocs.TetrisBoard;
import com.bloc.blocs.Tile;

public class SquarePiece extends Tetromino {

	public SquarePiece() {
		super(Math.round(TetrisBoard.RIGHT_X-5*TetrisBoard.TILE_DIMEN), 
				Math.round(TetrisBoard.TOP_Y-2*TetrisBoard.TILE_DIMEN), 2, 2, false);
		setTetrominoType("square");
		drawSprite();
	}
	
	@Override
	public void drawSprite() {

		Tile t = new Tile(START_ROW, START_COL);
		tiles.add(t);
		s = t.getTile();
		Tile t2 = new Tile(1, 0);
		tiles.add(t2);
		s.attachChild(t2.getTile());
		Tile t3 = new Tile(1, 1);
		tiles.add(t3);
		s.attachChild(t3.getTile());
		Tile t4 = new Tile(0, 1);
		tiles.add(t4);
		s.attachChild(t4.getTile());
	}

	@Override
	public float getOrigHeight() {
		return mHeight;
	}

	@Override
	public float getOrigWidth() {
		return mWidth;
	}

	@Override
	public void rotate() {
		s.setRotationCenter(TetrisBoard.TILE_DIMEN, TetrisBoard.TILE_DIMEN);
		s.setRotation(s.getRotation()+90);
	}
	
	public void checkLeftBound() {
		s.setPosition(TetrisBoard.LEFT_X, s.getY());
	}
	
	public void checkRightBound() {
		s.setPosition(TetrisBoard.RIGHT_X-2*TetrisBoard.TILE_DIMEN, s.getY());
	}
	
	public void fillTiles(TetrisBoard board) {
		//Bottom left piece
		float x = s.getX(); 
		float y = s.getY();
		
		//Upper left piece
		Tile t1 = board.getTileAt(x, y-TetrisBoard.TILE_DIMEN);
		board.fillBooleanTileAt(x, y-TetrisBoard.TILE_DIMEN);
		t1.setIsFilled(true);
		t1.setTetromino(this);
		//Upper right piece
 		Tile t2 = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y-TetrisBoard.TILE_DIMEN);
		board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y-TetrisBoard.TILE_DIMEN);
		t2.setIsFilled(true);
		t2.setTetromino(this);
		//Bottom right piece
		Tile t3 = board.getTileAt(x+TetrisBoard.TILE_DIMEN, y);
		board.fillBooleanTileAt(x+TetrisBoard.TILE_DIMEN, y);
		t3.setIsFilled(true);
		t3.setTetromino(this);
		//Bottom left piece
		Tile t4 = board.getTileAt(x, y);
		board.fillBooleanTileAt(x, y);
		t4.setIsFilled(true);
		t4.setTetromino(this);
	}

	@Override
	public boolean isClearBelow(TetrisBoard board) {
		float x = s.getX();
		float y = s.getY();
		if (board.isBooleanTileFilled(x, y+TetrisBoard.TILE_DIMEN) || board.isBooleanTileFilled(x+TetrisBoard.TILE_DIMEN, y+TetrisBoard.TILE_DIMEN)) {
			//Log.d("SquarePiece", "Is not clear below");
			return false;
		} else {
			return true;
		}
		
	}

}
