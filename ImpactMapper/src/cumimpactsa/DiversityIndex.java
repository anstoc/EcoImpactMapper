/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author ast
 */
public class DiversityIndex extends SpatialDataLayer
{
    public DiversityIndex(String saveFileName)
    {
        super(GlobalResources.getDateTime() + " Diversity index",null,GlobalResources.DATATYPE_SPATIALOUTPUT,null);
        source = new DataSourceInfo();
        source.sourceFile=saveFileName;
        source.xField="x";
        source.yField="y";
        source.valueField="value";
        
        this.type=GlobalResources.DATATYPE_SPATIALOUTPUT;
        
        //now create internal data grid
        double[][] data = new double[MappingProject.grid.getDimensions().x][MappingProject.grid.getDimensions().y];
        
        //load all processed ecological component grids
        ArrayList<double[][]> ecocompDataList = new ArrayList<double[][]>();
        for(int l=0;l<MappingProject.ecocomps.size();l++)
        {
            ecocompDataList.add(MappingProject.ecocomps.get(l).getprocessedGrid().getData());
        }
        
        //sum them up
        double max=0;
        double min=1;
        for(int l=0; l<ecocompDataList.size();l++)
        {
            //System.out.println("Calculatin diversity index, layer: " + l);
            MappingProject.processingProgressPercent = (int) (100*l/ecocompDataList.size());
            double[][] ecocompData=ecocompDataList.get(l);
            for(int y=0;y<data[0].length;y++)
            {
                for(int x=0;x<data.length;x++)
                {
                    if(ecocompData[x][y]==GlobalResources.NODATAVALUE || data[x][y]==GlobalResources.NODATAVALUE)
                    {
                        data[x][y]=GlobalResources.NODATAVALUE;
                    }
                    else
                    {
                        data[x][y]=data[x][y]+ecocompData[x][y];
                        if(data[x][y]>max) {max=data[x][y];}
                        if(data[x][y]<min) {min=data[x][y];}
                    }
                }
            }
        }
        
       grid = new DataGrid(data,max,min,GlobalResources.NODATAVALUE);
       
    }
}
