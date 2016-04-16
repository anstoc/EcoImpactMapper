/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ast;
 */
public class AreaRefiner extends GeneralProcessor 
{
    public AreaRefiner()
    {
        paramNames=new String[1];
        paramValues=new double[1];
        paramNames[0]="seed";
        paramValues[0]=0; //if seed is 0, not used; otherwise use the seed for deterministic result
    }
    
    private class AreaInfo
    {
        public ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        double originalValue = GlobalResources.NODATAVALUE;
        int cellNr=0;
        double valueSum=0;
        double randomSum=0;
        int areaCode=-1;
        ArrayList<Double> randomValues = new ArrayList<Double>();
    }
    
    //returns the AreaInfo object with the given area code or null if no such area exists
    private AreaInfo getAreaInfo(ArrayList<AreaInfo> list, int areaCode)
    {
        for(int i=0; i<list.size();i++)
        {
            if(list.get(i).areaCode == areaCode) {return list.get(i);}
        }
        
        return null;
        
    }
    
    @Override
    public DataGrid process(DataGrid grid) 
    {
        Random rand=null;
        if(paramValues[0]>0) 
        {
            rand=new Random((long) paramValues[0]);
        }
        
        //get areas
        int[][] areaCodes = grid.calculatePresenceAreas();
        double[][] originalData = grid.getData();
        
        //create a list of AreaInfo objects with x, y, area code, etc.
        ArrayList<AreaInfo> areas = new ArrayList<AreaInfo>();
        
        for(int x=0; x<originalData.length;x++)
        {
            for(int y=0; y<originalData[0].length;y++)
            {
                if(originalData[x][y]!=grid.getNoDataValue())
                {
                    //check if area info exists for the current code, create if not
                    AreaInfo info = getAreaInfo(areas, areaCodes[x][y]);
                    if(info == null) 
                    {
                        info = new AreaInfo();
                        info.areaCode = areaCodes[x][y];
                        info.originalValue = originalData[x][y];
                        areas.add(info);
                    }
                    
                    //fill in area info
                    double r;
                    if(paramValues[0]==0)  {r = Math.random();}
                    else {r=rand.nextDouble();}
                    
                    info.cellNr++;                   
                    info.randomSum+=r;
                    info.randomValues.add(r);
                    info.valueSum+=originalData[x][y];
                    info.x.add(x);
                    info.y.add(y);
                }          
            }
        }
        
        //now go through all area infos, and re-set data to scaled random values
        double max=-1;
        double[][] newData = GlobalResources.mappingProject.grid.getEmptyGrid();
        for(int i=0; i<areas.size();i++)
        {
            AreaInfo info = areas.get(i);
            for(int l=0;l<info.x.size();l++)
            {
                double newValue = (double) (info.randomValues.get(l)*info.valueSum/info.randomSum);
                newData[info.x.get(l)][info.y.get(l)]=newValue;
                if(newValue>max) {max=newValue;}
            }
        }
        
        //finally, use low-pass filter (5x5)
        DataGrid unfilteredGrid = new DataGrid(newData,max,0,grid.getNoDataValue());
    
        //TODO 6 for Micheli et al (26km), 2 for Korpinen et al (25km)
        return Helpers.lowPassFilter(unfilteredGrid, 6);
        
    }

    @Override
    public String getName() 
    {
        return "Redistribute effort in areas";
    }

    @Override
    public PreProcessor clone() 
    {
        AreaRefiner clone=new AreaRefiner();
        clone.setParamValue(paramNames[0],paramValues[0]);
        return clone;
    }
    
}
