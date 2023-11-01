package main;

import object.Object_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) { // Receives in return method GamePaner gp
		this.gp = gp;
		
	}
	public void setObject() {
		gp.Object_Key[0] = new Object_Key();
		gp.Object_Key[0].worldX = 23 * gp.tileSize;
		gp.Object_Key[0].worldY = 7 * gp.tileSize;
		
		gp.Object_Key[1] = new Object_Key();
		gp.Object_Key[1].worldX = 23 * gp.tileSize;
		gp.Object_Key[1].worldY = 40 * gp.tileSize;
	}
}
