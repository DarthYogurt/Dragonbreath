package com.flipdog.commons.a;

import android.database.Cursor;
import com.b.a.h;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class r
{
  public static int a(Cursor paramCursor, int paramInt1, int paramInt2)
  {
    String str = paramCursor.getString(paramInt1);
    if (str == null)
      return paramInt2;
    return Integer.parseInt(str);
  }

  public static int a(h paramh)
  {
    return a(paramh, "SELECT last_insert_rowid() AS id");
  }

  public static int a(h paramh, String paramString)
  {
    return a(paramh, paramString, new String[0]);
  }

  public static int a(h paramh, String paramString, String[] paramArrayOfString)
  {
    Cursor localCursor = paramh.a(paramString, paramArrayOfString);
    try
    {
      localCursor.moveToNext();
      int i = localCursor.getInt(0);
      return i;
    }
    finally
    {
      localCursor.close();
    }
  }

  public static int a(h paramh, String paramString, String[] paramArrayOfString, int paramInt)
  {
    Cursor localCursor = paramh.a(paramString, paramArrayOfString);
    try
    {
      if (localCursor.moveToNext())
      {
        int i = localCursor.getInt(0);
        return i;
      }
      return paramInt;
    }
    finally
    {
      localCursor.close();
    }
  }

  public static Boolean a(String paramString, Boolean paramBoolean)
  {
    if (paramString == null)
      return paramBoolean;
    return Boolean.valueOf(Boolean.parseBoolean(paramString));
  }

  public static Long a(Date paramDate)
  {
    if (paramDate == null)
      return null;
    return Long.valueOf(paramDate.getTime());
  }

  public static String a(List<String> paramList)
  {
    return a(ad.a(paramList));
  }

  public static String a(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      return null;
    String[] arrayOfString = new String[paramArrayOfString.length];
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfString.length)
        return ax.a(arrayOfString, ",");
      arrayOfString[i] = ab.a(paramArrayOfString[i]);
    }
  }

  public static Date a(long paramLong)
  {
    if (paramLong == 0L)
      return null;
    return new Date(paramLong);
  }

  public static Date a(Long paramLong)
  {
    if (paramLong == null)
      return null;
    return new Date(paramLong.longValue());
  }

  public static boolean a(Cursor paramCursor, int paramInt)
  {
    return paramCursor.isNull(paramInt);
  }

  public static Object[] a(int paramInt, String[] paramArrayOfString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localArrayList.add(paramInt);
        return localArrayList.toArray();
      }
      localArrayList.add(paramArrayOfString[j]);
    }
  }

  public static String[] a(String paramString)
  {
    int i = 0;
    if (paramString == null)
      return new String[0];
    String[] arrayOfString = ax.a(paramString, ",");
    while (true)
    {
      if (i >= arrayOfString.length)
        return arrayOfString;
      arrayOfString[i] = ab.b(arrayOfString[i]);
      i++;
    }
  }

  public static Integer b(Cursor paramCursor, int paramInt)
  {
    if (a(paramCursor, paramInt))
      return null;
    return Integer.valueOf(paramCursor.getInt(paramInt));
  }

  public static String b(Date paramDate)
  {
    if (paramDate == null)
      return null;
    return Long.valueOf(paramDate.getTime()).toString();
  }

  public static ArrayList<String> b(String paramString)
  {
    return ad.a(a(paramString));
  }

  public static Boolean c(String paramString)
  {
    if (paramString == null)
      return null;
    return Boolean.valueOf(Boolean.parseBoolean(paramString));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.r
 * JD-Core Version:    0.6.2
 */