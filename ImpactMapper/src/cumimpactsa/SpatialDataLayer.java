/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * 
 * @author ast
 */
public class SpatialDataLayer implements DrawableData
{
    protected String name;
    protected DataSourceInfo source;
    protected DataGrid grid;
    protected DataGrid processedGrid;
    protected int type;
    
    
    private ArrayList<PreProcessor> processingChain=new ArrayList<PreProcessor>();
    
    private boolean processingChainLoaded=false;
    
    public SpatialDataLayer(String name, DataGrid grid, int type, DataSourceInfo source)
    {
        this.name=name;
        this.grid=grid;
        this.type=type;
        this.source=source;
        
        //set processing chain to default: log(x+1)-transformation and then rescaling for stressors, nothing for ecocomps
        if(type==GlobalResources.DATATYPE_ECOCOMP) {processingChain.add(new IdentityProcessor());}
        else if(type==GlobalResources.DATATYPE_STRESSOR) 
        {
            processingChain.add(new LogTransformer());
            processingChain.add(new Rescaler());
        }  
    }
    
    protected boolean isProcessingChainLoaded()
    {
        return processingChainLoaded;
    }
    
    protected boolean hasProcessedGrid()
    {
        return (processedGrid!=null);
    }
    
    protected void setProcessingChainLoaded()
    {
        processingChainLoaded=true;
    }
    
    public String getName()
    {
        return name;
    }
    
    
    public DataGrid getGrid()
    {
        return grid;
    }
    
    public DataSourceInfo getSource()
    {
        return source;
    }
    
    public int getType()
    {
        return type;
    }
    
    public ArrayList<PreProcessor> getProcessingChain()
    {
        return processingChain;
    }
    
    public void setProcessingChain(ArrayList<PreProcessor> chain)
    {
        this.processingChain=chain;
        processedGrid=null;
    }
    

    
    public DataGrid getprocessedGrid()
    {
        //processed grid exists already
        if(processedGrid!=null)
        {
            return processedGrid;
        }
        //processing chain is empty
        else if(processingChain==null || processingChain.size()<1)
        {
            return grid;
        }
        //processed grid needs to be calculated now
        else 
        {
            processedGrid=processingChain.get(0).process(grid);
            for(int i=1;i<processingChain.size();i++)
            {
                processedGrid=processingChain.get(i).process(processedGrid);
            }
            return processedGrid;
        }        
    }

    @Override
    public BufferedImage getImage(ImageCreator creator, boolean variation) {
        BufferedImage image;
        if(!variation)
        {
              image = creator.createDataImage(getGrid(), MappingProject.grid.getDimensions());
                   
         }
         else
         {
             image = creator.createDataImage(getprocessedGrid(), MappingProject.grid.getDimensions());
         }
        
        return image;
               
    }
}
