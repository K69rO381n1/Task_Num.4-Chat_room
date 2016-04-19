package Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeStamp {

    private static final String DATE_FORMAT = "HH:mm:ss";

    public static String getCurrentTime() {
        return new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime());
    }
}
