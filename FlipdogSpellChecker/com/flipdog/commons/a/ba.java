package com.flipdog.commons.a;

import android.content.Context;
import android.text.format.DateFormat;
import com.flipdog.commons.i.b;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ba
{
  public static final SimpleDateFormat a = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
  public static final SimpleDateFormat b = new SimpleDateFormat("E, dd MMM yyyy h:mm a");
  public static final SimpleDateFormat c = new SimpleDateFormat("E, dd MMM yyyy H:mm");
  public static final SimpleDateFormat d = new SimpleDateFormat("E, dd MMM yyyy");
  public static final SimpleDateFormat e = new SimpleDateFormat("h:mm a");
  public static final SimpleDateFormat f = new SimpleDateFormat("H:mm");
  public static final SimpleDateFormat g = new SimpleDateFormat("dd MMM");
  public static final SimpleDateFormat h = new SimpleDateFormat("dd MMM yyyy");
  public static final SimpleDateFormat i = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
  public static final long j = 1000L;
  public static final long k = 60000L;
  public static final long l = 3600000L;
  public static final long m = 86400000L;
  public static final long n = 604800000L;
  private static SimpleDateFormat o = new SimpleDateFormat("dd MMM yyyy");
  private static SimpleDateFormat p = new SimpleDateFormat("E");
  private static Boolean q;

  public static int a(int paramInt)
  {
    return a().getYear() - paramInt;
  }

  public static long a(long paramLong)
  {
    return paramLong / 1000L;
  }

  public static String a(Date paramDate)
  {
    if (paramDate == null)
      return "";
    return p.format(paramDate);
  }

  public static String a(Date paramDate1, Date paramDate2)
  {
    if (paramDate2 == null)
      throw new RuntimeException("Specify current date.");
    if (paramDate1 == null)
      return "";
    if (paramDate1.getYear() != paramDate2.getYear())
      return h.format(paramDate1);
    if (paramDate1.getDate() != paramDate2.getDate())
      return g.format(paramDate1);
    if (b())
      return f.format(paramDate1);
    return e.format(paramDate1);
  }

  public static Date a()
  {
    return new Date();
  }

  public static Date a(int paramInt1, int paramInt2, int paramInt3)
  {
    return new Date(paramInt1 - 1900, paramInt2, paramInt3);
  }

  public static Date a(Date paramDate, int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.add(5, paramInt * -1);
    return localCalendar.getTime();
  }

  public static void a(boolean paramBoolean)
  {
    q = Boolean.valueOf(paramBoolean);
  }

  public static int b(int paramInt)
  {
    return a().getYear() - paramInt;
  }

  public static long b(long paramLong)
  {
    return paramLong / 60000L;
  }

  public static String b(Date paramDate)
  {
    if (paramDate == null)
      return "";
    if (b())
      return c.format(paramDate);
    return b.format(paramDate);
  }

  public static String b(Date paramDate1, Date paramDate2)
  {
    if (paramDate2 == null)
      throw new RuntimeException("Specify current date.");
    if (paramDate1 == null)
      return "";
    String str1;
    if (paramDate1.getYear() != paramDate2.getYear())
    {
      str1 = h.format(paramDate1);
      if (!b())
        break label100;
    }
    label100: for (String str2 = f.format(paramDate1); ; str2 = e.format(paramDate1))
    {
      return String.format("%s %s", new Object[] { str1, str2 });
      if (paramDate1.getDate() != paramDate2.getDate())
      {
        str1 = g.format(paramDate1);
        break;
      }
      str1 = "";
      break;
    }
  }

  public static Date b(Date paramDate, int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.add(12, paramInt * -1);
    return localCalendar.getTime();
  }

  public static boolean b()
  {
    if (q == null)
      q = Boolean.valueOf(DateFormat.is24HourFormat((Context)b.a(Context.class)));
    return q.booleanValue();
  }

  public static long c(long paramLong)
  {
    return paramLong / 3600000L;
  }

  public static String c(Date paramDate)
  {
    return a(paramDate, new Date());
  }

  public static Date c(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(5, paramInt);
    return localCalendar.getTime();
  }

  public static Date c(Date paramDate, int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.add(12, paramInt);
    return localCalendar.getTime();
  }

  public static boolean c(Date paramDate1, Date paramDate2)
  {
    if ((paramDate1 == null) && (paramDate2 == null));
    do
    {
      return true;
      if ((paramDate1 == null) || (paramDate2 == null))
        return false;
    }
    while ((paramDate1.getYear() == paramDate2.getYear()) && (paramDate1.getMonth() == paramDate2.getMonth()) && (paramDate1.getDate() == paramDate2.getDate()));
    return false;
  }

  public static long d(long paramLong)
  {
    return 1000L * paramLong;
  }

  public static long d(Date paramDate1, Date paramDate2)
  {
    return paramDate1.getTime() - paramDate2.getTime();
  }

  public static String d(Date paramDate)
  {
    if (paramDate == null)
      return "";
    return o.format(paramDate);
  }

  public static Date d(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(5, paramInt * -1);
    return localCalendar.getTime();
  }

  public static Date d(Date paramDate, int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.add(13, paramInt);
    return localCalendar.getTime();
  }

  public static long e(long paramLong)
  {
    return 60000L * paramLong;
  }

  public static String e(Date paramDate)
  {
    if (paramDate == null)
      return "";
    if (b())
      return f.format(paramDate);
    return e.format(paramDate);
  }

  public static Date e(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(12, paramInt * -1);
    return localCalendar.getTime();
  }

  public static Date e(Date paramDate1, Date paramDate2)
  {
    if ((paramDate1 == null) || (paramDate2 == null))
      if (paramDate1 == null)
        paramDate1 = paramDate2;
    while (paramDate1.getTime() > paramDate2.getTime())
      return paramDate1;
    return paramDate2;
  }

  public static long f(long paramLong)
  {
    return 3600000L * paramLong;
  }

  public static Date f(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(12, paramInt);
    return localCalendar.getTime();
  }

  public static Date f(Date paramDate)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    return new Date(paramDate.getTime() + localGregorianCalendar.get(15) + localGregorianCalendar.get(16));
  }

  public static Date g(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(13, paramInt);
    return localCalendar.getTime();
  }

  public static Date g(Date paramDate)
  {
    if (paramDate == null)
      return null;
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    return new Date(paramDate.getTime() - localGregorianCalendar.get(15) - localGregorianCalendar.get(16));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.ba
 * JD-Core Version:    0.6.2
 */