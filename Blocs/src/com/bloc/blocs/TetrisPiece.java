package com.bloc.blocs;

import static org.andengine.extension.physics.box2d.util.constants.PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

import java.util.Random;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
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

public class TetrisPiece extends Sprite {
	
	private static final FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
	private static final float TILE_DIMEN = TetrisBoard.TILE_DIMEN;
	
	PhysicsWorld mPhysicsWorld;
	public boolean moveable = true;
	Sprite piece;
	Body body;
	
	public TetrisPiece(float pX, float pY,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);

	}

	public TetrisPiece() {
		this(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().logPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
		mPhysicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);
		
		//piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().logPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
		
		//body = createLogBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
		
		/*int index = randInt(1, 7);
		
		switch (index) {
			case 1: //Log
				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().logPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createLogBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
				break;
			case 2: //Z
				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().zPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createZBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
				break;
			case 3: //S
				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().sPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createSBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
				break;
			case 4: //T
				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().tPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createTBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
				break;
			case 5: //Square
				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().sqPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createSquareBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
				break;
			case 6: //L
				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().lPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createLBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
				break;
			case 7: //Reverse L
				piece = new Sprite(Blocs.CAMERA_WIDTH/2, 60, Blocs.getSharedInstance().revLPiece, Blocs.getSharedInstance().getVertexBufferObjectManager());
				body = createRevLBody(mPhysicsWorld, piece, BodyType.StaticBody, FIXTURE_DEF);
				break;
			default:
				break;
		}*/
		
		TimerHandler timerHandler = new TimerHandler(1, true, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				Log.d("TetrisPIece", "move piece down");
				movePieceDown();
			}
		});
		registerUpdateHandler(timerHandler);
		//piece.registerUpdateHandler(timerHandler);
	}
	
	public void setMoveable(boolean b) {
		moveable = b;
	}
	
	public void movePieceDown() {
		if (moveable && getY() < TetrisBoard.BOTTOM_Y - TetrisBoard.TILE_DIMEN) {
			float newY = getY() + TetrisBoard.TILE_DIMEN;
			setPosition(getX(), newY);
		}
	}
	
	public Sprite getPiece() {
		return piece;
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
	
	public class Test extends Sprite {

		public Test(float pX, float pY, float pWidth, float pHeight,
				ITextureRegion pTextureRegion,
				VertexBufferObjectManager pVertexBufferObjectManager) {
			super(pX, pY, pWidth, pHeight, pTextureRegion, pVertexBufferObjectManager);
			// TODO Auto-generated constructor stub
		}
		
	}
	

}