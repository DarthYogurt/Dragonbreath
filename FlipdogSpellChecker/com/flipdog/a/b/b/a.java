package com.flipdog.a.b.b;

import com.flipdog.commons.a.ax;
import com.flipdog.commons.a.g;
import java.text.SimpleDateFormat;
import java.util.Date;

public class a
  implements Cloneable
{
  private static SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  public String a;
  public Date b;
  public b c;
  public String d;

  public a(String paramString)
  {
    this.a = paramString;
    this.d = null;
  }

  public a(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.d = paramString2;
  }

  public String a()
  {
    if (this.a == null)
      return "";
    int i = this.a.lastIndexOf('/');
    return this.a.substring(i + 1);
  }

  public a b()
  {
    try
    {
      a locala = (a)super.clone();
      return locala;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException(localCloneNotSupportedException);
    }
  }

  public boolean equals(Object paramObject)
  {
    a locala;
    if ((paramObject instanceof a))
    {
      locala = (a)paramObject;
      if (this.b == locala.b)
        break label59;
      if ((this.b != null) && (locala.b != null))
        break label39;
    }
    label39: label59: 
    do
    {
      do
        do
          return false;
        while (g.a(this.b.getTime(), locala.b.getTime()) != 0);
      while ((!ax.c(this.a, locala.a)) || (!ax.c(this.d, locala.d)));
      if (this.c == locala.c)
        return true;
    }
    while ((this.c == null) || (locala.c == null));
    return this.c.equals(locala.c);
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = a();
    String str1;
    if (this.b == null)
    {
      str1 = "";
      arrayOfObject[1] = str1;
      if (this.c != null)
        break label67;
    }
    label67: for (String str2 = null; ; str2 = this.c.a())
    {
      arrayOfObject[2] = str2;
      arrayOfObject[3] = this.d;
      return String.format("Name: %s. Date: %s. Parent: %s. Id: %s", arrayOfObject);
      str1 = e.format(this.b);
      break;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.b.b.a
 * JD-Core Version:    0.6.2
 */