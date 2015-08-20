/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

/**
 * 
 * @author ast
 */
public class DataGrid 
{
    private double[][] values;
    private double maxValue;
    private double minValue;
    private double noDataValue;
    
    /**
     * @summary Creates a grid with data.
     * @param values The values of the grid.
     * @param max The highest value.
     * @param min The lowest value. 
     * @param noDataValue The no data value.
     */
    public DataGrid(double[][] values, double max, double min, double noDataValue)
    {
        this.values=values;
        this.maxValue=max;
        this.minValue=min;
        this.noDataValue=noDataValue;
    }
    
    public double[][] getData()
    {
        return values;
    }
    
    public double getMax()
    {
        return maxValue;
    }       
    
    public double getMin()
    {
        return minValue;
    }
    
    public double getNoDataValue()
    {
        return noDataValue;
    }
    
}
