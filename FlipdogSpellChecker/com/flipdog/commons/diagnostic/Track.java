package com.flipdog.commons.diagnostic;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Track
{
  public static final String A = "Cert";
  private static Set<String> B = new HashSet();
  private static Set<String> C = new HashSet();
  private static f D;
  public static final String a = "StackTrace";
  public static final String b = "EventBus";
  public static final String c = "Licensing";
  public static final String d = "Async";
  public static final String e = "Line";
  public static final String f = "Ioc";
  public static final String g = "Zip";
  public static final String h = "Ads";
  public static final String i = "AdWhirl";
  public static final String j = "Keywords";
  public static final String k = "Protocol";
  public static final String l = "Sdcard";
  public static final String m = "Research";
  public static final String n = "Editor";
  public static final String o = "Eula";
  public static final String p = "Sql";
  public static final String q = "FileBrowser";
  public static final String r = "Dev";
  public static final String s = "Diagnostic";
  public static final String t = "Fragment";
  public static final String u = "CustomViewMixin";
  public static final String v = "Password";
  public static final String w = "Save";
  public static final String x = "Warning";
  public static final String y = "XmlParser";
  public static final String z = "Memory";

  private static String a(StackTraceElement paramStackTraceElement)
  {
    String str = paramStackTraceElement.getClassName();
    int i1 = str.lastIndexOf('.');
    if (i1 == -1)
      return str;
    return str.substring(i1 + 1);
  }

  private static HashSet<String> a(Collection<String> paramCollection)
  {
    return new HashSet(paramCollection);
  }

  private static void a(String paramString1, String paramString2)
  {
    if (D != null)
    {
      D.a("Track." + paramString2, paramString1);
      return;
    }
    System.out.println("Track." + paramString2 + "| " + paramString1);
  }

  private static void a(String paramString, Class<?>[] paramArrayOfClass)
  {
    it(formatStackTrace(k.a(15, paramArrayOfClass)).toString(), new String[] { paramString });
  }

  private static boolean a(String[] paramArrayOfString)
  {
    int i1 = paramArrayOfString.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return false;
      String str = paramArrayOfString[i2];
      if (B.contains(str))
        return true;
    }
  }

  private static List<String> b(String[] paramArrayOfString)
  {
    return Arrays.asList(paramArrayOfString);
  }

  public static StringBuilder formatStackTrace(List<StackTraceElement> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localStringBuilder.append("\n");
        return localStringBuilder;
      }
      StackTraceElement localStackTraceElement = (StackTraceElement)localIterator.next();
      if (localStringBuilder.length() == 0)
        localStringBuilder.append("    '->");
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = localStackTraceElement.getClassName();
      arrayOfObject[1] = localStackTraceElement.getMethodName();
      arrayOfObject[2] = Integer.valueOf(localStackTraceElement.getLineNumber());
      localStringBuilder.append(String.format("\n    %s.%s:%s", arrayOfObject));
    }
  }

  public static void init(f paramf, String[] paramArrayOfString)
  {
    D = paramf;
    B = a(b(paramArrayOfString));
  }

  public static boolean isDisabled(String paramString)
  {
    return !B.contains(paramString);
  }

  public static boolean isEnabled(String paramString)
  {
    return B.contains(paramString);
  }

  public static void it(String paramString, String[] paramArrayOfString)
  {
    int i1 = paramArrayOfString.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return;
      String str = paramArrayOfString[i2];
      if (B.contains(str))
        a(paramString, str);
    }
  }

  public static void it(Throwable paramThrowable)
  {
    if (B.contains("StackTrace"))
      paramThrowable.printStackTrace();
    if (C.contains("StackTrace"))
      j.a(paramThrowable);
  }

  public static void it(Throwable paramThrowable, String[] paramArrayOfString)
  {
    if (a(paramArrayOfString))
      paramThrowable.printStackTrace();
  }

  public static void line()
  {
    if (!isEnabled("Line"))
      return;
    long l1 = Thread.currentThread().getId();
    StackTraceElement localStackTraceElement = k.a(new Class[] { k.class, Track.class });
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Long.valueOf(l1);
    arrayOfObject[1] = a(localStackTraceElement);
    arrayOfObject[2] = localStackTraceElement.getMethodName();
    arrayOfObject[3] = Integer.valueOf(localStackTraceElement.getLineNumber());
    it(String.format(" ~>  [T %s] %s.%s:%s", arrayOfObject), new String[] { "Line" });
  }

  public static void location(String paramString, String[] paramArrayOfString)
  {
    int i1 = paramArrayOfString.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return;
      String str = paramArrayOfString[i2];
      if (B.contains(str))
      {
        it(paramString, new String[] { str });
        a(str, new Class[] { k.class, Track.class });
      }
    }
  }

  public static void me(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (B.contains(paramString1))
      a(String.format(paramString2, paramArrayOfObject), paramString1);
    if (C.contains(paramString1))
      j.a(paramString1, paramString2, paramArrayOfObject);
  }

  public static void setFileTracks(String[] paramArrayOfString)
  {
    C = a(b(paramArrayOfString));
  }

  public static void setTracks(Collection<String> paramCollection)
  {
    B = a(paramCollection);
  }

  public static void trace()
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.diagnostic.Track
 * JD-Core Version:    0.6.2
 */