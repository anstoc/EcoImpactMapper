/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

/**
 *
 * @author ast
 */
public class LinearResponse implements ResponseFunction
{
    @Override
    public double getResponse(double stressorIntensity) 
    {
        return stressorIntensity;
    }

    @Override
    public String getName() {
        return "Linear response";
    }
    
}
