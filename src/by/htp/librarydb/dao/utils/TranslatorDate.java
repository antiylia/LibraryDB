package by.htp.librarydb.dao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TranslatorDate {

	public static Date transformIntoDate (String date) {
		
	SimpleDateFormat simFormat = new SimpleDateFormat ("yyyy-MM-dd");
	Date dateUtil = null;
	try {
		dateUtil = simFormat.parse(date);
	} catch (ParseException e) {
		e.printStackTrace();
	}

	return dateUtil;
  }
	
	
	public static String transformDateIntoString (Date datestring) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sf.format(datestring);
		return dateStr;
	}
	
	
	
	
}
