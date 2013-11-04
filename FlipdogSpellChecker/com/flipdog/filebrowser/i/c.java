package com.flipdog.filebrowser.i;

import java.text.SimpleDateFormat;
import java.util.Date;

public class c
{
  private static final SimpleDateFormat a = new SimpleDateFormat("dd MMM yyyy h:mm a");
  private static final SimpleDateFormat b = new SimpleDateFormat("dd MMM yyyy");
  private static final SimpleDateFormat c = new SimpleDateFormat("dd/MM/yyyy");
  private static final SimpleDateFormat d = new SimpleDateFormat("h:mm a");

  public static final String a(Date paramDate)
  {
    if (paramDate == null)
      return null;
    return a.format(paramDate);
  }

  public static final Date a()
  {
    return new Date();
  }

  public static final String b(Date paramDate)
  {
    if (paramDate == null)
      return null;
    return b.format(paramDate);
  }

  public static final String c(Date paramDate)
  {
    if (paramDate == null)
      return null;
    return c.format(paramDate);
  }

  public static final String d(Date paramDate)
  {
    if (paramDate == null)
      return null;
    return d.format(paramDate);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.i.c
 * JD-Core Version:    0.6.2
 */