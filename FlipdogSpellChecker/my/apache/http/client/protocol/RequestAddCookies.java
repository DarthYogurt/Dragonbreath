package my.apache.http.client.protocol;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import my.apache.http.Header;
import my.apache.http.HttpException;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.ProtocolException;
import my.apache.http.RequestLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.client.CookieStore;
import my.apache.http.client.methods.HttpUriRequest;
import my.apache.http.client.params.HttpClientParams;
import my.apache.http.conn.HttpRoutedConnection;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.CookieSpec;
import my.apache.http.cookie.CookieSpecRegistry;
import my.apache.http.cookie.SetCookie2;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Immutable
public class RequestAddCookies
  implements HttpRequestInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"))
      return;
    CookieStore localCookieStore = (CookieStore)paramHttpContext.getAttribute("http.cookie-store");
    if (localCookieStore == null)
    {
      this.log.debug("Cookie store not specified in HTTP context");
      return;
    }
    CookieSpecRegistry localCookieSpecRegistry = (CookieSpecRegistry)paramHttpContext.getAttribute("http.cookiespec-registry");
    if (localCookieSpecRegistry == null)
    {
      this.log.debug("CookieSpec registry not specified in HTTP context");
      return;
    }
    HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
    if (localHttpHost == null)
    {
      this.log.debug("Target host not set in the context");
      return;
    }
    HttpRoutedConnection localHttpRoutedConnection = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
    if (localHttpRoutedConnection == null)
    {
      this.log.debug("HTTP connection not set in the context");
      return;
    }
    String str1 = HttpClientParams.getCookiePolicy(paramHttpRequest.getParams());
    if (this.log.isDebugEnabled())
      this.log.debug("CookieSpec selected: " + str1);
    URI localURI;
    int i;
    label275: CookieOrigin localCookieOrigin;
    CookieSpec localCookieSpec;
    ArrayList localArrayList2;
    Date localDate;
    Iterator localIterator1;
    label365: Iterator localIterator3;
    label401: int j;
    int k;
    Iterator localIterator2;
    if ((paramHttpRequest instanceof HttpUriRequest))
    {
      localURI = ((HttpUriRequest)paramHttpRequest).getURI();
      String str3 = localHttpHost.getHostName();
      i = localHttpHost.getPort();
      if (i < 0)
      {
        if (localHttpRoutedConnection.getRoute().getHopCount() != 1)
          break label564;
        i = localHttpRoutedConnection.getRemotePort();
      }
      String str4 = localURI.getPath();
      boolean bool = localHttpRoutedConnection.isSecure();
      localCookieOrigin = new CookieOrigin(str3, i, str4, bool);
      localCookieSpec = localCookieSpecRegistry.getCookieSpec(str1, paramHttpRequest.getParams());
      ArrayList localArrayList1 = new ArrayList(localCookieStore.getCookies());
      localArrayList2 = new ArrayList();
      localDate = new Date();
      localIterator1 = localArrayList1.iterator();
      if (localIterator1.hasNext())
        break label612;
      if (!localArrayList2.isEmpty())
      {
        localIterator3 = localCookieSpec.formatCookies(localArrayList2).iterator();
        if (localIterator3.hasNext())
          break label759;
      }
      j = localCookieSpec.getVersion();
      if (j > 0)
      {
        k = 0;
        localIterator2 = localArrayList2.iterator();
      }
    }
    while (true)
    {
      while (true)
      {
        if (localIterator2.hasNext())
          break label778;
        if (k != 0)
        {
          Header localHeader = localCookieSpec.getVersionHeader();
          if (localHeader != null)
            paramHttpRequest.addHeader(localHeader);
        }
        paramHttpContext.setAttribute("http.cookie-spec", localCookieSpec);
        paramHttpContext.setAttribute("http.cookie-origin", localCookieOrigin);
        return;
        try
        {
          String str2 = paramHttpRequest.getRequestLine().getUri();
          localURI = new URI(str2);
        }
        catch (URISyntaxException localURISyntaxException)
        {
          ProtocolException localProtocolException = new ProtocolException("Invalid request URI: " + paramHttpRequest.getRequestLine().getUri(), localURISyntaxException);
          throw localProtocolException;
        }
      }
      label564: String str5 = localHttpHost.getSchemeName();
      if (str5.equalsIgnoreCase("http"))
      {
        i = 80;
        break label275;
      }
      if (str5.equalsIgnoreCase("https"))
      {
        i = 443;
        break label275;
      }
      i = 0;
      break label275;
      label612: Cookie localCookie1 = (Cookie)localIterator1.next();
      if (!localCookie1.isExpired(localDate))
      {
        if (!localCookieSpec.match(localCookie1, localCookieOrigin))
          break label365;
        if (this.log.isDebugEnabled())
          this.log.debug("Cookie " + localCookie1 + " match " + localCookieOrigin);
        localArrayList2.add(localCookie1);
        break label365;
      }
      if (!this.log.isDebugEnabled())
        break label365;
      this.log.debug("Cookie " + localCookie1 + " expired");
      break label365;
      label759: paramHttpRequest.addHeader((Header)localIterator3.next());
      break label401;
      label778: Cookie localCookie2 = (Cookie)localIterator2.next();
      if ((j != localCookie2.getVersion()) || (!(localCookie2 instanceof SetCookie2)))
        k = 1;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.protocol.RequestAddCookies
 * JD-Core Version:    0.6.2
 */