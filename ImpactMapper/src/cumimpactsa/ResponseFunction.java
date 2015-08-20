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
public interface ResponseFunction 
{
    public String[] getParameterNames();
    public double[] getParameterValues();
    public double getResponse(double stressorIntensity);
    
}
