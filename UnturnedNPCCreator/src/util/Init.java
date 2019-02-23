package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import panels.FaceSelector;
import windows.Window;

public class Init 
{
	public static BufferedImage icon;
	
	public Init()
	{
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader(new File("E:\\Eclipse\\UnturnedNPCMaker\\src\\util\\Settings.txt")));
//		} catch (FileNotFoundException e) {
//			
//			e.printStackTrace();
//		}
		try {
			icon = ImageIO.read(Init.class.getResource("/Icons/UIIcons/BTWIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
