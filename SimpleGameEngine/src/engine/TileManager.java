package engine;
import java.awt.*;
import java.awt.image.BufferedImage;

import entity.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.*;
public class TileManager implements Serializable{
///GamePanel	
	private transient GamePanel gp;
	private final int TILE_SIZE;
///World Settings
	private ArrayList<Tile>tileList;
	private int[][] map;
	public transient TileTemplate[]tileTemplates;
//======================Constructor==========================		
	public TileManager(GamePanel gp) {
		this.gp=gp;
		TILE_SIZE=gp.getTileSize();
		tileList=new ArrayList<Tile>();
	}
//===============================G&S===================	
	public int getMaxTiles() {
		return tileTemplates.length;
	}
	public BufferedImage getTileImage(int num) {
		return tileTemplates[num].image;
	}
//============================paint====================	
public void draw(Graphics2D g2d) {
	
	int playerX=0;int playerY=0;Player player=gp.getPlayer();
	int middleScreenX=gp.getMiddleScreenX();
	int middleScreenY=gp.getMiddleScreenY();
	//Case A player is loaded
	if(player!=null) {
		playerX=player.getWorldX();
		playerY=player.getWorldY();
	}
	if(tileList!=null) {
		for(int i=0;i<tileList.size();i++) {
			Tile currentTile=tileList.get(i);
			int wrldX=currentTile.worldCol*TILE_SIZE;
			int wrldY=currentTile.worldRow*TILE_SIZE;
			int screenX=wrldX-playerX+middleScreenX;
			int screenY=wrldY-playerY+middleScreenY;
			g2d.drawImage(tileTemplates[currentTile.id].getTileImage(),screenX,screenY,
			TILE_SIZE,TILE_SIZE,null);}
}
	

    }
//===========================Methods===================	
public void spawnNewTile(int id,int col,int row) {		
	tileList.add(new Tile(id,col,row));
}

//==============================Load===================	
public void loadTiles() {
	//user input number of tiles
	int userInputTiles=4;
	tileTemplates=new TileTemplate[userInputTiles];
	try {
		for(int i=0;i<userInputTiles;i++) 
			tileTemplates[i]=new TileTemplate("T_"+i);
		}catch(Exception ex) {ex.printStackTrace();}
}
}
