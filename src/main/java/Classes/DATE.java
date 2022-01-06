/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : All related date functions.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */

package Classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

//Toutes les méthodes de cette classe sont pour les foncantilites liées à la date

public class DATE {
    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy    HH-mm-ss";

    public static String ChangeFormat(String strDate) {
        SimpleDateFormat format1 = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(date);
    }

    public static String MonthNameByNumber(String MonthNumber) {
        switch (MonthNumber) {
            case "01":
                return "janvier";
            case "02":
                return "février";
            case "03":
                return "Mars";
            case "04":
                return "avril";
            case "05":
                return "mai";
            case "06":
                return "juin";
            case "07":
                return "juillet";
            case "08":
                return "août";
            case "09":
                return "septembre";
            case "10":
                return "octobre";
            case "11":
                return "novembre";
            case "12":
                return "décembre";
            default:
                return "";
        }
    }

    public static String GetDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    public static String GetDayNum() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        return formatter.format(date);
    }

    public static String formatMonth(int month) {
        DateFormat formatter = new SimpleDateFormat("MMMM", new Locale("fr"));
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month - 1);
        return formatter.format(calendar.getTime());
    }

    public static String GetMonthNum() {
        Date d = new Date();
        SimpleDateFormat ffr = new SimpleDateFormat("MM", new Locale("fr"));
        return ffr.format(d).toUpperCase(Locale.ROOT);
    }

    public static String GetYear() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(date);
    }

    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public static String optionYear() {
        StringBuilder opt = new StringBuilder();
        for (int i = 1990; i <= Integer.parseInt(GetYear()); i++)
            if (i == Integer.parseInt(GetYear()))
                opt.append("<option selected value=\"").append(i).append("\">").append(i).append("</option>\n");
            else opt.append("<option value=\"").append(i).append("\">").append(i).append("</option>\n");
        return opt.toString();
    }

    public static String optionMonth() {
        String[] month = {"janvier", "février", "Mars", "avril", "mai", "juin", "juillet", "août", "septembre",
                "octobre","novembre", "décembre"};
        StringBuilder opt = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            if (i == Integer.parseInt(GetMonthNum()) - 1)
                opt.append("<option selected value=\"").append(String.format("%02d", i + 1)).append("\">")
                        .append(month[i]).append("</option>\n");
            else opt.append("<option value=\"").append(String.format("%02d", i + 1)).append("\">").append(month[i])
                    .append("</option>\n");

        }
        return opt.toString();
    }
}

