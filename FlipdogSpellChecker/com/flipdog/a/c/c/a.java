package com.flipdog.a.c.c;

import com.flipdog.commons.a.as;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.diagnostic.Track;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class a
{
  private c a(String paramString, int paramInt)
  {
    if ((paramString == null) || (paramInt == -1) || (paramInt + 2 > paramString.length()));
    while (true)
    {
      return null;
      int i = paramInt + 2;
      try
      {
        int j = Integer.parseInt(paramString.substring(paramInt, i));
        if (1 + (paramInt + j) <= paramString.length())
        {
          c localc = new c(null);
          int k = paramInt + 2;
          localc.b = (j + k);
          localc.a = paramString.substring(k, localc.b);
          if (ax.a(localc.a))
            localc.a = null;
          return localc;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Track.it(localNumberFormatException);
      }
    }
    return null;
  }

  public String a(Collection<String> paramCollection)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramCollection.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localStringBuilder.toString();
      String str = (String)localIterator.next();
      if (str == null)
      {
        localStringBuilder.append("00");
      }
      else
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(str.length());
        localStringBuilder.append(String.format("%02d", arrayOfObject)).append(str);
      }
    }
  }

  public String a(String[] paramArrayOfString)
  {
    return a(as.c(paramArrayOfString));
  }

  public List<String> a(String paramString)
  {
    List localList = as.b();
    c localc;
    for (int i = 0; ; i = localc.b)
    {
      localc = a(paramString, i);
      if (localc == null)
        return localList;
      localList.add(localc.a);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.c.c.a
 * JD-Core Version:    0.6.2
 */