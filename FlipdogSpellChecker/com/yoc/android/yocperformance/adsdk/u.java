package com.yoc.android.yocperformance.adsdk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class u
{
  private final List<String> a;
  private final boolean b;
  private final String c;

  public u(String paramString)
  {
    this.c = paramString;
    this.a = null;
    this.b = false;
  }

  public u(Collection<String> paramCollection)
  {
    this.a = new ArrayList(paramCollection);
    this.b = false;
    this.c = null;
  }

  public u(Collection<String> paramCollection, boolean paramBoolean)
  {
    this.a = new ArrayList(paramCollection);
    this.b = paramBoolean;
    this.c = null;
  }

  public u(boolean paramBoolean)
  {
    this.a = Collections.EMPTY_LIST;
    this.b = paramBoolean;
    this.c = null;
  }

  public String toString()
  {
    if (this.c != null)
      return this.c;
    StringBuilder localStringBuilder = new StringBuilder();
    if ((this.a.size() == 0) && (!this.b))
      localStringBuilder.append("ALL");
    while (true)
    {
      return localStringBuilder.toString();
      if (this.a.size() > 0)
      {
        Iterator localIterator = this.a.iterator();
        for (String str1 = ""; ; str1 = ",")
        {
          if (!localIterator.hasNext())
          {
            if (!this.b)
              break;
            localStringBuilder.append("|NOKEYWORDS");
            break;
          }
          String str2 = (String)localIterator.next();
          localStringBuilder.append(str1);
          localStringBuilder.append(str2);
        }
      }
      if (this.b)
        localStringBuilder.append("|NOKEYWORDS");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.u
 * JD-Core Version:    0.6.2
 */