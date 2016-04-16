/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author andy
 */
public class CsvTableFloat
{
    private ArrayList<double[]> data = new ArrayList<double[]>(); //a list of columns, where each column is an array of doubles
    private ArrayList<String> colNames = new ArrayList<String>();

    private void addColumn(String name, int rows)
    {
        colNames.add(name);
        data.add(new double[rows]);
    }
    
     public CsvTableFloat(File file)
    {
       //read file line-by-line
            try
            {
                //open reading stream
                FileInputStream fstream = new FileInputStream(file.getAbsoluteFile());
                DataInputStream ds = new DataInputStream(fstream);
                BufferedReader in = new BufferedReader(new InputStreamReader(ds));
                
                //read header
                String line = in.readLine();
                data = new ArrayList<double[]>(); //a list of columns, where each column is a list of strings
                ArrayList<String> colNames = lineToValues(line);
                
    
                //count lines
                int lines=0;
                while ((line = in.readLine()) != null)   
                {
                    lines++;
                }

                for(int i = 0; i < colNames.size(); i++)
                {
                    addColumn(colNames.get(i), lines);
                }
                
                //run through all lines and parse
                fstream = new FileInputStream(file.getAbsoluteFile());
                ds = new DataInputStream(fstream);
                in = new BufferedReader(new InputStreamReader(ds));
                in.readLine();
                int currentLine=0;
                while ((line = in.readLine()) != null)   
                {
                    ArrayList<Double> values = lineToValuesDouble(line);
                    for(int c=0; c<values.size();c++)
                    {
                        data.get(c)[currentLine] = values.get(c);
                    }
                    currentLine++;
                }
                
                in.close();
                ds.close();
                fstream.close();
                          
                
            }
            catch(Exception e)
            {
                System.err.println("Error while reading" + file);
                System.err.println(e.getMessage().toString());
           }
    }
    
    public double[] getColumn(String colName)
    {
        for(int i=0; i<colNames.size(); i++)
        {
            if(colNames.get(i).equals(colName))
            {
                return data.get(i);
            }
        }
        return null;
    } 
     
    private ArrayList<Double> lineToValuesDouble(String line)
    {
        ArrayList<Double> list = new ArrayList<Double>();
        
        while(line.contains(","))
        {
            String value = line.substring(0, line.indexOf(","));
            list.add(Double.parseDouble(value));
            line = line.substring(line.indexOf(",")+1);
        }
        
        //last column
        list.add(Double.parseDouble(line));
        
        return list;   
    }
    
    public ArrayList<String> lineToValues(String line)
    {
        ArrayList<String> list = new ArrayList<String>();
        
        while(line.contains(","))
        {
            String value = line.substring(0, line.indexOf(","));
            list.add(value);
            line = line.substring(line.indexOf(",")+1);
        }
        
        //last column
        list.add(line);
        
        return list;
        
    }
 
    /**
     * 
     * @return List of column names.
     */
    public ArrayList<String> getColNames()
    {
        return colNames;
    }
    


   
}
