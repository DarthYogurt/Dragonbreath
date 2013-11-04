package my.apache.http.impl.auth;

import java.util.Locale;
import my.apache.http.FormattedHeader;
import my.apache.http.Header;
import my.apache.http.HttpRequest;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.auth.AuthenticationException;
import my.apache.http.auth.ChallengeState;
import my.apache.http.auth.ContextAwareAuthScheme;
import my.apache.http.auth.Credentials;
import my.apache.http.auth.MalformedChallengeException;
import my.apache.http.protocol.HTTP;
import my.apache.http.protocol.HttpContext;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class AuthSchemeBase
  implements ContextAwareAuthScheme
{
  private ChallengeState challengeState;

  public AuthSchemeBase()
  {
    this(null);
  }

  public AuthSchemeBase(ChallengeState paramChallengeState)
  {
    this.challengeState = paramChallengeState;
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    return authenticate(paramCredentials, paramHttpRequest);
  }

  public ChallengeState getChallengeState()
  {
    return this.challengeState;
  }

  public boolean isProxy()
  {
    return (this.challengeState != null) && (this.challengeState == ChallengeState.PROXY);
  }

  protected abstract void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
    throws MalformedChallengeException;

  public void processChallenge(Header paramHeader)
    throws MalformedChallengeException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    String str1 = paramHeader.getName();
    CharArrayBuffer localCharArrayBuffer;
    int i;
    label66: int j;
    if (str1.equalsIgnoreCase("WWW-Authenticate"))
    {
      this.challengeState = ChallengeState.TARGET;
      if (!(paramHeader instanceof FormattedHeader))
        break label207;
      localCharArrayBuffer = ((FormattedHeader)paramHeader).getBuffer();
      i = ((FormattedHeader)paramHeader).getValuePos();
      if ((i < localCharArrayBuffer.length()) && (HTTP.isWhitespace(localCharArrayBuffer.charAt(i))))
        break label253;
      j = i;
    }
    while (true)
    {
      if ((i >= localCharArrayBuffer.length()) || (HTTP.isWhitespace(localCharArrayBuffer.charAt(i))))
      {
        String str3 = localCharArrayBuffer.substring(j, i);
        if (str3.equalsIgnoreCase(getSchemeName()))
          break label265;
        throw new MalformedChallengeException("Invalid scheme identifier: " + str3);
        if (str1.equalsIgnoreCase("Proxy-Authenticate"))
        {
          this.challengeState = ChallengeState.PROXY;
          break;
        }
        throw new MalformedChallengeException("Unexpected header name: " + str1);
        label207: String str2 = paramHeader.getValue();
        if (str2 == null)
          throw new MalformedChallengeException("Header value is null");
        localCharArrayBuffer = new CharArrayBuffer(str2.length());
        localCharArrayBuffer.append(str2);
        i = 0;
        break label66;
        label253: i++;
        break label66;
      }
      i++;
    }
    label265: parseChallenge(localCharArrayBuffer, i, localCharArrayBuffer.length());
  }

  public String toString()
  {
    String str = getSchemeName();
    if (str != null)
      return str.toUpperCase(Locale.US);
    return super.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.auth.AuthSchemeBase
 * JD-Core Version:    0.6.2
 */