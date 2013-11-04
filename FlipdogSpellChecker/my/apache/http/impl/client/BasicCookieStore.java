package my.apache.http.impl.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import my.apache.http.annotation.GuardedBy;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.client.CookieStore;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieIdentityComparator;

@ThreadSafe
public class BasicCookieStore
  implements CookieStore, Serializable
{
  private static final long serialVersionUID = -7581093305228232025L;

  @GuardedBy("this")
  private final TreeSet<Cookie> cookies = new TreeSet(new CookieIdentityComparator());

  public void addCookie(Cookie paramCookie)
  {
    if (paramCookie != null);
    try
    {
      this.cookies.remove(paramCookie);
      if (!paramCookie.isExpired(new Date()))
        this.cookies.add(paramCookie);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void addCookies(Cookie[] paramArrayOfCookie)
  {
    if (paramArrayOfCookie != null);
    try
    {
      int i = paramArrayOfCookie.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
          return;
        addCookie(paramArrayOfCookie[j]);
      }
    }
    finally
    {
    }
  }

  public void clear()
  {
    try
    {
      this.cookies.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean clearExpired(Date paramDate)
  {
    boolean bool;
    if (paramDate == null)
      bool = false;
    while (true)
    {
      return bool;
      bool = false;
      try
      {
        Iterator localIterator = this.cookies.iterator();
        while (localIterator.hasNext())
          if (((Cookie)localIterator.next()).isExpired(paramDate))
          {
            localIterator.remove();
            bool = true;
          }
      }
      finally
      {
      }
    }
  }

  public List<Cookie> getCookies()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.cookies);
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String toString()
  {
    try
    {
      String str = this.cookies.toString();
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.BasicCookieStore
 * JD-Core Version:    0.6.2
 */