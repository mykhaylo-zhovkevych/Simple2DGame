package main;
import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		
		int entityTopRow = entityTopWorldY/ gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.begroundM.mapTileNumber[entityLeftCol][entityTopRow]; // with this i check the collision from different sides
			tileNum2 = gp.begroundM.mapTileNumber[entityRightCol][entityTopRow];
			if(gp.begroundM.begrounds[tileNum1].collision || gp.begroundM.begrounds[tileNum2].collision == true) {
				entity.collisionON = true;
				// if(gp.begroundM.begrounds[tileNum1].collision == true || gp.begroundM.begrounds[tileNum2].collision == true)
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.begroundM.mapTileNumber[entityLeftCol][entityBottomRow]; // with this i check the collision from different sides
			tileNum2 = gp.begroundM.mapTileNumber[entityRightCol][entityBottomRow];
			if(gp.begroundM.begrounds[tileNum1].collision || gp.begroundM.begrounds[tileNum2].collision == true) {
				entity.collisionON = true;
			
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.begroundM.mapTileNumber[entityLeftCol][entityTopRow]; // with this i check the collision from different sides
			tileNum2 = gp.begroundM.mapTileNumber[entityLeftCol][entityBottomRow]; // both left Col 
			if(gp.begroundM.begrounds[tileNum1].collision || gp.begroundM.begrounds[tileNum2].collision == true) {
				entity.collisionON = true;
			
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.begroundM.mapTileNumber[entityRightCol][entityTopRow]; // with this i check the collision from different sides
			tileNum2 = gp.begroundM.mapTileNumber[entityRightCol][entityBottomRow];
			if(gp.begroundM.begrounds[tileNum1].collision || gp.begroundM.begrounds[tileNum2].collision == true) {
				entity.collisionON = true;
			
			}
			break;
		}
		}
		
		
	}
	
	
	
	
	

