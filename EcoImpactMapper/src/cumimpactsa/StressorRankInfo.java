/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

/**
 *
 * @author ast
 */
public class StressorRankInfo 
{
    public String name;
    public double minRank=9999;
    public double maxRank=-1;
    public double inMostImportant25p=0;       
    public double inLeastImportant25p=0;   
    public double included=0;   //how often has this stressor been included in the analysis?
    public double currentContribution=0;
    public boolean active=true;
}
