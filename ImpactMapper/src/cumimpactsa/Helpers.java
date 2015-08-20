/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.util.ArrayList;

/**
 *@summary This class provides a collection of helper functions.
 * @author ast
 */
public abstract class Helpers 
{
   public final static String OK = "ok"; 
   private static String error = OK;
    
    public static double[] stringListToDoubleArray(ArrayList<String> stringList)
    {
        double[] numList = new double[stringList.size()];
        error = OK;
        
        try
        {
            for(int i = 0; i < stringList.size(); i++)
            {
       
                numList[i] = Double.parseDouble(stringList.get(i));
            }
        }
        
        catch(Exception e)
        {
            error = "Error while parsing numbers from text. //// " + e.getMessage() + " //// " + e.getStackTrace();
        }
        
        
        return numList;
    }
    
    public static String[] stringListToStringArray(ArrayList<String> stringList)
    {
        String[] strings = new String[stringList.size()];
        error = OK;
        
        try
        {
            for(int i = 0; i < stringList.size(); i++)
            {
                strings[i] = stringList.get(i);
            }
        }
        
        catch(Exception e)
        {
            error = "Error while extracting strings from list. //// " + e.getMessage() + " //// " + e.getStackTrace();
        }
        
        
        return strings;
    }
    
    /**
     * @summary Returns last function call's error message. If no error, returns Helpers.OK.
     * @return Error string.
     */
    public static String getError()
    {
        return error;
    }
    
}
