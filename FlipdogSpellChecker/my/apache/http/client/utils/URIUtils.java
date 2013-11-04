package my.apache.http.client.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Stack;
import my.apache.http.HttpHost;
import my.apache.http.annotation.Immutable;

@Immutable
public class URIUtils
{
  @Deprecated
  public static URI createURI(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
    throws URISyntaxException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramString2 != null)
    {
      if (paramString1 != null)
      {
        localStringBuilder.append(paramString1);
        localStringBuilder.append("://");
      }
      localStringBuilder.append(paramString2);
      if (paramInt > 0)
      {
        localStringBuilder.append(':');
        localStringBuilder.append(paramInt);
      }
    }
    if ((paramString3 == null) || (!paramString3.startsWith("/")))
      localStringBuilder.append('/');
    if (paramString3 != null)
      localStringBuilder.append(paramString3);
    if (paramString4 != null)
    {
      localStringBuilder.append('?');
      localStringBuilder.append(paramString4);
    }
    if (paramString5 != null)
    {
      localStringBuilder.append('#');
      localStringBuilder.append(paramString5);
    }
    return new URI(localStringBuilder.toString());
  }

  public static HttpHost extractHost(URI paramURI)
  {
    if (paramURI == null);
    while (true)
    {
      return null;
      if (!paramURI.isAbsolute())
        continue;
      int i = paramURI.getPort();
      String str1 = paramURI.getHost();
      label69: int k;
      int m;
      int n;
      int i1;
      label108: int i2;
      if (str1 == null)
      {
        str1 = paramURI.getAuthority();
        if (str1 != null)
        {
          int j = str1.indexOf('@');
          if (j >= 0)
          {
            if (str1.length() <= j + 1)
              break label164;
            str1 = str1.substring(j + 1);
          }
          if (str1 != null)
          {
            k = str1.indexOf(':');
            if (k >= 0)
            {
              m = k + 1;
              n = 0;
              i1 = m;
              if (i1 < str1.length())
                break label169;
              if (n > 0)
                i2 = m + n;
            }
          }
        }
      }
      try
      {
        int i3 = Integer.parseInt(str1.substring(m, i2));
        i = i3;
        label136: str1 = str1.substring(0, k);
        String str2 = paramURI.getScheme();
        if (str1 == null)
          continue;
        return new HttpHost(str1, i, str2);
        label164: str1 = null;
        break label69;
        label169: if (!Character.isDigit(str1.charAt(i1)))
          break label108;
        n++;
        i1++;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        break label136;
      }
    }
  }

  private static URI removeDotSegments(URI paramURI)
  {
    String str1 = paramURI.getPath();
    if ((str1 == null) || (str1.indexOf("/.") == -1))
      return paramURI;
    String[] arrayOfString = str1.split("/");
    Stack localStack = new Stack();
    int i = 0;
    StringBuilder localStringBuilder;
    Iterator localIterator;
    if (i >= arrayOfString.length)
    {
      localStringBuilder = new StringBuilder();
      localIterator = localStack.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        try
        {
          URI localURI = new URI(paramURI.getScheme(), paramURI.getAuthority(), localStringBuilder.toString(), paramURI.getQuery(), paramURI.getFragment());
          return localURI;
        }
        catch (URISyntaxException localURISyntaxException)
        {
          throw new IllegalArgumentException(localURISyntaxException);
        }
        if ((arrayOfString[i].length() != 0) && (!".".equals(arrayOfString[i])))
        {
          if (!"..".equals(arrayOfString[i]))
            break label168;
          if (!localStack.isEmpty())
            localStack.pop();
        }
        while (true)
        {
          i++;
          break;
          label168: localStack.push(arrayOfString[i]);
        }
      }
      String str2 = (String)localIterator.next();
      localStringBuilder.append('/').append(str2);
    }
  }

  public static URI resolve(URI paramURI, String paramString)
  {
    return resolve(paramURI, URI.create(paramString));
  }

  public static URI resolve(URI paramURI1, URI paramURI2)
  {
    if (paramURI1 == null)
      throw new IllegalArgumentException("Base URI may nor be null");
    if (paramURI2 == null)
      throw new IllegalArgumentException("Reference URI may nor be null");
    String str1 = paramURI2.toString();
    if (str1.startsWith("?"))
      return resolveReferenceStartingWithQueryString(paramURI1, paramURI2);
    if (str1.length() == 0);
    for (int i = 1; ; i = 0)
    {
      if (i != 0)
        paramURI2 = URI.create("#");
      URI localURI = paramURI1.resolve(paramURI2);
      if (i != 0)
      {
        String str2 = localURI.toString();
        localURI = URI.create(str2.substring(0, str2.indexOf('#')));
      }
      return removeDotSegments(localURI);
    }
  }

  private static URI resolveReferenceStartingWithQueryString(URI paramURI1, URI paramURI2)
  {
    String str = paramURI1.toString();
    if (str.indexOf('?') > -1)
      str = str.substring(0, str.indexOf('?'));
    return URI.create(str + paramURI2.toString());
  }

  public static URI rewriteURI(URI paramURI)
    throws URISyntaxException
  {
    if (paramURI == null)
      throw new IllegalArgumentException("URI may not be null");
    if ((paramURI.getFragment() != null) || (paramURI.getUserInfo() != null) || (paramURI.getPath() == null) || (paramURI.getPath().length() == 0))
    {
      URIBuilder localURIBuilder = new URIBuilder(paramURI);
      localURIBuilder.setFragment(null).setUserInfo(null);
      if ((localURIBuilder.getPath() == null) || (localURIBuilder.getPath().length() == 0))
        localURIBuilder.setPath("/");
      paramURI = localURIBuilder.build();
    }
    return paramURI;
  }

  public static URI rewriteURI(URI paramURI, HttpHost paramHttpHost)
    throws URISyntaxException
  {
    return rewriteURI(paramURI, paramHttpHost, false);
  }

  public static URI rewriteURI(URI paramURI, HttpHost paramHttpHost, boolean paramBoolean)
    throws URISyntaxException
  {
    if (paramURI == null)
      throw new IllegalArgumentException("URI may not be null");
    URIBuilder localURIBuilder = new URIBuilder(paramURI);
    if (paramHttpHost != null)
    {
      localURIBuilder.setScheme(paramHttpHost.getSchemeName());
      localURIBuilder.setHost(paramHttpHost.getHostName());
      localURIBuilder.setPort(paramHttpHost.getPort());
    }
    while (true)
    {
      if (paramBoolean)
        localURIBuilder.setFragment(null);
      if ((localURIBuilder.getPath() == null) || (localURIBuilder.getPath().length() == 0))
        localURIBuilder.setPath("/");
      return localURIBuilder.build();
      localURIBuilder.setScheme(null);
      localURIBuilder.setHost(null);
      localURIBuilder.setPort(-1);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.utils.URIUtils
 * JD-Core Version:    0.6.2
 */