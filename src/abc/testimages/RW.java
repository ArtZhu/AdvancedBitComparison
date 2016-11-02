import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/*
interface RW_i{
	public abstract static BufferedImage read(String imgname);
	public abstract static int[][] extractRGB(BufferedImage bi);
}
*/

public class RW{
	static boolean DEBUG = true;
	
	public static void main(String[] args){
		String imgname;
		if(args.length == 0){
			System.out.println("no args");
			return;
		}

		imgname = args[0];

		BufferedImage img = null;
		try{
			img = ImageIO.read(new File(imgname));
		}catch(Exception e){
			e.printStackTrace();
			return;
		}

		
		int Width = img.getWidth();
		int Height = img.getHeight();
		System.out.printf("Width: %d; Height: %d.\n", Width, Height);

		int[][] r = new int[Width][Height];
		int[][] g = new int[Width][Height];
		int[][] b = new int[Width][Height];
		//
		for(int w=0; w<Width; w++){
			for(int h=0; h<Height; h++){
				int rgb = img.getRGB(w, h);
				r[w][h] = (rgb >> 16) & 0x000000FF;
				g[w][h] = (rgb >>  8) & 0x000000FF;
				b[w][h] = (rgb >>  0) & 0x000000FF;
			}
		}

		//
		System.out.println("R");
			for(int[] r1: r){
				for(int r2: r1){
					System.out.printf("%4d ", r2);
				}
				System.out.print('\n');
			}
			//r.forEach(forEach(System.out::print));
		System.out.println("G");
			for(int[] g1: g){
				for(int g2: g1){
					System.out.printf("%4d ", g2);
				}
				System.out.print('\n');
			}
		System.out.println("B");
			for(int[] b1: b){
				for(int b2: b1){
					System.out.printf("%4d ", b2);
				}
				System.out.print('\n');
			}
	}

	/**
	 * @param bi comes from the method read;
	 * @param threshold above which, converted to 1, otherwise 0
	 * @param ONE_ZERO whether we do not want grey SCALE.
	 * @return the 1, 0 matrix
	 */
	public static int[][] extractRGB(BufferedImage bi, int threshold, boolean ONE_ZERO){
		int Width = bi.getWidth();
		int Height = bi.getHeight();
		int[][] r = new int[Width][Height];
		//int[][] g = new int[Width][Height];
		//int[][] b = new int[Width][Height];

		for(int w=0; w<Width; w++){
			for(int h=0; h<Height; h++){
				int rgb = bi.getRGB(w, h);
				int val = (rgb >> 16) & 0x000000FF;
				val = !ONE_ZERO? val:
						val > threshold? 1 : 0;
				r[w][h] = val;
				//g[w][h] = (rgb >>  8) & 0x000000FF;
				//b[w][h] = (rgb >>  0) & 0x000000FF;
			}
		}

		return r;
	}

	public static BufferedImage read(String imgname){
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File(imgname));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		int Width = img.getWidth();
		int Height = img.getHeight();
		System.out.printf("Extracted Width: %d; Height: %d.\n", Width, Height);

		return img;
	}

	public static void debug(Object s, Object ... o){
		if(DEBUG){
			System.out.printf(s.toString() + "\n", o);
		}
	}
	
}
