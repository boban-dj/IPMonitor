/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.extras;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import model.configuration.*;
import model.ipmonitor.*;
import model.logger.*;

public class CommonFunctions {

    private static CommonFunctions instance = new CommonFunctions();
    private static final String SYSTEM_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private CommonFunctions() {
    }

    public static CommonFunctions getInstance() {
        return instance;
    }

    public String getFormattedDateTime(Date date) {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT,
                DateFormat.MEDIUM).format(date);
    }

    public String getSystemFormattedDateTime(Date date) {
        try {
            return new SimpleDateFormat(SYSTEM_DATE_TIME_FORMAT).format(date);
        } catch (Exception e) {
            // If date is null
            return "";
        }
    }

    public Date getSystemDateTime(String dateAsString) throws ParseException {
        return new SimpleDateFormat(SYSTEM_DATE_TIME_FORMAT).parse(dateAsString);
    }

    public void postLoadProperties(IPMonitor ipMonitor) {
        new File(model.configuration.ConfigurationManager.getInstance().getLogFilesDirectory()).mkdirs();
        MainLogger.getInstance().deleteOldFiles();
        ipMonitor.addIPMonitorListener(new IPMonitorEventLogger());
    }

    public boolean isValidIP(String ip) {
        return Pattern.matches(ConfigurationManager.getInstance().getIPPattern(), ip);
    }
}