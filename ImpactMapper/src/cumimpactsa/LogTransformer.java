/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

/**
 *
 * @author ast
 */
public class LogTransformer extends GeneralProcessor
{

        public DataGrid process(DataGrid grid) 
    {
        
        double[][] values = grid.getData();
        
        //make new value grid
        double[][] newValues = new double[values.length][values[0].length];
        
        for(int x=0; x<values.length;x++)
        {
            for(int y=0; y<values[0].length;y++)
            {
                if(values[x][y]==grid.getNoDataValue())
                {
                    newValues[x][y]=grid.getNoDataValue();
                }
                else
                {
                    newValues[x][y]=Math.log(values[x][y]+1);
                }
            }
        }
        
        return new DataGrid(newValues, Math.log(grid.getMax()+1),Math.log(grid.getMin()+1),grid.getNoDataValue());
 
    }

    @Override
    public String getName() {
        return "Log(X+1)-Transformation";
    }

    @Override
    public PreProcessor getNewInstance() {
        return new LogTransformer();
    }
        
}
