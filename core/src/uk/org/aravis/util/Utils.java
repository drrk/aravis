package uk.org.aravis.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;


public class Utils
{

    public static Vector toVector(Object[] array)
    {
        return new Vector(Arrays.asList(array));
    }

    public static Vector toVector(int[] array)
    {
        Vector v = new Vector();
        for (int i = 0; i < array.length; i++)
        {
            v.add(new Integer(array[i]));
        }
        return v;
    }

    public static Vector toVector(float[] array)
    {
        Vector v = new Vector();
        for (int i = 0; i < array.length; i++)
        {
            v.add(new Float(array[i]));
        }
        return v;
    }

    public static Vector toVector(boolean[] array)
    {
        Vector v = new Vector();
        for (int i = 0; i < array.length; i++)
        {
            v.add(new Boolean(array[i]));
        }
        return v;
    }

    public static Vector toVector(char[] array)
    {
        Vector v = new Vector();
        for (int i = 0; i < array.length; i++)
        {
            v.add(new Character(array[i]));
        }
        return v;
    }

    public static Vector toVector(short[] array)
    {
        Vector v = new Vector();
        for (int i = 0; i < array.length; i++)
        {
            v.add(new Short(array[i]));
        }
        return v;
    }

    public static Vector toVector(byte[] array)
    {
        Vector v = new Vector();
        for (int i = 0; i < array.length; i++)
        {
            v.add(new Byte(array[i]));
        }
        return v;
    }

    public static Vector toVector(double[] array)
    {
        Vector v = new Vector();
        for (int i = 0; i < array.length; i++)
        {
            v.add(new Double(array[i]));
        }
        return v;
    }

    public static Vector toVector(long[] array)
    {
        Vector v = new Vector();
        for (int i = 0; i < array.length; i++)
        {
            v.add(new Long(array[i]));
        }
        return v;
    }

    public static String getTimestamp()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yy HH:mm:ss.SSS");
        String time = fmt.format(Calendar.getInstance().getTime());
        return time;
    }

    private static SimpleDateFormat jadisDateFormat = null;

    private static SimpleDateFormat jadisDateFormat()
    {
        if (jadisDateFormat == null)
        {
            jadisDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        }
        return jadisDateFormat;
    }

    public static Date stringToDate(String date) throws ParseException
    {
        return jadisDateFormat().parse(date);
    }

}
