package engine;

import java.io.Serializable;

public class Tile implements Serializable{
	 int worldCol;
	 int worldRow;
	 int id;
	public Tile(int id,int worldCol,int worldRow) {
		this.id=id;
		this.worldCol=worldCol;
		this.worldRow=worldRow;
	}

}
