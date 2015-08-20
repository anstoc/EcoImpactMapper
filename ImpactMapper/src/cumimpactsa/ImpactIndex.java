/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.util.ArrayList;

/**
 *
 * @author ast
 */
public class ImpactIndex extends SpatialDataLayer
{
    
     private SensitivityScoreSet scores;
    
     public ImpactIndex(String saveFileName, SensitivityScoreSet sensitivityScores, boolean avg)  //avg: if true, divides by nr of ecocomps in each cell
     {
         
        super("Impact index",null,GlobalResources.DATATYPE_SPATIALOUTPUT,null);
        this.scores=sensitivityScores;
        source = new DataSourceInfo();
        source.sourceFile=saveFileName;
        source.xField="x";
        source.yField="y";
        source.valueField="value";
        this.name=GlobalResources.getDateTime() + " Impact Index";
        if(avg) {this.name = this.name + " (mean)";}
        else {this.name = this.name + " (sum)";}
        
        this.type = GlobalResources.DATATYPE_SPATIALOUTPUT;
        
        //needed to calculate average and impact contributions
        DiversityIndex divIndex = new DiversityIndex("not_saved");
        double[][] ecocompSum = divIndex.getGrid().getData();
        
                
         //set all ecocomp-stressor contributions to 0
        for(int i=0; i<scores.size();i++)
        {
            scores.getInfo(i).setContribution(0);
        }
        
        //now create internal data grid
        double[][] data = new double[MappingProject.grid.getDimensions().x][MappingProject.grid.getDimensions().y];
        //double[][] ecocompSum = new double[MappingProject.grid.getDimensions().x][MappingProject.grid.getDimensions().y];;
        
        double max=0;
        double min=999;
        //go through all impacts, get the stressor and ecocomp data and add to index
        
        for(int i=0; i<sensitivityScores.size();i++)
        {
            MappingProject.processingProgressPercent = (int) (100*i/sensitivityScores.size());
            
            ImpactInfo impact = sensitivityScores.getInfo(i);
            
            double score = impact.getSensitivityScore();
            double[][] ecocompData=impact.getEcocomp().getprocessedGrid().getData();
            double[][] stressorData=impact.getStressor().getprocessedGrid().getData();
            double z=0;
            for(int x=0;x<ecocompData.length;x++)
            {
                for(int y=0; y<ecocompData[0].length; y++)
                {
                    if(stressorData[x][y]==GlobalResources.NODATAVALUE || ecocompData[x][y]==GlobalResources.NODATAVALUE || data[x][y]==GlobalResources.NODATAVALUE)
                    {
                        data[x][y]=GlobalResources.NODATAVALUE;
                    }
                    else
                    {
                        double summand=score*ecocompData[x][y]*stressorData[x][y];
                        if(avg)
                        {
                            summand=summand/ecocompSum[x][y];
                        }
                       
                        data[x][y]=data[x][y]+summand;    
                        impact.addToContribution(summand);
                        if(data[x][y]>max && data[x][y]!=GlobalResources.NODATAVALUE) {max=data[x][y];}
                        else if(data[x][y]<min && data[x][y]!=GlobalResources.NODATAVALUE) {min=data[x][y];}

                    }
                }
            }
        }
        
        //calculate average from the sum if necessary
        /*if(avg)
        {
            
            max=-9999;
            min=9999;
            for(int x=0;x<data.length;x++)
            {
                for(int y=0; y<data[0].length; y++)
                {
                    if(data[x][y]!=GlobalResources.NODATAVALUE && ecocompSum[x][y]!=0 && ecocompSum[x][y]!=GlobalResources.NODATAVALUE)
                    {
                        data[x][y] = data[x][y]/ecocompSum[x][y];
                        if(data[x][y]>max && data[x][y]!=GlobalResources.NODATAVALUE) {max=data[x][y];}
                        else if(data[x][y]<min && data[x][y]!=GlobalResources.NODATAVALUE) {min=data[x][y];}
                    }
                    else
                    {
                        data[x][y]=GlobalResources.NODATAVALUE;
                    }
                }
            }    
        }*/
         
        
       grid = new DataGrid(data,max,min,GlobalResources.NODATAVALUE);
       
    }
     
     
    public SensitivityScoreSet getScores()
    {
        return scores;
    }
     
}
