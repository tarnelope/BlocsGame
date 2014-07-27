package com.bloc.blocs;

import static org.andengine.extension.physics.box2d.util.constants.PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.hardware.SensorManager;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class TetrisPiece {
	
	private static final FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
	private static final float TILE_DIMEN = TetrisBoard.TILE_DIMEN;
	
	PhysicsWorld mPhysicsWorld;
	public boolean moveable = true;
	Sprite piece;
	Body body;
	
	ArrayList<Sprite> buildTiles;
	Sprite cyan1;
	Sprite gray1;
	
	/**
	 * 1 = log
	 * 2 = square
	 * 3 = z
	 * 4 = s
	 * 5 = L
	 * 6 = rev L
	 * 7 = T
	 */
	int shapeType; 

	public TetrisPiece() {
		mPhysicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);
		
		buildTiles = new ArrayList<Sprite>();
		for (int i = 0; i < 4; i++) {
			Sprite bTile = new Sprite(0, 0, Blocs.getSharedInstance().cyanTile, Blocs.getSharedInstance().getVertexBufferObjectManager());
			buildTiles.add(bTile);
		}
		
		shapeType = randInt(1, 7);
		
		switch (shapeType) {
			case 1: //Log
				piece = logSprite();
				//piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().logPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				//body = createLogBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
				break;
			case 2: //Square
				piece = squareSprite();
/*				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().zPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createZBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
*/				break;
			case 3: //Z
				piece = zSprite();
/*				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().sPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createSBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
*/				break;
			case 4: //S
				piece = sSprite();
/*				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().tPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createTBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
*/				break;
			case 5: //L
				piece = lSprite();
/*				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().sqPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createSquareBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
*/				break;
			case 6: //Reverse L
				piece = revLSprite();
/*				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().lPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createLBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
*/				break;
			case 7: //T
				piece = tSprite();
/*				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().revLPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createRevLBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
*/				break;
			default:
				break;
		}
		
		piece.setPosition(Blocs.CAMERA_WIDTH/2, 120); 
		
		TimerHandler timerHandler = new TimerHandler(1, true, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				movePieceDown();
			}
		});
		//piece.registerUpdateHandler(timerHandler);
	}
	
	public void setMoveable(boolean b) {
		moveable = b;
	}
	
	public float getBottomY() {
		Float f = 0.0f;
		f = piece.getY()+piece.getHeight();
		return f;
	}
	
	public void movePieceDown() {
		if (moveable && piece.getY()+piece.getHeight() < TetrisBoard.BOTTOM_Y - TetrisBoard.TILE_DIMEN) {
			float newY = piece.getY() + TetrisBoard.TILE_DIMEN;
			piece.setPosition(piece.getX(), newY);
		}
		if (piece.getX() <= TetrisBoard.LEFT_X) {
			piece.setPosition(TetrisBoard.LEFT_X, piece.getY());
		}
		if (piece.getX() >= TetrisBoard.RIGHT_X) {
			piece.setPosition(TetrisBoard.RIGHT_X, piece.getY());
		}
	}
	
	public void movePieceHorizontally(float delta) {
		float oldX = piece.getX();
		if (delta > 0) {
			piece.setPosition(oldX+TILE_DIMEN, piece.getY());
		} else if (delta < 0) {
			piece.setPosition(oldX-TILE_DIMEN, piece.getY());
		}
		
	}
	
	public void rotatePieceCCW() {
		Log.d("TetrisPiece", "ROTATED!");
		piece.setRotation(piece.getRotation()+90);
	}
	
	public Sprite getPiece() {
		return piece;
	}
	
	private Sprite logSprite() {
		Sprite s = buildTiles.get(0);
		s.detachChildren();
		if (buildTiles.size() == 4) {
			Log.d("TetrisPiece", "LogSprite size is "+buildTiles.size());
			for (int i=1; i < 4; i++) {
				Sprite s2 = buildTiles.get(i);
				s2.setPosition(s.getX(), s.getY()+s.getHeight()*(i));
				s.attachChild(s2);
			}
		}
		return s;
	}
	private Sprite squareSprite() {
		Sprite s = buildTiles.get(0);
		s.detachChildren();
		Vector2[] v = {
				new Vector2(0,0),
				new Vector2(1,0),
				new Vector2(1,1),
				new Vector2(0,1)
		};
		if (buildTiles.size() == 4) {
			Log.d("TetrisPiece", "LogSprite size is "+buildTiles.size());
			for (int i=1; i < 4; i++) {
				Sprite s2 = buildTiles.get(i);
				
				float newX = s.getX()+(v[i].x * s.getWidth());
				float newY = s.getY()+(s.getHeight()*v[i].y);
				s2.setPosition(newX, newY);
				s.attachChild(s2);
			}
		}
		return s;
	}
	private Sprite zSprite() {
		Sprite s = buildTiles.get(0);
		s.detachChildren();
		Vector2[] v = {
				new Vector2(0,0),
				new Vector2(1,0),
				new Vector2(1,1),
				new Vector2(2,1)
		};
		if (buildTiles.size() == 4) {
			Log.d("TetrisPiece", "LogSprite size is "+buildTiles.size());
			for (int i=1; i < 4; i++) {
				Sprite s2 = buildTiles.get(i);
				float newX = s.getX()+(v[i].x * s.getWidth());
				float newY = s.getY()+(s.getHeight()*v[i].y);
				s2.setPosition(newX, newY);
				s.attachChild(s2);
			}
		}
		return s;
	}
	private Sprite sSprite() {
		Sprite s = buildTiles.get(0);
		s.detachChildren();
		Vector2[] v = {
				new Vector2(0,0),
				new Vector2(0,1),
				new Vector2(1,0),
				new Vector2(-1,1)
		};
		if (buildTiles.size() == 4) {
			for (int i=1; i < 4; i++) {
				Sprite s2 = buildTiles.get(i);
				float newX = s.getX()+(v[i].x * s.getWidth());
				float newY = s.getY()+(s.getHeight()*v[i].y);
				s2.setPosition(newX, newY);
				s.attachChild(s2);
			}
		}
		return s;
	}
	private Sprite lSprite() {
		Sprite s = buildTiles.get(0);
		s.detachChildren();
		Vector2[] v = {
				new Vector2(0,0),
				new Vector2(0,1),
				new Vector2(0,2),
				new Vector2(1,2)
		};
		if (buildTiles.size() == 4) {
			for (int i=1; i < 4; i++) {
				Sprite s2 = buildTiles.get(i);
				float newX = s.getX()+(v[i].x * s.getWidth());
				float newY = s.getY()+(s.getHeight()*v[i].y);
				s2.setPosition(newX, newY);
				s.attachChild(s2);
			}
		}
		return s;
	}
	private Sprite revLSprite() {
		Sprite s = buildTiles.get(0);
		s.detachChildren();
		Vector2[] v = {
				new Vector2(0,0),
				new Vector2(0,1),
				new Vector2(0,2),
				new Vector2(-1,2)
		};
		if (buildTiles.size() == 4) {
			for (int i=1; i < 4; i++) {
				Sprite s2 = buildTiles.get(i);
				float newX = s.getX()+(v[i].x * s.getWidth());
				float newY = s.getY()+(s.getHeight()*v[i].y);
				s2.setPosition(newX, newY);
				s.attachChild(s2);
			}
		}
		return s;
	}
	private Sprite tSprite() {
		Sprite s = buildTiles.get(0);
		s.detachChildren();
		Vector2[] v = {
				new Vector2(0,0),
				new Vector2(-1,0),
				new Vector2(1,0),
				new Vector2(0,1)
		};
		if (buildTiles.size() == 4) {
			for (int i=1; i < 4; i++) {
				Sprite s2 = buildTiles.get(i);
				float newX = s.getX()+(v[i].x * s.getWidth());
				float newY = s.getY()+(s.getHeight()*v[i].y);
				s2.setPosition(newX, newY);
				s.attachChild(s2);
			}
		}
		return s;
	}
	
	private static Body createLogBody(final PhysicsWorld pPhysicsWorld, final IAreaShape pShape, final BodyType pBodyType, final FixtureDef pFixtureDef) {
        /* Remember that the vertices are relative to the center-coordinates of the Shape. */
       
		final float centerX = 0;
		final float halfWidth = pShape.getWidthScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT;
		final float halfHeight = pShape.getHeightScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT;

        final Vector2[] vertices = {
        		new Vector2(-halfWidth, halfHeight),
        		new Vector2(halfWidth, halfHeight),
        		new Vector2(halfWidth, -halfHeight),
        		new Vector2(-halfWidth, -halfHeight)
        };

        return PhysicsFactory.createPolygonBody(pPhysicsWorld, pShape, vertices, pBodyType, pFixtureDef);
	}
	
	private static Body createSquareBody(final PhysicsWorld pPhysicsWorld, final IAreaShape pShape, final BodyType pBodyType, final FixtureDef pFixtureDef) {
        /* Remember that the vertices are relative to the center-coordinates of the Shape. */
		
		final float centerX = 0;
		final float halfWidth = pShape.getWidthScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT;
		final float halfHeight = pShape.getHeightScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT;

        final Vector2[] vertices = {
        		new Vector2(-halfWidth, halfHeight),
        		new Vector2(halfWidth, halfHeight),
        		new Vector2(halfWidth, -halfHeight),
        		new Vector2(-halfWidth, -halfHeight)
        };

        return PhysicsFactory.createPolygonBody(pPhysicsWorld, pShape, vertices, pBodyType, pFixtureDef);
	}
	
	private static Body createZBody(final PhysicsWorld pPhysicsWorld, final IAreaShape pShape, final BodyType pBodyType, final FixtureDef pFixtureDef) {
        /* Remember that the vertices are relative to the center-coordinates of the Shape. */
       
		final float centerX = 0;
		final float halfWidth = pShape.getWidthScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT;
		final float halfHeight = pShape.getHeightScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT;
       
        final Vector2[] vertices = {
        		new Vector2(-halfWidth, halfHeight),
        		new Vector2(halfWidth/3, halfHeight),
        		new Vector2(halfWidth/3, 0),
        		new Vector2(halfWidth, 0),
        		new Vector2(halfWidth, -halfHeight),
        		new Vector2(-halfWidth/3, -halfHeight),
        		new Vector2(-halfWidth/3, 0),
        		new Vector2(-halfWidth, 0),
        };

        return PhysicsFactory.createPolygonBody(pPhysicsWorld, pShape, vertices, pBodyType, pFixtureDef);
	}
	
	private static Body createSBody(final PhysicsWorld pPhysicsWorld, final IAreaShape pShape, final BodyType pBodyType, final FixtureDef pFixtureDef) {
        /* Remember that the vertices are relative to the center-coordinates of the Shape. */
       
		final float centerX = 0;
		final float halfWidth = -(pShape.getWidthScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT);
		final float halfHeight = -(pShape.getHeightScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT);
       
        final Vector2[] vertices = {
        		new Vector2(-halfWidth, halfHeight),
        		new Vector2(halfWidth/3, halfHeight),
        		new Vector2(halfWidth/3, 0),
        		new Vector2(halfWidth, 0),
        		new Vector2(halfWidth, -halfHeight),
        		new Vector2(-halfWidth/3, -halfHeight),
        		new Vector2(-halfWidth/3, 0),
        		new Vector2(-halfWidth, 0),
        };

        return PhysicsFactory.createPolygonBody(pPhysicsWorld, pShape, vertices, pBodyType, pFixtureDef);
	}
	
	private static Body createTBody(final PhysicsWorld pPhysicsWorld, final IAreaShape pShape, final BodyType pBodyType, final FixtureDef pFixtureDef) {
        /* Remember that the vertices are relative to the center-coordinates of the Shape. */
       
		final float centerX = 0;
		final float halfWidth = pShape.getWidthScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT;
		final float halfHeight = pShape.getHeightScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT;
       
        final Vector2[] vertices = {
        		new Vector2(-halfWidth, halfHeight),
        		new Vector2(halfWidth, halfHeight),
        		new Vector2(halfWidth, 0),
        		new Vector2(halfWidth/2, 0),
        		new Vector2(halfWidth/2, -halfHeight),
        		new Vector2(-halfWidth/2, -halfHeight),
        		new Vector2(-halfWidth/2, 0),
        		new Vector2(-halfWidth, 0),
        };

        return PhysicsFactory.createPolygonBody(pPhysicsWorld, pShape, vertices, pBodyType, pFixtureDef);
	}
	
	private static Body createLBody(final PhysicsWorld pPhysicsWorld, final IAreaShape pShape, final BodyType pBodyType, final FixtureDef pFixtureDef) {
        /* Remember that the vertices are relative to the center-coordinates of the Shape. */
       
		final float centerX = 0;
		final float halfWidth = pShape.getWidthScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT;
		final float halfHeight = pShape.getHeightScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT;
       
        final Vector2[] vertices = {
        		new Vector2(-halfWidth, halfHeight),
        		new Vector2(0, halfHeight),
        		new Vector2(0, -halfHeight/3),
        		new Vector2(halfWidth, -halfHeight/3),
        		new Vector2(halfWidth, -halfHeight),
        		new Vector2(-halfWidth, -halfHeight),
        };

        return PhysicsFactory.createPolygonBody(pPhysicsWorld, pShape, vertices, pBodyType, pFixtureDef);
	}
	
	private static Body createRevLBody(final PhysicsWorld pPhysicsWorld, final IAreaShape pShape, final BodyType pBodyType, final FixtureDef pFixtureDef) {
        /* Remember that the vertices are relative to the center-coordinates of the Shape. */
       
		final float centerX = 0;
		final float halfWidth = -(pShape.getWidthScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT);
		final float halfHeight = -(pShape.getHeightScaled() * 0.5f / PIXEL_TO_METER_RATIO_DEFAULT);
       
        final Vector2[] vertices = {
        		new Vector2(-halfWidth, halfHeight),
        		new Vector2(0, halfHeight),
        		new Vector2(0, -halfHeight/3),
        		new Vector2(halfWidth, -halfHeight/3),
        		new Vector2(halfWidth, -halfHeight),
        		new Vector2(-halfWidth, -halfHeight),
        };

        return PhysicsFactory.createPolygonBody(pPhysicsWorld, pShape, vertices, pBodyType, pFixtureDef);
	}
	
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	

}
