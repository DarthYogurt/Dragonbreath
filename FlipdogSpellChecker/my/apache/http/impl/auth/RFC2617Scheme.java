package my.apache.http.impl.auth;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import my.apache.http.HeaderElement;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.auth.ChallengeState;
import my.apache.http.auth.MalformedChallengeException;
import my.apache.http.message.BasicHeaderValueParser;
import my.apache.http.message.HeaderValueParser;
import my.apache.http.message.ParserCursor;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class RFC2617Scheme extends AuthSchemeBase
{
  private final Map<String, String> params = new HashMap();

  public RFC2617Scheme()
  {
    this(null);
  }

  public RFC2617Scheme(ChallengeState paramChallengeState)
  {
    super(paramChallengeState);
  }

  public String getParameter(String paramString)
  {
    if (paramString == null)
      return null;
    return (String)this.params.get(paramString.toLowerCase(Locale.ENGLISH));
  }

  protected Map<String, String> getParameters()
  {
    return this.params;
  }

  public String getRealm()
  {
    return getParameter("realm");
  }

  protected void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
    throws MalformedChallengeException
  {
    HeaderElement[] arrayOfHeaderElement = BasicHeaderValueParser.DEFAULT.parseElements(paramCharArrayBuffer, new ParserCursor(paramInt1, paramCharArrayBuffer.length()));
    if (arrayOfHeaderElement.length == 0)
      throw new MalformedChallengeException("Authentication challenge is empty");
    this.params.clear();
    int i = arrayOfHeaderElement.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      HeaderElement localHeaderElement = arrayOfHeaderElement[j];
      this.params.put(localHeaderElement.getName(), localHeaderElement.getValue());
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.auth.RFC2617Scheme
 * JD-Core Version:    0.6.2
 */