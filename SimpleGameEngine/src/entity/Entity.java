package entity;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.*;

import engine.GamePanel;

import java.util.Random;
public class Entity implements Serializable{
	GamePanel gp;
    BufferedImage UP1,UP2,DOWN1,DOWN2,LEFT1,LEFT2,RIGHT1,RIGHT2,currentImage;
    int timer=-1;
    int sprite=1;
	int worldX;
	int worldY;
	int speed=3;
	private Random random;
	private int directionConst;
	private static ArrayList<EntityTemplate> entityType;
	
//======================Constructor==========================	
	public Entity() {
		
	}
	public Entity(int num,int col,int row,GamePanel gp) {
		this.gp=gp;
		worldX=col*gp.getTileSize();
		worldY=row*gp.getTileSize();
		random=new Random();
		loadEntityImage(num);
	}
//===============================G&S===================	
	public int getWorldX() {
		return worldX;
	}
	public int getWorldY() {
		return worldY;
	}
	public BufferedImage getEntityImage() {
		return DOWN1;
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
		int screenX=worldX-playerX+middleScreenX;
		int screenY=worldY-playerY+middleScreenY;
		g2d.drawImage(currentImage,screenX,screenY,null);
		
	}
//======================Methods=========================	
	public void move() {
		if(timer==130||timer==-1) {
			sprite=1;
			directionConst=random.nextInt(201);
			timer=0;
		}
		else if(timer==65) {
			sprite=2;
		}
		if(directionConst<50) {
			if(sprite==1)
				currentImage=UP1;
			else
				currentImage=UP2;
			worldY-=speed;
		}
		else if(directionConst<100) {
			if(sprite==1)
				currentImage=DOWN1;
			else
				currentImage=DOWN2;
			worldY+=speed;
		}
		else if(directionConst<150) {
			if(sprite==1)
				currentImage=LEFT1;
			else
				currentImage=LEFT2;
			worldX-=speed;
		}
		else{
			if(sprite==1)
				currentImage=RIGHT1;
			else
				currentImage=RIGHT2;
			worldX+=speed;
		}
			
	}
//==============================Load===================
	public static void loadEntities(int num) {
		entityType=new ArrayList<EntityTemplate>();
		for(int i=0;i<num;i++) {
			entityType.add(new EntityTemplate("E_"+i));
		}
		
	}
	public void loadEntityImage(int num) {
		EntityTemplate template=entityType.get(num);
		this.UP1=template.UP1;
		this.UP2=template.UP2;
		this.DOWN1=template.DOWN1;
		this.DOWN2=template.DOWN2;
		this.LEFT1=template.LEFT1;
		this.LEFT2=template.LEFT2;
		this.RIGHT1=template.RIGHT1;
		this.RIGHT2=template.RIGHT2;
	}
	
	
	

}
