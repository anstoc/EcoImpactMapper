/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;

/**
 * @summary Helper class creating images from data
 * @author ast
 */
public class ImageCreator 
{
    int xSize=-1;
    int ySize=-1;
    
    Color noDataColor = new Color(30,30,30); //TODO: Make editable
    public static Color minColor = new Color(50,50,200);
    public static Color midColor = new Color(0,255,0);
    public static Color maxColor = new Color(230,10,10);
    
    public ImageCreator(int xSize, int ySize)
    {
        this.xSize=xSize;
        this.ySize=ySize;
    }
    
    public BufferedImage createStudyAreaImage(double[][] aoi, Point2DInt originalDimensions)
    {

        BufferedImage image = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
        
        double[][] imageData=transformGrid(aoi,originalDimensions);
        
        for(int x=0; x<xSize;x++)
        {
            for(int y=0;y<ySize;y++)
            {
                
                if(imageData[x][y]==0) {image.setRGB(x,y,Color.BLACK.getRGB());}
                else {image.setRGB(x, y, Color.GRAY.getRGB());}
            }
        }  
        
        return image;
    }
    
    public BufferedImage createDataImage(DataGrid data, Point2DInt originalDimensions)
    {
        
        BufferedImage image = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
        
        double[][] imageData=transformGrid(data.getData(),originalDimensions);
        
        for(int x=0; x<xSize;x++)
        {
            for(int y=0;y<ySize;y++)
            {
                
                if(imageData[x][y]==GlobalResources.NODATAVALUE) {image.setRGB(x,y,this.noDataColor.getRGB());}
                else 
                    {
                        image.setRGB(x, y, interpolateColor(imageData[x][y],data.getMin(),data.getMax()).getRGB());
                    }
            }
        }  
        
        return image;
    }
    
    
    public BufferedImage createVerticalColorScaleImage()
    {
        
        BufferedImage image = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
        
        
        for(int x=0; x<xSize;x++)
        {
            for(int y=0;y<ySize;y++)
            {
               
                        image.setRGB(x, ySize-y-1, interpolateColor(y,0,ySize-1).getRGB());
            }
        }  
        
        return image;
    }
    
    private Color interpolateColor(double value, double min, double max)
    {
        
        Color result=null;
        
        double middle=(max-min)/2;
        if(value<=middle)
        {
            double proportion=(value-min)/(middle-min);
            
            int red = (int) (proportion * midColor.getRed() + (1-proportion) * minColor.getRed());
            int green = (int) (proportion * midColor.getGreen() + (1-proportion) * minColor.getGreen());
            int blue = (int) (proportion * midColor.getBlue() + (1-proportion) * minColor.getBlue()); 
            
            result = new Color(red, green, blue);
        }
        else
        {
            double proportion=(max-value)/(max-middle);
            
            int red = (int) (proportion * midColor.getRed() + (1-proportion) * maxColor.getRed());
            int green = (int) (proportion * midColor.getGreen() + (1-proportion) * maxColor.getGreen());
            int blue = (int) (proportion * midColor.getBlue() + (1-proportion) * maxColor.getBlue()); 
            

            
            result = new Color(red, green, blue);
           
           
        }
       
        return result;
    }
    
    private double[][] transformGrid(double[][] original, Point2DInt originalDimensions)
    {
        double[][] imageGrid=new double[xSize][ySize];
        
        double xMultiplier = ((double)originalDimensions.x)/((double) xSize);
        double yMultiplier = ((double)originalDimensions.y)/((double) ySize);
        
        for(int x=0; x<xSize; x++)
        {
            for(int y=0; y<ySize;y++)
            {
                int dataX=(int) Math.floor(xMultiplier*x);
                int dataY= (int) Math.floor(yMultiplier*y);
                imageGrid[x][y]=original[dataX][dataY];
            }
        }
        
        
        return imageGrid;
    }
     
    
    
}
