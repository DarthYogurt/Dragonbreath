package my.apache.http.impl.client;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import my.apache.http.FormattedHeader;
import my.apache.http.Header;
import my.apache.http.HttpResponse;
import my.apache.http.annotation.Immutable;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthSchemeRegistry;
import my.apache.http.auth.AuthenticationException;
import my.apache.http.auth.MalformedChallengeException;
import my.apache.http.client.AuthenticationHandler;
import my.apache.http.protocol.HTTP;
import my.apache.http.protocol.HttpContext;
import my.apache.http.util.CharArrayBuffer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
@Immutable
public abstract class AbstractAuthenticationHandler
  implements AuthenticationHandler
{
  private static final List<String> DEFAULT_SCHEME_PRIORITY = Collections.unmodifiableList(Arrays.asList(new String[] { "negotiate", "NTLM", "Digest", "Basic" }));
  private final Log log = LogFactory.getLog(getClass());

  protected List<String> getAuthPreferences()
  {
    return DEFAULT_SCHEME_PRIORITY;
  }

  protected List<String> getAuthPreferences(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    return getAuthPreferences();
  }

  protected Map<String, Header> parseChallenges(Header[] paramArrayOfHeader)
    throws MalformedChallengeException
  {
    HashMap localHashMap = new HashMap(paramArrayOfHeader.length);
    int i = paramArrayOfHeader.length;
    int j = 0;
    if (j >= i)
      return localHashMap;
    Header localHeader = paramArrayOfHeader[j];
    CharArrayBuffer localCharArrayBuffer;
    int k;
    label62: int m;
    if ((localHeader instanceof FormattedHeader))
    {
      localCharArrayBuffer = ((FormattedHeader)localHeader).getBuffer();
      k = ((FormattedHeader)localHeader).getValuePos();
      if ((k < localCharArrayBuffer.length()) && (HTTP.isWhitespace(localCharArrayBuffer.charAt(k))))
        break label193;
      m = k;
    }
    while (true)
    {
      if ((k >= localCharArrayBuffer.length()) || (HTTP.isWhitespace(localCharArrayBuffer.charAt(k))))
      {
        localHashMap.put(localCharArrayBuffer.substring(m, k).toLowerCase(Locale.US), localHeader);
        j++;
        break;
        String str = localHeader.getValue();
        if (str == null)
          throw new MalformedChallengeException("Header value is null");
        localCharArrayBuffer = new CharArrayBuffer(str.length());
        localCharArrayBuffer.append(str);
        k = 0;
        break label62;
        label193: k++;
        break label62;
      }
      k++;
    }
  }

  public AuthScheme selectScheme(Map<String, Header> paramMap, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    AuthSchemeRegistry localAuthSchemeRegistry = (AuthSchemeRegistry)paramHttpContext.getAttribute("http.authscheme-registry");
    if (localAuthSchemeRegistry == null)
      throw new IllegalStateException("AuthScheme registry not set in HTTP context");
    List localList = getAuthPreferences(paramHttpResponse, paramHttpContext);
    if (localList == null)
      localList = DEFAULT_SCHEME_PRIORITY;
    if (this.log.isDebugEnabled())
      this.log.debug("Authentication schemes in the order of preference: " + localList);
    Iterator localIterator = localList.iterator();
    Object localObject;
    while (true)
    {
      boolean bool = localIterator.hasNext();
      localObject = null;
      if (!bool);
      String str;
      while (true)
      {
        if (localObject != null)
          break label328;
        throw new AuthenticationException("Unable to respond to any of these challenges: " + paramMap);
        str = (String)localIterator.next();
        if ((Header)paramMap.get(str.toLowerCase(Locale.ENGLISH)) == null)
          break label282;
        if (this.log.isDebugEnabled())
          this.log.debug(str + " authentication scheme selected");
        try
        {
          AuthScheme localAuthScheme = localAuthSchemeRegistry.getAuthScheme(str, paramHttpResponse.getParams());
          localObject = localAuthScheme;
        }
        catch (IllegalStateException localIllegalStateException)
        {
        }
      }
      if (this.log.isWarnEnabled())
      {
        this.log.warn("Authentication scheme " + str + " not supported");
        continue;
        label282: if (this.log.isDebugEnabled())
          this.log.debug("Challenge for " + str + " authentication scheme not available");
      }
    }
    label328: return localObject;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.AbstractAuthenticationHandler
 * JD-Core Version:    0.6.2
 */