package my.apache.http.client.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import my.apache.http.Consts;
import my.apache.http.NameValuePair;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.conn.util.InetAddressUtils;
import my.apache.http.message.BasicNameValuePair;

@NotThreadSafe
public class URIBuilder
{
  private String encodedAuthority;
  private String encodedFragment;
  private String encodedPath;
  private String encodedQuery;
  private String encodedSchemeSpecificPart;
  private String encodedUserInfo;
  private String fragment;
  private String host;
  private String path;
  private int port;
  private List<NameValuePair> queryParams;
  private String scheme;
  private String userInfo;

  public URIBuilder()
  {
    this.port = -1;
  }

  public URIBuilder(String paramString)
    throws URISyntaxException
  {
    digestURI(new URI(paramString));
  }

  public URIBuilder(URI paramURI)
  {
    digestURI(paramURI);
  }

  private String buildString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.scheme != null)
      localStringBuilder.append(this.scheme).append(':');
    if (this.encodedSchemeSpecificPart != null)
    {
      localStringBuilder.append(this.encodedSchemeSpecificPart);
      if (this.encodedFragment == null)
        break label317;
      localStringBuilder.append("#").append(this.encodedFragment);
    }
    while (true)
    {
      return localStringBuilder.toString();
      if (this.encodedAuthority != null)
      {
        localStringBuilder.append("//").append(this.encodedAuthority);
        label92: if (this.encodedPath == null)
          break label263;
        localStringBuilder.append(normalizePath(this.encodedPath));
      }
      while (true)
      {
        if (this.encodedQuery == null)
          break label289;
        localStringBuilder.append("?").append(this.encodedQuery);
        break;
        if (this.host == null)
          break label92;
        localStringBuilder.append("//");
        if (this.encodedUserInfo != null)
        {
          localStringBuilder.append(this.encodedUserInfo).append("@");
          label170: if (!InetAddressUtils.isIPv6Address(this.host))
            break label251;
          localStringBuilder.append("[").append(this.host).append("]");
        }
        while (true)
        {
          if (this.port < 0)
            break label261;
          localStringBuilder.append(":").append(this.port);
          break;
          if (this.userInfo == null)
            break label170;
          localStringBuilder.append(encodeUserInfo(this.userInfo)).append("@");
          break label170;
          label251: localStringBuilder.append(this.host);
        }
        label261: break label92;
        label263: if (this.path != null)
          localStringBuilder.append(encodePath(normalizePath(this.path)));
      }
      label289: if (this.queryParams == null)
        break;
      localStringBuilder.append("?").append(encodeQuery(this.queryParams));
      break;
      label317: if (this.fragment != null)
        localStringBuilder.append("#").append(encodeFragment(this.fragment));
    }
  }

  private void digestURI(URI paramURI)
  {
    this.scheme = paramURI.getScheme();
    this.encodedSchemeSpecificPart = paramURI.getRawSchemeSpecificPart();
    this.encodedAuthority = paramURI.getRawAuthority();
    this.host = paramURI.getHost();
    this.port = paramURI.getPort();
    this.encodedUserInfo = paramURI.getRawUserInfo();
    this.userInfo = paramURI.getUserInfo();
    this.encodedPath = paramURI.getRawPath();
    this.path = paramURI.getPath();
    this.encodedQuery = paramURI.getRawQuery();
    this.queryParams = parseQuery(paramURI.getRawQuery(), Consts.UTF_8);
    this.encodedFragment = paramURI.getRawFragment();
    this.fragment = paramURI.getFragment();
  }

  private String encodeFragment(String paramString)
  {
    return URLEncodedUtils.encFragment(paramString, Consts.UTF_8);
  }

  private String encodePath(String paramString)
  {
    return URLEncodedUtils.encPath(paramString, Consts.UTF_8);
  }

  private String encodeQuery(List<NameValuePair> paramList)
  {
    return URLEncodedUtils.format(paramList, Consts.UTF_8);
  }

  private String encodeUserInfo(String paramString)
  {
    return URLEncodedUtils.encUserInfo(paramString, Consts.UTF_8);
  }

  private static String normalizePath(String paramString)
  {
    if (paramString == null)
    {
      paramString = null;
      break label18;
      label6: return paramString;
    }
    for (int i = 0; ; i++)
    {
      if (i >= paramString.length())
      {
        label18: if (i <= 1)
          break label6;
        return paramString.substring(i - 1);
      }
      if (paramString.charAt(i) != '/')
        break;
    }
  }

  private List<NameValuePair> parseQuery(String paramString, Charset paramCharset)
  {
    if ((paramString != null) && (paramString.length() > 0))
      return URLEncodedUtils.parse(paramString, paramCharset);
    return null;
  }

  public URIBuilder addParameter(String paramString1, String paramString2)
  {
    if (this.queryParams == null)
      this.queryParams = new ArrayList();
    this.queryParams.add(new BasicNameValuePair(paramString1, paramString2));
    this.encodedQuery = null;
    this.encodedSchemeSpecificPart = null;
    return this;
  }

  public URI build()
    throws URISyntaxException
  {
    return new URI(buildString());
  }

  public String getFragment()
  {
    return this.fragment;
  }

  public String getHost()
  {
    return this.host;
  }

  public String getPath()
  {
    return this.path;
  }

  public int getPort()
  {
    return this.port;
  }

  public List<NameValuePair> getQueryParams()
  {
    if (this.queryParams != null)
      return new ArrayList(this.queryParams);
    return new ArrayList();
  }

  public String getScheme()
  {
    return this.scheme;
  }

  public String getUserInfo()
  {
    return this.userInfo;
  }

  public URIBuilder removeQuery()
  {
    this.queryParams = null;
    this.encodedQuery = null;
    this.encodedSchemeSpecificPart = null;
    return this;
  }

  public URIBuilder setFragment(String paramString)
  {
    this.fragment = paramString;
    this.encodedFragment = null;
    return this;
  }

  public URIBuilder setHost(String paramString)
  {
    this.host = paramString;
    this.encodedSchemeSpecificPart = null;
    this.encodedAuthority = null;
    return this;
  }

  public URIBuilder setParameter(String paramString1, String paramString2)
  {
    if (this.queryParams == null)
      this.queryParams = new ArrayList();
    Iterator localIterator;
    if (!this.queryParams.isEmpty())
      localIterator = this.queryParams.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.queryParams.add(new BasicNameValuePair(paramString1, paramString2));
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        return this;
      }
      if (((NameValuePair)localIterator.next()).getName().equals(paramString1))
        localIterator.remove();
    }
  }

  public URIBuilder setPath(String paramString)
  {
    this.path = paramString;
    this.encodedSchemeSpecificPart = null;
    this.encodedPath = null;
    return this;
  }

  public URIBuilder setPort(int paramInt)
  {
    if (paramInt < 0)
      paramInt = -1;
    this.port = paramInt;
    this.encodedSchemeSpecificPart = null;
    this.encodedAuthority = null;
    return this;
  }

  public URIBuilder setQuery(String paramString)
  {
    this.queryParams = parseQuery(paramString, Consts.UTF_8);
    this.encodedQuery = null;
    this.encodedSchemeSpecificPart = null;
    return this;
  }

  public URIBuilder setScheme(String paramString)
  {
    this.scheme = paramString;
    return this;
  }

  public URIBuilder setUserInfo(String paramString)
  {
    this.userInfo = paramString;
    this.encodedSchemeSpecificPart = null;
    this.encodedAuthority = null;
    this.encodedUserInfo = null;
    return this;
  }

  public URIBuilder setUserInfo(String paramString1, String paramString2)
  {
    return setUserInfo(paramString1 + ':' + paramString2);
  }

  public String toString()
  {
    return buildString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.utils.URIBuilder
 * JD-Core Version:    0.6.2
 */