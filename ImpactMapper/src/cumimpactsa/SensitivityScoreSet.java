/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ast
 */
public class SensitivityScoreSet 
{
    private ArrayList<ImpactInfo> impacts = new ArrayList<ImpactInfo>();
    private String sourceFileName="";
    
    public void createFromFile(String filename)
    {
        sourceFileName=filename;
        CsvTable table = new CsvTable();
        table.readFromFile(new File(filename));
        
        
        //go through ecocomps(columns)
        for(int e=1; e<table.getColNames().size();e++)
        {
            String ecocompName = table.getColNames().get(e);
            //go through stressors (rows)
            for(int s=0; s<table.getColumn(ecocompName).size(); s++)
            {
                String stressorName = table.getColumn(table.getColNames().get(0)).get(s); //stressor name in first column
                double score = Double.parseDouble(table.getColumn(ecocompName).get(s));   //read out from table
                
                ImpactInfo impactInfo = new ImpactInfo(stressorName,ecocompName,score);
                
                //check if it's ok
                if(impactInfo.getEcocomp()==null)
                {
                    impacts = new ArrayList<ImpactInfo>();
                    JOptionPane.showMessageDialog(null, "Loading failed. Could not find ecosystem component: "+ecocompName);
                    return;
                }
                else if(impactInfo.getStressor()==null)
                {
                    impacts = new ArrayList<ImpactInfo>();
                    JOptionPane.showMessageDialog(null, "Loading failed. Could not find stressor: "+stressorName);
                    return;
                }
                else //everything ok
                {
                    impacts.add(impactInfo);
                }

            }
        }

    }
    
    public String getSourceFileName()
    {
        return sourceFileName;
    }
    
    public void addSensitivityScore(ImpactInfo info)
    {
        impacts.add(info);
    }
    
    public void addSensitivityScore(String stressorName, String ecocompName, double score)
    {
        impacts.add(new ImpactInfo(stressorName, ecocompName, score));
    }
    
    public ArrayList<ImpactInfo> getAllScores()
    {
        return impacts;
    }
    
    public int size()
    {
        return impacts.size();
    }
    
    public ImpactInfo getInfo(int index)
    {
        if(index<impacts.size())
        {
            return impacts.get(index);
        }
        else {return null;}
    }
 
    public double getStressorAverageScore(String stressorName)
    {
        double sum=0;
        int count=0;
        for(int i=0; i<this.impacts.size();i++)
        {
            if(impacts.get(i).getStressor().getName().equals(stressorName))
            {
                sum+=impacts.get(i).getSensitivityScore();
                count++;
            }
        }
        
        return sum/count;
        
    }

    public CsvTable getContributionsAsTable()
    {
        CsvTable table = new CsvTable();
        table.addColumn("stressor");
        table.addColumn("ecocomp");
        table.addColumn("contribution");
        
        for(int i=0; i<this.impacts.size();i++)
        {
            ArrayList<String> row = new ArrayList<String>();
            row.add(impacts.get(i).getStressor().getName());
            row.add(impacts.get(i).getEcocomp().getName());
            row.add(Double.toString(impacts.get(i).getContribution()));
            table.addRow(row);
        }
        
        return table;
        
    }
            
}
