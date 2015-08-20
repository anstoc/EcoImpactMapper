/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author ast
 */
public abstract class GlobalResources {
    public static final double NODATAVALUE=-9999.0;
    public static final int DATATYPE_STRESSOR=1;
    public static final int DATATYPE_ECOCOMP=2; 
    public static final int DATATYPE_PROCESSINGCHAINS=3;
    public static final int DATATYPE_SPATIALOUTPUT=4;
    public static final int DATATYPE_FREQUENCYOUTPUT=4;
    public static String lastUsedFolder=System.getProperty("user.home");
    public static CsvTable lastOpenedTable = null;
    public static String lastOpenedTableFile = "";
    
    public static String getDateTime()
    {
      return new SimpleDateFormat("yyyyMMdd HH:mm").format(Calendar.getInstance().getTime());
    }
    
}
