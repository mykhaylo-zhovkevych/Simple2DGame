package Backgrounds;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Backgrounds[] begrounds;
	public int mapTileNumber[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		begrounds = new Backgrounds[10]; // das bdeutet das hier wir 10 mal solche erschtellen
		mapTileNumber = new int [gp.maxWorldCol][gp.maxWorldRow];
		
		getbackgroundImage();
		loadMap("/maps/world01.txt");
	} 
	
	
	public void getbackgroundImage() {
		
		try {
			begrounds[0] = new Backgrounds();
			begrounds[0].image = ImageIO.read(getClass().getResource("/background/grass.png"));
			
			begrounds[1] = new Backgrounds();
			begrounds[1].image = ImageIO.read(getClass().getResource("/background/wall.png"));
			begrounds[1].collision = true;
			
			begrounds[2] = new Backgrounds();
			begrounds[2].image = ImageIO.read(getClass().getResource("/background/water.png"));
			begrounds[2].collision = true;
			
			begrounds[3] = new Backgrounds();
			begrounds[3].image = ImageIO.read(getClass().getResource("/background/earth.png"));
			
			begrounds[4] = new Backgrounds();
			begrounds[4].image = ImageIO.read(getClass().getResource("/background/tree.png"));
			begrounds[4].collision = true;
			
			
			begrounds[5] = new Backgrounds();
			begrounds[5].image = ImageIO.read(getClass().getResource("/background/sand.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	public void loadMap(String filePath) {
	    try {
	        InputStream is = getClass().getResourceAsStream(filePath);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        int col = 0;
	        int row = 0;
	        String line;

	        while ((line = br.readLine()) != null) {
	            String[] numbers = line.split(" ");

	            if (numbers.length != gp.maxWorldCol) {
	                System.out.println("Warning: Skipping a line with an invalid number of elements.");
	                continue; // Skip this line and move to the next one.
	            }

	            for (col = 0; col < gp.maxWorldCol; col++) {
	                int num = Integer.parseInt(numbers[col]);
	                mapTileNumber[col][row] = num;
	            }
	            row++;
	        }

	        br.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/*public void loadMap(String filePath) {
	    try {
	        InputStream is = getClass().getResourceAsStream(filePath);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        int col = 0;
	        int row = 0;
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] numbers = line.split(" ");
	            if (numbers.length != gp.maxScreenCol) {
	                throw new IllegalArgumentException("Invalid number of elements in the line.");
	            }

	            for (col = 0; col < gp.maxScreenCol; col++) {
	                int num = Integer.parseInt(numbers[col]);
	                mapTileNumber[col][row] = num;
	            }
	            row++;
	        }

	        br.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
*/
	public void draw(Graphics2D g2) {
	    int worldCol = 0;
	    int worldRow = 0;
	  

	    while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
	    {
	    	
	    	int tileNum = mapTileNumber[worldCol][worldRow];
	    	
	    	int worldX = worldCol * gp.tileSize;
	    	int worldY = worldRow * gp.tileSize;
	    	int screenX = worldX - gp.player.worldX + gp.player.screenX;
	    	int screenY = worldY - gp.player.worldY + gp.player.screenY;
	    	      if  (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
	    			&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
	    			&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
	    			&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {g2.drawImage(begrounds[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);}
        	 worldCol++;

	        if (worldCol == gp.maxWorldCol) {
	        	worldCol = 0;
	        	worldRow++;
	        	
	      
	            // Don't forget to increment the row when you move to the next row.
	        }
	    }
	}
}