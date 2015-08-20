/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.awt.Color;
import java.util.List;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 * @summary This class represents a human impact mapping project.
 * @author ast
 */
public abstract class MappingProject 
{
    public static  MappingGrid grid=null;
   
    public static ArrayList<SpatialDataLayer> stressors = new ArrayList<SpatialDataLayer>();
    public static ArrayList<SpatialDataLayer> ecocomps = new ArrayList<SpatialDataLayer>();
    public static ArrayList<PreProcessor> processors = new ArrayList<PreProcessor>();
    public static SensitivityScoreSet sensitivityScores = new SensitivityScoreSet();
    
    private static String projectPath;
    
    private static SpatialDataLayer lastDataAdded; 
    
    public static ArrayList<DrawableData> results=new ArrayList<DrawableData>();    //processing results, e.g. impact indices
    
    public static String projectFolder="";
    
    public static boolean processing = false;
    public static int processingProgressPercent=-1;
    
    
    public static void reset()
    {
        grid=null;
        stressors = new ArrayList<SpatialDataLayer>();
        ecocomps = new ArrayList<SpatialDataLayer>();
        lastDataAdded=null;
        sensitivityScores=new SensitivityScoreSet();
        results=new ArrayList<DrawableData>();
        
    }
    
    
    public static void initializeProcessors()
    {
        processors = new ArrayList<PreProcessor>();
        processors.add(new IdentityProcessor());
        processors.add(new LogTransformer());
        processors.add(new Rescaler());        
    }
    
    public static String[] getProcessorNames()
    {
        String[] names = new String[processors.size()];
        for(int i=0; i<processors.size();i++) {names[i]=processors.get(i).getName();}
        return names;
    }
    
    public static void addData(String name, DataGrid grid, int type, DataSourceInfo source)
    {
        //remove commas from name 
        name=name.replace(',',';');
        
        SpatialDataLayer layer = new SpatialDataLayer(name, grid, type, source);
        if(type==GlobalResources.DATATYPE_ECOCOMP)
            {ecocomps.add(layer);}
        else if(type==GlobalResources.DATATYPE_STRESSOR)
            {stressors.add(layer);}
        else if(type==GlobalResources.DATATYPE_SPATIALOUTPUT)
            {results.add(layer);}
        
        lastDataAdded=layer;
        
    }
    
    public static String[] getStressorNames()
    {
        String[] result = new String[stressors.size()];
        for(int i=0; i<stressors.size();i++)
        {
            result[i]=stressors.get(i).getName();
        }
        return result;
    }
    
    public static String[] getResultNames()
    {
        String[] result = new String[results.size()];
        for(int i=0; i<results.size();i++)
        {
            result[i]=results.get(i).getName();
        }
        return result;
    }
    
    public static SpatialDataLayer getLastDataAdded()
    {
        return lastDataAdded;
    }
    
    public static SpatialDataLayer getStressorByName(String name)
    {
        
        if(name==null) return null;
        
        String[] stressorNames=getStressorNames();
        for(int i=0; i<stressorNames.length;i++)
        {
            if(stressorNames[i].trim().equalsIgnoreCase(name.trim()))
                {return stressors.get(i);}
        }
        return null;
    }
    
    public static void removeStressorByName(String name)
    {
        String[] stressorNames=getStressorNames();
        for(int i=0; i<stressorNames.length;i++)
        {
            if(stressorNames[i].equals(name))
            {
                stressors.remove(i);
            }   
        }
    }
    
    public static String[] getEcocompNames()
    {
        String[] result = new String[ecocomps.size()];
        for(int i=0; i<ecocomps.size();i++)
        {
            result[i]=ecocomps.get(i).getName();
        }
        return result;
    }
    

    
    public static SpatialDataLayer getEcocompByName(String name)
    {
        if(name==null) return null;
        
        String[] names=getEcocompNames();
        for(int i=0; i<names.length;i++)
        {
            if(names[i].trim().equalsIgnoreCase(name.trim()))
                {return ecocomps.get(i);}
        }
        return null;
    }
    
    public static SpatialDataLayer getDataLayerByName(String name)
    {
        SpatialDataLayer layer=getStressorByName(name);
        if(layer==null) {layer=getEcocompByName(name);}
        return layer;
    }
    
    public static void removeEcocompByName(String name)
    {
        String[] names=getEcocompNames();
        for(int i=0; i<names.length;i++)
        {
            if(names[i].equals(name))
            {
                ecocomps.remove(i);
            }   
        }
    }
    
    public static PreProcessor getNewProcessorByName(String name)
    {
        for(int i=0; i<processors.size();i++)
        {
            if(processors.get(i).getName().equals(name))
            {
                return processors.get(i).getNewInstance();
            }
        }    
            
        return null;
    }
    
    public static void save(String filename)
    {
        projectFolder = Paths.get(filename).getParent().toString();
        
        CsvTable table=new CsvTable();
        table.addColumn("ResType");
        table.addColumn("ResName");
        table.addColumn("File");
        table.addColumn("XField");
        table.addColumn("YField");
        table.addColumn("ValueField");
        //write stressor sources
        for(int i=0; i<stressors.size();i++)
        {
           ArrayList<String> row = new ArrayList<String>();
           row.add("stressor");
           row.add(stressors.get(i).getName());
           row.add(getRelativePath(stressors.get(i).getSource().sourceFile));
           row.add(stressors.get(i).getSource().xField);
           row.add(stressors.get(i).getSource().yField);
           row.add(stressors.get(i).getSource().valueField);
           table.addRow(row);
        }
        
        //write ecocomp sources
         for(int i=0; i<ecocomps.size();i++)
        {
           ArrayList<String> row = new ArrayList<String>();
           row.add("ecocomp");
           row.add(ecocomps.get(i).getName());
           row.add(getRelativePath(ecocomps.get(i).getSource().sourceFile));
           row.add(ecocomps.get(i).getSource().xField);
           row.add(ecocomps.get(i).getSource().yField);
           row.add(ecocomps.get(i).getSource().valueField);
           table.addRow(row);
        }
         
         //write results
         for(int i=0; i<results.size();i++)
        {
           ArrayList<String> row = new ArrayList<String>();
           row.add("result");
           row.add(results.get(i).getName());
           row.add(getRelativePath(results.get(i).getSource().sourceFile));
           row.add(results.get(i).getSource().xField);
           row.add(results.get(i).getSource().yField);
           row.add(results.get(i).getSource().valueField);
           table.addRow(row);
        }
         
        //write sensitivity score source, if set
        if(sensitivityScores!=null && sensitivityScores.getAllScores().size()>0 && sensitivityScores.getSourceFileName()!=null && sensitivityScores.getSourceFileName().length()>0) 
        {
            ArrayList<String> scoreRow = new ArrayList<String>();
            scoreRow.add("sensitivityscorefile");
            scoreRow.add("n/a");
            scoreRow.add(getRelativePath(sensitivityScores.getSourceFileName()));
            scoreRow.add("n/a");scoreRow.add("n/a");scoreRow.add("n/a");
            table.addRow(scoreRow);
        }
        
         //add path to sourcefile for processing chain
        String basepath;
        int pos=filename.lastIndexOf(".");
        if(pos<1) {basepath=filename;}
        else {basepath = filename.substring(0,pos);}
        String procFileName=basepath+"_pchains.csv";
        ArrayList<String> row = new ArrayList<String>();
        row.add("processingchains");
        row.add("n/a");
        row.add(getRelativePath(procFileName));
        row.add("n/a");row.add("n/a");row.add("n/a");
        table.addRow(row);
        //create processing chain file
        saveProcessingChains(procFileName);
        
        //save colors
        ArrayList<String> maxRow = new ArrayList<String>();
        ArrayList<String> midRow = new ArrayList<String>();
        ArrayList<String> minRow = new ArrayList<String>();
        maxRow.add("color"); midRow.add("color"); minRow.add("color");
        maxRow.add("maxcolor"); midRow.add("midcolor"); minRow.add("mincolor");
        maxRow.add("n/a"); midRow.add("n/a"); minRow.add("n/a");
        maxRow.add("n/a"); midRow.add("n/a"); minRow.add("n/a");
        maxRow.add("n/a"); midRow.add("n/a"); minRow.add("n/a");
        maxRow.add(ImageCreator.maxColor.getRGB()+""); midRow.add(ImageCreator.midColor.getRGB()+""); minRow.add(ImageCreator.minColor.getRGB()+"");
        table.addRow(maxRow);table.addRow(midRow);table.addRow(minRow);
        
        
        
        table.writeToFile(filename);
        
    }
    
    //Turns an absolute path into a path relative to the project folder.
    private static String getRelativePath(String absPath)
    {
        Path pathAbs = Paths.get(absPath);
        Path pathBase = Paths.get(projectFolder);
        return pathBase.relativize(pathAbs).toString();
    }
    
    private static void saveProcessingChains(String filename)
    {
        CsvTable table = new CsvTable();
        table.addColumn("DataLayer");
        table.addColumn("Processor");
        
        //go through stressors
        for(int i=0; i<stressors.size();i++)
        {
            ArrayList<PreProcessor> procChain = stressors.get(i).getProcessingChain();
            if(procChain.size()==0)
            {
                procChain.add(new IdentityProcessor());
            }
            for(int j=0; j<procChain.size();j++)
            {
                ArrayList<String> row = new ArrayList<String>();
                row.add(stressors.get(i).getName());
                row.add(procChain.get(j).getName());
                table.addRow(row);
            }     
        }

        for(int i=0; i<ecocomps.size();i++)
        {
            ArrayList<PreProcessor> procChain = ecocomps.get(i).getProcessingChain();
            if(procChain.size()==0)
            {
                procChain.add(new IdentityProcessor());
            }
            for(int j=0; j<procChain.size();j++)
            {
                ArrayList<String> row = new ArrayList<String>();
                row.add(ecocomps.get(i).getName());
                row.add(procChain.get(j).getName());
                table.addRow(row);
            }     
        }
        
        table.writeToFile(filename);
    }
    
    public static void createFromTable(CsvTable table)
    {
        //load all info
        MappingProject.reset();
        processing=true;
        final ArrayList<String> resType = table.getColumn("ResType");
        final ArrayList<String> resName = table.getColumn("ResName");
        final ArrayList<String> file = table.getColumn("File");
        final ArrayList<String> xField = table.getColumn("XField");
        final ArrayList<String> yField = table.getColumn("YField");
        final ArrayList<String> valueField = table.getColumn("ValueField");
        //final StatusWindow statusWindow = new StatusWindow();
        final CsvTable procTable=new CsvTable();
        //statusWindow.setVisible(true);
        
        //do loading in worker thread
        SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() 
        {
             @Override
             protected Boolean doInBackground() throws Exception 
             {
                
                 //go through line-by-line and load data
                for(int row=0; row<resType.size(); row++)
                { 
                    //publish(row);
                    //System.out.println("Row: "+row);
                    processingProgressPercent=(int) (100*row/resType.size());
                    //Thread.sleep(100);
                    //load stressors and ecosystem components
                    if(resType.get(row).equals("stressor") || resType.get(row).equals("ecocomp") || resType.get(row).equals("result"))
                    {
                        int dataType=-1;
                        if(resType.get(row).equals("stressor")) {dataType=GlobalResources.DATATYPE_STRESSOR;}
                        else if(resType.get(row).equals("ecocomp")) {dataType=GlobalResources.DATATYPE_ECOCOMP;}
                        else if(resType.get(row).equals("result")) {dataType=GlobalResources.DATATYPE_SPATIALOUTPUT;}
                
                        //read input file for this row, if not last loaded and still open
                        CsvTable inputData;
                        if(GlobalResources.lastOpenedTableFile.equals(getAbsolutePath(file.get(row))))
                        {
                            inputData = GlobalResources.lastOpenedTable;
                        }
                        else
                        {
                            inputData = new CsvTable();
                            inputData.readFromFile(new File(getAbsolutePath(file.get(row))));
                            GlobalResources.lastOpenedTable=inputData;
                            GlobalResources.lastOpenedTableFile=getAbsolutePath(file.get(row));
                        }

                        double[] x = Helpers.stringListToDoubleArray(inputData.getColumn(xField.get(row)));
                        double[] y = Helpers.stringListToDoubleArray(inputData.getColumn(yField.get(row)));
                        double[] values = Helpers.stringListToDoubleArray(inputData.getColumn(valueField.get(row)));
                
                        //make mapping grid if necessary
                        if(grid==null)
                        {
                            MappingGrid grid = new MappingGrid(x,y);
                            MappingProject.grid=grid;
                        }
                    
                        DataGrid grid = MappingProject.grid.createDataGrid(x, y, values);
                        DataSourceInfo info = new DataSourceInfo();
                        info.sourceFile=getAbsolutePath(file.get(row));
                        info.valueField=valueField.get(row);
                        info.xField=xField.get(row);
                        info.yField=yField.get(row);
                        MappingProject.addData(resName.get(row), grid, dataType, info);
                
                    }
                   else if(resType.get(row).equals("processingchains"))
                   {
                        //load table
                       String procFileName=getAbsolutePath(file.get(row));
                       //procTable=new CsvTable();
                       procTable.readFromFile(new File(procFileName));
                       //processing of this table will occur when all other data are loaded to make sure no data layers are missing
                   }
                   else if(resType.get(row).equals("color"))
                   {
                       if(resName.get(row).equals("maxcolor")) 
                       {
                           int col=Integer.parseInt(valueField.get(row));
                           ImageCreator.maxColor=new Color(col);
                       }
                       else if(resName.get(row).equals("midcolor")) 
                       {
                           int col=Integer.parseInt(valueField.get(row));
                           ImageCreator.midColor=new Color(col);
                       }
                       else if(resName.get(row).equals("mincolor")) 
                       {
                           int col=Integer.parseInt(valueField.get(row));
                           ImageCreator.minColor=new Color(col);
                       }
                   }
                   else if(resType.get(row).equals("sensitivityscorefile"))
                   {
                       sensitivityScores=new SensitivityScoreSet();
                       sensitivityScores.createFromFile(getAbsolutePath(file.get(row)));
                   }

                    
                }

        
                //project file contained reference to a processing chain table
                if(procTable.getColNames().size()>0)
                {
                    ArrayList<String> layers=procTable.getColumn("DataLayer");
                    ArrayList<String> processors=procTable.getColumn("Processor");
            
                    for(int i=0; i<layers.size();i++)
                    {
                        SpatialDataLayer layer = MappingProject.getDataLayerByName(layers.get(i));
                        if(!layer.isProcessingChainLoaded())
                        {
                            layer.getProcessingChain().clear();  //remove default list
                            layer.setProcessingChainLoaded();
                        }
                        PreProcessor processor = MappingProject.getNewProcessorByName(processors.get(i));
                        layer.getProcessingChain().add(processor);
                    }
                }
                 
                processing=false;
                return true;
                
            }
             
	};

	worker.execute();

        
    }

    //Creates an absolute path from the project folderand a path relative to it
    private static String getAbsolutePath(String relPath)
    {
        Path pathRel= Paths.get(relPath);
        Path pathBase = Paths.get(projectFolder);
        return pathBase.resolve(pathRel).toString();
    }
            
    public static void removeResultByName(String resultName) {
        String[] names=getResultNames();
        for(int i=0; i<names.length;i++)
        {
            if(names[i].equals(resultName))
            {
                results.remove(i);
            }   
        }
    }

    static DrawableData getResultByName(String resultName) 
    {
        
        if(resultName==null) return null;
        
        String[] names=getResultNames();
        for(int i=0; i<names.length;i++)
        {
            if(names[i].trim().equalsIgnoreCase(resultName.trim()))
                {return results.get(i);}
        }
        return null;
    }
    
}
