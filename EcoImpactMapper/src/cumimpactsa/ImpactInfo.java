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
    private boolean active=true;     //used to switch stressors on and off   
    private ResponseFunction responseFunction=new LinearResponse();
    public double sortField = 0;
    
    public ImpactInfo(String stressorName, String ecocompName, double sensitivityScore)
    {
        this.stressor=GlobalResources.mappingProject.getStressorByName(stressorName);
        this.ecocomp=GlobalResources.mappingProject.getEcocompByName(ecocompName);
        this.sensitivityScore=sensitivityScore;
      
    }
    
    public void setStressor(SpatialDataLayer stressor)
    {
        this.stressor=stressor;
    }
    
    public void setEcocomp(SpatialDataLayer ecocomp)
    {
        this.ecocomp=ecocomp;
    }
    
    public void setResponseFunction(ResponseFunction r)
    {
        responseFunction=r;
    }
    
    public ResponseFunction getResponseFunction()
    {
        return responseFunction;
    }
    
    public ImpactInfo(SpatialDataLayer stressor, SpatialDataLayer ecocomp, double sensitivityScore)
    {
        this.stressor=stressor;
        this.ecocomp=ecocomp;
        this.sensitivityScore=sensitivityScore;
      
    }
    
    public void changeSensitivtyScore(double newValue)
    {
        sensitivityScore=newValue;
    }
    
    public SpatialDataLayer getStressor()
    {
        return stressor;
    }
    
    public boolean isActive()
    {
        return active;
    }
    
    public void setActive(boolean active)
    {
        this.active=active;
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
