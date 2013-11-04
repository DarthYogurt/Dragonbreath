package my.apache.http.impl.cookie;

import java.lang.ref.SoftReference;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import my.apache.http.annotation.Immutable;

@Immutable
public final class DateUtils
{
  private static final String[] DEFAULT_PATTERNS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy" };
  private static final Date DEFAULT_TWO_DIGIT_YEAR_START = localCalendar.getTime();
  public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
  public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
  public static final String PATTERN_RFC1036 = "EEE, dd-MMM-yy HH:mm:ss zzz";
  public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

  static
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeZone(GMT);
    localCalendar.set(2000, 0, 1, 0, 0, 0);
    localCalendar.set(14, 0);
  }

  public static void clearThreadLocal()
  {
    DateFormatHolder.clearThreadLocal();
  }

  public static String formatDate(Date paramDate)
  {
    return formatDate(paramDate, "EEE, dd MMM yyyy HH:mm:ss zzz");
  }

  public static String formatDate(Date paramDate, String paramString)
  {
    if (paramDate == null)
      throw new IllegalArgumentException("date is null");
    if (paramString == null)
      throw new IllegalArgumentException("pattern is null");
    return DateFormatHolder.formatFor(paramString).format(paramDate);
  }

  public static Date parseDate(String paramString)
    throws DateParseException
  {
    return parseDate(paramString, null, null);
  }

  public static Date parseDate(String paramString, String[] paramArrayOfString)
    throws DateParseException
  {
    return parseDate(paramString, paramArrayOfString, null);
  }

  public static Date parseDate(String paramString, String[] paramArrayOfString, Date paramDate)
    throws DateParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("dateValue is null");
    if (paramArrayOfString == null)
      paramArrayOfString = DEFAULT_PATTERNS;
    if (paramDate == null)
      paramDate = DEFAULT_TWO_DIGIT_YEAR_START;
    if ((paramString.length() > 1) && (paramString.startsWith("'")) && (paramString.endsWith("'")))
      paramString = paramString.substring(1, -1 + paramString.length());
    int i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        throw new DateParseException("Unable to parse the date " + paramString);
      SimpleDateFormat localSimpleDateFormat = DateFormatHolder.formatFor(paramArrayOfString[j]);
      localSimpleDateFormat.set2DigitYearStart(paramDate);
      ParsePosition localParsePosition = new ParsePosition(0);
      Date localDate = localSimpleDateFormat.parse(paramString, localParsePosition);
      if (localParsePosition.getIndex() != 0)
        return localDate;
    }
  }

  static final class DateFormatHolder
  {
    private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal()
    {
      protected SoftReference<Map<String, SimpleDateFormat>> initialValue()
      {
        return new SoftReference(new HashMap());
      }
    };

    public static void clearThreadLocal()
    {
      THREADLOCAL_FORMATS.remove();
    }

    public static SimpleDateFormat formatFor(String paramString)
    {
      Object localObject = (Map)((SoftReference)THREADLOCAL_FORMATS.get()).get();
      if (localObject == null)
      {
        localObject = new HashMap();
        THREADLOCAL_FORMATS.set(new SoftReference(localObject));
      }
      SimpleDateFormat localSimpleDateFormat = (SimpleDateFormat)((Map)localObject).get(paramString);
      if (localSimpleDateFormat == null)
      {
        localSimpleDateFormat = new SimpleDateFormat(paramString, Locale.US);
        localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        ((Map)localObject).put(paramString, localSimpleDateFormat);
      }
      return localSimpleDateFormat;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.DateUtils
 * JD-Core Version:    0.6.2
 */