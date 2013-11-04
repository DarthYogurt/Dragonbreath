package my.apache.http.client.protocol;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import my.apache.http.Header;
import my.apache.http.HeaderIterator;
import my.apache.http.HttpException;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseInterceptor;
import my.apache.http.annotation.Immutable;
import my.apache.http.client.CookieStore;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.CookieSpec;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Immutable
public class ResponseProcessCookies
  implements HttpResponseInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  private void processCookies(HeaderIterator paramHeaderIterator, CookieSpec paramCookieSpec, CookieOrigin paramCookieOrigin, CookieStore paramCookieStore)
  {
    while (true)
    {
      if (!paramHeaderIterator.hasNext())
        return;
      Header localHeader = paramHeaderIterator.nextHeader();
      try
      {
        Iterator localIterator = paramCookieSpec.parse(localHeader, paramCookieOrigin).iterator();
        while (localIterator.hasNext())
        {
          Cookie localCookie = (Cookie)localIterator.next();
          try
          {
            paramCookieSpec.validate(localCookie, paramCookieOrigin);
            paramCookieStore.addCookie(localCookie);
            if (!this.log.isDebugEnabled())
              continue;
            this.log.debug("Cookie accepted: \"" + localCookie + "\". ");
          }
          catch (MalformedCookieException localMalformedCookieException2)
          {
          }
          if (this.log.isWarnEnabled())
            this.log.warn("Cookie rejected: \"" + localCookie + "\". " + localMalformedCookieException2.getMessage());
        }
      }
      catch (MalformedCookieException localMalformedCookieException1)
      {
      }
      if (this.log.isWarnEnabled())
        this.log.warn("Invalid cookie header: \"" + localHeader + "\". " + localMalformedCookieException1.getMessage());
    }
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    CookieSpec localCookieSpec = (CookieSpec)paramHttpContext.getAttribute("http.cookie-spec");
    if (localCookieSpec == null)
      this.log.debug("Cookie spec not specified in HTTP context");
    CookieStore localCookieStore;
    CookieOrigin localCookieOrigin;
    do
    {
      return;
      localCookieStore = (CookieStore)paramHttpContext.getAttribute("http.cookie-store");
      if (localCookieStore == null)
      {
        this.log.debug("Cookie store not specified in HTTP context");
        return;
      }
      localCookieOrigin = (CookieOrigin)paramHttpContext.getAttribute("http.cookie-origin");
      if (localCookieOrigin == null)
      {
        this.log.debug("Cookie origin not specified in HTTP context");
        return;
      }
      processCookies(paramHttpResponse.headerIterator("Set-Cookie"), localCookieSpec, localCookieOrigin, localCookieStore);
    }
    while (localCookieSpec.getVersion() <= 0);
    processCookies(paramHttpResponse.headerIterator("Set-Cookie2"), localCookieSpec, localCookieOrigin, localCookieStore);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.protocol.ResponseProcessCookies
 * JD-Core Version:    0.6.2
 */