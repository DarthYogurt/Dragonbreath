package my.apache.http.cookie;

import java.io.Serializable;
import java.util.Comparator;
import my.apache.http.annotation.Immutable;

@Immutable
public class CookieIdentityComparator
  implements Serializable, Comparator<Cookie>
{
  private static final long serialVersionUID = 4466565437490631532L;

  public int compare(Cookie paramCookie1, Cookie paramCookie2)
  {
    int i = paramCookie1.getName().compareTo(paramCookie2.getName());
    String str3;
    String str4;
    if (i == 0)
    {
      str3 = paramCookie1.getDomain();
      if (str3 != null)
        break label110;
      str3 = "";
      str4 = paramCookie2.getDomain();
      if (str4 != null)
        break label146;
      str4 = "";
    }
    while (true)
    {
      i = str3.compareToIgnoreCase(str4);
      if (i == 0)
      {
        String str1 = paramCookie1.getPath();
        if (str1 == null)
          str1 = "/";
        String str2 = paramCookie2.getPath();
        if (str2 == null)
          str2 = "/";
        i = str1.compareTo(str2);
      }
      return i;
      label110: if (str3.indexOf('.') != -1)
        break;
      str3 = str3 + ".local";
      break;
      label146: if (str4.indexOf('.') == -1)
        str4 = str4 + ".local";
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.cookie.CookieIdentityComparator
 * JD-Core Version:    0.6.2
 */