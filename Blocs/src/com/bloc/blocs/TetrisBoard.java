package com.bloc.blocs;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;

import android.util.Log;

public class TetrisBoard {
	
	private final static String TAG = "TetrisBoard";
	
	Camera mCamera;
	private TetrisBoard instance;
	
	public final static int BOARD_WIDTH = 300;
	public final static int BOARD_HEIGHT = 660;
	public final static float TILE_DIMEN = 30;
	public final static int LEFT_X = 90;
	public final static int RIGHT_X = 390;
	public final static int TOP_Y= 60;
	public final static int BOTTOM_Y = 690;
	
	public final static int NUM_COLUMNS = 10;
	public final static int NUM_ROWS = 22;
	
	private boolean[][] boardArray;
	
	final Rectangle ground;

	public ArrayList<Tile> lastRowArray;
	private Tile[][] tileArray;;
	
	public TetrisBoard getTetrisBoardInstance(Scene s) {
		if (instance == null)
			instance = new TetrisBoard(s);
		return instance;
	}
	
	public TetrisBoard(Scene s) {
		
		lastRowArray = new ArrayList<Tile>();
		
		boardArray = new boolean[NUM_COLUMNS][NUM_ROWS];
		
		tileArray = new Tile[NUM_COLUMNS][NUM_ROWS];
		
		for (int col = 0; col < NUM_COLUMNS; col++) {
			for (int row = 0; row < NUM_ROWS; row++) {
				float x = TILE_DIMEN*col + LEFT_X;
				float y = TILE_DIMEN*row + TOP_Y;
				Tile tile = new Tile(row, col, x, y, TILE_DIMEN, TILE_DIMEN, Blocs.getSharedInstance().grayTile);
				boardArray[col][row] = tile.isFilled();
				tileArray[col][row] = tile;
				if (row == 0) {
					lastRowArray.add(tile);
				}
				s.attachChild(tile.getTile());
			}
		}
		
		ground = new Rectangle(LEFT_X, BOTTOM_Y+TILE_DIMEN, BOARD_WIDTH, 2, Blocs.getSharedInstance().getVertexBufferObjectManager());
		ground.setColor(0f, 0f, 0f);
		s.attachChild(ground);
	}
	
	public Rectangle getGround() {
		return ground;
	}
	
	public ArrayList<Tile> getLastRowArray() {
		return lastRowArray;
	}
	
	public Tile getTileAt(float x, float y) {
		int colNum = Math.round((x-LEFT_X)/TILE_DIMEN);
		int rowNum = Math.round((y-TOP_Y)/TILE_DIMEN);
		if (rowNum < NUM_ROWS && colNum < NUM_COLUMNS && rowNum > 0 && colNum > 0) {
			Tile t = tileArray[colNum][rowNum];
			return t;
		} else {
			return new Tile(0, 0);
		}
	}
	
	public void checkRows() {
		for (int i = NUM_ROWS-1; i >= 0; i--) {
			boolean allFilled = true;
			for (int j = 0; j < NUM_COLUMNS; j++) {
				Tile t = tileArray[j][i];
				if (!t.isFilled()) {
					allFilled = false;
				}
			}
			//What to do when we detect row is all filled
			if (allFilled) {
				Log.d(TAG, "All filled!");
			}
		}
	}

}
