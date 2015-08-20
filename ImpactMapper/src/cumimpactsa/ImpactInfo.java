/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

/**
 *
 * @author ast
 */
public class ImpactInfo 
{
    private SpatialDataLayer stressor;
    private SpatialDataLayer ecocomp;
    private double sensitivityScore;
    private double contribution=0;
            
    public ImpactInfo(String stressorName, String ecocompName, double sensitivityScore)
    {
        this.stressor=MappingProject.getStressorByName(stressorName);
        this.ecocomp=MappingProject.getEcocompByName(ecocompName);
        this.sensitivityScore=sensitivityScore;
      
    }
    
    public SpatialDataLayer getStressor()
    {
        return stressor;
    }
    
    public SpatialDataLayer getEcocomp()
    {
        return ecocomp;
    }
    
    public double getSensitivityScore()
    {
        return sensitivityScore;
    }
    
    public double getContribution()
    {
        return contribution;
    }

    void setContribution(double value) 
    {
        this.contribution=value;
    }
    
    void addToContribution(double value) 
    {
        this.contribution+=value;
    }
    
    
}
