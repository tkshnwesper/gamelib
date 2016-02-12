package gamelib;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;

import com.jogamp.opengl.GL2;

public class TileMap {
	
	private Sprite tile;
	private float size;
	private static float defaultSize = 0.2f;
	private float tileHeight, tileWidth;
	
	private static void calculateDimensions(TileMap tm) {
		tm.tileHeight = tm.size;
		tm.tileWidth = tm.size * tm.tile.getAspectRatio();
	}
	
	public TileMap(Sprite sp) {
		this(sp, defaultSize);
	}
	
	public TileMap(Sprite sp, float size) {
		tile = sp;
		this.size = size;
		calculateDimensions(this);
	}
	
	public void setSize(float s) {
		size = s;
		calculateDimensions(this);
	}
	
	public void draw(GL2 gl, int rows, int cols, float x, float y) {
		float totalWidth  = cols * tileWidth;
		float totalHeight = rows * tileHeight;
		float screenWidth = 2 * tile.getAspectRatio();
		float screenHeight = 2f;
		
		int i, j;
		boolean broken;
		
		for(i=1;i<=cols;i++) {
			if(i * tileWidth >= x) {
				break;
			}
		}
		
		cols -= i - 1;
		x -= tileWidth * (i - 1);
		
		for(i=1;i<=rows;i++) {
			if(i * tileHeight >= y) {
				break;
			}
		}
		
		rows -= i - 1;
		y -= tileHeight * (i - 1);
		
		broken = false;
		for(i=1;i<=cols;i++) {
			if(i * tileWidth >= screenWidth + x) {
				broken = true;
				break;
			}
		}
		
		if(broken) {
			cols = i;
		}
		
		broken = false;
		for(i=1;i<=rows;i++) {
			if(i * tileHeight >= screenHeight + y) {
				broken = true;
				break;
			}
		}
		
		if(broken) {
			rows = i;
		}
		
		float startX = -1 - (x * tile.getAspectRatio());
		float startY = -1 - y;
		
		for(i=0;i<rows;i++) {
			for(j=0;j<cols;j++) {
				tile.draw(gl, startX + (i * tileWidth), startY + (j * tileHeight), 0, size);
			}
		}
		
	}

}
