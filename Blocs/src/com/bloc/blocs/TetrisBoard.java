package com.bloc.blocs;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

public class TetrisBoard {
	
	Camera mCamera;
	private TetrisBoard instance;
	
	public final static int BOARD_WIDTH = 300;
	public final static int BOARD_HEIGHT = 660;
	public final static float TILE_DIMEN = 30;
	public final static int LEFT_X = 90;
	public final static int RIGHT_X = 390;
	public final static int TOP_Y= 60;
	public final static int BOTTOM_Y = 720;
	
	public final static int NUM_COLUMNS = 10;
	public final static int NUM_ROWS = 22;
	
	private boolean[][] boardArray;
	
	public TetrisBoard getTetrisBoardInstance(Scene s) {
		if (instance == null)
			instance = new TetrisBoard(s);
		return instance;
		
	}
	
	public TetrisBoard(Scene s) {
		//mCamera = Blocs.getSharedInstance().mCamera;
		
		boardArray = new boolean[NUM_COLUMNS][NUM_ROWS];
		
		for (int col = 0; col < NUM_COLUMNS; col++) {
			for (int row = 0; row < NUM_ROWS; row++) {
				float x = TILE_DIMEN*col + LEFT_X;
				float y = BOTTOM_Y - TILE_DIMEN*row;
				//float y = TILE_DIMEN*row + TOP_Y;
				TetrisTile tile = new TetrisTile(row, col, x, y, TILE_DIMEN, TILE_DIMEN, Blocs.getSharedInstance().getVertexBufferObjectManager());
				boardArray[row][col] = tile.isFilled;
				s.attachChild(tile);
			}
		}
	}
	
	public class TetrisTile extends Rectangle {
		
		boolean isFilled = false;
		int row;
		int col;
		
		public TetrisTile(int r, int c, float pX, float pY, float pWidth, float pHeight,
				VertexBufferObjectManager vertexBufferObjectManager) {
			super(pX, pY, pWidth, pHeight, vertexBufferObjectManager);
			row = r;
			col = c;
			setColor(new Color(0.1f, 0.2f, 0.6f));
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
		
	}

}
