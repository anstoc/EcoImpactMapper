/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

/**
 *
 * @author ast
 */
public class CellRankInfo 
{
    public int x;
    public int y;
    public double minPerc=(double)1.01;
    public double maxPerc=-1;
    public int inHighest25p=0;       
    public int inLowest25p=0;  
    public int inHighest10p=0;
    public int inLowest10p=0;
    public double currentImpact=0;  
    public int[] inQuintile = new int[5]; //how often is the cell in the first quintile, etc; used for majoprity vote of an impact class
    public double quantileSum=0; //sum of quantiles in all simulation runs.
}
