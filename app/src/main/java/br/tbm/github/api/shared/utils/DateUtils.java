package br.tbm.github.api.shared.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class DateUtils {

    public static String formatDate(String created) {
        String formattedDate = "";
        try {
            long timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(created).getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            formattedDate = sdf.format(new Date(timestamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }
}
