package entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class EntityTemplate implements Serializable{
     BufferedImage UP1,UP2,DOWN1,DOWN2,LEFT1,LEFT2,RIGHT1,RIGHT2;
     public EntityTemplate(String path) {
    	 loadEntityImage(path);
     }
  //==============================Load===================	
  	public void loadEntityImage(String path) {
  		try {
  			UP1 =ImageIO.read(getClass().getResourceAsStream("/res/entity/"+path+"/UP1.png"));
  			UP2 =ImageIO.read(getClass().getResourceAsStream("/res/entity/"+path+"/UP2.png"));
  			DOWN1 =ImageIO.read(getClass().getResourceAsStream("/res/entity/"+path+"/DOWN1.png"));
  			DOWN2 =ImageIO.read(getClass().getResourceAsStream("/res/entity/"+path+"/DOWN2.png"));
  			LEFT1 =ImageIO.read(getClass().getResourceAsStream("/res/entity/"+path+"/LEFT1.png"));
  			LEFT2 =ImageIO.read(getClass().getResourceAsStream("/res/entity/"+path+"/LEFT2.png"));
  			RIGHT1 =ImageIO.read(getClass().getResourceAsStream("/res/entity/"+path+"/RIGHT1.png"));
  			RIGHT2 =ImageIO.read(getClass().getResourceAsStream("/res/entity/"+path+"/RIGHT2.png"));
  		}catch(Exception ex) {ex.printStackTrace();}
  	}
}
