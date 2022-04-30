package utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil extends AssertUtil {

	public List<String> sMonthFullName = Arrays.asList("January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December");

	/**
	 * 
	 * Reusable date method to get today's date
	 * 
	 * @return
	 */
	public static String getTodaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		return todaysDate;

	}

	/**
	 * 
	 * Reusable method to get date with addition of n No of days
	 * 
	 * @param sDate
	 * @param sOldFormat
	 * @param iNoOfDays
	 * @return
	 */
	public static String additionOfDate(String sDate, String sOldFormat, int iNoOfDays) {
		// Given Date in String format
		System.out.println("Date before Addition: " + sDate);
		// Specifying date format that matches the given date
		SimpleDateFormat sdf = new SimpleDateFormat(sOldFormat);
		Calendar c = Calendar.getInstance();
		try {
			// Setting the date to the given date
			c.setTime(sdf.parse(sDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, iNoOfDays);
		// Date after adding the days to the given date
		String newDate = sdf.format(c.getTime());
		// Displaying the new Date after addition of Days
		System.out.println("Date after Addition: " + newDate);
		return newDate;

	}

}
