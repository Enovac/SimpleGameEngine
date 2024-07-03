package entity;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

import engine.*;
public class Player extends Entity implements Serializable{
	 GamePanel gp;////////////////////////
	transient KeyHandler kh;
//======================Constructor==========================		
	public Player(GamePanel gp,KeyHandler kh) {
		this.gp=gp;
		this.kh=kh;
		this.worldX=0;
		this.worldY=0;
		loadPlayerImage();
	}
//============================paint====================	
	@Override
	public void draw(Graphics2D g2d){
		g2d.drawImage(currentImage,gp.getMiddleScreenX(),gp.getMiddleScreenY(),null);
	}
//============================Methods===================	
	public void move() {
		if(kh.upPressed||kh.downPressed||kh.leftPressed||kh.rightPressed) {
			if(timer==12) {
				timer=0;
				if(sprite==1)
					sprite=2;
				else
					sprite=1;
			}
			if(kh.upPressed) {
				if(sprite==1)
					currentImage=UP1;
				else
					currentImage=UP2;
				worldY-=speed;
			}
			else if(kh.downPressed) {
				if(sprite==1)
					currentImage=DOWN1;
				else
					currentImage=DOWN2;
				worldY+=speed;
			}
			else if(kh.leftPressed) {
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
			timer++;
		}
	}

//==============================Load===================	
	public void loadPlayerImage() {
		try {
			UP1 =ImageIO.read(getClass().getResourceAsStream("/res/player/UP1.png"));
			UP2 =ImageIO.read(getClass().getResourceAsStream("/res/player/UP2.png"));
			DOWN1 =ImageIO.read(getClass().getResourceAsStream("/res/player/DOWN1.png"));
			DOWN2 =ImageIO.read(getClass().getResourceAsStream("/res/player/DOWN2.png"));
			LEFT1 =ImageIO.read(getClass().getResourceAsStream("/res/player/LEFT1.png"));
			LEFT2 =ImageIO.read(getClass().getResourceAsStream("/res/player/LEFT2.png"));
			RIGHT1 =ImageIO.read(getClass().getResourceAsStream("/res/player/RIGHT1.png"));
			RIGHT2 =ImageIO.read(getClass().getResourceAsStream("/res/player/RIGHT2.png"));
			currentImage=DOWN1;
		}catch(Exception ex) {ex.printStackTrace();}
	}
	
	
	
	
	
}
