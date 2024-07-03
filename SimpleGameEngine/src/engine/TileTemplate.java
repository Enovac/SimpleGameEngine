package engine;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TileTemplate{
	BufferedImage image;
	 
	public TileTemplate(String path) {
		try {
			image=(ImageIO.read(getClass().getResourceAsStream("/res/Tile/"+path+".png")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to load tile: "+path);
		}
	}
	public BufferedImage getTileImage() {
		return image;
	}
}

	


