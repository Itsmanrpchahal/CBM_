package com.casebeaumonde.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String changeDateTimeToDate(String time)
    {
        String time_stamp = String.valueOf(0);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date date = formatter.parse(time);

            // Format for output
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
            time_stamp = (dateFormatter.format(date));
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return time_stamp;

    }

    public static String changeDateTimeToDateTime(String time)
    {
        String time_stamp = String.valueOf(0);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date date = formatter.parse(time);

            // Format for output
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM d, yyyy hh:mm aaa");
            time_stamp = (dateFormatter.format(date));
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return time_stamp;

    }
}
