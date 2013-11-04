package com.flipdog.a;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.flipdog.a.b.b;
import com.flipdog.commons.a.as;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.commons.p.c;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class a extends c
  implements com.flipdog.a.a.a
{
  private final String c;
  private final String d;

  protected a(String paramString, int paramInt)
  {
    this.c = paramString;
    this.d = (com.flipdog.a.h.a.a(paramInt) + "_");
  }

  private String c(b paramb)
  {
    if (paramb == null)
      return null;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = e();
    arrayOfObject[1] = paramb.a;
    return String.format("%s%s", arrayOfObject);
  }

  private List<String> d()
  {
    Map localMap = this.a.getAll();
    List localList = as.b();
    String str1 = e();
    Iterator localIterator = localMap.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localList;
      String str2 = (String)localIterator.next();
      if (str2.startsWith(str1))
        localList.add(str2);
    }
  }

  private String e()
  {
    return this.d;
  }

  protected b a(com.flipdog.a.c.c.a parama, String paramString1, String paramString2)
  {
    List localList = parama.a(c(paramString2));
    String str1 = paramString2.substring(paramString1.length());
    String str2 = (String)localList.get(0);
    String str3 = (String)localList.get(1);
    String str4 = (String)localList.get(2);
    b localb = new b(str1, str2);
    localb.c = str3;
    localb.d = str4;
    return localb;
  }

  public void a(b paramb)
  {
    com.flipdog.a.c.c.a locala = new com.flipdog.a.c.c.a();
    String[] arrayOfString = new String[3];
    arrayOfString[0] = paramb.b;
    arrayOfString[1] = paramb.c;
    arrayOfString[2] = paramb.d;
    String str = locala.a(arrayOfString);
    a(c(paramb), str);
    c();
  }

  public void b()
  {
    List localList = d();
    Iterator localIterator;
    if (!localList.isEmpty())
      localIterator = localList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        c();
        String str1 = this.c;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(localList.size());
        Track.me(str1, "Clear preference: %d", arrayOfObject);
        return;
      }
      String str2 = (String)localIterator.next();
      this.b.remove(str2);
    }
  }

  public void b(b paramb)
  {
    String str = c(paramb);
    this.b.remove(str);
    c();
  }

  public List<b> b_()
  {
    List localList1 = d();
    List localList2 = as.b();
    com.flipdog.a.c.c.a locala = new com.flipdog.a.c.c.a();
    String str = e();
    Iterator localIterator = localList1.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localList2;
      b localb = a(locala, str, (String)localIterator.next());
      Track.me(this.c, "Account in pref: %s", new Object[] { localb });
      localList2.add(localb);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.a
 * JD-Core Version:    0.6.2
 */