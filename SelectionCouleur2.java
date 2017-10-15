import ij.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.color.*;

public class SelectionCouleur2 implements PlugInFilter {
	int couleurVersNG(int couleur) {
		int r = new Color(couleur).getRed();
		int g = new Color(couleur).getGreen();
		int b = new Color(couleur).getBlue();
		
		int ng = (int)(0.3*r+0.59*g+0.11*b);
		r=ng;
		g=ng;
		b=ng;
		int ngcolor = new Color(r,g,b).getRGB();
		return ngcolor;
	}
	double distance(int couleur1, int couleur2) {
		int red1 = new Color(couleur1).getRed();
		int green1 = new Color(couleur1).getGreen();
		int blue1 = new Color(couleur1).getBlue();
		float[] hsb1 = new float[3];
		Color.RGBtoHSB(red1,green1,blue1,hsb1);

		
		int red2 = new Color(couleur2).getRed();
		int green2 = new Color(couleur2).getGreen();
		int blue2 = new Color(couleur2).getBlue();
		float[] hsb2 = new float[3];
		Color.RGBtoHSB(red2,green2,blue2,hsb2);
		
		
		double distance = Math.abs((hsb2[0]-hsb1[0]));

		return distance;
	}
	public void run(ImageProcessor ip) {
		
		IJ.log("Comparaison de couleurs basée sur la teinte");
		
		int width = ip.getWidth();
		int height = ip.getHeight();
		
		ColorChooser cc = new ColorChooser("couleur de référence",
				Color.yellow, true);
		Color cRef = cc.getColor();
		if (cRef == null) {
		IJ.error( "PlugIn cancelled" );
		return;
		}
		int couleurRef = (cRef.getRed()<< 16) + (cRef.getGreen() << 8)+
				cRef.getBlue();
		int red = new Color(couleurRef).getRed();
		int green = new Color(couleurRef).getGreen();
		int blue= new Color(couleurRef).getBlue();
		float[] hsb = new float[3];
		Color.RGBtoHSB(red,green,blue,hsb);

		
		
		double distanceMax = 0.17;// seuil à choisir
		int count = 0;
		for (int j = 0;j < height; j++)
		{
			for(int i = 0; i < width; i++)
			{
				int value = ip.getPixel(i,j);
				double distance = distance(value,couleurRef);
				if (distance > distanceMax)
				{
					count++;
					int new_value = couleurVersNG(value);
					ip.putPixel(i,j,new_value);
				}
			}
		}
		IJ.log(Integer.toString(count)+"pixels affectes");
				
	}
	public int setup(String arg, ImagePlus imp)
	{
		return DOES_RGB;
	}
}