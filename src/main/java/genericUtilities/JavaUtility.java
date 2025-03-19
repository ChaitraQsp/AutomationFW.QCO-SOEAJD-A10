package genericUtilities;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class consists of generic methods related to Java
 * @author Chaitra M
 *
 */
public class JavaUtility {
	
	/**
	 * This method will capture the current system date and return to caller
	 * Used to name screenshots and reports
	 * @return
	 */
	public String getSystemDateInformat()
	{
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
		String date = f.format(d);
		return date;
	}

}
